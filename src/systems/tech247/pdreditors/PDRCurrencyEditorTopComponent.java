/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
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
import static systems.tech247.dbaccess.DataAccess.entityManager;
import systems.tech247.hr.Currencies;
import systems.tech247.pdr.NodeRefreshCurrencyEvent;
import systems.tech247.pdr.UtilityPDR;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//PDRCurrencyEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "PDRCurrencyEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdreditors.PDRCurrencyEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_PDRCurrencyEditorAction",
        preferredID = "PDRCurrencyEditorTopComponent"
)
@Messages({
    "CTL_PDRCurrencyEditorAction=PDRCurrencyEditor",
    "CTL_PDRCurrencyEditorTopComponent=Currency Editor",
    "HINT_PDRCurrencyEditorTopComponent=This is a PDRCurrencyEditor window"
})
public final class PDRCurrencyEditorTopComponent extends TopComponent {
    
    String currencyString = "";
    String currencyCode = "";
    String symbol= "";
    BigDecimal conversion = null;
    Boolean isBaseCurrency = false;
    Boolean changed = false;
    String conversionString;
    Currencies updateable;
    InstanceContent ic = new InstanceContent();
    Lookup lookup = new AbstractLookup(ic);
    public PDRCurrencyEditorTopComponent(){
        this(null);
    }

