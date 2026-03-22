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
 ** Abstract base class for all PixelCraft image converters.
 *
 * Handles the common workflow shared by every converter:
 * reading the input PNG file, delegating the pixel level transformation
 * to the concrete subclass via {@link #process(BufferedImage)}, and
 * writing the result to the output PNG file.
 *
 * Subclasses must implement {@link #process(BufferedImage)} to define
 * their specific image transformation.
 * @author Koosha Shamdani
 */
public abstract class Converter {
    /**
     * Reads the input image, applies the converter's transformation, and
     * writes the processed image to the output file.
     *
     * @param inputFileName  path to the source PNG image
     * @param outputFileName path where the processed PNG image will be saved
     * @throws IOException if the input file cannot be read or the output file cannot be written
     */
    public void convert(String inputFileName, String outputFileName) throws IOException{
        //Read the source image from disk
        File inputFile = new File(inputFileName); 
        BufferedImage originalImage = ImageIO.read(inputFile); 
        
        if (originalImage == null){
            throw new IOException("Could not read image file: " + inputFileName); 
        }
        
        //Delegate the actual pixel transformation to the subclasses
        BufferedImage processedImage = process(originalImage); 
        
        //Save the result as a PNG file
        File outputFile = new File(outputFileName); 
        ImageIO.write(processedImage, "PNG", outputFile); 
        System.out.println("Saved processed image to : " + outputFileName);
    }
    
 /**
     * Performs the image transformation specific to this converter.
     *
     * @param image the source {@link BufferedImage} to process
     * @return a new {@link BufferedImage} containing the transformed pixels
     */
    protected abstract BufferedImage process(BufferedImage image);
}
