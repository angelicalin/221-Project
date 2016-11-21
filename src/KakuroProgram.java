
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Created by Leqi on 11/16/16.
 */
public class KakuroProgram extends GraphicsProgram {
    private final int CANVAS_WIDTH = 500;
    private final int CANVAS_HEIGHT = 500;
    private CheckboardGraphics checkBoard;

    public void init(){
        setSize(CANVAS_WIDTH,CANVAS_HEIGHT);
        checkBoard = new CheckboardGraphics(Color.DARK_GRAY);


        add(checkBoard, 75, 75);

    }
}
