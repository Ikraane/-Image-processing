package se.kth.idali2.labboration5.model;

/**
 * Interface to implement in all processes
 */
public interface ImageProcess {
    public abstract int[][] processImage(int[][] originalImage);
}