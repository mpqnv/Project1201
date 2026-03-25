/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage;

/**
 * Applies a simple box blur effect to the image by replacing each pixel's
 * colour with the average colour of its surrounding neighbourhood (including
 * itself). A 5×5 kernel radius is used to produce a clearly visible blur.
 *
 * Pixels near the border use only the valid neighbours that fall within the
 * image bounds, so no special padding is needed.
 *
 * Implementation strategy: iterative (nested for loops over every pixel,
 * with an inner loop over the blur kernel).
 * @author Koosha Shamdani
 */
public class Blur extends Converter{
    
     //Half size of the sqare blur karnel
    private static final int RADIUS = 2; 
    
    /**
     * Applies a box blur to the given image.
     *
     * @param image the source image
     * @return a new blurred image of the same dimensions
     */
    
    @Override
    protected BufferedImage process(BufferedImage image){
        int width = image.getWidth(); 
        int height = image.getHeight(); 
        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); 
        
        for (int y = 0; y <height; y++){
            for (int x = 0; x < width; x++){
                int sumA = 0, sumR = 0, sumG= 0, sumB = 0; 
                
                int count  = 0; 
                
                //Add color values from the neighbourhood kernel
                
                for (int dy = -RADIUS; dy <= RADIUS; dy++){
                    
                    for (int dx = -RADIUS; dx <= RADIUS; dx++){
                        
                        int nx = x + dx; 
                        int ny = y + dy; 
                        
                        //Skip neighbours that fall outside the image bounds
                        if (nx < 0 || nx >=width || ny < 0 || ny >= height) continue; 
                        
                        ARGB neighbour = new ARGB (image.getRGB(nx, ny));
                        
                        sumA += neighbour.alpha; 
                        sumR += neighbour.red; 
                        sumG += neighbour.green; 
                        sumB += neighbour.blue; 
                        count++; 
                    }
                }
                
                //Write the averaged pixel to the result image
                ARGB blured = new ARGB (sumA / count, sumR/ count, sumG / count, sumB / count); 
                result.setRGB(x, y, blured.toInt()); 
            }
        }
        //Return the final image
        return result;
    }
    
}
