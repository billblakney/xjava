import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.io.File;

public class DialogBoxGridBagLayout extends JDialog implements ActionListener
{
   private JPanel _panel = null;

   // Ok and Cancel buttons
   private JButton _okButton = null;
   private JButton _cancelButton = null;

   // Row 1: label, combobox
   private JLabel _firstNameLabel = null;
   private JComboBox<String> _firstNameComboBox = null;

   // Row 2: label, combobox
   private JLabel _lastNameLabel = null;
   private JComboBox<String> _lastNameComboBox = null;

   // Row 2: label, file text and browse button
   private JLabel _fileLabel = null;
   private JTextField _fileTextField = null;
   private JButton _fileBrowseButton = null;
   private JPanel _filePane = null;

   // selected file
   private String _fileDir = null;

   // pressed ok or cancel
   private boolean _pressedOk = false;

   /**
    * Use GridBagLayout for dialog user choices
    */
   public DialogBoxGridBagLayout(JFrame aOwner)
   {
      super(aOwner, true/* modal */);

      Dimension tDim;

      _fileDir = ".";

      _panel = new JPanel();
      getContentPane().add(_panel);

      // create _firstNameLabel and _firstNameComboBox
      createComponentsRow1();

      // create _lastNameLabel and _lastNameComboBox
      createComponentsRow2();

      // create _fileLable, _fileTextField, _fileBrowseButton
      createComponentsRow3();

      // create the main pane for user inputs
      JPanel tGridPane = createGridPane();

      // create the ok/cancel button pane
      JPanel tButtonPane = createButtonPane();

      // setup the main content pane
      Container contentPane = getContentPane();
      contentPane.add(tGridPane, BorderLayout.CENTER);
      contentPane.add(tButtonPane, BorderLayout.PAGE_END);

      pack();
      setLocationRelativeTo(aOwner);
      setSize(400, 185);
      setVisible(true);
   }

   /**
    */
   JPanel createGridPane()
   {
      /*
       * Use a GridBagLayout for the upper settings area. How to use
       * GridBagLayout:
       * https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
       */
      JPanel tGridPane = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();

      // common settings for column 1
      c.fill = GridBagConstraints.NONE; // use natural size
      c.anchor = GridBagConstraints.LINE_END; // right align

      _filePane = createFilePane();

      // extra space on top of first row
      int tXTop = 15;
      // extra space at bottom top of last row
      int tXBottom = 5;
      // extra space at right edge of last column
      int tXRight = 15;
      // extra space at left edge of first column
      int tXLeft = 5;

      // settings for firstName label
      c.gridx = 0;
      c.gridy = 0;
      c.insets = new Insets(5 + tXTop, 5+ tXLeft, 5, 5);
      tGridPane.add(_firstNameLabel, c);

      // settings for lastName label
      c.gridx = 0;
      c.gridy = 1;
      c.insets = new Insets(5, 5+ tXLeft, 5, 5);
      tGridPane.add(_lastNameLabel, c);

      // settings for file label
      c.gridx = 0;
      c.gridy = 2;
      c.insets = new Insets(5, 5+ tXLeft, 5+ tXBottom, 5);
      tGridPane.add(_fileLabel, c);

      // common settings for column w
      c.anchor = GridBagConstraints.LINE_START; // left align

      // settings for firstName combobox
      c.gridx = 1;
      c.gridy = 0;
      c.weightx = 0.25;
      c.insets = new Insets(5+ tXTop, 5, 5, 5+ tXRight);
      tGridPane.add(_firstNameComboBox, c);

      // settings for lastName combobox
      c.gridx = 1;
      c.gridy = 1;
      c.weightx = 0.25;
      c.insets = new Insets(5, 5, 5, 5+ tXRight);
      tGridPane.add(_lastNameComboBox, c);

      // settings for file area
      c.fill = GridBagConstraints.HORIZONTAL; // stretch to fill horizontal

      c.gridx = 1;
      c.gridy = 2;
      c.weightx = 0.95;
      c.insets = new Insets(5, 5, 5+ tXBottom, 5+ tXRight);
      tGridPane.add(_filePane, c);

      return tGridPane;
   }

   /**
    * Creates row1: Name: <firstName_combobox>
    */
   void createComponentsRow1()
   {
      _firstNameLabel = new JLabel("First name:");

      String[] tSomeNames = { "John", "Mary", "Sylvester" };
      _firstNameComboBox = new JComboBox<String>(tSomeNames);
   }

