/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.JobPositions;
import systems.tech247.pdr.UtilityPDR;


/**
 *
 * @author Admin
 */
public class PDRPositionEditor extends javax.swing.JPanel{

    
    
    DataAccess da = new DataAccess();
    JobPositions position;
    
    NumberFormat nf = new DecimalFormat("#,###.00");
    
    
    
    
    //Updatables
  
    String positionString = null;
    BigDecimal casualDailyRate;
    String casual;
    BigDecimal defaultRenumeration;
    String renumeration;
    int maxEmployees = 1;
    String maximEmployees = null;
    Boolean showInOrgan = false;
    String positionDetails;
    String positionCode;
  
    EntityManager entityManager = da.getEntityManager();
    
   
    /**
     * Creates new form PersonalInfoPanel
     */
    public PDRPositionEditor(){
        this(null);
    }
    
    
    
    
 public PDRPositionEditor(JobPositions position) {
        initComponents();
        //Start transaction
        this.position = position;
        
        if(position != null){
            jtPositionName.setText(position.getPositionName());
            jcbShowInOrgan.setSelected(position.getShowInOrganogram());
            jtPositionCode.setText(position.getPositionCode());
            jtPositionDetails.setText(position.getPositionDetails());
            jtCasualRate.setText(nf.format(position.getCasualDailyRate()));
            jtDefaultRenumeration.setText(nf.format(position.getDefaultRemuneration()));
            jtMaximumEmployees.setText(position.getMaxEmpsInOrganogram()+"");
            showInOrgan = position.getShowInOrganogram();
        }
        
        jtPositionName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                positionString = jtPositionName.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                positionString = jtPositionName.getText();            
            }

