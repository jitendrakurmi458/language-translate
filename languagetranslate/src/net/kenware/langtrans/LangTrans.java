/*
 * LangTrans.java
 *
 * Created on March 23, 2007, 9:58 PM
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package net.kenware.langtrans;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import net.stoken.utils.AppInfo;
import net.stoken.swing.AppInfoPanel;

/**
 * Main user interface for Language Translation.  This UI has two main
 * panes.  The top has the source and the bottom has the translated 
 * results.  The contents from the top and bottom can be flipped using 
 * a flip button.
 *
 * @author uuklanger
 */
public class LangTrans extends javax.swing.JFrame {
    
    // =============================================================
    //  Constants
    // =============================================================
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    /**
     * List of supported languages for things like the comboboxes.
     */
    public static String[] LANGUAGE_ISO3166_LIST = new String[]{ "de", "en", "es", "fr", "ru" };
    // </editor-fold>
    
    // =============================================================
    //  Constructors and Init
    // =============================================================
    
    // <editor-fold defaultstate="collapsed" desc="Constructors and Init">
    /**
     * Creates new form LangTrans
     */
    public LangTrans() {
        InputStream in = null;
        Properties props = new Properties();
        
        //
        // net netbeans do its magic
        //
        initComponents();
        
        //
        // load properties file
        //
        try {
            //
            // get Application information
            //
            in = getClass().getResourceAsStream(AppInfo.DEFAULT_PROPFILENAME);
            props.load(in);
            AppInfo.init(props);
            in.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } // end-try-catch
        
        //
        // set main frame title and size.
        //
        this.setTitle(AppInfo.getPROGNAME() + " " + AppInfo.getFULLVERSION());
        
        //
        // set any Misc GUI settings
        //
        guiInit();
        
        return;
    }
    
    /**
     * After all the standard init is complete, this is called
     * to for anything not set during Matisse init.
     */
    private void guiInit() {
        InputStream in = null;
        Properties langTransProps = new Properties();
        
        //
        // load properties file
        //
        try {
            //
            // get Application information
            //
            in = getClass().getResourceAsStream("LangTransDefaults.properties");
            langTransProps.load(in);
            in.close();
            
            //
            // set GUI defaults
            //
            this.jComboBoxSource.setSelectedItem(langTransProps.getProperty("SOURCE.LANG"));
            this.jComboBoxTarget.setSelectedItem(langTransProps.getProperty("TARGET.LANG"));
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } // end-try-catch
        
        return;
    }
    
    // </editor-fold>
    
