/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Arrays;
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
import systems.tech247.hr.Countries;
import systems.tech247.hr.Employees;
import systems.tech247.hr.Locations;
import systems.tech247.pdr.UtilityPDR;
import systems.tech247.pdr.NodeContactRefreshEvent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//LocationEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "LocationEditorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdreditors.LocationEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_LocationEditorAction",
        preferredID = "LocationEditorTopComponent"
)
@Messages({
    "CTL_LocationEditorAction=Location Editor",
    "CTL_LocationEditorTopComponent=Location Editor",
    "HINT_LocationEditorTopComponent=This is a Location Editor window"
})
public final class LocationEditorTopComponent extends TopComponent implements LookupListener {
    
    
    
    //Lookup for the Contact Type
    Lookup.Result<Countries> rslt = WindowManager.getDefault().findTopComponent("CountryTopComponent").getLookup().lookupResult(Countries.class);
    
    
    
    
    Employees emp;
    InstanceContent ic = new InstanceContent();
    
    //The updateable
    Locations updateable;
    
    //Contact Details
    Countries country = null;
    
    

    //Contact
    String location = "";
    
    //Comment
    String locationCode = null;
    
    
    
    
    public LocationEditorTopComponent(){
        this(null);
    }
    

    
    
    public LocationEditorTopComponent(Locations loc) {
        initComponents();
        setName(Bundle.CTL_LocationEditorTopComponent());
        setToolTipText(Bundle.HINT_LocationEditorTopComponent());
        associateLookup(new AbstractLookup(ic));
        
        
        if(null != loc){
            this.updateable = DataAccess.getEntityManager().find(Locations.class, loc.getLocationID());
            
        }
        fillTheFields(loc);
        
        jtCountry.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TopComponent tc = WindowManager.getDefault().findTopComponent("CountryTopComponent");
                DialogDisplayer.getDefault().notify(new DialogDescriptor(tc, "Select A Country"));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        
        jtLocation.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                location = jtLocation.getText();
                try{
                    updateable.setLocationName(location);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                location = jtLocation.getText();
                try{
                    updateable.setLocationName(location);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                location = jtLocation.getText();
                try{
                    updateable.setLocationName(location);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        
        jtLocationCode.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                locationCode = jtLocationCode.getText();
                try{
                    updateable.setLocationCode(locationCode);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                locationCode = jtLocationCode.getText();
                try{
                    updateable.setLocationCode(locationCode);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                locationCode = jtLocationCode.getText();
                try{
                    updateable.setLocationCode(locationCode);
                }catch(NullPointerException ex){
                    
                }
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
       
            
        
            
    }
    
    void fillTheFields(Locations loc){
        if(loc!=null){
            try{
                //get the original values
                country = loc.getCountryID();
                
                location = loc.getLocationName();
                
                locationCode = loc.getLocationCode();
                
                jtLocation.setText(loc.getLocationName());
                jtCountry.setText(loc.getCountryID().getCountryName());
                jtLocationCode.setText(loc.getLocationCode());
            }catch(NullPointerException ex){
                
            }
        }
        
       
       

    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result<Countries> rslt = (Lookup.Result<Countries>)le.getSource();
        for(Countries c : rslt.allInstances()){
            jtCountry.setText(c.getCountryName());
            country = c;
            try{
                updateable.setCountryID(country);
            }catch(NullPointerException ex){
                
            }
            
            modify();
        }
    }
    
    
    
    private class LocationSavable extends AbstractSavable{
        
        LocationSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            return "Location";
        }
        
        LocationEditorTopComponent tc(){
            return LocationEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==updateable){
               
                
                
                        String insertSQL = "INSERT INTO \n" +
"           [dbo].[Locations]\n" +
"           ([CountryID]\n" +
"           ,[LocationCode]\n" +
"           ,[LocationName]\n" +
"           ,[Deleted])\n" +
"     VALUES\n" +
"           (?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(4, 0);
            query.setParameter(2, locationCode);
            query.setParameter(3, location);
            query.setParameter(1, country.getCountryID());
            
            
            
            

            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Crea
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            UtilityPDR.pdrIC.set(Arrays.asList(new NodeContactRefreshEvent()), null);
        
            
            
            
            this.tc().close();
            
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof LocationSavable){
                LocationSavable e = (LocationSavable)o;
                return tc() == e.tc();
            }
            return false;        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
    
    public void modify(){
        
        
    
            if(country==null && updateable==null){
                StatusDisplayer.getDefault().setStatusText("Select A Country");
            }else if(location.length()<2 && updateable==null){
                StatusDisplayer.getDefault().setStatusText("A proper contact is needed");
            }else{
                //Are there changes
                
                
                if(getLookup().lookup(LocationSavable.class)==null){
                            ic.add(new LocationSavable());
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
        jtLocation = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtCountry = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtLocationCode = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(LocationEditorTopComponent.class, "LocationEditorTopComponent.jLabel2.text")); // NOI18N

        jtLocation.setText(org.openide.util.NbBundle.getMessage(LocationEditorTopComponent.class, "LocationEditorTopComponent.jtLocation.text")); // NOI18N
        jtLocation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtLocationKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(LocationEditorTopComponent.class, "LocationEditorTopComponent.jLabel7.text")); // NOI18N

        jtCountry.setText(org.openide.util.NbBundle.getMessage(LocationEditorTopComponent.class, "LocationEditorTopComponent.jtCountry.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(LocationEditorTopComponent.class, "LocationEditorTopComponent.jLabel8.text")); // NOI18N

        jtLocationCode.setText(org.openide.util.NbBundle.getMessage(LocationEditorTopComponent.class, "LocationEditorTopComponent.jtLocationCode.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jtCountry)
                    .addComponent(jtLocationCode))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jtLocationCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtLocationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtLocationKeyPressed

    }//GEN-LAST:event_jtLocationKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jtCountry;
    private javax.swing.JTextField jtLocation;
    private javax.swing.JTextField jtLocationCode;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        rslt.addLookupListener(this);
        resultChanged(new LookupEvent(rslt));
    }

    @Override
    public void componentClosed() {
        rslt.removeLookupListener(this);
    
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
