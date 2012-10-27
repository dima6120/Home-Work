/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * IdLex
 * @author dima6120
 */

package interpreter.lexer;


public class IdLex extends Lexeme {
    private String name;
    
    public IdLex(String name, int begin) {
        super(begin, begin + name.length(), LexType.ID);
        this.name = name; 
    }
    
    public String getName() {
        return name;
    }
}
