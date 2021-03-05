package softwaredesign;

import java.util.ArrayList;

public class Buffer {
    private static int bufferSize;
    private static ArrayList<String> buffer = new ArrayList<String>();

    public static void emptyBuffer(){
        buffer.clear();
    }

    public static void addToBuffer(String hash){
        buffer.add(hash);
    }

    public static ArrayList<String> getBuffer(){
        return buffer;
    }

    public static void setBufferSize(int temp){
        bufferSize = temp;
    }

    public static int getBufferSize(){
        return bufferSize;
    }
}