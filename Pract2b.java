/* Design a java class for the use of interface. */
// Source : http://www.codemiles.com/java-examples/java-interface-example-t3505.html

public class Main {
             
    public static void main(String[] args) {
         
          shape circleshape=new circle();
           
             circleshape.Draw();
    }
}
 
interface shape
 {
     public   String baseclass="shape";
      
     public void Draw();     
      
 }

 class circle implements shape
 {
 
    public void Draw() {
        System.out.println("Drawing Circle here");
    }
      
      
 }

