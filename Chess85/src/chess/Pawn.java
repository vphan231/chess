package chess;

public class Pawn extends Piece{
	public Pawn(boolean color, String name) {
		this.color = color;
		this.name = name;
	}
	
	boolean validMove(int x1, int x2, int y1, int y2) {
		//if pawn havn't move yet: can move 2 spaces, 1 space, or 1 space diagonal to kill
		if(!moveYet(x1)) {
			if(x1 == x2 && y2 == y1+2) {
				return true;
			}
			else if(x1 == x2 && y2 == y1+1) {
				return true;
			}
			//this returns true only if there is a pawn of opposite color on its foward diagnonal.
			//this is a legal move, but leave the check on the main to find out if pawn is there to kill?
			else if(x1 == x2-1 && y2 == y1+1) {
				return true;
			}
			else {
				return false;
			}
		}
		//pawn already moved
		else {
			if(x1 == x2 && y2 == y1+1 ) {
				return true;
			}
			else if(x1 == x2-1 && y2 == y1+1) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public boolean moveYet(int x1) {
		if(name.equalsIgnoreCase("wp") && x1 == 6) {
			return false;
		}
		else if(name.equalsIgnoreCase("bp") && x1 == 1) {
			return false;
		}
		else {
			return true;
		}
	}
}
