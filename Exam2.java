package exlab10;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


class Examtest extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel p1, p2, p3, p4, p5;
	JButton b1, b2, filebutton;
	JTextField t1, t2, t3, t4, t5, t6;
	JRadioButton r1, r2, r3;
	ButtonGroup cg;
	JLabel l1, l2;
	int a,b,c=0,f,g,h=0;

	Examtest(String title) {
		super(title);
		setLayout(new BorderLayout());
		p1 = new JPanel();
		b1 = new JButton("Sum");
		t1 = new JTextField("0", 10);
		t2 = new JTextField("0", 10);
		t3 = new JTextField(10);
		p1.add(b1);
		p1.add(t1);
		p1.add(t2);
		p1.add(t3);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String s1=t1.getText();
				String s2= t2.getText();
				if(isNumeric(s1))
					a=Integer.parseInt(s1);
				else a=0;
				if(isNumeric(s2))
					b=Integer.parseInt(s2);
				c=a+b;
				t3.setText(String.valueOf(c));
				l2.setText(String.valueOf(c));
			}
		});

		p2 = new JPanel();
		b2 = new JButton("Diff");
		t4 = new JTextField("0", 10);
		t5 = new JTextField("0", 10);
		t6 = new JTextField(10);
		p2.add(b2);
		p2.add(t4);
		p2.add(t5);
		p2.add(t6);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String s3=t4.getText();
				String s4=t5.getText();
				if(isNumeric(s3))
					f=Integer.parseInt(s3);
				else f=0;
				if(isNumeric(s4))
					g=Integer.parseInt(s4);
				else g=0;
				h=f-g;
				
				t6.setText(String.valueOf(h));
				l1.setText(String.valueOf(h)); 
			}
		});

		p3 = new JPanel();
		r1 = new JRadioButton("Window", true);
		r2 = new JRadioButton("Metal", false);
		r3 = new JRadioButton("Motif LAF", false);
		cg = new ButtonGroup();
		cg.add(r1);
		cg.add(r2);
		cg.add(r3);
		p3.add(r1);
		p3.add(r2);
		p3.add(r3);
		r1.addActionListener(this);
		r2.addActionListener(this);
		r3.addActionListener(this);

		p4 = new JPanel();
		l1 = new JLabel("Diff sol");
		l2 = new JLabel("Sum sol");
		p4.add(l1);
		p4.add(l2);

		p5 = new JPanel();
		filebutton = new JButton("Save into File");
		p5.add(filebutton);
		filebutton.addActionListener(this);

		this.add(p1, BorderLayout.NORTH);
		this.add(p2, BorderLayout.SOUTH);
		this.add(p3, BorderLayout.CENTER);
		this.add(p4, BorderLayout.EAST);
		this.add(p5, BorderLayout.WEST);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae) {
		String Laf = null;
		if(ae.getSource()==r1)
			Laf="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		else if(ae.getSource()==r2)
			Laf="javax.swing.plaf.metal.MetalLookAndFeel";
		else if(ae.getSource()==r3)
			Laf="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		try {
			UIManager.setLookAndFeel(Laf);
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception e) {
			System.out.println("we got a problem "+e);
		}
		if(ae.getSource()==filebutton) {
			try (FileWriter f= new FileWriter("Results.txt", true);){
				f.write("sum = " +l2.getText()+"\n");
				f.write("diff= "+l1.getText()+"\n" );
			}catch(IOException e) {
				System.out.println("Problem with file"+e);
			}
		}
	}
	public static boolean isNumeric(String str) {
		for(char c:str.toCharArray())
			if(Character.isDigit(c))
				return true;
			 return false;
	}
}

public class Exam2 {
	public static void main(String[] args) {

		Examtest frame = new Examtest("Application");
		frame.setSize(600, 200);
		frame.setVisible(true);

	}
}