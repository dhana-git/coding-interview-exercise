package mine.exercise.codetest.hard.ratinmaze.test;

public class Position {
    int row;
    int column;

    public Position() {
        this.row = 0;
        this.column = 0;
    }

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "(" + this.row + "," + this.column + ")";
    }

}
