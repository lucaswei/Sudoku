import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class MyLabel extends JLabel implements MouseListener, KeyListener{
	private SudokuScreen frame;
	private int x;
	private int y;
	public boolean isFixed;
	private Color tempColor;
	private Color FIX;
	private Color HOVER;
	private Color HIGHLIGHT;
	private Color CLICK;
	private Color BACK;

	public MyLabel(SudokuScreen frame){
		super();
		this.frame = frame;
		FIX       = Color.GRAY;
		HOVER     = Color.RED;
		HIGHLIGHT = Color.ORANGE;
		CLICK     = Color.YELLOW;
		BACK      = Color.WHITE;
		setHorizontalAlignment(JLabel.CENTER);
		setOpaque(true);
		setBackground(BACK);
		addMouseListener(this);
		addKeyListener(this);
	}
	public void setPoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void setItem(int item){
		if(item >0 && item <10 && !isFixed)
			setText(Integer.toString(item));
	}
	public void setFixed(boolean toggle){
		setBackground(FIX);
		isFixed = toggle;
		removeMouseListener(this);
		removeKeyListener(this);
	}
	public void onClick(){
		if(!isFixed){
			setBackground(CLICK);
		}
	}
	public void highLight(){
		setBackground(HIGHLIGHT);
	}
	public void clear(){
		if(!isFixed){
			setBackground(BACK);
		}
	}
	private void hover(boolean isHover){
		if(isFixed){
			return;
		}
		if(isHover){
			tempColor = getBackground();
			setBackground(HOVER);
		}else{
			setBackground(tempColor);
		}
	}
	public void mouseClicked(MouseEvent e){
		frame.onClick(x, y);
		requestFocus();
	}
	public void mouseEntered(MouseEvent e){
		hover(true);
	}
	public void mouseExited(MouseEvent e){
		hover(false);
	}
	public void mouseReleased(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){
	}
	public void keyTyped(KeyEvent e){
		char keyChar = e.getKeyChar();
		switch(keyChar){
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '0':
				frame.setItem(x, y, keyChar-'0');
				break;
			default:
		}
	}
}

