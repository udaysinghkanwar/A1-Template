package ca.mcmaster.se2aa4.mazerunner.commands;

import ca.mcmaster.se2aa4.mazerunner.model.MazePosition;

public class MoveForwardCommand implements MazeCommand {
    private final int[][] mazeGrid;
    
    public MoveForwardCommand(int[][] mazeGrid) {
        this.mazeGrid = mazeGrid;
    }
    
    @Override
    public void execute(MazePosition position) {
        switch(position.getDirection()) {
            case 'E': position.setCol(position.getCol() + 1); break;
            case 'W': position.setCol(position.getCol() - 1); break;
            case 'N': position.setRow(position.getRow() - 1); break;
            case 'S': position.setRow(position.getRow() + 1); break;
        }
    }
} 