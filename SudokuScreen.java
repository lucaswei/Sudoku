import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class SudokuScreen extends JFrame{
	private JFrame jFrame;
	private JLabel[][] boardLabel;
	private Color color;
	private Sudoku sudoku;

	private static final int WIDTH = 300;
	private static final int HEIGHT = 400;
	private static final int GAP = 3;
	private static final int LABEL_WIDTH = 30;
	private static final int LABEL_HEIGHT = 30;

	public SudokuScreen(Sudoku sudoku){
		super("Sudoku");
		boardLabel = new JLabel[9][9];
		this.sudoku = sudoku;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		buildPanelBoard();
		pack();
		buildItem();
	}
	private void buildPanelBoard(){
		JPanel gameBoard = new JPanel(new GridLayout(9, 9, GAP, GAP));
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				boardLabel[i][j] = new JLabel();
				boardLabel[i][j].setHorizontalAlignment(JLabel.CENTER);
				ControlListener listener = new ControlListener(this, i, j);
				boardLabel[i][j].addMouseListener(listener);
				boardLabel[i][j].addKeyListener(listener);
				boardLabel[i][j].setOpaque(true);
				boardLabel[i][j].setBackground(Color.WHITE);
				boardLabel[i][j].setPreferredSize(new Dimension(LABEL_WIDTH,LABEL_HEIGHT));
				gameBoard.add(boardLabel[i][j]);
			}
		}
		this.add(gameBoard,BorderLayout.CENTER);

		JPanel southButtonPanel = new JPanel(new FlowLayout());
		JButton submit = new JButton("submit");
		submit.addActionListener(new ButtonListener(this));
		southButtonPanel.add(submit);
		this.add(southButtonPanel,BorderLayout.SOUTH);
		
		JPanel northButtonPanel = new JPanel(new FlowLayout());
		JButton save = new JButton("save");
		save.addActionListener(new ButtonListener(this));
		northButtonPanel.add(save);
		JButton load = new JButton("load");
		load.addActionListener(new ButtonListener(this));
		northButtonPanel.add(load);
		JButton newBoard = new JButton("newBoard");
		newBoard.addActionListener(new ButtonListener(this));
		northButtonPanel.add(newBoard);
		this.add(northButtonPanel,BorderLayout.NORTH);
	}
	public void clickedItem(int x, int y){
		if(boardLabel[x][y].getBackground() == Color.GRAY){
			return;
		}
		int areaX,areaY;
		areaX = (x/3)*3;
		areaY = (y/3)*3;
		clear();
		for (int i=0; i<9; i++) {
			boardLabel[x][i].setBackground(Color.ORANGE);
			boardLabel[i][y].setBackground(Color.ORANGE);
			boardLabel[areaX+i/3][areaY+i%3].setBackground(Color.ORANGE);
		}
	}
	public void setItem(int x, int y, int item){
		if(boardLabel[x][y].getBackground() == Color.GRAY){
			return;
		}
		if(item != 0){
			boardLabel[x][y].setText(Integer.toString(item));
		}else
			boardLabel[x][y].setText("");

	}
	public void hover(int x, int y, boolean isOn){
		if(isOn){
			color = boardLabel[x][y].getBackground();
			boardLabel[x][y].setBackground(Color.RED);
		}else{
			boardLabel[x][y].setBackground(color);
		}
	}
	private void clear(){
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				boardLabel[i][j].setBackground(Color.WHITE);
			}
		}
	}
	private void buildItem(){
		int[][] board;
		int item;
		board = sudoku.getBoard();
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				item = board[i][j];
				if(item != 0){
					boardLabel[i][j].setText(Integer.toString(item));
					boardLabel[i][j].setBackground(Color.GRAY);
				}
			}
		}
	}
}

