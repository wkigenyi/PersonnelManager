/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.io.IOException;
import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.awt.ActionID;
import org.openide.awt.StatusDisplayer;
import org.openide.util.NbBundle;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.dbaccess.DataAccess;
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
        preferredID = "BankEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdr.BankEditorTopComponent")

@NbBundle.Messages({
    "CTL_BankEditorAction=Bank Branches",
    "CTL_BankEditorTopComponent=Bank Branches",
    "HINT_BankEditorTopComponent="
})





public class BankEditorTopComponent extends TopComponent{

    
    
    DataAccess da = new DataAccess();
    Banks updateable;
    Banks bank;
    InstanceContent ic = new InstanceContent(); 
    
    
    
    
    //Updatables
  
    String bankString = null;
    String bankCode = null;
    String bankComments = null;
  
    EntityManager entityManager = DataAccess.getEntityManager();
    
   
    /**
     * Creates new form PersonalInfoPanel
     */
    
    public BankEditorTopComponent(){
        this(null);
    }
    
    

    
    
    
    
 public BankEditorTopComponent(Banks bank) {
        initComponents();
        setName(Bundle.CTL_BankEditorTopComponent());
        setToolTipText(Bundle.HINT_BankEditorTopComponent());
        //Start transaction
        
        jbSave.setEnabled(false);
        jbSave.setVisible(false);
        
        this.bank = bank;
        
         //jtBankName.setText(bankID.getBankName());
        
        if(bank!= null){
            updateable = entityManager.find(Banks.class, bank.getBankID());

            
        }
        
        fillFields();
        
        
        
        
        
        
        jtBankCode.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                bankCode = jtBankCode.getText();
                try{
                    updateable.setBankCode(bankCode);
                }catch(Exception ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                bankCode = jtBankCode.getText();
                try{
                    updateable.setBankCode(bankCode);
                }catch(Exception ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                bankCode = jtBankCode.getText();
                try{
                    updateable.setBankCode(bankCode);
                }catch(Exception ex){
                    
                }
                modify();
            }
        });
        
        jTBankComments.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                bankComments = jTBankComments.getText();
                try{
                    updateable.setComments(bankComments);
                }catch(Exception ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                bankComments = jTBankComments.getText();
                try{
                    updateable.setComments(bankComments);
                }catch(Exception ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                bankComments = jTBankComments.getText();
                try{
                    updateable.setComments(bankComments);
                }catch(Exception ex){
                    
                }
                modify();
            }
        });
        
        jtBankName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                bankString = jtBankName.getText();
                try{
                    updateable.setBankName(bankString);
                }catch(Exception ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                 bankString = jtBankName.getText();
                try{
                    updateable.setBankName(bankString);
                }catch(Exception ex){
                    
                }
                modify(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                 bankString = jtBankName.getText();
                try{
                    updateable.setBankName(bankString);
                }catch(Exception ex){
                    
                }
                modify(); //To change body of generated methods, choose Tools | Templates.
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
        jbSave = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(BankEditorTopComponent.class, "BankEditorTopComponent.jLabel1.text")); // NOI18N

        jtBankName.setText(org.openide.util.NbBundle.getMessage(BankEditorTopComponent.class, "BankEditorTopComponent.jtBankName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(BankEditorTopComponent.class, "BankEditorTopComponent.jLabel2.text")); // NOI18N

        jtBankCode.setText(org.openide.util.NbBundle.getMessage(BankEditorTopComponent.class, "BankEditorTopComponent.jtBankCode.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(BankEditorTopComponent.class, "BankEditorTopComponent.jLabel3.text")); // NOI18N

        jTBankComments.setColumns(20);
        jTBankComments.setRows(5);
        jScrollPane1.setViewportView(jTBankComments);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jtBankName, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtBankCode, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1))
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
                    .addComponent(jLabel2)
                    .addComponent(jtBankCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                .addContainerGap())
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(BankEditorTopComponent.class, "BankEditorTopComponent.jbSave.text")); // NOI18N
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
            String insertSQL = "INSERT INTO [dbo].[Banks]\n" +
"           ([BankCode]\n" +
"           ,[BankName]\n" +
"           ,[Comments])\n" +
"     VALUES\n" +
"           (?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, bankCode);
            query.setParameter(2, bankString);
            query.setParameter(3, bankComments);
            
            
            
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            StatusDisplayer.getDefault().setStatusText("Bank Saved");
            
            
            
        }else{
                 
            
            
            
            entityManager.getTransaction().commit();

        }
        

    }//GEN-LAST:event_jbSaveActionPerformed
    
    void fillFields(){
        
        if(null != updateable){
            try{
            jtBankName.setText(updateable.getBankName());    
            jtBankCode.setText(updateable.getBankCode());
            jTBankComments.setText(updateable.getComments());
            bankString = updateable.getBankName();
            
            }catch(NullPointerException ex){
                
            }
        }
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTBankComments;
    private javax.swing.JButton jbSave;
    private javax.swing.JTextField jtBankCode;
    private javax.swing.JTextField jtBankName;
    // End of variables declaration//GEN-END:variables

    
    
    
    private class BankSavable extends AbstractSavable{
        
        BankSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            if(null==updateable){
                return "New Bank";
            }else{
                return "Bank";
            }
        }
        
        BankEditorTopComponent tc(){
            return BankEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Branch
            if(null==updateable){
               
                
                
                       String insertSQL = "INSERT INTO [dbo].[Banks]\n" +
"           ([BankCode]\n" +
"           ,[BankName]\n" +
"           ,[Comments])\n" +
"     VALUES\n" +
"           (?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, bankCode);
            query.setParameter(2, bankString);
            query.setParameter(3, bankComments);
            
            
            
            

            
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
            resetEditor();
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof BankSavable){
                BankSavable e = (BankSavable)o;
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
                    StatusDisplayer.getDefault().setStatusText("Proper Bank Name is required");
                }else{    
                    if(getLookup().lookup(BankSavable.class)==null){
                        ic.add(new BankSavable());
                    }
                }
            }else{ 
                if(updateable.getBankName().length()<4){
                    StatusDisplayer.getDefault().setStatusText("Proper Bank Name is required");
                }else {    
                
                if(getLookup().lookup(BankSavable.class)==null){
                            ic.add(new BankSavable());
                }
            }
            }
        
        
            
        
    }

    
    
    
    
    
    void resetEditor(){
        
        jtBankCode.setText("");
        jTBankComments.setText("");
        jtBankName.setText("");
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
