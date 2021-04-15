package softwaredesign;

import javafx.scene.control.Button;
import javafx.util.Pair;

public class Cell {
    private final int BUTTON_SIZE = 55; //This is for changing values easier in the code
    private final Button BUTTON;
    private final Pair<Integer,Integer> COORDINATE;

    public Cell(int x, int y, String hash) {
        BUTTON = createButton(hash);
        COORDINATE = new Pair<>(x,y);
    }

    private Button createButton(String hash){
        Button myButton = new Button();
        myButton.setText(hash);
        myButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        return myButton;
    }

    public void paintButton(String style) {
        BUTTON.setStyle(style);
    }

    public Pair<Integer,Integer> getCOORDINATE() {
        return COORDINATE;
    }

    public Button getBUTTON() {
        return BUTTON;
    }
}
