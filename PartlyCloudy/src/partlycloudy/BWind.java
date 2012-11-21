/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * BWind
 * @author dima6120
 */

package partlycloudy;

import static java.lang.Math.*;

public class BWind implements IWind {
    @Override
    public int getPower() {
        return (int)round(random()*10);
    }
    
}
