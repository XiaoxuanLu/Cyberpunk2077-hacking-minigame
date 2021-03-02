import java.util.ArrayList;

public class ClickableCells {
    private static ArrayList<String> idList = new ArrayList<String>();
    private static ArrayList<String> clickedList = new ArrayList<String>();
    private static int direction = 0;

    public static void reset(){
        idList.clear();
        clickedList.clear();
        direction = 0;
    }

    public static void initClickableCells(){
        createCoordinates(0);
    }

    public static void createCoordinates(int val){
        idList.clear();
        if (direction == 1){
            for (int i = 0; i < Puzzle.getGridSize(); i++){
                String temp = "" + val / 10 + i;
                if (!clickedList.contains(temp)){
                    idList.add(temp);
                }
            }
            direction = 0;
        } else {
            for (int i = 0; i < Puzzle.getGridSize(); i++){
                String temp = "" + i + val % 10;
                if (!clickedList.contains(temp)){
                    idList.add(temp);
                }
            }
            direction = 1;
        }
    }

    public static void addToClickedList(String id){
        clickedList.add(id);
    }

    public static ArrayList<String> getClickableCells(){
        return idList;
    }

    public static ArrayList<String> getClickedCells(){
        return clickedList;
    }
}