package com.manas;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

import nu.pattern.OpenCV;

public class CameraTest {

    public static void main(String[] args) {

        OpenCV.loadLocally();

        VideoCapture camera = new VideoCapture(0);

        if (!camera.isOpened()) {
            System.out.println("Cannot open webcam");
            return;
        }

        Mat frame = new Mat();

        while (true) {

            camera.read(frame);

            if (frame.empty()) {
                continue;
            }

            HighGui.imshow("Webcam", frame);

            if (HighGui.waitKey(1) == 27) {
                break;
            }
        }

        camera.release();
        HighGui.destroyAllWindows();
    }
}