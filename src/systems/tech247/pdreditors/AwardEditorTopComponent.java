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
import java.util.Arrays;
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
import systems.tech247.hr.Awards;
import systems.tech247.hr.Currencies;
import systems.tech247.hr.Employees;
import systems.tech247.pdr.NodeRefreshAwardEvent;
import systems.tech247.pdr.UtilityPDR;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//AwardEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "AwardEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdreditors.AwardEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_AwardEditorAction",
        preferredID = "AwardEditorTopComponent"
)
@Messages({
    "CTL_AwardEditorAction=Award Editor",
    "CTL_AwardEditorTopComponent=Award Editor",
    "HINT_AwardEditorTopComponent=This is Award Editor window"
})
public final class AwardEditorTopComponent extends TopComponent implements LookupListener {
    
    //Lookup for the Currency
    Lookup.Result<Currencies> rslt = WindowManager.getDefault().findTopComponent("CurrenciesTopComponent").getLookup().lookupResult(Currencies.class);   
    
    
    
    
    
    Awards application;
    Employees emp;
    InstanceContent ic = new InstanceContent();
    
    //The updateable
    Awards updateable;
    
    //Contract Type Details
    long currencyID =1;
    
    //Currencies 
    Currencies currency;
    //Permit
    String award ="";

    //Contact
    String classString = "";
    
    //Comment
    String comment = null;
    
    //Money Amount
    BigDecimal amount;
    
    //From Date
    Date from;
    //To Date
    Date to;
    
    //ref
    String ref = "";
    
    public AwardEditorTopComponent(){
        this(null);
    }
    
