package com.manas;

import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtSession;

public class ModelInspector {

    public static void main(String[] args)
            throws Exception {

        String modelPath =
                "models/2d106det.onnx";

        OrtEnvironment env =
                OrtEnvironment.getEnvironment();

        OrtSession session =
                env.createSession(
                        modelPath,
                        new OrtSession.SessionOptions()
                );

        System.out.println("=== INPUTS ===");

        for(String name :
                session.getInputNames()) {

            System.out.println(
                    name + " -> " +
                            session.getInputInfo()
                                    .get(name)
                                    .getInfo()
            );
        }

        System.out.println();

        System.out.println("=== OUTPUTS ===");

        for(String name :
                session.getOutputNames()) {

            System.out.println(
                    name + " -> " +
                            session.getOutputInfo()
                                    .get(name)
                                    .getInfo()
            );
        }

        session.close();
        env.close();
    }
}