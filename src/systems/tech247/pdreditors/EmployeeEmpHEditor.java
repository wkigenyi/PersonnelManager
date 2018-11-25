/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Employees;
import systems.tech247.hr.Employment;
import systems.tech247.pdr.UtilityPDR;


/**
 *
 * @author Admin
 */
public class EmployeeEmpHEditor extends javax.swing.JPanel{

    
    
    DataAccess da = new DataAccess();
    Employment contact;
    Employees employeeID;
    
    
    //Updatables
    String employer = null;
    Date fro = null;
    Date to = null;
    String desig = null;
    String comments = null;
    String benefits = null;
    String homephone = null;
    EntityManager entityManager = da.getEntityManager();
   
    
    
   
    /**
     * Creates new form PersonalInfoPanel
     */
    public EmployeeEmpHEditor(Employment contact){
        this(contact.getEmployeeId(),contact);
    }
    
    public EmployeeEmpHEditor(Employees emp){
        this(emp,null);
    }
    
    
 public EmployeeEmpHEditor(Employees empID,Employment acc) {
        initComponents();
        //Start transaction
        contact = acc;
        
        if(acc != null){
            jtEmployer.setText(contact.getEmployer());
            jdcFrom.setDate(contact.getCFrom());
            jdcTO.setDate(contact.getCTo());
            jtDesignation.setText(contact.getDesig());
            jtBenefits.setText(contact.getDesig());
            jtTel.setText(contact.getPhone());
            jtBenefits.setText(contact.getBenefits());
            jtComment.setText(contact.getComments());
          
        }
        
        
       
        
        employeeID = empID;
        jtEmployer.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                employer = jtEmployer.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                employer = jtEmployer.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                employer = jtEmployer.getText();
                modify();
            }
        });
        
       jdcFrom.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getSource()==jdcFrom && "date" == evt.getPropertyName()){
                    fro = jdcFrom.getDate();
                    modify();
                }
            }
        });
        
        jdcTO.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getSource()==jdcTO && "date" == evt.getPropertyName()){
                    to = jdcTO.getDate();
                }
            }
        });
        
        jtTel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                homephone = jtTel.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                homephone = jtTel.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                homephone = jtTel.getText();
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
        
        jtBenefits.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                benefits = jtBenefits.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                benefits = jtBenefits.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                benefits = jtBenefits.getText();
                modify();
            }
        });
        
        jtDesignation.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                desig = jtDesignation.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                desig = jtDesignation.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                desig = jtDesignation.getText();
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
        jtEmployer = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtBenefits = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jtTel = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtComment = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtDesignation = new javax.swing.JTextField();
        jdcFrom = new com.toedter.calendar.JDateChooser();
        jdcTO = new com.toedter.calendar.JDateChooser();
        jbSave = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(EmployeeEmpHEditor.class, "EmployeeEmpHEditor.jLabel3.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EmployeeEmpHEditor.class, "EmployeeEmpHEditor.jLabel1.text_1")); // NOI18N

        jtEmployer.setText(org.openide.util.NbBundle.getMessage(EmployeeEmpHEditor.class, "EmployeeEmpHEditor.jtEmployer.text")); // NOI18N

        jtBenefits.setColumns(20);
        jtBenefits.setRows(5);
        jScrollPane1.setViewportView(jtBenefits);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(EmployeeEmpHEditor.class, "EmployeeEmpHEditor.jLabel2.text_1")); // NOI18N

        jtTel.setText(org.openide.util.NbBundle.getMessage(EmployeeEmpHEditor.class, "EmployeeEmpHEditor.jtTel.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(EmployeeEmpHEditor.class, "EmployeeEmpHEditor.jLabel4.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(EmployeeEmpHEditor.class, "EmployeeEmpHEditor.jLabel5.text_1")); // NOI18N

        jtComment.setColumns(20);
        jtComment.setRows(5);
        jScrollPane2.setViewportView(jtComment);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(EmployeeEmpHEditor.class, "EmployeeEmpHEditor.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(EmployeeEmpHEditor.class, "EmployeeEmpHEditor.jLabel7.text")); // NOI18N

        jtDesignation.setText(org.openide.util.NbBundle.getMessage(EmployeeEmpHEditor.class, "EmployeeEmpHEditor.jtDesignation.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtDesignation, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdcFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtTel, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addComponent(jtEmployer)
                            .addComponent(jdcTO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtEmployer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jdcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jdcTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtDesignation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(EmployeeEmpHEditor.class, "EmployeeEmpHEditor.jbSave.text_1")); // NOI18N
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
            String insertSQL = "INSERT INTO [dbo].[Employment]\n" +
"           ([employee_id]\n" +
"           ,[Employer]\n" +
"           ,[CFrom]\n" +
"           ,[CTo]\n" +
"           ,[Desig]\n" +                    
"           ,[Benefits]\n" +
"           ,[Comments]\n" +
"           ,[Code]\n" +                    
"           ,[Phone])\n" +                    
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, employeeID.getEmployeeID());
            query.setParameter(2, employer);
            query.setParameter(3, fro);
            query.setParameter(4, to);
            query.setParameter(5, desig);
            query.setParameter(6, benefits);
            query.setParameter(7, comments);
            query.setParameter(8, "EH*");
            query.setParameter(9, homephone);
                       
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            StatusDisplayer.getDefault().setStatusText("Employment Added");
            
            UtilityPDR.loadEmployment(employeeID);
            
        }else{
            
            Employment eba = entityManager.find(Employment.class, contact.getId());
            
            
            
            if(null != employer){
                eba.setEmployer(employer);
            }
            
            if(null != fro){
                eba.setCFrom(fro);
            }
            
            if(null != to){
                eba.setCTo(to);
            }
            
            if(null != desig){
                eba.setDesig(desig);
            }
            
            if(null != benefits){
                eba.setBenefits(benefits);
            }
            
            if(null != homephone){
                eba.setPhone(homephone);
            }
            
            if(null != comments){
                eba.setComments(comments);
            }
            

            
            entityManager.getTransaction().commit();
            
            StatusDisplayer.getDefault().setStatusText("Employment Saved");
            
            UtilityPDR.loadEmployment(employeeID);
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
    private com.toedter.calendar.JDateChooser jdcFrom;
    private com.toedter.calendar.JDateChooser jdcTO;
    private javax.swing.JTextArea jtBenefits;
    private javax.swing.JTextArea jtComment;
    private javax.swing.JTextField jtDesignation;
    private javax.swing.JTextField jtEmployer;
    private javax.swing.JTextField jtTel;
    // End of variables declaration//GEN-END:variables


    
    
    void modify(){
        
                jbSave.setEnabled(true);
            
        
    }

    
    public Lookup getLookup() {
        TopComponent tc = WindowManager.getDefault().findTopComponent("EmployeeEditorTopComponent");
        return tc.getLookup();
    }
    
    
    
    void resetEditor(){
        jtEmployer.setText("");
        jdcFrom.setDate(null);
        jtBenefits.setText("");
        jtTel.setText("");
        jtComment.setText("");
        jdcTO.setDate(null);
        jtDesignation.setText("");
        to = null;
        comments = null;
        fro = null;
        benefits = null;
        employer = null;
        benefits = null;
        homephone = null;
    }
    
    


}
