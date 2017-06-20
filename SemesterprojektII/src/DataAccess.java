
import java.sql.*;
import java.util.*;

public class DataAccess extends Thread {

    private Connection conn = null;
    private Statement stmt;
    private Queue q = null;

    public DataAccess(Queue q) {
        this.q = q;
        makeConnection(); // Bruges til at lave forbindelse, når objektet initialiseres. 
        createTable(); //Opretter tabellen hvis den ikke eksistere i forvejen
    }

    public void makeConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        //System.out.println("Opened database successfully"); - whitebox test?
    }

    public void createTable() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
        }

        try {
            stmt.executeUpdate("DROP TABLE SensorValues");

        } catch (SQLException ex) {
            System.out.println("DROP TABLE: " + ex);
        }
        try {
            stmt.executeUpdate("CREATE TABLE SensorValues(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, value INT, type INT)");
        } catch (SQLException ex) {
            System.out.println("CreateTable: " + ex);
        }
    }

    public void setEKG(int[] value) {

        try {
            //PreparedStatement pstmt = new PreparedStatement();
            for (int i = 0; i < value.length; i++) {
                stmt.executeUpdate("INSERT INTO SensorValues(type,value) VALUES ('EKG', " + value[i] + ")");
            }
        } catch (Exception e) {
            System.out.println("setEKG: " + e);
        }
    }

    public void setPulse(int value) {
        try {
            stmt.executeUpdate("INSERT INTO SensorValues(type,value) VALUES ('Pulse', " + value + ")");
        } catch (Exception e) {
            System.out.println("setPulse: " + e);
        }
    }

    public ArrayList<Integer> getEKG() {
        ArrayList<Integer> data = new ArrayList<Integer>();

        try {
            ResultSet r = stmt.executeQuery("SELECT * FROM SensorValues WHERE type = 0 ORDER BY id DESC LIMIT 400");

            while (r.next()) {
                data.add(0, r.getInt("value"));
            }

        } catch (Exception e) {
            System.out.println("getEKG: " + e);
        }
        if (data.size() > 0) {
            System.out.println("getEKG: " + data.size() + " " + data.get(0));
        }
        return data;
    }

    public ArrayList<Integer> getPulse() {
        ArrayList<Integer> data = new ArrayList<Integer>();

        try {
            ResultSet r = stmt.executeQuery("SELECT * FROM SensorValues WHERE type = 'pulse' ORDER BY id DESC LIMIT 20");

            while (r.next()) {
                data.add(0, r.getInt("value"));
            }

        } catch (Exception e) {
            //Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println(data);
        return data;
    }

    public void run() {
        for (;;) {

            int[] data = q.getFromQ();
            String s = "INSERT INTO SensorValues(type, value) VALUES";
            for (int i = 0; i < data.length; i++) {
                if (i > 0) {
                    s += ",";
                }
                s += "(0," + data[i] + ")";
            }
            try {
                stmt.executeUpdate(s);
            } catch (SQLException ex) {

            }
        }
    }

}
