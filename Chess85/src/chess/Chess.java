
//MAIN CLASS
package chess;
public class Chess {
	
	public static void main(String[] args) {
		Board b = new Board();
		b.initialize();
		b.print();
		//start(b);
	}
	
	/*
	public static void start( Board board ) {
		
		
	}
	*/
	
	public static int[] convert( String rcStr ) {
		int[] rc = new int[2];
		char row = rcStr.charAt(0);
		char col = rcStr.charAt(2);
		
		switch(row) {
			case '8': rc[0] = 0; break;
			case '7': rc[0] = 1; break;
			case '6': rc[0] = 2; break;
			case '5': rc[0] = 3; break;
			case '4': rc[0] = 4; break;
			case '3': rc[0] = 5; break;
			case '2': rc[0] = 6; break;
			case '1': rc[0] = 7; break;
		}
		switch(col) {
			case 'a': rc[1] = 0; break;
			case 'b': rc[1] = 1; break;
			case 'c': rc[1] = 2; break;
			case 'd': rc[1] = 3; break;
			case 'e': rc[1] = 4; break;
			case 'f': rc[1] = 5; break;
			case 'g': rc[1] = 6; break;
			case 'h': rc[1] = 7; break;
		}
		
		return rc;
	}
}