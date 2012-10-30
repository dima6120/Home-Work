/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * LexemeTypeMismatchException
 * @author dima6120
 */

package interpreter.exceptions;

import interpreter.lexer.Lexeme;

public class LexemeTypeMismatchException extends Exception {
    public String exp;
    public Lexeme got;
    
    public LexemeTypeMismatchException(String exp, Lexeme got) {
        this.exp = exp;
        this.got = got;
    }
}
