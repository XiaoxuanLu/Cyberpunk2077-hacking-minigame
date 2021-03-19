package softwaredesign;

import java.util.ArrayList;

//Global enumerator to detect the axis of the next move
enum Direction {
    HORIZONTAL,
    VERTICAL
}

public class ClickableCells {
    //Variables of the ClickableCells class
    private final int gridSize;
    private ArrayList<String> clickableList = new ArrayList<>();
    private ArrayList<String> clickedList = new ArrayList<>();
    private Direction direction = Direction.HORIZONTAL;

    //Constructor
    public ClickableCells(int myGridSize) {
        this.gridSize = myGridSize;
    }

    //Initializes the first move of the game
    public void createFirstCells() {
        this.direction = Direction.HORIZONTAL;
        this.createClickableCells(0);
    }

    //Clears the previous list and creates new clickable cells and parses them
    public void createClickableCells(int val) {
        this.clickableList.clear();
        switch (direction) {
            case VERTICAL:
                this.createCoordinates(val);
                this.direction = Direction.HORIZONTAL;
                break;
            case HORIZONTAL:
                this.createCoordinates(val);
                this.direction = Direction.VERTICAL;
                break;
        }
    }

    //Creates coordinates for the picked tile and the current direction, adds them to the list
    private void createCoordinates(int val) {
        for (int i = 0; i < gridSize; i++) {
            String twoDigitCoordinate = "";

            //Decomposes the id of the tile to create coordinates of it
            switch (direction) {
                case VERTICAL: twoDigitCoordinate = "" + val / 10 + i;
                break;
                case HORIZONTAL: twoDigitCoordinate = "" + i + val % 10;
                break;
            }
            if (!this.clickedList.contains(twoDigitCoordinate)) {
                this.clickableList.add(twoDigitCoordinate);
            }
        }
    }

    //Sets the direction
    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    //Sets the clickableList to a new one
    public void setClickableList(ArrayList<String> newClickableList) {
        this.clickableList = newClickableList;
    }

    //Sets the clickedList to a new one
    public void setClickedList(ArrayList<String> newClickedList) {
        this.clickedList = newClickedList;
    }

    //Adds an element to the clickedList
    public void addToClickedList(String id) {
        this.clickedList.add(id);
        this.createClickableCells(Integer.parseInt(id));
    }

    //Returns clickableCells
    public ArrayList<String> getClickableCells() {
        return new ArrayList<>(this.clickableList);
    }

    //Check if the argument ID exists in the clickedList
    public boolean isClicked(String id) {
        return this.getClickedCells().contains(id);
    }

    //Returns clickedList
    public ArrayList<String> getClickedCells() {
        return new ArrayList<>(this.clickedList);
    }

    //Check if the argument ID exists in the clickableList
    public boolean isClickable(String id) {
        return this.getClickableCells().contains(id);
    }

    //Returns current direction
    public Direction getDirection() {
        return this.direction;
    }
}