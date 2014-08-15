/* Design a class in java to add two complex numbers using constructors. */
// https://www.classle.net/book/simple-java-program-adding-2-complex-numbers


class Complex

{

                int iReal,iImaginary;

               

                Complex() //empty constructor

                {}

               

                Complex(int iTempReal,int iTempImaginary) // Two argument constructor

                {

                                iReal=iTempReal;

                                iImaginary=iTempImaginary;   

                }

               

                Complex fnAddComplex(Complex C1,Complex C2) // function to add the complex numbers

                {

                                Complex CTemp=new Complex();

                                CTemp.iReal=C1.iReal+C2.iReal;

                                CTemp.iImaginary=C1.iImaginary+C2.iImaginary;

                                return CTemp;

                }

}

 

class Complexmain

{

                public static void main(String[] a)

                {

                                Complex C1=new Complex(4,8); //calls the two argument constructor

                                Complex C2=new Complex(5,7); //calls the two argument constructor

                                Complex C3=new Complex();//calls the empty constructor

                                C3=C3.fnAddComplex(C1,C2); //function call

 

                                //Display the results

                                System.out.println("---Sum---");

                                System.out.println("Real :" + C3.iReal);

                                System.out.println("Imaginary :" + C3.iImaginary);

                }

}


