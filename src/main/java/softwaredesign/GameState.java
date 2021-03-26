package softwaredesign;

import javafx.util.Pair;
import java.util.ArrayList;

public class GameState {
    //Variables of the GameState class
    private final ArrayList<String> BUFFER;
    private final ArrayList<Pair<Integer,Integer>> CLICKABLE_LIST;
    private final ArrayList<Pair<Integer,Integer>> CLICKED_LIST;
    private final Direction DIRECTION;
    private final ArrayList<ArrayList<Cell>> GRID;

    //GameState constructor with the desired arguments
    public GameState(Buffer buffer, Grid grid) {
        this.BUFFER = buffer.getBuffer();
        this.CLICKABLE_LIST = grid.clickableCells.getClickableCells();
        this.CLICKED_LIST = grid.clickableCells.getClickedCells();
        this.DIRECTION = grid.clickableCells.getDirection();
        this.GRID = grid.getGrid();
    }

    //These 5 methods return the current state's variables in the desired type
    public ArrayList<String> getBUFFER() {
        return new ArrayList<>(BUFFER);
    }
    public ArrayList<Pair<Integer,Integer>> getCLICKABLE_LIST() {
        return new ArrayList<>(CLICKABLE_LIST);
    }
    public ArrayList<Pair<Integer,Integer>> getCLICKED_LIST() {
        return new ArrayList<>(CLICKED_LIST);
    }
    public Direction getDIRECTION() {
        return DIRECTION;
    }
    public ArrayList<ArrayList<Cell>> getGRID() {
        return new ArrayList<>(GRID);
    }
}
