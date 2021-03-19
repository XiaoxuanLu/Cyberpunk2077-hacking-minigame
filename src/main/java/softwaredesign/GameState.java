package softwaredesign;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class GameState {
    //Variables of the GameState class
    private ArrayList<String> myBuffer = new ArrayList<>();
    private ArrayList<String> myClickableList = new ArrayList<>();
    private ArrayList<String> myClickedList = new ArrayList<>();
    private Direction myDirection;
    private ArrayList<ArrayList<Button>> myGrid = new ArrayList<>();

    //GameState constructor with the desired arguments
    public void constructGameState(ArrayList<String> buffer1, ArrayList<String> clickableList1, ArrayList<String> clickedList1, Direction direction1, ArrayList<ArrayList<Button>> grid1) {
        this.myBuffer = buffer1;
        this.myClickableList = clickableList1;
        this.myClickedList = clickedList1;
        this.myDirection = direction1;
        this.myGrid = grid1;
    }

    //These 5 methods return the current state's variables in the desired type
    public ArrayList<String> getMyBuffer() {
        return new ArrayList<>(this.myBuffer);
    }
    public ArrayList<String> getMyClickableList() {
        return new ArrayList<>(this.myClickableList);
    }
    public ArrayList<String> getMyClickedList() {
        return new ArrayList<>(this.myClickedList);
    }
    public Direction getMyDirection() {
        return myDirection;
    }
    public ArrayList<ArrayList<Button>> getMyGrid() {
        return new ArrayList<>(this.myGrid);
    }
}
