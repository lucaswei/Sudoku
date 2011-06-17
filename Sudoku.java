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
		boardToPuzzle();
	}
	public void boardToPuzzle(){
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				 puzzle[i][j] = board[i][j];
			}
		}
	}
	public int getItem(int x, int y){
		return puzzle[x][y];
	}
	public void setItem(int x, int y, int item){
		puzzle[x][y] = item;
	}
	public int[][] getBoard(){
		return board;
	}
	public int[][] getPuzzle(){
		return puzzle;
	}
	public void setNewBoard(int seed){
		this.seed = seed;
		random.setSeed(seed);
		int[] row = new int[9];
		int[][] sample = new int[3][3];
		int[][] temp;
		randomArray(row);
		for (int i=0; i<3; i++) {
			for (int j=0; j<3;j++) {
				sample[i][j] = row[i*3+j];
			}
		}
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				temp = shift(sample, j, i);

				for (int a=0; a<3;a++){
					for (int b=0; b<3; b++) {
						answer[i*3+a][j*3+b] = temp[a][b];
					}
				}
			}
		}

		makePuzzle();
	}
	private int[][] shift(int[][] sample, int x, int y){
		int[][] result = new int[3][3];
		int a,b;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if((i+x)>=3)
					a = i+x-3;
				else
					a =i+x;
				if((j+y)>=3)
					b = j+y-3;
				else
					b =j+y;
				result[i][j] = sample[a][b];
			}
		}
		return result;
	}
	public boolean isCorrect(){
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if(puzzle[i][j] == answer[i][j])
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
				output += Integer.toString(puzzle[i][j]) + "-";
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
			seed = Integer.parseInt(stringItems);
			setNewBoard(seed);
			for (int i=0; i<9; i++) {
				stringItems = buf.readLine();
				items = stringItems.split("-");
				for (int j=0; j<9; j++) {
					if(items[j] != null) {
						puzzle[i][j] = Integer.parseInt( items[j] );
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
	public void printP(){
		for (int i=0; i<9; i++)	{
			for (int j=0; j<9; j++) {
				System.out.printf("%d ",puzzle[i][j]);
			}
			System.out.printf("\n");
		}
	}
	private void makePuzzle(){
		random.setSeed(seed);
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if(random.nextInt(4)==0)
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
