/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Main
 * @author dima6120
 */

package main;

import partlycloudy.BMagic;
import partlycloudy.Cloud;
import partlycloudy.DayLightType;
import partlycloudy.IDayLight;
import partlycloudy.ILuminary;
import partlycloudy.IWind;


class MWind implements IWind {
    private int i;
    
    public MWind(int i) {
        this.i = i;
    }
    @Override
    public int getPower() {
        return i;
    }
}


class MLum implements ILuminary {
    private boolean b;
    
    public MLum(boolean b) {
        this.b = b;
    }
    @Override
    public boolean isShining() {
        return b;
    }
    
}



class MDL implements IDayLight {
    private DayLightType dl;
    
    public MDL(DayLightType dl) {
        this.dl = dl;
    }
    @Override
    public DayLightType current() {
        return dl;
    }
    
}


public class Main {
    public static void main(String[] args) {
        Cloud c = new Cloud(new MWind(0), new MLum(true), new MDL(DayLightType.DAY), new BMagic());
        System.out.println(c.create().getCreatureType());
    }
}
