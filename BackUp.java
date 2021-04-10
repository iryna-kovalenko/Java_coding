import java.net.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.Map.Entry;

import javax.jws.Oneway;

import org.omg.IOP.Codec;

import java.io.*;
import java.io.ObjectInputStream.GetField;
public class BackUp {
public static void main (String args[]) {
	Hashtable clients = new Hashtable();
    try{
    	System.out.println(" Back up is waiting ");
	int backUpPort = 50000; // the server back up port
	ServerSocket listenSocket = new ServerSocket(backUpPort);
	Socket serveSocket=listenSocket.accept();
	
	DataInputStream inFromServer= new DataInputStream(serveSocket.getInputStream());
	int port1=50551;
	ServerSocket listenSocket1 = new ServerSocket(port1);
	Socket serveSocket1=listenSocket1.accept();
	
	
	ThreadForBackUp pleaseWorkBackUp=new ThreadForBackUp(serveSocket1);//receiving alive msg
	while(true) {
		String msg=inFromServer.readUTF();
		
			
			String splitReceivedData []=msg.split("#");
			String ip=splitReceivedData[0];
			String ipAndPortOfClient=ip.substring(1,ip.length())+"/"+splitReceivedData[1];//ip and port of a clinet
			clients.put(ipAndPortOfClient,splitReceivedData[2]);//sum 
			System.out.println(" Table " );
			Enumeration keys = clients.keys(); 
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				System.out.println(key +" "+  clients.get(key));
				
			}
			System.out.println("-----------------------------------------------------------------------------------");
		
		}	
	
    } catch(IOException e) {
    	  Enumeration keys = clients.keys(); 
    	  while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				//System.out.println(key +" "+  clients.get(key));//key- IP & Port, we need to break it down
				String splitIpAndPort[]=key.split("/"); 
				System.out.println(splitIpAndPort[0] + " " + splitIpAndPort[1]);
				try {
					Socket socketalive= new Socket(splitIpAndPort[0],Integer.parseInt(splitIpAndPort[1]));
					Connection Takeover = new Connection(socketalive,Integer.parseInt(clients.get(key).toString()));//thread.. u need to extract sun and i, constractor we chnaged it
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
    } 
    
      
}}


