/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.test1;

/**
 *
 * @author hasna2
 */
public class Resource {
    String name;
    int size;
    int numofCurrentUsed;
    Process []  currentUsed= new Process[size+1];
    public Resource(String name){
        this.name=name;
        this.size=Integer.MAX_VALUE;
        this.numofCurrentUsed=0;
    }
    public Resource(String name,int size ){
        this.name=name;
        this.size=size;
        this.numofCurrentUsed=0;
    }
    
    public Resource(String name,int size,Process currentUsed[]){
        this.name=name;
        this.size=size;
        this.numofCurrentUsed=0;
        this.currentUsed=currentUsed;
    }
    public boolean addProcess (Process newProcess){
        if(this.numofCurrentUsed==size)
            return false;
        else
        {
            this.currentUsed[this.numofCurrentUsed++]=newProcess;
            return true;
        }
    }
    
    public int getNumofCurrentUsed (){
        return this.numofCurrentUsed;
    }
    public void printcurrentProcess(){
        System.out.println("there are "+this.numofCurrentUsed+"are currently busy by process");
        System.out.println("    ");
        for(int i=0; i<this.numofCurrentUsed ; i++){
            System.out.print(currentUsed[i].getProcessName());
            if(i==this.numofCurrentUsed-1)
                System.out.println(".");
            else
                System.out.print(" , ");
        }
    }
}
