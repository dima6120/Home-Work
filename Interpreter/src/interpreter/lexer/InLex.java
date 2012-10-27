/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * InLex
 * @author dima6120
 */

package interpreter.lexer;


public class InLex extends Lexeme {
    public InLex(int begin) {
        super(begin, begin + 2, LexType.IN);
    }
}
