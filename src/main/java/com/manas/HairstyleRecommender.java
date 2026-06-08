package com.manas;

import java.util.List;

public class HairstyleRecommender {

    public static List<String> recommend(
            FaceShape shape) {

        return switch(shape) {

            case OVAL -> List.of(
                    "Textured Quiff",
                    "Pompadour",
                    "French Crop",
                    "Side Part",
                    "Slick Back"
            );

            case ROUND -> List.of(
                    "High Fade",
                    "Pompadour",
                    "Spiky Hair",
                    "Faux Hawk",
                    "Undercut"
            );

            case SQUARE -> List.of(
                    "Crew Cut",
                    "Buzz Cut",
                    "Ivy League",
                    "Side Part",
                    "Textured Crop"
            );

            case HEART -> List.of(
                    "Side Swept Fringe",
                    "Textured Crop",
                    "Layered Medium",
                    "Messy Fringe",
                    "Curtains"
            );

            case DIAMOND -> List.of(
                    "Curtains",
                    "Medium Waves",
                    "Textured Fringe",
                    "Bro Flow",
                    "Side Part"
            );

            case OBLONG -> List.of(
                    "Fringe",
                    "Caesar Cut",
                    "Medium Length",
                    "Textured Crop",
                    "Side Fringe"
            );
        };
    }
}