package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * The Stopwatch is a thread-safe factory class implements IStopwatch objects.
 * It includes all stopwatch's methods for getID of the stopwatch, start,stop, lap and reset stopwatch*/
public class Stopwatch implements IStopwatch{

	//Each stopwatch has a unique ID
	private String ID;
	private long StartTime;
	private long StopTime;
	private long ElapsedTime;
	private boolean IsRunning=false;
	private ArrayList<Long> LapTimes= new ArrayList<Long>();
	
	/**This is the constructor of Stopwatch
	 * @param id 
	 *        the id of the stopwatch
	 * @return the id of the stopwatch*/
	public Stopwatch(String id){
		this.ID=id;
	}
	
	/**
	 * Returns the Id of this stopwatch
	 * @return the Id of this stopwatch.  Will never be empty or null.
	 */
	@Override
	public synchronized String getId() {
		return ID;
	}


	/**
	 * Starts the stopwatch if the stopwatch never run before.
	 * Continue the stopwatch if the stopwatch has been stoped
	 * @throws IllegalStateException if called when the stopwatch is already running
	 */
	@Override
	public synchronized void start() {
		//throw exception when the stopwatch is already running
		if(IsRunning==true){
			throw new IllegalStateException();
		}
		IsRunning=true;
		StartTime=System.currentTimeMillis();	
		ElapsedTime=StartTime;
		System.out.println("StartTime is "+ StartTime);
	}
	
	/**
	 * Stores the time elapsed since the last time lap() was called
	 * or since start() was called if this is the first lap.
	 * @throws IllegalStateException if called when the stopwatch isn't running
	 */
	@Override
	public synchronized void lap() {
		//throw exception when the stopwatch isn't running
				if(IsRunning==false){
					throw new IllegalStateException();
				}
				long LapTime=System.currentTimeMillis()-ElapsedTime;
				ElapsedTime=System.currentTimeMillis();
				LapTimes.add(LapTime);
	}
	
	/**
	 * Stops the stopwatch (and records one final lap).
	 * @throws IllegalStateException if called when the stopwatch isn't running
	 */
	@Override
	public synchronized void stop(){
		//throw exception when the stopwatch isn't running
				if(IsRunning==false){
					throw new IllegalStateException();
				}
				IsRunning=false;
				long LapTime=System.currentTimeMillis()-StartTime;
				StopTime=StopTime+System.currentTimeMillis()-StartTime;
				System.out.println("StopTime is "+StopTime);
				System.out.println("Final laptime is "+LapTime);	
	}
	
	/**
	 * Resets the stopwatch.  If the stopwatch is running, this method stops the
	 * watch and resets it.  This also clears all recorded laps.
	 */
	@Override
	public synchronized void reset() {
		this.StartTime=0;
		this.StopTime=0;
		this.ElapsedTime=0;
		this.IsRunning=false;
		this.LapTimes.clear();
	}

	/**
	 * Returns a list of lap times (in milliseconds).  This method can be called at
	 * any time and will not throw an exception.
	 * @return a list of recorded lap times or an empty list if no times are recorded.
	 */
	@Override
	public List<Long> getLapTimes() {
		printLapTimes();
		return LapTimes;
	}
	
	private synchronized void printLapTimes(){
		for(int i=0;i<LapTimes.size();i++){
			System.out.println("the "+i+"th laptime is "+ LapTimes.get(i));
		}
	}
}
