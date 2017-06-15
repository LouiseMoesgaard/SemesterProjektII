
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class Sensor {

    public String name;
    public String result;
    public boolean openPort = false;
    SerialPort serialPort;
    public Sensor() {
    
    };
    
    public static void main (String args[]){
        Sensor sensor = new Sensor();
        sensor.findPort();
    }
    
    public String findPort(){
    String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
			name = portNames[i];
            System.out.println(name);
            serialPort = new SerialPort(name);
            System.out.println(serialPort);
             
            
        }
        return name;
    }
    public boolean openPort() {

        try {

            serialPort.openPort();
            serialPort.setParams(38400, 8, 1, 0); //sætter parametre
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            serialPort.setDTR(true);
            openPort = true;

        } catch (SerialPortException ex) {
            System.out.println("Der er ikke tilsluttet en sensor.");
            openPort = false;
        }

        return openPort;
    }

    public String getData() {
        
        try {
            if (serialPort.getInputBufferBytesCount() > 0) { //Indhenter data fra arduino
                result = serialPort.readString(); //gemmer data som string
                System.out.println(result);
            } else {
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(1000); 
            }
        } catch (SerialPortException ex) {
            System.out.println("Fejl i linje 38 sensor");
        } catch (InterruptedException ex) {
            System.out.println("Wait Interrupted: " + ex);
        }
        return result;
    }
}