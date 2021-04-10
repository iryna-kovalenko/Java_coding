import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

class Connection extends Thread {
        DataInputStream in;
        DataOutputStream outToServer,outToBackup;
        int sum=0;
        Socket clientSocket;
        Socket backUpSocket;
        int something;
        public Connection (Socket aClientSocket, Socket aBackUpSocket) {
            try {
        	clientSocket = aClientSocket;
            backUpSocket=aBackUpSocket;
        	in = new DataInputStream( clientSocket.getInputStream());
        	outToServer =new DataOutputStream( clientSocket.getOutputStream());//client buffer connected to a socket 
        	outToBackup= new DataOutputStream(backUpSocket.getOutputStream());//back up buffer connected on a socket 
        	something=1;
        	this.start();
             } catch(IOException e) {System.out.println(" Connection:"+e.getMessage());}
        }
            
            
            public Connection (Socket aClientSocket,int sum) {
                try {
                clientSocket=aClientSocket;
            	in = new DataInputStream( clientSocket.getInputStream());//buffer for client receving
            	outToServer =new DataOutputStream( clientSocket.getOutputStream());//client buffer connected to a socket send to client
            	something=2;
            	this.sum=sum;
            	this.start();
                 } catch(IOException e) {System.out.println(" Connection:"+e.getMessage());}
        }

        public void run(){
        	
        
             try {	
            	
            	 if(something==1) {//sending to client and backup sum
            	 sum=0;
            	 for (int i=1;i<100;i++) {      		 
            	 sum+=in.readInt();
            	 outToServer.writeInt(sum);            	 
            	 String addressString= clientSocket.getInetAddress().toString();
        		 outToBackup.writeUTF(addressString+"#"+clientSocket.getPort()+"#"+sum+"#"+i );}
            	 }
            	 else if(something==2) {//when server fails, sending to clien only
            	 
            		 //sum=0;//sum must = the sum currently held by the client 
            		 
                	 for (int i=1;i<100;i++) {  // i must = the current i of the client   
                		 
                	 sum+=in.readInt();
                	 outToServer.writeInt(sum);            	 
            	 }
        		 //outToBackup.writeUTF("1");
            	 }
        	
             }catch (EOFException e){System.out.println(" Connect EOF:"+e.getMessage());
             } catch(IOException e) {System.out.println("readline:"+e.getMessage());
             } finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
        
             }
             }