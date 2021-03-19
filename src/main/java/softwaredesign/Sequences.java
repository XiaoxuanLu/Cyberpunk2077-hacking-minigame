package softwaredesign;

import java.util.ArrayList;
import java.util.Arrays;

public class Sequences {
    //Variables of the Sequences class
    private ArrayList<ArrayList<String>> sequenceList;

    /*This methods uses two containers as arguments such as sequenceLengths = [2,2] and list = [55,1c,e9,55]
    * By decomposing the list into pieces with the length information from sequenceLengths, we can set the sequences.*/
    public void setSequenceList(ArrayList<Integer> sequenceLengths, String[] list) {
        this.sequenceList = new ArrayList<>(sequenceLengths.size());
        int counter = 0;
        for (Integer sequenceLength : sequenceLengths) {
            ArrayList<String> temp = new ArrayList<>(sequenceLength);
            temp.addAll(Arrays.asList(list).subList(counter, sequenceLength + counter));
            counter += sequenceLength;
            sequenceList.add(temp);
        }
    }

    //Returns the sequenceList in the desired type
    public ArrayList<ArrayList<String>> getSequenceList() {
        return new ArrayList<>(this.sequenceList);
    }

    //Returns the sequenceList in the desired format to show them in the screen
    public String getSequencesAsString() {
        StringBuilder sequenceString = new StringBuilder();
        for (ArrayList<String> w : this.sequenceList) {
            sequenceString.append("[").append(w.get(0));
            for (int i = 1; i < w.size(); i++) {
                sequenceString.append(",").append(w.get(i));
            }
            sequenceString.append("]\n");
        }
        return sequenceString.toString();
    }
}