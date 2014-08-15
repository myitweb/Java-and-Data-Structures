/* Design a Calculator class in java, and implement all the methods required by calculator operations. */
// Source : techtopz.in/2013/07/23/java-programming-creating-a-simple-calculator/



//import Scanner as we require it.
import java.util.Scanner;
 
// the name of our class its public
public class SimpleCalculator {
    //void main
        public static void main (String[] args)
        {
            //declare int and char
            int a,b,result=0;
            char c;
 
            //Declare input as scanner
            Scanner input = new Scanner(System.in);
 
            //Take inputs
             System.out.println("Enter no. :");
             a = input.nextInt();
             System.out.println("Enter no. :");
             b = input.nextInt();
             System.out.println("Enter Operator :");
             String st = input.next();
             c = st.charAt(0);
 
             //add a switch statement
             switch(c)
             {
             case '+':
                 result = a+b;
                 System.out.println("Result = "+result);
                 break;
             case '-':
                 result = a-b;
                 System.out.println("Result = "+result);
                 break;
             case 'x':
                 result = a*b;
                 System.out.println("Result = "+result);
                 break;
             case '/':
                 result = a/b;
                 System.out.println("Result = "+result);
                 break;
             default:
                System.out.println("Syntax Error");
             }
        }
}
