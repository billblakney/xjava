import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import javafx.util.Pair;
import javax.swing.JFrame;

/**
 * Demonstrates use of the IdComboBox.
 */
public class XIdComboBox implements ActionListener
{
   private JFrame frame;

   private IdComboBox _pairComboBox;

   private IdComboBox _mapComboBox;

   /**
    * Launch the application.
    */
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
      {
         public void run()
         {
            try
            {
               XIdComboBox window = new XIdComboBox();
               window.frame.setVisible(true);
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public XIdComboBox()
   {
      initialize();
   }

   /**
    * Initialize the contents of the frame. Creates two instances of IdComboBox:
    * one using a vector of (ID,name) pairs, and one using a map of (ID,name)
    * entries. Registers an action listener for both instances, which prints out
    * the selected IDs.
    */
   private void initialize()
   {
      frame = new JFrame();
      frame.setBounds(800, 500, 100, 130);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(new FlowLayout());

      /*
       * Create the instance that uses a vector of (ID,name) pairs.
       */
      Vector<Pair<Integer, String>> tPairs = new Vector<Pair<Integer, String>>();
      tPairs.add(new Pair<Integer, String>(9, "Nine"));
      tPairs.add(new Pair<Integer, String>(7, "Seven"));
      tPairs.add(new Pair<Integer, String>(5, "Five"));

      _pairComboBox = new IdComboBox(tPairs);
      _pairComboBox.addActionListener(this);

      frame.getContentPane().add(_pairComboBox);

      /*
       * Create the instance that uses a map of (ID,name) entries. In this case,
       * we use a LinkedHashMap so that the names will show up in the combobox
       * list in the same order that they are added here.
       */
      Map<Integer, String> tIdMap = new LinkedHashMap<Integer, String>();
      tIdMap.put(8, "Eight");
      tIdMap.put(6, "Six");
      tIdMap.put(4, "Four");
      tIdMap.put(7, "Seven");

      _mapComboBox = new IdComboBox(tIdMap);
      _mapComboBox.addActionListener(this);

      frame.getContentPane().add(_mapComboBox);
   }

   /**
    * Print the selected ID and the IdComboBox from which it was selected.
    */
   public void actionPerformed(ActionEvent e)
   {
      System.out.println("in actionPerformed");
      IdComboBox tComboBox = (IdComboBox) e.getSource();

      if (tComboBox == _pairComboBox)
      {
         System.out.println("_pairComboBox, selected id: "
               + _pairComboBox.getSelectedId());
      }
      else if (tComboBox == _mapComboBox)
      {
         System.out.println("_mapComboBox, selected id: "
               + _mapComboBox.getSelectedId());
      }
   }
}
