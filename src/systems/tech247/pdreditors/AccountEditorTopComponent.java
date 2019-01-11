/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Arrays;
import javax.persistence.Query;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import static systems.tech247.dbaccess.DataAccess.entityManager;
import systems.tech247.hr.BankBranches;
import systems.tech247.hr.EmployeeBankAccounts;
import systems.tech247.hr.Employees;
import systems.tech247.pdr.UtilityPDR;
import systems.tech247.pdr.NodeRefreshBankAccountEvent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//AccountEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "AccountEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdreditors.AccountEditorTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_AccountEditorAction",
//        preferredID = "AccountEditorTopComponent"
//)
@Messages({
    "CTL_AccountEditorAction=Account Editor",
    "CTL_AccountEditorTopComponent=Account Editor",
    "HINT_AccountEditorTopComponent="
})
public final class AccountEditorTopComponent extends TopComponent implements LookupListener {
    
    TopComponent tc = WindowManager.getDefault().findTopComponent("BanksTopComponent");
    
    
    //Lookup for the Contact Type
    Lookup.Result<BankBranches> rslt = WindowManager.getDefault().findTopComponent("BanksTopComponent").getLookup().lookupResult(BankBranches.class);
    
    
    
    EmployeeBankAccounts application;
    Employees emp;
    InstanceContent ic = new InstanceContent();
    
    //The updateable
    EmployeeBankAccounts updateable;
    
    //Contact Details
    long branchID =0;
    
    

    //Account Number
    String accountNumber = "";
    
    //Account Name
    String accountName = "";
    
    //swiftCode
    String swiftCode = "";
    
    //Boolean Is Main
    Boolean isMain=false;
    
    public AccountEditorTopComponent(){
        this(null);
    }
    
