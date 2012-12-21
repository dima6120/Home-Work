/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * FieldsController
 * @author dima6120
 */

package guiins;

import java.text.ParseException;
import java.util.Observable;
import javax.swing.JFormattedTextField.AbstractFormatter;


public class FieldsController extends Observable{
    private AbstractFormatter af;
    
    public FieldsController(AbstractFormatter af) {
        this.af = af;
    }
    public void setChanges(FieldType ft, String s) {
        String fs;
        try {
            fs = af.valueToString(af.stringToValue(s));
        } catch (ParseException ex) {
            fs = "";
        }
        setChanged();
        notifyObservers(new FieldPackage(ft, fs));
    }
}
