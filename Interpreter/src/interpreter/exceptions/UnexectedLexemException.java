/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * UnexectedLexemException
 * @author dima6120
 */

package interpreter.exceptions;

import interpreter.lexer.Lexeme;

public class UnexectedLexemException extends Exception {
    public Lexeme got;
    public UnexectedLexemException(Lexeme got) {
        this.got = got;
    }
}
