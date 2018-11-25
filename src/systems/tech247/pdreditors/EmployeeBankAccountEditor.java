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
import systems.tech247.hr.BankBranches;
import systems.tech247.hr.EmployeeBankAccounts;
import systems.tech247.hr.Employees;
import systems.tech247.pdr.UtilityPDR;


/**
 *
 * @author Admin
 */
public class EmployeeBankAccountEditor extends javax.swing.JPanel implements LookupListener{

    
    
    DataAccess da = new DataAccess();
    EmployeeBankAccounts account;
    Employees employeeID;
    
    
    //Updatables
    String accountNo = null;
    String accountName = null;
    BankBranches branch = null;
    String accountType = null;
    String swiftCode = null;
    Boolean isMain = false;
    Boolean isDeleted  = false;
    EntityManager entityManager = da.getEntityManager();
    TopComponent empTc = WindowManager.getDefault().findTopComponent("EmployeesTopComponent");
    Lookup.Result<Employees> empRslt = empTc.getLookup().lookupResult(Employees.class);
    TopComponent bankTc = WindowManager.getDefault().findTopComponent("BanksTopComponent");
    Lookup.Result<BankBranches> bankRslt = bankTc.getLookup().lookupResult(BankBranches.class);
   
    /**
     * Creates new form PersonalInfoPanel
     */
    public EmployeeBankAccountEditor(EmployeeBankAccounts acc){
        this(acc.getEmployeeID(),acc);
    }
    
