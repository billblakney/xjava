package factorial;
import java.util.Scanner;

public class Factorial
{
   static public void main(String[] args)
   {
      testFactorial();
   }

   static public int factorial(int n)
   {
      if (n == 0)
      {
         return 0;
      }
      else if (n == 1)
      {
         return 1;
      }
      else
      {
         return n * factorial(n - 1);
      }
   }

   /**
    */
   static public void testFactorial()
   {
      Scanner tScanner = new Scanner(System.in);

      System.out.print( "Enter int: ");
      while (tScanner.hasNextLine())
      {
         int n = tScanner.nextInt();
         int f = factorial(n);
         System.out.println("factorial(" + n + ")=" + f);
         System.out.print( "Enter int: ");
      }
   }
}
