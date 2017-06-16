import java.util.*;

public class Queue {

    private ArrayList<int[]> queueArray = new ArrayList<>();

    public synchronized void addToQ(int[] queueData) {

        queueArray.add(queueData);
        if (queueArray.size() >= 1) {
            notify();
        }

    }

    public synchronized int[] getFromQ() {
        int[] data = new int[0];
        if (queueArray.size() < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
            data = queueArray.get(0);
            queueArray.remove(0);

           
        }
         return data;
    }
    
    public int size(){
        return queueArray.size();
    }
    
    public int[] get(int index){
        return queueArray.get(index);
    }
}

