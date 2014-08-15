/* Hybrid Inheritance example program in Java */
// Source : http://beginnersbook.com/2013/10/hybrid-inheritance-java-program/



public class A
{
     public void methodA()
     {
           System.out.println("Class A methodA");
      }
}
public class B extends A
{
      public void methodA()
      {
           System.out.println("Child class B is overriding inherited method A");
      }
      public void methodB()
      {
           System.out.println("Class B methodB");
       }
}
public class C extends A
{
      public void methodA()
      {
           System.out.println("Child class C is overriding the methodA");
       }
      public void methodC()
      {
           System.out.println("Class C methodC");
       }
}
public class D extends B, C
{
      public void methodD()
      {
           System.out.println("Class D methodD");
       }
       public static void main(String args[])
       {
              D obj1= new D();
              obj1.methodD();
              obj1.methodA();
       }
}
