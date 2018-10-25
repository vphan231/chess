package chess;

public abstract class Piece {
	
	char type;
	boolean color;
	String name;
	
	//Fields
	//Boolean Color
	//String Name
	//Methods
	abstract boolean validMove(int x1,int y1,int x2,int y2);
	
}

