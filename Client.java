import java.net.*; 
import java.io.*;

public class Client {
	public static void main (String args[]) throws InterruptedException { 
	   // arguments supply message and hostname
		Socket s = null;
		ServerSocket bb=null;
		int myPort=0;
		int j=0;
		try{
			int serverPort=55555;
			s=new Socket(args[0],serverPort);
			myPort=s.getLocalPort();//to restablish the connection with the servwr 
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out= new DataOutputStream(s.getOutputStream());
			for(int i=1;i<100;i++) {
				out.writeInt(i);//send 
				int data=in.readInt();//receive
				System.out.println(i + " Received: "+ data) ; 
				Thread.sleep(2000);
				j=i;
			}
			
      	}catch (UnknownHostException e) {System.out.println("Socket:"+e.getMessage());
      	}catch (EOFException e){System.out.println("Client EOF:"+e.getMessage());
      	}catch (IOException e){System.out.println("readline:"+e.getMessage());//
      	
      	
      	int backupPort=50000;
      	if(s!=null) 
			try {s.close(); 
		  	}catch (IOException ee) {System.out.println ("close:" + ee.getMessage());}
		try {//establish connection with backup
			bb=new ServerSocket(myPort);
			Socket clientSocket = bb.accept();
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());// prepare buffer to recive data from backup
			DataOutputStream out= new DataOutputStream(clientSocket.getOutputStream());//prepare buffer to send data to backup
			for(int i=j+1;i<101;i++) {//loop to loop thingspt
				out.writeInt(i);//send current i to connection which is backup
				int data=in.readInt();// prepare to read an int from the backup
				System.out.println(i + " Received: "+ data) ; 
				Thread.sleep(200);
				
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      	
      
      	
      	}finally {
		}
	}
}
                       