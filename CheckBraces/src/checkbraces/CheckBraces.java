/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
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
    //regexp для открывающей XML'ой скобки
    private final String opxmlbr = "\\<[a-zA-z]+(([ ]{0,})|([ ]+.+))\\>";
    //regexp для одиночной XML'ой скобки
    private final String ocxmlbr ="\\<[a-zA-z]+[ ]{0,}\\/\\>";
    //regexp для закрывающей XML'ой скобки
    private final String clxmlbr = "\\<\\/[a-zA-z]+\\>";
    private Stack s; 
    //текущий символ: <,(,[ или nothing
    private ElemType lb = ElemType.nothing;
    //был <
    private boolean tb = false;
    //последний важный символ
    private String lc;
    //строка для составления XML'ой скобки
    private String str = new String();
    
    //тест на открывающую скобку
    private boolean isOpenBracket(char c) {
        switch (c) {
            case '(': 
                lb = ElemType.robrace; 
                lc = "("; 
                return true;
            case '[': 
                lb = ElemType.sobrace; 
                lc = "["; 
                return true;
            case '<': 
                lb = ElemType.tobrace; lc = "<"; 
                str += '<'; tb = true;
                return true;
            default: 
                lb = ElemType.nothing; 
                return false;
        }
    }
    //получить пару открывающей скобке
    private char getPair(char bracket) {
        return (bracket == '(') ? ')' : (bracket == '[') ? ']' : 0; 
    }
    //тест на закрывающую скобку
    private boolean isCloseBracket(char ch) {
        return ch == getPair('(')|| ch == getPair('[');
    }
    //получаем имя XML'ой скобки
    private String getName(String s) {
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
    //тест на соответствие одной из XML'ых скобок
    private boolean matching() {
        return matchingOBXML() || matchingСBXML() || matchingOCBXML();
    }
    //тест на закрывающую XML'ую скобку
    private boolean matchingСBXML() {
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
    //тест на открывающую XML'ую скобку
    private boolean matchingOBXML() {
        p = Pattern.compile(opxmlbr);
        m = p.matcher(str); 
        if (m.matches()) {
            //возможна путаница одиночной и открывающей скобки.
            //поэтому уточним какая же всё-таки это скобка
            if (!matchingOCBXML()) {
                    //все-таки открывающая
                    s.push(new StackElem(ElemType.xmlobrace, getName(str)));
            } 
            return true;
        } else {
            return false;
        }
    }
    //тест на одиночную XML'ую скобку
    private boolean matchingOCBXML() {
        p = Pattern.compile(ocxmlbr);
        m = p.matcher(str); 
        return m.matches();
    }
    private boolean analize(String seq) {
        for (char c : seq.toCharArray()) {
            if (tb) {
                //собираем XML'ю скобку
                if (c == '>') {
                    str += '>'; 
                    tb = false;
                    //тестируем претендента на XML'ю скобку
                    if (!matching()) {
                        return false;
                    }
                    str = "";
                } else {
                    //собираем...
                    str += c;
                }
                continue;
            }
            if (isOpenBracket(c)) {
                //попалась одна из скобок: (,[,<
                if (lb != ElemType.tobrace) {
                    //если не <, то сразу в стек
                    s.push(new StackElem(lb, lc));
                }
            }
            if (isCloseBracket(c)) {
                //попалась одна из скобок: ),]
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
        
        return (s.isEmpty() && !tb);
    }
        
    public boolean testBraces(String seq) {
        if (seq != null) {
            s = new Stack(); 
            lb = ElemType.nothing;
            tb = false; 
            str = "";
            return analize(seq);
        } else {
            return false; 
        }
    }
}
