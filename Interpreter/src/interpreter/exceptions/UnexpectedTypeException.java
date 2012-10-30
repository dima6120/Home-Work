/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * UnexpectedTypeException
 * @author dima6120
 */

package interpreter.exceptions;

import interpreter.treenodes.Node;

public class UnexpectedTypeException extends Exception {
    public Node got;
    
    public UnexpectedTypeException(Node got) {
        this.got = got;
    }
}
