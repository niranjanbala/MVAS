package com.coextrix.mvas.model;

import java.io.Serializable;

/**
 * @author Sripad
 * 
 */
public class CropImage implements Serializable {

	private static final long serialVersionUID = 4247982568836891828L;
	public static int currentNo;
	private String targetFileDir;
	private long id;
	private int x;
	private int y;
	private int w;
	private int h;
	private long particleId;

	/**
	 * @return the targetFileDir
	 */
	public String getTargetFileDir() {
		return targetFileDir;
	}

	/**
	 * @param targetFileDir
	 *            the targetFileDir to set
	 */
	public void setTargetFileDir(String targetFileDir) {
		this.targetFileDir = targetFileDir;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the w
	 */
	public int getW() {
		return w;
	}

	/**
	 * @param w
	 *            the w to set
	 */
	public void setW(int w) {
		this.w = w;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}

	/**
	 * @param h
	 *            the h to set
	 */
	public void setH(int h) {
		this.h = h;
	}

	/**
	 * @return the currentNo
	 */
	public static int getCurrentNo() {
		return currentNo;
	}

	/**
	 * @param currentNo the currentNo to set
	 */
	public static void setCurrentNo(int currentNo) {
		CropImage.currentNo = currentNo;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public long getParticleId() {
		return particleId;
	}

	public void setParticleId(long particleId) {
		this.particleId = particleId;
	}
	
}
