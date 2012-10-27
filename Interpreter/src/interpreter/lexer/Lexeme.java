/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Lexeme
 * @author dima6120
 */

package interpreter.lexer;


public class Lexeme {
    private int begin;
    private int end;
    private LexType type; 
    
    public Lexeme(int begin, int end, LexType type) {
        this.begin = begin;
        this.end = end;
        this.type = type;
    }
    
    public int getBegin() {
        return begin;
    }
    
    public int getEnd() {
        return end;
    }
    
    public LexType getType() {
        return type;
    }
}
