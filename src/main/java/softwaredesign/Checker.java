package softwaredesign;

import java.util.ArrayList;

public class Checker {
    //Variables of the Checker class
    private static int matches = 0;
    private Buffer buffer = new Buffer();
    private Sequences sequences = new Sequences();

    //Constructor
    public Checker(Buffer myBuffer, Sequences mySequences) {
        this.buffer = myBuffer;
        this.sequences = mySequences;
    }

    //Checks if all sequences exist in the current buffer
    public boolean isAllSequencesFound() {
        return (numberOfMatch() == this.sequences.getSequenceList().size());
    }

    //Sets the endGame message in desired form
    public String getMessage() {
        if (matches > 0) return "You have found " + matches + " sequence(s).";
        else return "You couldn't find any sequence.";
    }

    //Finds the number of matches in the current buffer
    public int numberOfMatch() {
        matches = 0;

        //Gets sequences and splits them into desired type and format
        ArrayList<ArrayList<String>> sequences = this.sequences.getSequenceList();
        String[] currentBuffer = this.buffer.getBuffer().toArray(new String[0]);
        int[] rewarding = new int[sequences.size()];

        //Uses helper method
        sequenceFinder(sequences,currentBuffer,rewarding);

        for (int i : rewarding) matches += i;
        return matches;
    }

    /*Helper function to count occurrences
    * With this function, the multiple occurrences of a sequence will be ignored */
    private void sequenceFinder(ArrayList<ArrayList<String>> sequences, String[] currentBuffer, int[] rewarding) {
        for (int k = 0; k < sequences.size(); k++) {
            for (int i = 0; i < currentBuffer.length; i++) {
                if(currentBuffer[i].equals(sequences.get(k).get(0))) {
                    int counter = 0;
                    for (int j = 0; j < sequences.get(k).size(); j++) {
                        if (i + j < currentBuffer.length) {
                            if (currentBuffer[i + j].equals(sequences.get(k).get(j))) {
                                counter++;
                                if (counter == sequences.get(k).size()) {
                                    rewarding[k] = 1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //Returns true if user has won
    public boolean hasWon() {
        return matches > 0;
    }
}