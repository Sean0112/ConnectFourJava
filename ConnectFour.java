import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConnectFour extends JFrame{
	
	public ConnectFour() {
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		DrawPanel d = new DrawPanel();
		this.getContentPane().add(d);
		this.setSize(900,800);
		this.setVisible(true);
		addMouseListener(d);	
	}
	
	public static void main(String[] args) {
		new ConnectFour();
	}
}

class DrawPanel extends JPanel implements MouseListener{
	
	static int[][] board = new int[6][7];
	static int count = -1;
	ArrayList<Coin> coins = new ArrayList<>(); 
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(10));
		for (int i = 1; i <= 8; i++)
            g2.drawLine(100 * i, 100, 100 * i, 700);
        for (int i = 1; i <= 7; i++)
            g2.drawLine(100, 100 * i, 800, 100 * i);
        if(count >= 0)
        	play(g);
	}
	
	public void play(Graphics g) {
		for(Coin c : coins) {
			g.setColor(c.color);
			int x = (c.column + 1) * 100 + 10;
			int y = (c.row + 1) * 100 + 10;
			if( y != 10)
				g.fillOval(x, y, 80, 80);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		if(e.getClickCount() >= 2) {
			int x = MouseInfo.getPointerInfo().getLocation().x;
			//System.out.println(x);
			if(x >= 808) x -= 100;
			Coin c = new Coin((x-108)/100);
			coins.add(c);
			count++;
			repaint();
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static int getCount() {
		return count;
	}
}

class Coin{
	
	int row; //make sure row does not equal -1
	int column;
	Color color;
	
	public Coin(int column) {
		this.column = column;
		this.row = getRow();
		this.color = getColor();
	}
	
	public int getRow() {
		int r = -1;
		for(int row = 5; row >= 0; row--) {
			if(DrawPanel.board[row][column] == 0) {
				if(DrawPanel.getCount() % 2 == 0)
					DrawPanel.board[row][column] = 1; 
				else
					DrawPanel.board[row][column] = 2;
				r = row;
				break;
			}
		}
		return r;
	}
	
	public Color getColor() {
		if(DrawPanel.getCount() % 2 == 0) {
			return Color.red;
		}
		else {
			return Color.yellow;
		}
	}
	
}
