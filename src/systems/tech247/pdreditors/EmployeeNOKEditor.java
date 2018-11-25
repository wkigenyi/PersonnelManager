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
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Employees;
import systems.tech247.hr.Kin;
import systems.tech247.pdr.UtilityPDR;


/**
 *
 * @author Admin
 */
public class EmployeeNOKEditor extends javax.swing.JPanel{

    
    
    DataAccess da = new DataAccess();
    Kin contact;
    Employees employeeID;
    
    
    //Updatables
    String fName = null;
    String lName = null;
    String email = null;
    String mobile = null;
    String comments = null;
    String address = null;
    String homephone = null;
    EntityManager entityManager = DataAccess.getEntityManager();
   
    
    
   
    /**
     * Creates new form PersonalInfoPanel
     */
    public EmployeeNOKEditor(Kin contact){
        this(contact.getEmployeeId(),contact);
    }
    
    public EmployeeNOKEditor(Employees emp){
        this(emp,null);
    }
    
    
 public EmployeeNOKEditor(Employees empID,Kin acc) {
        initComponents();
        //Start transaction
        contact = acc;
        
        if(acc != null){
            jtfname.setText(contact.getOtherNames());
            jtLastname.setText(contact.getSurName());
            jtMobile.setText(contact.getMNo());
            jthometelephone.setText(contact.getHTelNo());
            jtAddress.setText(contact.getAddress());
            jtEmail.setText(contact.getEMail());
            jtComment.setText(contact.getComments());
          
        }
        
        
       
        
        employeeID = empID;
        jtfname.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                fName = jtfname.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                fName = jtfname.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                fName = jtfname.getText();
                modify();
            }
        });
        
        jtLastname.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                lName = jtLastname.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                lName = jtLastname.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                lName = jtLastname.getText();
                modify();
            }
        });
        
        jtMobile.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                mobile = jtMobile.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                mobile = jtMobile.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                mobile = jtMobile.getText();
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
        
        jtComment.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                comments = jtComment.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                comments = jtComment.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                comments = jtComment.getText();
                modify();
            }
        });
        
        jtAddress.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                address = jtAddress.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                address = jtAddress.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                address = jtAddress.getText();
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
        jtMobile = new javax.swing.JTextField();
        jtfname = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtAddress = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtComment = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jtLastname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jthometelephone = new javax.swing.JTextField();
        jbSave = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(EmployeeNOKEditor.class, "EmployeeNOKEditor.jLabel3.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EmployeeNOKEditor.class, "EmployeeNOKEditor.jLabel1.text_1")); // NOI18N

        jtMobile.setText(org.openide.util.NbBundle.getMessage(EmployeeNOKEditor.class, "EmployeeNOKEditor.jtMobile.text_1")); // NOI18N

        jtfname.setText(org.openide.util.NbBundle.getMessage(EmployeeNOKEditor.class, "EmployeeNOKEditor.jtfname.text")); // NOI18N

        jtAddress.setColumns(20);
        jtAddress.setRows(5);
        jScrollPane1.setViewportView(jtAddress);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(EmployeeNOKEditor.class, "EmployeeNOKEditor.jLabel2.text_1")); // NOI18N

        jtEmail.setText(org.openide.util.NbBundle.getMessage(EmployeeNOKEditor.class, "EmployeeNOKEditor.jtEmail.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(EmployeeNOKEditor.class, "EmployeeNOKEditor.jLabel4.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(EmployeeNOKEditor.class, "EmployeeNOKEditor.jLabel5.text_1")); // NOI18N

        jtComment.setColumns(20);
        jtComment.setRows(5);
        jScrollPane2.setViewportView(jtComment);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(EmployeeNOKEditor.class, "EmployeeNOKEditor.jLabel6.text")); // NOI18N

        jtLastname.setText(org.openide.util.NbBundle.getMessage(EmployeeNOKEditor.class, "EmployeeNOKEditor.jtLastname.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(EmployeeNOKEditor.class, "EmployeeNOKEditor.jLabel7.text")); // NOI18N

        jthometelephone.setText(org.openide.util.NbBundle.getMessage(EmployeeNOKEditor.class, "EmployeeNOKEditor.jthometelephone.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(jtMobile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(jtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(jtLastname, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jthometelephone, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jtEmail, jtMobile, jtfname});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jthometelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(EmployeeNOKEditor.class, "EmployeeNOKEditor.jbSave.text_1")); // NOI18N
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbSave, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbSave)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveActionPerformed
        entityManager.getTransaction().begin();    
        
        if(contact == null){ //Add New Referee
            String insertSQL = "INSERT INTO [dbo].[Kin]\n" +
"           ([employee_id]\n" +
"           ,[OtherNames]\n" +
"           ,[MNo]\n" +
"           ,[Address]\n" +
"           ,[Comments]\n" +                    
"           ,[Email]\n" +
"           ,[Surname]\n" +
"           ,[HTelNo])\n" +                    
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, employeeID.getEmployeeID());
            query.setParameter(2, fName);
            query.setParameter(3, mobile);
            query.setParameter(4, address);
            query.setParameter(5, comments);
            query.setParameter(6, email);
            query.setParameter(7, lName);
            query.setParameter(8, homephone);
                       
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            StatusDisplayer.getDefault().setStatusText("Next Of Kin Added");
            
            UtilityPDR.loadNOK(employeeID);
            
        }else{
            
            Kin eba = entityManager.find(Kin.class, contact.getId());
            
            
            
            if(null != fName){
                eba.setOtherNames(fName);
            }
            
            if(null != lName){
                eba.setSurName(lName);
            }
            
            if(null != email){
                eba.setEMail(email);
            }
            
            if(null != mobile){
                eba.setMNo(mobile);
            }
            
            if(null != address){
                eba.setAddress(address);
            }
            
            if(null != homephone){
                eba.setHTelNo(homephone);
            }
            

            
            entityManager.getTransaction().commit();
            
            StatusDisplayer.getDefault().setStatusText("Next Of Kin Saved");
            
            UtilityPDR.loadNOK(employeeID);
            resetEditor();
        }
        

    }//GEN-LAST:event_jbSaveActionPerformed


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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbSave;
    private javax.swing.JTextArea jtAddress;
    private javax.swing.JTextArea jtComment;
    private javax.swing.JTextField jtEmail;
    private javax.swing.JTextField jtLastname;
    private javax.swing.JTextField jtMobile;
    private javax.swing.JTextField jtfname;
    private javax.swing.JTextField jthometelephone;
    // End of variables declaration//GEN-END:variables


    
    
    void modify(){
        
                jbSave.setEnabled(true);
            
        
    }

    
    public Lookup getLookup() {
        TopComponent tc = WindowManager.getDefault().findTopComponent("EmployeeEditorTopComponent");
        return tc.getLookup();
    }
    
    
    
    void resetEditor(){
        jtfname.setText("");
        jtMobile.setText("");
        jtAddress.setText("");
        jtEmail.setText("");
        jtComment.setText("");
        jtLastname.setText("");
        jthometelephone.setText("");
        mobile = null;
        comments = null;
        lName = null;
        fName = null;
        address = null;
        email = null;
        homephone = null;
    }
    
    


}
