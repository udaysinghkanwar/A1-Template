package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.strategy.MazeSolvingStrategy;
import ca.mcmaster.se2aa4.mazerunner.strategy.RightHandSolver;

public class MazeSolver {
    private final MazeSolvingStrategy strategy;
    
    public MazeSolver(Maze maze) {
        this.strategy = new RightHandSolver(maze);
    }
    
    public String solveMaze() throws Exception {
        strategy.perform();
        return strategy.getPath();
    }

    // convert the canonical path into factorized form
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
