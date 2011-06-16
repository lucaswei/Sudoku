import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class SudokuScreen extends JFrame{
	private JFrame jFrame;
	private MyLabel[][] boardLabel;
	private Color color;

	public Sudoku sudoku;

	private static final int WIDTH = 300;
	private static final int HEIGHT = 400;
	private static final int GAP = 3;
	private static final int LABEL_WIDTH = 30;
	private static final int LABEL_HEIGHT = 30;

	public SudokuScreen(Sudoku sudoku){
		super("Sudoku");
		boardLabel = new MyLabel[9][9];
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
				boardLabel[i][j] = new MyLabel(this);
				boardLabel[i][j].setPoint(i, j);
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
	public void onClick(int x, int y){
		if(boardLabel[x][y].isFixed){
			return;
		}
		int areaX,areaY;
		areaX = (x/3)*3;
		areaY = (y/3)*3;
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				boardLabel[i][j].clear();
			}
		}
		for (int i=0; i<9; i++) {
			boardLabel[x][i].onClick();
			boardLabel[i][y].onClick();
			boardLabel[areaX+i/3][areaY+i%3].onClick();
		}
		boardLabel[x][y].highLight();
	}
	public void setItem(int x, int y, int item){
		sudoku.setItem(x, y, item);
		boardLabel[x][y].setItem(item);
	}
	private void buildItem(){
		int[][] board;
		int item;
		board = sudoku.getBoard();
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				item = board[i][j];
				boardLabel[i][j].setItem(item);
				if(item != 0){
					boardLabel[i][j].setFixed(true);
				}
			}
		}
	}
}

