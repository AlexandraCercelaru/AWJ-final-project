package implementation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageWriter extends WritingTimeCounter {

    public ImageWriter() {
        // resetarea contorului de timp
        resetCounter();
    }

    public void writeImage(BufferedImage outputImage, String filename) {

        // pornirea contorului de timp
        startCounter();

        // calea către imaginea introdusă de la tastatură
        filename = "./images/" + filename;

        try (FileOutputStream stream = new FileOutputStream(filename)) {

            // scrierea imaginii în fișier
            ImageIO.write(outputImage, "bmp", stream);

        } catch (IOException e) {
            System.out.println("A apărut o eroare în timpul scrierii fișierului: " + filename);
            return;
        }

        // oprirea contorului de timp
        stopCounter();
    }

}
