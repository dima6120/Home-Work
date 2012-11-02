/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Main
 * @author dima6120
 */

package main;

import interpreter.*;
import interpreter.exceptions.*;
import interpreter.parser.*;
import interpreter.treenodes.Expression;
import interpreter.treenodes.Node;

public class Main {
    public static void main(String[] args) throws LexemeTypeMismatchException, UnexectedLexemException, UnexectedSymbolException, DivByZeroException, TypeMismatchException, UnexpectedTypeException {
        Parser pr = new Parser();
        Node n = pr.parse("let f = fun x -> fun y -> x + y in f 1 (f 1 (f 1 1))");
        //let f = fun x -> fun y -> fun z -> fun w -> (x + (y + z)*2+1)*w in let x = (1) in (f (f 1 x 3 4) x) (x) ((fun x -> f x 1 1 (x+1)) 1)
        // ((fun x -> x) (fun x -> x + 5)) 3
        // let f = fun x -> fun y -> x + y in f 1 (f 1 (f 1 1))
        Interpreter i = new NormalInterpreter();
        Expression e = i.eval((Expression)n);
        System.out.println(e.toString());
    }
}
