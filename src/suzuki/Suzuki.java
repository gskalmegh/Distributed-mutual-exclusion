/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suzuki;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

/**
 *
 * @author ghanshyam
 */
 
public class Suzuki {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        int port[]=new int[3];
        //int port[]={1992,1993,1994};
        int siteid=1;
        int dport=2192;
        String host[]=new String[3];
       // String host[]={"localhost","localhost","localhost"};
        boolean token=true;
        Site s;
        
       BufferedReader cin=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter site id[0 or 1 or 2]:");
        siteid=Integer.parseInt(cin.readLine());
        for(int i=0;i<3;i++)
        {
            System.out.println("Enter host:"+i);
            host[i]=cin.readLine();
            System.out.println("Enter port:"+i);
            port[i]=Integer.parseInt(cin.readLine());
            
        }
        
        System.out.println("Enter dispatcher port:");
            dport=Integer.parseInt(cin.readLine());
            
             System.out.println("Enter token value (true/false):");
            token=Boolean.parseBoolean(cin.readLine());
           
          
      System.out.println("===========================================");

        System.out.println("server port="+port[siteid]);
          System.out.println("dispatcher port="+dport);
            System.out.println("===========================================");
            
            
           // Site(boolean token,int siteid,int[] port,String[] host)
            s=new Site(token,siteid,port,host);
            
       
       
        new Server(s).start();
        new Dispatch(dport,s).start();
           
                
                           
                for(int i=0;i<port.length;i++)
                {
                    if(i!=siteid)
                       new Client(i,s).start();
                }
           
            
       }
    
}
