/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.CompanyDetails;
import systems.tech247.pdr.UtilityPDR;

/**
 *
 * @author Admin
 */
public class PDREditorCompanyInfoPanel extends javax.swing.JPanel implements LookupListener{

    CompanyDetails emp;
    DataAccess da = new DataAccess();
    Boolean edit =false;
    
    //Updatables
    String pinNumber = null;
    String nssfNumber = null;
    String nhifNumber = null;
    String physicalAddress = null;
    String postalAddress =null;
    String tel1 =null;
    String tel2 = null;
    String tel3 = null;
    String email = null;
    String web = null;
    String fax = null;
    byte[] logo = null;
    String companyName = null;
    String helb = null;
    
    TopComponent companyTc = WindowManager.getDefault().findTopComponent("CompanyTopComponent");
    Lookup.Result<CompanyDetails> empRslt = companyTc.getLookup().lookupResult(CompanyDetails.class);
    
    /**
     * Creates new form PersonalInfoPanel
     */
    public PDREditorCompanyInfoPanel() {
        initComponents();
        //Start transaction
        
        
        empRslt.addLookupListener(this);
        resultChanged(new LookupEvent(empRslt));
        
        
        jtHelb.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                helb = jtHelb.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                helb = jtHelb.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                helb = jtHelb.getText();
                modify();
            }
        });
        
        jtTelephone1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                tel1 = jtTelephone1.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                tel1 = jtTelephone1.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                tel1 = jtTelephone1.getText();
                modify();
            }
        });
        
        jtEmail.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                email = jtEmail.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                email = jtEmail.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                email = jtEmail.getText();
                modify();
            }
        });
        
        
        jtPinno.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                pinNumber = jtPinno.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                pinNumber = jtPinno.getText();
                modify();
                
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pinNumber = jtPinno.getText();
                modify();
                
                
            }
        });
        jtCompany.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                companyName = jtCompany.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                companyName = jtCompany.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                companyName = jtCompany.getText();
                modify();
            }
        });
        
        jtNSSF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                nssfNumber = jtNSSF.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                nssfNumber = jtNSSF.getText();
                modify();
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                nssfNumber = jtNSSF.getText();
                modify();
            }
        });
        
        jtPostalAddress.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                postalAddress = jtPostalAddress.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                postalAddress = jtPostalAddress.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                postalAddress = jtPostalAddress.getText();
                modify();
            }
        });
        
        
        
        
        
        
        jtWeb.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                web = jtWeb.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                web = jtWeb.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                web = jtWeb.getText();
                modify();
            }
        });
        
       
        jtNHIFNumber.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                nhifNumber = jtNHIFNumber.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                nhifNumber = jtNHIFNumber.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                nhifNumber = jtNHIFNumber.getText();
                modify();
            }
        });
        
        jtFax.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                fax = jtFax.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                fax = jtFax.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                fax = jtFax.getText();
                modify();
            }
        });
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtPinno = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jlPhoto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jtNSSF = new javax.swing.JTextField();
        jtNHIFNumber = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtTelephone1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtWeb = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtFax = new javax.swing.JTextField();
        jtPostalAddress = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtTelephone2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jtTelephone3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPhysicalAddress = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtCompany = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtHelb = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jLabel1.text")); // NOI18N

        jtPinno.setText(org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jtPinno.text")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jlPhoto, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jlPhoto.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jLabel2.text")); // NOI18N

        jtNSSF.setText(org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jtNSSF.text")); // NOI18N

        jtNHIFNumber.setText(org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jtNHIFNumber.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jLabel3.text")); // NOI18N

        jtTelephone1.setText(org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jtTelephone1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jLabel4.text")); // NOI18N

        jtEmail.setText(org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jtEmail.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jLabel6.text")); // NOI18N

        jtWeb.setText(org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jtWeb.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jLabel7.text")); // NOI18N

        jtFax.setText(org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jtFax.text")); // NOI18N

        jtPostalAddress.setText(org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jtPostalAddress.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jLabel12.text")); // NOI18N

        jtTelephone2.setText(org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jtTelephone2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jLabel13.text")); // NOI18N

        jtTelephone3.setText(org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jtTelephone3.text")); // NOI18N

        jtPhysicalAddress.setColumns(20);
        jtPhysicalAddress.setRows(5);
        jScrollPane1.setViewportView(jtPhysicalAddress);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jLabel14.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jLabel9.text")); // NOI18N

        jtCompany.setText(org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jtCompany.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jLabel10.text")); // NOI18N

        jtHelb.setText(org.openide.util.NbBundle.getMessage(PDREditorCompanyInfoPanel.class, "PDREditorCompanyInfoPanel.jtHelb.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtPostalAddress)
                            .addComponent(jtFax)
                            .addComponent(jtWeb)
                            .addComponent(jtEmail)
                            .addComponent(jtTelephone1)
                            .addComponent(jtNHIFNumber)
                            .addComponent(jtNSSF)
                            .addComponent(jtPinno)
                            .addComponent(jtTelephone2)
                            .addComponent(jtTelephone3)
                            .addComponent(jtCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtHelb))))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jtPinno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtNSSF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jtHelb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtNHIFNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtTelephone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jtTelephone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jtTelephone3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jtWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jtPostalAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(37, 37, 37))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser jfc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Company Logo", "jpg","png","JPEG");
        jfc.setFileFilter(filter);
        int returnVal = jfc.showOpenDialog(this);
        
        String extension = "";
        if(returnVal==JFileChooser.APPROVE_OPTION){
            File file = jfc.getSelectedFile();
            extension = file.getName().replaceAll("^.*\\.([^.]+)$", "$1");
            //StatusDisplayer.getDefault().setStatusText("Extension: "+ extension);
           
            try {
                BufferedImage imm = ImageIO.read(file);
                ByteArrayOutputStream boas = new ByteArrayOutputStream();
                //Add the Encoding
                ImageIO.write(imm, extension, boas);
                boas.flush();
                logo = boas.toByteArray();
                boas.close();
                modify();
                
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }else{
            StatusDisplayer.getDefault().setStatusText("File Upload Aborted");
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlPhoto;
    private javax.swing.JTextField jtCompany;
    private javax.swing.JTextField jtEmail;
    private javax.swing.JTextField jtFax;
    private javax.swing.JTextField jtHelb;
    private javax.swing.JTextField jtNHIFNumber;
    private javax.swing.JTextField jtNSSF;
    private javax.swing.JTextArea jtPhysicalAddress;
    private javax.swing.JTextField jtPinno;
    private javax.swing.JTextField jtPostalAddress;
    private javax.swing.JTextField jtTelephone1;
    private javax.swing.JTextField jtTelephone2;
    private javax.swing.JTextField jtTelephone3;
    private javax.swing.JTextField jtWeb;
    // End of variables declaration//GEN-END:variables

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result<CompanyDetails> result = (Lookup.Result<CompanyDetails>)le.getSource();
        for (CompanyDetails e: result.allInstances()){
            
                
                
                
                emp = (CompanyDetails)e;
                try{
                jtPinno.setText(emp.getPINNumber() );
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtNSSF.setText(emp.getNSSFNumber());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                
                
                jtNHIFNumber.setText(emp.getNHIFNumber());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                
                try{
                
                
                jtCompany.setText(emp.getCompanyName());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtTelephone1.setText(emp.getTelephone1());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                
                try{
                jtHelb.setText(emp.getHelbLoanNumber()+"");
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                
                try{
                
                
                jtWeb.setText(emp.getWebURL());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtEmail.setText(emp.getEMail());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtFax.setText(emp.getFax());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtPostalAddress.setText(emp.getPostalAddress());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                
                
                //Set the photo   
             try {
                   ImageIcon photo = UtilityPDR.getImage(emp.getLogo());
                   //byte[] imageInfo = DataAccess.getPhotoBytes(e.getEmployeeID());
                   //BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageInfo));
                   //jlEmployeePhoto.setText(" Photo is too Large");
                   jlPhoto.setText("");
                   //StatusDisplayer.getDefault().setStatusText("Photo is too large");
                   
                   jlPhoto.setIcon(photo);
               } catch (IOException ex) {
                   Exceptions.printStackTrace(ex);
               } catch (NullPointerException ex){
                   jlPhoto.setIcon(null);
                   jlPhoto.setText("No Logo");
               }
                
                
                
            }
            
            
            
           
            
           
            
            
            
        
    }
    
    void modify(){
        
            if(getLookup().lookup(MySavable.class)==null){
                UtilityPDR.editorIC.add(new MySavable());
            }
        
    }

    
    public Lookup getLookup() {
        TopComponent tc = WindowManager.getDefault().findTopComponent("EmployeeEditorTopComponent");
        return tc.getLookup();
    }
    
    private class MySavable extends AbstractSavable{

        public MySavable(){
            register();
        } 
        
        @Override
        protected String findDisplayName() {
            return "Company Info";
        }

        @Override
        protected void handleSave() throws IOException {
            EntityManager entityManager = DataAccess.getEntityManager();
            //If it is an update
            if(emp != null ){
            UtilityPDR.editorIC.remove(this);
            unregister();
            entityManager.getTransaction().begin();
            CompanyDetails e = entityManager.find(CompanyDetails.class, emp.getCompanyID());
            if(null != tel1){
            e.setTelephone1(tel1);
            }
            if(null != tel2)
            {
                e.setTelephone2(tel2);
            }
            
            if(null!=tel3){
                e.setTelephone3(tel3);
            }
            
            if(fax != null){
                e.setFax(fax);
            }
            
            if(companyName != null){
            e.setCompanyName(companyName);
            }
            if(physicalAddress != null){
            e.setPhysicalAddress(physicalAddress);
            }
            
            if(postalAddress != null){
                e.setPostalAddress(postalAddress);
            }
            
            if(email != null){
                e.setEMail(email);
            }
            
            if(logo != null){
                e.setLogo(logo);
            }
            
            if(web != null){
                e.setWebURL(web);
            }
            
            if(pinNumber != null){
                e.setPINNumber(pinNumber);
            }
            
            if(nssfNumber != null){
                e.setNSSFNumber(nssfNumber);
            }
            
            if(nhifNumber != null){
                e.setNHIFNumber(nhifNumber);
            }
            
            
            entityManager.getTransaction().commit();
            }else{
                
            }
            
        }
        
        PDREditorCompanyInfoPanel pnel(){
            return PDREditorCompanyInfoPanel.this;
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof MySavable){
                MySavable m = (MySavable)o;
                return pnel() == m.pnel();
            }
            return false;
        }

        @Override
        public int hashCode() {
            return pnel().hashCode();
        }
        
    }
    
    
}
