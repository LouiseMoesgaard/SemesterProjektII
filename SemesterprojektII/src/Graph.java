import java.awt.*;
import java.io.File; // nyt
import java.io.FileNotFoundException; // nyt
import java.io.FileReader; // nyt
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Graph extends JPanel  {
  
    ArrayList<Double> inddata; // nyt
    
    private final Color lineColor =  new Color(44, 102, 230, 180);
    private final Color pointColor = new Color (100, 100, 100, 180);
    private final Color gridColor = new Color (200, 200, 200, 200);
    private final static Stroke GRAPH_STROKE = new BasicStroke(2f);
    private final int pointWidth= 4;
    private static final int BORDER_GAP = 30;
    private final int numberYDivisions = 9;
    private ArrayList<Integer> data = new ArrayList<Integer>();
    private final int padding = 25;
    private final int labelPadding = 25;
    private int width, height;
  
      
    public Graph(ArrayList<Integer> data, JPanel pan){
       this.data = data;
       
       Dimension dim = pan.getSize();
       
     
       this.width = dim.width;
       this.height = dim.height;

     simulerData();
    }

    Graph() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    public void GraphfromFile(){ // nyt
        
        File filnavn = new File("ecg.csv");
        inddata = new ArrayList<>();
        
       try{
           FileReader ind = new FileReader(filnavn);
           Scanner scanner = new Scanner(ind);
           
       while(scanner.hasNext()){
           try{
           inddata.add(Double.parseDouble(scanner.nextLine()));
           } catch(NumberFormatException e){ continue; }
       }
       } catch(FileNotFoundException e){e.printStackTrace();}
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (this.data.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMax() - getMin());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < this.data.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMax() - data.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

         /*Tegner hvid baggrund*/
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

       /*Tilføjer gitter for y-aksen*/
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (this.data.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMin() + (getMax() - getMin()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        /*Gitter for x-aksen*/
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (this.data.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((this.data.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        /*Opretter vi x- og y-aksen*/
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        } 
    }
    
    private int getMin() {
        int min = 0;
        return min;
    }

    private int getMax() {
        int max = 45;
        return max;
    }

    private void simulerData() {
        int[] simuler = new int[super.getWidth()/2]; 
        //Lav et array med halvt så mange pladser som der er pixels.
        for (int i =0;i<simuler.length; i++){
            //For hvert element i simulere, skal det være et tilfældig tal.   
        }
    }
}




