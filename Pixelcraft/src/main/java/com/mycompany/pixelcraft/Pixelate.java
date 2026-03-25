/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage;

/**
 *Applies a mosaic / "pixelation" effect by dividing the image into square
 * tiles and filling every pixel in each tile with the average colour of that tile.
 * This simulates the appearance of a very low resolution image scaled up.
 *
 *Tiles near the right or bottom edge may be smaller than {@value #TILE_SIZE}
 * pixels if the image dimensions are not exact multiples of the tile size; they
 * are handled correctly by clamping to the image bounds.
 *
 *Implementation strategy: iterative; two levels of nested loops: one over
 * tiles and one over the pixels within each tile. The per tile average is
 * computed in a separate helper to keep the logic clear.
 * @author Koosha Shamdani
 */
public class Pixelate extends Converter{
    
    private static final int TILE_SIZE = 15; 
    /**
     * Applies the pixelation effect to the given image.
     *
     * @param image the source image
     * @return a new pixelated image of the same dimensions
     */
    @Override
    protected BufferedImage process(BufferedImage image){
        int width = image.getWidth(); 
        int height = image.getHeight();
        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); 
        
        //Iterate over the top left corner of every tile
        for (int tileY = 0; tileY < height; tileY += TILE_SIZE){
            for (int tileX = 0; tileX < width; tileX+= TILE_SIZE){
                
                //Compute the average colour of this tile
                ARGB avg = averageColour(image, tileX, tileY,
                Math.min(tileX + TILE_SIZE, width),
                Math.min(tileY + TILE_SIZE, height)); 
                
                int avgInt = avg.toInt(); 
                //Fill every pixel in the title with the average colour
                for (int y = tileY; y < Math.min(tileY + TILE_SIZE, height); y++){
                    for (int x = tileX; x < Math.min(tileX + TILE_SIZE, width); x++){
                    result.setRGB(x, y, avgInt); 
                }
                }
            }
        }
        return result; 
    }
    
    /**
     * Computes the average ARGB colour of a rectangular region in the image.
     *
     * @param image the source image
     * @param x0    left boundary (inclusive)
     * @param y0    top boundary (inclusive)
     * @param x1    right boundary (exclusive)
     * @param y1    bottom boundary (exclusive)
     * @return the average colour as an {@link ARGB} instance
     */
    private ARGB averageColour(BufferedImage image, int x0, int y0, int x1, int y1){
        long sumA = 0, sumR = 0, sumG = 0, sumB = 0; 
        int count = 0; 
        
        for (int y = y0; y<y1; y++){
            for (int x = x0; x<x1; x++){
                ARGB p = new ARGB(image.getRGB(x, y)); 
                sumA += p.alpha; 
                sumR += p.red; 
                sumG += p.green; 
                sumB += p.blue; 
                count++; 
            }
        }
        //Return the final result
        return new ARGB((int)(sumA / count), (int)(sumR / count), (int)(sumG / count), (int)(sumB / count)); 
    }
    
}
