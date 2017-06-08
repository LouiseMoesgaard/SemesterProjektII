import java.sql.*;
import java.util.*;

public class DataAccess {
    private Connection conn = null;
    
    public DataAccess(){
        makeConnection(); // bruges til at lave forbindelse, når objektet initialiseres. 
        createTable(); //opretter tabellen hvis den ikke eksistere i forvejen
    }
    
    public void makeConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         }
        //System.out.println("Opened database successfully"); - whitebox test?
    }
    
    public void createTable(){
        try{   
            Statement stmt = conn.createStatement();
            ResultSet r = stmt.executeQuery("SELECT * FROM SensorValues");        
        } 
        catch (Exception e) {
          stmt.executeUpdate("CREATE TABLE SensorValues(id DOUBLE PRIMARY KEY NOT NULL, value DOUBLE, type TEXT, time TIMESTAMP)");
        } 
    }
   
    public void SetEKG(){    
    }
    
    public void SetPulse(){   
    }
    
    public ArrayList<Double> getEKG(){
    }
    
    public ArrayList<Double> getPulse(String pulse){
        
    }
}
