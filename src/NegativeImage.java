/**
 * This class is mainly responsible for processing a group of images 
 * and converting them into inverted images respectively.
 * @author Asad Zia
 * @version 1.0
 */

import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;

public class NegativeImage 
{
	/**
	 * A function which converts a pixel into an inverted pixel by taking an average of the R, G and B values.
	 * @param pixel
	 * @return result
	 */
	public static int convertToInvertedPixel (int pixel)
	{
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		
		int result = ((255 - red) << 16) | ((255 - green) << 8) | (255 - blue);
		
		return result;
	}
	
	/**
	 * The function processes an image and converts it into an inverted format.
	 * @param image
	 * @return newImage
	 */
	public static BufferedImage processImage (BufferedImage image) 
	{
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < width; ++i) 
		{
			for (int j = 0; j < height; ++j)
			{
				int pixel = image.getRGB(i, j);				
				int result = convertToInvertedPixel(pixel);
				newImage.setRGB(i, j, result);
			}
		}
		return newImage;
	}
	
	/**
	 * A function for finding the extension of the image file.
	 * Has to be updated to check for more types of image extensions.
	 * @param file
	 * @return ext
	 */
	public static String processFileExtension (File file)
	{
		String ext = file.getName().substring(file.getName().lastIndexOf('.') + 1, file.getName().length());
		
		if (ext.equals("jpg"))
		{
			return "JPEG";
		}
		else if (ext.equals("png"))
		{
			return "PNG";
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * The function which implements the conversion of normal images to inverted images.
	 * @throws IOException
	 * @throws SecurityException
	 * @throws NullPointerException
	 */
	public static void Run_Negative () throws IOException, SecurityException, NullPointerException
	{
		try 
		{			
			JFileChooser chooser = new JFileChooser();
			chooser.setMultiSelectionEnabled(true);
			int ret = chooser.showOpenDialog(null);
			
			if (ret == JFileChooser.APPROVE_OPTION)
			{
				File theDir = new File("Negative-Images");

				// if the directory does not exist, create it
				if (!theDir.exists()) 
				{
				    System.out.println("Creating directory: " + "Negative-Images");
				    boolean result = false;

				    try
				    {
				        theDir.mkdir();
				        result = true;
				    } 
				    catch (SecurityException se)
				    {
				        se.printStackTrace();
				    }        
				    if(result) 
				    {    
				        System.out.println(theDir.getName());  
				    }
				}
				
				File [] inputImages = chooser.getSelectedFiles();
				
				for (int i = 0; i < inputImages.length; ++i)
				{
					BufferedImage image = ImageIO.read(inputImages[i]);
					BufferedImage newImage = processImage(image);
					File output = new File(theDir.getName() + "/Inverted-" + inputImages[i].getName());
					ImageIO.write(newImage, processFileExtension(inputImages[i]), output);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No Images Selected.", "Message", JOptionPane.PLAIN_MESSAGE);
				//System.out.println("No images selected. Exiting Program.");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (NullPointerException r)
		{
			r.printStackTrace();
			JOptionPane.showMessageDialog(null, "File type not supported.", "Message", JOptionPane.ERROR_MESSAGE);
			//System.out.println("Not a valid image.");
		}
	}
	
	/**
	 * The driver method of the class
	 * @param args
	 * @throws IOException 
	 * @throws NullPointerException 
	 * @throws SecurityException 
	 */
	/*public static void main (String[] args) throws SecurityException, NullPointerException, IOException
	{
		Run_Negative();
	}*/
}