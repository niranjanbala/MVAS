package com.coextrix.mvas.service.cropimage;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.coextrix.mvas.model.CropImage;
import com.coextrix.mvas.model.FrameImage;

public class CropImageThread implements Runnable {

	public CropImageThread() {
		super();
		this.frameImages = new ArrayList<FrameImage>();
	}

	private List<FrameImage> frameImages;

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

	private  void cropImage(final FrameImage frameImage)throws FileNotFoundException, IOException, SQLException {
		final Image image = new ImageIcon(frameImage.getSourceFolder() + "\\"
				+ frameImage.getSourceFileName()).getImage();
		final BufferedImage bufferedImage = toBufferedImage(image);
		BufferedImage cropImg;
		File tempFile;
		OutputStream tmpOutputStream;
		int i = 0;
		for (CropImage cropImage : frameImage.getCropImages()) {
			CropImage.currentNo++;
			i++;
			cropImg = bufferedImage.getSubimage(cropImage.getX(), cropImage
					.getY(), cropImage.getW(), cropImage.getH());
			tempFile = new File(frameImage.getTargetFolder() + "\\"
					+ cropImage.getTargetFileDir()+ cropImage.getParticleId()+".jpg");
			tmpOutputStream = new FileOutputStream(tempFile);
			ImageIO.write(cropImg, frameImage.getFormatName(), tempFile);
			tmpOutputStream.close();
		}
	}

	private  static BufferedImage toBufferedImage(final Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}
		// This code ensures that all the pixels in the image are loaded
		Image imageIcon = new ImageIcon(image).getImage();
		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent
		// Pixels
		boolean hasAlpha = hasAlpha(imageIcon);
		// boolean hasAlpha = false;
		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		GraphicsEnvironment gEnv = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			if (hasAlpha) {
				transparency = Transparency.BITMASK;
			}
			// Create the buffered image
			GraphicsDevice gDevice = gEnv.getDefaultScreenDevice();
			GraphicsConfiguration gConfig = gDevice.getDefaultConfiguration();
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
		Graphics graphics = bimage.createGraphics();
		// Paint the image onto the buffered image
		graphics.drawImage(imageIcon, 0, 0, null);
		graphics.dispose();
		return bimage;
	}

	// This method returns true if the specified image has transparent pixels
	private  static boolean hasAlpha(final Image image) {
		// If buffered image, the color model is readily available
		if (image instanceof BufferedImage) {
			BufferedImage bimage = (BufferedImage) image;
			return bimage.getColorModel().hasAlpha();
		}
		// Use a pixel grabber to retrieve the image's color model;
		// grabbing a single pixel is usually sufficient
		PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, 1, 1, false);
		try {
			pixelGrabber.grabPixels();
		} catch (InterruptedException e) {
		}
		// Get the image's color model
		ColorModel colorModel = pixelGrabber.getColorModel();
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

}
