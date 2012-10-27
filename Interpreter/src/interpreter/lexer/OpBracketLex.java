/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * OpBracketLex
 * @author dima6120
 */

package interpreter.lexer;


public class OpBracketLex extends Lexeme {
    public OpBracketLex(int begin) {
        super(begin, begin + 1, LexType.OPBRACKET);
    }
}
