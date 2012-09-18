/**
 * Main
 * @author Xiao-Mei
 */

import world.*;

public class main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Василий", 4, 5.0f),
            cat2 = new Cat("Борис", 4, 5.0f);
        Dog dog1 = new Dog("Пентон", 5, 20.0f);
        cat1.HandlerEvent(cat2.PutEvent(Action.voice));
        cat2.HandlerEvent(cat1.PutEvent(Action.attack));
        cat2.HandlerEvent(cat1.Voice(TVoice.anger));
        cat1.HandlerEvent(dog1.Voice(TVoice.anger));
    }
}
