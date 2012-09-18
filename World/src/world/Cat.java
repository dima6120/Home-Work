/*
 * Cat
 * @author dima6120
 */ 

package world;

import static world.Print.*;

public class Cat extends Animal{
    public Cat (String name, int age, float weigth) {
        super(name, age, weigth, AType.cat);
    }
    @Override
    public Event Voice(TVoice v) {
        print("Кот " + getName(), false);
        switch (v) {
            case def: append(": *мяу*\n"); break;
            case joy: append(": *мур*\n"); break;
            case anger: append(": *шипение*\n");
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
                            case anger: Voice(TVoice.anger); break;
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
                append("кота " + getName()); print(); print("=>", true);
                Voice(TVoice.anger);
        }
    }

    @Override
    public Event PutEvent(Action act) {
        Object d = null;
        switch (act) {
            case voice: Voice(TVoice.def); d = TVoice.def; break;
            case attack: Voice(TVoice.anger);
                         print("Кот " + getName() + " напал на ", false);
        }
        return new Event(this, act, d);
    }

    @Override
    public void ShowInfo() {
        print("Инфо: Кот ", false); 
        super.ShowInfo();
    }
    
}
