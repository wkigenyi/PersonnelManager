/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.CompanyAssets;
import systems.tech247.hr.Employees;

import systems.tech247.hr.Nationalities;
import systems.tech247.hr.Religions;
import systems.tech247.hr.Tribes;
import systems.tech247.pdr.UtilityPDR;
import systems.tech247.util.Gender;
import systems.tech247.util.Marital;

/**
 *
 * @author Admin
 */
public class PDRCompanyAssetEditor extends javax.swing.JPanel{

    CompanyAssets emp;
    DataAccess da = new DataAccess();
    Boolean edit =false;
    
    //Updatables
    String assetName = null;
    String assetCode = null;
    String  comments = null;
    Date manufDate = null;
    Date purchaseDate =null;
    String model =null;
    String make = null;
    String serialNo = null;
    
    
    EntityManager entityManager = da.getEntityManager();
    TopComponent empTc = WindowManager.getDefault().findTopComponent("EmployeesTopComponent");
    Lookup.Result<Employees> empRslt = empTc.getLookup().lookupResult(Employees.class);
    TopComponent nationTc = WindowManager.getDefault().findTopComponent("NationsTopComponent");
    Lookup.Result<Nationalities> nationRslt = nationTc.getLookup().lookupResult(Nationalities.class);
    TopComponent tribeTc = WindowManager.getDefault().findTopComponent("TribesTopComponent");
    Lookup.Result<Tribes> tribeRslt = tribeTc.getLookup().lookupResult(Tribes.class);
    TopComponent genderTc = WindowManager.getDefault().findTopComponent("GenderTopComponent");
    Lookup.Result<Gender> genderRslt = genderTc.getLookup().lookupResult(Gender.class);
    TopComponent maritalTc = WindowManager.getDefault().findTopComponent("MaritalTopComponent");
    Lookup.Result<Marital> maritalRslt = maritalTc.getLookup().lookupResult(Marital.class);
    TopComponent religionTc = WindowManager.getDefault().findTopComponent("ReligionsTopComponent");
    Lookup.Result<Religions> religionRslt = religionTc.getLookup().lookupResult(Religions.class);
    /**
     * Creates new form PersonalInfoPanel
     */
    public PDRCompanyAssetEditor(){
        this(null);
    }
    
    
    public PDRCompanyAssetEditor(CompanyAssets asset) {
        initComponents();
        //Update the editor screen
        if(null != asset){
        emp = asset;
        
        jtAssetName.setText(emp.getAssetName());
        jtAssetCode.setText(emp.getAssetCode());
        jtaComments.setText(emp.getComments());
        jtAssetMake.setText(emp.getMake());
        jtModel.setText(emp.getModel());
        jdcDOP.setDate(emp.getStartDate());
        jtSerialNo.setText(emp.getRegistrationNumber());
        
        
        }
        jtAssetMake.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                make = jtAssetMake.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                make = jtAssetMake.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                make = jtAssetMake.getText();
            }
        });
        
        jtSerialNo.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                serialNo = jtSerialNo.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                serialNo = jtSerialNo.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                serialNo = jtSerialNo.getText();
            }
        });
        
        
        jtAssetName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                assetName = jtAssetName.getText();
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                assetName = jtAssetName.getText();
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                assetName = jtAssetName.getText();
                
            }
        });
        
        jtAssetCode.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                assetCode = jtAssetCode.getText();
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                assetCode = jtAssetCode.getText();
                
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                assetCode = jtAssetCode.getText();
                
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
        jLabel1 = new javax.swing.JLabel();
        jtAssetName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtAssetCode = new javax.swing.JTextField();
        jtModel = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtAssetMake = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtSerialNo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaComments = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jdcDOP = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jLabel1.text")); // NOI18N

        jtAssetName.setText(org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jtAssetName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jLabel2.text")); // NOI18N

        jtAssetCode.setText(org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jtAssetCode.text")); // NOI18N

        jtModel.setText(org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jtModel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jLabel3.text")); // NOI18N

        jtAssetMake.setText(org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jtAssetMake.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jLabel5.text")); // NOI18N

        jtSerialNo.setText(org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jtSerialNo.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jLabel6.text")); // NOI18N

        jTextField6.setText(org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jTextField6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jLabel7.text")); // NOI18N

        jtaComments.setColumns(20);
        jtaComments.setRows(5);
        jScrollPane1.setViewportView(jtaComments);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jLabel8.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcDOP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jtSerialNo, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jtAssetMake, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jtModel)
                            .addComponent(jtAssetCode, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jtAssetName)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtAssetName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtAssetCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtAssetMake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtSerialNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jdcDOP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(PDRCompanyAssetEditor.class, "PDRCompanyAssetEditor.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //If it is an update
        if(emp != null ){

            entityManager.getTransaction().begin();
            CompanyAssets e = entityManager.find(CompanyAssets.class, emp.getId());
            if(null != assetName){
                e.setAssetName(assetName);
            }
            if(null != assetCode)
            {
                e.setAssetCode(assetCode);
            }

            if(make !=null){
                e.setMake(make);
            }

            if( model != null){
                e.setModel(model);
            }

            if(serialNo != null){
                e.setRegistrationNumber(serialNo);
            }
            if(manufDate != null){
                e.setStartDate(manufDate);
            }

            if(comments != null){
                e.setComments(comments);
            }

            entityManager.getTransaction().commit();
            String sqlString ="SELECT * from CompanyAssets r";
            UtilityPDR.loadCompanyAssets(sqlString);
        }else{
            //It is a new entry

            entityManager.getTransaction().begin();

            String insertSQL = "INSERT INTO [dbo].[CompanyAssets]\n" +
            "           ([AssetCode]\n" +
            "           ,[AssetName]\n" +
            "           ,[Comments]\n" +
            "           ,[Model]\n" +
            "           ,[Make]\n" +
            "           ,[RegistrationNumber]\n" +
            "           ,[StartDate])\n" +

            "     VALUES\n" +
            "           (?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, assetCode);
            query.setParameter(2, assetName);
            query.setParameter(3, comments);
            query.setParameter(4, model);
            query.setParameter(5, make);
            query.setParameter(6, serialNo);
            query.setParameter(7, purchaseDate);
            query.executeUpdate();
            entityManager.getTransaction().commit();
            
            String sqlString ="SELECT * from CompanyAssets r";
            UtilityPDR.loadCompanyAssets(sqlString);
            resetEditor();

        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField6;
    private com.toedter.calendar.JDateChooser jdcDOP;
    private javax.swing.JTextField jtAssetCode;
    private javax.swing.JTextField jtAssetMake;
    private javax.swing.JTextField jtAssetName;
    private javax.swing.JTextField jtModel;
    private javax.swing.JTextField jtSerialNo;
    private javax.swing.JTextArea jtaComments;
    // End of variables declaration//GEN-END:variables

    
    
    

    
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
            return "Employee";
        }

        @Override
        protected void handleSave() throws IOException {
            
            //If it is an update
            if(emp != null ){
            UtilityPDR.editorIC.remove(this);
            unregister();
            entityManager.getTransaction().begin();
            CompanyAssets e = entityManager.find(CompanyAssets.class, emp.getId());
            if(null != assetName){
            e.setAssetName(assetName);
            }
            if(null != assetCode)
            {
            e.setAssetCode(assetCode);
            }
            
            if(make !=null){
                e.setMake(make);
            }
            
            if( model != null){
                e.setModel(model);
            }
            
            if(serialNo != null){
            e.setRegistrationNumber(serialNo);
            }
            if(manufDate != null){
            e.setStartDate(manufDate);
            }
            
            
            if(comments != null){
            e.setComments(comments);
            }
            
            
            
            
            
            
            
            
            entityManager.getTransaction().commit();
            }else{
                //It is a new entry 
                UtilityPDR.editorIC.remove(this);
                unregister();
                entityManager.getTransaction().begin();
                
                
            String insertSQL = "INSERT INTO [dbo].[CompanyAssets]\n" +
"           ([AssetCode]\n" +
"           ,[AssetName]\n" +
"           ,[Comments]\n" +
"           ,[Model]\n" +
"           ,[Make]\n" +
"           ,[RegistrationNumber]\n" +
"           ,[StartDate])\n" +                    
                    
"     VALUES\n" +
"           (?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, assetCode);
            query.setParameter(2, assetName);
            query.setParameter(3, comments);
            query.setParameter(4, model);
            query.setParameter(5, make);
            query.setParameter(6, serialNo);
            query.setParameter(7, purchaseDate);
            query.executeUpdate();
            entityManager.getTransaction().commit();
            resetEditor();
            }
            
        }
        
        PDRCompanyAssetEditor pnel(){
            return PDRCompanyAssetEditor.this;
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
    
    void resetEditor(){
        jtAssetName.setText("");
        jtModel.setText("");
        jtAssetCode.setText("");
        jtaComments.setText("");
        jtAssetMake.setText("");
        jdcDOP.setDate(null);
        jtSerialNo.setText("");
        
      
        
        
        
        
        
        assetName = null;
        assetCode = null;
        comments = null;
        make = null;
        model =null;
        purchaseDate = null;
        
    }
}
