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
		board[0][0] = new Rook(false,"bR"); //color, name
		board[0][7] = new Rook(false,"bR");
		board[0][1] = new Knight(false,"bN");
		board[0][6] = new Knight(false,"bN");
		//board[0][2] = new Bishop(false,"bB");
		board[0][5] = new Bishop(false,"bB");
		//board[0][3] = new Queen(false,"bQ");
		board[0][4] = new King(false,"bK");	
		for(int i = 0; i<8 ; i++) {
			board[1][i] = new Pawn(false,"bp");
		}
		//create white pieces
		board[7][0] = new Rook(true,"wR"); //color, name
		board[7][7] = new Rook(true,"wR");
		//board[7][1] = new Knight(true,"wN");
		board[7][6] = new Knight(true,"wN");
		//board[7][2] = new Bishop(true,"wB");
		board[7][5] = new Bishop(true,"wB");
		//board[7][3] = new Queen(true,"wQ");
		board[7][4] = new King(true,"wK");
		for(int i = 0; i<8 ; i++) {
			board[6][i] = new Pawn(true,"wp");
		}
		board[1][1] = null;
		board[6][1] = new Pawn(false, "bp");
		board[6][0] = null;
		board[1][0] = new Pawn(true, "wp");
		/*
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
		//board[7][1] = new Knight(true,"wN");
		board[7][6] = new Knight(true,"wN");
		//board[7][2] = new Bishop(true,"wB");
		board[7][5] = new Bishop(true,"wB");
		//board[7][3] = new Queen(true,"wQ");
		board[7][4] = new King(true,"wK");
		for(int i = 0; i<8 ; i++) {
			board[6][i] = new Pawn(true,"wp");
		}
		*/
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
			//System.out.println(" " + i);
			colDis--;
		}
		System.out.println(" a  b  c  d  e  f  g  h");
		//System.out.println(" 0  1  2  3  4  5  6  7 ");
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
		//System.out.println("goingRight: " + goingRight);
		for( int i = begin; i <= end; i++ ) {
			if( goingRight == true ) {
				//int j = higherC + k;
				//System.out.println("["+ i +"][" + j + "]");
				if( board[i][higherC+k] != null ) { 
					//System.out.println("pathD false 1");
					return false; 
				}
			}else {
				if( board[i][higherC-k] != null ) { 
					//int j = higherC + k;
					//System.out.println("["+ i +"][" + j + "]");
					//System.out.println("pathD false 2");
					return false; 
				}
			}
			k++;
		}
		if( board[y2][x2] != null && (board[y1][x1].color == board[y2][x2].color) ) {
			//System.out.println("pathD false 3");
			return false;
		}
		//System.out.println("pathD true");
		return true;
	}
	
	public boolean valid( int x1, int y1, int x2, int y2, char promote, boolean color ) {

		Piece p = board[y1][x1];
		if( p == null ) { //no piece to move
			System.out.println("no piece");
			return false;
		}
		if( p.color != color ) { //trying to move the other color's piece
			System.out.println("other team's piece");
			return false;
		}
		
		char type = p.type;
		boolean output = true;
		
		if( p.type == 'K' && Math.abs(x2-x1) == 2 && y2-y1==0){ 
			 if( !validCastling(x1,y1,x2,y2,color) ) {
				 System.out.println("invalid castling");
				 return false;
			 }
		 }else if(  p.type == 'P' && x2 == prevX2 && Math.abs(prevY2-y2) == 1 && board[y2][x2] == null ){//isEnpassant
		 	if( !validEnpassant(x1,y1,x2,y2,type) ) {
		 		System.out.println("invalid Enpassant");
		 		return false;
		 	}		 
		 }else { //regular moves
			if ( !p.validMove(x1, y1, x2, y2) ) {
				//System.out.println("invalid for piece");
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
					case 1: output = h; break;
					case 2: output = v; break;
				}
			}
			if( type == 'P' ) {
				//System.out.println("direction: " + direction );
				if( direction == 3) {
					if( board[y2][x2] != null ) {
						output = d;
					}else{
						output = false;
					}
				}else if( board[y2][x2] != null ) {
					output = false;
				}else {
					output = v;
				}
			}
			if( type == 'Q' ) {
				switch(direction) {
					case 1: output = h; break;
					case 2: output = v; break;
					case 3: output = d; break;
				}
			}
			if( type == 'R' ) {
				switch( direction ) {
				case 1: output = h; break;
				case 2: output = v; break;
				}
			}
		}
		if( output == false ) {
			//System.out.println("path blocked");
			return output;
		}
		/*
		if( check( x1, y1, x2, y2, promote, color) ) { //moves puts own king in check
			//System.out.println("puts king in check");
			return false;
		}
		*/
		System.out.println("valid: " + output);
		return output;
	}

	public void move( int x1, int y1, int x2, int y2, char c ) {
		Piece p = board[y1][x1];
		if( p.type == 'K' && Math.abs(x2-x1) == 2 && y2-y1 == 0){
			board[y2][x2] = p;
			board[y1][x1] = null;
			Piece p2 = null;
			if(board[y2][x2-2] != null && board[y2][x2-2].type == 'R') {
				p2 = board[y2][x2-2];
				board[y2][x2+1] = p2;
				board[y2][x2-2] = null;
			}
			else if(board[y2][x2+1] != null && board[y2][x2+1].type == 'R') {
				p2 = board[y2][x2+1];
				board[y2][x2-1] = p2;
				board[y2][x2+1] = null;
			}
		 	return;
		}
		
		if( p.type == 'P' && x2 == prevX2 && Math.abs(prevY2-y2) == 1 && board[y2][x2] == null ){
			board[y2][x2] = p;
			board[y1][x1] = null;
			if(p.color == false) {
				board[y2-1][x2] = null;
			}
			else {
				board[y2+1][x2] = null;
			}
			return;
		}
		
		board[y1][x1] = null;
		board[y2][x2] = p;
		
		//pawn needs to be promoted
		if( ( y2 == 0 || y2 == 7 ) && p.type == 'P' ) {
			promote( x2, y2, c );
		}
		return;
	}

	public boolean validPromote( int x1, int y1, int x2, int y2, char c ) {
		//checks if x1 y1 x2 y2 is a pawn moving to the end of the board
		//checks if c is a valid piece to promote to
		//System.out.println("c: " + c);
		if (c != 'Q' && c != 'R' && c != 'N' && c != 'B' ) {
			System.out.println("not QRNB");
			return false;
		}
		// 1st: if piece is a pawn. 2nd: if validate a pawn's move. 3rd: Final move is at the end of the board
		/*
		System.out.println("type: " + board[y1][x1].type );
		System.out.println("type: " + board[])
		*/
		if (board[y1][x1].type == 'P' && board[y1][x1].validMove(x1, y1, x2, y2) && (y2 == 0 || y2 == 7) ) {
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
		//System.out.println("Board.check:");
		//check if move puts king of specified color in check
		Piece[][] copy = new Piece[8][8];
		for( int i = 0; i < 8; i++ ) {
			for( int k = 0; k < 8; k++ ) {
				copy[i][k] = this.board[i][k];
			}
		}
		System.out.println("start:");
		print();
		move( x1, y1, x2, y2, promote);
		System.out.println("moved:");
		print();
		
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
		System.out.println("King[" + kingY + "][" + kingX + "]");
		System.out.println("King color: " + pieceColor);
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
		//System.out.println("reverted:");
		//print();
		return check;
	}
	
	public boolean checkmate( boolean color ) {
		//System.out.println("Board.Checkmate");
		//have to check if any piece on board can get the king out of check by killing/blocking/the king moving itself 
		//only checkmate if there is no moves for any piece that gets the king out of check
		boolean nonCheck = true; //assume there is no noncheck moves until noncheck move is found
		for( int y = 0; y < 8; y++ ) { 
			for( int x = 0; x < 8; x++ ) {
				//finds every piece of color on board
				//System.out.println("[" + y + "][" + x+ "]");m;
				if( board[y][x] != null && board[y][x].color == color ) {
					//checks every space on board and see if its a valid move for that piece
					//System.out.println("found: " + board[y][x].name);
					for( int y2 = 0; y2 < 8; y2++ ) {
						for( int x2 = 0; x2 < 8; x2++ ) {
							if( valid( x, y, x2, y2, '/', color ) ) {
								return false;
							}
						}
					}
				}
			}
		}
		return nonCheck; 
	}
	
	public boolean validEnpassant(int x1, int y1, int x2, int y2, char type) {
		//System.out.println("Valid Enpassant:");
		//1)check if both are not pawns. 2)check if the prev move was not a double move vertical. 
		if(prevType != 'P' || type != 'P' || Math.abs(prevY2-prevY1) != 2) {
			return false;
		}
		//move() not called, so piece did not move yet. Compare initial move (x1,y1) with final move (prevX2,prevY2)
		if(prevX2+1 > 7) {//out of bounds. just check left side (if they are next to each other)
			if(board[prevY2][prevX2-1] == board[y1][x1]) {
				//yes both pawns are right next to each other.
				//check if the movement (x2,y2) is the correct movement
				if(x2 == prevX2 && Math.abs(prevY2-y2) == 1 && board[y2][x2] == null) { //pawn has moved into the same column as prev pawn and the spot is empty
					return true;
				}
				
			}
		}
		else if(prevX2-1 < 0) {//out of bounds. just check right side
			if(board[prevY2][prevX2+1] == board[y1][x1]) {
				if(x2 == prevX2 && Math.abs(prevY2-y2) == 1 && board[y2][x2] == null) {
					return true;
				}
			}
		}
		else {//check both left and right
			if(board[prevY2][prevX2+1] == board[y1][x1] || board[prevY2][prevX2-1] == board[y1][x1]) {
				if(x2 == prevX2 && Math.abs(prevY2-y2) == 1 && board[y2][x2] == null) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean validCastling(int x1, int y1, int x2, int y2, boolean pieceColor) {
		/*king and rook cannot move yet
		 * cannot castle while in check, through a check(example: king moves 2 spot to the left but the 1st spot it
		 * has to get through would have made it a check.), and to a location that will cause a check(2nd spot).
		 */
		boolean castleRight = false;
		if(x2 - x1 > 0) {
			castleRight = true;
		}
		//System.out.println("castleRight = "+castleRight + " "+pathH(4,0,1,0)+ " "+ pieceColor);
		//System.out.println("board[0][4] = "+board[0][4].type + " "+board[0][4].moveYet+ " "+ board[0][0].type + " "+board[0][0].moveYet);
		//castling to black's left
		if(pieceColor == false && castleRight == false && board[0][0].type == 'R' && board[0][0].moveYet == false 
		&& board[0][4].type == 'K' && board[0][4].moveYet == false && pathH(4,0,1,0) == true) {
			//1) current spot-> !check 
			//2) 1 spot left or right depending on which way he/she wants to castle-> !check
			//3) 2 spot left or right depending on direction of castle(where king will be)-> !check
			//if all 3 scenarios are true -> return true;
			if(!check(4,0,4,0,'/',pieceColor) && !check(4,0,3,0,'/',pieceColor) && !check(4,0,2,0,'/',pieceColor)) {;
				return true;
			}
		}
		//castling to black's right
		else if(pieceColor == false && castleRight == true && board[0][7].type == 'R' && board[0][7].moveYet == false 
		&& board[0][4].type == 'K' && board[0][4].moveYet == false && pathH(4,0,6,0) == true) {
			if(!check(4,0,4,0,'/',pieceColor) && !check(4,0,5,0,'/',pieceColor) && !check(4,0,6,0,'/',pieceColor)) {
				return true;
			}
		}
		//castling to white's left
		else if(pieceColor == true && castleRight == false && board[7][0].type == 'R' && board[7][0].moveYet == false 
		&& board[7][4].type == 'K' && board[7][4].moveYet == false && pathH(4,7,1,7) == true) {
			System.out.println("1");
			if(!check(4,7,4,7,'/',pieceColor) && !check(4,7,3,7,'/',pieceColor) && !check(4,7,2,7,'/',pieceColor)) {
				return true;
			}
		}
		//castling to white's right
		else if(pieceColor == true && castleRight == true && board[7][7].type == 'R' && board[7][7].moveYet == false
		&& board[7][4].type == 'K' && board[7][4].moveYet == false && pathH(4,7,6,7) == true) {
			if(!check(4,7,4,7,'/',pieceColor) && !check(4,7,5,7,'/',pieceColor) && !check(4,7,6,7,'/',pieceColor)) {
				return true;
			}
		}
		else {
			return false;
		}
		return false;
	}
	
	public char getType(int x2, int y2) {
		return board[y2][x2].type;
	}
	
}
