package chess;


public class Board {

	public Piece[][] board;
	
	public Board() {
		this.board = new Piece[8][8];
	}
	
	//returns true if path is open, false if path is clear
	public boolean pathH( int r1, int c1, int r2, int c2 ) {
		//row remains the same, move along col
		boolean clear = true;
		int lower = c2; int higher = c1;
		if( c1 <= c2 ) {
			lower = c1;	higher = c2;
		}
		
		for( int i = lower+1; i < higher-1; i++) {
			if(board[r1][i]!= null) {
				clear = false;
			}
		}
		return clear;
	}
	public boolean pathV( int r1, int c1, int r2, int c2 ) {
		//move along row, col remains the same
		boolean clear = true;
		int lower = r2; int higher = r1;
		if( r1 <= r2 ) {
			lower = r1;	higher = r2;
		}
		for( int i = lower+1; i < higher-1; i++) {
			if(board[i][c1]!= null) {
				clear = false;
			}
		}
		return clear;
	}
	
	public boolean pathD( int r1, int c1, int r2, int c2 ) {
		boolean clear = true;
		int lowerR = r2; int higherR = r1;
		int lowerC = c2; int higherC = c1;
		if( r1 < r2 ) {
			lowerR = r1; higherR = r2;
			lowerC = c1; higherC = c2;
		}
		boolean goingRight = true; // \
		if ( lowerC < higherC ) {
			goingRight = false; // /
		}
		int k = 0;
		for( int i = lowerR+1; i < (higherR-lowerR)-1 ; i++ ) {
			if( goingRight == true ) {
				if( board[i][lowerC+k] != null ) { clear = false; }
			}else {
				if( board[i][lowerC-k] != null ) { clear = false; }
			}
			k++;
		}
		
		
		return clear;
	}

	
	public void print() {
		int colDis = 8;
		for( int i = 0; i < 8; i++ ) {
			for( int k = 0; k < 8; k++ ) {
				if( board[i][k] != null ) {
					System.out.print(board[i][k].name);
					System.out.print(" ");
				}else {
					if( i % 2 == 0 ) {
						if( k % 2 == 0) {
							System.out.print("   ");
						}else {
							System.out.print("## ");
						}
	
					}else {
						if( k % 2 == 0) {
							System.out.print("## ");
						}else {
							System.out.print("   ");
						}
					}
				}
			}
			System.out.println(colDis);
			colDis--;
		}
		System.out.println(" a  b  c  d  e  f  g  h");
	}
	
	public boolean move() {
		return false; //placeholder
	}

	public void initialize() {
		//black == false; white == true
		//create black pieces
		board[0][0] = new Rook(false,"bR"); //color, name
		board[0][7] = new Rook(false,"bR");
		board[0][1] = new Knight(false,"bN");
		board[0][6] = new Knight(false,"bN");
		board[0][2] = new Bishop(false,"bB");
		board[0][5] = new Bishop(false,"bB");
		board[0][3] = new Queen(false,"bQ");
		board[0][4] = new King(false,"bK");	
		for(int i = 0; i<8 ; i++) {
			board[1][i] = new Pawn(false,"bp");
		}
		//create white pieces
		board[7][0] = new Rook(true,"wR"); //color, name
		board[7][7] = new Rook(true,"wR");
		board[7][1] = new Knight(true,"wN");
		board[7][6] = new Knight(true,"wN");
		board[7][2] = new Bishop(true,"wB");
		board[7][5] = new Bishop(true,"wB");
		board[7][3] = new Queen(true,"wQ");
		board[7][4] = new King(true,"wK");
		for(int i = 0; i<8 ; i++) {
			board[6][i] = new Pawn(true,"wp");
		}
		return;

	}
}
