/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.StatusDisplayer;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import static systems.tech247.dbaccess.DataAccess.entityManager;
import systems.tech247.hr.Employees;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//DisengagementEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "ProbationEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdreditors.ProbationEditorTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_ProbationEditorAction",
//        preferredID = "ProbationEditorTopComponent"
//)
@Messages({
    "CTL_ProbationEditorAction=Probation",
    "CTL_ProbationEditorTopComponent=Probation",
    "HINT_ProbationEditorTopComponent="
})
public final class ProbationEditorTopComponent extends TopComponent{
    
    
    
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
    
    
    
    
    public ProbationEditorTopComponent(){
        
    }
    
    
    
    
    public ProbationEditorTopComponent(Employees emp) {
        initComponents();
        setName(Bundle.CTL_ProbationEditorTopComponent()+"->"+emp.getSurName()+" "+emp.getOtherNames());
        setToolTipText(Bundle.HINT_ProbationEditorTopComponent());
        associateLookup(new AbstractLookup(ic));
        this.updateable = entityManager.find(Employees.class, emp.getEmployeeID());
        
        
        fillTheFields();
        
        
        
        
        
        
    
        
        
        
        
        
        
        
        
        
        
        jdcProbationStart.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getSource()==jdcProbationStart && "date".equals(evt.getPropertyName())){
                    from = jdcProbationStart.getDate();
                    try{
                        updateable.setProbationStartDate(from);
                    }catch(NullPointerException ex){
                        
                    }
                    modify();
                }
            }
        });
        
        jdcConfirmationDate.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getSource()==jdcConfirmationDate && evt.getPropertyName()=="date"){
                    to = jdcConfirmationDate.getDate();
                    updateable.setConfirmationDate(to);
                    modify();
                }
            }
        });
        
        jftPeriod.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try{
                    BigDecimal period = new BigDecimal(jftPeriod.getText());
                    updateable.setProbationPeriod(period);
                    modify();
                }catch(Exception ex){
                    
                }
            }
        });
        
        
       
            
        
            
    }
    
    void fillTheFields(){
        
            try{
                //get the original values
                
                
                jcbIsOnProb.setSelected(updateable.getIsOnProbation());
                jftPeriod.setValue(updateable.getProbationPeriod());
                jdcConfirmationDate.setDate(updateable.getConfirmationDate());
                
                
                jdcProbationStart.setDate(updateable.getProbationStartDate());
                
            }catch(NullPointerException ex){
                
            }
        
        
       
       

    }


    
    
    
    private class EmploymentSavable extends AbstractSavable{
        
        EmploymentSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            return "Probation For " +updateable.getSurName()+" "+updateable.getOtherNames();
        }
        
        ProbationEditorTopComponent tc(){
            return ProbationEditorTopComponent.this;
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

                if(updateable.getProbationStartDate()==null){
                    StatusDisplayer.getDefault().setStatusText("Specify The Start Date");
                }else if(updateable.getProbationPeriod()==null){
                    StatusDisplayer.getDefault().setStatusText("Specify The Probation Period");
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

        jLabel8 = new javax.swing.JLabel();
        jdcProbationStart = new com.toedter.calendar.JDateChooser();
        jcbIsOnProb = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jdcConfirmationDate = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jftPeriod = new javax.swing.JFormattedTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(ProbationEditorTopComponent.class, "ProbationEditorTopComponent.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbIsOnProb, org.openide.util.NbBundle.getMessage(ProbationEditorTopComponent.class, "ProbationEditorTopComponent.jcbIsOnProb.text")); // NOI18N
        jcbIsOnProb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbIsOnProbActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(ProbationEditorTopComponent.class, "ProbationEditorTopComponent.jLabel10.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ProbationEditorTopComponent.class, "ProbationEditorTopComponent.jLabel1.text")); // NOI18N

        jftPeriod.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jftPeriod.setText(org.openide.util.NbBundle.getMessage(ProbationEditorTopComponent.class, "ProbationEditorTopComponent.jftPeriod.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jftPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcProbationStart, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcConfirmationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jcbIsOnProb)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jdcConfirmationDate, jdcProbationStart});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbIsOnProb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jdcProbationStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jftPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jdcConfirmationDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbIsOnProbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbIsOnProbActionPerformed
        updateable.setIsOnProbation(jcbIsOnProb.isSelected());
        modify();
    }//GEN-LAST:event_jcbIsOnProbActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JCheckBox jcbIsOnProb;
    private com.toedter.calendar.JDateChooser jdcConfirmationDate;
    private com.toedter.calendar.JDateChooser jdcProbationStart;
    private javax.swing.JFormattedTextField jftPeriod;
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
