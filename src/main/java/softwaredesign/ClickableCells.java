package softwaredesign;

import javafx.util.Pair;

import java.util.ArrayList;

//Global enumerator to detect the axis of the next move
enum Direction {
    HORIZONTAL,
    VERTICAL
}

public class ClickableCells {
    //Variables of the ClickableCells class
    private final int GRID_SIZE;
    private ArrayList<Pair<Integer,Integer>> clickableList = new ArrayList<>();
    private ArrayList<Pair<Integer,Integer>> clickedList = new ArrayList<>();
    private Direction direction = Direction.HORIZONTAL;

    //Constructor
    public ClickableCells(int myGridSize) {
        GRID_SIZE = myGridSize;
    }

    //Initializes the first move of the game
    public void createFirstCells() {
        direction = Direction.HORIZONTAL;
        Pair<Integer,Integer> firstCoordinate = new Pair<>(0,0);
        createClickableCells(firstCoordinate);
    }

    //Clears the previous list and creates new clickable cells and parses them
    public void createClickableCells(Pair<Integer,Integer> coordinate) {
        this.clickableList.clear();
        switch (direction) {
            case VERTICAL:
                createCoordinates(coordinate);
                direction = Direction.HORIZONTAL;
                break;
            case HORIZONTAL:
                createCoordinates(coordinate);
                direction = Direction.VERTICAL;
                break;
        }
    }

    //Creates coordinates for the picked tile and the current direction, adds them to the list
    private void createCoordinates(Pair<Integer,Integer> coordinate) {
        for (int i = 0; i < GRID_SIZE; i++) {
            //Decomposes the id of the tile to create coordinates of it
            Pair<Integer,Integer> coordinateToAdd = new Pair<>(0,0);
            switch (direction) {
                case VERTICAL: coordinateToAdd = new Pair<>(coordinate.getKey(),i);
                break;
                case HORIZONTAL: coordinateToAdd = new Pair<>(i,coordinate.getValue());
                break;
            }
            if (!clickedList.contains(coordinateToAdd)) {
                clickableList.add(coordinateToAdd);
            }
        }
    }

    //Sets the direction
    public void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    //Sets the clickableList to a new one
    public void setClickableList(ArrayList<Pair<Integer,Integer>> newClickableList) {
        clickableList = newClickableList;
    }

    //Sets the clickedList to a new one
    public void setClickedList(ArrayList<Pair<Integer,Integer>> newClickedList) {
        clickedList = newClickedList;
    }

    //Adds an element to the clickedList
    public void addToClickedList(Pair<Integer,Integer> coordinate) {
        clickedList.add(coordinate);
        createClickableCells(coordinate);
    }

    //Returns clickableCells
    public ArrayList<Pair<Integer,Integer>> getClickableCells() {
        return new ArrayList<>(clickableList);
    }

    //Check if the argument ID exists in the clickedList
    public boolean isClicked(Pair<Integer,Integer> coordinate) {
        return getClickedCells().contains(coordinate);
    }

    //Returns clickedList
    public ArrayList<Pair<Integer,Integer>> getClickedCells() {
        return new ArrayList<>(clickedList);
    }

    //Check if the argument ID exists in the clickableList
    public boolean isClickable(Pair<Integer,Integer> coordinate) {
        return getClickableCells().contains(coordinate);
    }

    //Returns current direction
    public Direction getDirection() {
        return direction;
    }
}