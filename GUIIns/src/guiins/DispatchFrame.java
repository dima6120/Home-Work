/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * DispatchFrame
 * @author dima6120
 */

package guiins;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class DispatchFrame extends JFrame {
    private JLabel wait = new JLabel();
    
    private void init() {
        ImageIcon img = new ImageIcon("email.png");
        this.setIconImage(img.getImage());
        
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       
        wait.setText("Sending...");
        
        GroupLayout layout = new GroupLayout(getContentPane());
        
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().
            addComponent(wait, GroupLayout.Alignment.CENTER));
        layout.setHorizontalGroup(hGroup);
        
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(wait, GroupLayout.Alignment.CENTER));
        layout.setVerticalGroup(vGroup);
        
        this.pack();
    }
    public DispatchFrame() {
        super("Sending...");
        init();
    }
}

