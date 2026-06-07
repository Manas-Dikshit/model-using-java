package com.manas;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector {

    private final CascadeClassifier classifier;

    public FaceDetector(String cascadePath) {

        classifier = new CascadeClassifier(cascadePath);

        if (classifier.empty()) {
            throw new RuntimeException(
                "Failed to load cascade: " + cascadePath);
        }
    }

    public Rect[] detect(Mat frame) {

        MatOfRect faces = new MatOfRect();

        classifier.detectMultiScale(frame, faces);

        return faces.toArray();
    }
}