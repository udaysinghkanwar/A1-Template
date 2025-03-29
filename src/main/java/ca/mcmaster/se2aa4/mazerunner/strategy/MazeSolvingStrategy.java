package ca.mcmaster.se2aa4.mazerunner.strategy;

import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.model.MazePosition;

public abstract class MazeSolvingStrategy implements MazeMove {
    protected final Maze maze;
    protected MazePosition position;
    
    protected MazeSolvingStrategy(Maze maze) {
        this.maze = maze;
    }
    
    // Template method - made final so it cannot be overridden
    @Override
    public final void perform() throws Exception {
        validateMaze();     // Step 1 (fixed)
        initializeState(); // Step 2 (fixed)
        executeSolve();    // Step 3 (hook without default)
        logResult();       // Step 4 (hook with default)
    }
    
    // Fixed steps
    private void validateMaze() throws Exception {
        if (maze.getExitRow() == -1) {
            throw new Exception("No valid exit point");
        }
    }
    
    private void initializeState() {
        position = new MazePosition(maze.getEntryRow(), 0, 'E');
    }
    
    // Hook method without default implementation
    protected abstract void executeSolve();
    
    // Hook method with default implementation
    protected void logResult() {
        System.out.println("Maze solving completed by: " + getClass().getSimpleName());
    }
    
    @Override
    public void undo() {
        // Default implementation - can be overridden if needed
        position = new MazePosition(maze.getEntryRow(), 0, 'E');
    }
    
    // Method to get the solution path
    public abstract String getPath();
} 