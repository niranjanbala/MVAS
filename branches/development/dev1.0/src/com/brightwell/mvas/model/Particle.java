package com.brightwell.mvas.model;


/**
 * @author Sripad
 * 
 */
public class Particle {
	private java.lang.Integer id; // counter
	private java.lang.Integer particleId; // first column
	private java.lang.Integer frameNumber; // second
	private java.lang.Integer particleNumber; // third
	private java.lang.Double timestamp; // fourth column divide by 60
	private java.lang.Double eCD;
	private java.lang.Double aspectRatio;
	private java.lang.Double circularity;
	private java.lang.Double intensityMean;
	private java.lang.Double intensitySTD;
	private java.lang.Integer intensityMin;
	private java.lang.Integer intensityMax;
	private java.lang.Double area;
	private java.lang.Double perimeter;
	private java.lang.Double maxFeretDiameter;
	private java.lang.Integer xLeft;
	private java.lang.Integer xRight;
	private java.lang.Integer yTop;
	private java.lang.Integer yBottom;
	private java.lang.Integer edgeParticle;
	private java.lang.Integer thumbnailFlag; // by default 0
	private java.lang.Integer subPopulationFlag; // by default 0
	private java.lang.Integer filterFlag;// by default 0
	private java.lang.Integer filterManagerFlag; // by default 0
	/**
	 * @return the id
	 */
	public java.lang.Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	/**
	 * @return the particleId
	 */
	public java.lang.Integer getParticleId() {
		return particleId;
	}
	/**
	 * @param particleId the particleId to set
	 */
	public void setParticleId(java.lang.Integer particleId) {
		this.particleId = particleId;
	}
	/**
	 * @return the frameNumber
	 */
	public java.lang.Integer getFrameNumber() {
		return frameNumber;
	}
	/**
	 * @param frameNumber the frameNumber to set
	 */
	public void setFrameNumber(java.lang.Integer frameNumber) {
		this.frameNumber = frameNumber;
	}
	/**
	 * @return the particleNumber
	 */
	public java.lang.Integer getParticleNumber() {
		return particleNumber;
	}
	/**
	 * @param particleNumber the particleNumber to set
	 */
	public void setParticleNumber(java.lang.Integer particleNumber) {
		this.particleNumber = particleNumber;
	}
	/**
	 * @return the timestamp
	 */
	public java.lang.Double getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(java.lang.Double timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the eCD
	 */
	public java.lang.Double getECD() {
		return eCD;
	}
	/**
	 * @param ecd the eCD to set
	 */
	public void setECD(java.lang.Double ecd) {
		eCD = ecd;
	}
	/**
	 * @return the aspectRatio
	 */
	public java.lang.Double getAspectRatio() {
		return aspectRatio;
	}
	/**
	 * @param aspectRatio the aspectRatio to set
	 */
	public void setAspectRatio(java.lang.Double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}
	/**
	 * @return the circularity
	 */
	public java.lang.Double getCircularity() {
		return circularity;
	}
	/**
	 * @param circularity the circularity to set
	 */
	public void setCircularity(java.lang.Double circularity) {
		this.circularity = circularity;
	}
	/**
	 * @return the intensityMean
	 */
	public java.lang.Double getIntensityMean() {
		return intensityMean;
	}
	/**
	 * @param intensityMean the intensityMean to set
	 */
	public void setIntensityMean(java.lang.Double intensityMean) {
		this.intensityMean = intensityMean;
	}
	/**
	 * @return the intensitySTD
	 */
	public java.lang.Double getIntensitySTD() {
		return intensitySTD;
	}
	/**
	 * @param intensitySTD the intensitySTD to set
	 */
	public void setIntensitySTD(java.lang.Double intensitySTD) {
		this.intensitySTD = intensitySTD;
	}
	/**
	 * @return the intensityMin
	 */
	public java.lang.Integer getIntensityMin() {
		return intensityMin;
	}
	/**
	 * @param intensityMin the intensityMin to set
	 */
	public void setIntensityMin(java.lang.Integer intensityMin) {
		this.intensityMin = intensityMin;
	}
	/**
	 * @return the intensityMax
	 */
	public java.lang.Integer getIntensityMax() {
		return intensityMax;
	}
	/**
	 * @param intensityMax the intensityMax to set
	 */
	public void setIntensityMax(java.lang.Integer intensityMax) {
		this.intensityMax = intensityMax;
	}
	/**
	 * @return the area
	 */
	public java.lang.Double getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(java.lang.Double area) {
		this.area = area;
	}
	/**
	 * @return the perimeter
	 */
	public java.lang.Double getPerimeter() {
		return perimeter;
	}
	/**
	 * @param perimeter the perimeter to set
	 */
	public void setPerimeter(java.lang.Double perimeter) {
		this.perimeter = perimeter;
	}
	/**
	 * @return the maxFeretDiameter
	 */
	public java.lang.Double getMaxFeretDiameter() {
		return maxFeretDiameter;
	}
	/**
	 * @param maxFeretDiameter the maxFeretDiameter to set
	 */
	public void setMaxFeretDiameter(java.lang.Double maxFeretDiameter) {
		this.maxFeretDiameter = maxFeretDiameter;
	}
	/**
	 * @return the xLeft
	 */
	public java.lang.Integer getXLeft() {
		return xLeft;
	}
	/**
	 * @param left the xLeft to set
	 */
	public void setXLeft(java.lang.Integer left) {
		xLeft = left;
	}
	/**
	 * @return the xRight
	 */
	public java.lang.Integer getXRight() {
		return xRight;
	}
	/**
	 * @param right the xRight to set
	 */
	public void setXRight(java.lang.Integer right) {
		xRight = right;
	}
	/**
	 * @return the yTop
	 */
	public java.lang.Integer getYTop() {
		return yTop;
	}
	/**
	 * @param top the yTop to set
	 */
	public void setYTop(java.lang.Integer top) {
		yTop = top;
	}
	/**
	 * @return the yBottom
	 */
	public java.lang.Integer getYBottom() {
		return yBottom;
	}
	/**
	 * @param bottom the yBottom to set
	 */
	public void setYBottom(java.lang.Integer bottom) {
		yBottom = bottom;
	}
	/**
	 * @return the edgeParticle
	 */
	public java.lang.Integer getEdgeParticle() {
		return edgeParticle;
	}
	/**
	 * @param edgeParticle the edgeParticle to set
	 */
	public void setEdgeParticle(java.lang.Integer edgeParticle) {
		this.edgeParticle = edgeParticle;
	}
	/**
	 * @return the thumbnailFlag
	 */
	public java.lang.Integer getThumbnailFlag() {
		return thumbnailFlag;
	}
	/**
	 * @param thumbnailFlag the thumbnailFlag to set
	 */
	public void setThumbnailFlag(java.lang.Integer thumbnailFlag) {
		this.thumbnailFlag = thumbnailFlag;
	}
	/**
	 * @return the subPopulationFlag
	 */
	public java.lang.Integer getSubPopulationFlag() {
		return subPopulationFlag;
	}
	/**
	 * @param subPopulationFlag the subPopulationFlag to set
	 */
	public void setSubPopulationFlag(java.lang.Integer subPopulationFlag) {
		this.subPopulationFlag = subPopulationFlag;
	}
	/**
	 * @return the filterFlag
	 */
	public java.lang.Integer getFilterFlag() {
		return filterFlag;
	}
	/**
	 * @param filterFlag the filterFlag to set
	 */
	public void setFilterFlag(java.lang.Integer filterFlag) {
		this.filterFlag = filterFlag;
	}
	/**
	 * @return the filterManagerFlag
	 */
	public java.lang.Integer getFilterManagerFlag() {
		return filterManagerFlag;
	}
	/**
	 * @param filterManagerFlag the filterManagerFlag to set
	 */
	public void setFilterManagerFlag(java.lang.Integer filterManagerFlag) {
		this.filterManagerFlag = filterManagerFlag;
	}

	
}
