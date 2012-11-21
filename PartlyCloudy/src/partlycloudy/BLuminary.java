/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * BLuminary
 * @author dima6120
 */

package partlycloudy;

import static java.lang.Math.*;

public class BLuminary implements ILuminary {
    @Override
    public boolean isShining() {
        int i = (int)round(random()*10);
        return i > 5;
    }
    
}
