import javax.swing.JDialog; 
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Vector;
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

public class Dialog extends JDialog implements ActionListener
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

    public Dialog(JFrame aOwner)
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

       getContentPane().setLayout(new BorderLayout());

       JPanel centerPnl = new JPanel();
       centerPnl.setLayout(new BorderLayout());
       JPanel gridLayoutPane = new JPanel(); 
       GridLayout gridLayout = new GridLayout(2,3);

       gridLayoutPane.setLayout(gridLayout);
       gridLayoutPane.add(new JLabel("Group:"));
       gridLayoutPane.add(_someComboBox);
       gridLayoutPane.add(new JLabel("Empty"));
       gridLayoutPane.add(new JLabel("File:"));
       gridLayoutPane.add(_filenameField);
       gridLayoutPane.add(_chooseFileButton);

       centerPnl.add(gridLayoutPane, BorderLayout.CENTER);
       //      centerPnl.add(new JLabel("Long label should be going here"), BorderLayout.SOUTH);

       add(centerPnl, BorderLayout.CENTER);

       JPanel bottomPanel = new JPanel();
       bottomPanel.add(_okButton);
       bottomPanel.add(_cancelButton);

       add(bottomPanel, BorderLayout.SOUTH);


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
