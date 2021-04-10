import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ThreadIamAlive extends Thread {
	 DataInputStream in;
     DataOutputStream outToServer,outToBackup;
     
     Socket clientSocket;
     Socket backUpSocket;
     public ThreadIamAlive ( Socket aBackUpSocket) {
         try {
     	
     	outToBackup= new DataOutputStream(aBackUpSocket.getOutputStream());//back up buffer connected on a socket 
          } catch(IOException e) {System.out.println(" Connection:"+e.getMessage());}
      	this.start();
     }
    	
    	 public void run(){
             try {		
            	 while(true) {
        		 outToBackup.writeUTF("1");//1 means I am alive
        		 Thread.sleep(1000);}
            	 
             }catch (EOFException e){System.out.println(" Connect EOF:"+e.getMessage());
             } catch(IOException e) {System.out.println("readline:"+e.getMessage());
             } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
    	
    	 }
	}


