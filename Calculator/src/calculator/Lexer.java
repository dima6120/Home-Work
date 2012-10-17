/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Lexer
 * @author dima6120
 */

package calculator;

import hashtable.*;

public class Lexer {
    private String line;
    private Lex curlex;
    private Lex futlex;
    private int len;
    private int pos = 0;
    private Calculator calc;
    private boolean waspeek;
    
    public Lexer(Calculator _calc) {
        calc = _calc;
    }
    public Lexem curlex() {
        return curlex.lex;
    }
    public void setcurlex(Lexem lex) {
        curlex.lex = lex;
    }
    public boolean isnewvar() {
        return curlex.newvar;
    }
    public String getvarname() {
        return curlex.varname;
    }
    public int getval() {
        return curlex.val;
    }
    public void setLine(String _line) {
        line = _line;
        len = line.length();
        curlex = new Lex(Lexem.NOTHING, false, "", 0);
        pos = 0;
        waspeek = false;
    }
    public Lex getfuturelex() {
        Lex x = new Lex(curlex);
        nextlex();
        futlex = new Lex(curlex);
        curlex = x;
        waspeek = true;
        return futlex;
    }
    public void nextlex() {
        if (waspeek) {
            curlex = futlex;
            waspeek = false;
            return;
        }
        try {
            while(line.charAt(pos) == ' ') {
                pos++;
            }
        } catch(StringIndexOutOfBoundsException e) {
            curlex.lex = Lexem.NOTHING;
            return;
        }
        
        char c = line.charAt(pos);
        
        curlex.newvar = false;
        
        switch(c) {
            case '=':
                curlex.lex = Lexem.ASSIGN;
                pos++;
                break;
            case '+':
                curlex.lex = Lexem.PLUS;
                pos++;
                break;
            case '-':
                curlex.lex = Lexem.MINUS;
                pos++;
                break;
            case '*':
                curlex.lex = Lexem.MULT;
                pos++;
                break;
            case '/':
                curlex.lex = Lexem.DIV;
                pos++;
                break;
            case '(':
                curlex.lex = Lexem.OBR;
                pos++;
                break;
            case ')':
                curlex.lex = Lexem.CBR;
                pos++;
                break;
            default:
                if (Character.isDigit(c)) {
                    String numb = "";
                    try {
                        while (Character.isDigit(c)) {
                            numb += c;
                            pos++;
                            c = line.charAt(pos);
                        }
                    } catch(StringIndexOutOfBoundsException e) {
                    }
                    try {
                        curlex.val = Integer.parseInt(numb);
                        curlex.lex = Lexem.NUMB;
                    } catch(NumberFormatException e) {
                        curlex.lex = Lexem.ERR;
                    }
                    return;
                }
                if (Character.isLetter(c)) {
                    String var = new String();
                    try {
                        while (Character.isDigit(c) || Character.isLetter(c)) {
                            var += c;
                            pos++;
                            c = line.charAt(pos);
                        }
                    } catch(StringIndexOutOfBoundsException e) {
                    }
                    
                    HTElem e = calc.vars.get(var);
                    
                    curlex.varname = var;
                    
                    if (e == null) {
                        curlex.newvar = true;
                        calc.vars.put(var, null);
                        curlex.lex = Lexem.VAR;
                        return;
                    } else {
                        curlex.newvar = false;
                        curlex.val = (int)e.getData();
                        curlex.lex = Lexem.VAR;
                        return;
                    }
                } 
                curlex.lex = Lexem.ERR;
        }
    }
}
