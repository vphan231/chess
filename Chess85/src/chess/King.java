package chess;

public class King extends Piece {
	
	char type = 'K';
	
	public King( boolean color, String name ) {
		this.color = color;
		this.name = name;
	}
	
	boolean validMove(int x1, int y1, int x2, int y2) {
		//move up 1.
		if(x1 == x2 && y2 == y1+1) {
			return true;
		}
		//move down 1
		else if(x1 == x2 && y2 == y1-1) {
			return true;
		}
		//move left 1
		else if(x2 == x1-1 && y1 == y2) {
			return true;
		}
		//move right 1
		else if(x2 == x1+1 && y1 == y2) {
			return true;
		}
		//move diagonal up left
		else if(x2 == x1-1 && y2 == y1+1) {
			return true;
		}
		//move diagonal up right
		else if(x2 == x1+1 && y2 == y1+1) {
			return true;
		}
		//move diagonal down left
		else if(x2 == x1-1 && y2 == y1-1) {
			return true;
		}
		//move diagonal down right.
		else if(x2 == x1+1 && y2 == y1-1) {
			return true;
		}
		else {
			return false;
		}
	}
}
