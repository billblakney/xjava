import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;

public class TestColorGetter1 extends AbstractTableCellValue<Color>
{
   TestColorGetter1()
   {
	   super(Color.class);
   }

	public Color getValue(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column)
	{
	   Color tValue = null;

	   if (column == 1 && row == 1)
	   {
	      tValue = Color.BLUE;
	   }
	   
	   return tValue;
	}
}
