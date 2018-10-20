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

	public BP[][] initialize() {
		//black == false
		//white == true
		BP[][] cBoard = new BP[8][8];
		
		//create black pieces
		Piece bR1 = new Rook(false,"bR"); // (color, name)
		BP temp = fill(true, bR1); //(occupy, piece)
		cBoard[0][0] = temp;
		
		Piece bR2 = new Rook(false,"bR");
		temp = fill(true, bR2);
		cBoard[0][7] = temp;
		
		Piece bN1 = new Knight(false,"bN");
		temp = fill(true, bN1);
		cBoard[0][1] = temp;
		
		Piece bN2 = new Knight(false,"bN");
		temp = fill(true, bN2);
		cBoard[0][6] = temp;
		
		Piece bB1 = new Bishop(false,"bB");
		temp = fill(true, bB1)
		cBoard[0][2] = temp;
		
		Piece bB2 = new Bishop(false,"bB");
		temp = fill(true, bB2);
		cBoard[0][5] = temp;
		
		Piece bQ = new Queen(false,"bQ");
		temp = fill(true, bQ);
		cBoard[0][3] = temp;
		
		Piece bK = new King(false,"bK");
		temp = fill(true, bK);
		cBoard[0][4] = temp;
		
		for(int i = 0; i<8 ; i++) {
			Piece bP1 = new Pawn(false,"bp");
			temp = fill(true, bP1);
			cBoard[1][i] = temp;
		}
		
		//create white pieces
		Piece wR1 = new Rook(false,"wR"); // (color, name)
		temp = fill(true, wR1); //(occupy, piece)
		cBoard[7][0] = temp;
		
		Piece wR2 = new Rook(false,"wR");
		temp = fill(true, wR2);
		cBoard[7][7] = temp;
		
		Piece wN1 = new Knight(false,"wN");
		temp = fill(true, wN1);
		cBoard[7][1] = temp;
		
		Piece wN2 = new Knight(false,"wN");
		temp = fill(true, wN2);
		cBoard[7][6] = temp;
		
		Piece wB1 = new Bishop(false,"wB");
		temp = fill(true, wB1);
		cBoard[7][2] = temp;
		
		Piece wB2 = new Bishop(false,"wB");
		temp = fill(true, wB2);
		cBoard[7][5] = temp;
		
		Piece wQ = new Queen(false,"wQ");
		temp = fill(true, wQ);
		cBoard[7][3] = temp;
		
		Piece wK = new King(false,"wK");
		temp = fill(true, wK);
		cBoard[7][4] = temp;
		
		for(int i = 0; i<8 ; i++) {
			Piece wP1 = new Pawn(false,"wp");
			temp = fill(true, wP1);
			cBoard[6][i] = temp;
		}
		return cBoard;

	}
	
	public BP fill(boolean oc, Piece x) {
		BP output = new BP();
		output.piece = x;
		output.oc = oc;
		return output;
	}
}
