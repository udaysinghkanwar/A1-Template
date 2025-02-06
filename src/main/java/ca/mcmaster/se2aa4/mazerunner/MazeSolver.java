package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolver {
    protected Maze maze;
    
    
    public MazeSolver(Maze maze) {
        this.maze= maze;
    }

    /*public String solveMaze(){
        int[][] mazeGrid=maze.getMazeGrid();
        int entryRow=maze.getEntryRow();
        int exitRow=maze.getExitRow();
        int mazeWidth= maze.getMazeWidth();

        int currentRow = entryRow;
        int currentCol = 0;
        String path="";

        char currentDirection = 'E';
        while(currentRow!=exitRow && currentCol!= mazeWidth-1){
            
            if (currentDirection == 'E'){
                currentCol++;
                path+="F";  
                while (mazeGrid[currentRow+1][currentCol]==1 && mazeGrid[currentRow][currentCol+1]==0) {
                    currentCol++;
                    path+="F";  
                }
                if (mazeGrid[currentRow+1][currentCol]==0) {
                    currentDirection='S';
                    path+='R';
                }
                if (mazeGrid[currentRow][currentCol+1]==0) {
                    currentDirection='N';
                    path+='L';
                }
                
            }
            
            else if (currentDirection == 'S'){
                currentRow++;
                path+='F';  
                while (mazeGrid[currentRow][currentCol-1]==1 && mazeGrid[currentRow+1][currentCol]==0) {
                    currentRow++;
                    path+='F';  
                }
            

                if (mazeGrid[currentRow][currentCol-1]==0) {
                    currentDirection='S';
                    path+='R'; 
                }
                else if (mazeGrid[currentRow+1][currentCol]==1) {
                    currentDirection='E';
                    path+='L'; 
                }
            
            else if (currentDirection == 'N'){
                currentRow--;
                path+='F';  
                while (mazeGrid[currentRow+1][currentCol]==1 && mazeGrid[currentRow][currentCol-1]==0) {
                    currentRow--;
                    path+='F';  
                }

                if (mazeGrid[currentRow+1][currentCol]==0) {
                    currentDirection='E';
                    path+='R'; 
                }
                else if (mazeGrid[currentRow][currentCol-1]==1) {
                    currentDirection='W';
                    path+='L'; 
                }
                
            }
            else if (currentDirection == 'W'){
                currentCol--;
                path+='F';  
                while (mazeGrid[currentRow][currentCol-1]==1 && mazeGrid[currentRow-1][currentCol]==0) {
                    currentCol--;
                    path+='F';  
                }

                if (mazeGrid[currentRow+1][currentCol]==0) {
                    currentDirection='E';
                    path+='R'; 
                }
                else if (mazeGrid[currentRow][currentCol-1]==1) {
                    currentDirection='W';
                    path+='L'; 
                }
                
             }


            }
        
        }

        return path;
    }*/

    public String solveMaze(){
        int[][] mazeGrid = maze.getMazeGrid();
        int entryRow = maze.getEntryRow();
        int exitRow = maze.getExitRow();
        int mazeWidth = maze.getMazeWidth();
        int mazeHeight = mazeGrid.length;
        
        // Start at the entry (entryRow, 0)
        int currentRow = entryRow;
        int currentCol = 0;
        String path = "";
        
        // We use a char to indicate the current direction:
        // 'E' = east, 'S' = south, 'N' = north, 'W' = west.
        // Start facing East.
        char currentDirection = 'E';
        
        // Continue until we reach the exit at (exitRow, mazeWidth-1)
        while (!(currentRow == exitRow && currentCol == mazeWidth - 1)) {
            
            if (currentDirection == 'E') {
                // Move east (increment column) if possible.
                if (currentCol + 1 < mazeWidth && mazeGrid[currentRow][currentCol+1] == 0) {
                    currentCol++;
                    path += "F";
                }
                // While the cell ahead (east) is free and the cell to the right (south) is a wall,
                // continue moving east.
                while ((currentCol + 1 < mazeWidth) &&
                       mazeGrid[currentRow][currentCol+1] == 0 &&
                       ((currentRow + 1 >= mazeHeight) || mazeGrid[currentRow+1][currentCol] == 1)) {
                    currentCol++;
                    path += "F";
                }
                // If the cell to the right (south) is free, turn right (face South).
                if (currentRow + 1 < mazeHeight && mazeGrid[currentRow+1][currentCol] == 0) {
                    currentDirection = 'S';
                    path += "R";
                }
                // Otherwise, if the cell to the left (north) is free, turn left (face North).
                else if (currentRow - 1 >= 0 && mazeGrid[currentRow-1][currentCol] == 0) {
                    currentDirection = 'N';
                    path += "L";
                }
                
            } else if (currentDirection == 'S') {
                // Move south (increment row)
                if (currentRow + 1 < mazeHeight && mazeGrid[currentRow+1][currentCol] == 0) {
                    currentRow++;
                    path += "F";
                }
                // While the cell ahead (south) is free and the cell to the right (west) is a wall,
                // continue moving south.
                while ((currentRow + 1 < mazeHeight) &&
                       mazeGrid[currentRow+1][currentCol] == 0 &&
                       ((currentCol - 1 < 0) || mazeGrid[currentRow][currentCol-1] == 1)) {
                    currentRow++;
                    path += "F";
                }
                // If the cell to the right (west) is free, turn right (face West).
                if (currentCol - 1 >= 0 && mazeGrid[currentRow][currentCol-1] == 0) {
                    currentDirection = 'W';
                    path += "R";
                }
                // Otherwise, if the cell to the left (east) is free, turn left (face East).
                else if (currentCol + 1 < mazeWidth && mazeGrid[currentRow][currentCol+1] == 0) {
                    currentDirection = 'E';
                    path += "L";
                }
                
            } else if (currentDirection == 'N') {
                // Move north (decrement row)
                if (currentRow - 1 >= 0 && mazeGrid[currentRow-1][currentCol] == 0) {
                    currentRow--;
                    path += "F";
                }
                // While the cell ahead (north) is free and the cell to the right (east) is a wall,
                // continue moving north.
                while ((currentRow - 1 >= 0) &&
                       mazeGrid[currentRow-1][currentCol] == 0 &&
                       ((currentCol + 1 >= mazeWidth) || mazeGrid[currentRow][currentCol+1] == 1)) {
                    currentRow--;
                    path += "F";
                }
                // If the cell to the right (east) is free, turn right (face East).
                if (currentCol + 1 < mazeWidth && mazeGrid[currentRow][currentCol+1] == 0) {
                    currentDirection = 'E';
                    path += "R";
                }
                // Otherwise, if the cell to the left (west) is free, turn left (face West).
                else if (currentCol - 1 >= 0 && mazeGrid[currentRow][currentCol-1] == 0) {
                    currentDirection = 'W';
                    path += "L";
                }
                
            } else if (currentDirection == 'W') {
                // Move west (decrement column)
                if (currentCol - 1 >= 0 && mazeGrid[currentRow][currentCol-1] == 0) {
                    currentCol--;
                    path += "F";
                }
                // While the cell ahead (west) is free and the cell to the right (north) is a wall,
                // continue moving west.
                while ((currentCol - 1 >= 0) &&
                       mazeGrid[currentRow][currentCol-1] == 0 &&
                       ((currentRow - 1 < 0) || mazeGrid[currentRow-1][currentCol] == 1)) {
                    currentCol--;
                    path += "F";
                }
                // If the cell to the right (north) is free, turn right (face North).
                if (currentRow - 1 >= 0 && mazeGrid[currentRow-1][currentCol] == 0) {
                    currentDirection = 'N';
                    path += "R";
                }
                // Otherwise, if the cell to the left (south) is free, turn left (face South).
                else if (currentRow + 1 < mazeHeight && mazeGrid[currentRow+1][currentCol] == 0) {
                    currentDirection = 'S';
                    path += "L";
                }
            }
        }
        return path;
    }
    
}
