
import java.util.ArrayList;


public class PulseCalculator extends Thread {
    
    private ArrayList<Integer> sortData(int[] data){
        ArrayList<Integer> valueArray = new ArrayList<Integer>();;
        for (int i = 0; i < data.length - 1; i++) {
            int value = 0;
            try {
                value = data[i];
                if (value > 5) {
                    value = 0;
                }
            } catch (NumberFormatException ex) {
                value = 0;
            }
            if (value == 0) {
                if (i > 0) {
                    value = data[i - 1];
                } else {
                    value = 0;
                }
            }

                valueArray.add(value);
        }
        return valueArray;
    }

    public void run(){
    
        while (true){
            System.out.println("calculating pulse");
            try {
                int[] data = Examination.q.getFromQ();
                if(data.length < 1){
                    java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500);
                    continue;
                }
                ArrayList<Integer> valueArray = sortData(data);
                boolean found = false;
                boolean foundBefore;
                int n = 0;
                for (int t = 0; t < valueArray.size() - 1; t++) {
                    int pulseValue = valueArray.get(t);
                    foundBefore = found;
                    if (pulseValue >= 3) {
                        found = true;
                    } else {
                        found = false;
                    }

                    if (!foundBefore && found) {
                        n++;
                    }
                }
                System.out.println(n+" "+valueArray.size());
                int pulse = (n / ((valueArray.size()) / 200)) * 60;
                Examination.dao.setPulse(pulse);
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500);
            } catch (Exception e){
                System.out.println(e);
                try{
                    java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500);
                } catch(Exception ex){}
                continue;  
            }
        }
    }
    
}
