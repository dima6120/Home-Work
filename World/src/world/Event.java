/*
 * Event
 * @author dima6120
 */ 

package world;

public class Event {
    public Event(Animal sdr, Action a, Object d) {
        sender = sdr; act = a; data = d;
    }
    Animal sender;
    Action act;
    Object data;
}
