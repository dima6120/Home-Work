
package interpreter;

import interpreter.exceptions.DivByZeroException;
import interpreter.exceptions.TypeMismatchException;
import interpreter.exceptions.UnexpectedTypeException;
import interpreter.treenodes.*;
import interpreter.treenodes.Number;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * InterpreterTest
 * @author dima6120
 */
public class InterpreterTest {

    public InterpreterTest() {
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
            result= instance.substitute(expr[i], id[i], x[i]);
            assertEquals(expResult[i], result.toString());
            System.out.println(i);
        }
    }

}