package mine.exercise.codetest.hard.ratinmaze.test;

import java.util.Iterator;

public class RatInMazeApplication implements Application {

	@Override
	public boolean isOk(Position pos) {
		return false;
	}

	@Override
	public boolean isGoal(Position pos) {
		return false;
	}

	@Override
	public boolean markAsPossible(Position pos) {
		return false;
	}

	@Override
	public boolean markAsDeadEnd(Position pos) {
		return false;
	}

	@Override
	public Iterator<Position> iterator(Position pos) {
		return new MazeIterator(pos);
	}

	@Override
	public Position getStart() {
		return null;
	}

	@Override
	public Position getEnd() {
		return null;
	}

}
