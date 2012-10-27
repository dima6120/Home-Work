/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * NormalInterpreter
 * @author dima6120
 */

package interpreter;

import interpreter.syntax.*;


public class NormalInterpreter extends Interpreter{

    @Override
    Expression eval(FunCall funcll) {
        Expression f = eval(funcll.getFun());
        
        if (f.getType() != ExprType.FUNCALL) {
            //кидаем исключение
            return null;
        }
        
        FunDef fun = (FunDef)f; 
        
        return eval(substitute(fun.getBody(), 
                               fun.getId(), 
                               eval(funcll.getArg())
                              ));
    }

    @Override
    Expression eval(Let let) {
        Expression x = eval(let.getBound());
       
        return eval(substitute(let.getExpr(),
                               let.getId(), 
                               eval(let.getBound())
                              ));
    }

}
