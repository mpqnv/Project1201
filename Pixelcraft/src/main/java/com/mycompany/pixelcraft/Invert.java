/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage; 

/**
 *Produces a negative of the image by inverting each color channel.
 * @author Alper Diker
 */
public class Invert extends Converter {

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