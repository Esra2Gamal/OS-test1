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
public class OSTest1 {
//kfjehbfj hfv
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cache c=new Cache ("FIFO");
        Process p1=new Process("1");
        Process p2=new Process("2");
        Process p3=new Process("3");
        Process p4=new Process("4");
        Process p5=new Process("5");
        p1.setPriority(2);
        c.readRequest(p3);
        c.readRequest(p2);
        c.readRequest(p1);
        c.readRequest(p3);
        c.readRequest(p4);
        c.readRequest(p1);
        c.readRequest(p1);
        c.readRequest(p5);
        c.printinfo();
        
    }
    
}
