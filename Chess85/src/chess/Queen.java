package chess;

public class Queen extends Piece{
	public Queen(boolean color, String name) {
		this.color = color;
		this.name = name;
	}
	
	boolean validMove(int x1, int x2, int y1, int y2) {
		//if movement is diagonal, horizontal, or vertical
		if((x1 - y1) == (x2 - y2) || (x1 + y1) == (x2 + y2) || (x1 == x2) || (y1 == y2)) {
			return true;
		}
		else {
			return false;
		}
	}
}
