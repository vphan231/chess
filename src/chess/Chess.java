
//MAIN CLASS
package chess;

import java.util.Scanner;

public class Chess {
	
	public static void main(String[] args) {
		Board b = new Board();
		b.initialize();
		start(b);
	}
	
	
	public static void start( Board b ) {
		Scanner sc = new Scanner( System.in );
		boolean wTurn = true; //true-white, false-black
		String input = new String();
		int[] moveCom = new int[4]; //moveCommands
		boolean drawProposed = false;
		boolean gameEnded = false;
		boolean valid = true;
		boolean check; 
		
		while( gameEnded == false ){
			System.out.println();
			if( valid == true) { //dont reprint if bad input == true
				b.print();
				System.out.println();
			}
			if( wTurn ) {
				System.out.print("White's turn: ");
			}else {					
				System.out.print("Black's turn: ");
			}
			input = sc.nextLine();
			
			valid = validInput( b, input, wTurn);
			System.out.println("validInput: " + valid);
			if( valid == false ) {
				System.out.println("Illegal move, try again:");
				continue;
			}
		
			if( input.equals("resign") ) {
				gameEnded = true;
				valid = true;
				if( wTurn ) {
					System.out.println("Black wins");
				}else {
					System.out.println("White wins");
				}
				continue;
			}
			
			if(drawProposed == true && input.equals("draw") ) {
				valid = true;
				gameEnded = true;
				continue;
			}

			//Need to work on this
			valid = true;
			if( input.length() == 11 ) {
				drawProposed = true;
			}
			moveCom = convertArr(input); // moveCom[4] = x1, x1, x2, x2	
			
			/*
			check = b.check( moveCom[0], moveCom[1], moveCom[2], moveCom[3], !wTurn); //checks if move puts other player in check
			System.out.println("check: " + check);
			if( check == true ) {
				System.out.println("Check");
			}				
			*/
			
			b.move( moveCom[0], moveCom[1], moveCom[2], moveCom[3]);
			if( input.length() == 7 ) {
				b.promote(moveCom[2], moveCom[3], input.charAt(6));
			}
			
			/*
			if( b.checkmate() == true ) { 
				System.out.println("Checkmate");
				gameEnded = true;
				if( wTurn ) {
					System.out.println("White wins");
				}else {
					System.out.println("Black wins");
				}
				continue;
			}
			*/
			
			//Alternates players
			if( wTurn ) {
				wTurn = false;
			}else {
				wTurn = true;
			}
		}
		sc.close();
	}
	

	public static boolean validInput( Board b, String str, boolean color ) {
		if( str.equals("draw") || str.equals("resign") ) {
			return true;
		}
		boolean x1 = Character.isLetter(str.charAt(0));
		boolean y1 = Character.isDigit(str.charAt(1));
		boolean x2 = Character.isLetter(str.charAt(3));
		boolean y2 = Character.isDigit(str.charAt(4));
		if( !(x1 && y1 && x2 && y2) ) {
			return false;
		}
		if( str.length() > 7 && !str.contains("draw") ) {
			return false;
		}
		
		int[] moveCom = convertArr( str );
	
		if( str.length() == 7 ) { //g7 g8 Q
			char c = str.charAt(6);
			if( c!='Q' && c!='N' && c!='R' && c!='B' ) {
				return false;
			}
			return b.validPromote( moveCom[0], moveCom[1], moveCom[2], moveCom[3], str.charAt(6));
		}
		return b.valid( moveCom[0], moveCom[1], moveCom[2], moveCom[3], color );
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