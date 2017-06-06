
package semesterprojekt_2;

import javax.swing.JFrame;

public class Semesterprojekt_2 {

    public static void main(String[] args) throws Exception {
        
        JFrame ramme = new JFrame();
        ramme.add(new EKG());
        ramme.setTitle("EKG for patienten");
        ramme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramme.setResizable(false);
        ramme.pack();
        ramme.setVisible(true);
    }
    
}
