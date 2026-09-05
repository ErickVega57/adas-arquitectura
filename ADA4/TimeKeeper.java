public class TimeKeeper {
    //a class to keep track of the total seconds and minuntes the player has used to get to a leve
    int minutes=0; 
    int seconds=0; 

    public void TimeKeeper(int min, int sec) { 
	    if(sec + seconds <=60) {
	        minutes+=min;
	        seconds=sec+seconds;
        } else {
	        minutes+=min;
	        minutes+=1*((sec+seconds)/60);
	        seconds=(sec+seconds)%60;
	    }
    }//end TimeKeeper
	
	public int getMinutes() {
	    return minutes;
	}
	
	public int getSeconds() {
	    return seconds;
    }	
}//end class
