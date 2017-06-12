
import javax.swing.JFrame;

public class Examination {
    
    public static void main (String[] args ){
        JFrame frame = new JFrame();
       // frame.add(new GUIPanel());
       frame.add(new Graph());
        frame.setTitle("Semesterprojekt EKG");
        //til når ny frame
        frame.setSize(700,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        //frame.pack();
        frame.setVisible(true);
        frame.repaint();
        
        //DataAccess dao = new DataAccess();
        
    }
   
    
}
