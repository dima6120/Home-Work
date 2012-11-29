/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
 * InterpreterTest
 * @author dima6120
 */

package interpreter;

import interpreter.exceptions.DivByZeroException;
import interpreter.exceptions.LexemeTypeMismatchException;
import interpreter.exceptions.TypeMismatchException;
import interpreter.exceptions.UnexectedLexemException;
import interpreter.exceptions.UnexectedSymbolException;
import interpreter.exceptions.UnexpectedTypeException;
import interpreter.lexer.Lexer;
import interpreter.parser.Parser;
import interpreter.treenodes.*;
import interpreter.treenodes.Number;
import static org.junit.Assert.*;
import org.junit.Test;

public class InterpreterTest {

    public InterpreterTest() {
    }

    @Test
    public void testLazyAndNormalInterpreter() throws LexemeTypeMismatchException, DivByZeroException, TypeMismatchException, UnexpectedTypeException, UnexectedLexemException, UnexectedSymbolException {
        
        String []exprs = {"let x = 2 in x", "let y = 2*2 in y*y-1",
                          "let x = 5 in let y = 6 in x + y",
                          "(fun x -> x+3) 5", "((fun x -> x)(fun x -> x + 5)) 3",
                          "let x = 3 in let f = fun y -> x + y in let x = 5 in f 4"
                         }; 
        String []eres = {"2", "15", "11", "8", "8", "7"};
        Expression res;
                              
        Interpreter intr = new LazyInterpreter();
        Parser pr = new Parser();
        
        System.out.println("lazy interpreter");
        
        for(int i = 0; i < 6; i++) {
            System.out.print(i);
            res = intr.evalExpr((Expression)pr.parse(exprs[i]));
            assertEquals(res.toString(), eres[i]);
            System.out.println("-ok");
        }
        
        intr = new NormalInterpreter();
        
        System.out.println("normal interpreter");
        
        for(int i = 0; i < 6; i++) {
            System.out.print(i);
            res = intr.evalExpr((Expression)pr.parse(exprs[i]));
            assertEquals(res.toString(), eres[i]);
            System.out.println("-ok");
        }
    }
    @Test
    public void testSubstitute() {
        System.out.println("substitute");
        Expression []expr = {new BinOp(Op.ADD, new Number(1), new Identifier("x")),
                             new FunCall(new Identifier("f"), new Number(1)),
                             new FunDef("x", new Identifier("x")),
                             new Number(1),
                             new FunDef("x", new BinOp(Op.ADD, new Identifier("y"), new Identifier("x"))),
                             new Let("x", new Number(1), new BinOp(Op.ADD, new Identifier("x"), new Identifier("y"))),
                             new Let("x", new Number(1), new BinOp(Op.ADD, new Identifier("x"), new Number(1))),
                             new FunCall(new Identifier("f"), new FunCall(new Identifier("f"), new Number(1)))
                            };
        String []id = {"x",
                       "f",
                       "x",
                       "x",
                       "y",
                       "y",
                       "x",
                       "f"
                      };
        Number n = new Number(1);
        FunDef fd = new FunDef("x", new Identifier("x"));
        Expression []x = {n,
                          fd,
                          n,
                          n,
                          n,
                          n,
                          n,
                          fd
                         };
        Interpreter instance = new Interpreter() {

            @Override
            Expression eval(FunCall funcll) throws DivByZeroException, TypeMismatchException, UnexpectedTypeException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            Expression eval(Let let) throws DivByZeroException, TypeMismatchException, UnexpectedTypeException {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        
        String []expResult = {"(1 + 1)", 
                              "[call [fun x -> x] (1)]",
                              "[fun x -> x]",
                              "1",
                              "[fun x -> (1 + x)]",
                              "[let x = 1 in (x + 1)]",
                              "[let x = 1 in (x + 1)]",
                              "[call [fun x -> x] ([call [fun x -> x] (1)])]"
                             };
        Expression result;
        for (int i = 0; i < 8; i++) {
            System.out.print(i);
            result= instance.substitute(expr[i], id[i], x[i]);
            assertEquals(expResult[i], result.toString());
            System.out.println("-ok");
        }
    }

}