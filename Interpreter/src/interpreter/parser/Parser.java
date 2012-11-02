/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Parser
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

public class Parser {
    private Lexer lexer = new Lexer();
    
    private Node parseExpr() throws LexemeTypeMismatchException, UnexectedLexemException {
        switch (lexer.currlex()) {
            case LET: return parseLet();
            case FUN: return parseFun();
            default: return expr(); 
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
        return funcall();
    }
    
    private Node funcall() throws LexemeTypeMismatchException, UnexectedLexemException {
        Node n = primary();
        if (((Expression)n).getType() != ExprType.NUMBER) {
            while(lexer.currlex() == LexType.ID ||
                  lexer.currlex() == LexType.OPBRACKET ||
                  lexer.currlex() == LexType.NUMB) {
                n = new FunCall((Expression)n, (Expression)primary());
            }
        }
        return n;
    }
    private Node primary() throws LexemeTypeMismatchException, UnexectedLexemException {
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
        Node res = this.parseExpr();
        if (lexer.currlex() != LexType.EOF) {
            throw new LexemeTypeMismatchException("EOFLexeme", 
                    lexer.getcurrlex());
        }
        return res;
    }
}

