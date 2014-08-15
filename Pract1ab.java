// Design a java program for type casting different types of variables. (explicit)
// Source  : http://www.studytonight.com/java/type-casting-in-java.php 

public class Test
{
    public static void main(String[] args)
    {
      double d = 100.04;  
      long l = (long)d;  //explicit type casting required  
      int i = (int)l;	//explicit type casting required  
      
      System.out.println("Double value "+d);
      System.out.println("Long value "+l);
      System.out.println("Int value "+i);
     
    }
    
}


