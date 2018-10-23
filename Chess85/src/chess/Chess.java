
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
	
	
	public static void start( Board b ) {
		Scanner sc = new Scanner( System.in );
		boolean wTurn = true; //true-white, false-black
		String input = new String();
		int[] moveCom = new int[4]; //moveCommands
		boolean drawProposed = false;
		boolean gameEnded = false;
		boolean badInput = false;
		
		while( gameEnded == false ){
			
			if( badInput == false ) { //dont reprint if bad input == true
				if( wTurn ) {
					System.out.println("White's turn: ");
				}else {					
					System.out.println("Black's turn: ");
				}
			}
			input = sc.nextLine();
			
			badInput = validInput( b, input);
			if( badInput == true ) {
				System.out.println("Invalid move, try again:");
				//don't run anything else in this loop iteration (is that continue or break?)
			}
		
			if( input.equals("resign") ) {
				gameEnded = true;
				badInput = false;
				if( wTurn ) {
					System.out.println("Black wins");
				}else {
					System.out.println("White wins");
				}
				//Dont run anything else in this loop iteration
			}
			
			if(drawProposed == true && input.equals("draw") ) {
				badInput = false;
				gameEnded = true;
				//Dont run anything else in this loop iteration
			}

			//Need to work on this
			if( validInput(b, input) == true ) {
				badInput = false;
				if( input.length() == 11 ) {
					drawProposed = true;
				}
				moveCom = convertArr(input); // moveCom[4] = x1, x1, x2, x2	
				b.move( moveCom[0], moveCom[1], moveCom[2], moveCom[3]);
				if( input.length() == 7 ) {
					b.promote(moveCom[2], moveCom[3], input.charAt(6));
				}
			}
		
			if( b.checkmate() == true ) { 
				gameEnded = true;
				if( wTurn ) {
					System.out.println("White wins");
				}else {
					System.out.println("Black wins");
				}
				//Dont run anything else in this loop iteration
			}
			
			//Alternates players
			badInput = false;
			if( wTurn ) {
				wTurn = false;
			}else {
				wTurn = true;
			}
		}
		
		sc.close();
	}
	

	public static boolean validInput( Board b, String str ) {
		
		if( str.equals("draw") || str.equals("resign") ) {
			return true;
		}
		
		int[] moveCom = convertArr( str );
		
		if( /*  str is not of the format g7 g8 || g7 g8 draw? || g7 g8 Q */ ) { //should there be a new method for this check 
			return false;
		}
		
		if( str.length() == 7 ) { //g7 g8 Q
			return b.validPromote( moveCom[0], moveCom[1], moveCom[2], moveCom[3], str.charAt(6));
		}
		
		return b.valid( moveCom[0], moveCom[1], moveCom[2], moveCom[3]);
	}
	
	public static int[] convertArr( String moveStr ) {
		//moveStr = g7 g8 draw
		int[] coords = new int[4];
		coords[0] = convert( moveStr.charAt(0) );
		coords[1] = convert( moveStr.charAt(1) );
		coords[2] = convert( moveStr.charAt(3) );
		coords[3] = convert( moveStr.charAt(4) );
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