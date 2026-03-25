/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage;
/**
 * Rotates the image 90 degrees clockwise.
 *
 * For a source pixel at (x, y) in an image of size W × H, the destination
 * coordinates in the rotated image (H × W) are:
 * 
 *   destX = (H - 1) - y
 *   destY = x
 * 
 *
 * Implementation strategy: iterative (nested for loops over every pixel).
 * @author Koosha Shamdani
 */
public class Rotate extends Converter{
    /**
     * Rotates the image 90 degrees clockwise.
     *
     * @param image the source image
     * @return a new image that is the 90 degrees clockwise rotation of the source
     */
    @Override
    protected BufferedImage process(BufferedImage image){
        int srcWidth = image.getWidth(); 
        int srcHeight = image.getHeight(); 
        
        //After a 90 degree clockwise rotation the dimensions are transposed
        BufferedImage result = new BufferedImage(srcHeight, srcWidth, BufferedImage.TYPE_INT_ARGB); 
        
        for (int y = 0; y < srcHeight; y++){
            for (int x = 0; x < srcWidth; x++){
                int pixel = image.getRGB(x, y); 
                
                //Map source (x, y) tp destination for clockwise rotation
                int destX = (srcHeight - 1) - y; 
                int destY = x; 
                result.setRGB(destX, destY, pixel); 
            }
        }
        //Return the final image
        return result; 
    }
    
}
