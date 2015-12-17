
package os.test1;

public class Process implements Runnable {
    private int excuteTime;
    private String name;
    private int priority;
    
    public Process (){
        this.priority=5;
        this.excuteTime=2;
    }
    
    public Process (String name){
        this.priority=5;
        this.excuteTime=2;
        this.name=name;
    }
    public Process (String name,int priority ,int excuteTime){
        setPriority(priority);
        this.excuteTime=excuteTime;
        this.name=name;
    }
    public Process (String name,int priority){
       // this.priority=priority;
        
        setPriority(priority);
        this.excuteTime=2;
        this.name=name;
        
    }
    public void setName (String name){
        this.name=name;
    }
    public void setPriority (int t){
        if(t>10) t=10;
        if(t<=0) t=1;
    } 
    public int getPriority (){
        return this.priority;
    }
    public String getProcessName(){
        return name;
    }
    public void getInfo (){
        System.out.println("Process Info:");
        System.out.println("    Process name : "+this.name);
        System.out.println("    process Periority : "+this.priority);
        System.out.println("    process excute time : "+this.excuteTime);
    }

    @Override
    public void run() {
        System.out.println("the process"+this.name+"is now Running");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
