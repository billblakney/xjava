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