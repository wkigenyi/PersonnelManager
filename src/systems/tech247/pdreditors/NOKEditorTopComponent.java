/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.persistence.Query;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.ActionID;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import static systems.tech247.dbaccess.DataAccess.entityManager;
import systems.tech247.hr.Contacts;
import systems.tech247.hr.Ctypes;
import systems.tech247.hr.Employees;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//ContactEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "ContactEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdreditors.NOKEditorTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_NOKEditorAction",
//        preferredID = "NOKEditorTopComponent"
//)
@Messages({
    "CTL_NOKEditorAction=Contact Editor",
    "CTL_NOKEditorTopComponent=Contact Editor",
    "HINT_NOKEditorTopComponent=This is a Contact Editor window"
})
public final class NOKEditorTopComponent extends TopComponent implements LookupListener {
    
    
    
    
    
    
    
    Contacts application;
    Employees emp;
    InstanceContent ic = new InstanceContent();
    
    //The updateable
    Contacts updateable;
    
    //Contact Details
    long CtypeTypeID =0;
    
    

    //Contact
    String contacts = null;
    
    //Comment
    String comment = null;
    
    
    public NOKEditorTopComponent(){
        this(null);
    }
    
    public NOKEditorTopComponent(Employees employee){
        this(null,employee);
        
    }
    
    
    public NOKEditorTopComponent(Contacts contact,Employees emp) {
        initComponents();
        setName(Bundle.CTL_ContactEditorTopComponent());
        setToolTipText(Bundle.HINT_ContactEditorTopComponent());
        this.emp = emp;
        this.application = contact;
        if(null!=application){
            this.updateable = DataAccess.getEntityManager().find(Contacts.class, contact.getContactID());
            
        }
        jtContact.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                contacts = jtContact.getText();
                try{
                    updateable.setContact(contacts);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                contacts = jtContact.getText();
                try{
                    updateable.setContact(contacts);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                                contacts = jtContact.getText();
                try{
                    updateable.setContact(contacts);
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
                comment = jtComments.getText();
                try{
                    updateable.setComments(comment);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                comment = jtComments.getText();
                try{
                    updateable.setComments(comment);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                comment = jtComments.getText();
                try{
                    updateable.setComments(comment);
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
                jtContactType.setText(application.getCtypeID().getDescription());
                jtContact.setText(application.getContact());
                jtComments.setText(application.getComments());
            }catch(NullPointerException ex){
                
            }
        }
        
       
       

    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result<Ctypes> rslt = (Lookup.Result<Ctypes>)le.getSource();
        for(Ctypes l : rslt.allInstances()){
            jtContactType.setText(l.getDescription());
            CtypeTypeID = l.getId();
            try{
                updateable.setCtypeID(l);
            }catch(NullPointerException ex){
                
            }
            
            modify();
        }
    }
    
    
    
    private class LeaveAppSavable extends AbstractSavable{
        
        LeaveAppSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            if(null==application){
                return "New Contact";
            }else if(emp!=null){
                return "Contact For" + emp.getSurName()+" "+emp.getOtherNames();
            }else{
                return "Contact";
            }
        }
        
        NOKEditorTopComponent tc(){
            return NOKEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==application){
               
                
                
                        String insertSQL = "INSERT INTO [dbo].[contacts]\n" +
"           ([CtypeID]\n" +
"           ,[Contact]\n" +
"           ,[Comments]\n" +
"           ,[EmployeeID])\n" +
"     VALUES\n" +
"           (?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(4, emp.getEmployeeID());
            query.setParameter(2, contacts);
            query.setParameter(3, comment);
            query.setParameter(1, CtypeTypeID);
            
            
            
            

            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Creating a new Employee
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            
            
            
            this.tc().close();
            
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof LeaveAppSavable){
                LeaveAppSavable e = (LeaveAppSavable)o;
                return tc() == e.tc();
            }
            return false;        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
    
    public void modify(){
        
        
    
            if(CtypeTypeID == 0){
                StatusDisplayer.getDefault().setStatusText("Choose A contact Type");
            
            }else{
                if(getLookup().lookup(LeaveAppSavable.class)==null){
                            ic.add(new LeaveAppSavable());
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
        jtContactType = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtComments = new javax.swing.JTextArea();
        jtContact = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(NOKEditorTopComponent.class, "NOKEditorTopComponent.jLabel2.text")); // NOI18N

        jtContactType.setText(org.openide.util.NbBundle.getMessage(NOKEditorTopComponent.class, "NOKEditorTopComponent.jtContactType.text")); // NOI18N
        jtContactType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtContactTypeKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(NOKEditorTopComponent.class, "NOKEditorTopComponent.jLabel7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(NOKEditorTopComponent.class, "NOKEditorTopComponent.jLabel1.text")); // NOI18N

        jtComments.setColumns(20);
        jtComments.setRows(5);
        jScrollPane1.setViewportView(jtComments);

        jtContact.setText(org.openide.util.NbBundle.getMessage(NOKEditorTopComponent.class, "NOKEditorTopComponent.jtContact.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtContactType, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(jtContact)))
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtContactType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtContactTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtContactTypeKeyPressed
        TopComponent tc = WindowManager.getDefault().findTopComponent("ContactTypesTopComponent");
        DialogDisplayer.getDefault().notify(new DialogDescriptor(tc, "Select A Contact Type"));
    }//GEN-LAST:event_jtContactTypeKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtComments;
    private javax.swing.JTextField jtContact;
    private javax.swing.JTextField jtContactType;
    // End of variables declaration//GEN-END:variables
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
