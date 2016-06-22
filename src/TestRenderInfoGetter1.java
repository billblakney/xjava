import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JLabel;

public class TestRenderInfoGetter1 extends AbstractTableCellValue<TableCellInfo>
{
   protected int _row = -1;
   protected int _col = -1;

   TestRenderInfoGetter1(int aRow,int aColumn)
   {
	   super(TableCellInfo.class);
	   _row = aRow;
	   _col = aColumn;
   }

	public TableCellInfo getValue(JLabel label,JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column)
	{
	   TableCellInfo tValue = null;

	   if (column == _col && row == _row)
	   {
	      tValue = new TableCellInfo();
	      tValue._text = "HELLO";
	      tValue._font = label.getFont().deriveFont(Font.BOLD);
	      tValue._bgColor = Color.BLACK;
	      tValue._fgColor = Color.WHITE;
	   }
	   
	   return tValue;
	}
}
