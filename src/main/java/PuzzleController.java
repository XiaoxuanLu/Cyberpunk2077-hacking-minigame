import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PuzzleController implements Initializable {
    @FXML Label buffer;
    @FXML Label sequences;
    @FXML GridPane matrix;
    @FXML Label bufferSize;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        setSequences();
        setMatrix();
        setEntriesLeft();
    }

    private Button createCell(int i, int j){
        Button cell = new Button(Puzzle.getGrid()[i][j]);
        cell.setPrefSize(60,60);
        cell.setId(""+i+j);//For direction handling
        return buttonClick(cell);
    }

    private Button buttonClick(Button cell){
        cell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if (ClickableCells.getClickableCells().contains(cell.getId())) {
                    Buffer.addToBuffer(cell.getText());
                    setBuffer(Buffer.getBuffer());
                    setEntriesLeft();
                    ClickableCells.addToClickedList(cell.getId());
                    ClickableCells.createCoordinates(Integer.parseInt(cell.getId()));
                    paintButtons(cell);
                    if (Checker.checkBufferSize()){
                        try {
                            Checker.setWin(Checker.numberOfMatch());
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("end.fxml"));

                            Parent endView = loader.load();
                            Scene endScene = new Scene(endView);

                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(endScene);
                            stage.show();
                        } catch (Exception e){
                            System.out.println("an error occurred");
                        }
                    }
                }
            }
        });
        return cell;
    }

    private void setMatrix(){
        if (Puzzle.getGridSize() == 6) {
            matrix.addColumn(0);
            matrix.addRow(0);
        }
        for (int i = 0; i < Puzzle.getGridSize(); i++){
            for (int j = 0; j < Puzzle.getGridSize(); j++){
                Button cell = createCell(i,j);
                matrix.add(cell,i,j);
            }
        }
    }

    private void paintButtons(Button button){
        button.setStyle("-fx-background-color: red");
    }

    private void setSequences(){
        String temp = "";
        for (String[] i : Puzzle.getSequence()){
            for (String j : i){
                temp += j + " ";
            }
            temp += "\n";
        }
        sequences.setText(temp);
    }

    public void setBuffer(ArrayList<String> list){
        buffer.setText(String.valueOf(list));
    }

    private void setEntriesLeft(){
        bufferSize.setText("Entries left: " + (Buffer.getBufferSize() - Buffer.getBuffer().size()));
    }
}