package implementation;

import java.io.FileWriter;
import java.io.IOException;

public class TimeManager {

    private float totalTime = 0;
    
    // varargs in parametrii
    public void manageTime(AbstractExecutionTimeCounter... counters) {

        for (int i = 0; i < counters.length; ++i) {
        	// asignare de element din vectorul transmis ca parametru
            AbstractExecutionTimeCounter counter = counters[i];
            
            // afisare timp
            counter.displayExecutionTime();
            
            // deschidere fisier .txt si scriere timp in fisier 
            try (FileWriter fileWriter = new FileWriter("timp_" + i + ".txt")) {

                fileWriter.write(counter.getDisplayExecutionTimeType() + " " + counter.getExecutionTime().toString());

                System.out.println("Execution time successfully persisted to file");
            } catch (IOException e) {
                System.out.println("An error occurred while persisting execution time to file");
                e.printStackTrace();
            }
            
            // calculare timp total program
            totalTime += counter.getExecutionTime();
        }


    }

    public void manageTotalTime() {
        System.out.println("Timpul total este:");
        System.out.println(totalTime + " secunde");
        
        // deschidere fisier .txt pentru timpul total si scriere timp total

        try (FileWriter fileWriter = new FileWriter("timp_total.txt")) {

            fileWriter.write("Timp total: " + totalTime);

            System.out.println("Total execution time successfully persisted to file");
        } catch (IOException e) {
            System.out.println("An error occurred while persisting total execution time to file");
            e.printStackTrace();
        }
    }

}
