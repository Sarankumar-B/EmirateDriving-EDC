package com.testcases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DemoClass extends BaseClass {

	
	
		
		public static void main(String[] args) {
	        try {
	            // Load the input image
	            File inputFile = new File("C:\\Users\\ranjithkumar.sivakum\\Downloads\\img3.png"); // Replace with the path to your input image
	            BufferedImage inputImage = ImageIO.read(inputFile);

	            // Define the output image format (e.g., "PNG" or "JPEG")
	            String outputFormat = "PNG";

	            // Define the output file path and name
	            File outputFile = new File("./src/test/resources/Screenshot/img6.png"); // Replace with the desired output path and filename

	            // Write the image in the new format
	            ImageIO.write(inputImage, outputFormat, outputFile);

	            System.out.println("Image format conversion completed.");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		
	    

	}

}
