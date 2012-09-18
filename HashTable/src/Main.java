/**
 * Main
 * @author dima6120
 */

import strcount.*;
import static strcount.Print.*;

public class Main {
    public static void main(String[] args) {
        StrCount c = new StrCount(10);
        c.put("abc");
        c.put("abc");
        c.put("abd");
        c.put("sdfsg");
        c.put("afdsc");
        c.put("afdsc");
        print("abc:" + c.get("abc"),true);
        print("abd:" + c.get("abd"),true);
        print("afdsc:" + c.get("afdsc"),true);
    }
}