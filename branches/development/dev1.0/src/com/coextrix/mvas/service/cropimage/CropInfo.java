package com.coextrix.mvas.service.cropimage;

public class CropInfo {
	private String framesFolderPath;
	private String projectCacheDir;
	private String projectTitle;
	private double ecdValue;
	private long limitThumbNails;
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
}