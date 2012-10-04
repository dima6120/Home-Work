/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
 * TPlanet
 * @author dima6120
 */

package solarsystem;

public abstract class TPlanet extends Group{
    protected float xs; // x владельца
    protected float ys; // y владельца
    protected float ds; //расстояние до владельца
    protected double alphas; // угол поворота относительно центра владельца
    protected double dalpha; // скорость поворота
}
