package implementation;

public abstract class AbstractExecutionTimeCounter implements ExecutionTimeCounter {

    protected Float executionTime;
    private Long executionStartTime;

    public void startCounter() {
    	// timp curent
        executionStartTime = System.currentTimeMillis();
    }

    public void stopCounter() {
    	// durata timpului de executie (citire/scriere/procesare)
        executionTime = (System.currentTimeMillis() - executionStartTime) / 1000.0f;
    }

    public void resetCounter() {
    	// resetare variabile pentru timp
        executionStartTime = null;
        executionTime = null;
    }

    public Float getExecutionTime() {
        return executionTime;
    }

    public abstract String getDisplayExecutionTimeType();

    public abstract void displayExecutionTime();

}
