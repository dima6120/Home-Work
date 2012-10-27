/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * ArrowLex
 * @author dima6120
 */

package interpreter.lexer;


public class ArrowLex extends Lexeme {
    public ArrowLex(int begin) {
        super(begin, begin + 2, LexType.ARROW);
    }
}
