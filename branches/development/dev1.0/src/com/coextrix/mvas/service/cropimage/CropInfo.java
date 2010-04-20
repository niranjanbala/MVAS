package com.coextrix.mvas.service.cropimage;

import java.util.ArrayList;
import java.util.List;

public class CropInfo {
	private String framesFolderPath;
	private String projectCacheDir;
	private String projectTitle;
	private double ecdValue;
	private long limitThumbNails;
	private static List<Integer> missingIds = new ArrayList<Integer>();
	private static List<Integer> missingFrameNumbers = new ArrayList<Integer>();

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
	 * @param missingIds
	 *            the missingIds to set
	 */
	public static void addMissingIds(List<Integer> missingIds) {
		CropInfo.missingIds.addAll(missingIds);
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
	 * @param missingFrameNumbers
	 *            the missingFrameNumbers to set
	 */
	public static void addMissingFrameNumbers(List<Integer> missingFrameNumbers) {
		CropInfo.missingFrameNumbers.addAll(missingFrameNumbers);
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
	public static List<Integer> getMissingFrameNumbers() {
		return CropInfo.missingFrameNumbers;
	}
}