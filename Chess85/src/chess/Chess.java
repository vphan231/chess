
//MAIN CLASS
package chess;

import java.util.Scanner;

public class Chess {
	
	public static void main(String[] args) {
		Board b = new Board();
		b.initialize();
		b.print();
		start(b);
	}
	
	
	public static void start( Board board ) {
		Scanner sc = new Scanner( System.in );
		boolean wTurn = true; //true-white, false-black
		String from = new String();
		String to = new String();
		String input = new String();
		int[] moveCom = new int[4]; //moveCommands
		while( /*game is still on aka not checkmate, draw, resign */){
			if( wTurn ) {
				System.out.println("White's turn: ");
				wTurn = false;
			}else {
				System.out.println("Black's turn: ");
				wTurn = true;
			}
			input = sc.nextLine();
			//check type of input, see notes.txt
			//error check
			if( /* Regular input g7 g8 */) {
				moveCom = convertArr(input); // moveCom[4] = x1, x1, x2, x2
				
			}
			
		
		}
	}
	
	
	public static int[] convertArr( String moveStr ) {
		int[] coords = new int[4];
		coords[0] = convert( moveStr.charAt(0) );
		coords[1] = convert( moveStr.charAt(2) );
		coords[2] = convert( moveStr.charAt(4) );
		coords[3] = convert( moveStr.charAt(6) );
		return coords;
	}
	public static int convert( char c ) {
		switch(c) {
			case '8': return 0; case 'a': return 0;
			case '7': return 1; case 'b': return 1;
			case '6': return 2; case 'c': return 2;
			case '5': return 3; case 'd': return 3;
			case '4': return 4; case 'e': return 4;
			case '3': return 5; case 'f': return 5;
			case '2': return 6; case 'g': return 6;
			case '1': return 7; case 'h': return 7;
		}
		return -1; //should never return this
	}
}