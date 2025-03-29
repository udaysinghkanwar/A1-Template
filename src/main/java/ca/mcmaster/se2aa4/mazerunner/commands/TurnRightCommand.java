package ca.mcmaster.se2aa4.mazerunner.commands;

import ca.mcmaster.se2aa4.mazerunner.model.MazePosition;

public class TurnRightCommand implements MazeCommand {
    @Override
    public void execute(MazePosition position) {
        switch(position.getDirection()) {
            case 'E': position.setDirection('S'); break;
            case 'S': position.setDirection('W'); break;
            case 'W': position.setDirection('N'); break;
            case 'N': position.setDirection('E'); break;
        }
    }
} 