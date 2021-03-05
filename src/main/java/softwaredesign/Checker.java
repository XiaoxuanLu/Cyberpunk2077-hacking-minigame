package softwaredesign;

public class Checker {
    private static int matches = 0;

    public static boolean isBufferFull(){
        return (Buffer.getBuffer().size() == Buffer.getBufferSize());
    }

    public static boolean isAllSequencesFound(){
        return (numberOfMatch() == Puzzle.getSequence().length);
    }

    public static void reset(){
        matches = 0;
    }

    public static int getMatches(){
        return matches;
    }

    public static int numberOfMatch(){
        reset();
        String[][] sequences = Puzzle.getSequence();
        String[] buffer = Buffer.getBuffer().toArray(new String[0]);
        int[] rewarding = new int[sequences.length];

        for (int k = 0; k < sequences.length; k++){
            for (int i = 0; i < buffer.length; i++){
                if(buffer[i].equals(sequences[k][0])){
                    int counter = 0;
                    for (int j = 0; j < sequences[k].length; j++){
                        if (i + j < buffer.length) {
                            if (buffer[i + j].equals(sequences[k][j])) {
                                counter++;
                                if (counter == sequences[k].length) {
                                    rewarding[k] = 1;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i : rewarding) {
            matches += i;
        }
        return matches;
    }
}