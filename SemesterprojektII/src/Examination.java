

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Examination {

    static Queue q = new Queue();
    static final DataAccess dao = new DataAccess(q);
    static Sensor sensor = new Sensor(q);
    //static PulseCalculator pCalc = new PulseCalculator();
    static JFrame frame = new JFrame();
    static GUIPanel gui = new GUIPanel(dao);

    public static void main(String[] args) {

        sensor.start();
        dao.start();
        //pCalc.start();
        frame.add(gui);

        frame.setTitle("Semesterprojekt EKG");
        //til når ny frame
        frame.setSize(950, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        //frame.pack();
        frame.setVisible(true);
        frame.repaint();
        while (true) {

            try {
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Examination.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
