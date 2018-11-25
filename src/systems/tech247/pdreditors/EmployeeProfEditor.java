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
import systems.tech247.hr.Edu;
import systems.tech247.hr.Employees;
import systems.tech247.hr.Prof;
import systems.tech247.pdr.UtilityPDR;


/**
 *
 * @author Admin
 */
public class EmployeeProfEditor extends javax.swing.JPanel{

    
    
    DataAccess da = new DataAccess();
    Prof contact;
    Employees employeeID;
    
    
    //Updatables
    String school = null;
    Date fro = null;
    Date to = null;
    String course = null;
    String comments = null;
    String award = null;
    String homephone = null;
    EntityManager entityManager = da.getEntityManager();
   
    
    
   
    /**
     * Creates new form PersonalInfoPanel
     */
    public EmployeeProfEditor(Prof contact){
        this(contact.getEmployeeId(),contact);
    }
    
    public EmployeeProfEditor(Employees emp){
        this(emp,null);
    }
    
    
 public EmployeeProfEditor(Employees empID,Prof acc) {
        initComponents();
        //Start transaction
        contact = acc;
        
        if(acc != null){
            jtInsititute.setText(contact.getInstitution());
            try{
            jdcFrom.setDate(contact.getCFrom());
            
            
            }catch(Exception e){
                StatusDisplayer.getDefault().setStatusText(e.getLocalizedMessage());
            }
            try{
            jdcTO.setDate(contact.getCTo());
            }catch(Exception e){
                StatusDisplayer.getDefault().setStatusText(e.getLocalizedMessage());
            }
            jtCourse.setText(contact.getCourse());
            jtComment.setText(contact.getComments());
            jtAward.setText(contact.getAward());
            
          
        }
        
        
       
        
        employeeID = empID;
        jtInsititute.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                school = jtInsititute.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                school = jtInsititute.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                school = jtInsititute.getText();
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
        
        jtAward.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                homephone = jtAward.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                homephone = jtAward.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                homephone = jtAward.getText();
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
        
        
        
        jtAward.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                award = jtAward.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                award = jtAward.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                award = jtAward.getText();
                modify();
            }
        });
        
        jtCourse.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                course = jtCourse.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                course = jtCourse.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                course = jtCourse.getText();
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
        jtInsititute = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtComment = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jtAward = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtCourse = new javax.swing.JTextField();
        jdcFrom = new com.toedter.calendar.JDateChooser();
        jdcTO = new com.toedter.calendar.JDateChooser();
        jbSave = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(EmployeeProfEditor.class, "EmployeeProfEditor.jLabel3.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EmployeeProfEditor.class, "EmployeeProfEditor.jLabel1.text_1")); // NOI18N

        jtInsititute.setText(org.openide.util.NbBundle.getMessage(EmployeeProfEditor.class, "EmployeeProfEditor.jtInsititute.text")); // NOI18N

        jtComment.setColumns(20);
        jtComment.setRows(5);
        jScrollPane1.setViewportView(jtComment);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(EmployeeProfEditor.class, "EmployeeProfEditor.jLabel2.text_1")); // NOI18N

        jtAward.setText(org.openide.util.NbBundle.getMessage(EmployeeProfEditor.class, "EmployeeProfEditor.jtAward.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(EmployeeProfEditor.class, "EmployeeProfEditor.jLabel4.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(EmployeeProfEditor.class, "EmployeeProfEditor.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(EmployeeProfEditor.class, "EmployeeProfEditor.jLabel7.text")); // NOI18N

        jtCourse.setText(org.openide.util.NbBundle.getMessage(EmployeeProfEditor.class, "EmployeeProfEditor.jtCourse.text")); // NOI18N

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
                            .addComponent(jtCourse, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdcFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtAward, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                            .addComponent(jtInsititute)
                            .addComponent(jdcTO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 328, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtInsititute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jtCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtAward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(EmployeeProfEditor.class, "EmployeeProfEditor.jbSave.text_1")); // NOI18N
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
            String insertSQL = "INSERT INTO [dbo].[Prof]\n" +
"           ([employee_id]\n" +
"           ,[Institution]\n" +
"           ,[CFrom]\n" +
"           ,[CTo]\n" +
"           ,[Course]\n" +                    
"           ,[Award]\n" +
"           ,[Comments])\n" +                    
"     VALUES\n" +
"           (?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, employeeID.getEmployeeID());
            query.setParameter(2, school);
            query.setParameter(3, fro);
            query.setParameter(4, to);
            query.setParameter(5, course);
            query.setParameter(6, award);
            query.setParameter(7, comments);
           
                       
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            StatusDisplayer.getDefault().setStatusText("Proffesion Added");
            
            UtilityPDR.loadProf(employeeID);
            
        }else{
            
            Prof eba = entityManager.find(Prof.class, contact.getId());
            
            
            
            if(null != school){
                eba.setInstitution(school);
            }
            
            if(null != fro){
                eba.setCFrom(fro);
            }
            
            if(null != to){
                eba.setCTo(to);
            }
            
            if(null != course){
                eba.setCourse(course);
            }
            
            if(null != award){
                eba.setAward(award);
            }
            
           
            
            if(null != comments){
                eba.setComments(comments);
            }
            

            
            entityManager.getTransaction().commit();
            
            StatusDisplayer.getDefault().setStatusText("Proffession Saved");
            
            UtilityPDR.loadProf(employeeID);
            resetEditor();
        }
        

    }//GEN-LAST:event_jbSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbSave;
    private com.toedter.calendar.JDateChooser jdcFrom;
    private com.toedter.calendar.JDateChooser jdcTO;
    private javax.swing.JTextField jtAward;
    private javax.swing.JTextArea jtComment;
    private javax.swing.JTextField jtCourse;
    private javax.swing.JTextField jtInsititute;
    // End of variables declaration//GEN-END:variables


    
    
    void modify(){
        
                jbSave.setEnabled(true);
            
        
    }

    
    public Lookup getLookup() {
        TopComponent tc = WindowManager.getDefault().findTopComponent("EmployeeEditorTopComponent");
        return tc.getLookup();
    }
    
    
    
    void resetEditor(){
        jtInsititute.setText("");
        jdcFrom.setDate(null);
        jtComment.setText("");
        jtAward.setText("");
        jtComment.setText("");
        jdcTO.setDate(null);
        jtCourse.setText("");
        to = null;
        comments = null;
        fro = null;
        award = null;
        school = null;
        award = null;
        homephone = null;
    }
    
    


}
