/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * StackElem
 * @author dima6120
 */

package checkbraces;
//элемент стека
public class StackElem {
    protected ElemType type; //тип элемента
    protected String name; //имя XML'ой скобки или "(", "["
    public StackElem(ElemType t, String n) {
        type = t; name =n;
    }
    
}
