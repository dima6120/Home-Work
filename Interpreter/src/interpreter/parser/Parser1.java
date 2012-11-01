/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Parser1
 * @author dima6120
 */

package interpreter.parser;

import interpreter.exceptions.*;
import interpreter.lexer.*;
import interpreter.treenodes.BinOp;
import interpreter.treenodes.ExprType;
import interpreter.treenodes.Expression;
import interpreter.treenodes.FunCall;
import interpreter.treenodes.FunDef;
import interpreter.treenodes.Identifier;
import interpreter.treenodes.Let;
import interpreter.treenodes.Node;
import interpreter.treenodes.Number;
import interpreter.treenodes.Op;
import java.util.Stack;

public class Parser1 {
    private Lexer lexer = new Lexer();
    private Stack brst = new Stack();
    private boolean allowed = false;
    private boolean funcall = false;
    
    private boolean endofexpr(Expression res) {
        switch(lexer.currlex()) {
            case CLBRACKET:
            case EOF:    
            case IN:
                return true;
            case NUMB:
            case ID:
            case OPBRACKET:
                return res.getType() == ExprType.NUMBER || 
                       (res.getType() == ExprType.IDENTIFIER && funcall);
            default:
                return false;
        }
    }
    
    private Node parseExpr() throws LexemeTypeMismatchException, UnexectedLexemException {
        switch (lexer.currlex()) {
            case LET: return parseLet();
            case FUN: return parseFun();
            default:
                Node res = expr(); ExprType rest = ((Expression)res).getType();
                if (endofexpr(((Expression)res))) {
                    funcall = false;
                    return res;
                } 
                if (((Expression)res).getType() == ExprType.FUNCALL) {
                    if (allowed) {
                        if (!brst.empty()) {
                            brst.pop();
                            return res;
                        }
                    }
                }
                
                if (((Expression)res).getType() == ExprType.IDENTIFIER
                        && (lexer.currlex() != LexType.NUMB &&
                        lexer.currlex() != LexType.ID)) {
                    switch(lexer.currlex()) {
                        case CLBRACKET:
                        case EOF: 
                            break;
                        default:
                            brst.push(LexType.OPBRACKET);
                            allowed = true;
                    }
                }
                funcall = true;
                FunCall fc = new FunCall((Expression)res, (Expression)parseExpr());
                while (lexer.currlex() == LexType.NUMB ||
                       lexer.currlex() == LexType.ID ||
                       lexer.currlex() == LexType.OPBRACKET) {
                    res = parseExpr();
                    fc = new FunCall(fc,(Expression)res);
                }
                allowed = rest == ExprType.FUNCALL || 
                        lexer.currlex() == LexType.CLBRACKET;;
                return fc;
        }
    }
    
    private Node expr() throws LexemeTypeMismatchException, UnexectedLexemException {
        Node left = term();
        
        if (lexer.currlex() != LexType.BINOP) {
            return left;
        }
        Op op = ((BinOpLex)lexer.getcurrlex()).getOp();
        if (op == Op.ADD || op == Op.SUB) {
            lexer.nextlexem();
            return new BinOp(op, (Expression)left, (Expression)expr());
        } 
        return left;
    }
    
    private Node term() throws LexemeTypeMismatchException, UnexectedLexemException {
        Node left = factor();
        
        if (lexer.currlex() != LexType.BINOP) {
            return left;
        }
        
        Op op = ((BinOpLex)lexer.getcurrlex()).getOp();
        if (op == Op.MULT || op == Op.DIV) {
            lexer.nextlexem();
            return new BinOp(op, (Expression)left, (Expression)term());
        } 
        return left;
    }
    
    private Node factor() throws LexemeTypeMismatchException, UnexectedLexemException {
        switch(lexer.currlex()) {
            case NUMB: 
                int val = ((NumbLex)lexer.getcurrlex()).getValue();
                lexer.nextlexem();
                return new Number(val);
            case ID: 
                String name =  ((IdLex)lexer.getcurrlex()).getName();
                lexer.nextlexem();
                return new Identifier(name);
            case OPBRACKET:
                return parseBracket();
        }
        throw new LexemeTypeMismatchException("NumberLexeme or IdLexeme or OpBracketLexeme", 
                lexer.getcurrlex());
    }
    
    private Node parseBracket() throws LexemeTypeMismatchException, UnexectedLexemException {
        lexer.nextlexem();
        allowed = lexer.currlex() != LexType.OPBRACKET || allowed;
        Node res = parseExpr();
        if (lexer.currlex() != LexType.CLBRACKET) {
            throw new LexemeTypeMismatchException("ClBracketLexeme", lexer.getcurrlex());
        }
        lexer.nextlexem();
        return res;
    }
    
    private Node parseLet() throws LexemeTypeMismatchException, UnexectedLexemException {
        lexer.nextlexem();
        if (lexer.currlex() != LexType.ID) {
            throw new LexemeTypeMismatchException("IdLexeme", 
                    lexer.getcurrlex());
        }
        String id = ((IdLex)lexer.getcurrlex()).getName();
        lexer.nextlexem();
        if (lexer.currlex() != LexType.ASSIGN) {
            throw new LexemeTypeMismatchException("AssignLexeme", 
                    lexer.getcurrlex());
        }
        lexer.nextlexem();
        //возможно исключение
        Expression bound = (Expression)parseExpr();
        if (lexer.currlex() != LexType.IN) {
            throw new LexemeTypeMismatchException("InLexeme", 
                    lexer.getcurrlex());
        }
        lexer.nextlexem();
        //возможно исключение
        Expression expr = (Expression)parseExpr();
        return new Let(id, bound, expr);
    }
    
    private Node parseFun() throws LexemeTypeMismatchException, UnexectedLexemException {
        lexer.nextlexem();
        if (lexer.currlex() != LexType.ID) {
            throw new LexemeTypeMismatchException("IdLexeme", 
                    lexer.getcurrlex());
        }
        String id = ((IdLex)lexer.getcurrlex()).getName();
        lexer.nextlexem();
        if (lexer.currlex() != LexType.ARROW) {
            throw new LexemeTypeMismatchException("ArrowLexeme", 
                    lexer.getcurrlex());
        }
        lexer.nextlexem();
        //возможно исключение
        Expression body = (Expression)parseExpr();
        return new FunDef(id, body);
    }
    
    public Node parse(String text) throws LexemeTypeMismatchException, UnexectedLexemException, UnexectedSymbolException {
        lexer.parse(text);
        allowed = false;
        funcall = false;
        Node res = this.parseExpr();
        if (lexer.currlex() != LexType.EOF) {
            throw new LexemeTypeMismatchException("EOFLexeme", 
                    lexer.getcurrlex());
        }
        return res;
    }
}

