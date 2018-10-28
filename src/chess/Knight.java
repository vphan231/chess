package chess;
/**
 * @author Cindy Lin
 * @author Vincent Phan
 */
public class Knight extends Piece {
	
	/**
	 * Knight constructor
	 * @param boolean color: black = false and white = true
	 * @param String name: name of the piece 
	 */
	public Knight( boolean color, String name ) {
		this.type = 'N';
		this.color = color;
		this.name = name;
	}
	
	/**
	 * Checks for valid move of a Knight
	 * @param int x1,x2,y1,y2: coordinates
	 * @return true or false
	 * 
	 */
	boolean validMove( int x1, int y1, int x2, int y2 ) {
		int x = Math.abs(x2-x1);
		int y = Math.abs(y2-y1);
		if( (x==2 && y==1) || (x==1 && y==2) ) {
			return true;
		}
		return false;
	}
}
