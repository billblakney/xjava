package generictype;

public abstract class AbstractBaseType<T>
{
   private final Class<T> _type;

   public AbstractBaseType(Class<T> type)
   {
      this._type = type;
   }
      
   public Class<T> getType()
   {
      return this._type;
   }

	abstract T getValue();
}