package softwaredesign;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GameEngine {
    private static int puzzleID;

    public static void setPuzzleID(int ID){
        puzzleID = ID;
    }

    public static String ignoreEmptyLine(Scanner myReader, String line){
        while (line.isEmpty()) {
            line = myReader.nextLine();
        }
        return line;
    }

    public static void readSequences(Scanner myReader, String line){
        //Initialize element counter for each of the sequences
        ArrayList<Integer> sequenceLengths = new ArrayList<>();

        //Split the first row of the sequences
        String[] list = line.split(" ");
        sequenceLengths.add(list.length);

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] temp = data.split(" ");
            sequenceLengths.add(temp.length);
            String[] newList = new String[list.length + temp.length];
            System.arraycopy(list,0,newList,0,list.length);
            System.arraycopy(temp,0,newList,list.length,temp.length);
            list = newList;
        }

        Puzzle.setSequence(sequenceLengths,list);
    }

    public static void readGrid(Scanner myReader, String line){
        //Split the first row of the grid
        String[] list = line.split(" ");
        Puzzle.setGridSize(list.length);

        Puzzle.allocGrid();
        Puzzle.setGrid(0,list);

        //Read from file and fill the grid
        for (int i = 0; i < Puzzle.getGridSize() - 1; i++) {
            line = myReader.nextLine();
            list = line.split(" ");
            Puzzle.setGrid(i+1,list);
        }

    }

    public static void fileHandler() throws Exception {
        String line = "";
        //Opening file
        InputStream is = GameEngine.class.getClassLoader().getResourceAsStream("puzzles/" + puzzleID + ".txt");
        if (is == null){
            throw new Exception("file not found");
        }

        Scanner myReader = new Scanner(is);

        //Read buffer size
        Buffer.setBufferSize(Integer.parseInt(myReader.nextLine()));

        //Ignore empty lines
        line = ignoreEmptyLine(myReader,line);

        //Reading grid from txt file
        readGrid(myReader,line);

        //Initializing clickable coordinates as the first row of the matrix
        ClickableCells.initClickableCells();
        line = "";

        //Ignore empty lines
        line = ignoreEmptyLine(myReader,line);

        //Reading sequences from txt file
        readSequences(myReader,line);

        myReader.close();
    }
}