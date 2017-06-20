
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrentPulse extends Thread {

    private javax.swing.JLabel label;

    public CurrentPulse(javax.swing.JLabel label) {
        this.label = label;
    }

    public void run() {
        while (true) {
            try {
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500);
            } catch (Exception ex) {
                Logger.getLogger(GUIPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
