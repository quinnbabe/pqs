package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;


/**
 * The StopwatchFactory is a thread-safe factory class for IStopwatch objects.
 * It maintains references to all created IStopwatch objects and provides a
 * convenient method for getting a list of those objects.
 *
 */
public class StopwatchFactory {
    private static ArrayList<Stopwatch> Stopwatches=new ArrayList<Stopwatch>();
    
    /**
     * Creates and returns a new Stopwatch object
     * @param id The identifier of the new object
     * @return The new IStopwatch object
     * @throws IllegalArgumentException if <code>id</code> is empty, null, or already
   *     taken.
     */
    public static Stopwatch getStopwatch(String id) {
        addStopwatch(id);
        return new Stopwatch(id);   
    }
    
    //make the constructor private
    private StopwatchFactory(){ 
    }
    
    //If the id has exist or null, throws IllegalArgumentException, otherwise add this new stopwatch
    private synchronized static void addStopwatch(String id){
        boolean exist_id=false;
        //@throws IllegalArgumentException if <code>id</code> is empty
        if(id==null){
            throw new IllegalArgumentException();
        }
        //@throws IllegalArgumentException if <code>id</code> is already taken
        for(int i=0;i<Stopwatches.size();i++){
            if(id.equals(Stopwatches.get(i).getId())){
                exist_id=true;
                throw new IllegalArgumentException();
            }
        }
        if(exist_id==false){
            Stopwatches.add(new Stopwatch(id));
        }
    }

    /**
     * Returns a list of all created stopwatches
     * @return a List of all creates Stopwatch objects.  Returns an empty
     * list if no Stopwatches have been created.
     */
    public synchronized static List<Stopwatch> getStopwatches() {
        if(Stopwatches.size()==0){
            System.out.println("No Stopwatches have been created!");
        }
        else{
            for(int i=0;i<Stopwatches.size();i++){
                System.out.println("The list of stopwatch is " + Stopwatches.get(i).getId());
            }
        }
        return Stopwatches;
    }
    
}
