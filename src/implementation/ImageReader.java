package implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageReader extends ReadingTimeCounter {

    private FileInputStream fis;
    private int length;
    private byte[] quarter;
    private byte[] lastQuarter;
    private Buffer buffer;

    public ImageReader() {
        // resetarea contorului de timp
        resetCounter();
    }

    public void openForReading(File file, Buffer buffer) throws IOException {
        this.fis = new FileInputStream(file.getPath());

        this.length = (int) file.length();
        // alocare sfert din imagine
        this.quarter = new byte[length / 4];
        // alocare ultimul sfert din imagine
        this.lastQuarter = new byte[length / 4 + length % 4];

        this.buffer = buffer;

        // pornirea contorului de timp
        startCounter();
    }

    public void readQuarter() throws IOException {
    	// citire sfert din fisier 
        fis.read(quarter, 0, length / 4);
        // punere sfert din fisier in vectorul din buffer
        buffer.putBytes(quarter);
    }

    public void readLastQuarter() throws IOException {
    	// citire ultim sfert din fisier 
        fis.read(lastQuarter, 0, length / 4 + length % 4);
        // punere ultim sfert din fisier in vectorul din buffer
        buffer.putBytes(lastQuarter);
    }

    public void closeReading() throws IOException {
        fis.close();

        // oprirea contorului de timp
        stopCounter();
    }

}