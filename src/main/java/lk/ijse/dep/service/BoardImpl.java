package lk.ijse.dep.service;

public  class BoardImpl implements Board{
    private Piece[][] pieces;
    private BoardUI boardUI;

public BoardImpl(BoardUI boardUI){
        this.boardUI=boardUI;
        pieces=new Piece[NUM_OF_COLS][NUM_OF_ROWS];

        for (int i=0; i< pieces.length; i++){
            for (int j=0; j< pieces[i].length; j++){
                pieces [i][j]= Piece.EMPTY;
            }
        }
}

    @Override
    public BoardUI getBoardUI() {
        return null;
    }

    @Override
    public int findNextAvailableSpot(int col) {
    int count=0;
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[col][i] == Piece.EMPTY);
                count++;
        }
        if(count == 0){
            count =-1;
        }
        return count;
    }

    @Override
    public boolean isLegalMoves() {
      return false;
    }


    @Override
   public boolean isLegalMoves(int col) {
        boolean legalMove = true;
        int availableSpot = findNextAvailableSpot(col);

        if(availableSpot == -1)
            legalMove = false;
        return legalMove;
    }


    @Override
    public boolean existLegalMoves() {
       boolean legalMove = false;

        for (int i = 0; i <pieces.length ; i++) {
            for(int j=0; i< pieces[i].length; j++){
                if(pieces[i][i] == Piece.EMPTY){
                    legalMove = true;
                }
            }
        }
        return legalMove;
    }

    @Override
    public void updateMoves(int col, Piece move) {
        int row = findNextAvailableSpot(col);
        pieces[row][col]=move;   // put the passed piece (move) to next available spot
    }

    @Override
    public Winner findWinner() {

        int rowCount;

        for (int col = 0; col < NUM_OF_COLS; col++) {
            int rowForLoop = findNextAvailableSpot(col) == -1 ? NUM_OF_ROWS : findNextAvailableSpot(col);
            System.out.println("rowForLoop " + rowForLoop);
            rowCount = 0;
            System.out.println("col " + col);

            for (int row = 1; row < rowForLoop; row++) {
                System.out.println(row);
                System.out.println("pieces[col][row]" + pieces[col][row]);
                if (pieces[col][row] .equals(pieces[col][row-1])) {
                    rowCount++;
                    System.out.println("rowCount " + rowCount);
                    if (rowCount == 3) {

                        return new Winner(pieces[col][row], col, row-3, col, row );
                    }
                } else {
                    rowCount = 0;
                }
            }

        }


        for(int row = 0; row <NUM_OF_ROWS; ++row) {
            int count = 1;

            for(int col = 1; col <NUM_OF_COLS; ++col) {
                if (pieces[col][row] == pieces[col-1][row] ) {
                    count++;
                    if (count == 4) {
                        return new Winner(pieces[col][row], col - 3, row, col, row);
                    }
                } else {
                    count = 1;

                }
            }
        }
        return new Winner(Piece.EMPTY);
    }

}