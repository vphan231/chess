package chess;

public class BP {
	Piece piece;
	boolean oc;
	
	public BP() {
		this.piece = null;
		this.oc = false;
	}
	public BP(boolean oc, Piece piece ) {
		this.piece = piece;
		this.oc = oc;
	}
}
