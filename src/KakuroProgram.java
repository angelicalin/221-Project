
import acm.program.GraphicsProgram;

/**
 * Created by Leqi on 11/16/16.
 */
public class KakuroProgram extends GraphicsProgram {
    private final int CANVAS_WIDTH = 500;
    private final int CANVAS_HEIGHT = 500;
    private CheckboardGraphics checkBoard;
    private GameSolver gameSolver;


    /**
     * This method runs the entire program.
     */
    public void init(){
        setSize(CANVAS_WIDTH,CANVAS_HEIGHT);
        int[][] gameInit = new int[][]{
                {-1,-1,-1,-1,-1},
                {-1,-1,-1,6,3},
                {-1,-1,3,0,0},
                {-1,4,3,0,0},
                {10,0,0,0,0},
                {-1,0,0,0,0},
                {3,0,0,-1,-1},
                {-1,0,0,-1,-1}
        };

        checkBoard = new CheckboardGraphics(gameInit);


        add(checkBoard, 75, 75);
        gameSolver = new GameSolver(gameInit, checkBoard);
        gameSolver.analyizeGameInit();

    }
}
