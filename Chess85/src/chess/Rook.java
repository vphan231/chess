package chess;

public class Rook extends Piece{
	public Rook(boolean color, String name) {
		this.color = color;
		this.name = name;
	}
	
	boolean validMove(int x1, int x2, int y1, int y2) {
		//move horizontal or vertical
		if(x1 == x2 || y1 == y2) {
			return true;
		}
		else {
			return false;
		}
	}
}
