package implementation;

public class WritingTimeCounter extends AbstractExecutionTimeCounter {

    @Override
    public String getDisplayExecutionTimeType() {
    	// returnare tip de executie sub forma de string in fisierul .txt
        return "Timp scriere:";
    }

    @Override
    public void displayExecutionTime() {
    	// printare in consola timp de scriere
        System.out.println("Timpul de scriere este:");
        System.out.println(executionTime + " secunde");
    }

}
