/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * ReportFrame
 * @author dima6120
 */

package guiins;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ReportFrame extends JFrame{
    private JLabel label = new JLabel(); 
    private JButton ok = new JButton("Ok");
    
    private void OkMouseClicked(MouseEvent evt) {
        this.setVisible(false);
        this.dispose();
    }
    
    private void init(String mes) {
        ImageIcon img = new ImageIcon("info.png");
        this.setIconImage(img.getImage());
        
        this.setResizable(false);
        label.setText(mes);
        
        ok.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                  OkMouseClicked(evt);
             }
         });


        GroupLayout layout = new GroupLayout(getContentPane());    

        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).
            addComponent(label, GroupLayout.Alignment.CENTER).
            addComponent(ok, GroupLayout.Alignment.CENTER));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                    addComponent(label));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                    addComponent(ok));
        layout.setVerticalGroup(vGroup);

        this.pack();
    }
    
    public ReportFrame(String mes) {
        super("Report");
        init(mes);
    }
}
