import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*; 

public class TextEditor extends JFrame {
	private JTextArea textArea = new JTextArea(); 
	private JFileChooser filechooser = new JFileChooser(); 
	private Action open = new openAction(); 
	private Action save = new saveAction();	
	private Action incFont = new biggerFont();
	private Action decFont = new smallerFont(); 
	private Map<Integer, String> accents; 
	
	private String fontName = "Calibri"; 
	private int fontStyle = Font.PLAIN; 
	private int fontSize = 20; 

	public TextEditor() {
		accents = new HashMap<Integer, String>(); 
		setUpTones(); 
		
		textArea = new JTextArea(15, 60);
		textArea.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		// Sets font style & size 
		textArea.setFont(new Font(fontName, fontStyle, fontSize));
		// Sets background color 
		textArea.setBackground(Color.BLACK);
		// Sets font color 
		textArea.setForeground(Color.WHITE);
		JScrollPane scrolltext = new JScrollPane(textArea); 
		
		JPanel content = new JPanel(); 
		content.setLayout(new BorderLayout()); 
		content.add(scrolltext, BorderLayout.CENTER); 
		JMenuBar menubar = new JMenuBar(); 
		setContentPane(content); 
		setJMenuBar(menubar); 
		
		JButton button = new JButton("Open");
		menubar.add(button); 
		button.addActionListener(open);
		
		JButton button2 = new JButton("Save");
		menubar.add(button2); 
		button2.addActionListener(save);
				
		JButton button3 = new JButton("Font++"); 
		menubar.add(button3); 
		button3.addActionListener(incFont);
		
		JButton button4 = new JButton("Font--"); 
		menubar.add(button4); 
		button4.addActionListener(decFont);
		
		JMenu help = new JMenu("Help"); 
		menubar.add(help); 
		help.add("Before a vowel..."); 
		help.add("Press 1 for First Tone");
		help.add("Press 2 for Second Tone");
		help.add("Press 3 for Third Tone");
		help.add("Press 4 for Fourth Tone");
		help.addSeparator();
		help.add("For \u01DA, press 'u' followed by 5"); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		pack(); 
		setLocationRelativeTo(null); 
		setVisible(true); 
		
		textArea.addKeyListener(new AL());
	}
	
	class openAction extends AbstractAction {
		public openAction() {
			super("Open..."); 
			putValue(MNEMONIC_KEY, new Integer('0')); 
		}
		
		public void actionPerformed(ActionEvent arg0) {
			int retval = filechooser.showOpenDialog(TextEditor.this);
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
	}
	
	class saveAction extends AbstractAction {
		public saveAction() {
			super("Save"); 
			putValue(MNEMONIC_KEY, new Integer('S')); 
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int retval = filechooser.showOpenDialog(TextEditor.this);
			if (retval == JFileChooser.APPROVE_OPTION) {
				File f = filechooser.getSelectedFile(); 
				try {
					FileWriter writer = new FileWriter(f); 
					textArea.write(writer);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(TextEditor.this, e);
					System.exit(1);
				}
			}
		}
	}
	
	class biggerFont extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			fontSize += 4; 
			textArea.setFont(new Font(fontName, fontStyle, fontSize));
		}
	}
	
	class smallerFont extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			fontSize -= 4; 
			textArea.setFont(new Font(fontName, fontStyle, fontSize));
		}
	}
	
	// extends KeyAdapter ?? 
	class AL implements KeyListener {
		public void keyPressed(KeyEvent e) {

		}
		
		public void keyReleased(KeyEvent e) {

		}

		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() >= '1' && e.getKeyChar() <= '4' && textArea.getText().length() != 0) {
				char letter = textArea.getText().charAt(textArea.getText().length() - 1);
				if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') {
					textArea.setText("" + textArea.getText().substring(0, textArea.getText().length() - 1));
					textArea.append(accents.get(letter + e.getKeyChar())); 
					e.consume();
				}
			}
		}
	}
	
	// When typing Pinyin, user will want a specific vowel to have
	// a specific tone 
	// 1 	First Tone
	// 2	Second Tone
	// 3 	Third Tone 
	// 4	Fourth Tone 
	// "u" is an exception where there is a fifth tone used under
	// special circumstances 
	public void setUpTones() {
		accents.put('a' + '1', "\u0101"); 
		accents.put('a' + '2', "\u00E1"); 
		accents.put('a' + '3', "\u01CE"); 
		accents.put('a' + '4', "\u00E0"); 

		accents.put('e' + '1', "\u0113"); 
		accents.put('e' + '2', "\u00E9"); 
		accents.put('e' + '3', "\u011B"); 
		accents.put('e' + '4', "\u00E8");
		
		accents.put('i' + '1', "\u012B"); 
		accents.put('i' + '2', "\u00ED"); 
		accents.put('i' + '3', "\u01D0"); 
		accents.put('i' + '4', "\u00EC");
		
		accents.put('o' + '1', "\u014D"); 
		accents.put('o' + '2', "\u00F3"); 
		accents.put('o' + '3', "\u01D2"); 
		accents.put('o' + '4', "\u00F2");
		
		accents.put('u' + '1', "\u016B"); 
		accents.put('u' + '2', "\u00FA"); 
		accents.put('u' + '3', "\u01D4"); 
		accents.put('u' + '4', "\u00F9");
		accents.put('u' + '5', "\u01DA");
	}
}