    public PDRCurrencyEditorTopComponent(Currencies currency) {
        initComponents();
        setName(Bundle.CTL_PDRCurrencyEditorTopComponent());
        setToolTipText(Bundle.HINT_PDRCurrencyEditorTopComponent());
        associateLookup(lookup);
        try{
        updateable = entityManager.find(Currencies.class, currency.getCurrencyID());
        }catch(NullPointerException ex){
            
        }
        fillFields(currency);
        
        jtCurrencyName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                currencyString = jtCurrencyName.getText();
                try{
                    updateable.setCurrencyName(currencyString);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                currencyString = jtCurrencyName.getText();
                try{
                    updateable.setCurrencyName(currencyString);
                    
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                currencyString = jtCurrencyName.getText();
                try{
                    updateable.setCurrencyName(currencyString);
                    
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        jtCurrencyCode.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                currencyCode = jtCurrencyCode.getText();
                try{
                    updateable.setCurrencyCode(currencyCode);
                    
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                currencyCode = jtCurrencyCode.getText();
                try{
                    updateable.setCurrencyCode(currencyCode);
                    
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                currencyCode = jtCurrencyCode.getText();
                try{
                    updateable.setCurrencyCode(currencyCode);
                    
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        jftExchangeRate.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try{
                    conversion = new BigDecimal(jftExchangeRate.getText());
                    try{
                        updateable.setConversionRate(conversion);
                        
                    }catch(NullPointerException ex){
                        
                    }
                    modify();
                }catch(Exception ex){
                    
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try{
                    conversion = new BigDecimal(jftExchangeRate.getText());
                    try{
                        updateable.setConversionRate(conversion);
                        
                    }catch(NullPointerException ex){
                        
                    }
                    modify();
                }catch(Exception ex){
                    
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try{
                    conversion = new BigDecimal(jftExchangeRate.getText());
                    try{
                        updateable.setConversionRate(conversion);
                        
                    }catch(NullPointerException ex){
                        
                    }
                    modify();
                }catch(Exception ex){
                    
                }
            }
        });
        
        jcbBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isBaseCurrency = jcbBase.isSelected();
                try{
                    updateable.setIsBaseCurrency(isBaseCurrency);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtCurrencyName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtCurrencyCode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jftExchangeRate = new javax.swing.JFormattedTextField();
        jcbBase = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PDRCurrencyEditorTopComponent.class, "PDRCurrencyEditorTopComponent.jLabel1.text")); // NOI18N

        jtCurrencyName.setText(org.openide.util.NbBundle.getMessage(PDRCurrencyEditorTopComponent.class, "PDRCurrencyEditorTopComponent.jtCurrencyName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PDRCurrencyEditorTopComponent.class, "PDRCurrencyEditorTopComponent.jLabel2.text")); // NOI18N

        jtCurrencyCode.setText(org.openide.util.NbBundle.getMessage(PDRCurrencyEditorTopComponent.class, "PDRCurrencyEditorTopComponent.jtCurrencyCode.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PDRCurrencyEditorTopComponent.class, "PDRCurrencyEditorTopComponent.jLabel3.text")); // NOI18N

        jftExchangeRate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jftExchangeRate.setText(org.openide.util.NbBundle.getMessage(PDRCurrencyEditorTopComponent.class, "PDRCurrencyEditorTopComponent.jftExchangeRate.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbBase, org.openide.util.NbBundle.getMessage(PDRCurrencyEditorTopComponent.class, "PDRCurrencyEditorTopComponent.jcbBase.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbBase)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jtCurrencyName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtCurrencyCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jftExchangeRate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jftExchangeRate, jtCurrencyCode, jtCurrencyName});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtCurrencyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtCurrencyCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jftExchangeRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbBase)
                .addContainerGap(192, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JCheckBox jcbBase;
    private javax.swing.JFormattedTextField jftExchangeRate;
    private javax.swing.JTextField jtCurrencyCode;
    private javax.swing.JTextField jtCurrencyName;
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
    
    void fillFields(Currencies currency){
        if(currency != null){
            jtCurrencyName.setText(currency.getCurrencyName());
            jtCurrencyCode.setText(currency.getCurrencyCode());
            jftExchangeRate.setValue(currency.getConversionRate());
            jcbBase.setSelected(currency.getIsBaseCurrency());
        }
    }
    
    private class CurrencySavable extends AbstractSavable{
        
        CurrencySavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            return "Currency";
        }
        
        PDRCurrencyEditorTopComponent tc(){
            return PDRCurrencyEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==updateable){
String insertSQL = "INSERT INTO [dbo].[Currencies]\n" +
"           ([CurrencyCode]\n" +
"           ,[CurrencyName]\n" +
"           ,[CurrencySymbol]\n" +
"           ,[ConversionRate]\n" +
"           ,[IsBaseCurrency]\n" +
"           ,[Deleted]\n" +
"           ,[isSecondCur])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, currencyCode);
            query.setParameter(2, currencyString);
            //query.setParameter(3, symbol);
            query.setParameter(4, conversion);
            query.setParameter(5, isBaseCurrency);
            query.setParameter(6, false);
            query.setParameter(7, false);
            
            
            

            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Creating a new Employee
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            UtilityPDR.pdrIC.set(Arrays.asList(new NodeRefreshCurrencyEvent()), null);
            
            
            
            this.tc().close();
            
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof CurrencySavable){
                CurrencySavable e = (CurrencySavable)o;
                return tc() == e.tc();
            }
            return false;        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
    
    public void modify(){
        
        
    
            if(null==updateable){ //New Currency
            if(currencyString.length()<= 1){
                StatusDisplayer.getDefault().setStatusText("A proper Currency name is required");
            }else if(currencyCode.length()!=3){
                StatusDisplayer.getDefault().setStatusText("Currency Code Should be 3 letters");
            }else{
                if(getLookup().lookup(CurrencySavable.class)==null){
                            ic.add(new CurrencySavable());
                }else{
                    
                }
            }
        }else{
            if(updateable.getCurrencyName().length()<=1){
                StatusDisplayer.getDefault().setStatusText("A proper Currency name is required");
            }else if(updateable.getCurrencyCode().length()!=3){
                StatusDisplayer.getDefault().setStatusText("Code Must 3 Letters");
            }else{
                if(getLookup().lookup(CurrencySavable.class)==null){
                            ic.add(new CurrencySavable());
                }else{
                    StatusDisplayer.getDefault().setStatusText("Savable is not null");
                }
                
            }
        }
        
        
            
        
    }
}
