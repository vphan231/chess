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
			System.out.print(colDis);
			System.out.println(" " + i);
			colDis--;
		}
		System.out.println(" a  b  c  d  e  f  g  h");
		System.out.println(" 0  1  2  3  4  5  6  7 ");
	}
	
	//returns true if path is open, false if path is clear
	public boolean pathH( int y1, int x1, int y2, int x2 ) {
		//row remains the same, move along col
		int begin = x1+1; int end = x2; // y1 <= y2
		if( x1 > x2 ){
			begin = x2; end = x1-1;
		}
		for( int i = begin; i <= end; i++ ) {
			if( board[y1][i] !=  null ) {
				return false;
			}
		}

		return true;
	}
	public boolean pathV( int y1, int x1, int y2, int x2 ) {
		//move along row, col remains the same
		int begin = y1+1; int end = y2; // y1 <= y2
		
		if( y1 > y2 ){
			begin = y2; end = y1-1;
		}
		
		for( int i = begin; i <= end; i++ ) {
			//System.out.println(x1 + " " + i);
			if( board[i][x1] !=  null ) {
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
	
	public boolean valid( int r1, int c1, int r2, int c2, boolean color ) {

		Piece p = board[r1][c1];
		if( p == null ) { //no piece to move
			return false;
		}
		if( p.color != color ) {
			return false;
		}
		char type = p.type;
		//System.out.println("piece: " + p.name);
		//System.out.println("type: " + type );
		boolean spec = p.validMove(r1, c1, r2, c2);
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

	public boolean move( int y1, int x1, int y2, int x2 ) {
		/*
		System.out.print("move: ");
		System.out.print(y1);
		System.out.print(x1);
		System.out.print(y2);
		System.out.println(x2);
		*/
		Piece p = board[y1][x1];
		//System.out.println("piece: " + p.name);
		
		boolean valid = valid( y1, x1, y2, x2, p.color );
		if( valid == false ) {
			return false;
		}
		board[y1][x1] = null;
		board[y2][x2] = p;
		
		//default promote pawn g7 g8
		if( y2 == 1 && p.type == 'P') {
			board[y2][x2] = new Queen( true, "wQ" );
		}
		if( y2 == 7 && p.type == 'P') {
			board[y2][x2] = new Queen( false, "bQ" );
		}
		
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
	
	public boolean check( int y1, int x1, int y2, int x2, boolean pieceColor ) {
		//check if move puts king of specified color in check
		
		/*assume the move has already been made from (x1,y1) to (x2,y2). traverse the board to find the king: 
		 *board[i][j].type = 'K' and match color. Check PathH left side of king to end of the board, if true: means there are
		 * no pieces to its left, else if false, need to traverse left until not null. Check if the piece type = Queen or Rook.
		 *Do the same for right side. Do the same for PathV for checks for Queen or Rook. Do the same for PathD for
		 *checks of Bishop and Queen.   
		 */
		
		//make the move - has to be reverted before every return
		
		/*
		System.out.print("check: ");
		System.out.print(y1);
		System.out.print(x1);
		System.out.print(y2);
		System.out.println(x2);
		System.out.println( "color: " + pieceColor);
		*/
		
		board[y2][x2] = board[y1][x1]; 
		board[y1][x1] = null;
		//print();
	
		int kingX= 0, kingY= 0;
			
		//get king's coordinates
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece p = board[i][j];
				if( p != null ) {
					//System.out.print( p.name + " ");
					//System.out.println(p.type );
				}
				if( p != null && p.type == 'K' && p.color == pieceColor) {
					kingX = i;
					kingY = j;
					//System.out.println();
					System.out.println("King found at: " + kingX + ", " + kingY);
				}
			}
		}
		//System.out.println();

		
		//check left horizontal of king
		if(!pathH(kingX, kingY, kingX, 0)) {
			for (int i = kingY-1; i >= 0; i--) {
				if (board[kingX][i] != null) { 
					if (board[kingX][i].color != pieceColor && (board[kingX][i].type == 'Q' || board[kingX][i].type == 'R')) {
						board[x1][y1] = board[x2][y2];
						board[x2][y2] = null;
						return false;
					}
					else {
						break;
					}
				}
			}
		}
		//check right horizontal of King
		if(!pathH(kingX, kingY, kingX, 7)) {
			for (int i = kingY+1; i <= 7; i++) {
				if (board[kingX][i] != null) { 
					if (board[kingX][i].color != pieceColor && (board[kingX][i].type == 'Q' || board[kingX][i].type == 'R')) {
						board[x1][y1] = board[x2][y2];
						board[x2][y2] = null;
						return false;
					}
					else {
						break;
					}
				}
			}
		}
		//check upper vertical of King
		if(!pathV(kingX, kingY, 0, kingY)) {
			for (int i = kingX-1; i >= 0; i--) {
				if (board[i][kingY] != null) { 
					if(board[i][kingY].color != pieceColor && (board[i][kingY].type == 'Q' || board[i][kingY].type == 'R')) {
						board[x1][y1] = board[x2][y2];
						board[x2][y2] = null;
						return false;
					}
					else {
						break;
					}
				}
			}
		}
		//check lower vertical of King
		if(!pathV(kingX, kingY, 7, kingY)) {
			for (int i = kingX+1; i <= 7; i++) {
				if (board[i][kingY] != null) { 
					if(board[i][kingY].color != pieceColor && (board[i][kingY].type == 'Q' || board[i][kingY].type == 'R')) {
						board[x1][y1] = board[x2][y2];
						board[x2][y2] = null;
						return false;
					}
					else {
						break;
					}
				}
			}
		}
		//check upper left diagonal
		int tempY = kingY;
		for (int i = kingX-1; i >= 0; i--) {
			tempY--;
			if (tempY >= 0 && board[i][tempY] != null) {
				if(board[i][tempY].color != pieceColor && (board[i][tempY].type == 'Q' || board[i][tempY].type == 'B')) {
					board[x1][y1] = board[x2][y2];
					board[x2][y2] = null;
					return false;
				}
				else {
					break;
				}
			}
		}
		//check upper right diagonal
		tempY = kingY;
		for (int i = kingX-1; i >= 0; i--) {
			tempY++;
			if (tempY <= 7 && board[i][tempY] != null) {
				if(board[i][tempY].color != pieceColor && (board[i][tempY].type == 'Q' || board[i][tempY].type == 'B')) {
					board[x1][y1] = board[x2][y2];
					board[x2][y2] = null;
					return false;
				}
				else {
					break;
				}
			}
		}
		//check lower left diagonal
		tempY = kingY;
		for (int i = kingX+1; i <= 7; i++) {
			tempY--;
			if (tempY >= 0 && board[i][tempY] != null) {
				if(board[i][tempY].color != pieceColor && (board[i][tempY].type == 'Q' || board[i][tempY].type == 'B')) {
					board[x1][y1] = board[x2][y2];
					board[x2][y2] = null;
					return false;
				}
				else {
					break;
				}
			}
		}
		//check lower right diagonal
		tempY = kingY;
		for (int i = kingX+1; i <= 7; i++) {
			tempY++;
			if (tempY <= 7 && board[i][tempY] != null) {
				if(board[i][tempY].color != pieceColor && (board[i][tempY].type == 'Q' || board[i][tempY].type == 'B')) {
					board[x1][y1] = board[x2][y2];
					board[x2][y2] = null;
					return false;
				}
				else {
					break;
				}
			}
		}
		board[y1][x1] = board[y2][x2];
		board[y2][x2] = null;
		return true; //finally return true if none of the false conditions happen
		
	}
	

	public boolean checkmate() {
		//checks if either king is in checkmate
		return false; //placeholder
	}
	
}
