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
public class Blur extends Converter{
    
     //Half size of the sqare blur karnel
    private static final int RADIUS = 2; 
    
    
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
        return result;
    }
    
}
