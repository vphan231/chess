package chess;

/**
 * @author Cindy Lin
 * @author Vincent Phan
 */

public abstract class Piece {
	
	/**
	 * K (king), Q (queen), N (knight), R (rook), B (bishop), P (pawn)
	 */
	char type; 
	
	/**
	 * true (white), false (black)
	 */
	boolean color;
	
	/**
	 * 
	 */
	String name;
	
	/**
	 * true - piece has been moved
	 * false - piece has never been moved
	 */
	boolean moveYet;
	
	/**
	 * @param coordinates
	 * @return true if move is allowed for the piece, false if not allowed for the piece
	 */
	abstract boolean validMove(int x1,int y1,int x2,int y2);
	
}

