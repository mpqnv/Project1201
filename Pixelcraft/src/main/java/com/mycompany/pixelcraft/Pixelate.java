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
public class Pixelate extends Converter{
    
    private static final int TILE_SIZE = 15; 
    
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
        
        return new ARGB((int)(sumA / count), (int)(sumR / count), (int)(sumG / count), (int)(sumB / count)); 
    }
    
}
