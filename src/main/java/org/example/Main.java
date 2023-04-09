package org.example;

public class Main {
    public static void main(String[] args) {
        Camera camera = new JavacvCamera();
        camera.shotVideo(20,"D:\\test.mp4");
    }
}