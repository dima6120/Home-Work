/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Lex
 * @author dima6120
 */

package calculator;

public class Lex {
    protected Lexem lex; 
    protected boolean newvar;
    protected String varname;
    protected int val;
    protected Lex(Lex l) {
        lex = l.lex;
        newvar = l.newvar;
        varname = l.varname;
        val = l.val;
    }
    protected Lex(Lexem _lex, boolean _newvar, String _varname, int _val) {
        lex = _lex;
        newvar = _newvar;
        varname = _varname;
        val = _val;
    }
}