/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Event
 * @author dima6120
 */

package solarsystem;
//событие
public class Event {
    public Event(TObject _sender, TObject _addr, TEvent _type) {
        sender = _sender; addr = _addr; type = _type;
    }
    protected TObject sender; //кто
    protected TObject addr; //кому
    protected TEvent type; //зачем
}
