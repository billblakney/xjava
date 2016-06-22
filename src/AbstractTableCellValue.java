import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JLabel;

public abstract class AbstractTableCellValue<T>
{
   private final Class<T> _type;

   public AbstractTableCellValue(Class<T> type)
   {
      this._type = type;
   }
      
   public Class<T> getMyType()
   {
      return this._type;
   }

	abstract T getValue(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column);
}