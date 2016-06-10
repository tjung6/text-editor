package gui;
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.HashMap;
import javax.swing.*;

public class TextEditorMain {		
	public TextEditorMain() {	
		TextEditorArea textArea = new TextEditorArea(); 
				
		JFrame frame = new JFrame(); 
		frame.setPreferredSize(new Dimension(800, 800));
		
		JPanel content = new JPanel(); 
		content.setLayout(new BorderLayout()); 
		content.add(new JScrollPane(textArea), BorderLayout.CENTER); 
		frame.setContentPane(content); 
		
		frame.setJMenuBar(new TextEditorMenuBar(frame, textArea)); 
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.pack(); 
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TextEditorMain(); 
	}
		
}
