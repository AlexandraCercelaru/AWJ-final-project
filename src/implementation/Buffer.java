package implementation;

public class Buffer {
	
	// vector pentru salvarea datelor din poza
    private final byte[] data;
    private int index = 0;
    // variabila pentru sincronizarea intre cele 2 thread-uri
    private boolean available = false;

    public Buffer(int size) {
        data = new byte[size];
    }

    public int getIndex() {
        return index;
    }

    public synchronized void putBytes(byte[] bytes) {
    	// functie care pune cate un sfert din poza in vectorul data 
    	// pentru a fi folosit mai tarziu
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        available = true;
        // se notifica thread-urile
        notifyAll();

        for (int i = 0; i < bytes.length; ++i, ++index) {
            data[index] = bytes[i];
        }
    }

    
    public synchronized byte[] getBytes() {
    	// functie care returneaza sfertul respectiv din poza
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        available = false;
        // se notifica thread-urile
        notifyAll();

        return data;
    }

}