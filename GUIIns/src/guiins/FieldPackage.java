/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * FieldPackage
 * @author dima6120
 */

package guiins;


public class FieldPackage {
    private String[] sl = new String[3];
    public FieldPackage(FieldType ft, String s) {
        if ("".equals(s)) {
            sl[0] = ""; 
            sl[1] = "";
            sl[2] = "";
        } else {
            int v = Integer.parseInt(s);
            switch (ft) {
                case CF: 
                    sl[0] = s;
                    sl[1] = Integer.toString(v + 273);
                    sl[2] = Long.toString(Math.round(v * 9/5 + 32));
                    break;
                case KF:
                    sl[0] = Integer.toString(v - 273);
                    sl[1] = s;
                    sl[2] = Long.toString(Math.round(v * 9/5 - 459));
                    break;
                case FF:
                    sl[0] = Long.toString(Math.round(v - 32)*5/9);
                    sl[1] = Long.toString(Math.round((v + 459.67)*5/9));
                    sl[2] = s;
            }
        }
    }
    public String getCel() {
        return sl[0];
    }
    public String getKel() {
        return sl[1];
    }
    public String getFar() {
        return sl[2];
    }
}
