package chess;

public class Knight extends Piece {
	
	char type = 'N';
	
	public Knight( boolean color, String name ) {
		this.color = color;
		this.name = name;
	}
	
	boolean validMove( int x1, int y1, int x2, int y2 ) {
		int x = Math.abs(x2-x1);
		int y = Math.abs(y2-y1);
		if( (x==2 && y==1) || (x==1 && y==2) ) {
			return true;
		}
		return false;
	}
}
