/**
 * This class is mainly responsible for processing a group of images 
 * and converting them into inverted images respectively.
 * @author Asad Zia
 * @version 1.0
 */

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class BlurImage {
			
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
	 * The function which implements the conversion of normal images to blur images.
	 * @throws IOException
	 * @throws SecurityException
	 * @throws NullPointerException
	 */
	public static void Run_Blur () throws IOException, SecurityException, NullPointerException
	{
		try 
		{			
			JFileChooser chooser = new JFileChooser();
			chooser.setMultiSelectionEnabled(true);
			int ret = chooser.showOpenDialog(null);
			
			if (ret == JFileChooser.APPROVE_OPTION)
			{
				File theDir = new File("Blur-Images");

				// if the directory does not exist, create it
				if (!theDir.exists()) 
				{
				    System.out.println("Creating directory: " + "Blur-Images");
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
					float[] matrix = new float[900];
					for (int i1 = 0; i1 < 900; i1++)
						matrix[i1] = 1.0f/900.0f;
					
					/* TODO 
					 * Need to fix the blurring near the edges as some parts are not blurred properly.
					 */
					
					BufferedImageOp op = new ConvolveOp(new Kernel(30, 30, matrix), ConvolveOp.EDGE_NO_OP, null);
					BufferedImage blurredImage = op.filter(ImageIO.read(inputImages[i]), null);
					File output = new File(theDir.getName() + "/Blurred-" + inputImages[i].getName());
					ImageIO.write(blurredImage, processFileExtension(inputImages[i]), output);
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
		Run_Blur();
	}*/
}
