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
        Node n = pr.parse("let f = fun x -> fun y -> x + y in let y = 1 in let x = 1 in f y (f (f x 1) x)");
        // ((fun x -> x) (fun x -> x + 5)) 3
        // let f = fun x -> fun y -> x + y in f 1 (1 (f 1 1))
        Interpreter i = new LazyInterpreter();
        Expression e = i.eval((Expression)n);
        System.out.println(e.toString());
    }
}
