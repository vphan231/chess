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
				if( board[i][k] != null ) {
					System.out.print(board[i][k].piece.name);
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
		System.out.println("  a  b  c  d  e  f  g  h");
	}
	
	public boolean move() {
		return false; //placeholder
	}

	public void initialize() {
		//black == false
		//white == true
		//BP[][] cBoard = new BP[8][8];
		
		//create black pieces
		Piece bR1 = new Rook(false,"bR"); // (color, name)
		BP temp = fill(true, bR1); //(occupy, piece)
		board[0][0] = temp;
		
		Piece bR2 = new Rook(false,"bR");
		temp = fill(true, bR2);
		board[0][7] = temp;
		
		Piece bN1 = new Knight(false,"bN");
		temp = fill(true, bN1);
		board[0][1] = temp;
		
		Piece bN2 = new Knight(false,"bN");
		temp = fill(true, bN2);
		board[0][6] = temp;
		
		Piece bB1 = new Bishop(false,"bB");
		temp = fill(true, bB1);
		board[0][2] = temp;
		
		Piece bB2 = new Bishop(false,"bB");
		temp = fill(true, bB2);
		board[0][5] = temp;
		
		Piece bQ = new Queen(false,"bQ");
		temp = fill(true, bQ);
		board[0][3] = temp;
		
		Piece bK = new King(false,"bK");
		temp = fill(true, bK);
		board[0][4] = temp;
		
		for(int i = 0; i<8 ; i++) {
			Piece bP1 = new Pawn(false,"bp");
			temp = fill(true, bP1);
			board[1][i] = temp;
		}
		
		//create white pieces
		Piece wR1 = new Rook(false,"wR"); // (color, name)
		temp = fill(true, wR1); //(occupy, piece)
		board[7][0] = temp;
		
		Piece wR2 = new Rook(false,"wR");
		temp = fill(true, wR2);
		board[7][7] = temp;
		
		Piece wN1 = new Knight(false,"wN");
		temp = fill(true, wN1);
		board[7][1] = temp;
		
		Piece wN2 = new Knight(false,"wN");
		temp = fill(true, wN2);
		board[7][6] = temp;
		
		Piece wB1 = new Bishop(false,"wB");
		temp = fill(true, wB1);
		board[7][2] = temp;
		
		Piece wB2 = new Bishop(false,"wB");
		temp = fill(true, wB2);
		board[7][5] = temp;
		
		Piece wQ = new Queen(false,"wQ");
		temp = fill(true, wQ);
		board[7][3] = temp;
		
		Piece wK = new King(false,"wK");
		temp = fill(true, wK);
		board[7][4] = temp;
		
		for(int i = 0; i<8 ; i++) {
			Piece wP1 = new Pawn(false,"wp");
			temp = fill(true, wP1);
			board[6][i] = temp;
		}
		return;

	}
	
	public BP fill(boolean oc, Piece x) {
		BP output = new BP();
		output.piece = x;
		output.oc = oc;
		return output;
	}
}
