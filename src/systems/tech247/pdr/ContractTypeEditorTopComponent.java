/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.awt.StatusDisplayer;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.PdContractTypes;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//ContractTypeEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "ContractTypeEditorTopComponent",
        iconBase="systems/tech247/util/icons/capex.png", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)



@Messages({
    
    "CTL_ContractTypeEditorTopComponent=New Contract Type",
    "HINT_ContractTypeEditorTopComponent=Contract Type Editor"
})
public final class ContractTypeEditorTopComponent extends TopComponent{
    
    PdContractTypes emp;
    
    DataAccess da = new DataAccess();
    InstanceContent ic = new InstanceContent();
    
    
    //Updatables
    String contractName = "";
    Boolean year = true;
    Boolean month = false;
    Boolean week = false;
    Boolean day = false;
    Long length  = Long.MIN_VALUE;
    
    
    
    
    
    EntityManager entityManager = DataAccess.getEntityManager();
    
    PdContractTypes updatable;
    
    public ContractTypeEditorTopComponent(){
        this(null);
    }

    public ContractTypeEditorTopComponent(PdContractTypes e) {
        initComponents();
        setName(Bundle.CTL_ContractTypeEditorTopComponent());
        setToolTipText(Bundle.HINT_ContractTypeEditorTopComponent());
        
        emp = e;
        
        jrbYear.setSelected(true);
        
        if(null!=e){
            updatable = entityManager.find(PdContractTypes.class, e.getId());
        }
        
        
        //Fill in the employee details
        if(null!=e){
            fillCategory(e);
        }
        
        
        jtContractName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                contractName = jtContractName.getText();
                try{
                    updatable.setDescription(contractName);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                contractName = jtContractName.getText();
                try{
                    updatable.setDescription(contractName);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                contractName = jtContractName.getText();
                try{
                    updatable.setDescription(contractName);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        jftLength.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                length = (Long)(jftLength.getValue());
                try{
                    updatable.setCorrespondingValue(length);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                length = (Long)(jftLength.getValue());
                try{
                    updatable.setCorrespondingValue(length);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                length = (Long)(jftLength.getValue());
                try{
                    updatable.setCorrespondingValue(length);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
       
                
        
        
        

    }
    
    private class CategorySavable extends AbstractSavable{
        
        CategorySavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            if(null==emp){
                return "New Contract Type";
            }else{
                return emp.getDescription();
            }
        }
        
        ContractTypeEditorTopComponent tc(){
            return ContractTypeEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==emp){
                
                        String insertSQL = "INSERT INTO [dbo].[pdContractTypes]\n" +
"           ([Code]\n" +
"           ,[Description]\n" +
"           ,[InDays]\n" +
"           ,[InWeeks]\n" +
"           ,[InMonths]\n" +
"           ,[InYears]\n" +
"           ,[CorrespondingValue]\n" +
"           ,[ContractWorkingDays]\n" +
"           ,[ChargeToOverTime]\n" +
"           ,[OverTimeRate])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            
            query.setParameter(2, contractName);
            
            query.setParameter(3, day);
            query.setParameter(4, week);
            query.setParameter(5, month);
            query.setParameter(6, year);
            query.setParameter(7, length);
            query.setParameter(8, "");
            query.setParameter(9, Boolean.FALSE);
            query.setParameter(10, 0);
            

            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            //Reload
                UtilityPDR.pdrIC.add(new NodeContractTypesRefreshEvent());
                tc().close();
            }else{
                //Creating a new Employee
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                UtilityPDR.pdrIC.add(new NodeContractTypesRefreshEvent());
                tc().close();
                
            }
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof CategorySavable){
                CategorySavable e = (CategorySavable)o;
                return tc() == e.tc();
            }
            return false;        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
    
    public void modify(){
        
        
    
            if(contractName.length()<6){
                StatusDisplayer.getDefault().setStatusText("A Proper Contract Name is required, Atleast 6 Letters");
            }   
            else{
                if(getLookup().lookup(CategorySavable.class)==null){
                            ic.add(new CategorySavable());
                }
            }
        
        
            
        
    }
    
    void fillCategory(PdContractTypes e){
        try{
                setName(e.getDescription());
                
                
                
                
                jtContractName.setText(e.getDescription() );
                jftLength.setValue(e.getCorrespondingValue());
                jrbDays.setSelected(e.getInDays());
                jrbWeeks.setSelected(e.getInWeeks());
                jrbMonth.setSelected(e.getInMonths());
                jrbYear.setSelected(e.getInYears());
                
                
              
                
                
                
                
                
                
                

                
                
                
                
                
                
                
                
                
        }catch(Exception ex){
            
        }
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupPeriodType = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jtContractName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jrbYear = new javax.swing.JRadioButton();
        jrbMonth = new javax.swing.JRadioButton();
        jrbWeeks = new javax.swing.JRadioButton();
        jrbDays = new javax.swing.JRadioButton();
        jftLength = new javax.swing.JFormattedTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ContractTypeEditorTopComponent.class, "ContractTypeEditorTopComponent.jLabel1.text")); // NOI18N

        jtContractName.setText(org.openide.util.NbBundle.getMessage(ContractTypeEditorTopComponent.class, "ContractTypeEditorTopComponent.jtContractName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(ContractTypeEditorTopComponent.class, "ContractTypeEditorTopComponent.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ContractTypeEditorTopComponent.class, "ContractTypeEditorTopComponent.jLabel2.text")); // NOI18N

        buttonGroupPeriodType.add(jrbYear);
        org.openide.awt.Mnemonics.setLocalizedText(jrbYear, org.openide.util.NbBundle.getMessage(ContractTypeEditorTopComponent.class, "ContractTypeEditorTopComponent.jrbYear.text")); // NOI18N
        jrbYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbYearActionPerformed(evt);
            }
        });

        buttonGroupPeriodType.add(jrbMonth);
        org.openide.awt.Mnemonics.setLocalizedText(jrbMonth, org.openide.util.NbBundle.getMessage(ContractTypeEditorTopComponent.class, "ContractTypeEditorTopComponent.jrbMonth.text")); // NOI18N
        jrbMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMonthActionPerformed(evt);
            }
        });

        buttonGroupPeriodType.add(jrbWeeks);
        org.openide.awt.Mnemonics.setLocalizedText(jrbWeeks, org.openide.util.NbBundle.getMessage(ContractTypeEditorTopComponent.class, "ContractTypeEditorTopComponent.jrbWeeks.text")); // NOI18N
        jrbWeeks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbWeeksActionPerformed(evt);
            }
        });

        buttonGroupPeriodType.add(jrbDays);
        org.openide.awt.Mnemonics.setLocalizedText(jrbDays, org.openide.util.NbBundle.getMessage(ContractTypeEditorTopComponent.class, "ContractTypeEditorTopComponent.jrbDays.text")); // NOI18N
        jrbDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbDaysActionPerformed(evt);
            }
        });

        jftLength.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jftLength.setText(org.openide.util.NbBundle.getMessage(ContractTypeEditorTopComponent.class, "ContractTypeEditorTopComponent.jftLength.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtContractName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftLength, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbDays)
                    .addComponent(jrbWeeks)
                    .addComponent(jrbMonth)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jrbYear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addGap(189, 189, 189))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jftLength, jtContractName});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtContractName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jftLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jrbYear)
                .addGap(11, 11, 11)
                .addComponent(jrbMonth)
                .addGap(11, 11, 11)
                .addComponent(jrbWeeks)
                .addGap(11, 11, 11)
                .addComponent(jrbDays)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jrbYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbYearActionPerformed
        year = jrbYear.isSelected();
        day = jrbDays.isSelected();
        month = jrbMonth.isSelected();
        week = jrbWeeks.isSelected();
        try{
        updatable.setInYears(year);
        updatable.setInDays(day);
        updatable.setInMonths(month);
        updatable.setInWeeks(week);
        }catch(NullPointerException ex){
            
        }
        modify();
    }//GEN-LAST:event_jrbYearActionPerformed

    private void jrbMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbMonthActionPerformed
            year = jrbYear.isSelected();
        day = jrbDays.isSelected();
        month = jrbMonth.isSelected();
        week = jrbWeeks.isSelected();
        try{
        updatable.setInYears(year);
        updatable.setInDays(day);
        updatable.setInMonths(month);
        updatable.setInWeeks(week);
        }catch(NullPointerException ex){
            
        }
        modify();
    }//GEN-LAST:event_jrbMonthActionPerformed

    private void jrbWeeksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbWeeksActionPerformed
            year = jrbYear.isSelected();
        day = jrbDays.isSelected();
        month = jrbMonth.isSelected();
        week = jrbWeeks.isSelected();
        try{
        updatable.setInYears(year);
        updatable.setInDays(day);
        updatable.setInMonths(month);
        updatable.setInWeeks(week);
        }catch(NullPointerException ex){
            
        }
        modify();
    }//GEN-LAST:event_jrbWeeksActionPerformed

    private void jrbDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbDaysActionPerformed
            year = jrbYear.isSelected();
        day = jrbDays.isSelected();
        month = jrbMonth.isSelected();
        week = jrbWeeks.isSelected();
        try{
        updatable.setInYears(year);
        updatable.setInDays(day);
        updatable.setInMonths(month);
        updatable.setInWeeks(week);
        }catch(NullPointerException ex){
            
        }
        modify();
    }//GEN-LAST:event_jrbDaysActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupPeriodType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JFormattedTextField jftLength;
    private javax.swing.JRadioButton jrbDays;
    private javax.swing.JRadioButton jrbMonth;
    private javax.swing.JRadioButton jrbWeeks;
    private javax.swing.JRadioButton jrbYear;
    private javax.swing.JTextField jtContractName;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
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
