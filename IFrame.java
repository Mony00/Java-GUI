package exlab10;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class IFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JTextField t1, t2;
	JComboBox cg;
	JButton b;
	JLabel l1, l2;

	@SuppressWarnings({ "unchecked", "rawtypes" })
IFrame(String title){
	  super(title);
	  this.setLayout(new GridBagLayout());
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  t1=new JTextField(30);
	  t2= new JTextField(10);
	  cg= new JComboBox();
	  cg.addActionListener(this);
	  cg.addItem("Sdic");
	  cg.addItem("AIC");
	  cg.addItem("CAD");
	  l1= new JLabel("Full Name");
	  l2= new JLabel("Group");
	  b= new JButton("Register");
	  t1.addKeyListener(new MyKeylistener());
	  t2.addKeyListener(new MyKeylistener2());
	  GridBagConstraints gbc = new GridBagConstraints();
	  gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(l1, gbc);
		gbc.gridx=1;
		gbc.gridy=0;
		this.add(t1, gbc);
		gbc.gridx=0;
		gbc.gridy=1;
		this.add(l2, gbc);
		gbc.gridx=1;
		gbc.gridy=1;
		this.add(t2, gbc);
		gbc.gridx=0;
		gbc.gridy=2;
		this.add(cg, gbc);
		gbc.gridx=0;
		gbc.gridy=3;
		this.add(b, gbc);
		b.addActionListener( this);
	  
  }
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==b) {
			try {
				File outputFile = new File("Result.txt");
					FileOutputStream fos = new FileOutputStream(outputFile);
					BufferedWriter bufout = new BufferedWriter(new OutputStreamWriter(fos));
					bufout.write(l1.getText()+": "+t1.getText()+"\n");
					bufout.write(l2.getText()+": "+t2.getText()+"\n");
					bufout.write("The selected discipline: "+cg.getItemAt(cg.getSelectedIndex()));
					bufout.close();
					fos.close();
					}
					catch(IOException e){
					System.out .println("Eroare: " + e.toString ());
					}
					}
					}
	
}

class MyKeylistener2 implements KeyListener {
	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent ke) {
		char c = ke.getKeyChar();
		if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE))
			ke.consume();
	}
}

class MyKeylistener implements KeyListener {
	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent evt) {
		 char c = evt.getKeyChar();
		      if(!(Character.isAlphabetic(c) ||  (c==KeyEvent.VK_BACK_SPACE)||  c==KeyEvent.VK_DELETE ))
		        evt.consume();
		 }
}


class Tester {
	public static void main(String[] args) {
		IFrame frame = new IFrame("inscriere");
		frame.setSize(500, 200);
		frame.setVisible(true);
	}
}

