
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Examination {
    static Queue q = new Queue();
    static final DataAccess dao = new DataAccess(q);
    
    
    public static void main (String[] args ){
        Sensor sensor = new Sensor(q);
        sensor.start();
        dao.start();
        //PulseCalculator pCalc = new PulseCalculator();
        //pCalc.start();
        JFrame frame = new JFrame();
        GUIPanel gui = new GUIPanel(dao);

       
        frame.add(gui);
        
        frame.setTitle("Semesterprojekt EKG");
        //til når ny frame
        frame.setSize(900,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        //frame.pack();
        frame.setVisible(true);
        frame.repaint();
        while(true){
            
            try {
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Examination.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //DataAccess dao = new DataAccess();
        
    }
   
    
}
