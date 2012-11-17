/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Creature
 * @author dima6120
 */

package partlycloudy;


public class Creature {
    private CreatureType ct;
    
    public Creature(CreatureType ct) {
        this.ct = ct;
    }
    public CreatureType getCreatureType() {
        return ct;
    }
}
