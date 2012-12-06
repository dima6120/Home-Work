/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * GUIIns
 * @author dima6120
 */

package guiins;

import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class GUIIns extends JFrame {
    private JMenuBar MenuBar = new JMenuBar();
    private JMenu FileMenu = new JMenu();
    private JMenuItem ExitItem = new JMenuItem();
    private JMenu HelpMenu = new JMenu();
    private JMenu AboutMenu = new JMenu();
    private JFormattedTextField CelsiusField = new JFormattedTextField();
    private JFormattedTextField FahrenheitField = new JFormattedTextField();
    private JFormattedTextField KelvinField = new JFormattedTextField(); 
    private JLabel CL = new JLabel();
    private JLabel KL = new JLabel();
    private JLabel FL = new JLabel();
    private About about = new About();
    
    private void ExitItemActionPerformed(ActionEvent evt) {
        this.dispose();
    }
    
    private void CelsiusFieldKeyPressed(KeyEvent evt) {
       if (evt.getKeyCode() == KeyEvent.VK_ENTER && !"".equals(CelsiusField.getText())) {
            try {
                CelsiusField.setValue(CelsiusField.getFormatter().stringToValue(CelsiusField.getText()));
                
            } catch (ParseException ex) {
                CelsiusField.setText("");
                KelvinField.setText("");
                FahrenheitField.setText("");
                return;
            }
            if (!"".equals(CelsiusField.getText())) {
                int v = Integer.parseInt(CelsiusField.getText());
                KelvinField.setText(Integer.toString(v + 273));
                FahrenheitField.setText(Long.toString(Math.round(v * 9/5 + 32)));
            } else {
                KelvinField.setText("");
                FahrenheitField.setText("");
            }
        } 
    }
    
    public void KelvinFieldKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !"".equals(KelvinField.getText())) {
            try {
                KelvinField.setValue(KelvinField.getFormatter().stringToValue(KelvinField.getText()));
                
            } catch (ParseException ex) {
                CelsiusField.setText("");
                KelvinField.setText("");
                FahrenheitField.setText("");
                return;
            }
            if (!"".equals(KelvinField.getText())) {
                int v = Integer.parseInt(KelvinField.getText());
                CelsiusField.setText(Integer.toString(v - 273));
                FahrenheitField.setText(Long.toString(Math.round(v * 9/5 - 459)));
            } else {
                CelsiusField.setText("");
                FahrenheitField.setText("");
            }
        }
    }
    
    public void FahrenheitFieldKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !"".equals(FahrenheitField.getText())) {
            try {
                FahrenheitField.setValue(FahrenheitField.getFormatter().stringToValue(FahrenheitField.getText()));
                
            } catch (ParseException ex) {
                CelsiusField.setText("");
                KelvinField.setText("");
                FahrenheitField.setText("");
                return;
            }
            if (!"".equals(FahrenheitField.getText())) {
                int v = Integer.parseInt(FahrenheitField.getText());
                CelsiusField.setText(Long.toString(Math.round(v - 32)*5/9));
                KelvinField.setText(Long.toString(Math.round((v + 459.67)*5/9)));
            } else {
                CelsiusField.setText("");
                KelvinField.setText("");
            }
        }
    }
    
    public void HelpMouseClicked(MouseEvent evt) {
        try {
            Runtime.getRuntime().exec("cmd /c notepad.exe readme.txt");
        } catch (IOException ex) {
            Logger.getLogger(GUIIns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AboutMouseClicked(MouseEvent evt) {
        about.setVisible(true);
    }
    
    private void Init() {
       ImageIcon img = new ImageIcon("icon.png");
       this.setIconImage(img.getImage());
       
       this.setResizable(false);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       CelsiusField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
       CelsiusField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                CelsiusFieldKeyPressed(evt);
            }
       });
 
       KelvinField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
       KelvinField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                KelvinFieldKeyPressed(evt);
            }
       });
       
       FahrenheitField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
       
       CL.setText("C");
       KL.setText("K");
       FL.setText("F");
       
       
       FileMenu.setText("File");
       ExitItem.setText("Exit");
       ExitItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitItemActionPerformed(evt);
            }
        });
       FileMenu.add(ExitItem);
       
       HelpMenu.setText("Help");
       HelpMenu.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(java.awt.event.MouseEvent evt) {
                HelpMouseClicked(evt);
           }
       });
       AboutMenu.setText("About");
       AboutMenu.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(java.awt.event.MouseEvent evt) {
                AboutMouseClicked(evt);
           }
       });
       
       MenuBar.add(FileMenu);
       MenuBar.add(HelpMenu);
       MenuBar.add(AboutMenu);
       
       this.setJMenuBar(MenuBar);
       
       GroupLayout layout = new GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       
       layout.setAutoCreateGaps(true);
       layout.setAutoCreateContainerGaps(true);
       
       layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(CelsiusField, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addComponent(KelvinField, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addComponent(FahrenheitField, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(CL)
                    .addComponent(KL)
                    .addComponent(FL))
                .addContainerGap()));
       
       layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(CelsiusField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(CL))
                .addGap(18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(KelvinField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(KL))
                .addGap(18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(FahrenheitField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(FL))
                )
        );
       this.pack();
    }
    
    public GUIIns() {
        super("TempConv");
        Init();
    }
    public static void main(String[] args) {
        GUIIns app = new GUIIns();
        app.setVisible(true);
    }
}
