/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * FunLex
 * @author dima6120
 */

package interpreter.lexer;


public class FunLex extends Lexeme {
    public FunLex(int begin) {
        super(begin, begin + 1, LexType.FUN);
    }
}
