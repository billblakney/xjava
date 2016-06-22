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

   Font _defaultFont = null;

   Vector<AbstractFontSetter> _fontSetters = new Vector<AbstractFontSetter>();
	
	public CustomTableCellRenderer(AbstractFontSetter aFontSetter) {
		super();
		init();
		_defaultFont = UIManager.getFont("Label.font");
		_fontSetters.add(aFontSetter);
	}
	
	public CustomTableCellRenderer(Vector<AbstractFontSetter> aFontSetters) {
		super();
		init();
		_fontSetters = aFontSetters;
	}

	private void init(){
		setOpaque(true);
		setFont(getFont().deriveFont(Font.PLAIN));
		setHorizontalAlignment(SwingConstants.RIGHT);
	}

	
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column)
	{
	   setText(value.toString());
	   
	   AbstractFontSetter._font = null;
	   for (AbstractFontSetter tSetter: _fontSetters)
	   {
	      tSetter.setFont(this, table, value, isSelected, hasFocus, row, column);
	   }
	   if (AbstractFontSetter._font != null)
	   {
	      setFont(AbstractFontSetter._font);
	   }
	   else
	   {
	      setFont(_defaultFont);
	   }
	   AbstractFontSetter._font = null;
//	   setBackground(scheme.bg_Normal[i]);
//	   setForeground(scheme.fg_Normal[i]);
		return this;
	}
}
