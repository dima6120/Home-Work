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
        int w = wind.getPower();
        if (luminary.isShining()) {
            switch(daylight.current()) {
                case MORNING:
                    if (w == 2 || w == 3) {
                        return new Creature(CreatureType.KITTTEN);
                    }
                    return new Creature(CreatureType.NOTCREATURE);
                case DAY:
                    if (w == 0 || w == 1 || (w >= 4 && w <= 6)) {
                        return new Creature(CreatureType.HEDGEHOG);
                    }
                    if (w >= 7 && w <= 10) {
                        return new Creature(CreatureType.BALLOON);
                    }
                    return new Creature(CreatureType.NOTCREATURE);
                case EVENING:
                    return new Creature(CreatureType.NOTCREATURE);
                case NIGHT:
                    if (w == 2 || w == 3) {
                        return new Creature(CreatureType.KITTTEN);
                    }
                    if (w == 0 || w == 1 || (w >= 4 && w <= 6)) {
                        return new Creature(CreatureType.HEDGEHOG);
                    }
                    if (w >= 7 && w <= 10) {
                        return new Creature(CreatureType.BALLOON);
                    }
                    return new Creature(CreatureType.NOTCREATURE);
            }
        } else {
            switch(daylight.current()) {
                case MORNING:
                    if (w == 9 || w == 10) {
                        return new Creature(CreatureType.BEARCUB);
                    }
                    return new Creature(CreatureType.NOTCREATURE);
                case DAY:
                    if (w >= 0 && w <= 3) {
                        return new Creature(CreatureType.PUPPY);
                    }
                    if (w == 7 || w == 8) {
                        return new Creature(CreatureType.BAT);
                    }
                    return new Creature(CreatureType.NOTCREATURE);
                case EVENING:
                    if (w >= 0 && w <= 3) {
                        return new Creature(CreatureType.PUPPY);
                    }
                    if (w >= 4 && w <= 6) {
                        return new Creature(CreatureType.PIGLET);
                    }
                    return new Creature(CreatureType.NOTCREATURE);
                case NIGHT:
                    if (w >= 4 && w <= 6) {
                        return new Creature(CreatureType.PIGLET);
                    }
                    if (w == 7 || w == 8) {
                        return new Creature(CreatureType.BAT);
                    }
                    if (w == 9 || w == 10) {
                        return new Creature(CreatureType.BEARCUB);
                    }
                    return new Creature(CreatureType.NOTCREATURE);
            }
        }
        return new Creature(CreatureType.NOTCREATURE);
    }
 
    public Creature create()
    {
        Creature creature = internalCreate();
         
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
            default:
        }

        return creature;
    }
}
