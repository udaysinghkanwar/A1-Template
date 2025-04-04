package ca.mcmaster.se2aa4.mazerunner.strategy;

import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.commands.MoveForwardCommand;
import ca.mcmaster.se2aa4.mazerunner.commands.TurnLeftCommand;
import ca.mcmaster.se2aa4.mazerunner.commands.TurnRightCommand;

public class RightHandSolver extends MazeSolvingStrategy {
    private final MoveForwardCommand moveForward;
    private final TurnRightCommand turnRight;
    private final TurnLeftCommand turnLeft;
    private final StringBuilder path;
    
    public RightHandSolver(Maze maze) {
        super(maze);
        moveForward = new MoveForwardCommand(maze.getMazeGrid());
        turnRight = new TurnRightCommand();
        turnLeft = new TurnLeftCommand();
        path = new StringBuilder();
    }
    
    // executeSolve method to solve the maze using the right hand rule as defined in the assignment
    @Override
    protected void executeSolve() {
        int[][] mazeGrid = maze.getMazeGrid();
        int exitRow = maze.getExitRow();
        int mazeWidth = maze.getMazeWidth();
        int mazeHeight = mazeGrid.length;
        
        while (!(position.getRow() == exitRow && position.getCol() == mazeWidth - 1)) {
            if (position.getDirection() == 'E') {
                if (position.getRow() + 1 < mazeHeight && mazeGrid[position.getRow()+1][position.getCol()] == 0) {
                    turnRight.execute(position);
                    path.append("R");
                    moveForward.execute(position);
                    path.append("F");
                } else if (position.getCol() + 1 < mazeWidth && mazeGrid[position.getRow()][position.getCol()+1] == 0) {
                    moveForward.execute(position);
                    path.append("F");
                } else {
                    turnLeft.execute(position);
                    path.append("L");
                }
            } else if (position.getDirection() == 'S') {
                if (position.getCol() - 1 >= 0 && mazeGrid[position.getRow()][position.getCol()-1] == 0) {
                    turnRight.execute(position);
                    path.append("R");
                    moveForward.execute(position);
                    path.append("F");
                } else if (position.getRow() + 1 < mazeHeight && mazeGrid[position.getRow()+1][position.getCol()] == 0) {
                    moveForward.execute(position);
                    path.append("F");
                } else {
                    turnLeft.execute(position);
                    path.append("L");
                }
            } else if (position.getDirection() == 'N') {
                if (position.getCol() + 1 < mazeWidth && mazeGrid[position.getRow()][position.getCol()+1] == 0) {
                    turnRight.execute(position);
                    path.append("R");
                    moveForward.execute(position);
                    path.append("F");
                } else if (position.getRow() - 1 >= 0 && mazeGrid[position.getRow()-1][position.getCol()] == 0) {
                    moveForward.execute(position);
                    path.append("F");
                } else {
                    turnLeft.execute(position);
                    path.append("L");
                }
            } else if (position.getDirection() == 'W') {
                if (position.getRow() - 1 >= 0 && mazeGrid[position.getRow()-1][position.getCol()] == 0) {
                    turnRight.execute(position);
                    path.append("R");
                    moveForward.execute(position);
                    path.append("F");
                } else if (position.getCol() - 1 >= 0 && mazeGrid[position.getRow()][position.getCol()-1] == 0) {
                    moveForward.execute(position);
                    path.append("F");
                } else {
                    turnLeft.execute(position);
                    path.append("L");
                }
            }
        }
    }
    
    @Override
    public String getPath() {
        return path.toString();
    }
} 