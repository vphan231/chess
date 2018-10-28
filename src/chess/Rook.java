package chess;
/**
 * @author Cindy Lin
 * @author Vincent Phan
 */
public class Rook extends Piece{
	/**
	 * Rook constructor
	 * @param boolean color: black = false and white = true
	 * @param String name: name of the piece 
	 */
	public Rook(boolean color, String name) {
		this.type = 'R';
		this.color = color;
		this.name = name;
		this.moveYet = false;
	}
	/**
	 * Checks for valid move of a Rook
	 * @param int x1,x2,y1,y2: coordinates
	 * @return true or false
	 * 
	 */
	boolean validMove(int x1, int y1, int x2, int y2) {
		//move horizontal or vertical
		if(x1 == x2 || y1 == y2) {
			return true;
		}
		else {
			return false;
		}
	}
}
