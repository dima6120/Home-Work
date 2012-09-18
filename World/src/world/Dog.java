/*
 * Dog
 * @author dima6120
 */ 

package world;

import static world.Print.*;

public class Dog extends Animal{
    public Dog (String name, int age, float weigth) {
        super(name, age, weigth, AType.dog);
    }
    
    @Override
    public Event Voice(TVoice v) {
        print("Пёс " + getName(), false);
        switch (v) {
            case def: append(": *гав*\n"); break;
            case joy: append(": *гав гав*\n"); break;
            case anger: append(": *рычание*\n");
        }
        
        return new Event(this, Action.voice, v);
    }

    @Override
    public void HandlerEvent(Event e) {
        switch (e.act) {
            case voice: 
                print("=>", true);
                switch (e.sender.getAType()) {
                    case cat: 
                        switch ((TVoice)e.data) {
                            case anger: 
                                print("=>", true);
                                e.sender.HandlerEvent(PutEvent(Action.attack));
                                break;
                            case joy:
                            case def: Voice(TVoice.def); 
                        }
                        break;
                    case dog: 
                        switch ((TVoice)e.data) {
                            case anger: 
                                e.sender.HandlerEvent(PutEvent(Action.attack));
                                break;
                            case joy:  
                            case def: Voice(TVoice.def); 
                        }  
                } 
                break;
            case attack: 
                append("пса " + getName()); print(); print("=>", true);
                Voice(TVoice.anger);
        }
    }

    @Override
    public Event PutEvent(Action act) {
        Object d = null;
        switch (act) {
            case voice: Voice(TVoice.def); d = TVoice.def; break;
            case attack: Voice(TVoice.anger);
                         print("Пёс " + getName() + " напал на ", false);
        }
        return new Event(this, act, d);
    }
    
    @Override
    public void ShowInfo() {
        print("Инфо: Пёс ", false); 
        super.ShowInfo();
    }
}
