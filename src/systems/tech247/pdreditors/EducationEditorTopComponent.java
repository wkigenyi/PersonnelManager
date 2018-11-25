/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.Query;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.StatusDisplayer;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import static systems.tech247.dbaccess.DataAccess.entityManager;
import systems.tech247.hr.Edu;
import systems.tech247.hr.Employees;
import systems.tech247.pdr.NodeEducationRefreshEvent;
import systems.tech247.pdr.UtilityPDR;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//EducationEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EducationEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdreditors.EducationEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_EducationEditorAction",
        preferredID = "EducationEditorTopComponent"
)
@Messages({
    "CTL_EducationEditorAction=Education Editor",
    "CTL_EducationEditorTopComponent=Education Editor",
    "HINT_EducationEditorTopComponent="
})
public final class EducationEditorTopComponent extends TopComponent{
    
    
    
    //Lookup for the Contact Type
    
    
    
    
    Edu application;
    Employees emp;
    InstanceContent ic = new InstanceContent();
    
    //The updateable
    Edu updateable;
    
    //Employment Details
    String insitute = "";
    Date from;
    Date to;
    String course = "";
    String award ="";
    
    String comments = "";
    
    
    
    
    public EducationEditorTopComponent(){
        this(null);
    }
    
    public EducationEditorTopComponent(Employees employee){
        this(null,employee);
        
    }
    
    
    public EducationEditorTopComponent(Edu contact,Employees emp) {
        initComponents();
        setName(Bundle.CTL_EducationEditorTopComponent());
        setToolTipText(Bundle.HINT_EducationEditorTopComponent());
        associateLookup(new AbstractLookup(ic));
        this.emp = emp;
        this.application = contact;
        if(null!=application){
            this.updateable = DataAccess.getEntityManager().find(Edu.class, contact.getId());
            
        }
        fillTheFields();
        
        jtInsititute.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                insitute = jtInsititute.getText();
                try{
                    updateable.setInstitution(insitute);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                insitute = jtInsititute.getText();
                try{
                    updateable.setInstitution(insitute);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                insitute = jtInsititute.getText();
                try{
                    updateable.setInstitution(insitute);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        
        jtCourse.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                course = jtCourse.getText();
                try{
                    updateable.setCourse(course);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                course = jtCourse.getText();
                try{
                    updateable.setCourse(course);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                course = jtCourse.getText();
                try{
                    updateable.setCourse(course);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
                jtAward.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                award = jtAward.getText();
                try{
                    updateable.setAward(award);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                award = jtAward.getText();
                try{
                    updateable.setAward(award);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                award = jtAward.getText();
                try{
                    updateable.setAward(award);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        
        
        jtComments.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                comments = jtComments.getText();
                try{
                    updateable.setComments(comments);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                comments = jtComments.getText();
                try{
                    updateable.setComments(comments);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                comments = jtComments.getText();
                try{
                    updateable.setComments(comments);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        
        jdcFrom.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getSource()==jdcFrom && "date".equals(evt.getPropertyName())){
                    from = jdcFrom.getDate();
                    try{
                        updateable.setCFrom(from);
                    }catch(NullPointerException ex){
                        
                    }
                    modify();
                }
            }
        });
        
        jdcTo.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getSource()==jdcTo && "date".equals(evt.getPropertyName())){
                    to = jdcTo.getDate();
                    try{
                        updateable.setCTo(to);
                    }catch(NullPointerException ex){
                        
                    }
                    modify();
                }
            }
        });
       
            
        
            
    }
    
    void fillTheFields(){
        if(application!=null){
            try{
                //get the original values
                
                
                jtCourse.setText(application.getCourse());
                jtComments.setText(application.getComments());
                jtInsititute.setText(application.getInstitution());
                
                jtAward.setText(application.getAward());
                jdcFrom.setDate(application.getCFrom());
                jdcTo.setDate(application.getCTo());
            }catch(NullPointerException ex){
                
            }
        }
        
       
       

    }


    
    
    
    private class EmploymentSavable extends AbstractSavable{
        
        EmploymentSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            if(null==application){
                return "New Education";
            }else if(emp!=null){
                return "Education For" + emp.getSurName()+" "+emp.getOtherNames();
            }else{
                return "Education";
            }
        }
        
        EducationEditorTopComponent tc(){
            return EducationEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==application){
               
                
                
                        String insertSQL = "INSERT INTO [dbo].[edu]\n" +
"           ([EmpCode]\n" +
"           ,[Code]\n" +
"           ,[Course]\n" +
"           ,[CFrom]\n" +
"           ,[CTo]\n" +
"           ,[ELevel]\n" +
"           ,[Award]\n" +
"           ,[Comments]\n" +
"           ,[employee_id]\n" +
"           ,[Institution]\n" +
"           ,[education_id])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(9, emp.getEmployeeID());
            query.setParameter(2, "");
            query.setParameter(3, course);
            query.setParameter(4, from);
            query.setParameter(5, to);
            query.setParameter(7, award);
            query.setParameter(8, comments);
            query.setParameter(10, insitute);
            
            
            

            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Crea
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            UtilityPDR.pdrIC.set(Arrays.asList(new NodeEducationRefreshEvent()), null);
            UtilityPDR.pdrIC.set(Arrays.asList(new String()), null);
            
            
            
            this.tc().close();
            
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof EmploymentSavable){
                EmploymentSavable e = (EmploymentSavable)o;
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
                if(insitute.length()<4){
                    StatusDisplayer.getDefault().setStatusText("Proper Insitution Name is required");
                }else if(from==null || to==null){
                    StatusDisplayer.getDefault().setStatusText("Specify FROM and TO dates");
                }else if(to.before(from)){
                    StatusDisplayer.getDefault().setStatusText("To date cannot be before from date");
                }else{    
                    if(getLookup().lookup(EmploymentSavable.class)==null){
                        ic.add(new EmploymentSavable());
                    }
                }
            }else{ 
                if(updateable.getInstitution().length()<4){
                    StatusDisplayer.getDefault().setStatusText("Proper Insitution Name is required");
                }else if(updateable.getCTo().before(updateable.getCFrom())){
                    StatusDisplayer.getDefault().setStatusText("To date cannot be before from date");
                }else{    
                
                if(getLookup().lookup(EmploymentSavable.class)==null){
                            ic.add(new EmploymentSavable());
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
        jtInsititute = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtComments = new javax.swing.JTextArea();
        jtCourse = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jdcFrom = new com.toedter.calendar.JDateChooser();
        jdcTo = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jtAward = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(EducationEditorTopComponent.class, "EducationEditorTopComponent.jLabel2.text")); // NOI18N

        jtInsititute.setText(org.openide.util.NbBundle.getMessage(EducationEditorTopComponent.class, "EducationEditorTopComponent.jtInsititute.text")); // NOI18N
        jtInsititute.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtInsitituteKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(EducationEditorTopComponent.class, "EducationEditorTopComponent.jLabel7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EducationEditorTopComponent.class, "EducationEditorTopComponent.jLabel1.text")); // NOI18N

        jtComments.setColumns(20);
        jtComments.setRows(5);
        jScrollPane1.setViewportView(jtComments);

        jtCourse.setText(org.openide.util.NbBundle.getMessage(EducationEditorTopComponent.class, "EducationEditorTopComponent.jtCourse.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(EducationEditorTopComponent.class, "EducationEditorTopComponent.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(EducationEditorTopComponent.class, "EducationEditorTopComponent.jLabel9.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(EducationEditorTopComponent.class, "EducationEditorTopComponent.jLabel11.text")); // NOI18N

        jtAward.setText(org.openide.util.NbBundle.getMessage(EducationEditorTopComponent.class, "EducationEditorTopComponent.jtAward.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(77, 77, 77)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jdcTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                .addComponent(jtAward, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jtInsititute)
                                .addComponent(jtCourse, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jdcFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtInsititute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jtAward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jdcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jdcTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtInsitituteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtInsitituteKeyPressed

    }//GEN-LAST:event_jtInsitituteKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcFrom;
    private com.toedter.calendar.JDateChooser jdcTo;
    private javax.swing.JTextField jtAward;
    private javax.swing.JTextArea jtComments;
    private javax.swing.JTextField jtCourse;
    private javax.swing.JTextField jtInsititute;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        
    }

    @Override
    public void componentClosed() {
        
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
