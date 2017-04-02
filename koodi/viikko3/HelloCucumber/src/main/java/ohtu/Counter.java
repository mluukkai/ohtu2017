
package ohtu;

public class Counter {
   int val = 0;

   public void increase(){
        val++;
   } 
   
   public void reset(){
        val = 0;
   }    
   
   public void increment(int a){
        val += a;
   } 
   
   public int value(){
       return val;
   }
}
