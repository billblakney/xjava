import javax.swing.JDialog; 

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.io.File;

public class DialogBoxGridBagLayout extends JDialog implements ActionListener
{
    private JPanel _panel = null;

    /** Ok and Cancel buttons. */
    private JButton _okButton = null;
    private JButton _cancelButton = null;

    private JTextField _filenameField = null;
    private JButton _chooseFileButton = null;

    private JComboBox<String> _someComboBox = null;
    private boolean _pressedOk = false;
    private String _fileDir = null;

    public DialogBoxGridBagLayout(JFrame aOwner)
    {
       super(aOwner, true/*modal*/);

       _fileDir = ".";

       _panel = new JPanel();
       getContentPane().add(_panel);

       String[] tSomeNames = {"John", "Mary", "Sylvester" };
       _someComboBox = new JComboBox<String>(tSomeNames);
       _panel.add(_someComboBox);

       _filenameField = new JTextField(40);
       _panel.add(_filenameField);

       _chooseFileButton = new JButton("File...");
       _chooseFileButton.addActionListener(this);
       _panel.add(_chooseFileButton); 

       _okButton = new JButton("OK");
       _okButton.addActionListener(this);
       _panel.add(_okButton); 

       _cancelButton = new JButton("Cancel");
       _cancelButton.addActionListener(this);
       _panel.add(_cancelButton);  
       
       _okButton.setPreferredSize(_cancelButton.getPreferredSize());
       
       JPanel tLeftPane = new JPanel();
       tLeftPane.setLayout(new BoxLayout(tLeftPane, BoxLayout.PAGE_AXIS));
       JLabel label1 = new JLabel("Name");
       tLeftPane.add(label1);
       tLeftPane.add(Box.createRigidArea(new Dimension(0,5)));
       JLabel label2 = new JLabel("File");
       tLeftPane.add(label2);

       JPanel tFileGroupPane = new JPanel();
       tFileGroupPane.setLayout(new BoxLayout(tFileGroupPane, BoxLayout.LINE_AXIS));
       tFileGroupPane.add(_filenameField);
       tFileGroupPane.add(Box.createRigidArea(new Dimension(5,0)));
       tFileGroupPane.add(_chooseFileButton);
       
       JPanel tRightPane = new JPanel();
       tRightPane.setLayout(new BoxLayout(tRightPane, BoxLayout.PAGE_AXIS));
       tRightPane.add(_someComboBox);
       tRightPane.add(Box.createRigidArea(new Dimension(0,5)));
       tRightPane.add(tFileGroupPane);
       
       JPanel tTopPane = new JPanel();
       tTopPane.setLayout(new BoxLayout(tTopPane, BoxLayout.LINE_AXIS));
       tTopPane.add(tLeftPane);
       tTopPane.add(Box.createRigidArea(new Dimension(5,0)));
       tTopPane.add(tRightPane);

       JPanel tButtonPane = new JPanel();
       tButtonPane.setLayout(new BoxLayout(tButtonPane, BoxLayout.LINE_AXIS));
       tButtonPane.add(_okButton);
       tButtonPane.add(Box.createRigidArea(new Dimension(5,0)));
       tButtonPane.add(_cancelButton);

//       tFileGroupPane.add(listScroller);
//       tFileGroupPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
       Container contentPane = getContentPane();
       contentPane.add(tTopPane, BorderLayout.CENTER);
       contentPane.add(tButtonPane, BorderLayout.PAGE_END);

       pack();
       setLocationRelativeTo(aOwner);
       setSize(200,150);
       setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(_okButton == e.getSource()) {
            System.err.println("User chose OK.");
            _pressedOk = true;
            setVisible(false);
        }
        else if(_cancelButton == e.getSource()) {
            System.err.println("User chose Cancel.");
            _pressedOk = false;
            setVisible(false);
        }
        else if(_chooseFileButton == e.getSource()) {
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
    		_filenameField.setText(tFile);
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
    	return (String) _someComboBox.getSelectedItem();
    }
    
    public String getTradeFileName()
    {
    	return _filenameField.getText();
    }
}
