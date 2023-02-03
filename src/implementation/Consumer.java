package implementation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class Consumer extends Thread {

    private final File file;
    private final Buffer buffer;
    private final GrayScaleToBinaryConverter grayScaleToBinaryConverter;
    private final ImageWriter imageWriter;


    public Consumer(File file,
                    Buffer buffer,
                    GrayScaleToBinaryConverter grayScaleToBinaryConverter,
                    ImageWriter imageWriter) {
        this.file = file;
        this.buffer = buffer;
        this.grayScaleToBinaryConverter = grayScaleToBinaryConverter;
        this.imageWriter = imageWriter;
    }

    @Override
    public synchronized void start() {
        super.start();
        System.out.println("Consumer started execution");
    }

    @Override
    public void run() {
        byte[] data = new byte[(int) file.length()];
        int quarterNumber = 1;

        while (buffer.getIndex() < file.length() - 1) {
        	
        	// bitii din patrarul citit se pun in vectorul data
            data = buffer.getBytes();
            System.out.println("Se consuma sfertul " + quarterNumber++ + " din imagine");
        }

        try {
            BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(data));
            // se proceseaza imaginea si se pune intr-o variabila
            BufferedImage outputImage = grayScaleToBinaryConverter.process(inputImage);
            // variabila cu poza finala se scrie in fisier
            imageWriter.writeImage(outputImage, "final_" + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Consumer ended execution");
    }

}