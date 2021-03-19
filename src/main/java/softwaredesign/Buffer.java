package softwaredesign;

import java.util.ArrayList;

public class Buffer {
    //Containers of the Buffer class
    private int bufferSize;
    private ArrayList<String> buffer = new ArrayList<>();

    //Checks the buffer
    public boolean isBufferFull() {
        return (this.getBuffer().size() == this.getBufferSize());
    }

    //Adds an element to the buffer
    public void addToBuffer(String hash) {
        this.buffer.add(hash);
    }

    //Gets buffer as an ArrayList<>
    public ArrayList<String> getBuffer() {
        return new ArrayList<>(this.buffer);
    }

    //Sets buffer size throws if the input has a wrong type
    public void setBufferSize(String temp) throws Exception{
        try {
            this.bufferSize = Integer.parseInt(temp);
        } catch (Exception e) {
            throw new Exception("BufferSize couldn't set");
        }
    }

    //Returns buffer size
    public int getBufferSize() {
        return this.bufferSize;
    }

    //Calculates entries remaining and returns it as a desired string
    public String getEntriesLeftAsString() {
        return "Entries left: " + (this.getBufferSize() - this.getBuffer().size());
    }

    //Equals buffer to another buffer
    public void setBuffer(ArrayList<String> newBuffer) {
        this.buffer = newBuffer;
    }
}