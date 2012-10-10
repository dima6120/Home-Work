/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Calculator
 * @author dima6120
 */

package calculator;

import hashtable.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private Pattern p;
    private Matcher m;
    private final String as = "[a-zA-z][\\w]*[ ]*=.*";
    private HashTable vars = new HashTable(1000);
    private String line;
    private Lexem curlex = Lexem.NOTHING;
    private int len;
    private int pos = 0;
    private boolean newvar;
    private int val = 0;
    public Calculator() {
        p = Pattern.compile(as);
    }
    public int expr(String s) {
        HTElem e = null; boolean newvar = false;
        line = s;
        len = line.length();
        curlex = Lexem.NOTHING;
        pos = 0;
        val = 0;
        m = p.matcher(s);
        String name = "";
        if (m.matches()) {
            name = getName(s);
            e = vars.get(name);
            if (e == null) {
                newvar = true;
                vars.put(name, null);
                e = vars.get(name); 
            } 
        }
        nextlex();
        int n = expr();
        if (curlex != Lexem.NOTHING) {
            if (newvar) {
                vars.delete(name);
            }
            System.out.println("ERROR!");
        } else {
            if (e != null) {
                e.setData((int)n);
                System.out.println(name + "="+ Integer.toString(n));
            } else {
                System.out.println(n);
            }
        }
        return n;
    }
    private String getName(String s) {
        String name = "";
        for(char c : s.toCharArray()) {
            if (c == '=') {
                pos++;
                return name;
            }
            pos++;
            if (c != ' ') {
                name += c;
            }
        }
        return null;
    }
    private int expr() {
        int res = term();
        while(true) {
            switch(curlex) {
                case PLUS:
                    nextlex();
                    res += term();
                    break;
                case MINUS:    
                    nextlex();
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
            switch(curlex) {
                case MULT:
                    nextlex();
                    res *= factor();
                    break;
                case DIV:    
                    nextlex();
                    int value = factor();
                    if (value == 0) {
                        curlex = Lexem.ERR;
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
        switch(curlex) {
            case PLUS: 
                nextlex();
                return expr();
            case MINUS:
                nextlex();
                return -expr();
            case VAR:
                if (newvar) {
                    curlex = Lexem.ERR;
                    return 0;
                } 
                nextlex();
                return val;
            case NUMB:
                nextlex();
                return val;
            case OBR:
                nextlex();
                int res = expr();
                if (curlex != Lexem.CBR) {
                    curlex = Lexem.ERR;
                    return 0;
                }
                nextlex();
                return res;
            default: 
                curlex = Lexem.ERR;
                return 0;
        }
    }
    private void nextlex() {
        char c;
     
        try {
            while(line.charAt(pos) == ' ') {
                pos++;
            }
        } catch(StringIndexOutOfBoundsException e) {
            curlex = Lexem.NOTHING;
            return;
        }
        c = line.charAt(pos);
        newvar = false;
        switch(c) {
            case '+':
                curlex = Lexem.PLUS;
                pos++;
                break;
            case '-':
                curlex = Lexem.MINUS;
                pos++;
                break;
            case '*':
                curlex = Lexem.MULT;
                pos++;
                break;
            case '/':
                curlex = Lexem.DIV;
                pos++;
                break;
            case '(':
                curlex = Lexem.OBR;
                pos++;
                break;
            case ')':
                curlex = Lexem.CBR;
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
                        val = Integer.parseInt(numb);
                        curlex = Lexem.NUMB;
                    } catch(NumberFormatException e) {
                        curlex = Lexem.ERR;
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
                    
                    HTElem e = vars.get(var);
                    
                    if (e == null) {
                        newvar = true;
                        vars.put(var, null);
                        curlex = Lexem.VAR;
                        return;
                    } else {
                        val = (int)e.getData();
                        curlex = Lexem.VAR;
                        return;
                    }
                } 
                curlex = Lexem.ERR;
        }
    }
}
