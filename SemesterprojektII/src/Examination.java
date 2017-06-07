
import javax.swing.JFrame;

public class Examination {
    
    public static void main (String[] args ){
        JFrame ramme = new JFrame();
        ramme.add(new GUIPanel());
        ramme.setTitle("Semesterprojekt EKG");
        ramme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramme.setResizable(false);
        ramme.pack();
        ramme.setVisible(true);
    }
    
    
}
