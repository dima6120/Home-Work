/**
 * CheckBraces
 * @author dima6120
 */

package checkbraces;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckBraces {
    private Pattern p;
    private Matcher m;
    private final String opxmlbr = "\\<[a-zA-z]+(([ ]{0,})|([ ]+.+))\\>";
    private final String ocxmlbr ="\\<[a-zA-z]+[ ]{0,}\\/\\>";
    private final String clxmlbr = "\\<\\/[a-zA-z]+\\>";
    private Stack s = new Stack();
    private ElemType lb = ElemType.nothing;
    private boolean tb = false;
    private String lc;
    private String str = new String();
    
    boolean isOpenBracket(char c) {
        switch (c) {
            case '(': lb = ElemType.robrace; lc = "("; return true;
            case '[': lb = ElemType.sobrace; lc = "["; return true;
            case '<': 
                lb = ElemType.tobrace; lc = "<"; 
                str += '<'; tb = true;
                return true;
            default: lb = ElemType.nothing; return false;
        }
    }
    char getPair(char bracket) {
        switch(bracket) {
                case '(' : return ')';
                case '[' : return ']';
        }
        return 0;
    }
 
    boolean isCloseBracket(char ch) {
        return ch == getPair('(')|| ch == getPair('[');
    }
    String getName(String s) {
        String r = ""; int len = s.length(); char c;
        for(int i = 0; i < len; i++) {
            c = s.charAt(i);
            if (c == ' ') {
                break;
            }
            if (c != '<' && c != '>' && c != '/') {
              r += s.charAt(i);
            }
        }
        return r;
    }
    boolean Matching() {
        return MatchingOBXML() || MatchingEBXML() || MatchingOCBXML();
    }
    boolean MatchingEBXML() {
        p = Pattern.compile(clxmlbr);
        m = p.matcher(str); 
        if (m.matches()) {
           if (s.isEmpty()) {
               return false;
           }
           StackElem e = s.pop();
           if (e.type != ElemType.xmlobrace) {
               return false;
           } else {
               if (!getName(str).equals(e.name)) {
                   return false;
               }
           }
           return true;
        } else {
            return false;
        }
                
    }
    boolean MatchingOBXML() {
        p = Pattern.compile(opxmlbr);
        m = p.matcher(str); 
        if (m.matches()) {
            if (!MatchingOCBXML()) {
                    s.push(new StackElem(ElemType.xmlobrace, getName(str)));
            } 
            return true;
        } else {
            return false;
        }
    }
    boolean MatchingOCBXML() {
        p = Pattern.compile(ocxmlbr);
        m = p.matcher(str); 
        if (!m.matches()) {
            return false;
        }
        return true;
    }
    boolean analize(String seq) {
        int len = seq.length();
        
        for(int i = 0; i < len; i++) {
            if (tb) {
                if (seq.charAt(i) == '>') {
                    str += '>'; tb = false;
                    if (!Matching()) {
                        return false;
                    }
                    str = "";
                } else {
                    str += seq.charAt(i);
                }
                continue;
            }
            if (isOpenBracket(seq.charAt(i))) {
                if (lb != ElemType.tobrace) {
                    s.push(new StackElem(lb, lc));
                }
            }
            if (isCloseBracket(seq.charAt(i))) {
                if (s.isEmpty()) {
                    return false;
                } else {
                    StackElem e = s.pop();
                    if (e.type == ElemType.xmlobrace) {
                        return false;
                    }
                    char ch = e.name.charAt(0);
                    
                    if (getPair(ch) != seq.charAt(i)) {
                        return false;
                    }
                }
            }
        }
        
        return (!s.isEmpty() || tb) ? false : true;
    }
        
    public boolean testBraces(String seq) {
        if (seq != null) {
            s.ClearStack(); lb = ElemType.nothing;
            tb = false; str = "";
            return analize(seq);
        } else {
            return false; 
        }
    }
}
