package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolver {
    protected Maze maze;
    private String path;
    
    
    public MazeSolver(Maze maze) {
        this.maze= maze;
    }

    public String solveMaze() throws Exception {
        int[][] mazeGrid = maze.getMazeGrid();
        int entryRow = maze.getEntryRow();
        int exitRow = maze.getExitRow();
        int mazeWidth = maze.getMazeWidth();
        int mazeHeight = mazeGrid.length;
        
        // Start at the entry (entryRow, 0)
        int currentRow = entryRow;
        int currentCol = 0;
        path = "";

        if (exitRow ==-1) {
            throw new Exception("No valid exit point");
          }
        
        // Initial direction: East (assuming entry is on the left)
        char currentDirection = 'E';
        
        // Continue until we reach the exit at (exitRow, mazeWidth-1)
        while (!(currentRow == exitRow && currentCol == mazeWidth - 1)) {
            if (currentDirection == 'E') {
        
                if (currentRow + 1 < mazeHeight && mazeGrid[currentRow+1][currentCol] == 0) {
                    // Right is free: turn right (face South) and move forward.
                    currentDirection = 'S';
                    path += "R";
                    currentRow++;
                    path += "F";
                } else if (currentCol + 1 < mazeWidth && mazeGrid[currentRow][currentCol+1] == 0) {
                    // Forward is free: move forward.
                    currentCol++;
                    path += "F";
                } else {
                    // Neither right nor forward is free: turn left (face North).
                    currentDirection = 'N';
                    path += "L";
                }
            } else if (currentDirection == 'S') {
                
                if (currentCol - 1 >= 0 && mazeGrid[currentRow][currentCol-1] == 0) {
                    // Right is free: turn right (face West) and move forward.
                    currentDirection = 'W';
                    path += "R";
                    currentCol--;
                    path += "F";
                } else if (currentRow + 1 < mazeHeight && mazeGrid[currentRow+1][currentCol] == 0) {
                    // Forward is free: move forward.
                    currentRow++;
                    path += "F";
                } else {
                    // Neither right nor forward is free: turn left (face East).
                    currentDirection = 'E';
                    path += "L";
                }
            } else if (currentDirection == 'N') {
          
                if (currentCol + 1 < mazeWidth && mazeGrid[currentRow][currentCol+1] == 0) {
                    // Right is free: turn right (face East) and move forward.
                    currentDirection = 'E';
                    path += "R";
                    currentCol++;
                    path += "F";
                } else if (currentRow - 1 >= 0 && mazeGrid[currentRow-1][currentCol] == 0) {
                    // Forward is free: move forward.
                    currentRow--;
                    path += "F";
                } else {
                    // Neither right nor forward is free: turn left (face West).
                    currentDirection = 'W';
                    path += "L";
                }
            } else if (currentDirection == 'W') {
               
                if (currentRow - 1 >= 0 && mazeGrid[currentRow-1][currentCol] == 0) {
                    // Right is free: turn right (face North) and move forward.
                    currentDirection = 'N';
                    path += "R";
                    currentRow--;
                    path += "F";
                } else if (currentCol - 1 >= 0 && mazeGrid[currentRow][currentCol-1] == 0) {
                    // Forward is free: move forward.
                    currentCol--;
                    path += "F";
                } else {
                    // Neither right nor forward is free: turn left (face South).
                    currentDirection = 'S';
                    path += "L";
                }
            }
        }
        return path;
    }

    // convert the canoncical path into factorized form
    public String factorizedPath(String path) {
        StringBuilder factorizedString = new StringBuilder();

        int i = 0;
        while (i < path.length()) {
            char currentChar = path.charAt(i);
            int count = 1;
            
            // Count how many times the current character is repeated
            while (i + 1 < path.length() && path.charAt(i + 1) == currentChar) {
                count++;
                i++;
            }
            
            // Append the count only if it's more than 1
            if (count > 1) {
                factorizedString.append(count);
            }
            factorizedString.append(currentChar);
            
            
            i++;  
        }
        return factorizedString.toString();
    }

    
    
}
