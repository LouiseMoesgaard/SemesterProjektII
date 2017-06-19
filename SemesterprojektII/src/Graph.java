import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Graph extends JPanel implements Runnable  {
    
    private Color lineColor =  new Color(44, 102, 230, 180);
    private final Color pointColor = new Color(44, 102, 230, 180);
    private final Color gridColor = new Color (200, 200, 200, 200);
    private final static Stroke GRAPH_STROKE = new BasicStroke(2f);
    private final int pointWidth= 4;
    private int numberYDivisions = 10;
    private ArrayList<Integer> data = new ArrayList<Integer>();
    private final int padding = 25;
    private final int labelPadding = 15;
    private DataAccess dao = new DataAccess();
    private String type;
    private boolean running = true; 
    
    public Graph(ArrayList<Integer> data, JPanel pan, String type){
       this.data = data;
       this.type = type;
       System.out.println(data);
       
       Dimension dim = pan.getPreferredSize();
       this.setSize(dim);
    }
    
    public Graph(ArrayList<Integer> data, JPanel pan, String type, int diversion){
       this.data = data;
       this.type = type;
       this.numberYDivisions = diversion;
       System.out.println(data);
       
       Dimension dim = pan.getPreferredSize();

    }
    
    public Graph(ArrayList<Integer> data, JPanel pan, String type, Color lineColor){
       this.data = data;
       this.type = type;
       this.lineColor = lineColor;
       System.out.println(data);
       
       Dimension dim = pan.getPreferredSize();
       this.setSize(dim);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int fontSize = getHeight()/14;
        g2.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        super.paintComponent(g);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (data.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMax() - getMin());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMax() - data.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

         /*tegner baggrund*/
        g2.setColor(new Color(238,238,238,238));
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

       /*vi tilføjer gitter for y-aksen*/
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 =  getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (data.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMin() + (getMax() - getMin()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 3) - 1);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        /*og for x-aksen*/
        for (int i = 0; i < data.size(); i++) {
            if (data.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (data.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((data.size() / 20.0)) + 1)) == 0) {
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

        /*så opretter vi x- og y-aksen*/
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
        int max = 0;
        
        for(int i = 0; i < this.data.size()-1; i++){
            if(this.data.get(i) > max){
                
                max = this.data.get(i);
            }
        }
        
        return max+25;
    }
    
    public void run(){
        while (running){
            try {
                if (this.type == "pulse"){
                    this.data = dao.getPulse();
                } else{
                    this.data = dao.getEKG();
                }
                this.repaint();
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ex) {
    
                Logger.getLogger(Graph.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public void pause(){
        this.running = false;
    }
    
    public void resume(){
        this.running = true;
    }
    
    public boolean getState(){
        return this.running;
    }
   


}