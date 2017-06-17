
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Examination {
    public static Queue q = new Queue();
    public static DataAccess dao = new DataAccess();
    public static void main (String[] args ){
        
        
        Sensor sensor = new Sensor();
        sensor.start();
        PulseCalculator pCalc = new PulseCalculator();
        pCalc.start();
        System.out.println(dao.getPulse());
        System.out.println(dao.getEKG());
        JFrame frame = new JFrame();
        GUIPanel gui = new GUIPanel();
        Graph pulse = new Graph(dao.getPulse(), gui.PulsePanel);
        Graph ekg = new Graph(dao.getEKG(), gui.EKGPanel);
        gui.PulsePanel.add(pulse);
        gui.EKGPanel.add(ekg);
       
        frame.add(gui);
        
        frame.setTitle("Semesterprojekt EKG");
        //til når ny frame
        frame.setSize(850,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        //frame.pack();
        frame.setVisible(true);
        frame.repaint();
        while(true){
            
            try {
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("Queue is :"+q.size());
            } catch (InterruptedException ex) {
                Logger.getLogger(Examination.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //DataAccess dao = new DataAccess();
        
    }
   
    
}
