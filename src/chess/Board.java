package chess;


public class Board {

	public Piece[][] board;
	public int prevX1;
	public int prevY1;
	public int prevX2;
	public int prevY2;
	public char prevType;
	
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
	
	//returns true if path is open, false if path is blocked
	public boolean pathH( int x1, int y1, int x2, int y2 ) {
		// Same row, move along column - same y, different x
		int begin = x1+1; int end = x2-1; // y1 <= y2
		if( x1 > x2 ){
			begin = x2+1; end = x1-1;
		}
		for( int i = begin; i <= end; i++ ) {
			if( board[y1][i] !=  null ) {
				return false;
			}
		}
		if( board[y2][x2] != null && (board[y2][x2].color == board[y1][x1].color) ){
			return false;
		}
		return true;
	}
	public boolean pathV( int x1, int y1, int x2, int y2 ) {
		// Same column, move along row - same x, different y
		int begin = y1+1; int end = y2-1; // y1 <= y2
		if( y1 > y2 ){
			begin = y2+1; end = y1-1;
		}
		for( int i = begin; i <= end; i++ ) {
			if( board[i][x1] !=  null ) {
				return false;
			}
		}
		if( board[y2][x2] != null && (board[y2][x2].color == board[y1][x1].color) ){
			return false;
		}
		return true;
	}
	public boolean pathD( int x1, int y1, int x2, int y2 ) {
		int lowerR = y1; int lowerC = x1; 
		int higherR = y2; int higherC = x2; 
		if( y1 < y2 ) { 
			lowerR = y2; lowerC = x2; 
			higherR = y1; higherC = x1; 
		}
		boolean goingRight = true; // \
		if ( lowerC < higherC ) {
			goingRight = false; // /
		}
		int k = 1;
		int begin = higherR+1; // 3
		int end = lowerR-1; // 2

		for( int i = begin; i <= end; i++ ) {
			if( goingRight == true ) {
				if( board[i][higherC+k] != null ) { 
					return false; 
				}
			}else {
				if( board[i][higherC-k] != null ) { 
					return false; 
				}
			}
			k++;
		}
		if( board[y2][x2] != null && (board[y1][x1].color == board[y2][x2].color) ) {
			return false;
		}
		return true;
	}
	
	public boolean valid( int x1, int y1, int x2, int y2, char promote, boolean color ) {

		Piece p = board[y1][x1];
		if( p == null ) { //no piece to move
			return false;
		}
		if( p.color != color ) { //trying to move the other color's piece
			return false;
		}
		
		char type = p.type;
		boolean output = true;
		
		//~~~~~~~~~~~~~~~~~~~~~~~Enpassant and Castling~~~~~~~~~~~~~~~~~~~~~~~~~~
		//right or left 2 places 
		if( p.type == 'K' && Math.abs(x2-x1) == 2  ){
			 if( !validCastlingMove ) {
				 return false;
			 }
		 }else if(  p.type == 'P' && isEnpassant ){
		 	if( !validEnpassant ) {
		 		return false
		 	}
		 //~~~~~~~~~~~~~~~~~~~~~~~Enpassant and Castling~~~~~~~~~~~~~~~~~~~~~~~~~~
		 
		 }else { //regular moves
			if ( !p.validMove(x1, y1, x2, y2) ) {
				return false;
			}
		
			boolean h = false;
			boolean v = false;
			boolean d = false;
		
			int direction; //1-h, 2-v, 3-d
			if( y1 == y2 ) {
				h = pathH( x1, y1, x2, y2 );
				direction = 1;
			}else if( x1 == x2 ) {
				v = pathV( x1, y1, x2, y2 );
				direction = 2;
			}else { 
				d = pathD( x1, y1, x2, y2 );
				direction = 3; 
			}
		
			if( type == 'B') { 
				output = d;
			}
			if( type == 'K' ) {
				switch(direction) {
					case 1: output = h;
					case 2: output = v;
				}
			}
			if( type == 'P' ) {
				if( direction == 3) {
					if( board[y2][x2] != null ) {
						output = d;
					}
					output = false;
				}else if( board[y2][x2] != null ) {
					output = false;
				}else {
					output = v;
				}
			}
			if( type == 'Q' ) {
				switch(direction) {
					case 1: output = h;
					case 2: output = v;
					case 3: output = d;
				}
			}
			if( type == 'R' ) {
				switch( direction ) {
				case 1: output = h;
				case 2: output = v;
				}
			}
		}
		if( output == false ) {
			return output;
		}
		if( check( x1, y1, x2, y2, promote, color) ) { //moves puts own king in check
			return false;
		}
		return output;
	}

	public void move( int x1, int y1, int x2, int y2, char c ) {
		Piece p = board[y1][x1];
		//~~~~~~~~~~~~~~~~~~~~~~~~~ENPASSANT AND CASTLING~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		 if( p.type == 'K' && Math.abs(x2-x1) == 2 ){
		 	castlingMove - may or may not be a different method
		 	return;
		 }
		 */
		
		if( p.type == 'P' && isEnpassant ){
			enpassantMove - may or may not be a different method
			return;
		}
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~ENPASSANT AND CASTLING~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		board[y1][x1] = null;
		board[y2][x2] = p;
		if( p.type == 'K' || p.type == 'R') {
			p.moveYet = true;
		}
		
