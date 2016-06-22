import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JLabel;

public interface ColorGetter
{
	public Font getFont(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column);
}
