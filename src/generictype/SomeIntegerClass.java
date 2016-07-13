package generictype;

public class SomeIntegerClass extends AbstractBaseType<Integer>
{
   SomeIntegerClass()
   {
	   super(Integer.class);
   }

	public Integer getValue()
	{
	   return 1;
	}
}
