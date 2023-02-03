package implementation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayScaleToBinaryConverter extends ProcessingTimeCounter {

    public GrayScaleToBinaryConverter() {
        // resetarea contorului de timp
        resetCounter();
    }

    public static int colorToRGB(int red, int green, int blue) {
        int newPixel = 0;
        
        // shiftare pe biti pentru a parcurge toate 
        // componentele din RGB

        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;
    }

    public BufferedImage process(BufferedImage inputImage) {
    	// setare threshold la jumatate intre 0 si 255
        int threshold = 125;
        int pixelComponentRed;
        int pixelComponentNew;

        // pornirea contorului de timp
        startCounter();

        BufferedImage outputImage = new BufferedImage(
                inputImage.getWidth(),
                inputImage.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY
        );

        for (int i = 0; i < inputImage.getWidth(); i++) {
            for (int j = 0; j < inputImage.getHeight(); j++) {

                // citirea componentei RED din pixelul curent
                pixelComponentRed = new Color(inputImage.getRGB(i, j)).getRed();
                
                // setarea unei noi componente in functie de componenta RED
                if (pixelComponentRed > threshold) {
                    pixelComponentNew = 255;
                } else {
                    pixelComponentNew = 0;
                }
                
                // asigare noua valoare pentru toti bitii din pixel
                pixelComponentNew = colorToRGB(
                        pixelComponentNew,
                        pixelComponentNew,
                        pixelComponentNew
                );
                // setare nou pixel in imagine pe randul i si coloana j
                outputImage.setRGB(i, j, pixelComponentNew);
            }
        }

        // oprirea contorului de timp
        stopCounter();

        return outputImage;
    }

}