            @Override
            public void keyReleased(KeyEvent e) {
                positionString = jtPositionName.getText();            
            }
        });
        
        jtPositionCode.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                positionCode = jtPositionCode.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                positionCode = jtPositionCode.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                positionCode = jtPositionCode.getText();
            }
        });
        
        jtPositionDetails.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                positionDetails = jtPositionDetails.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                positionDetails = jtPositionDetails.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                positionDetails = jtPositionDetails.getText();
            }
        });
        
        jtDefaultRenumeration.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                renumeration = jtDefaultRenumeration.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                renumeration = jtDefaultRenumeration.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                renumeration = jtDefaultRenumeration.getText();
            }
        });
        
        jtCasualRate.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                casual = jtCasualRate.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                casual = jtCasualRate.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                casual = jtCasualRate.getText();
            }
        });
        
        jtMaximumEmployees.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                maximEmployees = jtMaximumEmployees.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                maximEmployees = jtMaximumEmployees.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                maximEmployees = jtMaximumEmployees.getText();
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

        jtRenumeration = new javax.swing.JTextField();
        jtDailyCasualRate = new javax.swing.JTextField();
        jtPositionCode1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtPositionName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtPositionCode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPositionDetails = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jcbShowInOrgan = new javax.swing.JCheckBox();
        jtDefaultRenumeration = new javax.swing.JFormattedTextField();
        jtCasualRate = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jtMaximumEmployees = new javax.swing.JFormattedTextField();
        jbSave = new javax.swing.JButton();

        jtRenumeration.setText(org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jtRenumeration.text")); // NOI18N

        jtDailyCasualRate.setText(org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jtDailyCasualRate.text")); // NOI18N

        jtPositionCode1.setText(org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jtPositionCode1.text")); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jLabel1.text_1")); // NOI18N

        jtPositionName.setText(org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jtPositionName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jLabel2.text_1")); // NOI18N

        jtPositionCode.setEditable(false);
        jtPositionCode.setText(org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jtPositionCode.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jLabel3.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jLabel4.text_1")); // NOI18N

        jtPositionDetails.setColumns(20);
        jtPositionDetails.setRows(5);
        jScrollPane1.setViewportView(jtPositionDetails);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbShowInOrgan, org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jcbShowInOrgan.text")); // NOI18N
        jcbShowInOrgan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbShowInOrganActionPerformed(evt);
            }
        });

        jtDefaultRenumeration.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jtDefaultRenumeration.setText(org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jtDefaultRenumeration.text")); // NOI18N

        jtCasualRate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jtCasualRate.setText(org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jtCasualRate.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jLabel6.text")); // NOI18N

        jtMaximumEmployees.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jtMaximumEmployees.setText(org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jtMaximumEmployees.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jcbShowInOrgan)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtPositionName, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                    .addComponent(jtPositionCode, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                    .addComponent(jtDefaultRenumeration)
                                    .addComponent(jtCasualRate)
                                    .addComponent(jtMaximumEmployees))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtPositionName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtPositionCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtMaximumEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtDefaultRenumeration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtCasualRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbShowInOrgan))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(PDRPositionEditor.class, "PDRPositionEditor.jbSave.text_1")); // NOI18N
        jbSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbSave, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbSave)
                .addGap(8, 8, 8))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveActionPerformed
        entityManager.getTransaction().begin();   
        
        
        if(position == null){ //Add New Position
            String insertSQL = "INSERT INTO [dbo].[JobPositions]\n" +
"           ([PositionName]\n" +
"           ,[PositionDetails]\n" +
"           ,[DefaultRemuneration]\n" +
"           ,[ShowInOrganogram]\n" +
"           ,[MaxEmpsInOrganogram]\n" +
"           ,[CasualDailyRate])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, positionString);
            query.setParameter(2, positionDetails);
            if(null != renumeration){
                defaultRenumeration = new BigDecimal(renumeration, MathContext.DECIMAL32);
                query.setParameter(3, defaultRenumeration);
            }
            if(null != casual){
                casualDailyRate = new BigDecimal(casual, MathContext.DECIMAL32);
                query.setParameter(6, casualDailyRate);
            }
            
            if(null != maximEmployees){
                maxEmployees = new Integer(maximEmployees);
                query.setParameter(5, maxEmployees);
            }
            query.setParameter(4, showInOrgan);
            
            
            
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            StatusDisplayer.getDefault().setStatusText("Position Added");
            String sqlString ="SELECT r from JobPositions r";
            UtilityPDR.loadJobPositions(sqlString);
        }else{
            
            JobPositions eba = entityManager.find(JobPositions.class, position.getPositionID());
            
            if(positionString != null){
                eba.setPositionName(positionString);
            }
            
            if(positionDetails != null){
                eba.setPositionDetails(positionDetails);
            }
            
            eba.setShowInOrganogram(showInOrgan);
            if(null != casualDailyRate){
                eba.setCasualDailyRate(casualDailyRate);
            }
            
            if(null != defaultRenumeration){
                eba.setDefaultRemuneration(defaultRenumeration);
            }
           
            
            
            
            entityManager.getTransaction().commit();
            String sqlString ="SELECT r from JobPositions r";
            UtilityPDR.loadJobPositions(sqlString);
            
            StatusDisplayer.getDefault().setStatusText("Position Saved");
            resetEditor();
        }
        

    }//GEN-LAST:event_jbSaveActionPerformed

    private void jcbShowInOrganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbShowInOrganActionPerformed
        showInOrgan = jcbShowInOrgan.isSelected();
    }//GEN-LAST:event_jcbShowInOrganActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbSave;
    private javax.swing.JCheckBox jcbShowInOrgan;
    private javax.swing.JFormattedTextField jtCasualRate;
    private javax.swing.JTextField jtDailyCasualRate;
    private javax.swing.JFormattedTextField jtDefaultRenumeration;
    private javax.swing.JFormattedTextField jtMaximumEmployees;
    private javax.swing.JTextField jtPositionCode;
    private javax.swing.JTextField jtPositionCode1;
    private javax.swing.JTextArea jtPositionDetails;
    private javax.swing.JTextField jtPositionName;
    private javax.swing.JTextField jtRenumeration;
    // End of variables declaration//GEN-END:variables

    
    
    
    void modify(){
        
                jbSave.setEnabled(true);
            
        
    }

    
    public Lookup getLookup() {
        TopComponent tc = WindowManager.getDefault().findTopComponent("EmployeeEditorTopComponent");
        return tc.getLookup();
    }
    
    
    
    void resetEditor(){
        jtPositionName.setText("");
        positionString = null;
        jtPositionCode.setText("");
        positionCode = null;
        jtMaximumEmployees.setText("");
        maxEmployees = 1;
        maximEmployees = null;
        jtCasualRate.setText("");
        jtDailyCasualRate.setText("");
        jtPositionDetails.setText("");
        positionDetails = null;
      
        
    }
    
    


}
