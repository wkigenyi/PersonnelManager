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
import javax.persistence.Query;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import static systems.tech247.dbaccess.DataAccess.entityManager;
import systems.tech247.hr.Employees;
import systems.tech247.hr.Family;
import systems.tech247.pdr.NodeRefreshFamilyEvent;
import systems.tech247.pdr.UtilityPDR;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//FamilyEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "FamilyEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdreditors.FamilyEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_FamilyEditorAction",
        preferredID = "FamilyEditorTopComponent"
)
@Messages({
    "CTL_FamilyEditorAction=Family Editor",
    "CTL_FamilyEditorTopComponent=Family Editor",
    "HINT_FamilyEditorTopComponent="
})
public final class FamilyEditorTopComponent extends TopComponent {
    
    
    
        
    InstanceContent ic = new InstanceContent();
    Lookup lookup = new AbstractLookup(ic);
    Family application;
    Employees emp;
    
    
    //The updateable
    Family updateable;
    
    //Next Of Kin Details
    String surname = "";
    String otherNames = "";
    String relation = "";
    String address = "";
    String contacts = "";
    String homeTelephone = "";
    String officeTelephone = "";
    String mobile = "";
    String email = "";
    

    
    
    public FamilyEditorTopComponent(){
        this(null);
    }
    
