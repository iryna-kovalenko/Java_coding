import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ThreadForBackUp extends Thread{
	DataInputStream in;
	Socket serverSocket;
	
	public ThreadForBackUp(Socket serverSocket)  {
		try {
			
			in = new DataInputStream( serverSocket.getInputStream());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.start();
	}
	
	 public void run(){
		 String mString;
			 try {
			 while(true) {
					  if(in.available()>0) {
						 mString=in.readUTF();
						 
					 if(mString.charAt(0)=='1') {
						System.out.println(" Server is alive ");
					}}
				 
					
				}
			 
			 
			 
			 }
			 catch (IOException e) {
				 System.out.println(" Exception in Thread back-up ");
				 
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		 }
			 
       	     		 
        	 

	 }

