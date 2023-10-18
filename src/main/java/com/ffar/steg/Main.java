package com.ffar.steg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Main {
	
	private static Scanner scan;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImageLSBEncrypter imageEncrypter = new ImageLSBEncrypter();
		scan = new Scanner(System.in);
		System.out.println("Enter the message to be hidden: ");
		String message = scan.nextLine();
		System.out.println("The message to be hidden is :"+message);
		try {
			BufferedImage inputImage = ImageIO.read(new File("src/main/resources/Images/input.png"));
			BufferedImage outputImage = imageEncrypter.encrypt(message, inputImage);
			ImageIO.write(outputImage, "png", new File("src/main/resources/Images/output.png"));
			System.out.println("Wrote the image successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Image doesn't exist!");
			e.printStackTrace();
		}
		
		}

}
