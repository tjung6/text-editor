package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextArea; 

public class TextEditorMenuBar extends JMenuBar implements ActionListener {
	JFrame frame;  
	private JFileChooser filechooser = new JFileChooser(); 
	
	private JTextArea textArea; 
	
	private String fontName = "Calibri"; 
	private int fontStyle = Font.PLAIN; 
	private int fontSize = 20; 

	public TextEditorMenuBar(JFrame frame, JTextArea textArea) {
		this.frame = frame; 
		this.textArea = textArea; 
		
		setBackground(Color.WHITE);
		
		JButton button = new JButton("Open");
		setupButton(button);
		button.addActionListener(this);
		button.setActionCommand("Open");
		
		JButton button2 = new JButton("Save");
		setupButton(button2); 
		button2.addActionListener(this);
				
		JButton button3 = new JButton("Font++");
		setupButton(button3); 
		button3.addActionListener(this);
		
		JButton button4 = new JButton("Font--");
		setupButton(button4); 
		button4.addActionListener(this); 
		
		JMenu help = new JMenu("Help"); 
		add(help); 
		help.add("After a syllable..."); 
		help.add("Press 1 for First Tone");
		help.add("Press 2 for Second Tone");
		help.add("Press 3 for Third Tone");
		help.add("Press 4 for Fourth Tone");
		help.addSeparator();
		help.add("For \u01DA, press 'u' followed by 5");
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getActionCommand().equals("Open")) {
			int retval = filechooser.showOpenDialog(frame);
			if (retval == JFileChooser.APPROVE_OPTION) {
				File f = filechooser.getSelectedFile(); 
				try {
					FileReader reader = new FileReader(f); 
					textArea.read(reader, ""); 
				} catch (IOException e) {
					System.out.println(e); 
					System.exit(1); 
				}
			}
		} else if (ev.getActionCommand().equals("Save")) {
			int retval = filechooser.showOpenDialog(frame);
			if (retval == JFileChooser.APPROVE_OPTION) {
				File f = filechooser.getSelectedFile(); 
				try {
					FileWriter writer = new FileWriter(f); 
					textArea.write(writer);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frame, e);
					System.exit(1);
				}
			}
		} else if (ev.getActionCommand().equals("Font++")) {
			fontSize += 4; 
			textArea.setFont(new Font(fontName, fontStyle, fontSize));
		} else if (ev.getActionCommand().equals("Font--")) {
			fontSize -= 4; 
			textArea.setFont(new Font(fontName, fontStyle, fontSize));
		}
	}
	
	public void setupButton(JButton b) {
		b.setBackground(Color.WHITE);
		b.setBorderPainted(false);
		add(b); 
	}
}