		//pawn needs to be promoted
		if( ( y2 == 0 || y2 == 7 ) && p.type == 'P' ) {
			promote( x2, y2, c );
		}
		return;
	}

	public boolean validPromote( int x1, int y1, int x2, int y2, char c ) {
		//checks if x1 y1 x2 y2 is a pawn moving to the end of the board
		//checks if c is a valid piece to promote to
		if (c != 'Q' || c != 'R' || c != 'N' || c != 'B' ) {
			return false;
		}
		// 1st: if piece is a pawn. 2nd: if validate a pawn's move. 3rd: Final move is at the end of the board
		if (board[x1][y1].type == 'P' && board[x1][y1].validMove(x1, y1, x2, y2) && (x2 == 0 || x2 == 7) ) {
			return true;
		}
		return false;
	}
	
	public void promote( int x, int y, char c ) {
		//promote piece at x y to piece c
		boolean pieceColor = board[y][x].color;
		String pieceChar = board[y][x].name.substring(0, 1); //just want the first char, either 'b' or 'w'
		
		if(c == 'R') {
			board[y][x] =  new Rook(pieceColor, pieceChar+"R");
		}
		else if(c == 'B') {
			board[y][x] =  new Bishop(pieceColor, pieceChar+"B");
		}
		else if(c == 'N') {
			board[y][x] =  new Knight(pieceColor, pieceChar+"N");
		}
		else {
			board[y][x] =  new Queen(pieceColor, pieceChar+"Q");
		}
		return;
	}
	
	public boolean check( int x1, int y1, int x2, int y2, char promote, boolean pieceColor ) {
		//check if move puts king of specified color in check
		
		Piece[][] copy = this.board; 
		move( x1, y1, x2, y2, promote);
		
		//get king's coordinates
		int kingX= 0, kingY= 0;	
		for (int  y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				Piece p = board[y][x];
				if( p != null && p.type == 'K' && p.color == pieceColor) {
					kingY = y;
					kingX = x;
				}
			}
		}
		//check if any enemy pieces has valid moves to king
		boolean check = false;
		for( int y = 0; y < 8; y++ ) {
			for( int x = 0; x < 8; x++ ) {
				Piece p = board[y][x];
				if( p != null && p.color == !pieceColor &&  valid(x, y, kingX, kingY, promote, !pieceColor) ) {
					check = true;
				}
			}
		}
		
		this.board = copy; //reverts all moves
		return check;
	}
	
	public boolean checkmate( boolean color ) {
		//have to check if any piece on board can get the king out of check by killing/blocking/the king moving itself 
		//only checkmate if there is no moves for any piece that gets the king out of check
		boolean nonCheck = false; //assume there is no noncheck moves until noncheck move is found
		for( int y = 0; y < 8; y++ ) { 
			for( int x = 0; x < 8; x++ ) {
				//finds every piece of color on board
				if( board[y][x] != null && board[y][x].color == color ) {
					//checks every space on board and see if its a valid move for that piece
					for( int y2 = 0; y2 < 8; y2++ ) {
						for( int x2 = 0; x2 < 8; x2++ ) {
							if( valid( x, y, x2, y2, '/', color ) ) {
								return true;
							}
						}
					}
				}
			}
		}
		return nonCheck; 
	}
	
	
	
	/*need to somehow have access to previous move. After a scan in: set board.prevX1,board.prevY1, board.prevX2
	 * board.prevY2, and board.prevType to the "g3 g2" that was entered. 
	 */
	public boolean enpassant(int x1, int y1, int x2, int y2, char type) {
		//1)check if both are pawns. 2)check if the prev move was a double move. 
		if(prevType != 'P' || type != 'P' || prevX1 != prevX2+2 || prevX1 != prevX2-2) {
			return false;
		}
		
		if(prevX1+1 > 7) {//out of bounds. just check left side (if they are next to each other)
			if(board[prevY2][prevX2-1] == board[y1][x1]) {
				//yes both pawns are right next to each other.
				//check if the movement (x2,y2) is the correct movement
				if(y2 == prevY2 && board[y2][x2] == null) { //pawn has moved into the same column as prev pawn and the spot is empty
					return true;
				}
				
			}
		}
		else if(prevX1-1 < 0) {//out of bounds. just check right side
			if(board[prevY2][prevX2+1] == board[y1][x1]) {
				if(y2 == prevY2 && board[y2][x2] == null) {
					return true;
				}
			}
		}
		else {//check both left and right
			if(board[prevY2][prevX2+1] == board[y1][x1] || board[prevY1][prevX1-1] == board[y1][x1]) {
				if(y2 == prevY2 && board[y2][x2] == null) {
					return true;
				}
			}
		}
		return false;
	}
	
	/*I'm going to have to make an update to Rook and King's field: add boolean moveYet = false. We will set it to
	 * true when we move a rook or king. We might have to do piece to have this field.
	 */
	public boolean castling(int x1, int y1, int x2, int y2, boolean pieceColor) {
		/*king and rook cannot move yet
		 * cannot castle while in check, through a check(example: king moves 2 spot to the left but the 1st spot it
		 * has to get through would have made it a check.), and to a location that will cause a check(2nd spot).
		 */
		int kingX= 0, kingY=0;
		
		if(pieceColor = false) {
			kingX=0;
			kingY=4;
		}
		else {
			kingX=7;
			kingY=4;
		}
		
		if(pieceColor == false && !board[kingX][kingY].moveYet) {//piece to have field moveYet?
			//1) current spot-> !check 
			//2) 1 spot left or right depending on which way he/she wants to castle-> !check
			//3) 2 spot left or right depending on direction of castle(where king will be)-> !check
			//if all 3 scenarios are true -> return true;
		}
		if(pieceColor == true && !board[kingX][kingY].moveYet) {
			//same as above
		}

		return false;//placeholder
	}
	
}
