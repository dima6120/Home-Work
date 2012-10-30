/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * BINOP
 * @author dima6120
 */

package interpreter.treenodes;


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
    public Object deepcopy() {
        return new BinOp(op,(Expression)left.deepcopy(),(Expression)right.deepcopy());
    }
    
    private String getStrOp() {
        switch (op) {
            case ADD: return "+";
            case SUB: return "-";
            case MULT: return "*";
            case DIV: return "/";
        }
        return "";
    }
    
    @Override
    public String toString() {
        return left.toString() + " " + getStrOp() + " " + right.toString();
    }
}
