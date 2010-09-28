package com.brightwell.mvas.service.cropimage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.brightwell.mvas.dao.CropImageDAO;
import com.brightwell.mvas.dao.CropUtil;
import com.brightwell.mvas.dao.DataSourceUtil;
import com.brightwell.mvas.model.FrameImage;

public final class CropImageService {

	private static Logger logger ;
	private static int ARG_COUNT = 5;
	private static final String LOG4J_PROPERTIES = "log4j.conf";
	
	private CropImageService() {
		super();
	}

	/**
	 * @param args
	 *            [framesFolderPath, projectCacheDir, projectTitle,
	 *            ecdValue,limitThumbNails]<br>
	 *            Example "F:\\backup\\MVAS\\Records_600K_Images"
	 *            "E:\\cache folder-path\\4324324324"
	 *            "NEWCODE_600K_All_Images_ECD_3" "3" "0"
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws FrameCroppingException 
	 */
	public static void main(final String[] args) throws  FrameCroppingException {
		long startTime = Calendar.getInstance().getTimeInMillis();
		if (args.length != ARG_COUNT) {
			throw new IllegalArgumentException(
					"Native process error:Cropping argument missmatch");
		}
		CropImageService service = new CropImageService();
		CropInfo cropInfo = service.getCropInfo(args);
		initilizeLog4j(cropInfo.getProjectCacheDir());
		logger = Logger.getLogger(CropImageService.class);
		
		if(logger.isInfoEnabled()){
			logger.info("Cropping started");
		}
		CropImageDAO cropImageDAO = new CropImageDAO();
		Connection connection = DataSourceUtil.getSQLiteConnection(cropInfo.getProjectCacheDir()
				+ "\\" + cropInfo.getProjectTitle() + ".db");
		List<FrameImage> frameImages = null;
		try{
		frameImages = cropImageDAO.findFrameImages(connection,cropInfo);
		ImageCropper cropper = new ImageCropper();
		cropper.cropImages(frameImages, cropInfo);
		cropImageDAO.updateThumbNails(connection, cropInfo);
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				throw new FrameCroppingException("Cropping failed",e);
			}
		}
		service.copyFrameImagesToProjectCache(cropInfo,frameImages);
		service.printTime(startTime); 
		if(logger.isInfoEnabled()){
			logger.info("Cropping Image End");
		}
		System.out.println("Image cropping completed :Missing frame ids="+CropUtil.integerListToString(CropInfo.getMissingFrameNumbers()));
	}

	private void copyFrameImagesToProjectCache(CropInfo cropInfo, List<FrameImage> frameImages) throws FrameCroppingException {
		String destination = cropInfo.getProjectCacheDir() + "\\"+ cropInfo.getProjectTitle() + "-Frames\\";
		for(FrameImage image:frameImages){
			File imgFile = new File(image.getSourceFilePath());
			try{
				FileChannel ic = new FileInputStream(imgFile).getChannel();
				FileChannel oc = new FileOutputStream(destination+imgFile.getName()).getChannel();
				ic.transferTo(0, ic.size(), oc);
				ic.close();
				oc.close();
			}catch (IOException e) {
				throw new FrameCroppingException("Copy images failed",e);
			}
		}
		
	}

	private void printTime(long startTime){
		//only to de-bug
		long endTime = Calendar.getInstance().getTimeInMillis();
		long timeElapsed = (endTime - startTime) / 1000;
		System.out.println("Image cropping completed -- time taken : "+ timeElapsed /60+ " min");
	}
	private CropInfo getCropInfo(String[] args) {
		CropInfo cropInfo = new CropInfo();
		String framesFolderPath = args[0];
		cropInfo.setFramesFolderPath(framesFolderPath);
		String projectCacheDir = args[1];
		cropInfo.setProjectCacheDir(projectCacheDir);
		String projectTitle = args[2];
		cropInfo.setProjectTitle(projectTitle);
		double ecdValue = Double.parseDouble(args[3]);
		cropInfo.setEcdValue(ecdValue);
		long limitThumbNails = Long.parseLong(args[4]);
		cropInfo.setLimitThumbNails(limitThumbNails);
		return cropInfo;
	}
	private static void initilizeLog4j(String cacheFolderPath){
		File file = new File(cacheFolderPath); //cacheFolderPath has project folder appended
		if(!file.exists()){
			return ;
		}
		String cacheFolder = file.getParent();//actual cache folder 
		String propertyFilePath = cacheFolder + File.separator + LOG4J_PROPERTIES;
		if(new File(propertyFilePath).exists()){//log4j configuration exist
			PropertyConfigurator.configure(propertyFilePath);
		}
	}

}