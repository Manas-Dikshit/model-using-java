package com.manas;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import nu.pattern.OpenCV;

public class CameraTest {

    public static void main(String[] args)
            throws Exception {

        OpenCV.loadLocally();

        FaceDetector detector =
                new FaceDetector(
                        "models/haarcascade_frontalface_default.xml");

        LandmarkDetector landmarkDetector =
                new LandmarkDetector(
                        "models/2d106det.onnx");

        VideoCapture camera =
                new VideoCapture(0);

        if (!camera.isOpened()) {

            System.out.println(
                    "Cannot open webcam");

            return;
        }

        Mat frame = new Mat();

        while (true) {

            camera.read(frame);

            Rect[] faces =
                    detector.detect(frame);

            for (Rect face : faces) {

                // Face rectangle
                Imgproc.rectangle(
                        frame,
                        new Point(face.x, face.y),
                        new Point(
                                face.x + face.width,
                                face.y + face.height),
                        new Scalar(0, 255, 0),
                        2);

                // Face crop
                Mat crop =
                        frame.submat(face);

                List<FaceLandmark> pts =
                        landmarkDetector.detect(crop);

                // Draw landmarks
                for (FaceLandmark p : pts) {

                    int px =
                            face.x +
                                    (int) (((p.x + 1.0f) / 2.0f)
                                            * face.width);

                    int py =
                            face.y +
                                    (int) (((p.y + 1.0f) / 2.0f)
                                            * face.height);

                    Imgproc.circle(
                            frame,
                            new Point(px, py),
                            2,
                            new Scalar(0, 255, 0),
                            -1);
                }

                // Face shape
                FaceShape shape =
                        FaceShapeClassifier.classify(pts);

                // Hairstyle recommendations
                List<String> styles =
                        HairstyleRecommender.recommend(shape);

                // Face shape text
                Imgproc.putText(
                        frame,
                        "Face Shape: " +
                                shape.name(),
                        new Point(
                                face.x,
                                Math.max(20,
                                        face.y - 10)),
                        Imgproc.FONT_HERSHEY_SIMPLEX,
                        0.7,
                        new Scalar(0, 255, 0),
                        2);

                // Hairstyles
                int textY =
                        face.y +
                                face.height +
                                25;

                for (int i = 0;
                     i < styles.size();
                     i++) {

                    Imgproc.putText(
                            frame,
                            (i + 1)
                                    + ". "
                                    + styles.get(i),
                            new Point(
                                    face.x,
                                    textY),
                            Imgproc.FONT_HERSHEY_SIMPLEX,
                            0.5,
                            new Scalar(
                                    0,
                                    255,
                                    255),
                            1);

                    textY += 20;
                }
            }

            HighGui.imshow(
                    "Face Shape Agent",
                    frame);

            if (HighGui.waitKey(1)
                    == 27) {
                break;
            }
        }

        camera.release();

        HighGui.destroyAllWindows();
    }
}