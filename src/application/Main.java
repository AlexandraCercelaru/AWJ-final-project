package application;

import implementation.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	// creare obiecte de tipuri diferite pe care le folosesc mai tarziu
        TimeManager timeManager = new TimeManager();
        ImageReader imageReader = new ImageReader();
        ImageWriter imageWriter = new ImageWriter();
        GrayScaleToBinaryConverter grayScaleToBinaryConverter = new GrayScaleToBinaryConverter();

        Scanner scanner = new Scanner(System.in);
        
        // calea pentru directorul unde sunt iamginile
        File currentDir = new File("./images");
        File currentImg;

        try {
            System.out.println("Bună ziua!");
            System.out.println("Tastați 'list' pentru a vedea imaginile disponibile.");
            System.out.println("Tastați ENTER pentru a continua.");

            if ("list".equals(scanner.nextLine())) {
                for (File file : currentDir.listFiles()) {
                    System.out.println(file.getName());
                }
            }

            while (true) {
                do {
                    System.out.println("Tastați numele imaginii cu extensia .bmp!!");
                    System.out.println("Tastați 'exit' pentru a închide aplicația.");
                    String nextLine = scanner.nextLine();

                    if ("exit".equals(nextLine)) {
                        System.out.println("O zi bună!");
                        return;
                    }

                    currentImg = new File(currentDir + "/" + nextLine);
                } while (!currentImg.exists() || !currentImg.isFile());

                Buffer buffer = new Buffer((int) currentImg.length());
                Producer producer = new Producer(currentImg, buffer, imageReader);
                Consumer consumer = new Consumer(currentImg, buffer, grayScaleToBinaryConverter, imageWriter);
                
                // pornire thread-uri
                producer.start();
                consumer.start();
                
                // asteptare thread-uri
                joinThread(producer);
                joinThread(consumer);
                
                // scriere timpi
                timeManager.manageTime(imageReader, grayScaleToBinaryConverter, imageWriter);
                timeManager.manageTotalTime();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void joinThread(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

}
