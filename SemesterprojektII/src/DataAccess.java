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
        Statement stmt = conn.createStatement();
        try{   
            ResultSet r = stmt.executeQuery("SELECT * FROM SensorValues");
            
            String sql = "CREATE TABLE SensorValues "+ 
                         "("+
                            "ID INT PRIMARY KEY NOT NULL,"+
                            "value INT,"+
                            "type TEXT,"+
                            "time TIMESTAMP" +
                         ")"; 
            stmt.executeUpdate(sql);
            stmt.close();
        } 
        catch (Exception e) {
          stmt.executeUpdate("CREATE TABLE SensorValues(id INT PRIMARY KEY NOT NULL, value INT, type TEXT, time TIMESTAMP)");
        } 
        
    }
}
