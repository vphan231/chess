
//MAIN CLASS
package chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Chess {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input move files (file1 file2): ");
		String input = sc.nextLine();
		sc.close();
		
		//Do we need to check for invalid input? - invalid format, file not found
		Scanner file1 = new Scanner( new File (input.substring(0, input.indexOf(' '))));
		Scanner file2 = new Scanner( new File( input.substring(input.indexOf(' ')+1)));
		
		//START GAME
		//file1 and file2 needs to be visible to other classes if game is implemented in another class
		
	}

}
