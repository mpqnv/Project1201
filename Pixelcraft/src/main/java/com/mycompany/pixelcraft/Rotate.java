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
public class Rotate extends Converter{
    
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
        return result; 
    }
    
}
