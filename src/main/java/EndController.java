import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class EndController implements Initializable {
    @FXML Text sequences;
    @FXML Text win;
    @FXML Text lose;
    @FXML Button tryAgain;
    @FXML Button goBack;
    @FXML Button quit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        if (Checker.getWin() > 0){
            win.setVisible(true);
            lose.setVisible(false);
            sequences.setText("You have found " + Checker.getWin() + " sequences.");
        } else {
            win.setVisible(false);
            lose.setVisible(true);
            sequences.setText("You couldn't find any sequence.");
        }
        Reset.reset();
    }

    public void goBackAction(javafx.event.ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("puzzleSelector.fxml"));

        Parent puzzleView = loader.load();
        Scene puzzleScene = new Scene(puzzleView);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(puzzleScene);
        stage.show();
    }

    public void quitAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void tryAgainAction(javafx.event.ActionEvent event) throws Exception{
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