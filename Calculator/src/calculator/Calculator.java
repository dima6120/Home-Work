/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Calculator
 * @author dima6120
 */

package calculator;

import hashtable.*;

public class Calculator {
    protected HashTable vars = new HashTable(1000);
    private Lexer lr;
    private int res;
    
    public Calculator() {
        lr = new Lexer(this);
    }
    public int expr(String s) {
        lr.setLine(s);
        if (!parseassign()) {
            res = expr();
            if (lr.curlex() == Lexem.NOTHING) {
                System.out.println(Integer.toString(res));
            } else {
                System.out.println("ERROR!");
            }
        }
        return res;
    }
    private boolean parseassign() {
        lr.nextlex();
        if (lr.curlex() == Lexem.VAR) {
            if (lr.getfuturelex().lex == Lexem.ASSIGN)
            {
                String varname = lr.getvarname().trim(); 
                boolean newvar = lr.isnewvar();
                if (newvar) {
                    vars.delete(varname);
                }
                lr.nextlex();
                lr.nextlex();
                res = expr();
                if (lr.curlex() != Lexem.NOTHING) {
                    res = 0;
                    System.out.println("ERROR!");
                } else {
                    if (newvar) {
                        vars.put(varname, res);
                    } else {
                        HTElem e = vars.get(varname);
                        if (e != null) {
                            e.setData(res);
                        }
                    }
                    System.out.println(varname + "="+ Integer.toString(res));
                }
                return true;
            }
        }
        return false;
    }
    private int expr() {
        int res = term();
        while(true) {
            switch(lr.curlex()) {
                case PLUS:
                    lr.nextlex();
                    res += term();
                    break;
                case MINUS:    
                    lr.nextlex();
                    res -= term();
                    break;
                default:
                    return res;
            }
        }
    }
    private int term() {
        int res = factor();
        while(true) {
            switch(lr.curlex()) {
                case MULT:
                    lr.nextlex();
                    res *= factor();
                    break;
                case DIV:    
                    lr.nextlex();
                    int value = factor();
                    if (value == 0) {
                        lr.setcurlex(Lexem.ERR);
                        return 0;
                    } 
                    res /= value;
                    break;
                default:
                    return res;
            }
        }
    }
    private int factor() {
        switch(lr.curlex()) {
            case PLUS: 
                lr.nextlex();
                return expr();
            case MINUS:
                lr.nextlex();
                return -expr();
            case VAR:
                if (lr.isnewvar()) {
                    lr.setcurlex(Lexem.ERR);
                    vars.delete(lr.getvarname());
                    return 0;
                } 
                lr.nextlex();
                return lr.getval();
            case NUMB:
                lr.nextlex();
                return lr.getval();
            case OBR:
                lr.nextlex();
                int res = expr();
                if (lr.curlex() != Lexem.CBR) {
                    lr.setcurlex(Lexem.ERR);
                    return 0;
                }
                lr.nextlex();
                return res;
            default: 
                lr.setcurlex(Lexem.ERR);
                return 0;
        }
    }
}
