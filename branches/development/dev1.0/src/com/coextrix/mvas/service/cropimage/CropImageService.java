package com.coextrix.mvas.service.cropimage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coextrix.mvas.dao.DataSourceUtil;
import com.coextrix.mvas.model.CropImage;
import com.coextrix.mvas.model.FrameImage;

public final class CropImageService {

	private static int ARG_COUNT = 5;
	private CropImageService() {
		super();
	}

	private static long totalCropImages;

	/**
	 * @param args
	 * [framesFolderPath, projectCacheDir, projectTitle, ecdValue,limitThumbNails]<br>
	 * Example "F:\\backup\\MVAS\\Records_600K_Images" "E:\cache-folder-path" "NEWCODE_600K_All_Images_ECD_3" 3 0
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void main(final String[] args) throws ClassNotFoundException,
			SQLException, IOException {
		if (args.length != ARG_COUNT) {
			throw new IllegalArgumentException("Native process error:Cropping argument missmatch");
		}
		CropImageService service = new CropImageService();
		
		CropInfo cropInfo = service.getCropInfo(args);
		Connection connection = DataSourceUtil.getSQLiteConnection(cropInfo.getProjectCacheDir()+"\\" + cropInfo.getProjectTitle()+ ".db");
		List<FrameImage> frameImages = service.findFrameImages(connection, cropInfo);
		ImageCropper cropper = new ImageCropper();
		cropper.cropImages(frameImages,totalCropImages);
		System.out.println(new Date());
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

	private List<FrameImage> findFrameImages(final Connection connection, CropInfo cropInfo)
			throws SQLException, IOException {
		
		List<FrameImage> frameImageList = new ArrayList<FrameImage>();
		Statement statement;
		ResultSet resultSet;
		statement = connection.createStatement();
		String selectSQL = getCropImageQuery(cropInfo);
		resultSet = statement.executeQuery(selectSQL);
		
		FrameImage frameImage = null;
		//CropImage cropImage;
		int frameNumber;
		int previousFrameNumber = -1;
		while (resultSet.next()) {
			frameNumber = resultSet.getInt("frame_number");
			if(frameNumber!=previousFrameNumber){
				frameImage = new FrameImage();
				frameImage.setCropImages(new ArrayList<CropImage>());
				frameImage.setFormatName("jpg");
				frameImage.setFrameNumber(frameNumber);
				frameImage.setSourceFolder(cropInfo.getFramesFolderPath());
				frameImage.setSourceFileName("Image_"+ formatNumber(frameNumber, 5) + ".jpg");
				frameImage.setTargetFolder(cropInfo.getProjectCacheDir()+ "\\" + cropInfo.getProjectTitle() + "-Thumbnails");
				frameImage.setCropImages(new ArrayList<CropImage>());
				
				frameImageList.add(frameImage);
			}
			
			
			CropImage cropImage = getCroppedImage(resultSet);
			frameImage.getCropImages().add(cropImage);//TODO:should not expose image array instead have a set method
			previousFrameNumber = frameNumber;
			totalCropImages++;
		}
		resultSet.close();
		statement.close();
		connection.close();
		System.out.println(totalCropImages);
		return frameImageList;
	}

	private CropImage getCroppedImage(ResultSet resultSet)
			throws SQLException {
		CropImage cropImage = new CropImage();
		cropImage.setTargetFileDir("");
		cropImage.setId(resultSet.getLong("particle_id"));
		cropImage.setParticleId(resultSet.getLong("id"));
		cropImage.setX(resultSet.getInt("x_left"));
		cropImage.setY(resultSet.getInt("y_top"));
		cropImage.setW(resultSet.getInt("x_right") - resultSet.getInt("x_left"));
		cropImage.setH(resultSet.getInt("y_bottom") - resultSet.getInt("y_top"));
		return cropImage;
	}

	private String getCropImageQuery(CropInfo cropInfo) {
		String selectSQL;
		if (cropInfo.getLimitThumbNails() == 0) {
			selectSQL = "select id,particle_id, frame_number, x_left, x_right, y_top, y_bottom from CsvData WHERE ECD>="
					+ cropInfo.getEcdValue() + " ORDER BY frame_number;";
		} else {
			selectSQL = "select id, frame_number, x_left, x_right, y_top, y_bottom from CsvData WHERE ECD>="
					+ cropInfo.getEcdValue()
					+ " ORDER BY frame_number limit "
					+ cropInfo.getLimitThumbNails() + ";";
		}
		return selectSQL;
	}

	private  String formatNumber(final int number, final int formatToDigits) {
		StringBuffer formattedNumber = new StringBuffer(Integer
				.toString(number));
		int digits = formattedNumber.length();
		while (digits < formatToDigits) {
			formattedNumber = formattedNumber.insert(0, "0");
			digits++;
		}
		return formattedNumber.toString();
	}

	
}