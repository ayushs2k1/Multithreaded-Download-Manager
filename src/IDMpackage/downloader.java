package IDMpackage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
public class downloader extends javax.swing.JFrame implements
        ActionListener{ static void downloadComplete() {
}
    IDM idm;
    Thread thread;
    ProgressPanel progressPanel;
    JLabel label;
    public static JFrame downloaderFrame;
    public downloader() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
// <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        urlTextField = new javax.swing.JTextField();
        downloadButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("URL : ");
        urlTextField.addActionListener(new java.awt.event.ActionListener()
        { public void actionPerformed(java.awt.event.ActionEvent evt) {
            urlTextFieldActionPerformed(evt);
        }
        });
        downloadButton.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        downloadButton.setText("Save To");
        downloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            { downloadButtonActionPerformed(evt);
            }
        });
        jButton1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        { public void actionPerformed(java.awt.event.ActionEvent evt)
        {
            jButton1ActionPerformed(evt);
        }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup( layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(urlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(downloadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup( layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(urlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(downloadButton)
                                .addComponent(jButton1))
                        .addContainerGap(14, Short.MAX_VALUE))
        );
        urlTextField.getAccessibleContext().setAccessibleName("urlTextField");
        pack();
    }// </editor-fold>
    private void urlTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt)
    { JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showSaveDialog(this);
        File file = fileChooser.getSelectedFile();
        System.out.println("directory: " + file.getPath());
        progressPanel = new ProgressPanel();
        try {
            idm = new IDM(urlTextField.getText(), progressPanel, file.getPath());
            System.out.println("Starting Executers");
            progressPanel.startThread(idm);
            progressPanel.setUrlTextField(urlTextField.getText());
            progressPanel.setOutputDirectory(file.getPath(), urlTextField.getText().substring(urlTextField.getText().lastIndexOf('/') +1));
            urlTextField.setText(urlTextField.getText());
            this.dispose();
        } catch (Exception e) {
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)
    { this.dispose();
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable()
        { public void run() {
            new downloader().setVisible(true);
        }
        });
    }
    // Variables declaration - do not modify
    private javax.swing.JButton downloadButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField urlTextField;
    // End of variables declaration
    @Override
    public void actionPerformed(ActionEvent evt) {
    }
}