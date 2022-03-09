package lab5.general;
public class EventQueue {

    private Event[] queue;

    public EventQueue(){
        queue = new Event[0];
    }

    /**
     * Adds event to queue in correct location
     * 
     * @param e Event to add
     */
    public void push(Event e){
        int n = size();
        Event[] temp = new Event[n + 1];       
        for (int i = 0; i < n; i++){
            temp[i] = queue[i];
        }
        temp[n] = e;
        queue = temp;
        sort();
    }

    /**
     * Sorts queue in ascending order (Bubble sort)
     */
    private void sort() {
        for (int i = 0; i < size(); i++) {
            for (int j = i + 1; j < size(); j++) {
                Event temp;
                if (queue[i].getTime() > queue[j].getTime()) {
                    temp = queue[i];
                    queue[i] = queue[j];
                    queue[j] = temp;
                }
            }
        } 
    }

    /**
     * Removes and returns the next element in queue
     */
    public Event pop() {
        int n = size();
        Event[] temp = new Event[n - 1];
        Event e = queue[0];
        for (int i = 1; i < n; i++) {
            temp[i - 1] = queue[i];
        }
        queue = temp;
        return e;
    }

    /**
     * Returns size of EventQueue
     */
    public int size() {
        return queue.length;
    }
}