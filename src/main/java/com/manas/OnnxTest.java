package com.manas;

import ai.onnxruntime.OrtEnvironment;

public class OnnxTest {

    public static void main(String[] args)
            throws Exception {

        OrtEnvironment env =
                OrtEnvironment.getEnvironment();

        System.out.println(
                "ONNX Runtime Loaded Successfully"
        );

        env.close();
    }
}