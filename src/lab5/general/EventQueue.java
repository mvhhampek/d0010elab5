package lab5.general;

public class EventQueue {

    private Event[] queue;

    public EventQueue(){
        queue = new Event[0];
    }


    /**
     * Adds event to queue
     */
    public void push(Event e){
        int n = size();
        Event[] temp = new Event[n + 1];       
        for (int i = 0; i < n; i++){
            temp[i] = queue[i];
        }
        temp[n] = e;
        queue = temp;
    }

    /**
     * Removes and returns the next element in queue
     */
    public Event pop(){
        sort();
        int n = size();
        Event[] temp = new Event[n - 1];
        Event e = queue[0];
        for (int i = 1; i < n; i++) {
            temp[i - 1] = queue[i];
        }
        queue = temp;
        return e;
    }
 
    public void sort(){
        // sortera queue enligt tid(?)
    }


    public size(){
        return queue.length;
    }

}