    public AccountEditorTopComponent(Employees employee){
        this(null,employee);
        
    }
    
    
    public AccountEditorTopComponent(EmployeeBankAccounts contact,Employees emp) {
        initComponents();
        setName(Bundle.CTL_AccountEditorTopComponent());
        setToolTipText(Bundle.HINT_AccountEditorTopComponent());
        associateLookup(new AbstractLookup(ic));
        this.emp = emp;
        this.application = contact;
        if(null!=application){
            this.updateable = DataAccess.getEntityManager().find(EmployeeBankAccounts.class, contact.getEmployeeBankAccountID());
            
        }
        fillTheFields();
        
        jtSwiftCode.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                swiftCode = jtSwiftCode.getText();
                try{
                    updateable.setSwiftCode(swiftCode);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                swiftCode = jtSwiftCode.getText();
                try{
                    updateable.setSwiftCode(swiftCode);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                swiftCode = jtSwiftCode.getText();
                try{
                    updateable.setSwiftCode(swiftCode);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        
        jtAccountNumber.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                accountNumber = jtAccountNumber.getText();
                try{
                    updateable.setAccountNumber(accountNumber);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                accountNumber = jtAccountNumber.getText();
                try{
                    updateable.setAccountNumber(accountNumber);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                accountNumber = jtAccountNumber.getText();
                try{
                    updateable.setAccountNumber(accountNumber);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        
        
        
        
        
        
        

        
        
        
        jtAccountName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                accountName = jtAccountName.getText();
                try{
                    updateable.setAccountName(accountName);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                accountName = jtAccountName.getText();
                try{
                    updateable.setAccountName(accountName);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                accountName = jtAccountName.getText();
                try{
                    updateable.setAccountName(accountName);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jtBranch.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(tc, "Search Banks,Select A Branch",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
       
            
        
            
    }
    
    void fillTheFields(){
        if(updateable!=null){
            try{
                //Fill Fields
                jtAccountName.setText(updateable.getAccountName());
                jtAccountNumber.setText(updateable.getAccountNumber());
                jtSwiftCode.setText(updateable.getSwiftCode());
                jcbIsMain.setSelected(updateable.getIsMainAccount());
                jtBranch.setText(updateable.getBankBranchID().getBankID().getBankName()+"-"+updateable.getBankBranchID().getBranchName());
                
                
            }catch(NullPointerException ex){
                
            }
        }
        
       
       

    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result<BankBranches> rslt = (Lookup.Result<BankBranches>)le.getSource();
        for(BankBranches l : rslt.allInstances()){
            jtBranch.setText(l.getBankID().getBankName()+"-"+l.getBranchName());
            branchID= l.getBankBranchID();
            
            try{
                updateable.setBankBranchID(l);
            }catch(NullPointerException ex){
                
            }
            
            modify();
        }
    }
    
    
    
    private class ContactAppSavable extends AbstractSavable{
        
        ContactAppSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            return "Bank Account";
        }
        
        AccountEditorTopComponent tc(){
            return AccountEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==application){
               
                
                
                        String insertSQL = "INSERT INTO [dbo].[EmployeeBankAccounts]\n" +
"           ([EmployeeID]\n" +
"           ,[BankBranchID]\n" +
"           ,[AccountName]\n" +
"           ,[AccountNumber]\n" +
"           ,[AccountType]\n" +
"           ,[IsMainAccount]\n" +
"           ,[Deleted]\n" +
"           ,[SwiftCode])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, emp.getEmployeeID());
            query.setParameter(2, branchID);
            query.setParameter(3, accountName);
            query.setParameter(4, accountNumber);
            query.setParameter(6,isMain );
            query.setParameter(7, 0);
            query.setParameter(8, swiftCode);
            
            
            

            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Crea
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            UtilityPDR.pdrIC.set(Arrays.asList(new NodeRefreshBankAccountEvent()), null);
            
            
            
            
            this.tc().close();
            
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof ContactAppSavable){
                ContactAppSavable e = (ContactAppSavable)o;
                return tc() == e.tc();
            }
            return false;        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
    
    public void modify(){
        if(updateable==null){ //new entry
            if(branchID==0){ //Bank Branch was not selected
                StatusDisplayer.getDefault().setStatusText("Choose A Bank Branch");
            }else if(accountName.length()<4){
                StatusDisplayer.getDefault().setStatusText("Account Name does not look right");
            }else if(accountNumber.length()<4){
                StatusDisplayer.getDefault().setStatusText("Account Number does not look right");
            }else{
                if(getLookup().lookup(ContactAppSavable.class)==null){
                            ic.add(new ContactAppSavable());
                }
            }
                    
                  
                    
            
        }else{
            if(updateable.getAccountName().length()<4){
                StatusDisplayer.getDefault().setStatusText("Account Name does not look right");
            }else if(updateable.getAccountNumber().length()<4){
                StatusDisplayer.getDefault().setStatusText("Account Number does not look right");
            }else{
                if(getLookup().lookup(ContactAppSavable.class)==null){
                            ic.add(new ContactAppSavable());
                }
            }
        }
            
        
        
            
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jtBranch = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtAccountName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtAccountNumber = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtSwiftCode = new javax.swing.JTextField();
        jcbIsMain = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(AccountEditorTopComponent.class, "AccountEditorTopComponent.jLabel2.text")); // NOI18N

        jtBranch.setText(org.openide.util.NbBundle.getMessage(AccountEditorTopComponent.class, "AccountEditorTopComponent.jtBranch.text")); // NOI18N
        jtBranch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtBranchMouseClicked(evt);
            }
        });
        jtBranch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtBranchActionPerformed(evt);
            }
        });
        jtBranch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtBranchKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(AccountEditorTopComponent.class, "AccountEditorTopComponent.jLabel7.text")); // NOI18N

        jtAccountName.setText(org.openide.util.NbBundle.getMessage(AccountEditorTopComponent.class, "AccountEditorTopComponent.jtAccountName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(AccountEditorTopComponent.class, "AccountEditorTopComponent.jLabel8.text")); // NOI18N

        jtAccountNumber.setText(org.openide.util.NbBundle.getMessage(AccountEditorTopComponent.class, "AccountEditorTopComponent.jtAccountNumber.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(AccountEditorTopComponent.class, "AccountEditorTopComponent.jLabel9.text")); // NOI18N

        jtSwiftCode.setText(org.openide.util.NbBundle.getMessage(AccountEditorTopComponent.class, "AccountEditorTopComponent.jtSwiftCode.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbIsMain, org.openide.util.NbBundle.getMessage(AccountEditorTopComponent.class, "AccountEditorTopComponent.jcbIsMain.text")); // NOI18N
        jcbIsMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbIsMainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbIsMain)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jtAccountName, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                        .addComponent(jtAccountNumber)
                        .addComponent(jtSwiftCode)
                        .addComponent(jtBranch)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtAccountName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jtAccountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtSwiftCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbIsMain)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtBranchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtBranchKeyPressed
        Object[] options = {new JButton("Close")};
        DialogDisplayer.getDefault().notify(new DialogDescriptor(tc, "Search For A Bank, Select A Branch",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
    }//GEN-LAST:event_jtBranchKeyPressed

    private void jtBranchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtBranchActionPerformed
        
        
    }//GEN-LAST:event_jtBranchActionPerformed

    private void jcbIsMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbIsMainActionPerformed
        isMain = jcbIsMain.isSelected();
        try{
            updateable.setIsMainAccount(true);
        }catch(NullPointerException ex){
            
        }
        modify();
    }//GEN-LAST:event_jcbIsMainActionPerformed

    private void jtBranchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtBranchMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtBranchMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JCheckBox jcbIsMain;
    private javax.swing.JTextField jtAccountName;
    private javax.swing.JTextField jtAccountNumber;
    private javax.swing.JTextField jtBranch;
    private javax.swing.JTextField jtSwiftCode;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        rslt.addLookupListener(this);
        resultChanged(new LookupEvent(rslt));
    }

    @Override
    public void componentClosed() {
        rslt.removeLookupListener(this);
        
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
