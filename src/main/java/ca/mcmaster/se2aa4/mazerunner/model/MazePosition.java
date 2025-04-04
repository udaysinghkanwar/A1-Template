package ca.mcmaster.se2aa4.mazerunner.model;

public class MazePosition {
    private int row;
    private int col;
    private char direction;
    
    public MazePosition(int row, int col, char direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }
    
    // Getters and setters for the maze position
    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }
    public int getCol() { return col; }
    public void setCol(int col) { this.col = col; }
    public char getDirection() { return direction; }
    public void setDirection(char direction) { this.direction = direction; }
} 