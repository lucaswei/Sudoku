import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
class ControlListener implements MouseListener,KeyListener{
	private SudokuScreen frame;
	private int x;
	private int y;

	public ControlListener(SudokuScreen frame, int x, int y){
		this.x = x;
		this.y = y;
		this.frame = frame;
	}
	public void mouseClicked(MouseEvent e){
		Component source = (Component)e.getSource();
		frame.clickedItem(x, y);
		source.requestFocus();
	}
	public void mouseEntered(MouseEvent e){
		frame.hover(x, y, true);	
	}
	public void mouseExited(MouseEvent e){
		frame.hover(x, y, false);
	}
	public void mouseReleased(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void keyPressed(KeyEvent e){
	}
	public void keyReleased(KeyEvent e){
	}
	public void keyTyped(KeyEvent e){
		char keyChar = e.getKeyChar();
		switch(keyChar){
			case '1':
				frame.setItem(x, y, 1);
				break;
			case '2':
				frame.setItem(x, y, 2);
				break;
			case '3':
				frame.setItem(x, y, 3);
				break;
			case '4':
				frame.setItem(x, y, 4);
				break;
			case '5':
				frame.setItem(x, y, 5);
				break;
			case '6':
				frame.setItem(x, y, 6);
				break;
			case '7':
				frame.setItem(x, y, 7);
				break;
			case '8':
				frame.setItem(x, y, 8);
				break;
			case '9':
				frame.setItem(x, y, 9);
				break;
			default:
				frame.setItem(x, y, 0);
				break;
		}
	}
}
class ButtonListener implements ActionListener{
	SudokuScreen screen;
	public ButtonListener(SudokuScreen screen){
		this.screen = screen;
	}
	public void actionPerformed(ActionEvent e){
		System.out.print(e.getActionCommand());
	}
}
