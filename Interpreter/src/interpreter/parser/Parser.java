/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Parser
 * @author dima6120
 */

package interpreter.parser;

import interpreter.lexer.*;
import interpreter.syntax.*;
import interpreter.syntax.Number;

public class Parser {
    private Lexer lexer = new Lexer();
    
    private Node parseFun() {
        if (lexer.currlex() != LexType.ID) {
            //исключение
        }
        String id = ((IdLex)lexer.getcurrlex()).getName();
        lexer.nextlexem();
        if (lexer.currlex() != LexType.ARROW) {
            //исключение
        }
        lexer.nextlexem();
        //возможно исключение
        Expression body = (Expression)parse();
        return new FunDef(id, body);
    }
    private Node parseLet() {
        if (lexer.currlex() != LexType.ID) {
            //исключение
        }
        String id = ((IdLex)lexer.getcurrlex()).getName();
        lexer.nextlexem();
        if (lexer.currlex() != LexType.ASSIGN) {
            //исключение
        }
        lexer.nextlexem();
        //возможно исключение
        Expression bound = (Expression)parse();
        lexer.nextlexem();
        if (lexer.currlex() != LexType.IN) {
            //исключение
        }
        //возможно исключение
        Expression expr = (Expression)parse();
        return new Let(id, bound, expr);
    }
    private Node parseFunCall() {
        switch(lexer.currlex()) {
            case NUMB: return new FunCall(null, 
                                          new Number(((NumbLex)lexer.getcurrlex()).getValue()));
            case ID: return new FunCall(null, 
                                        new Identifier(((IdLex)lexer.getcurrlex()).getName()));
            default: return new FunCall(null, (Expression)parse());
        }
    }
    private Node parseExpr() {
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
    private Node term() {
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
    private Node factor() {
        Node res;
        
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
                    case NONE: 
                    case BINOP:
                        lexer.nextlexem();
                        return new Identifier(((IdLex)lexer.getcurrlex()).getName());
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
                    //исключение
                    return null;
                }
                if (expr.getType() == ExprType.FUNDEF) {
                    lexer.nextlexem();
                    FunCall fc = (FunCall)parseFunCall();
                    fc.setFun(expr);
                    return fc;
                }
                return expr;
            default: 
                //исключение
                return null;
        }
    }
    private Node parse() {
        switch (lexer.currlex()) {      
            case LET: 
            case FUN:
            case CLBRACKET:
            case OPBRACKET:    
            case ID: 
            case NUMB: 
                return parseExpr();
        }
        //исключение
        return null;
    }
    public Node parse(String text) {
        //ловим исключение
        lexer.parse(text);
        lexer.nextlexem();
        //ловим исключение
        Node res = this.parse();
        
        return res;
    }
}
