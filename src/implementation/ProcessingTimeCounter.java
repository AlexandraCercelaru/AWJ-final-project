package implementation;

public class ProcessingTimeCounter extends AbstractExecutionTimeCounter {

    @Override
    public String getDisplayExecutionTimeType() {
    	// returnare tip de executie sub forma de string in fisierul .txt
        return "Timp procesare:";
    }

    @Override
    public void displayExecutionTime() {
    	// printare in consola timp de procesare
        System.out.println("Timpul de procesare este:");
        System.out.println(executionTime + " secunde");
    }

}