    public EmployeeBankAccountEditor(Employees emp){
        this(emp,null);
    }
    
    
 public EmployeeBankAccountEditor(Employees empID,EmployeeBankAccounts acc) {
        initComponents();
        //Start transaction
        account= acc;
        
        if(acc != null){
            jtBank.setText(acc.getBankBranchID().getBankID().getBankName()+" ("+account.getBankBranchID().getBranchName()+")");
            jtAccountName.setText(acc.getAccountName());
            jtAccountNumber.setText(acc.getAccountNumber());
            jtSwiftCode.setText(acc.getSwiftCode());
            jtAccountType.setText(acc.getAccountType());
        }
        
        
        bankRslt.addLookupListener(this);
        resultChanged(new LookupEvent(empRslt));
        
        employeeID = empID;
        jtBank.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(bankTc, "Select Bank Branch",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null)); }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
        jtAccountName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                accountName = jtAccountName.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                accountName = jtAccountName.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                accountName = jtAccountName.getText();
                modify();
            }
        });
        
        jtSwiftCode.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                swiftCode = jtSwiftCode.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                swiftCode = jtSwiftCode.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                swiftCode = jtSwiftCode.getText();
                modify();
            }
        });
        
        jtAccountType.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                accountType = jtAccountType.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                accountType = jtAccountType.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                accountType = jtAccountType.getText();
                modify();
            }
        });
        
        jtAccountNumber.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                accountNo = jtAccountNumber.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                accountNo = jtAccountNumber.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                accountNo = jtAccountNumber.getText();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jtAccountName = new javax.swing.JTextField();
        jtBank = new javax.swing.JTextField();
        jcbIsMain = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jtAccountNumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtSwiftCode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtAccountType = new javax.swing.JTextField();
        jcbIsDelete = new javax.swing.JCheckBox();
        jbSave = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(EmployeeBankAccountEditor.class, "EmployeeBankAccountEditor.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EmployeeBankAccountEditor.class, "EmployeeBankAccountEditor.jLabel1.text")); // NOI18N

        jtAccountName.setText(org.openide.util.NbBundle.getMessage(EmployeeBankAccountEditor.class, "EmployeeBankAccountEditor.jtAccountName.text")); // NOI18N

        jtBank.setBackground(new java.awt.Color(0, 204, 0));
        jtBank.setText(org.openide.util.NbBundle.getMessage(EmployeeBankAccountEditor.class, "EmployeeBankAccountEditor.jtBank.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbIsMain, org.openide.util.NbBundle.getMessage(EmployeeBankAccountEditor.class, "EmployeeBankAccountEditor.jcbIsMain.text")); // NOI18N
        jcbIsMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbIsMainActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(EmployeeBankAccountEditor.class, "EmployeeBankAccountEditor.jLabel4.text")); // NOI18N

        jtAccountNumber.setText(org.openide.util.NbBundle.getMessage(EmployeeBankAccountEditor.class, "EmployeeBankAccountEditor.jtAccountNumber.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(EmployeeBankAccountEditor.class, "EmployeeBankAccountEditor.jLabel5.text")); // NOI18N

        jtSwiftCode.setText(org.openide.util.NbBundle.getMessage(EmployeeBankAccountEditor.class, "EmployeeBankAccountEditor.jtSwiftCode.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(EmployeeBankAccountEditor.class, "EmployeeBankAccountEditor.jLabel6.text")); // NOI18N

        jtAccountType.setText(org.openide.util.NbBundle.getMessage(EmployeeBankAccountEditor.class, "EmployeeBankAccountEditor.jtAccountType.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbIsDelete, org.openide.util.NbBundle.getMessage(EmployeeBankAccountEditor.class, "EmployeeBankAccountEditor.jcbIsDelete.text")); // NOI18N
        jcbIsDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbIsDeleteActionPerformed(evt);
            }
        });

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
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbIsMain)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jtBank, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                        .addComponent(jtAccountName)
                        .addComponent(jtAccountNumber)
                        .addComponent(jtSwiftCode)
                        .addComponent(jtAccountType))
                    .addComponent(jcbIsDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtBank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtAccountName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtAccountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtSwiftCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbIsMain)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbIsDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(EmployeeBankAccountEditor.class, "EmployeeBankAccountEditor.jbSave.text")); // NOI18N
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
        
        if(account == null){ //Add New Account
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
            query.setParameter(1, employeeID.getEmployeeID());
            query.setParameter(2, branch.getBankBranchID());
            query.setParameter(3, accountName);
            query.setParameter(4, accountNo);
            query.setParameter(5, accountType);
            query.setParameter(6, true);
            query.setParameter(7, false);
            query.setParameter(8, swiftCode);
            
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            StatusDisplayer.getDefault().setStatusText("Account Added");
            UtilityPDR.loadAccounts(employeeID);
            
        }else{
            
            EmployeeBankAccounts eba = entityManager.find(EmployeeBankAccounts.class, account.getEmployeeBankAccountID());
            
            if(null != swiftCode){
                eba.setSwiftCode(swiftCode);
            }
            
            if(null != accountName){
                eba.setAccountName(accountName);
            }
            
            if(null != accountNo){
                eba.setAccountNumber(accountNo);
            }
            
            if(null != accountType){
                eba.setAccountType(accountType);
            }
            
            eba.setIsMainAccount(isMain);
            eba.setDeleted(isDeleted);
            
            entityManager.getTransaction().commit();
            StatusDisplayer.getDefault().setStatusText("Account Saved");
            UtilityPDR.loadAccounts(employeeID);
        }
        

    }//GEN-LAST:event_jbSaveActionPerformed

    private void jcbIsMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbIsMainActionPerformed
        isMain = jcbIsMain.isSelected();
    }//GEN-LAST:event_jcbIsMainActionPerformed

    private void jcbIsDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbIsDeleteActionPerformed
        isDeleted = jcbIsDelete.isSelected();
    }//GEN-LAST:event_jcbIsDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbSave;
    private javax.swing.JCheckBox jcbIsDelete;
    private javax.swing.JCheckBox jcbIsMain;
    private javax.swing.JTextField jtAccountName;
    private javax.swing.JTextField jtAccountNumber;
    private javax.swing.JTextField jtAccountType;
    private javax.swing.JTextField jtBank;
    private javax.swing.JTextField jtSwiftCode;
    // End of variables declaration//GEN-END:variables

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result result = (Lookup.Result)le.getSource();
        for (Object e: result.allInstances()){
            
                
                
               if(e instanceof BankBranches){
                branch = ((BankBranches) e);
                jtBank.setText(branch.getBankID().getBankName()+" ("+branch.getBranchName()+")");
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
        jtBank.setText("");
        jtAccountName.setText("");
        jtSwiftCode.setText("");
        jtAccountNumber.setText("");
        jtAccountType.setText("");
        jcbIsMain.setSelected(false);
        
        branch = null;
        accountName = null;
        accountType = null;
        accountNo = null;
        isMain =false;
        isDeleted = false;
    }
    
    


}
