package com.brightwell.mvas.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.brightwell.mvas.model.CropImage;
import com.brightwell.mvas.model.FrameImage;
import com.brightwell.mvas.service.cropimage.CropInfo;
import com.brightwell.mvas.service.cropimage.FrameCroppingException;

public class CropImageDAO {

	private Logger logger = Logger.getLogger(this.getClass());
	
	public CropImageDAO() {
		super();
	}

	public List<FrameImage> findFrameImages(final Connection connection,
			final CropInfo cropInfo) throws  FrameCroppingException {
		
		if(logger.isInfoEnabled()){
			logger.info("Find FrameImages Start");
		}
		int totalCroppedImages = 0;
		final List<FrameImage> frameImageList = new ArrayList<FrameImage>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getCropImageQuery(cropInfo));
			FrameImage frameImage = null;
			int frameNumber;
			int previousFrameNumber = -1;
			StringBuilder frameFileName;
			CropImage cropImage;
			while (resultSet.next()) {
				frameNumber = resultSet.getInt("frame_number");
				if (frameNumber != previousFrameNumber) {//only if new frame-image
					frameFileName = new StringBuilder();
					frameFileName.append(cropInfo.getFramesFolderPath());
					frameFileName.append("\\Image_");
					frameFileName.append(formatNumber(frameNumber, 5));
					frameFileName.append(".jpg");
					if (!new File(frameFileName.toString()).isFile()) {
						CropInfo.addMissingFrameNumbers(frameNumber);
						continue;
					}
					frameImage = new FrameImage();
					frameImage.setFrameNumber(frameNumber);
					frameImage.setSourceFilePath(frameFileName.toString());
					frameImageList.add(frameImage);
				}
				cropImage = getCroppedImage(resultSet);
				frameImage.addCropImage(cropImage);
				previousFrameNumber = frameNumber;
				totalCroppedImages++;
			}
		} catch (SQLException e) {
			logger.error("Error while retrieving frame image ",e);
			throw new FrameCroppingException("Error while retrieving frame image ",e);
		} finally {
			try{
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
			}catch (SQLException e) {
				logger.error("Sql Exception ",e);
				throw new FrameCroppingException("Sql Exception ",e);
				
			}
		}
		cropInfo.setTotalCropImages(totalCroppedImages);
		if(logger.isInfoEnabled()){
			logger.info("Find FrameImages End");
		}
		return frameImageList;
	}

	public void updateThumbNails(final Connection connection,
			final CropInfo cropInfo) throws FrameCroppingException {
		if(logger.isInfoEnabled()){
			logger.info("Update thumbnial Start");
		}
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(getUpdateThumbNailsQuery(cropInfo));
		} catch (SQLException e) {
			logger.info("Error updating thumbnails", e);
			throw new FrameCroppingException("Error updating thumbnails", e);
		} finally {
			if (statement != null) {
				try{
					statement.close();
				}catch (SQLException e) {
					logger.info("Sql exception", e);
					throw new FrameCroppingException("Sql exception", e);
				}
			}
		}
		if(logger.isInfoEnabled()){
			logger.info("Update thumbnial End");
		}
	}

	private String getUpdateThumbNailsQuery(final CropInfo cropInfo) {
		StringBuffer selectSQL = new StringBuffer("UPDATE CsvData SET THUMBNAILFLAG = 1");
		StringBuffer filterCondition = new StringBuffer();
		if (cropInfo.getLimitThumbNails() != 0) {
			filterCondition.append(" ID IN(SELECT ID FROM CsvData WHERE ECD >= ");
			filterCondition.append(cropInfo.getEcdValue());
			filterCondition.append(" ORDER BY FRAME_NUMBER LIMIT ");
			filterCondition.append(cropInfo.getLimitThumbNails());
			filterCondition.append(")");
		} else {
			filterCondition.append(" ECD >= ");
			filterCondition.append(cropInfo.getEcdValue());
		}
		if (!cropInfo.getMissingIds().isEmpty()) {
			filterCondition.append(" AND ID NOT IN(");
			filterCondition.append(CropUtil.integerListToString(cropInfo.getMissingIds()));
			filterCondition.append(")");
		}
		if (!CropInfo.getMissingFrameNumbers().isEmpty()) {
			filterCondition.append(" AND FRAME_NUMBER NOT IN(");
			filterCondition.append(CropUtil.integerListToString(CropInfo.getMissingFrameNumbers()));
			filterCondition.append(")");
		}
		selectSQL.append(" WHERE ");
		selectSQL.append(filterCondition);
		selectSQL.append(";");
		//System.out.println(selectSQL.toString());
		return selectSQL.toString();
	}

	private CropImage getCroppedImage(final ResultSet resultSet)
			throws FrameCroppingException {
		try{
			long id = resultSet.getLong("id");
			long particleId = resultSet.getLong("particle_id");
			int x = resultSet.getInt("x_left");
			int y = resultSet.getInt("y_top");
			int w = resultSet.getInt("x_right") - resultSet.getInt("x_left");
			int h = resultSet.getInt("y_bottom") - resultSet.getInt("y_top");
			return new CropImage(id, particleId, x, y, w, h);
		}catch (SQLException e) {
			throw new FrameCroppingException();
		}
	}

	private String getCropImageQuery(final CropInfo cropInfo) {
		String selectSQL;
		if (cropInfo.getLimitThumbNails() == 0) {
			selectSQL = "select id,particle_id, frame_number, x_left, x_right, y_top, y_bottom from CsvData WHERE ECD>="
					+ cropInfo.getEcdValue() + " ORDER BY frame_number;";
		} else {
			selectSQL = "select id,particle_id, frame_number, x_left, x_right, y_top, y_bottom from CsvData WHERE ECD>="
					+ cropInfo.getEcdValue()
					+ " ORDER BY frame_number limit "
					+ cropInfo.getLimitThumbNails() + ";";
		}
		return selectSQL;
	}

	
	private String formatNumber(final int number, final int formatToDigits) {
		StringBuilder formatted = new StringBuilder();
		int appendZeroCount =formatToDigits - Integer.toString(number).length();
		for(int i=0;i<appendZeroCount;i++){
			formatted.append("0");
		}
		return formatted.append(number).toString();
	}

}
