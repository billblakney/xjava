package generictype;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

/**
 * Demonstrate a pattern that allows users of generics to know what type they
 * are based on. See the AbstractBaseType, which has the key parts of the
 * pattern, including the constructor and getType method.
 */
public class XGenericType
{
   public static void main(String[] args)
   {
      SomeIntegerClass tInteger = new SomeIntegerClass();
      SomeColorClass tColor = new SomeColorClass();
      SomeFontClass tFont = new SomeFontClass();
      
      Vector<AbstractBaseType> tVector = new Vector<AbstractBaseType>();
      tVector.add(tInteger);
      tVector.add(tColor);
      tVector.add(tFont);
      
      checkTypes(tVector);
   }
   
   public static void checkTypes(Vector<AbstractBaseType> aVector)
   {
      for (AbstractBaseType tItem: aVector)
      {
         if (tItem.getType() == Integer.class)
         {
            System.out.println("Type is Integer");
         }
         else if (tItem.getType() == Color.class)
         {
            System.out.println("Type is Color");
         }
         else if (tItem.getType() == Font.class)
         {
            System.out.println("Type is Font");
         }
         else
         {
            System.out.println("Unexpected type");
         }
      }
   }
}