
public class Main {
	private static TextEditor editor; 
	
	public static void main(String[] args) {
		editor = new TextEditor(); 
		editor.setTitle("Pinyin Text Editor"); 
		// editor.setSize(800, 600);
		editor.setLocationRelativeTo(null);
		editor.setVisible(true);
	}

}
