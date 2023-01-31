package com.epam.rd.autotasks;

import java.util.HashMap;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

    Direction(final int degrees) {
        this.degrees = degrees;
    }

    private int degrees;

    public int getDegrees() {
        return degrees;
    }

    private static final HashMap<Integer, Direction> enumDegrees = new HashMap<>();
    static {
        for (Direction d : Direction.values()) {
            enumDegrees.put(d.getDegrees(), d);
        }
    }
    private static int rangeDegrees(int degrees){
        if (degrees < 0) degrees = 360 + degrees;
        degrees = degrees % 360;
        return degrees;
    }
    public static Direction ofDegrees(int degrees) {
        return enumDegrees.getOrDefault(rangeDegrees(degrees), null);

    }

    public static Direction closestToDegrees(int degrees) {
        Direction d = ofDegrees(degrees);
        if (d == null) {
            int range = rangeDegrees(degrees);
            Direction dir = null;
            int distance = 361;
            for (Integer i : enumDegrees.keySet()) {
                if (distance > Math.abs(i - range)) {
                    distance = Math.abs(i - range);
                    dir = ofDegrees(i);
                }
            }
            return dir;
        } else
            return d;
    }

    public Direction opposite() {
        return ofDegrees(rangeDegrees(degrees + 180));
    }

    public int differenceDegreesTo(Direction direction) {
        int t=Math.abs(degrees - direction.getDegrees());
        if (t > 180)
            return 360 - t;
        return t;
    }
}
