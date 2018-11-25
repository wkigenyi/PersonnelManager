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
import systems.tech247.hr.OrganizationUnitTypes;
import systems.tech247.hr.OrganizationUnits;
import systems.tech247.pdr.UtilityPDR;


/**
 *
 * @author Admin
 */
public class PDRDeparmentEditor extends javax.swing.JPanel implements LookupListener{

    
    
    DataAccess da = new DataAccess();
    OrganizationUnits department;
    
    
    
    
    //Updatables
    String departmentName = null;
    OrganizationUnits parentDepartment = null;
    int parentID = 0;
    String departmentCode = null;
    String departmentFunction = null;
    String email = null;
    String telephone = null;
    
    EntityManager entityManager = da.getEntityManager();
    TopComponent outypeTc = WindowManager.getDefault().findTopComponent("OUTypesTopComponent");
    Lookup.Result<OrganizationUnitTypes> outypeRslt = outypeTc.getLookup().lookupResult(OrganizationUnitTypes.class);   
    
    TopComponent ouTc = WindowManager.getDefault().findTopComponent("DepartmentsTopComponent");
    Lookup.Result<OrganizationUnits> ouRslt = ouTc.getLookup().lookupResult(OrganizationUnits.class);   

    
    
    
    /**
     * Creates new form PersonalInfoPanel
     */
    public PDRDeparmentEditor(){
        this(null);
    }
    
    
    
    
 public PDRDeparmentEditor(OrganizationUnits department) {
        initComponents();
        //Start transaction
        this.department = department;
        
        if(department != null){
            jtDepartmentName.setText(department.getOrganizationUnitName());
            jtDepartmentType.setText(department.getOrganizationUnitTypeID().getOrganizationUnitTypeName());
            try{
                jtParentDeparent.setText(department.getParentOrganizationUnitID().getOrganizationUnitName());
            }catch(NullPointerException ex){
                
            }
            jtTelephone.setText(department.getTelephone());
            jtDepartmentCode.setText(department.getOrganizationUnitCode());
        }
        
        outypeRslt.addLookupListener(this);
        resultChanged(new LookupEvent(outypeRslt));
        
        ouRslt.addLookupListener(this);
        resultChanged(new LookupEvent(ouRslt));
        
        jtDepartmentName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                departmentName = jtDepartmentName.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                departmentName = jtDepartmentName.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                departmentName = jtDepartmentName.getText();
            }
        });
        
        
        
        jtTelephone.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                telephone = jtTelephone.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                telephone = jtTelephone.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                telephone = jtTelephone.getText();
                modify();
            }
        });
        
        jtDepartmentCode.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                departmentCode = jtDepartmentCode.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                departmentCode = jtDepartmentCode.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                departmentCode = jtDepartmentCode.getText();
                modify();
            }
        });
        
        jtDepartmentType.addKeyListener(new KeyListener() {
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
        
        jtParentDeparent.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(ouTc, "Select Parent OU",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null)); 
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
        jtDepartmentType = new javax.swing.JTextField();
        jtDepartmentName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtParentDeparent = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtTelephone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtDepartmentCode = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtTelephone1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jbSave = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jLabel1.text")); // NOI18N

        jtDepartmentType.setBackground(new java.awt.Color(0, 204, 0));
        jtDepartmentType.setText(org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jtDepartmentType.text")); // NOI18N

        jtDepartmentName.setText(org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jtDepartmentName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jLabel4.text")); // NOI18N

        jtParentDeparent.setBackground(new java.awt.Color(0, 204, 0));
        jtParentDeparent.setText(org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jtParentDeparent.text")); // NOI18N
        jtParentDeparent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtParentDeparentActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jLabel5.text")); // NOI18N

        jtTelephone.setText(org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jtTelephone.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jLabel6.text")); // NOI18N

        jtDepartmentCode.setText(org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jtDepartmentCode.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jLabel7.text")); // NOI18N

        jtTelephone1.setText(org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jtTelephone1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jLabel2.text")); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtDepartmentName, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(jtDepartmentType)
                            .addComponent(jtParentDeparent)
                            .addComponent(jtTelephone)
                            .addComponent(jtDepartmentCode)
                            .addComponent(jtTelephone1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtDepartmentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtParentDeparent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtDepartmentCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtTelephone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(PDRDeparmentEditor.class, "PDRDeparmentEditor.jbSave.text")); // NOI18N
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
        
        if(department == null){ //Add New Account
            String insertSQL = "INSERT INTO [dbo].[OrganizationUnits]\n" +
"           ([OrganizationUnitCode]\n" +
"           ,[OrganizationUnitName]\n" +
"           ,[Address]\n" +
"           ,[Telephone]\n" +
"           ,[EMail]\n" +
"           ,[Fax]\n" +
"           ,[PINNo]\n" +
"           ,[NHIFNo]\n" +
"           ,[NSSFNo]\n" +
"           ,[LASCNo]\n" +
"           ,[MinEmployeePercentage]\n" +
"           ,[Comments]\n" +
"           ,[OrganizationUnitFunction]\n" +
"           ,[OrganizationUnitTypeID]\n" +
"           ,[ParentOrganizationUnitID])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, departmentCode);
            query.setParameter(2, departmentName);
            query.setParameter(4, telephone);
            query.setParameter(5, email);
            query.setParameter(12, departmentFunction);
            query.setParameter(14, parentDepartment.getOrganizationUnitID());
            
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            String sqlString ="SELECT r from OrganizationUnits r";
            UtilityPDR.loadOUs(sqlString);
            
            resetEditor();
            StatusDisplayer.getDefault().setStatusText("Department Added");
        }else{
            
            OrganizationUnits eba = entityManager.find(OrganizationUnits.class, department.getOrganizationUnitID());
            
            if(null != departmentCode){
                eba.setOrganizationUnitCode(departmentCode);
            }
            
            if(null != departmentName){
                eba.setOrganizationUnitName(departmentName);
            }
            
            if(null != departmentFunction){
                eba.setOrganizationUnitFunction(departmentFunction);
            }
            
            if(null != parentDepartment){
                eba.setParentOrganizationUnitID(parentDepartment);
            }
            
            
            
            entityManager.getTransaction().commit();
            String sqlString ="SELECT r from OrganizationUnits r";
            UtilityPDR.loadOUs(sqlString);
            StatusDisplayer.getDefault().setStatusText("OU Saved");
            resetEditor();
        }
        

    }//GEN-LAST:event_jbSaveActionPerformed

    private void jtParentDeparentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtParentDeparentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtParentDeparentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jbSave;
    private javax.swing.JTextField jtDepartmentCode;
    private javax.swing.JTextField jtDepartmentName;
    private javax.swing.JTextField jtDepartmentType;
    private javax.swing.JTextField jtParentDeparent;
    private javax.swing.JTextField jtTelephone;
    private javax.swing.JTextField jtTelephone1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result result = (Lookup.Result)le.getSource();
        for (Object e: result.allInstances()){
            
                
                
               if(e instanceof OrganizationUnits){
                parentDepartment = ((OrganizationUnits) e);
                jtParentDeparent.setText(parentDepartment.getOrganizationUnitName());
                modify();
            } 
               
               if(e instanceof OrganizationUnitTypes){
                
                jtDepartmentType.setText(((OrganizationUnitTypes) e).getOrganizationUnitTypeName());
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
        jtDepartmentName.setText("");
        jtDepartmentType.setText("");
        jtTelephone.setText("");
        jtParentDeparent.setText("");
        jtDepartmentCode.setText("");
        
        
        parentDepartment = null;
        departmentCode = null;
        departmentName = null;
        departmentFunction = null;
        
    }
    
    


}
