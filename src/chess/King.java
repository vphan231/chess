package chess;
/**
 * @author Cindy Lin
 * @author Vincent Phan
 */
public class King extends Piece {
	
	/**
	 * King constructor
	 * @param boolean color: black = false and white = true
	 * @param String name: name of the piece 
	 */
	public King( boolean color, String name ) {
		this.type = 'K';
		this.color = color;
		this.name = name;
		this.moveYet = false;
	}
	/**
	 * Checks for valid move of a King
	 * @param int x1,x2,y1,y2: coordinates
	 * @return true or false
	 * 
	 */
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
