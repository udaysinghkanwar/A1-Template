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

            Options options = new Options();
            options.addOption("i", true, "Path to the input maze file");
            options.addOption("p", true, "input path to validate");

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            if (!cmd.hasOption("i")) { throw new Exception("no command given"); }
            if (!cmd.hasOption("p")) {
                throw new Exception("Path (-p) not provided.");
            }
            
            String mazeFilePath = cmd.getOptionValue("i");
            System.out.println(mazeFilePath);

            String path = cmd.getOptionValue("p");

            Maze m = new Maze(mazeFilePath, logger);
            m.populateMazeGrid();
            m.displayMaze();
            m.entryExitPoints();

            if (m.isValidPath(path)) {
                System.out.println("The path is valid.");
              } else {
                System.out.println("The path is invalid.");
              }

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}