    // =============================================================
    //  Netbeans (Matisse) Generated Code
    // =============================================================
    
    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jSplitPane = new javax.swing.JSplitPane();
        jScrollPaneSource = new javax.swing.JScrollPane();
        jTextAreaSource = new javax.swing.JTextArea();
        jScrollPaneResult = new javax.swing.JScrollPane();
        jTextAreaResult = new javax.swing.JTextArea();
        jToolBar = new javax.swing.JToolBar();
        jLabelSource = new javax.swing.JLabel();
        jComboBoxSource = new javax.swing.JComboBox();
        jLabelTarget = new javax.swing.JLabel();
        jComboBoxTarget = new javax.swing.JComboBox();
        jButtonFlipLanguages = new javax.swing.JButton();
        jButtonTranslate = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemQuit = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jSplitPane.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jSplitPane.setDividerLocation(120);
        jSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jScrollPaneSource.setAutoscrolls(true);
        jScrollPaneSource.setDoubleBuffered(true);
        jTextAreaSource.setColumns(20);
        jTextAreaSource.setLineWrap(true);
        jTextAreaSource.setRows(5);
        jTextAreaSource.setWrapStyleWord(true);
        jTextAreaSource.setDoubleBuffered(true);
        jTextAreaSource.setDragEnabled(true);
        jTextAreaSource.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextAreaSourceFocusGained(evt);
            }
        });
        jTextAreaSource.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextAreaSourceKeyTyped(evt);
            }
        });

        jScrollPaneSource.setViewportView(jTextAreaSource);

        jSplitPane.setLeftComponent(jScrollPaneSource);

        jScrollPaneResult.setAutoscrolls(true);
        jTextAreaResult.setColumns(20);
        jTextAreaResult.setLineWrap(true);
        jTextAreaResult.setRows(5);
        jTextAreaResult.setWrapStyleWord(true);
        jTextAreaResult.setDoubleBuffered(true);
        jTextAreaResult.setDragEnabled(true);
        jTextAreaResult.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextAreaResultFocusGained(evt);
            }
        });

        jScrollPaneResult.setViewportView(jTextAreaResult);

        jSplitPane.setRightComponent(jScrollPaneResult);

        jToolBar.setRollover(true);
        jToolBar.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabelSource.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabelSource.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSource.setText("Source: ");
        jLabelSource.setMaximumSize(new java.awt.Dimension(50, 13));
        jLabelSource.setMinimumSize(new java.awt.Dimension(50, 13));
        jToolBar.add(jLabelSource);

        jComboBoxSource.setFont(new java.awt.Font("Dialog", 0, 10));
        jComboBoxSource.setModel(new javax.swing.DefaultComboBoxModel(LANGUAGE_ISO3166_LIST));
        jComboBoxSource.setMaximumSize(new java.awt.Dimension(50, 20));
        jComboBoxSource.setMinimumSize(new java.awt.Dimension(50, 20));
        jComboBoxSource.setPreferredSize(new java.awt.Dimension(50, 20));
        jToolBar.add(jComboBoxSource);

        jLabelTarget.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabelTarget.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTarget.setText("  Target: ");
        jLabelTarget.setMaximumSize(new java.awt.Dimension(50, 13));
        jLabelTarget.setMinimumSize(new java.awt.Dimension(50, 13));
        jToolBar.add(jLabelTarget);

        jComboBoxTarget.setFont(new java.awt.Font("Dialog", 0, 10));
        jComboBoxTarget.setModel(new javax.swing.DefaultComboBoxModel(LANGUAGE_ISO3166_LIST));
        jComboBoxTarget.setMaximumSize(new java.awt.Dimension(50, 20));
        jComboBoxTarget.setMinimumSize(new java.awt.Dimension(50, 20));
        jComboBoxTarget.setPreferredSize(new java.awt.Dimension(50, 20));
        jToolBar.add(jComboBoxTarget);

        jButtonFlipLanguages.setFont(new java.awt.Font("Dialog", 0, 10));
        jButtonFlipLanguages.setMnemonic('l');
        jButtonFlipLanguages.setText("Flip Language");
        jButtonFlipLanguages.setActionCommand("FlipLanguage");
        jButtonFlipLanguages.setMaximumSize(new java.awt.Dimension(100, 30));
        jButtonFlipLanguages.setMinimumSize(new java.awt.Dimension(100, 30));
        jButtonFlipLanguages.setPreferredSize(new java.awt.Dimension(100, 30));
        jButtonFlipLanguages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFlipLanguagesActionPerformed(evt);
            }
        });

        jToolBar.add(jButtonFlipLanguages);

        jButtonTranslate.setFont(new java.awt.Font("Dialog", 1, 10));
        jButtonTranslate.setMnemonic('t');
        jButtonTranslate.setText("Translate");
        jButtonTranslate.setEnabled(false);
        jButtonTranslate.setMaximumSize(new java.awt.Dimension(79, 30));
        jButtonTranslate.setMinimumSize(new java.awt.Dimension(70, 30));
        jButtonTranslate.setPreferredSize(new java.awt.Dimension(62, 30));
        jButtonTranslate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTranslateActionPerformed(evt);
            }
        });

        jToolBar.add(jButtonTranslate);

        jMenuFile.setMnemonic('f');
        jMenuFile.setText("File");
        jMenuFile.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItemQuit.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItemQuit.setMnemonic('q');
        jMenuItemQuit.setText("Quit");
        jMenuItemQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuitActionPerformed(evt);
            }
        });

        jMenuFile.add(jMenuItemQuit);

        jMenuBar.add(jMenuFile);

        jMenuHelp.setMnemonic('h');
        jMenuHelp.setText("Help");
        jMenuHelp.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItemAbout.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItemAbout.setMnemonic('a');
        jMenuItemAbout.setText("About");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });

        jMenuHelp.add(jMenuItemAbout);

        jMenuBar.add(jMenuHelp);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
            .addComponent(jSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // =============================================================
    //  Event Handlers
    // =============================================================
    
    // <editor-fold defaultstate="collapsed" desc="Event Handlers">
    /**
     * Quit button clicked
     * @param evt ActionEvent
     */
    private void jMenuItemQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuitActionPerformed
        this.dispose();
        return;
    }//GEN-LAST:event_jMenuItemQuitActionPerformed
    
    /**
     * About menu options clicked
     * @param evt ActionEvent
     */
    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        AppInfoPanel aboutpanel = new AppInfoPanel();
        aboutpanel.loadAppInfoValues();
        
        JOptionPane.showMessageDialog(
                this,
                aboutpanel,
                "About",
                JOptionPane.PLAIN_MESSAGE
                );
        System.out.println("RETURNED");
        
        return;
    }//GEN-LAST:event_jMenuItemAboutActionPerformed
    
    /**
     * Flip button pressed.  This will flip source/target languages.
     * @param evt ActionEvent
     */
    private void jButtonFlipLanguagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFlipLanguagesActionPerformed
        String source = this.jComboBoxSource.getSelectedItem().toString();
        String target = this.jComboBoxTarget.getSelectedItem().toString();
        String sourcetext = this.jTextAreaSource.getText();
        String resulttext = this.jTextAreaResult.getText();
        
        this.jComboBoxSource.setSelectedItem(target);
        this.jComboBoxTarget.setSelectedItem(source);
        
        this.jTextAreaSource.setText(resulttext);
        this.jTextAreaResult.setText(sourcetext);
        
        return;
    }//GEN-LAST:event_jButtonFlipLanguagesActionPerformed
    
    /**
     * When focus is gained, all text gets highlighted
     * @param evt ActionEvent
     */
    private void jTextAreaResultFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAreaResultFocusGained
        JTextArea jta = (JTextArea) evt.getComponent();
        jta.selectAll();
        
        return;
    }//GEN-LAST:event_jTextAreaResultFocusGained
    
    /**
     * When focus is gained, all text gets highlighted
     * @param evt ActionEvent
     */
    private void jTextAreaSourceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAreaSourceFocusGained
        JTextArea jta = (JTextArea) evt.getComponent();
        jta.selectAll();
        
        return;
    }//GEN-LAST:event_jTextAreaSourceFocusGained
    
    /**
     * Translate button clicked.
     * @param evt ActionEvent
     */
    private void jButtonTranslateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTranslateActionPerformed
        GoogleTranslater gt = new GoogleTranslater();
        String sourcelang = this.jComboBoxSource.getSelectedItem().toString();
        String targetlang = this.jComboBoxTarget.getSelectedItem().toString();
        String resulttext = null;
        
        try {
            if(this.jTextAreaSource.getText().length() > 0) {
                if(sourcelang.equals(targetlang)) {
                    this.jTextAreaResult.setText(this.jTextAreaSource.getText());
                    
                } else {
                    resulttext = gt.urlTranslate(
                            this.jTextAreaSource.getText(),
                            sourcelang,
                            targetlang
                            );
                    
                    this.jTextAreaResult.setText(resulttext);
                } // end-if
            } // end-if
            
        } catch(Exception ex) {
            System.err.println("EXCEPTION:" + ex.getMessage());
        } // end-try-catch
        
        return;
    }//GEN-LAST:event_jButtonTranslateActionPerformed
    
    /**
     * If a key is pressed, code is fired to decide if translate button
     * should be on.
     * @param evt ActionEvent
     */
    private void jTextAreaSourceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaSourceKeyTyped
        JTextArea jta = (JTextArea) evt.getSource();
        
        try {
            if(jta.getText().length() > 0) {
                this.jButtonTranslate.setEnabled(true);
            } else {
                this.jButtonTranslate.setEnabled(false);
            } // end-if
            
        } catch(Exception ex) {
            System.err.println("EXCEPTION:" + ex.getMessage());
        } // end-try-catch
        
        return;
    }//GEN-LAST:event_jTextAreaSourceKeyTyped
    // </editor-fold>
    
    // =============================================================
    //  MAIN
    // =============================================================
    
    // <editor-fold defaultstate="collapsed" desc="MAIN">
    /**
     * Main access point into program.
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LangTrans().setVisible(true);
            }
        });
    }
    // </editor-fold>
    
    // =============================================================
    //  Private Members
    // =============================================================
    
    // <editor-fold defaultstate="collapsed" desc="Private Members">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Button for flipping source/target languages and text
     */
    private javax.swing.JButton jButtonFlipLanguages;
    /**
     * Translate button
     */
    private javax.swing.JButton jButtonTranslate;
    /**
     * Source languages
     */
    private javax.swing.JComboBox jComboBoxSource;
    /**
     * Target languages
     */
    private javax.swing.JComboBox jComboBoxTarget;
    /**
     * "Source" label
     */
    private javax.swing.JLabel jLabelSource;
    /**
     * "Target" label
     */
    private javax.swing.JLabel jLabelTarget;
    /**
     * Menu bar
     */
    private javax.swing.JMenuBar jMenuBar;
    /**
     * File menu option
     */
    private javax.swing.JMenu jMenuFile;
    /**
     * Help menu
     */
    private javax.swing.JMenu jMenuHelp;
    /**
     * About menu item
     */
    private javax.swing.JMenuItem jMenuItemAbout;
    /**
     * Quit menu item.
     */
    private javax.swing.JMenuItem jMenuItemQuit;
    /**
     * Scroll pane for result
     */
    private javax.swing.JScrollPane jScrollPaneResult;
    /**
     * Scroll pane for source
     */
    private javax.swing.JScrollPane jScrollPaneSource;
    /**
     * top/bottom splitter
     */
    private javax.swing.JSplitPane jSplitPane;
    /**
     * results text area
     */
    private javax.swing.JTextArea jTextAreaResult;
    /**
     * text area source
     */
    private javax.swing.JTextArea jTextAreaSource;
    /**
     * toolbar at bottom.
     */
    private javax.swing.JToolBar jToolBar;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
} // end-class
