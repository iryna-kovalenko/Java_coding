import java.net.*;
import java.io.*;
public class Server {
public static void main (String args[]) {
      try{
    int serverBackUpPort = 50000; // the server back up port
    Socket socketForSocketBaclup= new Socket(args[0],serverBackUpPort);//establish the connection
 	DataOutputStream outForBackUp= new DataOutputStream(socketForSocketBaclup.getOutputStream());	  
    	  
	int serverPort = 55555; // the server port
	ServerSocket listenSocket = new ServerSocket(serverPort);
	
	int alivemesaageport=50551;
    Socket socketalive= new Socket(args[0],alivemesaageport);//establish the connection

	System.out.print(" Server is waiting ...");
	ThreadIamAlive alive= new ThreadIamAlive(socketalive);//calling alive thread

	while(true) {
		Socket clientSocket = listenSocket.accept();
		Connection c = new Connection(clientSocket,socketForSocketBaclup);//thread
	}
      } catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
}}

