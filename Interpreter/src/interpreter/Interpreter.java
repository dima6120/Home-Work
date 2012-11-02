/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Interpreter
 * @author dima6120
 */

package interpreter;

import interpreter.exceptions.*;
import interpreter.treenodes.BinOp;
import interpreter.treenodes.ExprType;
import interpreter.treenodes.Expression;
import interpreter.treenodes.FunCall;
import interpreter.treenodes.FunDef;
import interpreter.treenodes.Identifier;
import interpreter.treenodes.Let;
import interpreter.treenodes.Number;

public abstract class Interpreter {
    public Expression substitute(Expression expr, String id, Expression x) {
        switch (expr.getType()) {
            case BINOP: 
                BinOp op = (BinOp)expr;
                op.setLeft(substitute(op.getLeft(), id, x));        
                op.setRight(substitute(op.getRight(), id, x));
                return op;
            case IDENTIFIER: 
                Identifier i = (Identifier)expr;
                if (id.equals(i.getName())) {
                    return (Expression)x.deepcopy();
                }    
                return i;
            case FUNDEF:
                FunDef f = (FunDef)expr;
                //а если id = f.id? чё делать?
                if (!f.getId().equals(id)) {
                    f.setBody(substitute(f.getBody(), id, x));
                }
                return f;
            case FUNCALL: 
                FunCall c = (FunCall)expr;
                c.setFun(substitute(c.getFun(), id, x));
                c.setArg(substitute(c.getArg(), id, x));
                return c;
            case NUMBER: 
                return expr;
            case LET: 
                Let l = (Let)expr;
                if (!l.getId().equals(id)) {
                    l.setBound(substitute(l.getBound(), id, x));
                    l.setExpr(substitute(l.getExpr(), id, x));
                }
                return l;
        }
        return null;
    }
    
    abstract Expression eval(FunCall funcll) throws DivByZeroException, TypeMismatchException, UnexpectedTypeException;
    abstract Expression eval(Let let) throws DivByZeroException, TypeMismatchException, UnexpectedTypeException;
    
    private Expression eval(BinOp op) throws DivByZeroException, TypeMismatchException, UnexpectedTypeException {
        Expression l = eval(op.getLeft());
        Expression r = eval(op.getRight());
       
        if (l.getType() != ExprType.NUMBER || r.getType() != ExprType.NUMBER) {
            throw new TypeMismatchException("Number", l);
        }
        
        Number ln = (Number)l;
        Number rn = (Number)r;
        
        switch (op.getOp()) {
            case ADD: return new Number(ln.getVal() + rn.getVal());
            case SUB: return new Number(ln.getVal() - rn.getVal());
            case MULT: return new Number(ln.getVal() * rn.getVal());
            case DIV: 
                if (rn.getVal() == 0) {
                    throw new DivByZeroException();
                }
                return new Number(ln.getVal()/rn.getVal());
        }
        return null;
    }
    private Expression eval(FunDef fundf) throws DivByZeroException, TypeMismatchException {
        return fundf;
    }
    private Expression eval(Identifier id) throws UnexpectedTypeException {
        throw new UnexpectedTypeException(id);
    }
    private Expression eval(Number numb) {
        return numb;
    }
    public Expression eval(Expression expr) throws DivByZeroException, TypeMismatchException, UnexpectedTypeException {
        switch (expr.getType()) {
            case BINOP: return eval((BinOp)expr);
            case IDENTIFIER: return eval((Identifier)expr);
            case FUNDEF: return eval((FunDef)expr);
            case FUNCALL: return eval((FunCall)expr);
            case NUMBER: return eval((Number)expr);
            case LET: return eval((Let)expr);
        }
        return null;
    }
}
