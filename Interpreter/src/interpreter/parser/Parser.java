/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Parser
 * @author dima6120
 */

package interpreter.parser;

import interpreter.exceptions.*;
import interpreter.lexer.*;
import interpreter.syntax.*;
import interpreter.syntax.Number;

public class Parser {
    private Lexer lexer = new Lexer();
    
    private Node parseFun() throws LexemeTypeMismatchException, UnexectedLexemException {
        if (lexer.currlex() != LexType.ID) {
            throw new LexemeTypeMismatchException("IDENTIFIER Lexeme", 
                    lexer.getcurrlex());
        }
        String id = ((IdLex)lexer.getcurrlex()).getName();
        lexer.nextlexem();
        if (lexer.currlex() != LexType.ARROW) {
            throw new LexemeTypeMismatchException("ARROW Lexeme (->)", 
                    lexer.getcurrlex());
        }
        lexer.nextlexem();
        //возможно исключение
        Expression body = (Expression)parse();
        return new FunDef(id, body);
    }
    private Node parseLet() throws LexemeTypeMismatchException, UnexectedLexemException {
        if (lexer.currlex() != LexType.ID) {
            throw new LexemeTypeMismatchException("IDENTIFIER Lexeme", 
                    lexer.getcurrlex());
        }
        String id = ((IdLex)lexer.getcurrlex()).getName();
        lexer.nextlexem();
        if (lexer.currlex() != LexType.ASSIGN) {
            throw new LexemeTypeMismatchException("ASSIGN Lexeme (=)", 
                    lexer.getcurrlex());
        }
        lexer.nextlexem();
        //возможно исключение
        Expression bound = (Expression)parse();
        if (lexer.currlex() != LexType.IN) {
            throw new LexemeTypeMismatchException("IN Lexeme", 
                    lexer.getcurrlex());
        }
        lexer.nextlexem();
        //возможно исключение
        Expression expr = (Expression)parse();
        return new Let(id, bound, expr);
    }
    private Node parseFunCall() throws LexemeTypeMismatchException, UnexectedLexemException {
        switch(lexer.currlex()) {
            case NUMB: 
                    int val = ((NumbLex)lexer.getcurrlex()).getValue();
                    lexer.nextlexem();
                    return new FunCall(null, 
                                          new Number(val));
            case ID:
                String name = ((IdLex)lexer.getcurrlex()).getName();
                lexer.nextlexem();
                return new FunCall(null, 
                                        new Identifier(name));
            default: return new FunCall(null, (Expression)parse());
        }
    }
    private Node parseExpr() throws LexemeTypeMismatchException, UnexectedLexemException {
        Node res = term();
        
        while(true) {
            switch(lexer.currlex()) {
                case BINOP:
                    BinOpLex op = (BinOpLex)lexer.getcurrlex();
                    switch(op.getOp()) {
                        case ADD:
                        case SUB:
                            lexer.nextlexem();
                            res = new BinOp(op.getOp(),
                                            (Expression)res,
                                            (Expression)term());
                            break;
                    }
                default: 
                    return res;
            }
        }
    }
    private Node term() throws LexemeTypeMismatchException, UnexectedLexemException {
        Node res = factor();
        
        while(true) {
            switch(lexer.currlex()) {
                case BINOP:
                    BinOpLex op = (BinOpLex)lexer.getcurrlex();
                    switch(op.getOp()) {
                        case MULT:
                        case DIV:
                            lexer.nextlexem();
                            res = new BinOp(op.getOp(),
                                            (Expression)res,
                                            (Expression)term());
                            break;
                    }
                default: 
                    return res;
            }
        }
    }
    private Node factor() throws LexemeTypeMismatchException, UnexectedLexemException, UnexectedLexemException {
        switch(lexer.currlex()) {
            case LET: 
                lexer.nextlexem();
                return parseLet();
            case FUN: 
                lexer.nextlexem();
                return parseFun();
            case NUMB: 
                NumbLex n = (NumbLex)lexer.getcurrlex();
                lexer.nextlexem();
                return new Number(n.getValue());
            case ID:
                switch(lexer.futurelex()) {
                    case CLBRACKET:
                    case NONE: 
                    case IN:
                    case BINOP:
                        Identifier id = new Identifier(
                                ((IdLex)lexer.getcurrlex()).getName());
                        lexer.nextlexem();
                        return id;
                    default:
                        Expression fun = new Identifier(
                                ((IdLex)lexer.getcurrlex()).getName());
                        lexer.nextlexem();
                        FunCall fc = (FunCall)parseFunCall();
                        fc.setFun(fun);
                        return fc;
                }
            case OPBRACKET:
                lexer.nextlexem();
                Expression expr = (Expression)parse();
                if (lexer.currlex() != LexType.CLBRACKET) {
                    throw new LexemeTypeMismatchException("CLOSE BRACKET Lexeme", 
                            lexer.getcurrlex());
                }
                if (expr.getType() == ExprType.FUNDEF) {
                    lexer.nextlexem();
                    FunCall fc = (FunCall)parseFunCall();
                    fc.setFun(expr);
                    lexer.nextlexem();
                    return fc;
                }
                lexer.nextlexem();
                return expr;
            default: 
                throw new UnexectedLexemException(lexer.getcurrlex());
        }
    }
    private Node parse() throws LexemeTypeMismatchException, UnexectedLexemException {
        switch (lexer.currlex()) {      
            case LET: 
            case FUN:
            case OPBRACKET:    
            case ID: 
            case NUMB: 
                return parseExpr();
        }
        throw new UnexectedLexemException(lexer.getcurrlex());
    }
    public Node parse(String text) throws LexemeTypeMismatchException, UnexectedLexemException, UnexectedSymbolException {
        lexer.parse(text);
        Node res = this.parse();
        return res;
    }
}
