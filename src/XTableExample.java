import java.awt.Font;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
 
public class XTableExample extends JFrame
{
    public XTableExample()
    {
        //headers for the table
        String[] columns = new String[] {
            "Id", "Name", "Hourly Rate", "Part Time", "Int"
        };
         
        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
            {1, "John", 40.0, false, 1000000 },
            {2, "Rambo", 70.0, false, 599000 },
            {3, "Zorro", 60.0, true, 35 },
        };
 
        //create table with data
        JTable table = new JTable(data, columns);
        
        Font labelFont = UIManager.getFont("Label.font");

        Vector<AbstractTableCellValue> tSetters = new Vector<AbstractTableCellValue>();
        tSetters.add(new TestFontGetter2());
        tSetters.add(new TestFontGetter1());
        tSetters.add(new TestColorGetter1());
        tSetters.add(new TestIntegerGetter1(4/*column*/));

        CustomTableCellRenderer r = new CustomTableCellRenderer(tSetters);

        table.setDefaultRenderer(Object.class, r);
         
        //add the table to the frame
        this.add(new JScrollPane(table));
         
        this.setTitle("Table Example");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.pack();
        this.setVisible(true);
    }
     
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new XTableExample();
            }
        });
    }   
}