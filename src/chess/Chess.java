
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
			valid = true;
			if( input.contains("draw?")) {
				drawProposed = true;
			}
			moveCom = convertArr(input); // moveCom[4] = x1, x1, x2, x2	
			
			char promote = '/';
			if( input.length() == 7 ) {
				promote = input.charAt(6);
			}
		
			//System.out.println("Chess.start check: ");
			check = b.check( moveCom[0], moveCom[1], moveCom[2], moveCom[3], promote, !wTurn ); //checks if move puts other player in check
			//what if a pawn is moved then promoted to a queen that puts enemy king in check?
			//System.out.println("Check: " + check );
			b.move( moveCom[0], moveCom[1], moveCom[2], moveCom[3], promote);
			b.prevX1 = moveCom[0]; b.prevY1 = moveCom[1]; b.prevX2 = moveCom[2]; b.prevY2 = moveCom[3]; b.prevType = b.getType(moveCom[2], moveCom[3]); 
			
			
			boolean checkmate = b.checkmate(!wTurn);
			//System.out.println("checkmate: " + checkmate);
			if( checkmate == true ) {
				if( check == true ) {
					System.out.println("Checkmate");
					if( wTurn ) {
						System.out.println("White wins");
					}else {
						System.out.println("Black wins");
					}
				}if( check == false ) {
					System.out.println("Stalemate");
					System.out.println("draw");
				}
				gameEnded = true;	
				continue;
			}
			
			
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
		
		if( str.length() < 5 ) {
			System.out.println("invalid coordinate format1");
			return false;
		}
		
		if( str.equals("draw") || str.equals("resign") ) {
			return true;
		}
		boolean x1 = Character.isLetter(str.charAt(0));
		boolean y1 = Character.isDigit(str.charAt(1));
		boolean x2 = Character.isLetter(str.charAt(3));
		boolean y2 = Character.isDigit(str.charAt(4));
		if( !(x1 && y1 && x2 && y2) ) {
			System.out.println("Invalid coordinate format2");
			return false;
		}
		if( str.length() > 7 && !str.contains("draw") ) {
			return false;
		}
		
		int[] moveCom = convertArr( str );
		
		char promote = '/';
		if( str.length() == 7 ) { //g7 g8 Q
			promote = str.charAt(6);
			return b.validPromote( moveCom[0], moveCom[1], moveCom[2], moveCom[3], promote);
		}
		return b.valid( moveCom[0], moveCom[1], moveCom[2], moveCom[3], promote, color );
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