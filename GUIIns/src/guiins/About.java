/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * About
 * @author dima6120
 */

package guiins;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class About extends JFrame {
    private JLabel author = new JLabel();
    private JLabel app = new JLabel();
    private JButton ok = new JButton("Ok");
    
    public void OkMouseClicked(MouseEvent evt) {
        this.setVisible(false);
    }
    
    private void Init() {
        ImageIcon img = new ImageIcon("about.png");
        this.setIconImage(img.getImage());
        
        this.setResizable(false);
        
        app.setText("TempConv");
        author.setText("Копытов Дмитрий Сергеевич, (с) 2012 год ");
        
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
        hGroup.addGroup(layout.createParallelGroup().
            addComponent(app).addComponent(author).addComponent(ok, GroupLayout.Alignment.CENTER));
        layout.setHorizontalGroup(hGroup);
        
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(app));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(author));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).
            addComponent(ok));
        layout.setVerticalGroup(vGroup);
        
        this.pack();
    }
    public About() {
        super("About");
        Init();
    }
}
