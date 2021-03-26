package softwaredesign;

import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class Grid {
    //Variables of the Grid class
    private int gridSize = 0;
    public ClickableCells clickableCells;
    private ArrayList<ArrayList<Cell>> grid;

    //Creates the grid with the input read from the file
    public void createGrid(ArrayList<ArrayList<String>> hashGrid) {
        gridSize = hashGrid.size();
        grid = new ArrayList<>(gridSize);
        for (int i = 0; i < hashGrid.size(); i++){
            ArrayList<Cell> cellRow = new ArrayList<>();
            for (int j = 0; j < gridSize; j++){
                cellRow.add(new Cell(i,j,hashGrid.get(i).get(j)));
            }
            this.grid.add(cellRow);
        }
        initClickableCells();
    }

    //This function creates a ClickableCells object and initializes it
    private void initClickableCells() {
        clickableCells = new ClickableCells(gridSize);
        clickableCells.createFirstCells();
    }

    //Updates the grid's colors
    public void updateGrid() {
        for (ArrayList<Cell> row : grid) {
            for (Cell cell : row) {
                if (clickableCells.isClickable(cell.getCOORDINATE())) {
                    cell.paintButton("-fx-background-color: lime; -fx-font-size: 20px");
                } else if (clickableCells.isClicked(cell.getCOORDINATE())) {
                    cell.paintButton("-fx-background-color: red; -fx-font-size: 20px");
                } else {
                    cell.paintButton("-fx-background-color: null; -fx-font-size: 20px");
                }
            }
        }
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

    //Sets buttonGrid to a new ArrayList<>
    public void setGrid(ArrayList<ArrayList<Cell>> newButtonGrid) {
        grid = newButtonGrid;
    }

    //Returns the buttonGrid
    public ArrayList<ArrayList<Cell>> getGrid() {
        return new ArrayList<>(grid);
    }

    //Returns a tile
    public Cell getTile(int i, int j) {
        return grid.get(i).get(j);
    }

    //Returns the grid size
    public int getGridSize() {
        return gridSize;
    }
}