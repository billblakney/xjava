import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JLabel;

public class TestFontGetter1 extends AbstractTableCellValue<Font>
{
   TestFontGetter1()
   {
	   super(Font.class);
   }

	public Font getValue(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column)
	{
	   Font tValue = null;

	   if (column == 2)
	   {
	      tValue = label.getFont().deriveFont(Font.BOLD);
	   }
	   
	   return tValue;
	}
}
