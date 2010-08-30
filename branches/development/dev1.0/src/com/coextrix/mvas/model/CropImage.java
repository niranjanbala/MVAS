package com.coextrix.mvas.model;

import java.io.Serializable;

/**
 * @author Sripad
 * 
 */
public class CropImage implements Serializable {

	private static final long serialVersionUID = 4247982568836891828L;
	//public static long totalCropImages;
	//public static long currentNo;
	private long id;
	private long particleId;
	private int x;
	private int y;
	private int w;
	private int h;

	/**
	 * @param id
	 * @param particleId
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * 
	 */
	public CropImage(long id, long particleId, int x, int y, int w, int h) {
		super();
		this.id = id;
		this.particleId = particleId;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
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
	public void setX(final int x) {
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
	public void setY(final int y) {
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
	public void setW(final int w) {
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
	public void setH(final int h) {
		this.h = h;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	public long getParticleId() {
		return particleId;
	}

	public void setParticleId(final long particleId) {
		this.particleId = particleId;
	}

}
