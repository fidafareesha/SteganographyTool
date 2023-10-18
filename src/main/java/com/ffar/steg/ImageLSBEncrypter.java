package com.ffar.steg;

import java.awt.image.BufferedImage;

public class ImageLSBEncrypter {
	
	private String stringToBits(String message) {
		byte[] bytes = message.getBytes();
		
		StringBuilder bits = new StringBuilder();
		
		for(byte b: bytes) {
			for(int i=7; i>=0; i--) {
				bits.append((b >> i) & 1);
			}
		}
		return bits.toString();
	}
	BufferedImage encrypt(String message, BufferedImage input) {
		BufferedImage output = input;
		String bits = stringToBits(message);
//		String toPrint = bits.substring(0,8);
//		System.out.println("The bits string: "+toPrint);
//		System.out.println("Returned the image!!");
		int i = 0;
		
		for(int x = 0; x < input.getWidth(); x++) {
			for(int y = 0; y < input.getHeight(); y++) {
				if(i >= bits.length() - 3) {
					return output;
				}
				int pixel = input.getRGB(x, y);
				int alpha = (pixel >> 24) & 0xFF;
				int red = (pixel >> 16) & 0xFF;
				int green = (pixel >> 8) & 0xFF;
				int blue = pixel & 0xFF;
				
				red = (red & 0xFE) | bits.charAt(i);
				i++;
				green = (green & 0xFE) | bits.charAt(i);
				i++;
				blue = (blue & 0xFE) | bits.charAt(i);
				i++;
				
				int modifiedPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
				output.setRGB(x, y, modifiedPixel);
			}
		}
		return output;
	}
}
