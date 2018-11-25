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
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Query;
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
import systems.tech247.hr.Contracts;
import systems.tech247.hr.Employees;
import systems.tech247.hr.PdContractTypes;
import systems.tech247.pdr.UtilityPDR;
import systems.tech247.pdr.NodeContractRefreshEvent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//ContractEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "ContractEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdreditors.ContractEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_ContractEditorAction",
        preferredID = "ContractEditorTopComponent"
)
@Messages({
    "CTL_ContractEditorAction=Contract Editor",
    "CTL_ContractEditorTopComponent=Contract Editor",
    "HINT_ContractEditorTopComponent=This is a Contract Editor window"
})
public final class ContractEditorTopComponent extends TopComponent implements LookupListener {
    
    
    
    //Lookup for the Contact Type
    Lookup.Result<PdContractTypes> rslt = WindowManager.getDefault().findTopComponent("ContractTypesTopComponent").getLookup().lookupResult(PdContractTypes.class);
    
    
    
    Contracts application;
    Employees emp;
    InstanceContent ic = new InstanceContent();
    
    //The updateable
    Contracts updateable;
    
    //Contract Type Details
    long CtypeTypeID =0;
    
    PdContractTypes contractType = null;
    

    //Contact
    String contacts = "";
    
    //Comment
    String comment = null;
    
    //From Date
    Date from;
    //To Date
    Date to;
    //Active
    Boolean isactive;
    //ref
    String ref = "";
    
    public ContractEditorTopComponent(){
        this(null);
    }
    
