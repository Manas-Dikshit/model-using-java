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

        FaceDetector detector = new FaceDetector(
                "models/haarcascade_frontalface_default.xml");

        LandmarkDetector landmarkDetector = new LandmarkDetector(
                "models/2d106det.onnx");

        VideoCapture camera = new VideoCapture(0);

        if (!camera.isOpened()) {

            System.out.println(
                    "Cannot open webcam");
            return;
        }

        Mat frame = new Mat();

        while (true) {

            camera.read(frame);

            Rect[] faces = detector.detect(frame);

            for (Rect face : faces) {

                Imgproc.rectangle(
                        frame,
                        new Point(face.x, face.y),
                        new Point(
                                face.x + face.width,
                                face.y + face.height),
                        new Scalar(0, 255, 0),
                        2);

                Mat crop = frame.submat(face);

                List<FaceLandmark> pts = landmarkDetector.detect(crop);
                for (int i = 0; i < 10; i++) {
                    System.out.println(
                            i + " -> " +
                                    pts.get(i).x + ", " +
                                    pts.get(i).y);
                }

                for (FaceLandmark p : pts) {

                    int px = face.x +
                            (int) (((p.x + 1.0f) / 2.0f)
                                    * face.width);

                    int py = face.y +
                            (int) (((p.y + 1.0f) / 2.0f)
                                    * face.height);

                    Imgproc.circle(
                            frame,
                            new Point(px, py),
                            2,
                            new Scalar(0, 255, 0),
                            -1);
                }

                FaceShape shape = FaceShapeClassifier
                        .classify(pts);

                Imgproc.putText(
                        frame,
                        shape.name(),
                        new Point(
                                face.x,
                                face.y - 10),
                        Imgproc.FONT_HERSHEY_SIMPLEX,
                        0.7,
                        new Scalar(0, 255, 0),
                        2);
            }

            HighGui.imshow(
                    "Face Shape Agent",
                    frame);

            if (HighGui.waitKey(1) == 27) {
                break;
            }
        }

        camera.release();
        HighGui.destroyAllWindows();
    }
}