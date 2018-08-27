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
import java.util.Date;

/**
 *
 * @author ghanshyam
 */
public class Dispatch extends Thread{
    ServerSocket ss;
     Socket s;//new ServerSocket(2192);
    Site site;
    
    public Dispatch(int port,Site site) throws IOException
    {
         ss=new ServerSocket(port);
         this.site=site;
    }
    
    
    
    public void run()
    {
         
		  while(true)
		
             try {
                 
                    s=ss.accept();
                 
                        site.setIdle(false);
			String res="wrong command";
                       DataInputStream din=new DataInputStream(s.getInputStream());
                        byte buff[]=new byte[1024];
                        din.read(buff);
                        String req=new String(buff);
                        String arr[]=req.split("#");
                        System.out.println(req);
                        if(arr[0].equals("getdate"))
                        {
                            if(!site.token)
                            {
                               site.Rn[site.siteid]++;
                               
                               site.setReqtoken(true);
                               
                               System.out.println("request token set to true");
                            }
                             System.out.println("i have token");
                           
                            res=""+new Date();//consider this as critical section
                            site.setReqtoken(false);
                        }
                        
                        
                       DataOutputStream ds=new DataOutputStream(s.getOutputStream());
			
                       ds.write(res.getBytes());
			
                       ds.flush();
                       
                        site.setIdle(true);
                        
                        
                        
                        
                         } catch (Exception e) {
                             System.out.println(e);
                 
             }
                        
                        
		
        
        
    }
    
    
    
}
