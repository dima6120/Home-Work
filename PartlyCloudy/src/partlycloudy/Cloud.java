/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Cloud
 * @author dima6120
 */

package partlycloudy;


public class Cloud {
    private IDayLight daylight;
    private ILuminary luminary;
    private IWind wind;
    private IMagic magic; 
    
    public Cloud(IWind w, ILuminary l, IDayLight dl, IMagic m) {
        wind = w;
        luminary = l;
        daylight = dl;
        magic = m;
    }

    private Creature internalCreate()
    {
        if (luminary.isShining()) {
            if (wind.getPower() == 2 || wind.getPower() == 3) {
                if (daylight.current() == DayLightType.MORNING) {
                    return new Creature(CreatureType.KITTTEN);
                }
                return null;
            }
            if (wind.getPower() == 4 || wind.getPower() == 5) {
                if (daylight.current() == DayLightType.NIGHT) {
                    return new Creature(CreatureType.HEDGEHOG);
                }
                return null;
            }
            if (wind.getPower() == 8 || wind.getPower() == 9) {
                if (daylight.current() == DayLightType.DAY) {
                    return new Creature(CreatureType.BALLOON);
                }
                return null;
            }
        } else {
            if (wind.getPower() == 1) {
                if (daylight.current() == DayLightType.DAY) {
                    return new Creature(CreatureType.PUPPY);
                }
                return null;
            } 
            if (wind.getPower() == 6) {
                if (daylight.current() == DayLightType.EVENING) {
                    return new Creature(CreatureType.PIGLET);
                }
                return null;
            } 
            if (wind.getPower() == 7) {
                if (daylight.current() == DayLightType.NIGHT) {
                    return new Creature(CreatureType.BAT);
                }
                return null;
            } 
            if (wind.getPower() == 10) {
                if (daylight.current() == DayLightType.MORNING) {
                    return new Creature(CreatureType.BEARCUB);
                }
                return null;
            } 
        }
        return null;
    }
 
    public Creature create()
    {
        Creature creature = internalCreate();
        
        if (creature != null) { 
            switch (creature.getCreatureType()) {
                case PUPPY:
                case PIGLET:
                case BALLOON:
                    magic.callDaemon(creature.getCreatureType());
                    break;
                case KITTTEN:
                case HEDGEHOG:
                case BAT:  
                case BEARCUB:
                    magic.callStork(creature.getCreatureType());
            }
        }

        return creature;
    }
}
