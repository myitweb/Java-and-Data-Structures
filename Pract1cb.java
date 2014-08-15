/*
Design a java class for method overriding.
*/
// Source : http://www.careerride.com/java-method-overloading-and-overriding.aspx



class Super
{
     int sum;
     A(int num1, int num2)
     {
             sum = a+b;
     }
     void add()
     {
            System.out.println("Sum : " + sum);
     }
}
class Sub extends Sub
{
       int subSum;
       Sub(int num1, int num2, int num3)
       {
               super(num1, num2);
               subSum = num1+num2+num3;
       }
       void add()
       {
               super.add();
               System.out.println("Sum of 3 nos : " +subSum);
       }

