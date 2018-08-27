/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suzuki;

/**
 *
 * @author ghanshyam
 */
public class Site {
   boolean token,idle,reqtoken;
    int Rn[]=new int[3];
    int Ln[]=new int[3];
    int port[]=new int[3];
    String host[]=new String[3];
    int siteid;
    String queue="";
   
   public Site(boolean token,int siteid,int[] port,String[] host)
    {
        this.token=token;
        this.siteid=siteid;
        this.port=port;
        this.host=host;
        this.reqtoken=false;
        this.idle=true;
    }

    public boolean isToken() {
        return token;
    }

    public void setToken(boolean token) {
        this.token = token;
    }

   synchronized public boolean isIdle() {
        if(!this.idle)try{wait();}catch(Exception e){}
        return idle;
    }

  synchronized  public void setIdle(boolean idle) {
        
        this.idle = idle;
        if(this.idle)
            notify();
    }

    public boolean isReqtoken() {
       if(!reqtoken)
          try{ wait();}catch(Exception e){}
        return reqtoken;
    }

    synchronized public void setReqtoken(boolean reqtoken) {
        if(reqtoken)notifyAll();
        this.reqtoken = reqtoken;
    }

   synchronized public int[] getRn() {
        return Rn;
    }

   synchronized public void setRn(int[] Rn) {
        this.Rn = Rn;
    }

  public int[] getLn() {
        return Ln;
    }

   synchronized public void setLn(int[] Ln) {
        this.Ln = Ln;
    }

    public String getQueue() {
        return queue;
    }

  synchronized  public void setQueue(String queue) {
        this.queue = queue;
    }
   
   
   
   
   
   
   
   
   
 synchronized  public void enqueue(String str)
   {
       queue=queue+str+"#";
       
   }
   
   
 synchronized  public String dequeue()
   {
       String str="";
       int index=queue.indexOf("#");
       try{
        str=queue.substring(0, index);
       queue=queue.substring(index+1);
       }catch(Exception e){System.out.println(e);}
       return str;
   }
   
   public String getLnarr()
   {
       String op="";
       for(int i=0;i<Ln.length;i++)
           op=op+Ln[i]+"#";
       return op;
   }
   synchronized public void setLnarr(String str)
   {
       String arr[]=str.split(";");
       for(int i=0;i<arr.length;i++)
       {
           Ln[i]=Integer.parseInt(arr[i]);
       }
   }
    
}
