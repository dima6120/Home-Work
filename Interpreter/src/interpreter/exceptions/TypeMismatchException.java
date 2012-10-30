/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * TypeMismatchException
 * @author dima6120
 */

package interpreter.exceptions;

import interpreter.treenodes.Node;

public class TypeMismatchException extends Exception {
    public String exp;
    public Node got;
    public TypeMismatchException(String exp, Node got) {
        this.exp = exp;
        this.got = got;
    }
}
