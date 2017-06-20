import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class Sensor extends Thread {

    private static final int BSIZE =  400;
    private String portName;
    private String result;
    private boolean openPort = false;
    private SerialPort serialPort;
    private Queue q = null;
    private int[] Buffer = null;
    private int bIndex = 0;
    
    public Sensor(Queue q) {
        this.q = q;
        Buffer = new int[BSIZE];
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
            serialPort.setParams(38400, 8, 1, 0); // (baud rate, )
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
                //System.out.println("["+result+"]");
            } else {
                sleep(100); 
            }
        } catch (SerialPortException ex) {
            System.out.println("kan ikke finde sensor");
        } catch (InterruptedException ex) {
            System.out.println("Wait Interrupted: " + ex);
        }
        
       String[] data = result.split(",");
       int[] intData = new int[data.length];
       for(int i = 0;i <= data.length-1;i++){
           if (bIndex >= BSIZE) {
               Examination.q.addToQ(Buffer);
               bIndex = 0;
               Buffer = new int[BSIZE];
           }
           intData[i] = Integer.parseInt(data[i]);
           Buffer[bIndex] = Integer.parseInt(data[i]);
           bIndex++;
       }
       
       //Nedenstående skal bruges til EKG målingerne. 
       //Vi skal finde ud af hvordan vi finder en EKG værdi
       //int EKG = 
       //DataAccess dao = new DataAccess();
       //dao.setEKG(EKG);
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
