package com.manas;

import org.opencv.core.Core;

public class App {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        System.out.println("OpenCV loaded successfully.");
    }
}