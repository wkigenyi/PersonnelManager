/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JButton;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Employees;
import systems.tech247.hr.OrganizationUnitTypes;
import systems.tech247.pdr.UtilityPDR;


/**
 *
 * @author Admin
 */
public class PDROUTypeEditor extends javax.swing.JPanel implements LookupListener{

    
    
    DataAccess da = new DataAccess();
    OrganizationUnitTypes departmentType;
    
    
    
    
    //Updatables
    String outypeName = null;
    short level = -1;
    String ouTypeCode = null;
    String ouHeadTitle = null;
    int parentOUID  = -1;
    OrganizationUnitTypes parentOUType = null;
    
    
    EntityManager entityManager = da.getEntityManager();
    TopComponent empTc = WindowManager.getDefault().findTopComponent("EmployeesTopComponent");
    Lookup.Result<Employees> empRslt = empTc.getLookup().lookupResult(Employees.class);
    TopComponent outypeTc = WindowManager.getDefault().findTopComponent("OUTypesTopComponent");
    Lookup.Result<OrganizationUnitTypes> bankRslt = outypeTc.getLookup().lookupResult(OrganizationUnitTypes.class);
   
    /**
     * Creates new form PersonalInfoPanel
     */
    public PDROUTypeEditor(){
        this(null);
    }
    
    
    
    
 public PDROUTypeEditor(OrganizationUnitTypes departmentType) {
        initComponents();
        //Start transaction
        this.departmentType = departmentType;
        
        if(departmentType != null){
            jtOUTypeName.setText(departmentType.getOrganizationUnitTypeName());
            jtOUTypeCode.setText(departmentType.getOrganizationUnitTypeCode());
            try{
                jtParentOUType.setText(departmentType.getParentOrganizationUnitTypeID().getOrganizationUnitTypeName());
            }catch(NullPointerException ex){
                
            }
            
        }
        
        
        bankRslt.addLookupListener(this);
        resultChanged(new LookupEvent(empRslt));
        
        
        jtOUTypeName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                outypeName = jtOUTypeName.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                outypeName = jtOUTypeName.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                outypeName = jtOUTypeName.getText();
            }
        });
        
        jtOUTypeCode.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ouTypeCode = jtOUTypeCode.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                ouTypeCode = jtOUTypeCode.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                ouTypeCode = jtOUTypeCode.getText();
                modify();
            }
        });
        
        
        
        jtOUTypeTitle.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ouHeadTitle = jtOUTypeTitle.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                ouHeadTitle = jtOUTypeTitle.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                ouHeadTitle = jtOUTypeTitle.getText();
                modify();
            }
        });
        
        jtParentOUType.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
                
                
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(outypeTc, "Select OU Type",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null)); 
                
                
                
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
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

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jtOUTypeCode = new javax.swing.JTextField();
        jtOUTypeName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtParentOUType = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtOUTypeTitle = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtOUTypeLevel = new javax.swing.JTextField();
        jbSave = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PDROUTypeEditor.class, "PDROUTypeEditor.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PDROUTypeEditor.class, "PDROUTypeEditor.jLabel1.text")); // NOI18N

        jtOUTypeCode.setText(org.openide.util.NbBundle.getMessage(PDROUTypeEditor.class, "PDROUTypeEditor.jtOUTypeCode.text")); // NOI18N

        jtOUTypeName.setText(org.openide.util.NbBundle.getMessage(PDROUTypeEditor.class, "PDROUTypeEditor.jtOUTypeName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(PDROUTypeEditor.class, "PDROUTypeEditor.jLabel4.text")); // NOI18N

        jtParentOUType.setBackground(new java.awt.Color(0, 204, 0));
        jtParentOUType.setText(org.openide.util.NbBundle.getMessage(PDROUTypeEditor.class, "PDROUTypeEditor.jtParentOUType.text")); // NOI18N
        jtParentOUType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtParentOUTypeActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(PDROUTypeEditor.class, "PDROUTypeEditor.jLabel6.text")); // NOI18N

        jtOUTypeTitle.setText(org.openide.util.NbBundle.getMessage(PDROUTypeEditor.class, "PDROUTypeEditor.jtOUTypeTitle.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(PDROUTypeEditor.class, "PDROUTypeEditor.jLabel5.text")); // NOI18N

        jtOUTypeLevel.setText(org.openide.util.NbBundle.getMessage(PDROUTypeEditor.class, "PDROUTypeEditor.jtOUTypeLevel.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtOUTypeName, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jtOUTypeCode)
                    .addComponent(jtParentOUType)
                    .addComponent(jtOUTypeTitle)
                    .addComponent(jtOUTypeLevel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtOUTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtOUTypeCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtOUTypeLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtParentOUType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtOUTypeTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(PDROUTypeEditor.class, "PDROUTypeEditor.jbSave.text")); // NOI18N
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
        
        if(departmentType == null){ //Add New Account
            String insertSQL = "INSERT INTO [dbo].[OrganizationUnitTypes]\n" +
"           ([OrganizationUnitTypeCode]\n" +
"           ,[OrganizationUnitTypeName]\n" +
"           ,[OrganizationUnitTypeLevel]\n" +
"           ,[TitleOfHeadEmployee]\n" +
"           ,[ParentOrganizationUnitTypeID])\n" +
"     VALUES\n" +
"           (?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, ouTypeCode);
            query.setParameter(2, outypeName);
            query.setParameter(3, level);
            query.setParameter(4, ouHeadTitle);
            query.setParameter(5, parentOUID);
            
            
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            String sqlString ="SELECT r from OrganizationUnitTypes r";
            UtilityPDR.loadOUTypes(sqlString);
            StatusDisplayer.getDefault().setStatusText("OU Type Added");
        }else{
            
            OrganizationUnitTypes eba = entityManager.find(OrganizationUnitTypes.class, departmentType.getParentOrganizationUnitTypeID());
            
            if(null != ouTypeCode){
                eba.setOrganizationUnitTypeCode(ouTypeCode);
            }
            
            if(null != outypeName){
                eba.setOrganizationUnitTypeName(outypeName);
            }
            
            if(null != ouHeadTitle){
                eba.setTitleOfHeadEmployee(ouHeadTitle);
            }
            
            if(level >= 0){
                eba.setOrganizationUnitTypeLevel(level);
            }
            
            if(parentOUID >=0){
                eba.setParentOrganizationUnitTypeID(parentOUType);
            }
            
            
            
            entityManager.getTransaction().commit();
            StatusDisplayer.getDefault().setStatusText("OU Type Saved");
            
            String sqlString ="SELECT r from OrganizationUnitTypes r";
            UtilityPDR.loadOUTypes(sqlString);
            
            resetEditor();
        }
        

    }//GEN-LAST:event_jbSaveActionPerformed

    private void jtParentOUTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtParentOUTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtParentOUTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbSave;
    private javax.swing.JTextField jtOUTypeCode;
    private javax.swing.JTextField jtOUTypeLevel;
    private javax.swing.JTextField jtOUTypeName;
    private javax.swing.JTextField jtOUTypeTitle;
    private javax.swing.JTextField jtParentOUType;
    // End of variables declaration//GEN-END:variables

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result result = (Lookup.Result)le.getSource();
        for (Object e: result.allInstances()){
            
                
                
               if(e instanceof OrganizationUnitTypes){
                parentOUType = ((OrganizationUnitTypes) e);
                jtParentOUType.setText(parentOUType.getOrganizationUnitTypeName());
                modify();
            } 
                
                
                
                
                
                
            }
            
            
            
            
            
            
            
            
            
        }
    
    
    void modify(){
        
                jbSave.setEnabled(true);
            
        
    }

    
    public Lookup getLookup() {
        TopComponent tc = WindowManager.getDefault().findTopComponent("EmployeeEditorTopComponent");
        return tc.getLookup();
    }
    
    
    
    void resetEditor(){
        jtOUTypeName.setText("");
        jtOUTypeCode.setText("");
        jtParentOUType.setText("");
        jtOUTypeTitle.setText("");
        
        
        parentOUID = -1;
        parentOUType = null;
        ouHeadTitle = null;
        ouTypeCode = null;
        outypeName = null;
        
    }
    
    


}
