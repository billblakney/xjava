import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
//import javax.swing.table.TableRowSorter;

public class CustomTableCellRenderer extends JLabel implements TableCellRenderer {

//	TableRowSorter<GainTableModel> sorter;
	
//	TableTwoColorScheme scheme;

   Color _defaultForegroundColor = null;
   Color _defaultBackgroundColor = null;
   
//   Color color = UIManager.getColor ( "Panel.background" );
   
   Vector<AbstractTableCellValue> _fontSetters = new Vector<AbstractTableCellValue>();
	
	public CustomTableCellRenderer(AbstractTableCellValue aFontSetter) {
		super();
		init();
		_fontSetters.add(aFontSetter);
	}
	
	public CustomTableCellRenderer(Vector<AbstractTableCellValue> aFontSetters) {
		super();
		init();
		_fontSetters = aFontSetters;
	}

	private void init(){
		setOpaque(true);
		setFont(getFont().deriveFont(Font.PLAIN));
		setHorizontalAlignment(SwingConstants.RIGHT);
//		_defaultBackgroundColor = UIManager.getColor ( "Panel.background" );
//		_defaultForegroundColor = UIManager.getColor ( "Panel.foreground" );
	}

	
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column)
	{
	   String tText = null;
	   Font tFont = null;
	   Color tBackground = null;
	   Color tForeground = null;

	   for (AbstractTableCellValue tSetter: _fontSetters)
	   {
	      if (tSetter.getMyType() == TableCellInfo.class)
	      {
	         TableCellInfo tInfo = (TableCellInfo)tSetter.getValue(this, table, value, isSelected, hasFocus, row, column);
	         if (tInfo != null)
	         {
	            if (tInfo._text != null)
	            {
	               tText = tInfo._text;
	            }
	            if (tInfo._font != null)
	            {
	               tFont = tInfo._font;
	            }
	            if (tInfo._bgColor != null)
	            {
	               tBackground = tInfo._bgColor;
	            }
	            if (tInfo._fgColor != null)
	            {
	               tForeground = tInfo._fgColor;
	            }
	         }
	      }
	      else if (tSetter.getMyType() == Font.class)
	      {
	         Font tNewFont = (Font) tSetter.getValue(this, table, value, isSelected, hasFocus, row, column);
	         if (tNewFont != null)
	         {
	            tFont = tNewFont;
	         }
	      }
	      else if (tSetter.getMyType() == Color.class)
	      {
	         Color tNewColor = (Color) tSetter.getValue(this, table, value, isSelected, hasFocus, row, column);
	         if (tNewColor != null)
	         {
	            tBackground = tNewColor;
	         }
	      }
	      else if (tSetter.getMyType() == String.class)
	      {
	         String tNewString = (String) tSetter.getValue(this, table, value, isSelected, hasFocus, row, column);
	         if (tNewString != null)
	         {
	            tText = tNewString;
	         }
	      }
	   }
	   
	   if (tText != null)
	   {
	      setText(tText);
	   }
	   else
	   {
	      setText(value.toString());
	   }

	   if (tFont != null)
	   {
	      setFont(tFont);
	   }
	   else
	   {
	      setFont(null);
	   }
	   
	   if (tBackground != null)
	   {
	      setBackground(tBackground);
	   }
	   else
	   {
	      setBackground(null);
	   }
	   
	   if (tForeground != null)
	   {
	      setForeground(tForeground);
	   }
	   else
	   {
	      setForeground(null);
	   }
	   
//	   setBackground(scheme.bg_Normal[i]);
//	   setForeground(scheme.fg_Normal[i]);
		return this;
	}
}
