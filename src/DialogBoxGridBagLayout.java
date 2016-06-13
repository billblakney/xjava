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

    /** Ok and Cancel buttons. */
    private JButton _buttonOk = null;
    private JButton _buttonCancel = null;

    private JTextField _textFieldFile = null;
    private JButton _buttonBrowse = null;
    
    private JLabel _labelName = null;
    private JLabel _labelFile = null;

    private JComboBox<String> _comboBoxName = null;
    private boolean _pressedOk = false;
    private String _fileDir = null;

    public DialogBoxGridBagLayout(JFrame aOwner)
    {
       super(aOwner, true/*modal*/);
       
       Dimension tDim;

       _fileDir = ".";

       _panel = new JPanel();
       getContentPane().add(_panel);

       /*
        * Create the components.
        */
       String[] tSomeNames = {"John", "Mary", "Sylvester" };
       _comboBoxName = new JComboBox<String>(tSomeNames);
       _panel.add(_comboBoxName);

       /*
        * Create text field for filename.
        */
       _textFieldFile = new JTextField(40);
       tDim = _textFieldFile.getPreferredSize();
       tDim.width = 200;
       _textFieldFile.setPreferredSize(tDim);
       _panel.add(_textFieldFile);

       /*
        * Create the browse button.
        */
       _buttonBrowse = new JButton("Browse...");
       _buttonBrowse.addActionListener(this);
       tDim = _textFieldFile.getPreferredSize();
       tDim.width += 60;
       _textFieldFile.setPreferredSize(tDim);
       _panel.add(_buttonBrowse); 
       
       /*
        * Create the Name and File labels.
        */
       _labelName = new JLabel("Name:");
       _labelFile = new JLabel("File:");

       /*
        * Create the Ok and Cancel buttons.
        */
       _buttonOk = new JButton("OK");
       _buttonOk.addActionListener(this);
       _panel.add(_buttonOk); 

       _buttonCancel = new JButton("Cancel");
       _buttonCancel.addActionListener(this);
       _panel.add(_buttonCancel);  
       
       _buttonOk.setPreferredSize(_buttonCancel.getPreferredSize());
       
       /*
        * Use a BoxLayout pane for the filename field and browse button.
        */
       JPanel tFilePane = new JPanel();
       tFilePane.setLayout(new BoxLayout(tFilePane,BoxLayout.LINE_AXIS));
       tFilePane.add(_textFieldFile);
       tFilePane.add(Box.createRigidArea(new Dimension(5,0)));
       tFilePane.add(_buttonBrowse);
       
       /*
        * Use a GridBagLayout for the upper settings area.
        * How to use GridBagLayout:
        * https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
        */
       JPanel tGridPane = new JPanel(new GridBagLayout());
       GridBagConstraints c = new GridBagConstraints();

       // common settings for column 1
       c.fill = GridBagConstraints.NONE; // use natural size
       c.anchor = GridBagConstraints.LINE_END; // right align

       // buffer on top of first row
       int tTopEdgeSpace = 20;
       // buffer at bottom top of last row
       int tBottomEdgeSpace = 10;
       // buffer at right edge of last column
       int tRightEdgeSpace = 20;
       // buffer at left edge of first column
       int tLeftEdgeSpace = 10;
       
       // settings for name label
       c.gridx = 0;
       c.gridy = 0;
       c.insets = new Insets(tTopEdgeSpace,tLeftEdgeSpace,5,5);
       tGridPane.add(_labelName, c);

       // settings for file label
       c.gridx = 0;
       c.gridy = 1;
       c.insets = new Insets(5,tLeftEdgeSpace,tBottomEdgeSpace,5);
       tGridPane.add(_labelFile, c);

       // common settings for column w
       c.anchor = GridBagConstraints.LINE_START; // left align

       // settings for name combobox
       c.gridx = 1;
       c.gridy = 0;
       c.weightx = 0.25;
       c.insets = new Insets(tTopEdgeSpace,5,5,tRightEdgeSpace);
       tGridPane.add(_comboBoxName, c);

       // settings for file area
       c.fill = GridBagConstraints.HORIZONTAL; // stretch to fill horizontal

       c.gridx = 1;
       c.gridy = 1;
       c.weightx = 0.95;
       c.insets = new Insets(5,5,tBottomEdgeSpace,tRightEdgeSpace);
       tGridPane.add(tFilePane, c);

       /*
        * Use a BoxLayout for the Ok/Cancel button panel.
        * How to use BoxLayout:
        * https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
        */
       JPanel tButtonPane = new JPanel();
       tButtonPane.add(Box.createHorizontalGlue());
       tButtonPane.setLayout(new BoxLayout(tButtonPane, BoxLayout.LINE_AXIS));
       tButtonPane.add(_buttonOk);
       tButtonPane.add(Box.createRigidArea(new Dimension(25,0)));
       tButtonPane.add(_buttonCancel);
       tButtonPane.add(Box.createHorizontalGlue());

       tDim = _buttonOk.getPreferredSize();
       tDim.height += 20;
       tButtonPane.add(Box.createRigidArea(tDim));

       /*
        * Add the upper grid pane and Ok/Cancel buttons pane to the main pane.
        */
       Container contentPane = getContentPane();
       contentPane.add(tGridPane, BorderLayout.CENTER);
       contentPane.add(tButtonPane, BorderLayout.PAGE_END);

       pack();
       setLocationRelativeTo(aOwner);
       setSize(400,160);
       setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(_buttonOk == e.getSource()) {
            System.err.println("User chose OK.");
            _pressedOk = true;
            setVisible(false);
        }
        else if(_buttonCancel == e.getSource()) {
            System.err.println("User chose Cancel.");
            _pressedOk = false;
            setVisible(false);
        }
        else if(_buttonBrowse == e.getSource()) {
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
    	if (tOption == JFileChooser.APPROVE_OPTION) {
    		String tFile = chooser.getSelectedFile().getPath();
    		System.out.println("File selected: " + tFile);
    		// load the trades
    		_textFieldFile.setText(tFile);
    	} else {
    		System.out.println("File selection canceled");
    	}
    }

    public boolean pressedOk()
    {
    	return _pressedOk;
    }
    
    public String getAccountName()
    {
    	return (String) _comboBoxName.getSelectedItem();
    }
    
    public String getTradeFileName()
    {
    	return _textFieldFile.getText();
    }
}
