package chess;

public class Board {
	
	public BP[][] board;
	
	public Board() {
		this.board = new BP[8][8];
	}
	
	//returns true if path is open, false if path is clear
	public boolean pathH( int r1, int c1, int r2, int c2 ) {
		boolean clear = true;
		
		
		return clear;
	}
	public boolean pathV( int r1, int c1, int r2, int c2 ) {
		boolean clear = true;
		return clear;
	}
	public boolean pathD( int r1, int c1, int r2, int c2 ) {
		boolean clear = true;
		return clear;
	}

	
	public void print() {
		int colDis = 8;
		for( int i = 0; i < 8; i++ ) {
			for( int k = 0; k < 8; k++ ) {
				if( board[i][k].piece != null ) {
					board[i][k].piece.toString();
					System.out.print(" ");
				}else {
					if( i % 2 == 0 ) {
						if( k % 2 == 0) {
							System.out.println("   ");
						}else {
							System.out.println("## ");
						}
	
					}else {
						if( k % 2 == 0) {
							System.out.println("## ");
						}else {
							System.out.println("   ");
						}
					}
				}
			}
			System.out.println(colDis);
			colDis--;
		}
		System.out.println("  A  B  C  D  E  F  G  H");
	}
	
	public boolean move() {
		return false; //placeholder
	}
}
