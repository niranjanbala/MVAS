package com.coextrix.mvas.service.cropimage;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import com.coextrix.mvas.model.CropImage;
import com.coextrix.mvas.model.FrameImage;

public class CropImageThread implements Runnable {

	private final CropInfo cropInfo;
	private List<FrameImage> frameImages;
	private final long unitCropImages;
	private String outDirectory;
	public static int completionPercentage;

	public CropImageThread(final CropInfo cropInfo) {
		super();
		this.frameImages = new ArrayList<FrameImage>();
		this.cropInfo = cropInfo;
		this.unitCropImages = CropImage.totalCropImages / 100;
		this.outDirectory = cropInfo.getProjectCacheDir() + "\\"
				+ cropInfo.getProjectTitle() + "-Thumbnails\\";
	}

	@Override
	public void run() {
		if (!frameImages.isEmpty()) {
			try {
				for (FrameImage frmImage : frameImages) {
					cropImage(frmImage);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void cropImage(final FrameImage frameImage)
			throws FileNotFoundException, IOException, SQLException {
		//final Image image = new ImageIcon(frameImage.getSourceFilePath()).getImage();
		//final BufferedImage bufferedImage = toBufferedImage(image);
		final BufferedImage bufferedImage = toBufferedImage(frameImage.getSourceFilePath());
		BufferedImage cropImg;
		File tempFile;
		OutputStream tmpOutputStream;
		for (CropImage cropImage : frameImage.getCropImages()) {
			cropImg = bufferedImage.getSubimage(cropImage.getX(), cropImage
					.getY(), cropImage.getW(), cropImage.getH());
			tempFile = new File(outDirectory + cropImage.getParticleId()
					+ ".jpg");
			tmpOutputStream = new FileOutputStream(tempFile);
			ImageIO.write(cropImg, "jpg", tempFile);
			tmpOutputStream.close();
			CropImage.currentNo++;
			if (CropImage.currentNo == unitCropImages
					* (completionPercentage + 1)) {
				completionPercentage++;
				System.out.println("Native process: Cropping in progress,"
						+ CropImage.currentNo + "," + CropImage.totalCropImages
						+ "," + completionPercentage);
			}
		}
	}

	/**
	 * Returns BufferedImage by reading part of a JPEG from the disk
	 * Memory Efficient Jpeg Crop
	 * 
	 * @param sourceFilePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static BufferedImage toBufferedImage(final String sourceFilePath)
			throws FileNotFoundException, IOException {
		ImageReader reader = ImageIO.getImageReadersByFormatName("JPEG").next();
		ImageInputStream iis = ImageIO
				.createImageInputStream(new FileInputStream(sourceFilePath));
		reader.setInput(iis);
		// System.out.println("width = " + reader.getWidth(0));
		// System.out.println("height = " + reader.getHeight(0));
		// ImageReadParam param = reader.getDefaultReadParam();
		// param.setSourceRegion(new Rectangle(100, 100, 100, 100));
		//return reader.read(0, param);
		return reader.read(0);
	}

	private static BufferedImage toBufferedImage(final Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}
		// This code ensures that all the pixels in the image are loaded
		final Image imageIcon = new ImageIcon(image).getImage();
		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent
		// Pixels
		final boolean hasAlpha = hasAlpha(imageIcon);
		// boolean hasAlpha = false;
		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		final GraphicsEnvironment gEnv = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			if (hasAlpha) {
				transparency = Transparency.BITMASK;
			}
			// Create the buffered image
			final GraphicsDevice gDevice = gEnv.getDefaultScreenDevice();
			final GraphicsConfiguration gConfig = gDevice
					.getDefaultConfiguration();
			bimage = gConfig.createCompatibleImage(imageIcon.getWidth(null),
					imageIcon.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}
		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			if (hasAlpha) {
				type = BufferedImage.TYPE_INT_ARGB;
			}
			bimage = new BufferedImage(imageIcon.getWidth(null), imageIcon
					.getHeight(null), type);
		}
		// Copy image to buffered image
		final Graphics graphics = bimage.createGraphics();
		// Paint the image onto the buffered image
		graphics.drawImage(imageIcon, 0, 0, null);
		graphics.dispose();
		return bimage;
	}

	// This method returns true if the specified image has transparent pixels
	private static boolean hasAlpha(final Image image) {
		// If buffered image, the color model is readily available
		if (image instanceof BufferedImage) {
			final BufferedImage bimage = (BufferedImage) image;
			return bimage.getColorModel().hasAlpha();
		}
		// Use a pixel grabber to retrieve the image's color model;
		// grabbing a single pixel is usually sufficient
		final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, 1, 1,
				false);
		try {
			pixelGrabber.grabPixels();
		} catch (InterruptedException e) {
		}
		// Get the image's color model
		final ColorModel colorModel = pixelGrabber.getColorModel();
		return colorModel.hasAlpha();
	}

	public void cropImage() {
		Runnable thread = new Thread(this);
		thread.run();
	}

	/**
	 * @return the frameImages
	 */
	public List<FrameImage> getFrameImages() {
		return frameImages;
	}

	/**
	 * @param frameImages
	 *            the frameImages to set
	 */
	public void setFrameImages(final List<FrameImage> frameImages) {
		this.frameImages = frameImages;
	}

	/**
	 * @return the cropInfo
	 */
	public CropInfo getCropInfo() {
		return cropInfo;
	}

}
