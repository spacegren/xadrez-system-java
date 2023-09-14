package boardgame;

public class Board {
    private int rows;
    private int columns;
    private Peace[][] peaces;
    public Board(){

    }
    public Board(int rows,int columns) {
        super();
        this.rows = rows;
        this.columns = columns;
        peaces = new Peace[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Peace peace(int rows , int columns) {
        return peaces[rows][columns];
    }
    public Peace peace(Position position){
        return peaces[position.getRow()][position.getColumn()];
    }

}
