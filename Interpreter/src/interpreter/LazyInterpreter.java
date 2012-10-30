/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * LazyInterpreter
 * @author dima6120
 */

package interpreter;

import interpreter.exceptions.*;
import interpreter.treenodes.ExprType;
import interpreter.treenodes.Expression;
import interpreter.treenodes.FunCall;
import interpreter.treenodes.FunDef;
import interpreter.treenodes.Let;


public class LazyInterpreter extends Interpreter{

    @Override
    Expression eval(FunCall funcll) throws DivByZeroException, TypeMismatchException, UnexpectedTypeException{
        Expression f = eval(funcll.getFun());
        
        if (f.getType() != ExprType.FUNDEF) {
            throw new TypeMismatchException("FUNCTION DEFINITION", f);
        }
        
        FunDef fun = (FunDef)f; 
        
        return eval(substitute(fun.getBody(), fun.getId(), funcll.getArg()));
    }

    @Override
    Expression eval(Let let) throws DivByZeroException, TypeMismatchException, UnexpectedTypeException {      
        return eval(substitute(let.getExpr(), let.getId(), let.getBound()));
    }

}
