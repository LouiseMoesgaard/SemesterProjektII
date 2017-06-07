import java.sql.*;

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
            String sql = "CREATE TABLE SensorValues "+ //hjælp til if not exist
                         "("+
                            "ID INT PRIMARY KEY NOT NULL,"+
                            "value INT,"+
                            "type TEXT"+
                         ")"; 
            stmt.execute(sql);
            stmt.close();
        } 
        catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } 
        
    }
}
