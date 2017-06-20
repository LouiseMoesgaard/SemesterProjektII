import java.util.ArrayList;
import java.util.stream.IntStream;

public class PulseCalculator extends Thread {
    
    private DataAccess dao;
    private ArrayList<Integer> dataArrayList = new ArrayList<Integer>();
    double gennemsnit;
     private int type;
     int sum;
     boolean overMax = false;
     int pulsMinut;
    
     public PulseCalculator(DataAccess dao, int type){
       this.dao = dao;
       this.type = type;
     }
     
     public PulseCalculator(ArrayList<Integer> dataArrayList, int type){
       this.dataArrayList = dataArrayList;
       this.type = type;
     }
      public static void main (String[] args ){
      }
    
    public int getSum() {
   
        gennemsnit = 0.0; //Resetter gennemsnittet ved hvert gennemløb
        
      if (type == 0) {
            dataArrayList = dao.getEKG();
        }

         for (int i = 1; i < dataArrayList.size(); i++){
             sum += dataArrayList.get(i);
         }
         return sum;
    }
    
    public int calcPulse(){
        int count = 0;
        gennemsnit = 0.0;
        gennemsnit = sum / dataArrayList.size() * 1.1;
        
        for (int i = 0; i < dataArrayList.size(); i++) {        /* 3 */
                 //Hvis en måling er højere end gennemsnittet + 10%, tælles et slag
                if (dataArrayList.get(i) > gennemsnit && !overMax) {    /* 4 */
                    overMax = true;
                    count = count + 1;

                }
                if (dataArrayList.get(i) < gennemsnit && overMax) {     /* 5 */
                     // Når en måling kommer under gennemsnittet + 10%, sættes overMax til false
                    overMax = false;
                }
    pulsMinut = count * 60; //Beregning af pulsen i minuttet ud for antal count
            System.out.println("Her er pulsen:" + pulsMinut);
            }
           return pulsMinut;
        }
    

    public void run(){
    getSum();
    calcPulse();
        
    
}
}