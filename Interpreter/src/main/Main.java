/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Main
 * @author dima6120
 */

package main;

import interpreter.exceptions.*;
import interpreter.parser.Parser;
import interpreter.syntax.Node;

public class Main {
    public static void main(String[] args) throws LexemeTypeMismatchException, UnexectedLexemException, UnexectedSymbolException {
        Parser pr = new Parser();
        Node n = pr.parse("((fun x -> x+1) 2) + 2");
    }
}