    public AwardEditorTopComponent(Employees employee){
        this(null,employee);
        
    }
    
    
    public AwardEditorTopComponent(Awards contact,Employees emp) {
        initComponents();
        setName(Bundle.CTL_AwardEditorTopComponent());
        setToolTipText(Bundle.HINT_AwardEditorTopComponent());
        associateLookup(new AbstractLookup(ic));
        this.emp = emp;
        this.application = contact;
        if(null!=application){
            updateable = DataAccess.getEntityManager().find(Awards.class, contact.getId());
            
        }
        fillTheFields();
        
        
        
        
        
        
        
        
        
        
        

        
        
        
        
        
        jdcFrom.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if("date".equals(evt.getPropertyName()) && evt.getSource()==jdcFrom){
                    from = jdcFrom.getDate();
                    try{
                        
                        updateable.setCFrom(from);
                        
                    }catch(NullPointerException ex){
                        
                    }
                    modify();
                }
            }
        });
        
        
        jtComment.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                comment = jtComment.getText();
                try{
                    updateable.setComments(comment);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                comment = jtComment.getText();
                try{
                    updateable.setComments(comment);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                comment = jtComment.getText();
                try{
                    updateable.setComments(comment);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        
        
        jtAward.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                award = jtAward.getText();
                try{
                    updateable.setAward(award);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
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
            }
        });
        
        jftAmount.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String stringAmount = jftAmount.getText();
                    
                try{
                    amount = new BigDecimal(stringAmount);
                    updateable.setCAward(amount);
                }catch(Exception ex){
                    
                }
                modify();
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String stringAmount = jftAmount.getText();
                    
                try{
                    amount = new BigDecimal(stringAmount);
                    updateable.setCAward(amount);
                }catch(Exception ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String stringAmount = jftAmount.getText();
                    
                try{
                    amount = new BigDecimal(stringAmount);
                    updateable.setCAward(amount);
                }catch(Exception ex){
                    
                }
                modify();
            }
        });
        
        
        
       
        
            
        
            
    }
    
    void fillTheFields(){
        if(application!=null){
            try{
                //get the original values
                
                jtCurrency.setText(application.getCurrencyId().getCurrencyName());
                jftAmount.setValue(application.getCAward());
                jtAward.setText(application.getAward());
                jdcFrom.setDate(application.getCFrom());
               
                jtComment.setText(application.getComments());
                
            }catch(NullPointerException ex){
                
            }
        }
        
       
       

    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result<Currencies> rslt = (Lookup.Result<Currencies>)le.getSource();
        for(Currencies l : rslt.allInstances()){
            currency = l;
            jtCurrency.setText(currency.getCurrencyName());
            currencyID = l.getCurrencyID();
            
            try{
                updateable.setCurrencyId(currency);
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
                return "New Award";
            }else if(emp!=null){
                return "Award For" + emp.getSurName()+" "+emp.getOtherNames();
            }else{
                return "Award";
            }
        }
        
        AwardEditorTopComponent tc(){
            return AwardEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==application){
               
                
                
                        String insertSQL = "INSERT INTO [dbo].[awards]\n" +
"           ([EmpCode]\n" +
"           ,[Code]\n" +
"           ,[Course]\n" +
"           ,[CFrom]\n" +
"           ,[CTo]\n" +
"           ,[ELevel]\n" +
"           ,[Award]\n" +
"           ,[Comments]\n" +
"           ,[CAward]\n" +
"           ,[AType]\n" +
"           ,[employee_id]\n" +
"           ,[CurrencyId])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(4, from);
            
            
            
            query.setParameter(7, award);
            query.setParameter(8, comment);
            query.setParameter(9, amount);
            query.setParameter(11, emp.getEmployeeID());
            query.setParameter(12, currencyID);
            
            
            
            

            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Crea
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            UtilityPDR.pdrIC.set(Arrays.asList(new NodeRefreshAwardEvent()), null);
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
        
        
    
            if(award.length()<3 && updateable==null){
                StatusDisplayer.getDefault().setStatusText("Proper Award Name is required");
            }else if(from==null){
                StatusDisplayer.getDefault().setStatusText("Specify The Date");
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
        jtAward = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jdcFrom = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jftAmount = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jtCurrency = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtComment = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(AwardEditorTopComponent.class, "AwardEditorTopComponent.jLabel2.text")); // NOI18N

        jtAward.setText(org.openide.util.NbBundle.getMessage(AwardEditorTopComponent.class, "AwardEditorTopComponent.jtAward.text")); // NOI18N
        jtAward.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtAwardKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(AwardEditorTopComponent.class, "AwardEditorTopComponent.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(AwardEditorTopComponent.class, "AwardEditorTopComponent.jLabel10.text")); // NOI18N

        jftAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jftAmount.setText(org.openide.util.NbBundle.getMessage(AwardEditorTopComponent.class, "AwardEditorTopComponent.jftAmount.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(AwardEditorTopComponent.class, "AwardEditorTopComponent.jLabel11.text")); // NOI18N

        jtCurrency.setText(org.openide.util.NbBundle.getMessage(AwardEditorTopComponent.class, "AwardEditorTopComponent.jtCurrency.text")); // NOI18N
        jtCurrency.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtCurrencyKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(AwardEditorTopComponent.class, "AwardEditorTopComponent.jLabel3.text")); // NOI18N

        jtComment.setText(org.openide.util.NbBundle.getMessage(AwardEditorTopComponent.class, "AwardEditorTopComponent.jtComment.text")); // NOI18N
        jtComment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtCommentKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdcFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jftAmount)
                    .addComponent(jtCurrency)
                    .addComponent(jtComment, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jtAward))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtAward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jdcFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jftAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jtCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtAwardKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtAwardKeyPressed
        
    }//GEN-LAST:event_jtAwardKeyPressed

    private void jtCommentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtCommentKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtCommentKeyPressed

    private void jtCurrencyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtCurrencyKeyPressed
        TopComponent tc = WindowManager.getDefault().findTopComponent("CurrenciesTopComponent");
        DialogDisplayer.getDefault().notify(new DialogDescriptor(tc, "Select A Currency"));
    }//GEN-LAST:event_jtCurrencyKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private com.toedter.calendar.JDateChooser jdcFrom;
    private javax.swing.JFormattedTextField jftAmount;
    private javax.swing.JTextField jtAward;
    private javax.swing.JTextField jtComment;
    private javax.swing.JTextField jtCurrency;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        rslt.addLookupListener(this);
        resultChanged(new LookupEvent(rslt));
    }

    @Override
    public void componentClosed() {
        rslt.removeLookupListener(this);
        currencyID = 0;
        classString="";
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
