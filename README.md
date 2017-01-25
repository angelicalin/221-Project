This is a final project created for Algorithm Design course.

Author: Haihan (Angelica) Lin, Leqi (Lynn) Zhao

1. How to run this program?

   Please go to the KakuroProgram class and run it. It will present you a visualization of check board and the solution.


   In this game, we need to solve the values of the white cells, which add up to column sums and row sums in the pink cells. In one summation, there can be no repetition, nor can any value go up more than 9.
   The blue cells are here for fun and beauty. They are neither solution cells nor sum cells. 

   The program will first print the possible values for each emptcell. Then loops over the empty cells, and fill in the
   final solution. After it finishes solving, it will print "Solving Complete", and the 
   
   
2. It gets stucked?
   
   Because the solver uses random shuffle, there is a possibility that it does not always get a solution. If such     thing happens, please restart the program. 

3. Want to solve a different puzzle input?
   
   Please open KakuroProgram class, where you can find the board input. Each row of the board has two rows in this 2D array, First one is for any row sum, and second one is for any column sum. For empty cells waiting to be solved, please use 0, and for unfillable cells, please use -1. For example,   
   ![alt tag](http://www.anypuzzle.com/puzzles/logic/Kakuro/Easy%20Kakuro.png)    
   this will have the responding 2D array as following:   
               { {-1,-1,-1,-1,-1,-1,-1},  
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
                {-1,0,0,-1,-1,0,0}}  
