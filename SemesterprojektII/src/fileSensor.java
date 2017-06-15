import java.io.*;

public class fileSensor extends Thread {
    String fileName = "ecg.csv";
    DataAccess dao = new DataAccess();
        
     public void run(){
        File file = new File(fileName);
        try{
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                Double data = Double.parseDouble(sCurrentLine);
                dao.setEKG(data.intValue());
            }
        } catch (IOException e){
            e.printStackTrace();
        } 
     }  
}