    public ContractEditorTopComponent(Employees employee){
        this(null,employee);
        
    }
    
    
    public ContractEditorTopComponent(Contracts contact,Employees emp) {
        initComponents();
        setName(Bundle.CTL_ContractEditorTopComponent());
        setToolTipText(Bundle.HINT_ContractEditorTopComponent());
        associateLookup(new AbstractLookup(ic));
        this.emp = emp;
        this.application = contact;
        if(null!=application){
            this.updateable = DataAccess.getEntityManager().find(Contracts.class, contact.getId());
            
        }
        fillTheFields();
        
        
        jtContact.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                contacts = jtContact.getText();
                try{
                    updateable.setDescription(contacts);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                contacts = jtContact.getText();
                try{
                    updateable.setDescription(contacts);
                    
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                contacts = jtContact.getText();
                try{
                    updateable.setDescription(contacts);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        jtref.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ref = jtref.getText();
                try{
                    updateable.setRef(ref);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ref = jtref.getText();
                try{
                    updateable.setRef(ref);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                ref = jtref.getText();
                try{
                    updateable.setRef(ref);
                }catch(NullPointerException ex){
                    
                }
                modify();
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
        
        jdcFrom.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if("date".equals(evt.getPropertyName()) && evt.getSource()==jdcFrom){
                    from = jdcFrom.getDate();
                    //Compute the  expiry date
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(from);
                    if(contractType.getInDays()){
                        int lengthINDays = contractType.getCorrespondingValue().intValue();
                        cal.add(Calendar.DAY_OF_MONTH, lengthINDays);
                        to = cal.getTime();
                        jdcTo.setDate(to);
                        try{
                            updateable.setCTo(to);
                        }catch(NullPointerException ex){
                            
                        }
                    }else if(contractType.getInWeeks()){
                        int lengthINDays = contractType.getCorrespondingValue().intValue()*7;
                        cal.add(Calendar.DATE, lengthINDays);
                        to = cal.getTime();
                        jdcTo.setDate(to);
                        try{
                            updateable.setCTo(to);
                        }catch(NullPointerException ex){
                            
                        }
                    }else if(contractType.getInMonths()){
                        int lengthINDays = contractType.getCorrespondingValue().intValue();
                        cal.add(Calendar.MONTH, lengthINDays);
                        to = cal.getTime();
                        jdcTo.setDate(to);
                        try{
                            updateable.setCTo(to);
                        }catch(NullPointerException ex){
                            
                        }
                    }else if(contractType.getInYears()){
                        int lengthINDays = contractType.getCorrespondingValue().intValue();
                        cal.add(Calendar.YEAR, lengthINDays);
                        to = cal.getTime();
                        jdcTo.setDate(to);
                        try{
                            updateable.setCTo(to);
                        }catch(NullPointerException ex){
                            
                        }
                    }
                    try{
                        updateable.setCFrom(from);
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
                CtypeTypeID = application.getContractTypeID().getId();
                
                contacts = application.getDescription();
                jtref.setText(application.getRef());
                comment = application.getComments();
                
                jtContactType.setText(application.getContractTypeID().getDescription());
                jtContact.setText(contacts);
                jtComments.setText(comment);
            }catch(NullPointerException ex){
                
            }
        }
        
       
       

    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result<PdContractTypes> rslt = (Lookup.Result<PdContractTypes>)le.getSource();
        for(PdContractTypes l : rslt.allInstances()){
            contractType = l;
            jtContactType.setText(l.getDescription());
            jtContact.setText(l.getDescription());
            CtypeTypeID = l.getId();
            jtContact.setEditable(true);
            try{
                updateable.setContractTypeID(l);
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
            if(null==application){
                return "New Contract";
            }else if(emp!=null){
                return "Contract For" + emp.getSurName()+" "+emp.getOtherNames();
            }else{
                return "Contract";
            }
        }
        
        ContractEditorTopComponent tc(){
            return ContractEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==application){
               
                
                
                        String insertSQL = "INSERT INTO [dbo].[Contracts]\n" +
"           ([EmpCode]\n" +
"           ,[Code]\n" +
"           ,[Description]\n" +
"           ,[CFrom]\n" +
"           ,[CTo]\n" +
"           ,[Ref]\n" +
"           ,[Comments]\n" +
"           ,[employee_id]\n" +
"           ,[IsActive]\n" +
"           ,[ContractTypeID])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(4, from);
            query.setParameter(5, to);
            query.setParameter(3, contacts);
            query.setParameter(6, ref);
            query.setParameter(7, comment);
            query.setParameter(8, emp.getEmployeeID());
            query.setParameter(9, isactive);
            query.setParameter(10, contractType.getId());
            
            
            
            

            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Crea
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            UtilityPDR.pdrIC.set(Arrays.asList(new NodeContractRefreshEvent()), null);
            UtilityPDR.pdrIC.set(Arrays.asList(new String()), null);
            
            
            
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
        
        
    
            if(contractType==null && updateable==null){
                StatusDisplayer.getDefault().setStatusText("Choose A Contract Type");
            }else if(from==null){
                StatusDisplayer.getDefault().setStatusText("Specify The Start");
            }else{    

            //Are there changes
                
                
                if(getLookup().lookup(ContactAppSavable.class)==null){
                            ic.add(new ContactAppSavable());
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
        jLabel8 = new javax.swing.JLabel();
        jdcFrom = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jdcTo = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jtref = new javax.swing.JTextField();
        jcbActive = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ContractEditorTopComponent.class, "ContractEditorTopComponent.jLabel2.text")); // NOI18N

        jtContactType.setText(org.openide.util.NbBundle.getMessage(ContractEditorTopComponent.class, "ContractEditorTopComponent.jtContactType.text")); // NOI18N
        jtContactType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtContactTypeKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(ContractEditorTopComponent.class, "ContractEditorTopComponent.jLabel7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ContractEditorTopComponent.class, "ContractEditorTopComponent.jLabel1.text")); // NOI18N

        jtComments.setColumns(20);
        jtComments.setRows(5);
        jScrollPane1.setViewportView(jtComments);

        jtContact.setEditable(false);
        jtContact.setText(org.openide.util.NbBundle.getMessage(ContractEditorTopComponent.class, "ContractEditorTopComponent.jtContact.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(ContractEditorTopComponent.class, "ContractEditorTopComponent.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(ContractEditorTopComponent.class, "ContractEditorTopComponent.jLabel9.text")); // NOI18N

        jdcTo.setEnabled(false);
        jdcTo.setFocusable(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(ContractEditorTopComponent.class, "ContractEditorTopComponent.jLabel10.text")); // NOI18N

        jtref.setText(org.openide.util.NbBundle.getMessage(ContractEditorTopComponent.class, "ContractEditorTopComponent.jtref.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbActive, org.openide.util.NbBundle.getMessage(ContractEditorTopComponent.class, "ContractEditorTopComponent.jcbActive.text")); // NOI18N
        jcbActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbActiveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcbActive)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtContactType, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jtContact)
                                    .addComponent(jdcFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdcTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtref)))
                            .addComponent(jLabel1))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jdcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jdcTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jtref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbActive)
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtContactTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtContactTypeKeyPressed
        TopComponent tc = WindowManager.getDefault().findTopComponent("ContractTypesTopComponent");
        DialogDisplayer.getDefault().notify(new DialogDescriptor(tc, "Select A Contract Type"));
    }//GEN-LAST:event_jtContactTypeKeyPressed

    private void jcbActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbActiveActionPerformed
        isactive = jcbActive.isSelected();
        try{
            updateable.setIsActive(isactive);
        }catch(NullPointerException ex){
            
        }
    }//GEN-LAST:event_jcbActiveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jcbActive;
    private com.toedter.calendar.JDateChooser jdcFrom;
    private com.toedter.calendar.JDateChooser jdcTo;
    private javax.swing.JTextArea jtComments;
    private javax.swing.JTextField jtContact;
    private javax.swing.JTextField jtContactType;
    private javax.swing.JTextField jtref;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        rslt.addLookupListener(this);
        resultChanged(new LookupEvent(rslt));
    }

    @Override
    public void componentClosed() {
        rslt.removeLookupListener(this);
        CtypeTypeID = 0;
        contacts="";
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
