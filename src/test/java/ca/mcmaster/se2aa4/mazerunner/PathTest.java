package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

//FRFLFFLFRF
//LFFFF
class PathTest {
    private Maze maze;
    private static final String TEST_MAZE = """
            #####
            #   #
               
            #   #
            #####
            """;


    private static final String NO_EXIT_MAZE = """
            #####
            #   #
                #
            #   #
            #####
            """;

    private static final String COMPLEX_MAZE = """
            ######
            #    #
              #  #
            #     
            ######
            """;

    private static final String EDGE_MAZE = """
            #####
            #   #
                
            #   # 
            #   
            #####
            """;

    @BeforeEach
    void setUp(@TempDir Path tempDir) throws IOException {
        Path mazePath = tempDir.resolve("test_maze.txt");
        Files.writeString(mazePath, TEST_MAZE);
        maze = new Maze(mazePath.toString(), org.apache.logging.log4j.LogManager.getLogger());
    }

    @Test
    @DisplayName("Test empty path throws exception")
    void testEmptyPath() {
        Exception exception = assertThrows(Exception.class, () -> {
            maze.isValidPath("");
        });
        assertEquals("no -i command given", exception.getMessage());
    }

    @Test
    @DisplayName("Test valid path reaching exit")
    void testValidPath() throws Exception {
        // Simple path from left to right
        String path = "FFFF";
        assertTrue(maze.isValidPath(path));
    }

    @Test
    @DisplayName("Test invalid path hitting wall")
    void testPathHittingWall() throws Exception {
        // Path that leads into a wall
        String path = "FFFLF";
        assertFalse(maze.isValidPath(path));
    }

    @Test
    @DisplayName("Test path going out of bounds")
    void testOutOfBoundsPath() throws Exception {
        // Path that would go outside maze boundaries
        String path = "LFFFF";
        assertFalse(maze.isValidPath(path));
    }

    @Test
    @DisplayName("Test complex path with turns")
    void testComplexPath() throws Exception {
        // Path with multiple valid turns
        String path = "FRFLFLFRFF";
        assertTrue(maze.isValidPath(path));
    }

    @Test
    @DisplayName("Invalid input")
    void testInvalidInpu() throws Exception {
        // Path with turns that lead to invalid position
        String path = "!!45F";
        assertFalse(maze.isValidPath(path));
    }

    @Test
    @DisplayName("Test solving straight maze")
    void testSolveStraightMaze(@TempDir Path tempDir) throws Exception {
        Path mazePath = tempDir.resolve("test_maze.txt");
        Files.writeString(mazePath, TEST_MAZE);
        Maze straightMaze = new Maze(mazePath.toString(), org.apache.logging.log4j.LogManager.getLogger());
        MazeSolver solver = new MazeSolver(straightMaze);
    
        String path = solver.solveMaze();
        assertEquals(path, "FRFLFFLFRF");
        assertEquals("FRFL2FLFRF", solver.factorizedPath(path));
    }


    @Test
    @DisplayName("Test solving complex maze")
    void testSolveComplexMaze(@TempDir Path tempDir) throws Exception {
        Path mazePath = tempDir.resolve("complex_maze.txt");
        Files.writeString(mazePath, COMPLEX_MAZE);
        Maze complexMaze = new Maze(mazePath.toString(), org.apache.logging.log4j.LogManager.getLogger());
        MazeSolver solver = new MazeSolver(complexMaze);
        
        String path = solver.solveMaze();
        assertEquals(path, "FRFLFFFF");
    }

    @Test
    @DisplayName("Test maze with two exits")
    void testSolveEdgeMaze(@TempDir Path tempDir) throws Exception {
        Path mazePath = tempDir.resolve("edge_maze.txt");
        Files.writeString(mazePath, EDGE_MAZE);
        Maze edgeMaze = new Maze(mazePath.toString(), org.apache.logging.log4j.LogManager.getLogger());
        MazeSolver solver = new MazeSolver(edgeMaze);
        
        String path = solver.solveMaze();
        assertEquals(path, "FRFFLFFF");
    }
      
    
      @Test
      @DisplayName("Test maze with no exit throws exception")
      void testNoExitMaze(@TempDir Path tempDir) throws Exception {
        Path mazePath = tempDir.resolve("no_exit_maze.txt");
        Files.writeString(mazePath, NO_EXIT_MAZE);
        Maze edgeMaze = new Maze(mazePath.toString(), org.apache.logging.log4j.LogManager.getLogger());
        MazeSolver solver = new MazeSolver(edgeMaze);
        
        
        
        Exception exception = assertThrows(Exception.class, () -> {
            solver.solveMaze();
        });
        
        assertEquals("No valid exit point", exception.getMessage());
      }
    
}

