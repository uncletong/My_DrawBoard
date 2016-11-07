package My_Drawboard;

import javax.swing.JFrame;

public class DrawFrame extends JFrame{
	public DrawFrame() {
		// TODO Auto-generated constructor stub
		DrawPanel panel = new DrawPanel();
		add(panel);
		setTitle("tong's DrawPad");
		setBounds(100,100,800,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
