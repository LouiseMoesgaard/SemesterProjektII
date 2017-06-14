
import javax.swing.JFrame;

public class Examination {
    
    public static void main (String[] args ){
        DataAccess dao = new DataAccess();
        
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
        
        //DataAccess dao = new DataAccess();
        
    }
   
    
}
