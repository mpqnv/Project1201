/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;

//Import the necessary libraries
import javax.imageio.ImageIO; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
/**
 *
 * @author Koosha Shamdani
 */
public abstract class Converter {
    
    public void convert(String inputFileName, String outputFileName) throws IOException{
        //Read the source image from disk
        File inputFile = new File(inputFileName); 
        BufferedImage originalImage = ImageIO.read(inputFile); 
        
        if (originalImage == null){
            throw new IOException("Could not raed imnage file: " + inputFileName); 
        }
        
        //Delegate the actual pixel transformation to the subclasses
        BufferedImage processedImage = process(originalImage); 
        
        //Save the result as a PNG file
        File outputFile = new File(outputFileName); 
        ImageIO.write(processedImage, "PNG", outputFile); 
        System.out.println("Saved processed image to : " + outputFileName);
    }
    
    protected abstract BufferedImage process(BufferedImage image);
}
