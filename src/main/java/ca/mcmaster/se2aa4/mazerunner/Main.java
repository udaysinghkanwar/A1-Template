package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        try {
            // -i argument detection
            String path="";
            Options options = new Options();
            options.addOption("i", true, "Path to the input maze file");
            options.addOption("p", true, "input path to validate");

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            if (!cmd.hasOption("i")) { throw new Exception("no -i command given"); }
           
            String mazeFilePath = cmd.getOptionValue("i");
            System.out.println(mazeFilePath);

            Maze m = new Maze(mazeFilePath, logger);
            m.displayMaze();


            if (cmd.hasOption("p")) {

                path = cmd.getOptionValue("p");
                if (m.isValidPath(path)) {
                    System.out.println("The path is valid.");
                  } else {
                    System.out.println("The path is invalid.");
                }
    
            } 
            else {
                MazeSolver solver = new MazeSolver(m);
                String final_path = solver.solveMaze();
                System.out.println("The canonical path is : " + final_path);
                System.out.println("The factorized path is: " + solver.factorizedPath(final_path));
            }

            
            

        } catch(Exception e) {
            System.out.println(e.getMessage());
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}


