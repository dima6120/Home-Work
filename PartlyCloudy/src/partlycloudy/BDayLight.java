/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * BDayLight
 * @author dima6120
 */

package partlycloudy;

import static java.lang.Math.*;

public class BDayLight implements IDayLight {
    @Override
    public DayLightType current() {
        int i = (int)round(random()*8);
        switch(i) {
            case 0:
            case 1:
            case 2:
                return DayLightType.DAY;
            case 3:
            case 4:
                return DayLightType.EVENING;
            case 5:
            case 6:
                return DayLightType.MORNING;
            default:
                return DayLightType.NIGHT;
        }
    }
    
}
