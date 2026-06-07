package com.manas;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

public class CameraTest {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        VideoCapture camera = new VideoCapture(0);

        if (!camera.isOpened()) {
            System.out.println("Cannot open camera");
            return;
        }

        Mat frame = new Mat();

        while (true) {

            camera.read(frame);

            HighGui.imshow("Camera", frame);

            if (HighGui.waitKey(1) == 27) {
                break;
            }
        }

        camera.release();
        HighGui.destroyAllWindows();
    }
}