/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Lexeme
 * @author dima6120
 */

package interpreter.lexer;


public class Lexeme {
    private LexType type;
    
    public Lexeme(LexType type) {
        this.type = type;
    }
    
    public LexType getType() {
        return type;
    }
}
