/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * NormalInterpreter
 * @author dima6120
 */

package interpreter;

import interpreter.exceptions.DivByZeroException;
import interpreter.exceptions.TypeMismatchException;
import interpreter.syntax.*;


public class NormalInterpreter extends Interpreter{

    @Override
    Expression eval(FunCall funcll) throws DivByZeroException, TypeMismatchException{
        Expression f = eval(funcll.getFun());
        
        if (f.getType() != ExprType.FUNDEF) {
            throw new TypeMismatchException("Function Def", f);
        }
        
        FunDef fun = (FunDef)f; 
        Expression res = eval(substitute(fun.getBody(), 
                               fun.getId(), 
                               eval(funcll.getArg())
                              ));
        return res;
    }

    @Override
    Expression eval(Let let) throws DivByZeroException, TypeMismatchException {
        return eval(substitute(let.getExpr(),
                               let.getId(), 
                               eval(let.getBound())
                              ));
    }

}
