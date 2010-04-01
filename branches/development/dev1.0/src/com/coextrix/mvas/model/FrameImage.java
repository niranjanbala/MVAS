package com.coextrix.mvas.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Sripad
 * 
 */
public class FrameImage implements Serializable {

	private static final long serialVersionUID = -9179284031811051227L;
	private int frameNumber;
	private String sourceFolder;
	private String sourceFileName;
	private String targetFolder;
	private String formatName = "jpg";
	private List<CropImage> cropImages;

	/**
	 * @return the frameNumber
	 */
	public int getFrameNumber() {
		return frameNumber;
	}

	/**
	 * @param frameNumber
	 *            the frameNumber to set
	 */
	public void setFrameNumber(int frameNumber) {
		this.frameNumber = frameNumber;
	}

	/**
	 * @return the sourceFolder
	 */
	public String getSourceFolder() {
		return sourceFolder;
	}

	/**
	 * @param sourceFolder
	 *            the sourceFolder to set
	 */
	public void setSourceFolder(String sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	/**
	 * @return the sourceFileName
	 */
	public String getSourceFileName() {
		return sourceFileName;
	}

	/**
	 * @param sourceFileName
	 *            the sourceFileName to set
	 */
	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	/**
	 * @return the targetFolder
	 */
	public String getTargetFolder() {
		return targetFolder;
	}

	/**
	 * @param targetFolder
	 *            the targetFolder to set
	 */
	public void setTargetFolder(String targetFolder) {
		this.targetFolder = targetFolder;
	}

	/**
	 * @return the formatName
	 */
	public String getFormatName() {
		return formatName;
	}

	/**
	 * @param formatName
	 *            the formatName to set
	 */
	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	/**
	 * @return the cropImages
	 */
	public List<CropImage> getCropImages() {
		return cropImages;
	}

	/**
	 * @param cropImages
	 *            the cropImages to set
	 */
	public void setCropImages(List<CropImage> cropImages) {
		this.cropImages = cropImages;
	}

}