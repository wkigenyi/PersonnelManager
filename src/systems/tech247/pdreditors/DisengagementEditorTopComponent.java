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
        dtd = "-//systems.tech247.pdreditors//DisengagementEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EducationEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdreditors.DisengagementEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_DisengagementEditorAction",
        preferredID = "DisengagementEditorTopComponent"
)
@Messages({
    "CTL_DisengagementEditorAction=Education Editor",
    "CTL_DisengagementEditorTopComponent=Education Editor",
    "HINT_DisengagementEditorTopComponent="
})
public final class DisengagementEditorTopComponent extends TopComponent{
    
    
    
    //Lookup for the Contact Type
    
    
    
    
    
    Employees updateable;
    InstanceContent ic = new InstanceContent();
    
    
    
    //Employment Details
    String insitute = "";
    Date from;
    Date to;
    String course = "";
    String disengamentRef ="";
    
    String comments = "";
    
    
    
    
    public DisengagementEditorTopComponent(){
        
    }
    
    
    
    
    public DisengagementEditorTopComponent(Employees emp) {
        initComponents();
        setName(Bundle.CTL_DisengagementEditorTopComponent());
        setToolTipText(Bundle.HINT_DisengagementEditorTopComponent());
        associateLookup(new AbstractLookup(ic));
        this.updateable = entityManager.find(Employees.class, emp.getEmployeeID());
        
        
        fillTheFields();
        
        
        
        
        
        
    
        
        
        
        
        jtReason.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                comments = jtReason.getText();
                try{
                    updateable.setDisEngagementReason(comments);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                comments = jtReason.getText();
                try{
                    updateable.setDisEngagementReason(comments);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                comments = jtReason.getText();
                try{
                    updateable.setDisEngagementReason(comments);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jtDisengagementRef.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                disengamentRef = jtDisengagementRef.getText();
                try{
                    updateable.setDisEngagementReferenceNumber(disengamentRef);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 disengamentRef = jtDisengagementRef.getText();
                try{
                    updateable.setDisEngagementReferenceNumber(disengamentRef);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                disengamentRef = jtDisengagementRef.getText();
                try{
                    updateable.setDisEngagementReferenceNumber(disengamentRef);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        
        jdcDisengagementDate.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getSource()==jdcDisengagementDate && "date".equals(evt.getPropertyName())){
                    from = jdcDisengagementDate.getDate();
                    try{
                        updateable.setDateOfDisengagement(from);
                    }catch(NullPointerException ex){
                        
                    }
                    modify();
                }
            }
        });
        
        
       
            
        
            
    }
    
    void fillTheFields(){
        
            try{
                //get the original values
                
                
                jcbDisengagement.setSelected(updateable.getIsDisengaged());
                jtReason.setText(updateable.getDisEngagementReason());
                jtDisengagementRef.setText(updateable.getDisEngagementReferenceNumber());
                
                
                jdcDisengagementDate.setDate(updateable.getDateOfDisengagement());
                
            }catch(NullPointerException ex){
                
            }
        
        
       
       

    }


    
    
    
    private class EmploymentSavable extends AbstractSavable{
        
        EmploymentSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            return "Disengament For " +updateable.getSurName()+" "+updateable.getOtherNames();
        }
        
        DisengagementEditorTopComponent tc(){
            return DisengagementEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            
                //Crea
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            

            
            
            
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

                if(updateable.getDateOfDisengagement()==null){
                    StatusDisplayer.getDefault().setStatusText("Specify The Date");
                }else if(updateable.getDisEngagementReferenceNumber()==null){
                    StatusDisplayer.getDefault().setStatusText("Specify The Disengagement Reference");
                }else{    
                
                if(getLookup().lookup(EmploymentSavable.class)==null){
                            ic.add(new EmploymentSavable());
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtReason = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jdcDisengagementDate = new com.toedter.calendar.JDateChooser();
        jcbDisengagement = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jtDisengagementRef = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DisengagementEditorTopComponent.class, "DisengagementEditorTopComponent.jLabel1.text")); // NOI18N

        jtReason.setColumns(20);
        jtReason.setRows(5);
        jScrollPane1.setViewportView(jtReason);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(DisengagementEditorTopComponent.class, "DisengagementEditorTopComponent.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbDisengagement, org.openide.util.NbBundle.getMessage(DisengagementEditorTopComponent.class, "DisengagementEditorTopComponent.jcbDisengagement.text")); // NOI18N
        jcbDisengagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDisengagementActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(DisengagementEditorTopComponent.class, "DisengagementEditorTopComponent.jLabel9.text")); // NOI18N

        jtDisengagementRef.setText(org.openide.util.NbBundle.getMessage(DisengagementEditorTopComponent.class, "DisengagementEditorTopComponent.jtDisengagementRef.text")); // NOI18N

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
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbDisengagement)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jdcDisengagementDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jtDisengagementRef))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbDisengagement)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jdcDisengagementDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtDisengagementRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbDisengagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDisengagementActionPerformed
        boolean disengage = jcbDisengagement.isSelected();
        updateable.setIsDisengaged(disengage);
        if(disengage){
            
            updateable.setCategoryID(null);
            updateable.setPayrollid(null);
            updateable.setOrganizationUnitID(null);
            //updateable.setCurrencyID(null);
        }else{
            
        }
        modify();
    }//GEN-LAST:event_jcbDisengagementActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jcbDisengagement;
    private com.toedter.calendar.JDateChooser jdcDisengagementDate;
    private javax.swing.JTextField jtDisengagementRef;
    private javax.swing.JTextArea jtReason;
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
