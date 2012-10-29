/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Main
 * @author dima6120
 */

package main;

import interpreter.*;
import interpreter.exceptions.*;
import interpreter.parser.Parser;
import interpreter.syntax.Expression;
import interpreter.syntax.Node;

public class Main {
    public static void main(String[] args) throws LexemeTypeMismatchException, UnexectedLexemException, UnexectedSymbolException, DivByZeroException, TypeMismatchException {
        Parser pr = new Parser();
        Node n = pr.parse("let f = fun y -> 2 + y in f(f 3)");
        //let f = let x = 2 in fun y -> x + y in let x = 3 in f(f x)
        Interpreter i = new LazyInterpreter();
        Expression e = i.eval((Expression)n);
        System.out.println();
    }
}
