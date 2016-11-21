/**
 * Created by Leqi on 11/16/16.
 */

import acm.graphics.GRect;

import java.awt.*;


public class CheckboardGraphics extends GRect{
    public static final double BOARD_WIDTH = 500.0;
    public static final double BOARD_HEIGHT = 500.0;

    public CheckboardGraphics(double width, double height)  {
        super(BOARD_WIDTH, BOARD_HEIGHT);
    }

    public CheckboardGraphics(Color color) {
        this(BOARD_WIDTH, BOARD_HEIGHT);
        setFilled(true);
        setFillColor(color);
    }

/*
    public double getBoardWidth() {return BOARD_WIDTH;}

    public double getBoardHeight() {return BOARD_HEIGHT;}

 */

    public static double getBoardWidth() {
        return BOARD_WIDTH;
    }

    public static double getBoardHeight() {
        return BOARD_HEIGHT;
    }
}
