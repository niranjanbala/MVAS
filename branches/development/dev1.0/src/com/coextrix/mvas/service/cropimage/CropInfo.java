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
	public void setFramesFolderPath(String framesFolderPath) {
		this.framesFolderPath = framesFolderPath;
	}
	public String getProjectCacheDir() {
		return projectCacheDir;
	}
	public void setProjectCacheDir(String projectCacheDir) {
		this.projectCacheDir = projectCacheDir;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public double getEcdValue() {
		return ecdValue;
	}
	public void setEcdValue(double ecdValue) {
		this.ecdValue = ecdValue;
	}
	public long getLimitThumbNails() {
		return limitThumbNails;
	}
	public void setLimitThumbNails(long limitThumbNails) {
		this.limitThumbNails = limitThumbNails;
	}
	
}
