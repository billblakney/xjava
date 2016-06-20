import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class XDialogs implements ActionListener
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
            System.out.println("running XDialogs");
            try
            {
               XDialogs window = new XDialogs();
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
   public XDialogs()
   {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize()
   {
      frame = new JFrame();
//      frame.getContentPane().setBackground(new Color(0, 0, 128));
      frame.setBounds(100, 100, 868, 865);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);

      JMenuBar tMenuBar = new JMenuBar();
      tMenuBar.setBounds(0, 0, 200, 21);
      frame.getContentPane().add(tMenuBar);

      /* file menu */
      JMenu tFileMenu = new JMenu("File Menu");
      tMenuBar.add(tFileMenu);

      JMenuItem tSayHelloMenuItem = new JMenuItem("Say \"Hello\"");
      tSayHelloMenuItem.addActionListener(this);
      tFileMenu.add(tSayHelloMenuItem);

      /* edit menu */
      JMenu tEditMenu = new JMenu("Edit Menu");
      tMenuBar.add(tEditMenu);

      JMenuItem tEditMenuItem = new JMenuItem("Edit");
      tEditMenu.add(tEditMenuItem);

      /* test menu */
      JMenu tTestMenu = new JMenu("Test Menu");
      tMenuBar.add(tTestMenu);

      JMenuItem tTestMenuItem = new JMenuItem("Test");
      tTestMenu.add(tTestMenuItem);
   }
		public void actionPerformed(ActionEvent e) {
			// Handle "File Menu".
			if (e.getActionCommand().equals("Say \"Hello\"")) {
				System.out.println("User selected File Menu");
				DialogBoxGridBagLayout tDialog = new DialogBoxGridBagLayout(frame);
				if (tDialog.pressedOk()){
				   System.out.println("User pressed OK");
				}
			}
		}
}
