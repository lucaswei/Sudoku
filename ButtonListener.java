import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
class ButtonListener implements ActionListener{
	SudokuScreen screen;
	public ButtonListener(SudokuScreen screen){
		this.screen = screen;
	}
	public void actionPerformed(ActionEvent e){
		String command = e.getActionCommand();
		if(command == null)
			return;
		if(command.equals("save")){
			screen.sudoku.saveFile();
		}else if(command.equals("load")){
			screen.readFile();
		}else if(command.equals("submit")){
			screen.isCorrect();
		}else if(command.equals("newBoard")){
			screen.newBoard();
		}
	}
}
