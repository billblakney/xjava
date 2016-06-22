import java.text.DecimalFormat;
import javax.swing.JTable;
import javax.swing.JLabel;

public class TestIntegerGetter1 extends AbstractTableCellValue<String>
{
	protected DecimalFormat _format = new DecimalFormat(
	"###,###,###,##0");
	
	Integer _column = null;

   TestIntegerGetter1(Integer aColumn)
   {
	   super(String.class);
	   _column = aColumn;
   }

	public String getValue(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column)
	{
	   String tValue = null;

	   if (column == _column.intValue())
	   {
	      tValue = _format.format(value);
	   }
	   
	   return tValue;
	}
}
