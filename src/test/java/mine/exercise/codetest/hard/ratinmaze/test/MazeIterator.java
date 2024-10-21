package mine.exercise.codetest.hard.ratinmaze.test;

import java.util.Iterator;

public class MazeIterator implements Iterator<Position> {

    private Position pos;

    public MazeIterator(Position pos) {
        this.pos = pos;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Position next() {
        return null;
    }

}
