/**
 * Animal
 * @author Xiao-Mei
 */

package world;

import static world.Print.*;

public abstract class Animal {
    private AType atype;
    private int age;
    private float weigth;
    private String name;
    Animal(String n, int a, float w, AType t) {
        name = n; age = a; weigth = w; atype = t;
        String s = null;
        
        switch (atype) {
            case cat: s = "кот "; break;
            case dog: s = "пёс "; 
        }
        
        print("Появился " + s + name + ", возраст " + 
                Integer.toString(age) + ", вес " + Float.toString(weigth) +
                " кг", true); 
    }
    public abstract Event Voice(TVoice v);
    public abstract void HandlerEvent(Event e);
    public abstract Event PutEvent(Action act);
    
    void ShowInfo() {
        append(name + ", возраст " + 
                Integer.toString(age) + ", вес " + Float.toString(weigth) +
                " кг"); 
    }
    
    public int getAge() {
        return age;
    }
    void setAge(int new_age) {
        age = new_age;
    }
    public float getWeigth() {
        return weigth;
    }
    void setWeigth(float new_w) {
        weigth = new_w;
    }
    AType getAType() {
        return atype;
    }
    public void setName(String new_n) {
        name = new_n;
    }
    public String getName() {
        return name;
    }
}