/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * LazyInterpreter
 * @author dima6120
 */

package interpreter;

import interpreter.syntax.*;


public class LazyInterpreter extends Interpreter{

    @Override
    Expression eval(FunCall funcll) {
        Expression f = eval(funcll.getFun());
        
        if (f.getType() != ExprType.FUNCALL) {
            //кидаем исключение
            return null;
        }
        
        FunDef fun = (FunDef)f; 
        
        return eval(substitute(fun.getBody(), fun.getId(), funcll.getArg()));
    }

    @Override
    Expression eval(Let let) {       
        return eval(substitute(let.getExpr(), let.getId(), let.getBound()));
    }

}
