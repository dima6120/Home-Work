/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * BinOpLex
 * @author dima6120
 */

package interpreter.lexer;

import interpreter.syntax.Op;

public class BinOpLex extends Lexeme {
    private Op op;
    
    public BinOpLex(Op op, int begin) {
        super(begin, begin + 1, LexType.BINOP);
        this.op = op;
    }
    
    public Op getOp() {
        return op;
    }
}
