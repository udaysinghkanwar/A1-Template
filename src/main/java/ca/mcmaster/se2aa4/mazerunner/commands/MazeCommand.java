package ca.mcmaster.se2aa4.mazerunner.commands;

import ca.mcmaster.se2aa4.mazerunner.model.MazePosition;

public interface MazeCommand {
    void execute(MazePosition position);
} 