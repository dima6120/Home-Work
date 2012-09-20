/**
 * Event
 * @author dima6120
 */

package solarsystem;

public class Event {
    public Event(TObject _sender, TObject _addr, TEvent _type) {
        sender = _sender; addr = _addr; type = _type;
    }
    protected TObject sender;
    protected TObject addr;
    protected TEvent type;
}
