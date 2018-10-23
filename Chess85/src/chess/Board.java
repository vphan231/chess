package chess;


public class Board {

	public Piece[][] board;
	
	public Board() {
		this.board = new Piece[8][8];
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
	
	//returns true if path is open, false if path is clear
	public boolean pathH( int r1, int c1, int r2, int c2 ) {
		//row remains the same, move along col
		int lower = c2; int higher = c1;
		if( c1 <= c2 ) {
			lower = c1;	higher = c2;
		}
		
		for( int i = lower+1; i < higher-1; i++) {
			if( board[r1][i]!= null) {
				return false;
			}
		}
		return true;
	}
	public boolean pathV( int r1, int c1, int r2, int c2 ) {
		//move along row, col remains the same
		int lower = r2; int higher = r1;
		if( r1 <= r2 ) {
			lower = r1;	higher = r2;
		}
		for( int i = lower+1; i < higher-1; i++) {
			if(board[i][c1]!= null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean pathD( int r1, int c1, int r2, int c2 ) {
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
				if( board[i][lowerC+k] != null ) { return false; }
			}else {
				if( board[i][lowerC-k] != null ) { return false; }
			}
			k++;
		}
		return true;
	}
	
	public boolean valid( int r1, int c1, int r2, int c2 ) {
		Piece p = board[r1][c1];
		if( p == null ) {
			return false;
		}
		char type = p.type;
		boolean spec = p.validMove(r1, c1, r1, c2);
		if( spec == false ) {
			return false;
		}
		boolean h = pathH( r1, c1, r2, c2 ); 
		boolean v = pathV( r1, c1, r2, c2 );
		boolean d = pathD( r1, c1, r2, c2 );
		int direction; //1-h, 2-v, 3-d
		if( r1 == r2 ) {
			direction = 1;
		}else if( c1 == c2 ) {
			direction = 2;
		}else { 
			direction = 3; //has to be diagonal otherwise spec should've already returned false
		}
		
		if( type == 'B') { //Bishop
			return d;
		}
		if( type == 'K' ) {
			switch(direction) {
				case 1: return h;
				case 2: return v;
			}
		}
		if( type == 'P' ) {
			return h;
		}
		if( type == 'Q' ) {
			switch(direction) {
				case 1: return h;
				case 2: return v;
				case 3: return d;
			}
		}
		if( type == 'R' ) {
			return h;
		}
		return true;
	}

	public boolean move( int r1, int c1, int r2, int c2 ) {
		Piece p = board[r1][c1];
		boolean valid = valid( r1, c1, r2, c2 );
		if( valid == false ) {
			return false;
		}
		board[r1][c1] = null;
		board[r2][c2] = p;
		
		return true;
	}

	public boolean validPromote( int x1, int y1, int x2, int y2, char c ) {
		//checks if x1 y1 x2 y2 is a pawn moving to the end of the board
		//checks if c is a valid piece to promote to
		if (c != 'Q' || c != 'R' || c != 'N' || c != 'B') {
			return false;
		}
		// 1st: if piece is a pawn. 2nd: if validate a pawn's move. 3rd: Final move is at the end of the board
		if (board[x1][y1].type == 'P' && board[x1][y1].validMove(x1, y1, x2, y2) && (x2 == 0 || x2 == 7) ) {
			return true;
		}
		else {
			return false;
		}
	}
	public void promote( int x, int y, char c ) {
		//promote piece at x y to piece c
		boolean pieceColor = board[x][y].color;
		String pieceChar = board[x][y].name.substring(0, 1); //just want the first char, either 'b' or 'w'
		
		if(c == 'R') {
			board[x][y] =  new Rook(pieceColor, pieceChar+"R");
		}
		else if(c == 'B') {
			board[x][y] =  new Bishop(pieceColor, pieceChar+"B");
		}
		else if(c == 'N') {
			board[x][y] =  new Knight(pieceColor, pieceChar+"N");
		}
		else {
			board[x][y] =  new Queen(pieceColor, pieceChar+"Q");
		}
		return;
	}
	
	public boolean check( int x1, int y1, int x2, int y2 ) {
		//check if move puts own king in check
		
		/*assume the move has already been made from (x1,y1) to (x2,y2). traverse the board to find the king: 
		 *board[i][j].type = 'K' and match color. Check PathH left side of king to end of the board, if true: means there are
		 * no pieces to its left, else if false, need to traverse left until not null. Check if the piece type = Queen or Rook.
		 *Do the same for right side. Do the same for PathV for checks for Queen or Rook. Do the same for PathD for
		 *checks of Bishop and Queen.   
		 */
		int kingX= 0, kingY= 0;
		boolean pieceColor = false;
		if(board[x2][y2].color == true) {
			pieceColor = true;
		}
		//get king's coordinates
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[i][j].type == 'K' && (board[x2][y2].color == pieceColor)) {
					kingX = i;
					kingY = j;
				}
			}
		}
		//check left horizontal of king
		if(!pathH(kingX, kingY, 0, kingY)) {
			for (int i = kingX; i <= 0; i--) {
				if (board[i][kingY] != null) { 
					if (board[i][kingY].color != pieceColor && (board[i][kingY].type == 'Q' || board[i][kingY].type == 'R')) {
						return false;
					}
					else {
						break;
					}
				}
			}
		}
		//check right horizontal of King
		if(!pathH(kingX, kingY, 7, kingY)) {
			for (int i = kingX; i >= 7; i++) {
				if (board[i][kingY] != null) { 
					if (board[i][kingY].color != pieceColor && (board[i][kingY].type == 'Q' || board[i][kingY].type == 'R')) {
						return false;
					}
					else {
						break;
					}
				}
			}
		}
		//check upper vertical of King
		if(!pathV(kingX, kingY, kingX, 0)) {
			for (int i = kingY; i <= 0; i--) {
				if (board[kingX][i] != null) { 
					if(board[kingX][i].color != pieceColor && (board[kingX][i].type == 'Q' || board[kingX][i].type == 'R')) {
						return false;
					}
					else {
						break;
					}
				}
			}
		}
		//check lower vertical of King
		if(!pathV(kingX, kingY, kingX, 7)) {
			for (int i = kingY; i >= 7; i++) {
				if (board[kingX][i] != null) { 
					if(board[kingX][i].color != pieceColor && (board[kingX][i].type == 'Q' || board[kingX][i].type == 'R')) {
						return false;
					}
					else {
						break;
					}
				}
			}
		}
		//check upper left diagonal
		
		//check upper right diagonal
		
		//check lower left diagonal
		
		//check lower right diagonal
		return true; //placeholder
	}
	
	public boolean checkmate() {
		//checks if either king is in checkmate
		return true; //placeholder
	}
	
}
