/**
 * The class mainly sets up the gUI and functionality of the Image Converter Application
 * @author Asad Zia
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class FrontPage {
	
	private JFrame mainframe;
	private JLabel frontHeader;
	private JLabel frontFooter;
	private JButton EnterButton;
	
	/**
	 * The constructor of the class
	 */
	public FrontPage ()
	{
		prepareGUI();
	}
	
	/**
	 * The method which sets up the GUI initially.
	 */
	public void prepareGUI ()
	{
		mainframe = new JFrame("Image Converter");
		mainframe.setSize(800, 700);
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
		JLabel background = new JLabel(new ImageIcon(FrontPage.class.getResource("Background.jpg")));
		mainframe.add(background);
		background.setLayout(new GridLayout(3, 1));
		
		/* initializing the JLabel elements */
		frontHeader = new JLabel("", JLabel.CENTER);
		frontFooter = new JLabel("", JLabel.CENTER);
				
		/* setting up the enter button on the front screen */
		ImageIcon icon = new ImageIcon(FrontPage.class.getResource("Enter_icon.gif"));
		EnterButton = new JButton(icon);
		EnterButton.setBackground(Color.black);
		EnterButton.setOpaque(false);
		EnterButton.setContentAreaFilled(false);
		EnterButton.setBorderPainted(false);
		EnterButton.setFocusPainted(false);
		EnterButton.setActionCommand("Enter");
		
		/* adding the components to the background */
		background.add(frontHeader);
		background.add(EnterButton);
		background.add(frontFooter);
		mainframe.setVisible(true);
	}
	
	/**
	 * The method for giving functionality to the GUI.
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
				+ "A tool for adding filters to Images."
				+ "</div>"
				+ "</html>");
		
		frontFooter.setText("<html>"
				+ "<div style=\"line-height: 200px; margin: auto; margin-top: -100px; position: absolute;  font-size: 100px; left: 120px; font-weight: 600; letter-spacing: .2em; line-height: 1.6em;text-align:center; color: Black;  font-family: Sans-serif; font-size: 120%;\">"
				+ "Created By: AZ"
				+ "</div></html>");
				
		/* Add event for the Enter button */
		EnterButton.addActionListener(new ActionListener ()
		{
			public void actionPerformed( ActionEvent e)
			{
				String cmd = e.getActionCommand();
				
				if (cmd.equals("Enter"))
				{
					mainframe.dispose();
					new ConversionPage();
				}
			}
		});
		
		mainframe.setVisible(true);
	}	
	
	/*public static void main(String [] args)
	{
		FrontPage AppStartup = new FrontPage();
		AppStartup.ApplicationStartup();
	}*/
}
