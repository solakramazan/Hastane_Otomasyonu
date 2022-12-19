package com.Hastane_Otomasyonu;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("unused")
public class ImageBox extends JPanel{
	static final long serialVersionUID = 1000;
	private BufferedImage image;
	private String locationURL = "";
	public ImageBox()
	{
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(locationURL.isEmpty())
			return;
		try {
			image= ImageIO.read(new File(locationURL));
		} catch(IOException e) {
			
		}
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
	
	public void setLocationURL(String location) {
		this.locationURL = location;
	}
	
	public String getLocationURL() {return this.locationURL;}
}
