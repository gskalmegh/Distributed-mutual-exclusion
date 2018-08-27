/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suzuki;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ghanshyam
 */
public class Server extends Thread {
    
    Socket s;//new ServerSocket(2192);
    Site site;
     ServerSocket ss;
    public Server(Site site) throws IOException
    {
       
         this.site=site;
          ss=new ServerSocket(site.port[site.siteid]);
         
    }
    
    public void run()
    {
         
		while(true)
			
             try {
               
            
                        s=ss.accept();
                       DataInputStream din=new DataInputStream(s.getInputStream());
                        byte buff[]=new byte[1024];
                        din.read(buff);
                        //notifyAll();
                        
                        System.out.println(new String(buff));
                        String cmd[]=(new String(buff)).split("#");
                        if(cmd[0].equals("<reqtoken>"))
                        {
                         int reqsite=Integer.parseInt(cmd[1]);
                         int seqnum=Integer.parseInt(cmd[2]);
                         site.Rn[reqsite]=Math.max(site.Rn[reqsite],seqnum);
                         if(site.Rn[reqsite]==site.Ln[reqsite]+1)
                             site.enqueue(""+reqsite);
                         
                          }
                        if(cmd[0].equals("<token>"))
                        {
                            site.setReqtoken(false);
                            site.token=true;
                            site.queue=cmd[1];
                            site.setLnarr(cmd[2]);
                            site.idle=false;
                            site.setReqtoken(false);
                            System.out.println("I heve token");
                        }
                        
                       // while(!site.idle){}
                        if(site.isToken()&&site.isIdle())
                        {
                            site.Ln=site.Rn;
                            int ressite;
                            
                           if(site.queue.length()>0)
                            {
                                site.token=false;
                                System.out.println("I dont have token");
                              ressite=Integer.parseInt(site.dequeue());
                              Socket skt=new Socket(site.host[ressite],site.port[ressite]);
                              //Socket skt=new Socket("localhost",2198);
                              String msg="<token>#"+site.queue+"#"+site.getLnarr()+"#";
                             DataOutputStream tmp= new DataOutputStream(skt.getOutputStream());
                             tmp.write(msg.getBytes());
                             tmp.flush();
                             skt.close();
                              
                            }
                        }
                       
                        
                        
                       
                        
                        
                         } catch (Exception e) {
                 
             }
                        
                        
		
        
        
    }
}
