package ca.mcmaster.se2aa4.mazerunner.commands;

import ca.mcmaster.se2aa4.mazerunner.model.MazePosition;

public class TurnLeftCommand implements MazeCommand {
    // execute method to turn left in the maze
    @Override
    public void execute(MazePosition position) {
        switch(position.getDirection()) {
            case 'E': position.setDirection('N'); break;
            case 'N': position.setDirection('W'); break;
            case 'W': position.setDirection('S'); break;
            case 'S': position.setDirection('E'); break;
        }
    }
} 