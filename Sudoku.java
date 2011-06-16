import java.util.Random;
import java.io.*;
public class Sudoku{
	public int[][] board;
	public int[][] puzzle;
	public int[][] answer;
	public Random random;
	public int seed;


	public Sudoku(int seed){
		board  = new int[9][9];
		puzzle = new int[9][9];
		answer = new int[9][9];
		random = new Random();
		setNewBoard(seed);
	}
	public int getItem(int x, int y){
		return board[x][y];
	}
	public void setItem(int x, int y, int item){
		board[x][y] = item;
		System.out.println(item);
	}
	public int[][] getBoard(){
		return board;
	}
	public void setNewBoard(int seed){
		this.seed = seed;
		random.setSeed(seed);
		int[][] tempBoard = new int[9][9];
		int[] row = new int[9];
		int[] columnShift = new int[9];
		int offset;

		randomArray(row);
		randomArray(columnShift);

		for (int i=0; i<row.length; i++) {
			for (int j=0; j<row.length; j++) {
				offset = j+columnShift[i];
				if (offset >= 9) 
					offset = offset - 9;
				answer[i][j] = row[offset];
			}
		}
		makePuzzle();
	}
	public boolean isCorrect(){
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if(board[i][j] == answer[i][j])
					return false;
			}
		}
		return true;
	}
	//public int[][] getWrongPlace()
	public void saveFile(){
		String output = new String();
		output = output + Integer.toString(seed) +"\n";
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				output += Integer.toString(board[i][j]) + "-";
			}
			output += "\n";
		}
		try{
			FileWriter fp = new FileWriter("output.txt");
			fp.write(output);
			fp.close();
		}
		catch(IOException e){
			System.out.printf("Can't not open file");
		}
	}
	public void readFile(){
		FileReader fileReader;
		BufferedReader buf;
		String stringItems;
		String[] items;
		try{
			fileReader = new FileReader("output.txt");
			buf = new BufferedReader(fileReader);
			stringItems = buf.readLine();
			setNewBoard(Integer.parseInt(stringItems));
			for (int i=0; i<2; i++) {
				stringItems = buf.readLine();
				items = stringItems.split("-");
				for (int j=0; j<9; j++) {
					if(items[j] != null) {
						board[i][j] = Integer.parseInt( items[j] );
					}
				}
			}
		}
		catch(IOException e){
			System.out.printf("Can't not read file, the file have some errors");
		}
	}
	public void print(){
		for (int i=0; i<9; i++)	{
			for (int j=0; j<9; j++) {
				System.out.printf("%d ",answer[i][j]);
			}
			System.out.printf("\n");
		}
	}
	public void printB(){
		for (int i=0; i<9; i++)	{
			for (int j=0; j<9; j++) {
				System.out.printf("%d ",board[i][j]);
			}
			System.out.printf("\n");
		}
	}
	private void makePuzzle(){
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if(random.nextInt(6)==0)
					board[i][j] = answer[i][j];
				else
					board[i][j] = 0;
			}
		}
	}
	private int[] randomArray(int[] row){
		int temp;
		int a,b;
		for (int i=0; i<row.length; i++) {
			row[i] = i+1;
		}
		for (int i=0; i<row.length; i++) {
			a = random.nextInt(row.length);
			b = random.nextInt(row.length);
			temp   = row[a];
			row[a] = row[b];
			row[b] = temp;
		}
		return row;
	}
}
