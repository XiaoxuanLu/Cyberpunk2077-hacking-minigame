package softwaredesign;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.util.Timer;
import java.util.TimerTask;

public class GameScreenController {
    @FXML AnchorPane puzzleSelector;
    @FXML AnchorPane puzzleScreen;
    @FXML AnchorPane endScreen;
    @FXML Text sequencesFound;
    @FXML Text win;
    @FXML Text lose;
    @FXML Button tryAgain;
    @FXML Button goBack;
    @FXML Button quit;
    @FXML ImageView winImage;
    @FXML ImageView loseImage;
    @FXML Label entries;
    @FXML Label sequences;
    @FXML GridPane matrix;
    @FXML Label remainingEntries;
    @FXML Label timerText;
    @FXML Button undoButton;
    @FXML Button redoButton;
    @FXML Button enterPuzzleButton;
    @FXML TextField puzzleNumber;
    @FXML Text errorText;

    //Creating object which will be used
    private GameEngine gameEngine;
    private Timer timer;

    //This is less than the original value since the countdown starts to count after 1 second it is initialized
    private final int TIME = 29;

    //Variable has to be outside of the Timer method
    private int seconds = TIME;

    //Timer method and its variable
    private Timer setTimer() {
        //Creates a new object to
        Timer countdown = new Timer();
        resetSeconds();

        //Sets the timerText on screen
        Platform.runLater(() -> {
            timerText.setStyle("-fx-background-color: null");
            timerText.setText("Time: " + (seconds + 1));
        });

        //Creates a new TimerTask and sets it to countdown from "seconds"
        countdown.schedule(new TimerTask() {
            public void run() {
                //Reduces "second" by 1 each second
                if (seconds > 0) {
                    Platform.runLater(() -> {
                        //Runs platform commands to reach the original thread
                        if (seconds <= 10)  timerText.setStyle("-fx-background-color: #ff0000");
                        else timerText.setStyle("-fx-background-color: null");
                        timerText.setText("Time: " + seconds);
                        seconds--;
                    });
                } else {
                    //Ends the game if timer is up
                    Platform.runLater(() -> {
                        resetSeconds();
                        endGame();
                        timerText.setStyle("-fx-background-color: null");
                        timerText.setText("Time: " + seconds);
                    });
                    countdown.cancel();
                }
            }
        }, 1000,1000);

        //Returns countdown to cancel it manually after the game is ended due to full buffer
        return countdown;
    }

    //Resets the seconds value to use again
    private void resetSeconds() {
        seconds = TIME;
    }

    //Helper parseId method to convert string to int from the input of puzzleNumber
    private int parseId() {
        return Integer.parseInt(puzzleNumber.getText());
    }

    //Button function of the start screen
    public void enterPuzzleButtonAction() {
        try {
            //Checks the input errors and start the game
            if (puzzleNumber.getText().isEmpty() || parseId() > 40 || parseId() < 1) {
                errorText.setVisible(true);
            } else {
                //Sets the timer and stars the game
                timer = setTimer();
                startGame();
            }
        } catch (Exception e) {
            errorText.setVisible(true);
        }
    }

    //Start game function
    private void startGame() throws Exception {
        //Creates a new GameEngine and Timer
        gameEngine = new GameEngine();

        //Reads file and fills the containers: Buffer, ClickableCells, Grid, Sequences, Checker
        gameEngine.parseGameConfiguration(puzzleNumber.getText());

        //Managing the scene visibility
        puzzleSelector.setVisible(false);
        puzzleScreen.setVisible(true);

        //Set buttons on screen
        setButtons();

        //Show sequence list on the screen
        sequences.setText(gameEngine.sequences.getSequencesAsString());

        //Update the material on the screen
        updateScreenMaterials();

        //Adding current state to the state stack
        gameEngine.addCurrentState();
    }

    //Creates buttons, adds them to both grid and the visible matrix on the screen
    private void setButtons() {
        gameEngine.grid.setMatrixAlignment(matrix);
        for (int i = 0; i < gameEngine.grid.getGridSize(); i++) {
            for (int j = 0; j < gameEngine.grid.getGridSize(); j++) {
                Cell cell = gameEngine.grid.getTile(i,j);
                setButtonClickAction(cell);
                matrix.add(cell.getBUTTON(),i,j);
            }
        }
    }

    //Gives the button the on-click feature and returns it
    private void setButtonClickAction(Cell cell) {
        cell.getBUTTON().setOnAction(event -> {
            if (gameEngine.grid.clickableCells.isClickable(cell.getCOORDINATE())) {
                gameEngine.buffer.addToBuffer(cell.getBUTTON().getText());
                gameEngine.grid.clickableCells.addToClickedList(cell.getCOORDINATE());
                gameEngine.addCurrentState();
                updateScreenMaterials();
                if (gameEngine.checker.isAllSequencesFound() || gameEngine.buffer.isBufferFull()) {
                    try {
                        endGame();
                    } catch (Exception e) {
                        System.out.println("an error occurred");
                    }
                }
            }
        });
    }

    //This method calls the required methods to prepare end screen
    private void endGame() {
        //Clear the buttons in matrix
        matrix.getChildren().clear();

        //Cancel the timer
        timer.cancel();

        //Managing the scene visibility
        puzzleScreen.setVisible(false);
        endScreen.setVisible(true);

        //Check if the user has won the game
        if (gameEngine.checker.hasWon()) makeWin();
        else makeLose();

        //Tell the user if he/she found any sequences
        sequencesFound.setText(gameEngine.checker.getMessage());
    }

    //Updates the screen material for:
    private void updateScreenMaterials() {
        //Current buffer
        entries.setText(gameEngine.buffer.getBuffer().toString());
        //Remaining entries
        remainingEntries.setText(gameEngine.buffer.getEntriesLeftAsString());
        //Current grid
        gameEngine.grid.updateGrid();
        //Undo and Redo
        updateButtonVisible();
    }

    //Updates button visibility of undo and redo
    private void updateButtonVisible() {
        Pair<Integer,Integer> states = gameEngine.getStateSizes();
        undoButton.setVisible(states.getKey() > 0);
        redoButton.setVisible(states.getValue() > 0);
    }

    //Manage visibility of elements for win conditions
    private void makeWin() {
        win.setVisible(true);
        lose.setVisible(false);
        winImage.setVisible(true);
        loseImage.setVisible(false);
    }

    //Manage visibility of elements for lose conditions
    private void makeLose() {
        win.setVisible(false);
        lose.setVisible(true);
        winImage.setVisible(false);
        loseImage.setVisible(true);
    }

    //"Try Another Puzzle" button function
    public void goBackAction() {
        puzzleNumber.clear();
        errorText.setVisible(false);
        endScreen.setVisible(false);
        puzzleSelector.setVisible(true);
    }

    //"Quit" button function
    public void quitAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //"Try Same Puzzle" button function
    public void tryAgainAction() throws Exception {
        endScreen.setVisible(false);
        errorText.setVisible(false);
        timer = setTimer();
        startGame();
    }

    //"Redo" button function
    public void redoButtonAction() {
        gameEngine.redo();
        updateScreenMaterials();
        gameEngine.checker.numberOfMatch();
    }

    //"Undo" button function
    public void undoButtonAction() {
        gameEngine.undo();
        updateScreenMaterials();
        gameEngine.checker.numberOfMatch();
    }
}