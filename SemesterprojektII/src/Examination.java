
import javax.swing.JFrame;

public class Examination {
    
    public static void main (String[] args ){
        JFrame frame = new JFrame();
        frame.add(new GUIPanel());
        frame.setTitle("Semesterprojekt EKG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        
        DataAccess dao = new DataAccess();
        
    }
    
    
}
