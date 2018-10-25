package chess;

public class Bishop extends Piece {
	
	
	public Bishop(boolean color, String name) {
		this.type = 'B';
		this.color = color;
		this.name = name;
	}
	
	boolean validMove(int x1, int y1, int x2, int y2) {
		if((x1 - y1) == (x2 - y2) || (x1 + y1) == (x2 + y2)) {
			return true;
		}
		else {
			return false;
		}
	}
}
