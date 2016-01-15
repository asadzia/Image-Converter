/**
 * The class mainly sets up the gUI and functionality of the Image Converter Application
 * @author Asad Zia
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class ConversionPage {
	
	private JFrame mainframe;
	private JLabel frontHeader;
	private JPanel buttonPanel;
	private JButton Gray;
	private JButton Negative;
	private JButton Blur;
	private JButton Exit;
	
	/**
	 * The constructor of the class.
	 */
	public ConversionPage ()
	{
		prepareGUI();
		ApplicationStartup();
	}
	
	/**
	 * The function for setting up the GUI initially.
	 */
	public void prepareGUI ()
	{
		mainframe = new JFrame("Image Converter");
		mainframe.setSize(800, 750);
		mainframe.setLocationRelativeTo(null);
		
		mainframe.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent windowEvent)
			{
				System.exit(0);
			}
		});
		
		/* adding a background image by using JLabel */
		JLabel background = new JLabel(new ImageIcon(ConversionPage.class.getResource("Background.jpg")));
		mainframe.add(background);
		background.setLayout(new GridLayout(3, 1));
		
		buttonPanel = new JPanel(new GridLayout(1, 3));
		
		/* initializing the JLabel elements */
		frontHeader = new JLabel("", JLabel.CENTER);
				
		/* setting up the gray scale button*/
		ImageIcon icon = new ImageIcon(ConversionPage.class.getResource("Gray.png"));
		Gray = new JButton(icon);
		Gray.setBackground(Color.white);
		Gray.setFocusPainted(false);
		Gray.setActionCommand("Gray");
		
		/* setting up the negative button */
		ImageIcon iconTwo = new ImageIcon(ConversionPage.class.getResource("Negative.png"));
		Negative = new JButton(iconTwo);
		Negative.setBackground(Color.white);
		Negative.setFocusPainted(false);
		Negative.setActionCommand("Negative");
		
		/* setting up the Blur button */
		ImageIcon iconThree = new ImageIcon(ConversionPage.class.getResource("Blur.jpg"));
		Blur = new JButton(iconThree);
		Blur.setBackground(Color.white);
		Blur.setFocusPainted(false);
		Blur.setActionCommand("Blur");
		
		/* setting up the exit button */
		ImageIcon iconExit = new ImageIcon(ConversionPage.class.getResource("Exit.png"));
		Exit = new JButton(iconExit);
		Exit.setBackground(Color.white);
		Exit.setFocusPainted(false);
		Exit.setActionCommand("Exit");
		
		/* add buttons to the panel */
		buttonPanel.add(Gray);
		buttonPanel.add(Negative);
		buttonPanel.add(Blur);
		
		/* adding the components to the background */
		background.add(frontHeader);
		background.add(buttonPanel);
		background.add(Exit);
		mainframe.setVisible(true);
	}
	
	/**
	 * The constructor for providing functionality to the GUI.
	 */
	public void ApplicationStartup ()
	{
		/* USe HTML tags to set and arrange the text */
		frontHeader.setText("<html>"
				+ "<div style=\"line-height: 200px; margin: auto; margin-top: -100px; position: absolute;  font-size: 100px; left: 120px; font-weight: 600; letter-spacing: .2em; line-height: 1.1em;text-align:center; color: Black;  font-family: Sans-serif, Hoefler Text, Georgia, 'Times New Roman'; font-size: 150%;\">"
				+ "IMAGE CONVERTER"
				+ "</div>"
				+ "<br>"
				+ "<div  style=\"line-height: 200px; margin: auto; margin-top: -100px; position: absolute;  font-size: 100px; left: 120px; font-weight: normal; letter-spacing: .2em; line-height: 1.1em;text-align:center; color: Green; font-family: Sans-serif, Hoefler Text, Georgia, 'Times New Roman'; font-size: 120%; \">"
				+ "Click on the type of filter you require for your Images."
				+ "</div>"
				+ "</html>");
				
		/* Adding event for the Gray button */
		Gray.addActionListener(new ActionListener ()
		{
			public void actionPerformed(ActionEvent e)
			{
				String cmd = e.getActionCommand();
				
				if (cmd.equals("Gray"))
				{
					try {
						GrayImage.Run_Gray();
					} catch (SecurityException | NullPointerException | IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		/* Adding event for the Negative button */
		Negative.addActionListener(new ActionListener ()
		{
			public void actionPerformed( ActionEvent e)
			{
				String cmd = e.getActionCommand();
				
				if (cmd.equals("Negative"))
				{
					try {
						NegativeImage.Run_Negative();
					} catch (SecurityException | NullPointerException | IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		/* Adding event for the Blur button */
		Blur.addActionListener(new ActionListener ()
		{
			public void actionPerformed( ActionEvent e)
			{
				String cmd = e.getActionCommand();
				
				if (cmd.equals("Blur"))
				{
					try {
						BlurImage.Run_Blur();
					} catch (SecurityException | NullPointerException | IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		/* Adding event for the exit button */
		Exit.addActionListener(new ActionListener ()
		{
			public void actionPerformed( ActionEvent e)
			{
				String cmd = e.getActionCommand();
				
				if (cmd.equals("Exit"))
				{
					System.exit(0);
				}
			}
		});
		
		mainframe.setVisible(true);
	}	
	
	/**
	 * The driver function
	 * @param args
	 */
	public static void main(String [] args)
	{
		FrontPage AppStartup = new FrontPage();
		AppStartup.ApplicationStartup();
	}
}
