import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Graph extends JPanel  {
  
    private final int width = 800;
    private final int heigth = 400;
    private final Color lineColor =  new Color(44, 102, 230, 180);
    private final Color pointColor = new Color (100, 100, 100, 180);
    private final Color gridColor = new Color (200, 200, 200, 200);
    private final static Stroke GRAPH_STROKE = new BasicStroke(2f);
    private final int pointWidth= 4;
    private final int numberYDivisions = 9;
    private ArrayList<Integer> EKGData, pulsdata = new ArrayList<Integer>();
    private final int padding = 25;
    private final int labelPadding = 25;
    GUIPanel pan = new GUIPanel();
    
    int ekgwidth,pulswidth,ekgheight,pulsheight;
    
//    DataAccess da = new DataAccess();
    ArrayList data ;
    
    public Graph(){
       //pulsdata = da.getPulse();
      // EKGData = da.getEKG();
       
       Dimension pulsdim = pan.jPanel3.getSize();
       Dimension ekgdim = pan.jPanel2.getSize();
       
       ekgwidth = ekgdim.width;
       pulswidth = pulsdim.width;
       ekgheight = ekgdim.height;
       pulsheight = pulsdim.height;
     simulerData();
    }
    
    
    
    
    
@Override
protected void paintComponent(Graphics paint) {
    super.paintComponent(paint);
    Graphics2D paint2D = (Graphics2D) paint;
    paint2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    /*vi henter data ind hver gang vi repainter*/
   
//skaler x aksen

paint.drawLine(0, 0, 600, 234);



//skaler y aksen

    }

    private void simulerData() {
      int[] simuler = new int[super.getWidth()/2]; 
      //lav et array med halvt så mange pladser som der er pixels.
      for (int i =0;i<simuler.length; i++)
      {
          //for hvert element i simuler, skal det være et tilfældig tal. 
          
                  }
}
}




