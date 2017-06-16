import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class Sensor extends Thread {

    public String portName;
    public String result;
    public boolean openPort = false;
    SerialPort serialPort;
    
    public Sensor() {
        setPort();
        openPort();
    };
 
    
    public void setPort(){
    String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
			portName = portNames[i];
            serialPort = new SerialPort(portName);  
        }
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

    public void getData() {
        
        try {
            if (serialPort.getInputBufferBytesCount() > 0) { //Indhenter data fra arduino
                result = serialPort.readString(); //gemmer data som string
            } else {
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500); 
            }
        } catch (SerialPortException ex) {
            System.out.println("Fejl i linje 38 sensor");
        } catch (InterruptedException ex) {
            System.out.println("Wait Interrupted: " + ex);
        }
        
       String[] data = result.split("!");
       int[] intData = new int[data.length];
       for(int i = 0;i <= data.length-1;i++){
           intData[i] = Integer.parseInt(data[i]);
       }
       Examination.q.addToQ(intData);
    }
    
    public void run(){
        while (true) {
            try{
                this.getData();
            } catch(Exception e){
                
            }   
        } 
    }
}
