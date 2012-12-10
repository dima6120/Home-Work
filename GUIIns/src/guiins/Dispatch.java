/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Dispatch
 * @author dima6120
 */

package guiins;

import sendmail.SendMail;

public class Dispatch {
    public void dispatch(DispatchListener jf, String[] mes, String addr) {
        Thread st = new Thread(new ThreadForSend(jf, mes, addr));
        st.start();
    }
}

class ThreadForSend implements Runnable {
    private String []mes; 
    private DispatchListener jf;
    private String addr;
    public ThreadForSend(DispatchListener jf, String []mes, String addr) {
        this.mes = mes;
        this.jf = jf;
        this.addr = addr;
    }

    @Override
    public void run() {
        jf.dispatchfinished(SendMail.send(mes, addr));
    }
}