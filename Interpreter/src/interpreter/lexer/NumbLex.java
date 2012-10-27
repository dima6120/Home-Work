/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * NumbLex
 * @author dima6120
 */

package interpreter.lexer;


public class NumbLex extends Lexeme {
    private int value;
    
    public NumbLex(int value, int begin, int end) {
        super(begin, end, LexType.NUMB);
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
