/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suzuki;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author ghanshyam
 */
public class Client extends Thread {
    
   
    int channelid;
 
    Site site;
    public Client(int channelid,Site site) throws IOException
    {
       
         this.site=site;
         this.channelid=channelid;
    }
    
    
    public void run()
    {
        
        
        
		while(true)
                    try{
		
		//System.out.println("hi i am channel "+channelid+" for port"+site.port[channelid]);
                   
                    if(site.isReqtoken())
                    {
                        Socket s=new Socket(site.host[channelid],site.port[channelid]);
                String req="";
                    req="<reqtoken>#"+site.siteid+"#"+site.Rn[site.siteid]+"#";
               DataOutputStream ds=new DataOutputStream(s.getOutputStream());
		
               ds.write(req.getBytes());
		ds.flush();
               // System.out.println("channel "+channelid+": request sent");
                    }
		
		
                    }
                    catch(Exception e){}
                
		
        
        
        
        
    }
    
    
    
    
}
