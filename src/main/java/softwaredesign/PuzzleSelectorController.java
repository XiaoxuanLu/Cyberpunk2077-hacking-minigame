package softwaredesign;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class PuzzleSelectorController {
    @FXML Button enterPuzzleButton;
    @FXML TextField puzzleNumber;
    @FXML Text errorText;

    public void enterPuzzleButtonAction(javafx.event.ActionEvent event) throws IOException{
        if(puzzleNumber.getText().isEmpty()
                || Integer.parseInt(puzzleNumber.getText()) > 40
                || Integer.parseInt(puzzleNumber.getText()) < 1){
            errorText.setVisible(true);
        } else {
            GameEngine.setPuzzleID(Integer.parseInt(puzzleNumber.getText()));
            GameEngine.fileHandler();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("puzzle.fxml"));

            Parent puzzleView = loader.load();
            Scene puzzleScene = new Scene(puzzleView);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(puzzleScene);
            stage.show();
        }
    }
}