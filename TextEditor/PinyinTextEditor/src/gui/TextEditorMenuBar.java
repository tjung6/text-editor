package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants; 

public class TextEditorMenuBar extends JMenuBar implements ActionListener {
	private JFrame frame;  
	private JFileChooser filechooser = new JFileChooser(); 
	private JTextArea textArea; 
	private JComboBox<Integer> fontComboBox; 
	
	private String fontName = "Calibri"; 
	private int fontStyle = Font.PLAIN;
	private int MIN_FONT_SIZE = 20; 
	private int MAX_FONT_SIZE = 100; 
	private int FONT_SIZE = MIN_FONT_SIZE;
	private int CHANGE_IN_FONT = 4; 
	
	// For KeyBindings: "Ctrl + s" to save to Desktop a file called "pinyin.txt" 
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

	public TextEditorMenuBar(JFrame frame, JTextArea textArea) {
		this.frame = frame; 
		this.textArea = textArea; 
		
		setBackground(Color.WHITE);
		
		JButton openButton = new JButton("Open");
		setupButton(openButton);
		openButton.addActionListener(this);
		openButton.setActionCommand("Open");
		
		JButton saveAsButton = new JButton("Save As");
		setupButton(saveAsButton); 
		saveAsButton.addActionListener(this);
		
		// This uses a JMenuItem and attaches a setAccelerator() to perform the action without actually 
		// have to click the menu item but by pressing "Ctrl + s" 
//		JMenuItem menuItem = new JMenuItem(""); 
//		add(menuItem);
		// menuItem.addActionListener(this);
//		menuItem.setActionCommand("Save");
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		
		getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK), "Save");
		getActionMap().put("Save", new SaveAction("Save", textArea));
		
		JButton fontIncButton = new JButton("Font++");
		setupButton(fontIncButton); 
		fontIncButton.addActionListener(this);
		
		JButton fontDecButton = new JButton("Font--");
		setupButton(fontDecButton); 
		fontDecButton.addActionListener(this); 
		
		DefaultComboBoxModel<Integer> fontModel = new DefaultComboBoxModel<Integer>();
		for (int i = MIN_FONT_SIZE; i <= MAX_FONT_SIZE; i += CHANGE_IN_FONT) {
			fontModel.addElement(i);
		}
		fontComboBox = new JComboBox<Integer>(fontModel);
		fontComboBox.setSelectedItem(FONT_SIZE);
		add(fontComboBox); 
		fontComboBox.addActionListener(this);
		
		add(new JSeparator(SwingConstants.VERTICAL)); 
		
		JMenu help = new JMenu("Help"); 
		add(help); 
		help.add("Default save location is to Desktop");
		help.addSeparator();
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
		} 
		// Attached to JMenuItem 'menuItem' 
//		else if(ev.getActionCommand().equals("Save")) {
//			File f = new File("C:/Users/Jung/Desktop/pinyin.txt");
//			try {
//				FileWriter writer = new FileWriter(f);
//				textArea.write(writer);
//			} catch (IOException e) {
//				e.printStackTrace();
//			} 
//		} 
		else if (ev.getActionCommand().equals("Save As")) {
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
		} else if (ev.getActionCommand().equals("Font++") && FONT_SIZE + CHANGE_IN_FONT <= MAX_FONT_SIZE) {
			FONT_SIZE += CHANGE_IN_FONT; 
			textArea.setFont(new Font(fontName, fontStyle, FONT_SIZE));
			fontComboBox.setSelectedItem(FONT_SIZE);
		} else if (ev.getActionCommand().equals("Font--") && FONT_SIZE - CHANGE_IN_FONT >= MIN_FONT_SIZE) {
			FONT_SIZE -= CHANGE_IN_FONT; 
			textArea.setFont(new Font(fontName, fontStyle, FONT_SIZE));
			fontComboBox.setSelectedItem(FONT_SIZE);
		} else {
			FONT_SIZE = (int) fontComboBox.getSelectedItem();
			textArea.setFont(new Font(fontName, fontStyle, FONT_SIZE));
		}
	}
	
	public void setupButton(JButton b) {
		b.setBackground(Color.WHITE);
		b.setBorderPainted(false);
		add(b); 
	} 
}


