package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main implements ActionListener {
    private final JButton btn_loadPDF;
    private final JButton btn_savePDF;

    private File selectedFile;

    public Main() {
        JFrame frame = new JFrame("PDF Şifreleyici");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btn_loadPDF = new JButton("PDF Dosyası Seç");
        btn_loadPDF.addActionListener(this);

        btn_savePDF = new JButton("PDF Dosyasını Şifrele ve Kaydet");
        btn_savePDF.addActionListener(this);
        btn_savePDF.setEnabled(false);

        JPanel panel = new JPanel();
        panel.add(btn_loadPDF);
        panel.add(btn_savePDF);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_loadPDF) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + File.separator + "Desktop"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Dosyası", "pdf"));

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                btn_savePDF.setEnabled(true);
            } else {
                System.out.println("Dosya Seçilmedi!");
            }
        } else if (e.getSource() == btn_savePDF) {
            if (selectedFile != null) {
                String password = JOptionPane.showInputDialog(null, "Lütfen Şifreyi Belirleyin:","", JOptionPane.PLAIN_MESSAGE);

                if (password != null && !password.isEmpty()) {
                    try {
                        PDDocument document = PDDocument.load(selectedFile);
                        AccessPermission accessPermission = new AccessPermission();
                        accessPermission.setCanPrint(false);
                        accessPermission.setCanModify(false);
                        StandardProtectionPolicy protectionPolicy = new StandardProtectionPolicy(password, password, accessPermission);
                        document.protect(protectionPolicy);
                        JFileChooser saveFileChooser = new JFileChooser();
                        saveFileChooser.setFileFilter(new FileNameExtensionFilter("PDF Dosyası", "pdf"));
                        int saveReturnValue = saveFileChooser.showSaveDialog(null);
                        if (saveReturnValue == JFileChooser.APPROVE_OPTION) {
                            File saveFile = saveFileChooser.getSelectedFile();
                            // Kayıt sırasında sonunda ".pdf" ekliyor.
                            if (!saveFile.getName().toLowerCase().endsWith(".pdf")) {
                                saveFile = new File(saveFile.getAbsolutePath() + ".pdf");
                            }
                            document.save(saveFile);
                            document.close();
                            JOptionPane.showMessageDialog(null, "İşlem Başarılı.", "", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Geçerli bir şifre giriniz!", "Hata!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
