package implementation;

import java.io.File;
import java.io.IOException;

public class Producer extends Thread {

    private final ImageReader imageReader;

    public Producer(File file, Buffer buffer, ImageReader imageReader) throws IOException {
        this.imageReader = imageReader;
        // deschidere imagine
        imageReader.openForReading(file, buffer);
    }

    @Override
    public synchronized void start() {
        super.start();
        System.out.println("Producer started execution");
    }

    @Override
    public void run() {
        try {
        	// citirea patrarului din informatie
            System.out.println("Se produce sfertul 1 din imagine");
            imageReader.readQuarter();
            Thread.sleep(1000);

            System.out.println("Se produce sfertul 2 din imagine");
            imageReader.readQuarter();
            Thread.sleep(1000);

            System.out.println("Se produce sfertul 3 din imagine");
            imageReader.readQuarter();
            Thread.sleep(1000);

            System.out.println("Se produce sfertul 4 din imagine");
            imageReader.readLastQuarter();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            try {
                imageReader.closeReading();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Producer ended execution");
        }
    }

}
