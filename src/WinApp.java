import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class WinApp
{
   private JFrame frame;

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
               WinApp window = new WinApp();
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
   public WinApp()
   {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize()
   {
      frame = new JFrame();
      frame.getContentPane().setBackground(new Color(0, 0, 128));
      frame.setBounds(100, 100, 868, 865);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);

      JMenuBar menuBar = new JMenuBar();
      menuBar.setBounds(0, 0, 200, 21);
      frame.getContentPane().add(menuBar);

      JMenu mnNewMenu = new JMenu("File Menu");
      menuBar.add(mnNewMenu);

      JMenuItem mntmSayhello = new JMenuItem("Say \"Hello\"");
      mnNewMenu.add(mntmSayhello);

      JMenu mnNewMenu_2 = new JMenu("Edit Menu");
      menuBar.add(mnNewMenu_2);

      JMenuItem mntmNewMenuItem = new JMenuItem("Edit");
      mnNewMenu_2.add(mntmNewMenuItem);

      JMenu mnNewMenu_1 = new JMenu("Test Menu");
      menuBar.add(mnNewMenu_1);

      JMenuItem mntmNewMenuItem_1 = new JMenuItem("Test");
      mnNewMenu_1.add(mntmNewMenuItem_1);
   }
}
