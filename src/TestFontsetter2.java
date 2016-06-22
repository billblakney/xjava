import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JLabel;

public class TestFontsetter2 extends AbstractFontSetter
{
	public void setFont(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column)
	{
	   if (row == 2)
	   {
	      _font = label.getFont().deriveFont(Font.ITALIC);
	   }
	}
}
