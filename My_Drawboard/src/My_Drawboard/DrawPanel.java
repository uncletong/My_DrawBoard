package My_Drawboard;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.*;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements ActionListener,MouseListener,MouseMotionListener{
	JMenuBar mbBar;//�˵���
	JMenu menu1,menu2,menu3;
	JMenuItem i1,i2,i3,i4;
	JPanel jp1;
	public JButton anj0,anj1,anj2,anj3,anj4,anj5,anj6,anj7,anj8,anj9,anj10;
	JLabel l1,lcolor;
	Vector<position> thedraw = new Vector<position>(); //��ͼ�켣
	int style = 0;
	int x1 = 0;
	int x2 = 0;
	int y1 = 0;
	int y2 = 0;
	String input = "";//������������
	java.awt.Color linecolor = java.awt.Color.BLACK;//Ĭ�Ϻ�ɫ
	
	public DrawPanel(){
		setBackground(java.awt.Color.WHITE);
		setLayout(new BorderLayout());
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));	
		//�Ϸ��˵�
		mbBar = new JMenuBar();
		mbBar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		add(mbBar,BorderLayout.NORTH);
		menu1 = new JMenu(" �ļ� ");
		menu2 = new JMenu(" �༭ ");
		menu3 = new JMenu(" ���� ");
		i1 = new JMenuItem(" �� ");
		i2 = new JMenuItem(" ���� ");
		i3 = new JMenuItem(" ��� ");
		i4 = new JMenuItem(" ���� ");
		menu1.add(i1);
		menu1.addSeparator();
		menu1.add(i2);
		menu2.add(i3);
		menu3.add(i4);
		mbBar.add(menu1);
		mbBar.add(menu2);
		mbBar.add(menu3);
		add(mbBar,BorderLayout.NORTH);
		//�෽������
		jp1 = new JPanel();
		jp1.setBackground(java.awt.Color.lightGray);
		jp1.setLayout(new BoxLayout(jp1, BoxLayout.Y_AXIS));
		jp1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		add(jp1,BorderLayout.WEST);
		//���������
		anj0 = new JButton("����");
		anj1 = new JButton("ˢ��");
		anj2 = new JButton("��Ƥ");
		anj3 = new JButton("����");
		anj4 = new JButton("ֱ��");
		anj5 = new JButton("����");
		anj6 = new JButton("Բ��");
		anj7 = new JButton("��Բ");
		anj10 = new JButton("");
		lcolor = new JLabel("��");
		l1 = new JLabel(" ��ɫ");
		anj10.add(lcolor);
		anj10.add(l1);
		jp1.add(anj0);
		jp1.add(anj1);
		jp1.add(anj2);
		jp1.add(anj3);
		jp1.add(anj4);
		jp1.add(anj5);
		jp1.add(anj6);
		jp1.add(anj7);
		jp1.add(anj10);
		//�¼�����
		i1.addActionListener(this);
		i2.addActionListener(this);
		i3.addActionListener(this);
		i4.addActionListener(this);
		anj0.addActionListener(this);
		anj1.addActionListener(this);
		anj2.addActionListener(this);
		anj3.addActionListener(this);
		anj4.addActionListener(this);
		anj5.addActionListener(this);
		anj6.addActionListener(this);
		anj7.addActionListener(this);
		anj10.addActionListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		}
	
	//��¼���ѡ��
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == anj0) {
			style = 0;
		}
		else if (e.getSource() == anj1){
			style = 1;
		}
		else if (e.getSource() == anj2){
			Cursor cousor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("image/eare.jpg").getImage(), new Point(10,20), "stick");
			setCursor(cousor);
			style = 2;
		}
		else if (e.getSource() == anj3){
			style = 3;
			input = JOptionPane.showInputDialog("�������ֺ��ڻ�����λ�ã�");
		}
		else if (e.getSource() == anj4){
			style = 4;
		}
		else if (e.getSource() == anj5){
			style = 5;
		}
		else if (e.getSource() == anj6){
			style = 6;
		}
		else if (e.getSource() == anj7){
			style = 7;
		}
		else if (e.getSource() == anj10){
			linecolor = JColorChooser.showDialog(null, "��ѡ����ɫ", java.awt.Color.BLACK);
			lcolor.setForeground(linecolor);
		}
		else if (e.getActionCommand().equals("����")){
			JOptionPane.showMessageDialog(null, "uncletong ����̽","����",JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getActionCommand().equals("���")){
			thedraw.removeAllElements();
		}
		else if (e.getActionCommand().equals("����")){
			JFileChooser sfc = new JFileChooser();
			int flag = -1;
			//��ȡ�ļ�·��
			try {
				flag = sfc.showSaveDialog(this);
			} catch (HeadlessException he) {
				System.out.println("Save File Dialog Exception!");
			}
			if (flag == JFileChooser.APPROVE_OPTION){
				String filename =  sfc.getSelectedFile().getPath();
				try {
					FileOutputStream fos = new FileOutputStream(filename);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(thedraw);
					oos.close();
				} 
				catch (Exception es) {
					System.out.println(es);
				}
			}
		}
		else if (e.getActionCommand().equals("��")){
			JFileChooser ofc = new JFileChooser();
			int flag1 = -1;
			try {
				flag1 = ofc.showOpenDialog(this);
			} catch (HeadlessException he) {
				System.out.println("Save File Dialog Exception!");
			}
			// ��ȡѡ���ļ���·��
			if (flag1 == JFileChooser.APPROVE_OPTION) {
				String filename = ofc.getSelectedFile().getPath();
				try {
					FileInputStream fiS = new FileInputStream(filename);
					ObjectInputStream ois = new ObjectInputStream(fiS);
					thedraw = (Vector<position>)ois.readObject();
					ois.close();
				} 
				catch (Exception es) {
					System.out.println(es);
				}
			}
		}
		repaint();
	}
	
	//���ƻ滭���ܳ�������
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		draw((Graphics2D) g);
	}
	
	//��ͼ
	public void draw(Graphics2D g) {
		int n = thedraw.size();
		position p;
		for (int i = 0; i < n; i++) {
			try {
				p = thedraw.get(i);
				if (p.type == 0) {
					//����
					x1 = x2 = p.x;
					y1 = y2 = p.y;
					while(p.type == 0){
						x2 = p.x;
						y2 = p.y;
						Line2D t = new Line2D.Double(x1, y1, x2, y2);
						g.setColor(p.color);
						g.draw(t);
						i++;
						if(i == n){
							i--;						
							break;
						}
						p = thedraw.get(i);
						x1 = x2;
						y1 = y2;
					}
				}
				if(p.type == 1){
					//ˢ��
					while(p.type == 1){
						g.setColor(p.color);
						g.drawString("��",p.x,p.y);
						i++;
						if(i == n){
							i--;
							break;
						}
						p = thedraw.get(i);
					}
				}
				if(p.type == 2){
					//��Ƥ
					while(p.type == 2){
						g.setColor(java.awt.Color.WHITE);
						g.drawString("��",p.x,p.y);
						i++;
						if(i == n){
							i--;
							break;
						}
						p = thedraw.get(i);
					}
				}
				if(p.type == 3){
					//����
					while(p.type == 3){
						g.setColor(p.color);
						g.drawString(p.s,p.x,p.y);
						i++;
						if(i == n){
							i--;
							break;
						}
						p = thedraw.get(i);
					}
				}
				if(p.type == 4){
					//ֱ��
					x1 = p.x;
					y1 = p.y;
					i++;
					p = thedraw.get(i);
					x2 = p.x;
					y2 = p.y;
					if(p.type == 4){
						Line2D t = new Line2D.Double(x1,y1,x2,y2);
						g.setColor(p.color);
						g.draw(t);
						thedraw.remove(i);
					}
					else if(p.type == -1){
						Line2D t = new Line2D.Double(x1,y1,x2,y2);
						g.setColor(p.color);
						g.draw(t);
					}
					else i--;
				}
				if(p.type == 5){
					//����
					x1 = p.x;
					y1 = p.y;
					i++;
					p = thedraw.get(i);
					x2 = p.x;
					y2 = p.y;
					if(x2 < x1){
						int temp;
						temp = x1;
						x1 = x2;
						x2 = temp;
					}
					if(y2 < y1){
						int temp;
						temp = y1;
						y1 = y2;
						y2 =temp;
					}
					if(p.type == 5){
						//��̬�仯
						Rectangle2D t = new Rectangle2D.Double(x1,y1,x2-x1,y2-y1);
						g.setColor(p.color);
						g.draw(t);
						thedraw.remove(i);
					}
					else if(p.type == -1){
						Rectangle2D t = new Rectangle2D.Double(x1,y1,x2-x1,y2-y1);
						g.setColor(p.color);
						g.draw(t);
					}
					else
						i--;
				}
				if(p.type == 6){
					//Բ��
					x1 = p.x;
					y1 = p.y;
					i++;
					thedraw.get(i);
					x2 = p.x;
					y2 = p.y;
					if(x2 < x1){
						int temp;
						temp = x1;
						x1 = x2;
						x2 = temp;
					}
					if(y2 < y1){
						int temp;
						temp = y1;
						y1 = y2;
						y2 =temp;
					}
					if(p.type == 6){
						RoundRectangle2D t = new RoundRectangle2D.Double(x1, y1, x2-x1, y2-y1, 20, 20);
						g.setColor(p.color);
						g.draw(t);
						thedraw.remove(i);
					}
					else if(p.type == -1){
						RoundRectangle2D t = new RoundRectangle2D.Double(x1, y1, x2-x1, y2-y1, 20, 20);
						g.setColor(p.color);
						g.draw(t);
					}
					else
						i--;
				}
				if(p.type == 7){
					//��Բ
					x1 = p.x;
					y1 = p.y;
					i++;
					p = thedraw.get(i);
					x2 = p.x;
					y2 = p.y;
					if(x2 < x1){
						int temp;
						temp = x1;
						x1 = x2;
						x2 = temp;
					}
					if(y2 < y1){
						int temp;
						temp = y1;
						y1 = y2;
						y2 =temp;
					}
					if(p.type == 7){
						Ellipse2D t =new Ellipse2D.Double(x1,y1,x2-x1,y2-y1);
						g.setColor(p.color);
						g.draw(t);
						thedraw.remove(i);
					}
					else if(p.type == -1){
						Ellipse2D t =new Ellipse2D.Double(x1,y1,x2-x1,y2-y1);
						g.setColor(p.color);
						g.draw(t);
					}
					else i--;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
		position p = new position();
		p.x = e.getX();
		p.y = e.getY();
		p.type = style;
		p.s = input;
		p.color = linecolor;
		thedraw.add(p);
	}
	
	public void mouseExited(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		position p = new position();
		p.x = e.getX();
		p.y = e.getY();
		p.type = -1;
		p.s = input;
		p.color = linecolor;
		thedraw.add(p);
		repaint();
	}
	
	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		position p = new position();
		p.x = e.getX();
		p.y = e.getY();
		if (style == 3)
			p.type = -1;
		else
			p.type = style;
		p.s = input;
		p.color = linecolor;
		thedraw.add(p);
		repaint();
	}
}
	
