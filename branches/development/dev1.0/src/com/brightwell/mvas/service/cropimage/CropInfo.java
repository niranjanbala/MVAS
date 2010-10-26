package com.brightwell.mvas.service.cropimage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CropInfo
{
	private String framesFolderPath;
	private String projectCacheDir;
	private String projectTitle;
	private double ecdValue;
	private long limitThumbNails;
	
	/**
	 * This gives the number of thumbnails to display per page
	 */
	private long numberOfThumbnailsPerPage;
	
	/**
	 * This gives the complete path of 'log4j.conf' file
	 */
	private String log4JPropertiesFilePath;
	
	private static List<Integer> missingIds = new ArrayList<Integer>();
	private static Set<Integer> missingFrameNumbers = new HashSet<Integer>();//hold all frames-ids which are missing the image
	private long totalCropImages;
	
	public String getFramesFolderPath() {
		return framesFolderPath;
	}

	public void setFramesFolderPath(final String framesFolderPath) {
		this.framesFolderPath = framesFolderPath;
	}

	public String getProjectCacheDir() {
		return projectCacheDir;
	}

	public void setProjectCacheDir(final String projectCacheDir) {
		this.projectCacheDir = projectCacheDir;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(final String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public double getEcdValue() {
		return ecdValue;
	}

	public void setEcdValue(final double ecdValue) {
		this.ecdValue = ecdValue;
	}

	public long getLimitThumbNails() {
		return limitThumbNails;
	}

	public void setLimitThumbNails(final long limitThumbNails) {
		this.limitThumbNails = limitThumbNails;
	}

	/**
	 * @return the numberOfImagesInFirstPage
	 */
	public long getNumberOfThumbnailsPerPage()
	{
		return this.numberOfThumbnailsPerPage;
	}

	/**
	 * @param numberOfImagesPerPage: is the number of thumbnails to display per page
	 */
	public void setNumberOfThumbnailsPerPage(long numberOfThumbnailsPerPageArg)
	{
		this.numberOfThumbnailsPerPage = numberOfThumbnailsPerPageArg;
	}

	/**
	 * @return the log4JPropertiesFilePath
	 */
	public String getLog4JPropertiesFilePath()
	{
		return log4JPropertiesFilePath;
	}

	/**
	 * @param log4JPropertiesFilePath: is the complete path of 'log4j.conf' properties file
	 */
	public void setLog4JPropertiesFilePath(String log4JPropertiesFilePathArg)
	{
		this.log4JPropertiesFilePath = log4JPropertiesFilePathArg;
	}

	/**
	 * @param missingIds
	 *            the missingId to set
	 */
	public static void addMissingIds(int missingId) {
		CropInfo.missingIds.add(missingId);
	}

	/**
	 * @return the missingIds
	 */
	public List<Integer> getMissingIds() {
		return CropInfo.missingIds;
	}

	/**
	 * @param missingFrameNumber
	 *            the missingFrameNumbers to set
	 */
	public static void addMissingFrameNumbers(int missingFrameNumber) {
		CropInfo.missingFrameNumbers.add(missingFrameNumber);
	}

	/**
	 * @return the missingFrameNumbers
	 */
	public static Set<Integer> getMissingFrameNumbers() {
		return CropInfo.missingFrameNumbers;
	}
	public long getTotalCropImages() {
		return totalCropImages;
	}
	public void setTotalCropImages(long totalCropImages) {
		this.totalCropImages = totalCropImages;
	}
	
}