package com.brightwell.mvas.service.cropimage;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.brightwell.mvas.model.FrameImage;

/*
 * TODO cropImages() thread service has a busy wait while loop.This about an alternative
 */
public class ImageCropper {

	private Logger logger = Logger.getLogger(this.getClass());
	
	public void cropImages(final List<FrameImage> frameImages,
			final CropInfo cropInfo) {
		if(logger.isInfoEnabled()){
			logger.info("Crop Image started ");
		}
		long currentCount = 0;
		final Iterator<FrameImage> frameImagesIter = frameImages.iterator();

		int currentThread = 1;
		final long imagesPerThread = cropInfo.getTotalCropImages()/ 6;
		final CropImageThread cropImageThread1 = new CropImageThread(cropInfo);
		final CropImageThread cropImageThread2 = new CropImageThread(cropInfo);
		final CropImageThread cropImageThread3 = new CropImageThread(cropInfo);
		final CropImageThread cropImageThread4 = new CropImageThread(cropInfo);
		final CropImageThread cropImageThread5 = new CropImageThread(cropInfo);
		final CropImageThread cropImageThread6 = new CropImageThread(cropInfo);

		FrameImage frameImage;
		while (frameImagesIter.hasNext()) {
			frameImage = frameImagesIter.next();
			if (isCurrentThreadThreshHold(currentCount, frameImage
					.getCropImages().size(), imagesPerThread, currentThread)) {
				currentCount += frameImage.getCropImages().size();
			} else {
				currentThread++;
				currentCount = frameImage.getCropImages().size();
			}
			switch (currentThread) {
			case 1:
				cropImageThread1.getFrameImages().add(frameImage);
				break;
			case 2:
				cropImageThread2.getFrameImages().add(frameImage);
				break;
			case 3:
				cropImageThread3.getFrameImages().add(frameImage);
				break;
			case 4:
				cropImageThread4.getFrameImages().add(frameImage);
				break;
			case 5:
				cropImageThread5.getFrameImages().add(frameImage);
				break;
			case 6:
				cropImageThread6.getFrameImages().add(frameImage);
				break;
			default:
				break;
			}
		}
		cropImageThread1.cropImage();
		cropImageThread2.cropImage();
		cropImageThread3.cropImage();
		cropImageThread4.cropImage();
		cropImageThread5.cropImage();
		cropImageThread6.cropImage();
		
		if(logger.isInfoEnabled()){
			logger.info("Crop Image End ");
		}
	}

	private static boolean isCurrentThreadThreshHold(final long currentCount,
			final long cropImagesNo, final long imagesPerThread,
			final int currentThread) {
		return currentCount == 0
				|| imagesPerThread >= currentCount + cropImagesNo
				|| currentThread == 6;
	}
}
