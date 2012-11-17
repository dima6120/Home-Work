/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Cloud
 * @author dima6120
 */

package partlycloudy;


public class Cloud {
    private IDayLight daylight = new IDayLight() {

        @Override
        public DayLightType current() {
            return DayLightType.DAY;
        }
    };
    private ILuminary luminary = new ILuminary() {

        @Override
        public boolean isShining() {
            return true;
        }
    };
    private IWind wind = new IWind() {

        @Override
        public int getPower() {
            return 8;
        }
    };

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
        Magic magic = new Magic();
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
