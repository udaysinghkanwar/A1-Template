package ca.mcmaster.se2aa4.mazerunner.strategy;

public interface MazeMove {
    void perform() throws Exception;
    void undo();
} 