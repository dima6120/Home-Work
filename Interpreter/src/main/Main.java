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
        Parser1 pr = new Parser1();
        Node n = pr.parse("let f = fun x -> x + 1 in let x = 2 in f x");
        // ((fun x -> x) (fun x -> x + 5)) 3
        // let f = fun x -> fun y -> x + y in f ((f 1) 1) 1
        Interpreter i = new LazyInterpreter();
        Expression e = i.eval((Expression)n);
        System.out.println(e.toString());
    }
}
