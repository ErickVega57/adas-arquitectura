package src.model;

public class TimeKeeper {

	private static TimeKeeper instance;

	private int minutes = 0;
	private int seconds = 0;

	private TimeKeeper() {
	}

	public static TimeKeeper getInstance() {
		if (instance == null) {
			instance = new TimeKeeper();
		}
		return instance;
	}

	public void TimeKeeper(int min, int sec) {
		if (sec + seconds <= 60) {
			minutes += min;
			seconds = sec + seconds;
		} else {
			minutes += min;
			minutes += 1 * ((sec + seconds) / 60);
			seconds = (sec + seconds) % 60;
		}
	}

	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void resetTime() {
		this.minutes = 0;
		this.seconds = 0;
	}
}
