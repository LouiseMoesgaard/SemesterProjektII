
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrentPulse extends Thread {

    private DataAccess dao = new DataAccess();

    private javax.swing.JLabel label;

    public CurrentPulse(javax.swing.JLabel label) {
        this.label = label;
    }

    public void run() {
        while (true) {
            try {
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500);
                ArrayList<Integer> data = dao.getPulse();
                if (data.size() > 0) {
                    int d = 0;
                    if (data.size() > 1) {
                        d = data.get(data.size() - 1);
                    } else {
                        d = data.get(0);
                    }
                    System.out.println(d);
                    label.setText(Integer.toString(d));
                }
            } catch (Exception ex) {
                Logger.getLogger(GUIPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
