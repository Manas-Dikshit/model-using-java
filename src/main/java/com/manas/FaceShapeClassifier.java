package com.manas;

import java.util.List;

public class FaceShapeClassifier {

    public static FaceShape classify(
            List<FaceLandmark> pts) {

        return FaceShape.OVAL;
    }
}