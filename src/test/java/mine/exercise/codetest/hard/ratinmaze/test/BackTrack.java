package mine.exercise.codetest.hard.ratinmaze.test;

import java.util.Iterator;

public class BackTrack {
	private Application app;

	public BackTrack(Application app) {
		this.app = app;
	}

	public boolean tryToReachGoal(Position pos) {
		Iterator<Position> iter = app.iterator(pos);
		while (iter.hasNext()) {
			pos = iter.next();
			if (app.isOk(pos)) {
				app.markAsPossible(pos);
				if (app.isGoal(pos) || tryToReachGoal(pos)) {
					return true;
				}
				app.markAsDeadEnd(pos);
			}
		}
		return false;
	}
}
