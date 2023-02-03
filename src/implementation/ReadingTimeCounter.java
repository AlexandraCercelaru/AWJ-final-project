
package implementation;

public class ReadingTimeCounter extends AbstractExecutionTimeCounter {

    @Override
    public String getDisplayExecutionTimeType() {
    	// returnare tip de executie sub forma de string in fisierul .txt
        return "Timp citire:";
    }

    @Override
    public void displayExecutionTime() {
    	// printare in consola timp de citire
        System.out.println("Timpul de citire este:");
        System.out.println(executionTime + " secunde");
    }

}
