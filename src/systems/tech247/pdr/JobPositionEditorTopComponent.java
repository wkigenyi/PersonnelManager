/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.awt.StatusDisplayer;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.JobPositions;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//PositionEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmployeePersonalInfoEditorTopComponent",
        iconBase="systems/tech247/util/icons/position.png", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)



@Messages({
    
    "CTL_PositionEditorTopComponent=New Position",
    "HINT_PositionEditorTopComponent=Position Editor"
})
public final class JobPositionEditorTopComponent extends TopComponent{
    
    JobPositions emp;
    
    DataAccess da = new DataAccess();
    InstanceContent ic = new InstanceContent();
    
    
    //Updatables
    String pname = "";
    String pdetails = "";
    BigDecimal defaultRenu  = BigDecimal.ZERO;
    BigDecimal casualDaily = BigDecimal.ZERO;
    boolean showInOrganogram = false;
    String positionDetails = "";
    int maximnumInOrg = 0;
    
    String defa = "";
    String casual ="";
    
    
    
    EntityManager entityManager = DataAccess.getEntityManager();
    
    JobPositions updatable;
    
    public JobPositionEditorTopComponent(){
        this(null);
    }

    public JobPositionEditorTopComponent(JobPositions e) {
        initComponents();
        setName(Bundle.CTL_PositionEditorTopComponent());
        setToolTipText(Bundle.HINT_PositionEditorTopComponent());
        
        emp = e;
        
        if(null!=e){
            updatable = entityManager.find(JobPositions.class, e.getPositionID());
        }
        
        
        //Fill in the employee details
        if(null!=e){
            fillPosition(e);
        }
        
        jftDefaultRenumeration.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                defa = jftDefaultRenumeration.getText();
                try{
                    defaultRenu = new BigDecimal(defa);
                    updatable.setDefaultRemuneration(defaultRenu);
                    modify();
                }catch(Exception ex){
                    
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                defa = jftDefaultRenumeration.getText();
                try{
                    defaultRenu = new BigDecimal(defa);
                    updatable.setDefaultRemuneration(defaultRenu);
                    modify();
                }catch(Exception ex){
                    
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                defa = jftDefaultRenumeration.getText();
                try{
                    defaultRenu = new BigDecimal(defa);
                    updatable.setDefaultRemuneration(defaultRenu);
                    modify();
                }catch(Exception ex){
                    
                }
            }
        });
        
