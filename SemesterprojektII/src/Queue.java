import java.util.*;

public class Queue {

    private ArrayList<int[]> queueArray = new ArrayList<>();

    public synchronized void addToQ(int[] queueData) {

        queueArray.add(queueData);
        //System.out.println("Kø: " + queueArray.size() + "[" + queueData.length + "]");
        if (queueArray.size() >= 1) {
            notify();
        }

    }

    public synchronized int[] getFromQ() {
        int[] data = new int[0];
        if (queueArray.size() < 1) {
            data = queueArray.get(0);
            queueArray.remove(0);
        } else{
            try {
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(1000);
                data = queueArray.get(0);
                queueArray.remove(0);
            } catch (InterruptedException e) {
            }
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

