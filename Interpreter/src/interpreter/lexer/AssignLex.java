/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * AssignLex
 * @author dima6120
 */

package interpreter.lexer;


public class AssignLex extends Lexeme {
    public AssignLex(int begin) {
        super(begin, begin + 1, LexType.ASSIGN);
    }
}