        jtMaximumInOrg.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                try{
                    maximnumInOrg = new Integer(jtMaximumInOrg.getText());
                    updatable.setMaxEmpsInOrganogram(maximnumInOrg);
                    modify();
                }catch(Exception ex){
                    
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                try{
                    maximnumInOrg = new Integer(jtMaximumInOrg.getText());
                    updatable.setMaxEmpsInOrganogram(maximnumInOrg);
                    modify();
                }catch(Exception ex){
                    
                } //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try{
                    maximnumInOrg = new Integer(jtMaximumInOrg.getText());
                    updatable.setMaxEmpsInOrganogram(maximnumInOrg);
                    modify();
                }catch(Exception ex){
                    
                }//To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jftDailyRates.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                casual = jftDailyRates.getText();
                try{
                    casualDaily = new BigDecimal(casual);
                    updatable.setCasualDailyRate(casualDaily);
                    modify();
                }catch(Exception ex){
                    
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                casual = jftDailyRates.getText();
                try{
                    casualDaily = new BigDecimal(casual);
                    updatable.setCasualDailyRate(casualDaily);
                    modify();
                }catch(Exception ex){
                    
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                casual = jftDailyRates.getText();
                try{
                    casualDaily = new BigDecimal(casual);
                    updatable.setCasualDailyRate(casualDaily);
                    modify();
                }catch(Exception ex){
                    
                }
                
            }
        });
        
        
        
        
        jtPositionName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
                if(pname!=jtPositionName.getText()){
                        pname = jtPositionName.getText();
                        try{
                            updatable.setPositionName(pname);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                
                
                   if(pname!=jtPositionName.getText()){
                        pname = jtPositionName.getText();
                        try{
                            updatable.setPositionName(pname);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
                    
                
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
                
                
                    if(pname!=jtPositionName.getText()){
                        pname = jtPositionName.getText();
                        try{
                            updatable.setPositionName(pname);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
                    
               
                    
                
            }
        });
        
        jtaPositionDetails.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
                
                    if(positionDetails!= jtaPositionDetails.getText()){
                        positionDetails = jtaPositionDetails.getText();
                        
                        try{
                            updatable.setPositionDetails(positionDetails);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
                    
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(positionDetails!= jtaPositionDetails.getText()){
                        positionDetails = jtaPositionDetails.getText();
                        
                        try{
                            updatable.setPositionDetails(positionDetails);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(positionDetails!= jtaPositionDetails.getText()){
                        positionDetails = jtaPositionDetails.getText();
                        
                        try{
                            updatable.setPositionDetails(positionDetails);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
            }
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
       
                
        
        
        

    }
    
    private class PositionSavable extends AbstractSavable{
        
        PositionSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            if(null==emp){
                return "New Postion";
            }else{
                return emp.getPositionName();
            }
        }
        
        JobPositionEditorTopComponent tc(){
            return JobPositionEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==emp){
                
                        String insertSQL = "INSERT INTO [dbo].[JobPositions]\n" +
"           ([PositionName]\n" +
"           ,[PositionDetails]\n" +
"           ,[DefaultRemuneration]\n" +
"           ,[ShowInOrganogram]\n" +
"           ,[MaxEmpsInOrganogram]\n" +
"           ,[CasualDailyRate])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, pname);
            query.setParameter(2, positionDetails);
            
            query.setParameter(3, defaultRenu);
            
            
            query.setParameter(4, showInOrganogram);
            
            
            query.setParameter(5, maximnumInOrg);
            
            
            query.setParameter(6, casualDaily);
            
            
            

            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Creating a new Employee
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            
            UtilityPDR.loadPDRSetup();
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof PositionSavable){
                PositionSavable e = (PositionSavable)o;
                return tc() == e.tc();
            }
            return false;        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
    
    public void modify(){
        
        
    
            if(pname.length()<=1){
                StatusDisplayer.getDefault().setStatusText("Proper Position Name is Required");
            }else{
                if(getLookup().lookup(PositionSavable.class)==null){
                            ic.add(new PositionSavable());
                }
            }
        
        
            
        
    }
    
    void fillPosition(JobPositions e){
        try{
                setName(e.getPositionName());
                jtPostionCode.setText(e.getPositionCode());
                
                
                pname = e.getPositionName();
                jtPositionName.setText( e.getPositionName());
                
                
              
                maximnumInOrg = e.getMaxEmpsInOrganogram();
                jtMaximumInOrg.setText(e.getMaxEmpsInOrganogram()+"");
                
                
                
                
                defaultRenu = e.getDefaultRemuneration();
                jftDefaultRenumeration.setValue(defaultRenu);
                
                casualDaily = e.getCasualDailyRate();
                jftDailyRates.setValue(e.getCasualDailyRate());
                
                positionDetails = e.getPositionDetails();
                jtaPositionDetails.setText(positionDetails);
                
                showInOrganogram = e.getShowInOrganogram();
                jcbShowInOrganogram.setSelected(showInOrganogram);
                
                
                
                
                
                
                
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

        jLabel1 = new javax.swing.JLabel();
        jtPositionName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtPostionCode = new javax.swing.JTextField();
        jtMaximumInOrg = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcbShowInOrganogram = new javax.swing.JCheckBox();
        jftDefaultRenumeration = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jftDailyRates = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaPositionDetails = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(JobPositionEditorTopComponent.class, "JobPositionEditorTopComponent.jLabel1.text")); // NOI18N

        jtPositionName.setText(org.openide.util.NbBundle.getMessage(JobPositionEditorTopComponent.class, "JobPositionEditorTopComponent.jtPositionName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(JobPositionEditorTopComponent.class, "JobPositionEditorTopComponent.jLabel2.text")); // NOI18N

        jtPostionCode.setEditable(false);
        jtPostionCode.setText(org.openide.util.NbBundle.getMessage(JobPositionEditorTopComponent.class, "JobPositionEditorTopComponent.jtPostionCode.text")); // NOI18N

        jtMaximumInOrg.setText(org.openide.util.NbBundle.getMessage(JobPositionEditorTopComponent.class, "JobPositionEditorTopComponent.jtMaximumInOrg.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(JobPositionEditorTopComponent.class, "JobPositionEditorTopComponent.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(JobPositionEditorTopComponent.class, "JobPositionEditorTopComponent.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(JobPositionEditorTopComponent.class, "JobPositionEditorTopComponent.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbShowInOrganogram, org.openide.util.NbBundle.getMessage(JobPositionEditorTopComponent.class, "JobPositionEditorTopComponent.jcbShowInOrganogram.text")); // NOI18N
        jcbShowInOrganogram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbShowInOrganogramActionPerformed(evt);
            }
        });

        jftDefaultRenumeration.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jftDefaultRenumeration.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jftDefaultRenumeration.setText(org.openide.util.NbBundle.getMessage(JobPositionEditorTopComponent.class, "JobPositionEditorTopComponent.jftDefaultRenumeration.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(JobPositionEditorTopComponent.class, "JobPositionEditorTopComponent.jLabel13.text")); // NOI18N

        jftDailyRates.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jftDailyRates.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jftDailyRates.setText(org.openide.util.NbBundle.getMessage(JobPositionEditorTopComponent.class, "JobPositionEditorTopComponent.jftDailyRates.text")); // NOI18N

        jtaPositionDetails.setColumns(20);
        jtaPositionDetails.setRows(5);
        jScrollPane1.setViewportView(jtaPositionDetails);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(JobPositionEditorTopComponent.class, "JobPositionEditorTopComponent.jLabel4.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(103, 103, 103))
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbShowInOrganogram)
                            .addComponent(jtPositionName)
                            .addComponent(jtPostionCode, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jtMaximumInOrg, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jftDefaultRenumeration)
                            .addComponent(jftDailyRates)))
                    .addComponent(jLabel4))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtPositionName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtPostionCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtMaximumInOrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jcbShowInOrganogram)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jftDefaultRenumeration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jftDailyRates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbShowInOrganogramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbShowInOrganogramActionPerformed
        showInOrganogram = jcbShowInOrganogram.isSelected();
        try{
            updatable.setShowInOrganogram(showInOrganogram);
        }catch(Exception ex){
            
        }
        modify();
    }//GEN-LAST:event_jcbShowInOrganogramActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jcbShowInOrganogram;
    private javax.swing.JFormattedTextField jftDailyRates;
    private javax.swing.JFormattedTextField jftDefaultRenumeration;
    private javax.swing.JTextField jtMaximumInOrg;
    private javax.swing.JTextField jtPositionName;
    private javax.swing.JTextField jtPostionCode;
    private javax.swing.JTextArea jtaPositionDetails;
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
