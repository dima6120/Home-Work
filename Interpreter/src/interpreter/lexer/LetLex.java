/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * LetLex
 * @author dima6120
 */

package interpreter.lexer;


public class LetLex extends Lexeme {
    public LetLex(int begin) {
        super(begin, begin + 3, LexType.NUMB);
    }
}
