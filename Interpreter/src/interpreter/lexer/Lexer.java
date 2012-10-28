/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Lexer
 * @author dima6120
 */

package interpreter.lexer;

import interpreter.exceptions.*;
import interpreter.syntax.Op;
import java.util.ArrayList;


enum Symbol {DIGIT, LETTER, OPBR, CLBR, EQ, SPACE, BINOP, UNKNOW}

public class Lexer {
    private Lexeme[] l;
    private int pos;
    private boolean none;
    
    public void nextlexem() {
        if (pos < l.length - 1) { 
            pos++;
        } else {
            none = true;
        }
    }
    public LexType currlex() {
        return none ? LexType.NONE : l[pos].getType();
    }
    public LexType futurelex() {
        return (pos + 1) == l.length || none ? LexType.NONE : l[pos+1].getType();
    }
    public Lexeme getcurrlex() {
        return l[pos];
    }
    public void parse(String line) throws UnexectedSymbolException {
        ArrayList<Lexeme> res = new ArrayList<>();
        int pos = 0;
        int begin;
        char []l = line.toCharArray();
        while(pos < l.length) {
            Symbol symtype = nextSymbol(l[pos]);
            begin = pos;
            switch (symtype) {
                case DIGIT:
                    String numb = Character.toString(l[pos++]);
                    while (pos < l.length && nextSymbol(l[pos]) == Symbol.DIGIT) {
                        numb += Character.toString(l[pos++]);
                    }
                    res.add(new NumbLex(Integer.parseInt(numb),begin, begin + 
                            numb.length()));
                    break;
                case LETTER:
                    String str = Character.toString(l[pos++]);
                    while (pos < l.length && (nextSymbol(l[pos]) == Symbol.LETTER ||
                                              nextSymbol(l[pos]) == Symbol.DIGIT)) {
                        str += Character.toString(l[pos++]);
                    } 
                    switch (str) {
                        case "let":
                            res.add(new LetLex(begin));
                            break;
                        case "in":
                            res.add(new InLex(begin));
                            break;
                        case "fun":
                            res.add(new FunLex(begin));
                            break;
                        default:
                            res.add(new IdLex(str, begin));
                    }
                    break;
                case OPBR:
                    res.add(new OpBracketLex(begin));
                    pos++;
                    break;
                case CLBR:
                    res.add(new ClBracketLex(begin));
                    pos++;
                    break;
                case EQ:
                    res.add(new AssignLex(begin));
                    pos++;
                    break;
                case SPACE:
                    while (pos < l.length && 
                           nextSymbol(l[pos]) == Symbol.SPACE) {
                        pos++;
                    }
                    break;
                case BINOP:
                    if (l[pos] == '-') {
                        if ((pos + 1) < l.length && l[pos + 1] == '>') {
                            res.add(new ArrowLex(begin));
                            pos += 2;
                        } else {
                            res.add(new BinOpLex(toOp(l[pos++]), begin));
                        }
                    } else {
                        res.add(new BinOpLex(toOp(l[pos++]), begin));
                    }
                    break;
                case UNKNOW:
                    throw new UnexectedSymbolException(l[pos]);
            }
        }
        this.l = res.toArray(new Lexeme[0]);
        this.pos = 0;
        none = false;
    }
    private Op toOp(char c) {
        switch (c) {
            case '+': return Op.ADD;
            case '-': return Op.SUB;
            case '*': return Op.MULT;
            case '/': return Op.DIV;
        }
        return Op.NOTHING;
    }
    private Symbol nextSymbol(char c) {
        if (Character.isDigit(c)) {
            return Symbol.DIGIT;
        }
        if (Character.isLetter(c)) {
            return Symbol.LETTER;
        }
        if (Character.isWhitespace(c)) {
            return Symbol.SPACE;
        }
        if (c == '+' || c == '-' || c == '/' || c == '*') {
            return Symbol.BINOP;
        }
        if (c == '(') {
            return Symbol.OPBR;
        }
        if (c == ')') {
            return Symbol.CLBR;
        }
        if (c == '=') {
            return Symbol.EQ;
        }
        
        return Symbol.UNKNOW;
    }
}
