package com.manas;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtSession;

public class LandmarkDetector {

    private final OrtEnvironment env;
    private final OrtSession session;

    public LandmarkDetector(String modelPath)
            throws Exception {

        env = OrtEnvironment.getEnvironment();

        session = env.createSession(
                modelPath,
                new OrtSession.SessionOptions()
        );
    }

    public List<FaceLandmark> detect(Mat faceCrop)
            throws Exception {

        Mat resized = new Mat();

        Imgproc.resize(
                faceCrop,
                resized,
                new Size(192,192)
        );

        resized.convertTo(
                resized,
                CvType.CV_32FC3,
                1.0 / 255.0
        );

        float[] input =
                new float[3 * 192 * 192];

        int idx = 0;

        for(int c=0;c<3;c++) {
            for(int y=0;y<192;y++) {
                for(int x=0;x<192;x++) {

                    double[] pixel =
                            resized.get(y,x);

                    input[idx++] =
                            (float)pixel[c];
                }
            }
        }

        OnnxTensor tensor =
                OnnxTensor.createTensor(
                        env,
                        FloatBuffer.wrap(input),
                        new long[]{1,3,192,192}
                );

        Map<String,OnnxTensor> map =
                new HashMap<>();

        map.put("data", tensor);

        OrtSession.Result result =
                session.run(map);

        float[][] output =
                (float[][])
                        result.get("fc1")
                                .get()
                                .getValue();

        float[] pts = output[0];

        List<FaceLandmark> landmarks =
                new ArrayList<>();

        for(int i=0;i<212;i+=2) {

            landmarks.add(
                    new FaceLandmark(
                            pts[i],
                            pts[i+1]
                    )
            );
        }

        result.close();
        tensor.close();

        return landmarks;
    }
}