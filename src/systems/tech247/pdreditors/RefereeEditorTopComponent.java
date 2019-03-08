/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;


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
import systems.tech247.hr.Ref;
import systems.tech247.pdr.NodeRefreshRefereeEvent;
import systems.tech247.pdr.UtilityPDR;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//RefereeEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "RefereeEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdreditors.RefereeEditorTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_RefereeEditorAction",
//        preferredID = "RefereeEditorTopComponent"
//)
@Messages({
    "CTL_RefereeEditorAction=Referee Editor",
    "CTL_RefereeEditorTopComponent=Referee Editor",
    "HINT_RefereeEditorTopComponent="
})
public final class RefereeEditorTopComponent extends TopComponent {
    
    
    
        
    InstanceContent ic = new InstanceContent();
    Lookup lookup = new AbstractLookup(ic);
    Ref application;
    Employees emp;
    
    
    //The updateable
    Ref updateable;
    
    //Next Of Kin Details
    String surname = "";
    String otherNames = "";
    
    String address = "";
  
    String mobile = "";
    String email = "";
    String phone = "";
    

    
    
    public RefereeEditorTopComponent(){
        this(null);
    }
    
    public RefereeEditorTopComponent(Employees employee){
        this(null,employee);
        
    }
    
    
    public RefereeEditorTopComponent(Ref contact,Employees emp) {
        initComponents();
        setName(Bundle.CTL_RefereeEditorTopComponent());
        setToolTipText(Bundle.HINT_RefereeEditorTopComponent());
        associateLookup(lookup);
        this.emp = emp;
        this.application = contact;
        if(null!=application){
            this.updateable = DataAccess.getEntityManager().find(Ref.class, contact.getId());
            
        }
        
        fillTheFields();
        
        
        jtPhone.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                phone = jtPhone.getText();
                try{
                    updateable.setPhone(phone);
                }catch(NullPointerException ex){
                    
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                phone = jtPhone.getText();
                try{
                    updateable.setPhone(phone);
                }catch(NullPointerException ex){
                    
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                phone = jtPhone.getText();
                try{
                    updateable.setPhone(phone);
                }catch(NullPointerException ex){
                    
                }
            }
        });
        
