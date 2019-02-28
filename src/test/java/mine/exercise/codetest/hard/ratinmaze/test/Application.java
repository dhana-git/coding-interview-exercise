package mine.exercise.codetest.hard.ratinmaze.test;

import java.util.Iterator;

public interface Application {
	boolean isOk(Position pos);

	boolean isGoal(Position pos);

	boolean markAsPossible(Position pos);

	boolean markAsDeadEnd(Position pos);

	Position getStart();

	Position getEnd();

	Iterator<Position> iterator(Position pos);
}
