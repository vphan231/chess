
//MAIN CLASS
package chess;
public class Chess {
	
	public static void main(String[] args) {
		Board b = new Board();
		b.initialize();
		start(b);
	}

	public static void start( Board board ) {
		
		
	}
	
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
			case 'A': rc[1] = 0; break;
			case 'B': rc[1] = 1; break;
			case 'C': rc[1] = 2; break;
			case 'D': rc[1] = 3; break;
			case 'E': rc[1] = 4; break;
			case 'F': rc[1] = 5; break;
			case 'G': rc[1] = 6; break;
			case 'H': rc[1] = 7; break;
		}
		
		return rc;
	}
}