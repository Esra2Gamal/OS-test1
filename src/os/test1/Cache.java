/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.test1;

import java.util.Queue;

/**
 *
 * @author hasna2
 */
public class Cache {
    private final Process []caschedProcess;  // Procesies already on cash 
    private int []frequency;
    private int []recently;
    private  int capacity;                //cash memory size
    private  int size;
    private String policy;
    private int numofProcess;
    Queue <Process> q;
   

    public Cache(int capacity ,String policy){
        this.capacity=capacity;
        this.caschedProcess=new Process[this.capacity];
        this.frequency=new int [this.capacity];
        this.recently=new int [this.capacity];
        this.policy=policy;
        this.numofProcess=0;
        
    }
    public Cache(){
        capacity=4;
        caschedProcess=new Process[this.capacity];
        this.frequency=new int [this.capacity];
        this.recently=new int [this.capacity];
        this.policy="LFU";
        this.numofProcess=0;
    }
    public void printinfo (){
        for(int i=0 ; i<size ; i++){
            System.out.println("process :"+i+caschedProcess[i].getProcessName());
            System.out.print("  frq:"+frequency[i]);
            System.out.println("    rec:"+recently[i]);
            
        }
    }
    public void readRequest(Process newProcess){ //reading request from cash  
        /*if empty cache */
        if (! tryHit( newProcess )){
            //for miss print miss
            System.out.println("miss");
            if( !addProcess(newProcess)){
                System.out.println("need to replace ");
            if(policy.equals("FIFO"))
                FIFO(newProcess);
            if(policy.equals("LRU"))
                LRU(newProcess);
            if(policy.equals("LFU"))
                LFU(newProcess);
            } 
            else System.out.println("you can add process");
        }
        //for test when hit print hit
        else System.out.println("hit");
        
    }
    boolean tryHit (Process newProcess){   //try to git process from the cash
        for(int i=0; i<size ; i++)
            if (newProcess == caschedProcess[i]){
                frequency[i]++;
                recently[i]=numofProcess++;
                return true;
            }
        return false;
    }
     private boolean addProcess (Process newProcess){
         
         if(size<=capacity){
             q.add(newProcess);
             caschedProcess[size]=newProcess;
             frequency[size]++;
             recently[size]=numofProcess++;
             size++;
             System.out.println("new process added in "+(size-1) );
            return true; 
         }
       
         return false;
     }
         
     
     private void FIFO (Process newProcess){      //add process using First In First Out (FIFO)Raplacement algorithm
        q.poll();
        q.add(newProcess);
    }
    
      private void LRU (Process newProcess) {      //add process using Least Recently Used (LRU)Raplacement algorithm
         int minr=0,minpindx=0;
          for(int i=0 ; i<capacity ; i++){
              if(recently[i]<minr){
                  minr=recently[i];
                  minpindx=i;
              }
          }
          caschedProcess[minpindx]=newProcess;
          recently[minpindx]=numofProcess++; 
        
    }
      private void LFU (Process newProcess) {       //add process using Least frequently Used (LFU)Raplacement algorithm
          System.out.println("LFU is used to replace.....");
          int minf=0,minpindx=0;
          for(int i=0 ; i<capacity ; i++){
              if(frequency[i]<minf){
                  minf=frequency[i];
                  minpindx=i;
              }
          }
          System.out.println("replace "+minpindx+"process with min freq "+minf);
          caschedProcess[minpindx]=newProcess;
          frequency[minpindx]=1;
        
    }
    
}
