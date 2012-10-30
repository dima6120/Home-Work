/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * UnexectedSymbolException
 * @author dima6120
 */

package interpreter.exceptions;


public class UnexectedSymbolException extends Exception {
    public char got;
    public UnexectedSymbolException(char got) {
        this.got = got;
    }
}
