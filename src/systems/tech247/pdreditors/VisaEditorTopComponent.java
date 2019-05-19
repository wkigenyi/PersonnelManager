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
import systems.tech247.hr.Currencies;
import systems.tech247.hr.Employees;
import systems.tech247.hr.Visa;
import systems.tech247.pdr.UtilityPDR;
import systems.tech247.pdr.NodeVisaRefreshEvent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//VisaEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "VisaEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdreditors.VisaEditorTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_VisaEditorAction",
//        preferredID = "VisaEditorTopComponent"
//)
@Messages({
    "CTL_VisaEditorAction=Visa Editor",
    "CTL_VisaEditorTopComponent=Visa Editor",
    "HINT_VisaEditorTopComponent=This is a Contract Editor window"
})
public final class VisaEditorTopComponent extends TopComponent implements LookupListener {
    
    //Lookup for the Currency
    Lookup.Result<Currencies> rslt = WindowManager.getDefault().findTopComponent("CurrenciesTopComponent").getLookup().lookupResult(Currencies.class);   
    
    
    
    
    
    Visa application;
    Employees emp;
    InstanceContent ic = new InstanceContent();
    
    //The updateable
    Visa updateable;
    
    //Contract Type Details
    long currencyID =1;
    
    //Currencies 
    Currencies currency;
    //Permit
    String permit ="";

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
    //Receipt
    String receipt="";
    //ref
    String ref = "";
    
    public VisaEditorTopComponent(){
        this(null);
    }
    
