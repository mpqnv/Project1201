/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage;

/**
 *
 * @author Koosha Shamdani
 */
public class Brightness extends Converter{
    
    private static final int DELTA = 50;
    
    @Override
    protected BufferedImage process(BufferedImage image){
        int width = image.getWidth(); 
        int height = image.getHeight(); 
        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        //Start the recursive traversal at the top left pixel
        
        processPixel(image, result, 0, 0, width, height); 
        return result; 
    }
    
private void processPixel(BufferedImage src, BufferedImage dst, int x, int y, int width, int height) {
    
    // Base case
    if (y >= height) return;

    ARGB p = new ARGB(src.getRGB(x, y));
    ARGB bright = new ARGB(
        p.alpha,
        ARGB.clamp(p.red   + DELTA),
        ARGB.clamp(p.green + DELTA),
        ARGB.clamp(p.blue  + DELTA)
    );
    dst.setRGB(x, y, bright.toInt());

    // Recurse to the next pixel
    if (x + 1 < width) processPixel(src, dst, x + 1, y, width, height);
    else processPixel(src, dst, 0, y + 1, width, height);
}
    
}
