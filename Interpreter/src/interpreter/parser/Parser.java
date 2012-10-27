/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Parser
 * @author dima6120
 */

package interpreter.parser;

import interpreter.lexer.*;
import interpreter.syntax.*;

enum Expected {NONE, ID, EXPR}

public class Parser {
    private Lexer lexer = new Lexer();
    private Lexeme []l;
    private int pos;
    private Expected exp;
    
    private Node parse() {
        Node res;
        pos++;
        if (pos == l.length) {
            //исключение
            return null;
        }
        switch (l[pos].getType()) {
            case ARROW: 
            case ASSIGN: 
            case BINOP:
            case CLBRACKET:
            case OPBRACKET:
            case FUN:
            case ID:
            case IN: 
            case LET:
                /*exp = Expected.ID;
                Identifier id = (Identifier)parse();
                if ((pos + 1) < l.length && 
                        l[pos + 1].getType() == LexType.ASSIGN) {
                    exp = Expected.EXPR;
                    Expression bound = (Expression) parse();
                    
                    if ((pos + 1) < l.length && 
                            l[pos + 1].getType() == LexType.IN) {
                        exp = Expected.EXPR;
                        Expression expr = (Expression) parse();
                        return new Let(id.getName(), bound, expr);
                    }
                } 
                //исключение
                return null;*/
            case NUMB: 
        }
        //исключение
        return null;
    }
    public Node parse(String text) {
        //ловил исключение
        l = lexer.parse(text);
        pos = -1;
        exp = Expected.NONE;
        //ловим исключение
        Node res = this.parse();
        
        return res;
    }
}
