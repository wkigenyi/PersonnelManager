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
import systems.tech247.hr.Nationalities;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//PositionEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmployeePersonalInfoEditorTopComponent",
        iconBase="systems/tech247/util/icons/nation.png", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)



@Messages({
    
    "CTL_NationalityEditorTopComponent=New Nationality",
    "HINT_NationalityEditorTopComponent=Nationality Editor"
})
public final class NationalityEditorTopComponent extends TopComponent{
    
    Nationalities emp;
    
    DataAccess da = new DataAccess();
    InstanceContent ic = new InstanceContent();
    
    
    //Updatables
    String pname = "";
    
    
    
    
    EntityManager entityManager = DataAccess.getEntityManager();
    
    Nationalities updatable;
    
    public NationalityEditorTopComponent(){
        this(null);
    }

    public NationalityEditorTopComponent(Nationalities e) {
        initComponents();
        setName(Bundle.CTL_NationalityEditorTopComponent());
        setToolTipText(Bundle.HINT_NationalityEditorTopComponent());
        
        emp = e;
        
        if(null!=e){
            updatable = entityManager.find(Nationalities.class, e.getNationalityID());
        }
        
        
        //Fill in the employee details
        if(null!=e){
            fillCategory(e);
        }
        
        
        
        
        
        
        
        
        
        
        jtNationality.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
                if(pname!=jtNationality.getText()){
                        pname = jtNationality.getText();
                        try{
                            updatable.setNationality(pname);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                
                
                   if(pname!=jtNationality.getText()){
                        pname = jtNationality.getText();
                        try{
                            updatable.setNationality(pname);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
                    
                
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
                
                
                    if(pname!=jtNationality.getText()){
                        pname = jtNationality.getText();
                        try{
                            updatable.setNationality(pname);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
                    
               
                    
                
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
                return "New Nationality";
            }else{
                return emp.getNationality();
            }
        }
        
        NationalityEditorTopComponent tc(){
            return NationalityEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==emp){
                
                        String insertSQL = "INSERT INTO [dbo].[Nationalities]\n" +
"           ([Nationality])\n" +
"     VALUES\n" +
"           (?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, pname);
             
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
        
        
    
            if(pname.length()<1){
                StatusDisplayer.getDefault().setStatusText("Proper Nationality is Required");
            }else{
                if(getLookup().lookup(CategorySavable.class)==null){
                            ic.add(new CategorySavable());
                }
            }
        
        
            
        
    }
    
    void fillCategory(Nationalities e){
        try{
                setName(e.getNationality());
                
                
                
                pname = e.getNationality();
                jtNationality.setText(pname );
                
                
              
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
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
        jtNationality = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(NationalityEditorTopComponent.class, "NationalityEditorTopComponent.jLabel1.text")); // NOI18N

        jtNationality.setText(org.openide.util.NbBundle.getMessage(NationalityEditorTopComponent.class, "NationalityEditorTopComponent.jtNationality.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(NationalityEditorTopComponent.class, "NationalityEditorTopComponent.jLabel6.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(103, 103, 103)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jtNationality;
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
    
    void resetFields(){
        jtNationality.setText("");
    }

    
}
