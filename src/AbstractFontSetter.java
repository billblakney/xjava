import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JLabel;

abstract public class AbstractFontSetter
{
   public static Font _font = null;
   
	abstract public void setFont(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column);
}