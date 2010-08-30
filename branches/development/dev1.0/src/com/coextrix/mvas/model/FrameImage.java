package com.brightwell.mvas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sripad
 * 
 */
public class FrameImage implements Serializable {

	private static final long serialVersionUID = -9179284031811051227L;
	private int frameNumber;
	private String sourceFilePath;
	private final List<CropImage> cropImages;

	public FrameImage() {
		super();
		this.cropImages = new ArrayList<CropImage>();
	}

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
	public void setFrameNumber(final int frameNumber) {
		this.frameNumber = frameNumber;
	}

	/**
	 * @return the sourceFilePath
	 */
	public String getSourceFilePath() {
		return sourceFilePath;
	}

	/**
	 * @param sourceFilePath
	 *            the sourceFilePath to set
	 */
	public void setSourceFilePath(final String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	/**
	 * @return the cropImages
	 */
	public List<CropImage> getCropImages() {
		return cropImages;
	}

	/**
	 * @param cropImage
	 *            the cropImage to add
	 */
	public void addCropImage(final CropImage cropImage) {
		this.cropImages.add(cropImage);
	}

	/**
	 * @param cropImages
	 *            the cropImages to set
	 */
	public void getCropImage(final int index) {
		this.cropImages.get(index);
	}

}