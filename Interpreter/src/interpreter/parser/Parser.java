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
                            continue;
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
                                            (Expression)factor());
                            continue;
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
                    case EOF: 
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
                        
                        while (lexer.currlex() == LexType.NUMB ||
                               lexer.currlex() == LexType.ID ||
                               lexer.currlex() == LexType.OPBRACKET) {
                            FunCall fc1 = (FunCall)parseFunCall();
                            fc1.setFun(fc);
                            fc = fc1;
                        }
                        return fc;
                }
            case OPBRACKET:
                lexer.nextlexem();
                Expression expr = (Expression)parse();
                if (lexer.currlex() != LexType.CLBRACKET) {
                    throw new LexemeTypeMismatchException("CLOSE BRACKET Lexeme", 
                            lexer.getcurrlex());
                }
                if (expr.getType() == ExprType.FUNDEF || 
                        ((expr.getType() == ExprType.FUNCALL &&
                            (lexer.futurelex() == LexType.NUMB ||
                             lexer.futurelex() == LexType.ID ||
                             lexer.futurelex() == LexType.OPBRACKET)
                        ))) {
                    lexer.nextlexem();
                    FunCall fc = (FunCall)parseFunCall();
                    fc.setFun(expr);
                    while (lexer.currlex() == LexType.NUMB ||
                           lexer.currlex() == LexType.ID ||
                           lexer.currlex() == LexType.OPBRACKET) {
                        FunCall fc1 = (FunCall)parseFunCall();
                        fc1.setFun(fc);
                        fc = fc1;
                    }
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
        if (lexer.currlex() != LexType.EOF) {
            throw new LexemeTypeMismatchException("NONE", 
                    lexer.getcurrlex());
        }
        return res;
    }
}
