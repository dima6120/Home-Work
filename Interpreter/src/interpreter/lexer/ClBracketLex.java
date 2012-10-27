/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * ClBracketLex
 * @author dima6120
 */

package interpreter.lexer;


public class ClBracketLex extends Lexeme {
    public ClBracketLex(int begin) {
        super(begin, begin + 1, LexType.CLBRACKET);
    }
}
