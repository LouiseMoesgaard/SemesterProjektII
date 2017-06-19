
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CurrentPulse extends Thread {
    
    private Queue newq = new Queue();
    private DataAccess dao = new DataAccess(newq);
    
    private javax.swing.JLabel label;
    
    public CurrentPulse(javax.swing.JLabel label){
        this.label = label;    
    }
    
    public void run(){
        while (true){
               try {
                   java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500);
                   ArrayList<Integer> data = dao.getPulse();
                   int d = data.get(data.size() - 1);
                   label.setText(Integer.toString(d));
               } catch (InterruptedException ex) {
                   Logger.getLogger(GUIPanel.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
    }
}
