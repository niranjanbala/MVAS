package com.brightwell.mvas.service.cropimage;

public class FrameCroppingException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FrameCroppingException(){
		super();
	}
	public FrameCroppingException(String message){
		super(message);
	}
	public FrameCroppingException(String message ,Throwable error){
		super(message,error);
	}
	public FrameCroppingException(Throwable error){
		super(error);
	}

}