    public VisaEditorTopComponent(Employees employee){
        this(null,employee);
        
    }
    
    
    public VisaEditorTopComponent(Visa contact,Employees emp) {
        initComponents();
        setName(Bundle.CTL_VisaEditorTopComponent());
        setToolTipText(Bundle.HINT_VisaEditorTopComponent());
        associateLookup(new AbstractLookup(ic));
        this.emp = emp;
        this.application = contact;
        if(null!=application){
            updateable = DataAccess.getEntityManager().find(Visa.class, contact.getId());
            
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
        
        jdcTo.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if("date".equals(evt.getPropertyName()) && evt.getSource()==jdcTo){
                    to = jdcTo.getDate();
                    try{
                        
                        updateable.setCTo(to);
                        
                    }catch(NullPointerException ex){
                        
                    }
                    modify();
                }
            }
        });
        jtReceiptNumber.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                receipt = jtReceiptNumber.getText();
                try{
                    updateable.setRcptNo(receipt);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                receipt = jtReceiptNumber.getText();
                try{
                    updateable.setRcptNo(receipt);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                receipt = jtReceiptNumber.getText();
                try{
                    updateable.setRcptNo(receipt);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        jtClass.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                classString = jtClass.getText();
                try{
                    updateable.setClass1(classString);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                 classString = jtClass.getText();
                try{
                    updateable.setClass1(classString);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                classString = jtClass.getText();
                try{
                    updateable.setClass1(classString);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        jtPermitNumber.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                permit = jtPermitNumber.getText();
                try{
                    updateable.setPermit(permit);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                permit = jtPermitNumber.getText();
                try{
                    updateable.setPermit(permit);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                permit = jtPermitNumber.getText();
                try{
                    updateable.setPermit(permit);
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
                    updateable.setAmount(amount);
                }catch(Exception ex){
                    
                }
                modify();
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String stringAmount = jftAmount.getText();
                    
                try{
                    amount = new BigDecimal(stringAmount);
                    updateable.setAmount(amount);
                }catch(Exception ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String stringAmount = jftAmount.getText();
                    
                try{
                    amount = new BigDecimal(stringAmount);
                    updateable.setAmount(amount);
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
                jtClass.setText(application.getClass1());
                jtCurrency.setText(application.getCurrencyId().getCurrencyName());
                jftAmount.setValue(application.getAmount());
                jtPermitNumber.setText(application.getPermit());
                jdcFrom.setDate(application.getCFrom());
                jdcTo.setDate(application.getCTo());
                jtReceiptNumber.setText(application.getRcptNo());
                
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
                return "New Visa";
            }else if(emp!=null){
                return "Visa For" + emp.getSurName()+" "+emp.getOtherNames();
            }else{
                return "Visa";
            }
        }
        
        VisaEditorTopComponent tc(){
            return VisaEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==application){
               
                
                
                        String insertSQL = "INSERT INTO [dbo].[Visa]\n" +
"           ([EmpCode]\n" +
"           ,[Code]\n" +
"           ,[Permit]\n" +
"           ,[Class]\n" +
"           ,[Years]\n" +
"           ,[CFrom]\n" +
"           ,[CTo]\n" +
"           ,[Amount]\n" +
"           ,[RcptNo]\n" +
"           ,[Nationality]\n" +
"           ,[Title]\n" +
"           ,[employee_id]\n" +
"           ,[CurrencyId])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(4, classString);
            
            query.setParameter(3, permit);
            query.setParameter(6, from);
            query.setParameter(7, to);
            query.setParameter(8, amount);
            query.setParameter(9, receipt);
            query.setParameter(12, emp.getEmployeeID());
            query.setParameter(13, currencyID);
            
            
            
            

            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Crea
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            UtilityPDR.pdrIC.set(Arrays.asList(new NodeVisaRefreshEvent()), null);
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
        
        
    
            if(receipt.length()<3 && updateable==null){
                StatusDisplayer.getDefault().setStatusText("Proper Receipt No. is required");
            }else if(from==null || to==null){
                StatusDisplayer.getDefault().setStatusText("Specify The Dates");
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
        jtPermitNumber = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtClass = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jdcFrom = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jdcTo = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jftAmount = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jtCurrency = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtReceiptNumber = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(VisaEditorTopComponent.class, "VisaEditorTopComponent.jLabel2.text")); // NOI18N

        jtPermitNumber.setText(org.openide.util.NbBundle.getMessage(VisaEditorTopComponent.class, "VisaEditorTopComponent.jtPermitNumber.text")); // NOI18N
        jtPermitNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtPermitNumberKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(VisaEditorTopComponent.class, "VisaEditorTopComponent.jLabel7.text")); // NOI18N

        jtClass.setText(org.openide.util.NbBundle.getMessage(VisaEditorTopComponent.class, "VisaEditorTopComponent.jtClass.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(VisaEditorTopComponent.class, "VisaEditorTopComponent.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(VisaEditorTopComponent.class, "VisaEditorTopComponent.jLabel9.text")); // NOI18N

        jdcTo.setFocusable(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(VisaEditorTopComponent.class, "VisaEditorTopComponent.jLabel10.text")); // NOI18N

        jftAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jftAmount.setText(org.openide.util.NbBundle.getMessage(VisaEditorTopComponent.class, "VisaEditorTopComponent.jftAmount.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(VisaEditorTopComponent.class, "VisaEditorTopComponent.jLabel11.text")); // NOI18N

        jtCurrency.setText(org.openide.util.NbBundle.getMessage(VisaEditorTopComponent.class, "VisaEditorTopComponent.jtCurrency.text")); // NOI18N
        jtCurrency.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtCurrencyKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(VisaEditorTopComponent.class, "VisaEditorTopComponent.jLabel3.text")); // NOI18N

        jtReceiptNumber.setText(org.openide.util.NbBundle.getMessage(VisaEditorTopComponent.class, "VisaEditorTopComponent.jtReceiptNumber.text")); // NOI18N
        jtReceiptNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtReceiptNumberKeyPressed(evt);
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
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtPermitNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jtClass)
                    .addComponent(jdcFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdcTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jftAmount)
                    .addComponent(jtCurrency)
                    .addComponent(jtReceiptNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtPermitNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtReceiptNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jftAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jtCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtPermitNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtPermitNumberKeyPressed
        
    }//GEN-LAST:event_jtPermitNumberKeyPressed

    private void jtReceiptNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtReceiptNumberKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtReceiptNumberKeyPressed

    private void jtCurrencyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtCurrencyKeyPressed
        TopComponent tc = WindowManager.getDefault().findTopComponent("CurrenciesTopComponent");
        DialogDisplayer.getDefault().notify(new DialogDescriptor(tc, "Select A Currency"));
    }//GEN-LAST:event_jtCurrencyKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JDateChooser jdcFrom;
    private com.toedter.calendar.JDateChooser jdcTo;
    private javax.swing.JFormattedTextField jftAmount;
    private javax.swing.JTextField jtClass;
    private javax.swing.JTextField jtCurrency;
    private javax.swing.JTextField jtPermitNumber;
    private javax.swing.JTextField jtReceiptNumber;
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
