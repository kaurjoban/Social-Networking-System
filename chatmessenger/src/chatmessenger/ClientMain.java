package chatmessenger;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
class ClientMain
{
    public static void main(String args[])throws Exception
    {ClientMain cli=new ClientMain();
    
    }
    ClientMain()
    {try
      {
        Socket sock=new Socket("127.0.0.1",9900);
        DataInputStream dis=new DataInputStream(sock.getInputStream());
        DataOutputStream dos=new DataOutputStream(sock.getOutputStream());
        String msg=dis.readLine();
        System.out.println(msg);
        dos.writeBytes("welcome\r\n");
       }
    catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }         
            
}
