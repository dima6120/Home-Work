/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * BinOpLex
 * @author dima6120
 */

package interpreter.lexer;

import interpreter.syntax.Op;

public class BinOpLex extends Lexeme {
    private Op op;
    
    public BinOpLex(Op op) {
        super(LexType.BINOP);
        this.op = op;
    }
    
    public Op getOp() {
        return op;
    }
}
