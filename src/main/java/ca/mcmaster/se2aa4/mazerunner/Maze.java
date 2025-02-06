package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
  // Path to the maze file
  protected String mazeFilePath;

  protected int mazeHeight = -1;
  protected int mazeWidth = -1;
  // 2D array representation of the maze
  protected int[][] mazeGrid;

  protected int entryRow = -1;
  protected int exitRow = -1;

  // Constructor to initialize the maze using the file path
  public Maze(String filePath, Logger logger) {
    try {
      logger.trace("**** Reading the maze from file: " + filePath);
      BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
    
      mazeWidth = bufferedReader.readLine().length();
      // Calculate the height of the maze
      while (bufferedReader.readLine() != null) {
        mazeHeight += 1;
      }

      mazeFilePath = filePath;
    } catch (Exception e) {
      logger.error("/!\\ An error occurred. The file was not found. /!\\");
    }
  }

  // Method to populate the maze array from the file
  public void populateMazeGrid() {
    mazeGrid = new int[mazeHeight][mazeWidth];
    try {
      BufferedReader reader = new BufferedReader(new FileReader(mazeFilePath));
      String line;
      int row = 0;
      reader.readLine(); 
      
      // Populate the maze grid row by row
      while ((line = reader.readLine()) != null && row < mazeHeight) {
        for (int col = 0; col < line.length(); col++) {
          if (line.charAt(col) == '#') {
            mazeGrid[row][col] = 1; // Wall
          } else if (line.charAt(col) == ' ') {
            mazeGrid[row][col] = 0; // Path
          }
        }
        
        // Fill any remaining spaces (useful for the exit row)
        for (int extraCol = line.length(); extraCol < mazeWidth; extraCol++) {
          mazeGrid[row][extraCol] = 0; // Default to path
        }
        row++;
      }
    } catch (Exception e) {
      System.out.println("Error while populating the maze grid.");
    }
  }

  // Method to print the maze to the console
  public void displayMaze() {
    for (int row = 0; row < mazeHeight; row++) {
      for (int col = 0; col < mazeWidth; col++) {
        if (mazeGrid[row][col] == 1) {
          System.out.print("#");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }

  // Method to determine the entry and exit points of the maze
  public void entryExitPoints() {
    for (int row = 0; row < mazeHeight; row++) {
     
      if (mazeGrid[row][0] == 0) {
        entryRow = row;
      }
      
      if (mazeGrid[row][mazeWidth - 1] == 0) {
        exitRow = row;
      }
    }
  }

  public boolean isValidPath(String path) {
    int currentRow = entryRow;
    int currentCol = 0;
    char currentDirection = 'E'; // Starting direction (East)

    for (char move : path.toCharArray()) {
      if (move == 'F') {
        switch (currentDirection) {
          case 'E': currentCol++; break;
          case 'W': currentCol--; break;
          case 'N': currentRow--; break;
          case 'S': currentRow++; break;
        }
      } else if (move == 'R') {
        currentDirection = turnRight(currentDirection);
      } else if (move == 'L') {
        currentDirection = turnLeft(currentDirection);
      }

      // Check bounds and walls
      if (currentRow < 0 || currentRow >= mazeHeight || currentCol < 0 || currentCol >= mazeWidth || mazeGrid[currentRow][currentCol] == 1) {
        return false; // Out of bounds or hit a wall
      }
    }

    // Check if we reached the exit
    return currentRow == exitRow && currentCol == mazeWidth - 1;
  }

  private char turnRight(char direction) {
    switch (direction) {
      case 'E': return 'S';
      case 'S': return 'W';
      case 'W': return 'N';
      case 'N': return 'E';
    }
    return direction; // Should not reach here
  }

  private char turnLeft(char direction) {
    switch (direction) {
      case 'E': return 'N';
      case 'N': return 'W';
      case 'W': return 'S';
      case 'S': return 'E';
    }
    return direction; // Should not reach here
  }


}