        jtOthernames.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                otherNames = jtOthernames.getText();
                try{
                    updateable.setOtherNames(otherNames);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                otherNames = jtOthernames.getText();
                try{
                    updateable.setOtherNames(otherNames);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                 otherNames = jtOthernames.getText();
                try{
                    updateable.setOtherNames(otherNames);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        jtSurname.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                surname = jtSurname.getText();
                try{
                    updateable.setSurname(surname);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                surname = jtSurname.getText();
                try{
                    updateable.setSurname(surname);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                surname = jtSurname.getText();
                try{
                    updateable.setSurname(surname);
                }catch(NullPointerException ex){
                    
                }
                modify();
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
        
        
        
       
            
        
            
    }
    
    void fillTheFields(){
        if(application!=null){
            try{
                jtSurname.setText(application.getSurname());
                jtOthernames.setText(application.getOtherNames());
                jtAddress.setText(application.getAddress());
                jtEmail.setText(application.getEMail());
                
                jtPhone.setText(application.getPhone());
                
                jtMobile.setText(application.getMNo());
                
            }catch(NullPointerException ex){
                
            }
        }
        
       
       

    }


    
    
    
    private class RefereeSavable extends AbstractSavable{
        
        RefereeSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            if(null==application){
                return "New Referee";
            }else if(emp!=null){
                return "Referee For " + emp.getSurName()+" "+emp.getOtherNames();
            }else{
                return "Reeferee";
            }
        }
        
        RefereeEditorTopComponent tc(){
            return RefereeEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==application){
               
                
                
                        String insertSQL = "INSERT INTO [dbo].[ref]\n" +
"           ([Phone]\n" +
"           ,[Code]\n" +
"           ,[Surname]\n" +
"           ,[OtherNames]\n" +
"           ,[MNo]\n" +
"           ,[EMail]\n" +
"           ,[Address]\n" +
"           ,[Comments]\n" +
"           ,[employee_id])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(9, emp.getEmployeeID());
            query.setParameter(3, surname);
            query.setParameter(4, otherNames);
            query.setParameter(5, mobile);
            query.setParameter(6, email);
            query.setParameter(7, address);
            query.setParameter(1, phone);
            
            
            

            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Creating a new Employee
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            UtilityPDR.pdrIC.set(Arrays.asList(new NodeRefreshRefereeEvent()), null);
            
            
            
            this.tc().close();
            
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof RefereeSavable){
                RefereeSavable e = (RefereeSavable)o;
                return tc() == e.tc();
            }
            return false;        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
    
    public void modify(){
        
        
    
            if(null==updateable){
            if(surname.length()<= 1){
                StatusDisplayer.getDefault().setStatusText("A proper Surname is required");
            }else if(otherNames.length()<=1){
                StatusDisplayer.getDefault().setStatusText("A proper First Name is required");
            }else if(mobile.length()<=1){
                StatusDisplayer.getDefault().setStatusText("Mobile is required");
            }else{
                if(getLookup().lookup(RefereeSavable.class)==null){
                            ic.add(new RefereeSavable());
                }
            }
        }else{
            if(updateable.getSurname().length()<=1){
                StatusDisplayer.getDefault().setStatusText("A proper Surname is required");
            }else if(updateable.getOtherNames().length()<=1){
                StatusDisplayer.getDefault().setStatusText("A proper Last name is required");
            }else if(updateable.getMNo().length()<=1){
                StatusDisplayer.getDefault().setStatusText("Mobile is required");
            }else{
                if(getLookup().lookup(RefereeSavable.class)==null){
                            ic.add(new RefereeSavable());
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
        jtSurname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtAddress = new javax.swing.JTextArea();
        jtOthernames = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtEmail = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jtMobile = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jtPhone = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(RefereeEditorTopComponent.class, "RefereeEditorTopComponent.jLabel2.text")); // NOI18N

        jtSurname.setText(org.openide.util.NbBundle.getMessage(RefereeEditorTopComponent.class, "RefereeEditorTopComponent.jtSurname.text")); // NOI18N
        jtSurname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtSurnameKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(RefereeEditorTopComponent.class, "RefereeEditorTopComponent.jLabel7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(RefereeEditorTopComponent.class, "RefereeEditorTopComponent.jLabel1.text")); // NOI18N

        jtAddress.setColumns(20);
        jtAddress.setRows(5);
        jScrollPane1.setViewportView(jtAddress);

        jtOthernames.setText(org.openide.util.NbBundle.getMessage(RefereeEditorTopComponent.class, "RefereeEditorTopComponent.jtOthernames.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(RefereeEditorTopComponent.class, "RefereeEditorTopComponent.jLabel12.text")); // NOI18N

        jtEmail.setText(org.openide.util.NbBundle.getMessage(RefereeEditorTopComponent.class, "RefereeEditorTopComponent.jtEmail.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(RefereeEditorTopComponent.class, "RefereeEditorTopComponent.jLabel13.text")); // NOI18N

        jtMobile.setText(org.openide.util.NbBundle.getMessage(RefereeEditorTopComponent.class, "RefereeEditorTopComponent.jtMobile.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(RefereeEditorTopComponent.class, "RefereeEditorTopComponent.jLabel14.text")); // NOI18N

        jtPhone.setText(org.openide.util.NbBundle.getMessage(RefereeEditorTopComponent.class, "RefereeEditorTopComponent.jtPhone.text")); // NOI18N

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
                            .addGap(12, 12, 12)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(jtOthernames)
                                .addComponent(jtEmail)
                                .addComponent(jtMobile)
                                .addComponent(jtPhone)))
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
                    .addComponent(jLabel13)
                    .addComponent(jtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtAddress;
    private javax.swing.JTextField jtEmail;
    private javax.swing.JTextField jtMobile;
    private javax.swing.JTextField jtOthernames;
    private javax.swing.JTextField jtPhone;
    private javax.swing.JTextField jtSurname;
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
