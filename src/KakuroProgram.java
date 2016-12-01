
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
                {-1,-1,-1,-1,-1,-1,-1},
                {-1,3,4,-1,-1,16,3},
                {3,0,0,-1,4,0,0},
                {-1,0,0,22,-1,0,0},
                {9,0,0,0,7,0,0},
                {-1,0,0,0,17,0,0},
                {-1,-1,24,0,0,0,-1},
                {-1,-1,22,0,0,0,-1},
                {-1,23,0,0,0,-1,-1},
                {-1,17,0,0,0,16,12},
                {16,0,0,12,0,0,0},
                {-1,0,0,-1,0,0,0},
                {17,0,0,-1,17,0,0},
                {-1,0,0,-1,-1,0,0}
        };

        checkBoard = new CheckboardGraphics(gameInit);


        add(checkBoard, 75, 75);
        gameSolver = new GameSolver(gameInit);
        gameSolver.analyizeGameInit();

    }
}
