package generictype;

import java.awt.Color;

public class SomeColorClass extends AbstractBaseType<Color>
{
   SomeColorClass()
   {
	   super(Color.class);
   }

	public Color getValue()
	{
	   return Color.BLACK;
	}
}
