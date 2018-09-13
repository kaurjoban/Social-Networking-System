package chatmessenger;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
class ServerMain
{
 ServerMain()
 {
  try
  { ServerSocket sersock=new ServerSocket(9900);
    while(true)
    {
       Socket sock=sersock.accept();
       clientHandler obj=new clientHandler(sock);
    }    
          
  }
  catch(Exception ex)
  {
      ex.printStackTrace();
  }    
 }   
     ///inner class///
 class clientHandler implements Runnable
 {Socket sock;
 DataInputStream dis;
 DataOutputStream dos;
 clientHandler(Socket sock) throws IOException
 {
     this.sock=sock;
     dis=new DataInputStream(sock.getInputStream());
     dos=new DataOutputStream(sock.getOutputStream());
     Thread t=new Thread(this);
     t.start();
 }
 @Override
 public void run()
 {    
    try
    {
        dos.writeBytes("hello client\r\n");
        String msg=dis.readLine();
        System.out.println(msg);
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    } 
   }
 }
 public static void main(String args[])
 {ServerMain ser=new ServerMain();
 }
  
}
