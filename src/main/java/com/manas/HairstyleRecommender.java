package com.manas;

import java.util.List;

public class HairstyleRecommender {

    public static List<String> recommend(
            FaceShape shape) {

        return switch(shape) {

            case OVAL -> List.of(
                    "Buzz Cut",
                    "Crew Cut",
                    "Pompadour",
                    "Quiff",
                    "Side Part",
                    "French Crop",
                    "Slick Back",
                    "Curtains",
                    "Undercut",
                    "Textured Crop"
            );

            case ROUND -> List.of(
                    "Pompadour",
                    "High Fade",
                    "Quiff",
                    "Faux Hawk",
                    "Spiky Hair",
                    "Undercut",
                    "Textured Top",
                    "Side Sweep"
            );

            case SQUARE -> List.of(
                    "Crew Cut",
                    "Buzz Cut",
                    "Classic Taper",
                    "Side Part",
                    "Ivy League",
                    "Textured Crop"
            );

            case HEART -> List.of(
                    "Fringe",
                    "Layered Medium",
                    "Side Swept",
                    "Textured Crop"
            );

            case DIAMOND -> List.of(
                    "Curtains",
                    "Textured Fringe",
                    "Medium Waves",
                    "Side Part"
            );

            case OBLONG -> List.of(
                    "Caesar Cut",
                    "Fringe",
                    "Medium Length",
                    "Textured Crop"
            );
        };
    }
}