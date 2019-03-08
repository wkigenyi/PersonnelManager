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
import systems.tech247.hr.Employees;
import systems.tech247.hr.Employment;
import systems.tech247.pdr.NodeRefreshEmploymentEvent;
import systems.tech247.pdr.UtilityPDR;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//EmploymentEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmploymentEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdreditors.EmploymentEditorTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_EmploymentEditorAction",
//        preferredID = "EmploymentEditorTopComponent"
//)
@Messages({
    "CTL_EmploymentEditorAction=Employment Editor",
    "CTL_EmploymentEditorTopComponent=Employment Editor",
    "HINT_EmploymentEditorTopComponent="
})
public final class EmploymentEditorTopComponent extends TopComponent{
    
    
    
    //Lookup for the Contact Type
    
    
    
    
    Employment application;
    Employees emp;
    InstanceContent ic = new InstanceContent();
    
    //The updateable
    Employment updateable;
    
    //Employment Details
    String employer = "";
    Date from;
    Date to;
    String designation = "";
    String reason ="";
    String telephone ="";
    String address = "";
    
    
    
    
    public EmploymentEditorTopComponent(){
        this(null);
    }
    
    public EmploymentEditorTopComponent(Employees employee){
        this(null,employee);
        
    }
    
    
    public EmploymentEditorTopComponent(Employment contact,Employees emp) {
        initComponents();
        setName(Bundle.CTL_EmploymentEditorTopComponent());
        setToolTipText(Bundle.HINT_EmploymentEditorTopComponent());
        associateLookup(new AbstractLookup(ic));
        this.emp = emp;
        this.application = contact;
        if(null!=application){
            this.updateable = DataAccess.getEntityManager().find(Employment.class, contact.getId());
            
        }
        fillTheFields();
        
        jtEmployer.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                employer = jtEmployer.getText();
                try{
                    updateable.setEmployer(employer);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                employer = jtEmployer.getText();
                try{
                    updateable.setEmployer(employer);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                employer = jtEmployer.getText();
                try{
                    updateable.setEmployer(employer);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        
        jtDesignation.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                designation = jtDesignation.getText();
                try{
                    updateable.setDesig(designation);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                designation = jtDesignation.getText();
                try{
                    updateable.setDesig(designation);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                designation = jtDesignation.getText();
                try{
                    updateable.setDesig(designation);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        jtReason.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                reason = jtReason.getText();
                try{
                    updateable.setReasons(reason);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                reason = jtReason.getText();
                try{
                    updateable.setReasons(reason);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                reason = jtReason.getText();
                try{
                    updateable.setReasons(reason);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jtAddress.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                address = jtAddress.getText();
                try{
                    updateable.setAddress(address);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                address = jtAddress.getText();
                try{
                    updateable.setAddress(address);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                address = jtAddress.getText();
                try{
                    updateable.setAddress(address);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jtTelephone.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                telephone = jtTelephone.getText();
                try{
                    updateable.setPhone(telephone);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                telephone = jtTelephone.getText();
                try{
                    updateable.setPhone(telephone);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                telephone = jtTelephone.getText();
                try{
                    updateable.setPhone(telephone);
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
                
                
                jtDesignation.setText(application.getDesig());
                jtReason.setText(application.getReasons());
                jtEmployer.setText(application.getEmployer());
                jtAddress.setText(application.getAddress());
                jtTelephone.setText(application.getPhone());
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
                return "New Employment";
            }else if(emp!=null){
                return "Employment For" + emp.getSurName()+" "+emp.getOtherNames();
            }else{
                return "Employment";
            }
        }
        
        EmploymentEditorTopComponent tc(){
            return EmploymentEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==application){
               
                
                
                        String insertSQL = "INSERT INTO [dbo].[employment]\n" +
"           ([EmpCode]\n" +
"           ,[Code]\n" +
"           ,[Employer]\n" + //3.employer
"           ,[CFrom]\n" +    //4.from
"           ,[CTo]\n" +      //5.to   
"           ,[Reasons]\n" +     //6.reasons
"           ,[RName]\n" +
"           ,[Desig]\n" +       //8.design
"           ,[Super]\n" +
"           ,[Salary]\n" +
"           ,[Comments]\n" +
"           ,[Address]\n" +     //12.address
"           ,[Phone]\n" +       //13.telephone
"           ,[employee_id]\n" + //14.employee id
"           ,[CurrencyId]\n" +
"           ,[Isgross]\n" +
"           ,[Benefits])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(14, emp.getEmployeeID());
            query.setParameter(2, "");
            query.setParameter(3, employer);
            query.setParameter(4, from);
            query.setParameter(5, to);
            query.setParameter(6, reason);
            query.setParameter(8, designation);
            query.setParameter(12, address);
            query.setParameter(13, telephone);
            
            

            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Crea
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            UtilityPDR.pdrIC.set(Arrays.asList(new NodeRefreshEmploymentEvent()), null);
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
                if(employer.length()<4){
                    StatusDisplayer.getDefault().setStatusText("Proper Employer Name is required");
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
                if(updateable.getEmployer().length()<4){
                    StatusDisplayer.getDefault().setStatusText("Proper Employer Name is required");
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
        jtEmployer = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtReason = new javax.swing.JTextArea();
        jtDesignation = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jdcFrom = new com.toedter.calendar.JDateChooser();
        jdcTo = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtAddress = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jtTelephone = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(EmploymentEditorTopComponent.class, "EmploymentEditorTopComponent.jLabel2.text")); // NOI18N

        jtEmployer.setText(org.openide.util.NbBundle.getMessage(EmploymentEditorTopComponent.class, "EmploymentEditorTopComponent.jtEmployer.text")); // NOI18N
        jtEmployer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtEmployerKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(EmploymentEditorTopComponent.class, "EmploymentEditorTopComponent.jLabel7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EmploymentEditorTopComponent.class, "EmploymentEditorTopComponent.jLabel1.text")); // NOI18N

        jtReason.setColumns(20);
        jtReason.setRows(5);
        jScrollPane1.setViewportView(jtReason);

        jtDesignation.setText(org.openide.util.NbBundle.getMessage(EmploymentEditorTopComponent.class, "EmploymentEditorTopComponent.jtDesignation.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(EmploymentEditorTopComponent.class, "EmploymentEditorTopComponent.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(EmploymentEditorTopComponent.class, "EmploymentEditorTopComponent.jLabel9.text")); // NOI18N

        jtAddress.setColumns(20);
        jtAddress.setRows(5);
        jScrollPane2.setViewportView(jtAddress);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(EmploymentEditorTopComponent.class, "EmploymentEditorTopComponent.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(EmploymentEditorTopComponent.class, "EmploymentEditorTopComponent.jLabel10.text")); // NOI18N

        jtTelephone.setText(org.openide.util.NbBundle.getMessage(EmploymentEditorTopComponent.class, "EmploymentEditorTopComponent.jtTelephone.text")); // NOI18N
        jtTelephone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtTelephoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(27, 27, 27)
                                .addComponent(jtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtEmployer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jtDesignation, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jdcFrom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdcTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jdcFrom, jdcTo, jtDesignation, jtEmployer, jtTelephone});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtEmployer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtDesignation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(11, 11, 11)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtEmployerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtEmployerKeyPressed

    }//GEN-LAST:event_jtEmployerKeyPressed

    private void jtTelephoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtTelephoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtTelephoneActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcFrom;
    private com.toedter.calendar.JDateChooser jdcTo;
    private javax.swing.JTextArea jtAddress;
    private javax.swing.JTextField jtDesignation;
    private javax.swing.JTextField jtEmployer;
    private javax.swing.JTextArea jtReason;
    private javax.swing.JTextField jtTelephone;
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