    public FamilyEditorTopComponent(Employees employee){
        this(null,employee);
        
    }
    
    
    public FamilyEditorTopComponent(Family contact,Employees emp) {
        initComponents();
        setName(Bundle.CTL_FamilyEditorTopComponent());
        setToolTipText(Bundle.HINT_FamilyEditorTopComponent());
        associateLookup(lookup);
        this.emp = emp;
        this.application = contact;
        if(null!=application){
            this.updateable = DataAccess.getEntityManager().find(Family.class, contact.getId());
            
        }
        
        
        
        
        jtOthernames.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                otherNames = jtOthernames.getText();
                try{
                    updateable.setOtherNames(otherNames);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                otherNames = jtOthernames.getText();
                try{
                    updateable.setOtherNames(otherNames);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                otherNames = jtOthernames.getText();
                try{
                    updateable.setOtherNames(otherNames);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jtSurname.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                surname = jtSurname.getText();
                try{
                    updateable.setSurName(surname);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                surname = jtSurname.getText();
                try{
                    updateable.setSurName(surname);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                surname = jtSurname.getText();
                try{
                    updateable.setSurName(surname);
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
        
        jtrelation.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                relation = jtrelation.getText();
                try{
                    updateable.setRelation(relation);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                relation = jtrelation.getText();
                try{
                    updateable.setRelation(relation);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                relation = jtrelation.getText();
                try{
                    updateable.setRelation(relation);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jtEmail.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                email = jtEmail.getText();
                try{
                    updateable.setEMail(email);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                email = jtEmail.getText();
                try{
                    updateable.setEMail(email);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                email = jtEmail.getText();
                try{
                    updateable.setEMail(email);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jtHomePhone.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                homeTelephone = jtHomePhone.getText();
                try{
                    updateable.setHTelNo(homeTelephone);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                homeTelephone = jtHomePhone.getText();
                try{
                    updateable.setHTelNo(homeTelephone);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                homeTelephone = jtHomePhone.getText();
                try{
                    updateable.setHTelNo(homeTelephone);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jtOfficetelephone.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                officeTelephone = jtOfficetelephone.getText();
                try{
                    updateable.setOffTelNo(officeTelephone);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                officeTelephone = jtOfficetelephone.getText();
                try{
                    updateable.setOffTelNo(officeTelephone);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                officeTelephone = jtOfficetelephone.getText();
                try{
                    updateable.setOffTelNo(officeTelephone);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jtMobile.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                mobile = jtMobile.getText();
                try{
                    updateable.setMNo(mobile);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                mobile = jtMobile.getText();
                try{
                    updateable.setMNo(mobile);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                mobile = jtMobile.getText();
                try{
                    updateable.setMNo(mobile);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        
       
            fillTheFields();
        
            
    }
    
    void fillTheFields(){
        if(application!=null){
            try{
                jtSurname.setText(application.getSurName());
                jtOthernames.setText(application.getOtherNames());
                jtAddress.setText(application.getAddress());
                jtEmail.setText(application.getEMail());
                jtHomePhone.setText(application.getHTelNo());
                jtOfficetelephone.setText(application.getOffTelNo());
                jtrelation.setText(application.getRelation());
                jtMobile.setText(application.getMNo());
                jtrelation.setText(application.getRelation());
            }catch(NullPointerException ex){
                
            }
        }
        
       
       

    }


    
    
    
    private class FamilySavable extends AbstractSavable{
        
        FamilySavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            if(null==application){
                return "New Family";
            }else if(emp!=null){
                return "Family For" + emp.getSurName()+" "+emp.getOtherNames();
            }else{
                return "Family";
            }
        }
        
        FamilyEditorTopComponent tc(){
            return FamilyEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==application){
               
                
                
                        String insertSQL = "INSERT INTO [dbo].[family]\n" +
"           ([EmpCode]\n" +
"           ,[Code]\n" +
"           ,[SurName]\n" +
"           ,[OtherNames]\n" +
"           ,[IDNo]\n" +
"           ,[Relation]\n" +
"           ,[DOB]\n" +
"           ,[Occupation]\n" +
"           ,[Address]\n" +
"           ,[HTelNo]\n" +
"           ,[OffTelNo]\n" +
"           ,[MNo]\n" +
"           ,[EMail]\n" +
"           ,[Signed]\n" +
"           ,[SDate]\n" +
"           ,[Comments]\n" +
"           ,[employee_id]\n" +
"           ,[EmergencyContact])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(4, otherNames);
            query.setParameter(2, contacts);
            query.setParameter(3, surname);
            query.setParameter(9, address);
            query.setParameter(6, relation);
            query.setParameter(10, homeTelephone);
            query.setParameter(11, officeTelephone);
            query.setParameter(12, mobile);
            query.setParameter(13, email);
            query.setParameter(17, emp.getEmployeeID());
            
            
            
            

            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Creating a new Employee
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            UtilityPDR.pdrIC.set(Arrays.asList(new NodeRefreshFamilyEvent()), null);
            
            
            
            this.tc().close();
            
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof FamilySavable){
                FamilySavable e = (FamilySavable)o;
                return tc() == e.tc();
            }
            return false;        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
    
    public void modify(){
        
        
    
            if(surname.length()<= 1){
                StatusDisplayer.getDefault().setStatusText("A proper Surname is required");
            }else if(otherNames.length()<=1){
                StatusDisplayer.getDefault().setStatusText("A proper First Name is required");
            }else{
                if(getLookup().lookup(FamilySavable.class)==null){
                            ic.add(new FamilySavable());
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
        jtSurname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtAddress = new javax.swing.JTextArea();
        jtOthernames = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtrelation = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtHomePhone = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jtOfficetelephone = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtEmail = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jtMobile = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jLabel2.text_1")); // NOI18N

        jtSurname.setText(org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jtSurname.text")); // NOI18N
        jtSurname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtSurnameKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jLabel7.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jLabel1.text_1")); // NOI18N

        jtAddress.setColumns(20);
        jtAddress.setRows(5);
        jScrollPane1.setViewportView(jtAddress);

        jtOthernames.setText(org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jtOthernames.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jLabel9.text")); // NOI18N

        jtrelation.setText(org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jtrelation.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jLabel10.text")); // NOI18N

        jtHomePhone.setText(org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jtHomePhone.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jLabel11.text")); // NOI18N

        jtOfficetelephone.setText(org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jtOfficetelephone.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jLabel12.text")); // NOI18N

        jtEmail.setText(org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jtEmail.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jLabel13.text")); // NOI18N

        jtMobile.setText(org.openide.util.NbBundle.getMessage(FamilyEditorTopComponent.class, "FamilyEditorTopComponent.jtMobile.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(jtOthernames)
                                .addComponent(jtrelation)
                                .addComponent(jtHomePhone)
                                .addComponent(jtOfficetelephone)
                                .addComponent(jtEmail)
                                .addComponent(jtMobile)))
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtOthernames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtrelation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtHomePhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jtOfficetelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtSurnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtSurnameKeyPressed
        
    }//GEN-LAST:event_jtSurnameKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtAddress;
    private javax.swing.JTextField jtEmail;
    private javax.swing.JTextField jtHomePhone;
    private javax.swing.JTextField jtMobile;
    private javax.swing.JTextField jtOfficetelephone;
    private javax.swing.JTextField jtOthernames;
    private javax.swing.JTextField jtSurname;
    private javax.swing.JTextField jtrelation;
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