   /**
    * Creates row2: Name: <lastName_combobox>
    */
   void createComponentsRow2()
   {
      _lastNameLabel = new JLabel("Last name:");

      String[] tSomeNames = { "Smith", "Contrary", "Woo" };
      _lastNameComboBox = new JComboBox<String>(tSomeNames);
   }

   /**
    * Creates row2: File: <text_box> <browse_button>
    */
   void createComponentsRow3()
   {
      _fileLabel = new JLabel("File:");

      // Create text field for filename.
      {
      _fileTextField = new JTextField(40);
      Dimension tDim = _fileTextField.getPreferredSize();
      tDim.width = 200;
      _fileTextField.setPreferredSize(tDim);
      _panel.add(_fileTextField);
      }

      // create the browse button
      {
      _fileBrowseButton = new JButton("Browse...");
      _fileBrowseButton.addActionListener(this);
      Dimension tDim = _fileTextField.getPreferredSize();
      tDim = _fileTextField.getPreferredSize();
      tDim.width += 60;
      _fileTextField.setPreferredSize(tDim);
      _panel.add(_fileBrowseButton);
      }
   }

   /**
    * Create the panel to hold the file text field/browse button combination.
    */
   JPanel createFilePane()
   {
      /*
       * Use a BoxLayout pane for the filename field and browse button.
       */
      JPanel tFilePane = new JPanel();
      tFilePane.setLayout(new BoxLayout(tFilePane, BoxLayout.LINE_AXIS));
      tFilePane.add(_fileTextField);
      tFilePane.add(Box.createRigidArea(new Dimension(5, 0)));
      tFilePane.add(_fileBrowseButton);

      return tFilePane;
   }

   /**
    * Create the ok and cancel button pane and its components.
    */
   JPanel createButtonPane()
   {
      // create the Ok button
      _okButton = new JButton("OK");
      _okButton.addActionListener(this);
      _panel.add(_okButton);

      // create the Cancel button
      _cancelButton = new JButton("Cancel");
      _cancelButton.addActionListener(this);
      _panel.add(_cancelButton);

      // make ok button size same as cancel size
      _okButton.setPreferredSize(_cancelButton.getPreferredSize());

      /*
       * Use a BoxLayout for the Ok/Cancel button panel. How to use BoxLayout:
       * https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
       */
      JPanel tButtonPane = new JPanel();
      tButtonPane.setLayout(new BoxLayout(tButtonPane, BoxLayout.LINE_AXIS));
      tButtonPane.add(Box.createHorizontalGlue());
      tButtonPane.add(_okButton);
      tButtonPane.add(Box.createRigidArea(new Dimension(25, 0)));
      tButtonPane.add(_cancelButton);
      tButtonPane.add(Box.createHorizontalGlue());

      /*
       * To enlarge the button pane height, add a rigid area with extra height
       * but zero width.
       * Note that we could have just modified the height of the rigid area
       * between the ok and cancel buttons accordingly, instead of adding the
       * zero width rigid area here.
       */
      Dimension tDim = _okButton.getPreferredSize();
      tDim.width = 0;
      tDim.height += 20;
      tButtonPane.add(Box.createRigidArea(tDim));
      return tButtonPane;
   }

   public void actionPerformed(ActionEvent e)
   {
      if (_okButton == e.getSource())
      {
         System.err.println("User chose OK.");
         _pressedOk = true;
         setVisible(false);
      }
      else if (_cancelButton == e.getSource())
      {
         System.err.println("User chose Cancel.");
         _pressedOk = false;
         setVisible(false);
      }
      else if (_fileBrowseButton == e.getSource())
      {
         System.err.println("User pressed file button.");
         chooseFile();
         _pressedOk = false;
      }
   }

   private void chooseFile()
   {

      JFileChooser chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File(_fileDir));
      int tOption = chooser.showOpenDialog(this);
      if (tOption == JFileChooser.APPROVE_OPTION)
      {
         String tFile = chooser.getSelectedFile().getPath();
         System.out.println("File selected: " + tFile);
         // load the trades
         _fileTextField.setText(tFile);
      }
      else
      {
         System.out.println("File selection canceled");
      }
   }

   public boolean pressedOk()
   {
      return _pressedOk;
   }

   public String getAccountName()
   {
      return (String) _firstNameComboBox.getSelectedItem();
   }

   public String getTradeFileName()
   {
      return _fileTextField.getText();
   }
}
