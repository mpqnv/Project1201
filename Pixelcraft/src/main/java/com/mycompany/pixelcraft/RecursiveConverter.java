/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pixelcraft;
import java.awt.image.BufferedImage;
/**
 * Abstract base class for converters implemented using only recursion.
 * Provides the shared two level traversal (rows then columns) so subclasses
 * only need to implement the per pixel transformation.
 * @author Koosha Shamdani
 */
public abstract class RecursiveConverter extends Converter {
    /**
     * Processes the entire image by recursively traversing every pixel
     * and applying {@link #transformPixel(int)} to each one.
     *
     * @param image the source {@link BufferedImage} to convert
     * @return a new {@link BufferedImage} with all pixels transformed
     */
    @Override
    protected BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        // Create a new output image with full alpha support (ARGB)
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // Begin recursive row traversal starting at row 0
        processRow(image, result, 0, width, height);
        return result;
    }
/**
     * Recursively processes each row of the image from top to bottom.
     * For each row {@code y}, delegates to {@link #processPixel} to handle
     * all columns before advancing to the next row.
     *
     * @param src the source image to read pixels from
     * @param dst the destination image to write transformed pixels to
     * @param y the current row index being processed
     * @param width the total number of columns in the image
     * @param height the total number of rows in the image (base case boundary)
     */
    private void processRow(BufferedImage src, BufferedImage dst, int y, int width, int height) {
        // Base case: all rows have been processed
        if (y >= height) return;
        // Process every pixel in the current row, starting at column 0
        processPixel(src, dst, 0, y, width, height);
        // Recurse to the next row
        processRow(src, dst, y + 1, width, height);
    }
 /**
     * Recursively processes each pixel in a single row from left to right.
     * Reads the source pixel at ({@code x}, {@code y}), transforms it, and
     * writes the result to the destination image.
     *
     * @param src the source image to read pixels from
     * @param dst the destination image to write transformed pixels to
     * @param x the current column index being processed
     * @param y the current row index (fixed for this recursive call chain)
     * @param width the total number of columns in the image (base case boundary)
     * @param height the total number of rows in the image
     */
    private void processPixel(BufferedImage src, BufferedImage dst, int x, int y, int width, int height) {
        // Base case: all columns in this row have been processed
        if (x >= width) return;
        // Transform the current pixel and write it to the destination
        dst.setRGB(x, y, transformPixel(src.getRGB(x, y)));
        // Recurse to the next column in the same row
        processPixel(src, dst, x + 1, y, width, height);
    }

    /**
     * Transforms a single packed ARGB pixel value.
     * Subclasses must implement this method to define their specific
     * colour transformation (e.g. grayscale, invert, sepia).
     *
     * @param pixel the source pixel as a packed {@code int} in ARGB format
     * @return the transformed pixel as a packed {@code int} in ARGB format
     */
    protected abstract int transformPixel(int pixel);
}
