package com.manas;

import java.util.List;

public class FaceShapeClassifier {

    public static FaceShape classify(
            List<FaceLandmark> pts) {

        float minX = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE;
        float minY = Float.MAX_VALUE;
        float maxY = Float.MIN_VALUE;

        for(FaceLandmark p : pts) {

            minX = Math.min(minX, p.x);
            maxX = Math.max(maxX, p.x);

            minY = Math.min(minY, p.y);
            maxY = Math.max(maxY, p.y);
        }

        float width = maxX - minX;
        float height = maxY - minY;

        float ratio = height / width;

        if(ratio > 1.55f)
            return FaceShape.OBLONG;

        if(ratio > 1.35f)
            return FaceShape.OVAL;

        if(ratio < 1.05f)
            return FaceShape.ROUND;

        return FaceShape.SQUARE;
    }
}