import java.util.ArrayList;

public class Puzzle {
    private static int gridSize = 0;
    private static String[][] grid;
    private static String[][] sequence;

    public static void allocGrid(){
        grid = new String[gridSize][gridSize];
    }

    public static void setGrid(int line, String[] list){
        grid[line] = list;
    }

    public static String[][] getGrid(){
        return grid;
    }

    public static void setSequence(ArrayList<Integer> sequenceLengths, String[] list){
        sequence = new String[sequenceLengths.size()][];
        int counter = 0;

        for (int i = 0; i < sequenceLengths.size(); i++){
            String[] temp = new String[sequenceLengths.get(i)];
            for (int j = 0; j < sequenceLengths.get(i); j++){
                temp[j] = list[counter + j];
            }
            counter += sequenceLengths.get(i);
            sequence[i] = temp;
        }
    }

    public static String[][] getSequence(){
        return sequence;
    }

    public static void setGridSize(int temp){
        gridSize = temp;
    }

    public static int getGridSize(){
        return gridSize;
    }
}