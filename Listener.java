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
		System.out.println(command);
		System.out.println(command.equals("save"));
		if(command.equals("save")){
			System.out.println("in");
			screen.sudoku.saveFile();
		}
	}
}
