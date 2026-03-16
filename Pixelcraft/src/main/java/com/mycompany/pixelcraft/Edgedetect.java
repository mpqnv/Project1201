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
public class Edgedetect extends Converter{
    
    private static final int[][] SOBEL_X = {
        {-1, 0, 1},
        {-2, 0, 2},
        {-1, 0, 1}
    };
    
    private static final int[][] SOBEL_Y = {
        {-1, -2, -1},
        {0, 0, 0},
        {1, 2, 1}
    };
    
    @Override
    protected BufferedImage process(BufferedImage image){
        int width = image.getWidth(); 
        int height = image.getHeight(); 
        
        //Build a grayscale luminance map for the Sobel computation
        int[][] gray = buildGrayMap(image, width, height); 
        
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); 
        
        //Apply the Sobel operator to every non b oder pixel
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                //Border pixels cannot have a full 3x3 neighbourhood
                if (x == 0 || x == width - 1 || y == 0 || y == height -1){
                    //Black
                    result.setRGB(x, y, new ARGB(255, 0, 0, 0).toInt()); 
                    continue; 
                }
                
                int gx = 0, gy = 0; 
                
                //Add to the 3x3 neighbourhood with both Sobel kernels
                for (int ky = -1; ky <= 1; ky++){
                    for (int kx = -1; kx <=1; kx++){
                        int g = gray[y+ky][x+kx]; 
                        gx += SOBEL_X[ky+1][kx+1]*g; 
                        gy += SOBEL_Y[ky+1][kx+1]*g; 
                    }
                }
                
                //Gradiante magnitude
                int magnitude = ARGB.clamp(Math.abs(gx) + Math.abs(gy)); 
                
                //White pixel whose brightness encodes edge strength
                result.setRGB(x, y, new ARGB(255, magnitude, magnitude, magnitude).toInt()); 
            }
        }
        
        return result; 
    }
    
    private int[][] buildGrayMap(BufferedImage image, int width, int height){
        int[][] gray = new int[height][width]; 
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                ARGB p = new ARGB(image.getRGB(x, y)); 
                gray[y][x] = (int)(0.299 *p.red + 0.587 * p.green + 0.114 * p.blue); 
            }
        }
        return gray; 
    }
    
   
}
