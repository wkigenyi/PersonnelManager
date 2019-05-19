/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.awt.ActionID;
import org.openide.awt.StatusDisplayer;
import org.openide.util.NbBundle;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.BankBranches;
import systems.tech247.hr.Banks;
import systems.tech247.api.NodeRefreshEvent;
import systems.tech247.pdr.UtilityPDR;


/**
 *
 * @author Admin
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//BankBranches//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "BankBranchEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdr.BankBranchEditorTopComponent")

@NbBundle.Messages({
    "CTL_BankBranchEditorAction=Bank Branches",
    "CTL_BankBranchEditorTopComponent=Bank Branches",
    "HINT_BankBranchEditorTopComponent="
})





public class BankBranchEditorTopComponent extends TopComponent{

    
    
    DataAccess da = new DataAccess();
    BankBranches updateable;
    Banks bankID;
    InstanceContent ic = new InstanceContent(); 
    
    
    
    
    //Updatables
  
    String bankString = null;
    String bankCode = null;
    String bankComments = null;
  
    EntityManager entityManager = DataAccess.getEntityManager();
    
   
    /**
     * Creates new form PersonalInfoPanel
     */
    
    public BankBranchEditorTopComponent(){
        
    }
    
    
    public BankBranchEditorTopComponent(Banks bank){
      
      this(null, bank);   
    }
    
    public BankBranchEditorTopComponent(BankBranches bank){
      this(bank, bank.getBankID());   
    }
    
    
 public BankBranchEditorTopComponent(BankBranches branch, Banks bankID) {
        initComponents();
        setName(Bundle.CTL_BankBranchEditorTopComponent());
        setToolTipText(Bundle.HINT_BankBranchEditorTopComponent());
        //Start transaction
        
        jbSave.setEnabled(false);
        jbSave.setVisible(false);
        
        this.bankID = bankID;
        
         jtBankName.setText(bankID.getBankName());
        
        if(branch!= null){
            updateable = entityManager.find(BankBranches.class, branch.getBankBranchID());

            
        }
        
        fillFields();
        
        
        
        
        jtBranchName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                bankString = jtBranchName.getText();
                try{
                    updateable.setBranchName(bankString);
                }catch(Exception ex){
                    
                }
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                bankString = jtBranchName.getText();
                try{
                    updateable.setBranchName(bankString);
                }catch(Exception ex){
                    
                }
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                bankString = jtBranchName.getText();
                try{
                    updateable.setBranchName(bankString);
                }catch(Exception ex){
                    
                }
                modify();
            }
        });
        
        jtBankCode.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                bankCode = jtBankCode.getText();
                try{
                    updateable.setBranchCode(bankCode);
                }catch(Exception ex){
                    
                }
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                bankCode = jtBankCode.getText();
                try{
                    updateable.setBranchCode(bankCode);
                }catch(Exception ex){
                    
                }
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                bankCode = jtBankCode.getText();
                try{
                    updateable.setBranchCode(bankCode);
                }catch(Exception ex){
                    
                }
                modify();
            }
        });
        
        jTBankComments.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                bankComments = jTBankComments.getText();
                try{
                    updateable.setComments(bankComments);
                }catch(Exception ex){
                    
                }
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                bankComments = jTBankComments.getText();
                try{
                    updateable.setComments(bankComments);
                }catch(Exception ex){
                    
                }
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                bankComments = jTBankComments.getText();
                try{
                    updateable.setComments(bankComments);
                }catch(Exception ex){
                    
                }
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtBankName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtBankCode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBankComments = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jtBranchName = new javax.swing.JTextField();
        jbSave = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(BankBranchEditorTopComponent.class, "BankBranchEditorTopComponent.jLabel1.text")); // NOI18N

        jtBankName.setEditable(false);
        jtBankName.setText(org.openide.util.NbBundle.getMessage(BankBranchEditorTopComponent.class, "BankBranchEditorTopComponent.jtBankName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(BankBranchEditorTopComponent.class, "BankBranchEditorTopComponent.jLabel2.text")); // NOI18N

        jtBankCode.setText(org.openide.util.NbBundle.getMessage(BankBranchEditorTopComponent.class, "BankBranchEditorTopComponent.jtBankCode.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(BankBranchEditorTopComponent.class, "BankBranchEditorTopComponent.jLabel3.text")); // NOI18N

        jTBankComments.setColumns(20);
        jTBankComments.setRows(5);
        jScrollPane1.setViewportView(jTBankComments);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(BankBranchEditorTopComponent.class, "BankBranchEditorTopComponent.jLabel4.text")); // NOI18N

        jtBranchName.setText(org.openide.util.NbBundle.getMessage(BankBranchEditorTopComponent.class, "BankBranchEditorTopComponent.jtBranchName.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jtBankName, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtBankCode, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1)
                    .addComponent(jtBranchName, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtBankName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtBranchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtBankCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addContainerGap())
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(BankBranchEditorTopComponent.class, "BankBranchEditorTopComponent.jbSave.text")); // NOI18N
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
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbSave)
                .addGap(8, 8, 8))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveActionPerformed
        entityManager.getTransaction().begin();    
        
        if(updateable == null){ //Add New Account
            String insertSQL = "INSERT INTO [dbo].[BankBranches]\n" +
"           ([BranchCode]\n" +
                    
"           ,[BranchName]\n" +
"           ,[Comments]\n" +
                    "           ,[BankID])\n" +
"     VALUES\n" +
"           (?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(2, bankString);
            query.setParameter(1, bankCode);
            query.setParameter(3, bankComments);
            query.setParameter(4, bankID.getBankID() );
            
            
            
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            StatusDisplayer.getDefault().setStatusText("Bank Saved");
            
            
            
        }else{
                 
            
            
            
            entityManager.getTransaction().commit();

        }
        

    }//GEN-LAST:event_jbSaveActionPerformed
    
    void fillFields(){
        try{
            jtBankName.setText(bankID.getBankName());
        }catch(NullPointerException ex){
            
        }
        if(null != updateable){
            try{
            jtBankCode.setText(updateable.getBranchCode());
            jTBankComments.setText(updateable.getComments());
            bankString = updateable.getBranchName();
            jtBranchName.setText(updateable.getBranchName());
            }catch(NullPointerException ex){
                
            }
        }
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTBankComments;
    private javax.swing.JButton jbSave;
    private javax.swing.JTextField jtBankCode;
    private javax.swing.JTextField jtBankName;
    private javax.swing.JTextField jtBranchName;
    // End of variables declaration//GEN-END:variables

    
    
    
    private class BranchSavable extends AbstractSavable{
        
        BranchSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            if(null==updateable){
                return "New Branch";
            }else{
                return "Branch";
            }
        }
        
        BankBranchEditorTopComponent tc(){
            return BankBranchEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Branch
            if(null==updateable){
               
                
                
                        String insertSQL = "INSERT INTO [dbo].[BankBranches]\n" +
"           ([BranchCode]\n" +
                    
"           ,[BranchName]\n" +
"           ,[Comments]\n" +
                    "           ,[BankID])\n" +
"     VALUES\n" +
"           (?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(2, bankString);
            query.setParameter(1, bankCode);
            query.setParameter(3, bankComments);
            query.setParameter(4, bankID.getBankID() );
            
            
            
            

            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Crea
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            UtilityPDR.pdrIC.set(Arrays.asList(new NodeRefreshEvent()), null);
            UtilityPDR.pdrIC.set(Arrays.asList(new String()), null);
            
            
            
            this.tc().close();
            
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof BranchSavable){
                BranchSavable e = (BranchSavable)o;
                return tc() == e.tc();
            }
            return false;        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
    
    public void modify(){
        
        //New entry
            if(updateable==null){
                if(bankString.length()<4){
                    StatusDisplayer.getDefault().setStatusText("Proper Branch Name is required");
                }else{    
                    if(getLookup().lookup(BranchSavable.class)==null){
                        ic.add(new BranchSavable());
                    }
                }
            }else{ 
                if(updateable.getBranchName().length()<4){
                    StatusDisplayer.getDefault().setStatusText("Proper Branch Name is required");
                }else {    
                
                if(getLookup().lookup(BranchSavable.class)==null){
                            ic.add(new BranchSavable());
                }
            }
            }
        
        
            
        
    }

    
    
    
    
    
    void resetEditor(){
        
        jtBankCode.setText("");
        jTBankComments.setText("");
        jtBranchName.setText("");
        bankString = null;
        bankCode = null;
        bankComments = null;
      
        
    }
    
    @Override
    public void componentOpened() {
       
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    
    
    


    
    
    
    
    


}
