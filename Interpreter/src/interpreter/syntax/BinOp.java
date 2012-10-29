/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * BINOP
 * @author dima6120
 */

package interpreter.syntax;


public class BinOp extends Expression {
    private Expression left;
    private Expression right;
    private Op op;
    
    public BinOp(Op op, Expression left, Expression right) {
        this.left = left;
        this.right = right;
        this.op = op;
        type = ExprType.BINOP; 
    }
    
    public Op getOp() {
        return op;
    }
    
    public Expression getLeft() {
        return left;
    }
    
    public Expression getRight() {
        return right;
    }
    
    public void setRight(Expression r) {
        right = r;
    }
    
    public void setLeft(Expression l) {
        left = l;
    }

    @Override
    public Object getclone() {
        return new BinOp(op,(Expression)left.getclone(),(Expression)right.getclone());
    }
}
