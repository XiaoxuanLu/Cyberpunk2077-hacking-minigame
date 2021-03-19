package softwaredesign;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class Grid {
    //Variables of the Grid class
    public ClickableCells clickableCells;
    private int gridSize = 0;
    private ArrayList<ArrayList<String>> hashGrid;
    private ArrayList<ArrayList<Button>> buttonGrid;

    //This method is allocating grid space
    private void allocHashGrid() {
        this.hashGrid = new ArrayList<>(this.gridSize);
    }

    //Adds a line to hashGrid
    public void addToGrid(ArrayList<String> listLine) {
        this.hashGrid.add(listLine);
    }

    //Sets the grid size
    public void setGridSize(int size) {
        this.gridSize = size;
    }

    //Returns the grid size
    public int getGridSize() {
        return this.gridSize;
    }

    //Returns the hashGrid in desired type
    public ArrayList<ArrayList<String>> getHashGrid() {
        return new ArrayList<>(this.hashGrid);
    }

    //Sets the screen alignment of the matrix if it is a 5x5 or 6x6 matrix
    public void setMatrixAlignment(GridPane matrix) {
        if (this.getGridSize() == 6) {
            matrix.setLayoutX(484);
            matrix.setLayoutY(45);
        } else {
            matrix.setLayoutX(514);
            matrix.setLayoutY(75);
        }
    }

    //Returns a tile of the matrix
    public String getTile(int i, int j) {
        return getHashGrid().get(j).get(i);
    }

    /*Initializes grid by giving the first line as an argument
    * This method also uses setGridSize and allocateGrid methods*/
    public void initGrid(ArrayList<String> firstRow) {
        setGridSize(firstRow.size());
        allocHashGrid();
        addToGrid(firstRow);
        initClickableCells();
    }

    //This function creates a ClickableCells object and initializes it
    private void initClickableCells() {
        this.clickableCells = new ClickableCells(this.gridSize);
        this.clickableCells.createFirstCells();
    }

    //Creates a new buttonGrid with the grid size
    public void allocButtonGrid() {
        this.buttonGrid = new ArrayList<>(getGridSize());
    }

    //Fills the button grid
    public void addToButtonGrid(ArrayList<Button> buttonRow) {
        this.buttonGrid.add(buttonRow);
    }

    //Sets buttonGrid to a new ArrayList<>
    public void setButtonGrid(ArrayList<ArrayList<Button>> newButtonGrid) {
        this.buttonGrid = newButtonGrid;
    }

    //Updates the grid's colors
    public void updateGrid() {
        for (ArrayList<Button> row : this.buttonGrid) {
            for (Button button : row) {
                if (clickableCells.isClickable(button.getId())) {
                    button.setStyle("-fx-background-color: lime; -fx-font-size: 20px");
                } else if (clickableCells.isClicked(button.getId())) {
                    button.setStyle("-fx-background-color: red; -fx-font-size: 20px");
                } else {
                    button.setStyle("-fx-background-color: null; -fx-font-size: 20px");
                }
            }
        }
    }

    //Returns the buttonGrid
    public ArrayList<ArrayList<Button>> getButtonGrid() {
        return new ArrayList<>(buttonGrid);
    }
}