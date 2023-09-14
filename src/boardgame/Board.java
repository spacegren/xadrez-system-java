package boardgame;

public class Board {
    private int rows;
    private int columns;
    private Peace[][] peaces;
    public Board(){

    }
    public Board(int rows,int columns) {
        if (rows < 1 ||columns < 1){
            throw new BoardExeption("ERROR CREATING BOARD : MUST BE AT LEAST 1 ROW AND 1 COLUN");
        }

        this.rows = rows;
        this.columns = columns;
        peaces = new Peace[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Peace peace(int rows , int columns) {
        if (!positionExists(rows, columns)){
            throw new BoardExeption("POSITION NOT ON THE BOARD : ");
        }
        return peaces[rows][columns];

    }
    public Peace peace(Position position){
        if (!positionExists(position)){
            throw  new BoardExeption("POSIITON NOT ON THE BOARD ");
        }
        return peaces[position.getRow()][position.getColumn()];
    }
    public void placePeace(Peace peace , Position position){
        if (thereIsaPeace(position)){
            throw new BoardExeption("there is alredy a piace on position" + position);
        }
        peaces[position.getRow()][position.getColumn()] = peace;
        peace.position = position;
    }
    private boolean positionExists(int rows , int columns){
        return rows >= 0 && rows < rows && columns >= 0 && columns < columns;
    }
    public boolean positionExists(Position position){
        return positionExists(position.getRow() , position.getColumn());
    }
    public boolean thereIsaPeace(Position position){
        if (!positionExists(position)){
            throw new BoardExeption("position not on the board");
        }
        return peace(position) != null;
    }

}
