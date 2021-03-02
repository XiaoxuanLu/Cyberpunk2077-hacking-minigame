import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
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
    @FXML ImageView winimage;
    @FXML ImageView loseimage;

    public void makeWin(){
        win.setVisible(true);
        lose.setVisible(false);
        winimage.setVisible(true);
        loseimage.setVisible(false);
        sequences.setText("You have found " + Checker.getWin() + " sequences.");
    }

    public void makeLose(){
        win.setVisible(false);
        lose.setVisible(true);
        winimage.setVisible(false);
        loseimage.setVisible(true);
        sequences.setText("You couldn't find any sequence.");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        if (Checker.getWin() > 0){
            makeWin();
        } else {
            makeLose();
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