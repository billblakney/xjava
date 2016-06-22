import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTable;

public class IntWithCommaGetter implements TableCellInfoGetter
{
	protected DecimalFormat _format = new DecimalFormat(
	"###,###,###,##0");
	
	Integer _column = null;

   IntWithCommaGetter(Integer aColumn)
   {
	   _column = aColumn;
   }

   @Override
	public TableCellInfo getInfo(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column)
	{
	   TableCellInfo tInfo = null;

	   if (column == _column.intValue())
	   {
	      tInfo = new TableCellInfo();
	      tInfo._text = _format.format(value);
	   }
	   
	   return tInfo;
	}

}
