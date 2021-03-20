package softwaredesign;

import javafx.util.Pair;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class GameEngine {
    //Variables of the GameEngine class
    private final String PUZZLE_LOCATION = "puzzles/";
    private Stack<GameState> stateStack = new Stack<>();
    private Stack<GameState> undoStack = new Stack<>();

    //Creates its containers
    public Buffer buffer = new Buffer();
    public Sequences sequences = new Sequences();
    public Grid grid = new Grid();
    public Checker checker = new Checker(buffer,sequences);

    //Reads file and stars the parsing
    public void parseGameConfiguration(String puzzleID) throws Exception {
        try {
            String line = "";
            //Opening file
            InputStream is = GameEngine.class.getClassLoader().getResourceAsStream(this.PUZZLE_LOCATION + puzzleID + ".txt");
            if (is == null) {
                throw new FileNotFoundException("file not found");
            }

            Scanner myReader = new Scanner(is);

            //Read buffer size
            this.buffer.setBufferSize(myReader.nextLine());

            //Ignore empty lines
            line = ignoreEmptyLine(myReader, line);

            //Reading grid from txt file
            readGrid(myReader, line);

            //Initializing clickable coordinates as the first row of the matrix
            this.grid.clickableCells = new ClickableCells(this.grid.getGridSize());
            this.grid.clickableCells.createFirstCells();

            line = "";

            //Ignore empty lines
            line = ignoreEmptyLine(myReader, line);

            //Reading sequences from txt file
            readSequences(myReader, line);
            myReader.close();
        }  catch (Exception e) {
            throw new Exception("An error occurred");
        }
    }

    //This method reads the sequences and do the parsing to required container
    private void readSequences(Scanner myReader, String line) throws Exception{
        try {
            //Initialize element counter for each of the sequences
            ArrayList<Integer> sequenceLengths = new ArrayList<>();

            //Split the first row of the sequences
            String[] list = line.split(" ");
            sequenceLengths.add(list.length);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] temp = data.split(" ");

                //Here we hold a list for the length of each sequence.
                sequenceLengths.add(temp.length);

                //Whit this method, we can later decompose the list into each sequence.
                String[] newList = new String[list.length + temp.length];
                System.arraycopy(list, 0, newList, 0, list.length);
                System.arraycopy(temp, 0, newList, list.length, temp.length);
                list = newList;
            }

            this.sequences.setSequenceList(sequenceLengths, list);
        } catch (Exception e) {
            throw new Exception("Sequences couldn't set");
        }
    }

    //This method reads the grid and do the parsing to required container
    private void readGrid(Scanner myReader, String line) throws Exception {
        try {
            //Split the first row of the grid
            String[] tempList = line.split(" ");

            //Convert the list to ArrayList<String> in order to use it in method setGrid
            ArrayList<String> gridRows = new ArrayList<>(Arrays.asList(tempList));

            //Initializing the grid by assigning first line
            this.grid.initGrid(gridRows);

            //Read from file and fill the grid
            while (!line.isEmpty()) {
                line = myReader.nextLine();
                tempList = line.split(" ");
                gridRows = new ArrayList<>(Arrays.asList(tempList));
                this.grid.addToGrid(gridRows);
            }
        } catch (Exception e) {
            throw new Exception("Grid couldn't set");
        }
    }

    //A helper method to ignore empty lines
    private String ignoreEmptyLine(Scanner myReader, String line) {
        while (line.isEmpty()) {
            line = myReader.nextLine();
        }
        return line;
    }

    //Creates current state and adds it to the stateStack
    public void addCurrentState() {
        GameState myState = new GameState();
        myState.constructGameState(this.buffer.getBuffer(),this.grid.clickableCells.getClickableCells(),this.grid.clickableCells.getClickedCells(),this.grid.clickableCells.getDirection(),this.grid.getButtonGrid());
        stateStack.add(myState);
        undoStack.clear();
    }

    /*Undo action gets the last element of the stateStack and parses it to current containers
    * This functions also passes the state to undoStack to use it later with redo method*/
    public void undo() {
        if (stateStack.size() > 1) {
            undoStack.add(stateStack.pop());
            GameState myState = stateStack.peek();

            this.buffer.setBuffer(myState.getMyBuffer());
            this.grid.clickableCells.setClickableList(myState.getMyClickableList());
            this.grid.clickableCells.setDirection(myState.getMyDirection());
            this.grid.clickableCells.setClickedList(myState.getMyClickedList());
            this.grid.setButtonGrid(myState.getMyGrid());
        }
    }

    /*Redo action gets the last element of the undoStack and parses it to current containers
    * This functions also passes the state to stateStack to use it later with undo method*/
    public void redo() {
        if (!undoStack.empty()) {
            GameState myState = undoStack.pop();

            this.buffer.setBuffer(myState.getMyBuffer());
            this.grid.clickableCells.setClickableList(myState.getMyClickableList());
            this.grid.clickableCells.setDirection(myState.getMyDirection());
            this.grid.clickableCells.setClickedList(myState.getMyClickedList());
            this.grid.setButtonGrid(myState.getMyGrid());

            stateStack.add(myState);
        }
    }

    //Returns the number of elements of the stateStack and undoStack to configure button visibility
    public Pair<Integer,Integer> getStateSizes() {
        return new Pair<>(stateStack.size() - 1,undoStack.size());
    }
}