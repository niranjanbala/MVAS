package com.coextrix.mvas.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.coextrix.mvas.model.CropImage;
import com.coextrix.mvas.model.FrameImage;
import com.coextrix.mvas.service.cropimage.CropInfo;

public class CropImageDAO {

	public CropImageDAO() {
		super();
	}

	

	public List<FrameImage> findFrameImages(final Connection connection,
			final CropInfo cropInfo) throws IOException, SQLException {
		final List<FrameImage> frameImageList = new ArrayList<FrameImage>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getCropImageQuery(cropInfo));
			FrameImage frameImage = null;
			int frameNumber;
			int previousFrameNumber = -1;
			StringBuffer frameFileName;
			CropImage cropImage;
			while (resultSet.next()) {
				frameNumber = resultSet.getInt("frame_number");
				if (frameNumber != previousFrameNumber) {
					frameFileName = new StringBuffer();
					frameFileName.append(cropInfo.getFramesFolderPath());
					frameFileName.append("\\Image_");
					frameFileName.append(formatNumber(frameNumber, 5));
					frameFileName.append(".jpg");
					if (!new File(frameFileName.toString()).isFile()) {
						continue;
					}
					frameImage = new FrameImage();
					frameImage.setFrameNumber(frameNumber);
					frameImage.setSourceFilePath(frameFileName.toString());
					frameImageList.add(frameImage);
				}
				cropImage = getCroppedImage(resultSet);
				frameImage.addCropImage(cropImage);// TODO:should not expose
				// image array instead have
				// a set method
				previousFrameNumber = frameNumber;
				CropImage.totalCropImages++;
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage(), e);
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			connection.close();
		}
		return frameImageList;
	}

	private CropImage getCroppedImage(final ResultSet resultSet)
			throws SQLException {
		long id = resultSet.getLong("id");
		long particleId = resultSet.getLong("particle_id");
		int x = resultSet.getInt("x_left");
		int y = resultSet.getInt("y_top");
		int w = resultSet.getInt("x_right") - resultSet.getInt("x_left");
		int h = resultSet.getInt("y_bottom") - resultSet.getInt("y_top");
		return new CropImage(id, particleId, x, y, w, h);
	}

	private String getCropImageQuery(final CropInfo cropInfo) {
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

	private String formatNumber(final int number, final int formatToDigits) {
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
