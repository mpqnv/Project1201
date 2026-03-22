/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage; 

/**
 * Produces a negative of the image by inverting each colour channel:
 * {@code channel' = 255 - channel}. The alpha channel is preserved.
 *
 * This achieves an effect similar to a photographic negative, meaning dark areas
 * become light and vice versa, while colours are replaced by their complements.
 *
 * Implementation strategy: purely recursive, meaning no loops are
 * used anywhere. A single recursive helper processes every pixel by iterating
 * over (x, y) coordinates via tail recursion.
 * @author Alper Diker
 */
public class Invert extends Converter {
    /**
     * Applies the invert (negative) filter to the given image.
     *
     * @param image the source image
     * @return a new inverted image of the same dimensions
     */
    @Override
    protected BufferedImage process(BufferedImage image) {
        // Get the dimensions of the original image
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a new image to store the inverted result
        BufferedImage result = new BufferedImage(width, height, image.getType());
        
        processPixel(image, result, 0, 0, width, height) ;
        return result; 
    }
    /**
     * Recursively processes every pixel in row major order.
     *
     * Advances column-by-column; at the end of a row it moves to the first
     * column of the next row. Terminates when all rows have been processed.
     *
     * @param src    the source image (read-only)
     * @param dst    the destination image (written to)
     * @param x      current column index
     * @param y      current row index
     * @param width  image width
     * @param height image height
     */
        private void processPixel(BufferedImage src, BufferedImage dst, int x, int y, int width, int height){
            if (y >= height) return; 
            
            int rgb = src.getRGB(x, y); 
            int a = (rgb >> 24) & 255; 
            int r = (rgb >> 16) & 255; 
            int g = (rgb >> 8) & 255; 
            int b = rgb & 255; 
            
            dst.setRGB(x, y, (a << 24) | ((255-r) << 16) | ((255-g) << 8) | (255-b));
            
            if (x + 1 < width) processPixel(src, dst, x+1, y, width, height); 
            else processPixel(src, dst, 0, y +1, width, height); 
        }
}