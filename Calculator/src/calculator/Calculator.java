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
    private OpType lop = OpType.NONE;
    
    public Calculator() {
        lr = new Lexer(this);
    }
    public OpType getlastop() {
        return lop;
    }
    public int getres() {
        return res;
    }
    public void expr(String s) {
        if (s != null) {
            lr.setLine(s);
            if (!parseassign()) {
                res = expr();
                lop = lr.curlex() != Lexem.NOTHING ? OpType.ERROR : OpType.EVAL;
                if (lr.curlex() == Lexem.VAR && lr.isnewvar()) {
                    vars.delete(lr.getvarname());
                }
            }
        } else {
            lop = OpType.ERROR;
        }
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
                    lop = OpType.ERROR;
                    return true;
                } else {
                    if (newvar) {
                        vars.put(varname, res);
                    } else {
                        HTElem e = vars.get(varname);
                        if (e != null) {
                            e.setData(res);
                        }
                    }
                }
                lop = OpType.ASSIGN;
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
