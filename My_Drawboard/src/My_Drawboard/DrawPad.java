package My_Drawboard;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class DrawPad {
	public static void main(String[] args){
		try {
			for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
				if("Nimubs".equals(info.getName())){
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		new DrawFrame();
	}

}
