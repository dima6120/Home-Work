/**
 * CheckBraces
 * @author dima6120
 */

package checkbraces;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckBraces {
    private Pattern p;
    private Matcher m;
    private final String opxmlbr = "\\<[a-zA-z]+(([ ]{0,})|([ ]+.+))\\>";
    private final String ocxmlbr ="\\<[a-zA-z]+[ ]{0,}\\/\\>";
    private final String clxmlbr = "\\<\\/[a-zA-z]+\\>";
    private Stack s;
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
        String r = "";
        for(char c : s.toCharArray()) {
            if (c == ' ') {
                break;
            }
            if (c != '<' && c != '>' && c != '/') {
              r += c;
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
           StackElem e = (StackElem) (s.pop());
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
        for (char c : seq.toCharArray()) {
            if (tb) {
                if (c == '>') {
                    str += '>'; tb = false;
                    if (!Matching()) {
                        return false;
                    }
                    str = "";
                } else {
                    str += c;
                }
                continue;
            }
            if (isOpenBracket(c)) {
                if (lb != ElemType.tobrace) {
                    s.push(new StackElem(lb, lc));
                }
            }
            if (isCloseBracket(c)) {
                if (s.isEmpty()) {
                    return false;
                } else {
                    StackElem e = (StackElem) s.pop();
                    if (e.type == ElemType.xmlobrace) {
                        return false;
                    }
                    char ch = e.name.charAt(0);
                    
                    if (getPair(ch) != c) {
                        return false;
                    }
                }
            }
        }
        
        return (!s.isEmpty() || tb) ? false : true;
    }
        
    public boolean testBraces(String seq) {
        if (seq != null) {
            s = new Stack(); lb = ElemType.nothing;
            tb = false; str = "";
            return analize(seq);
        } else {
            return false; 
        }
    }
}
