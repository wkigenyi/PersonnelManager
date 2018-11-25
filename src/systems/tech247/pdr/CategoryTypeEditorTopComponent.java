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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.awt.StatusDisplayer;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.CSSSCategories;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//CategoryTypeEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "CategoryTypeEditorTopComponent",
        iconBase="systems/tech247/util/icons/EmpCategory.png", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)



@Messages({
    
    "CTL_CategoryTypeEditorTopComponent=New Category Type",
    "HINT_CategoryTypeEditorTopComponent=Category Type Editor"
})
public final class CategoryTypeEditorTopComponent extends TopComponent{
    
    CSSSCategories emp;
    
    DataAccess da = new DataAccess();
    InstanceContent ic = new InstanceContent();
    
    
    //Updatables
    String pname = "";
    String pdetails = "";
    BigDecimal defaultRenu  = BigDecimal.ZERO;
    BigDecimal casualDaily = BigDecimal.ZERO;
    boolean showInOrganogram = false;
    String positionDetails = "";
    short catLevel = 0;
    
    String defa = "";
    String casual ="";
    
    
    
    
    
    CSSSCategories updatable;
    
    public CategoryTypeEditorTopComponent(){
        this(null);
    }

    public CategoryTypeEditorTopComponent(CSSSCategories e) {
        initComponents();
        setName(Bundle.CTL_CategoryTypeEditorTopComponent());
        setToolTipText(Bundle.HINT_CategoryTypeEditorTopComponent());
        
        emp = e;
        
        if(null!=e){
            updatable = DataAccess.entityManager.find(CSSSCategories.class, e.getCSSSCategoryID());
        }
        
        
        //Fill in the employee details
        if(null!=e){
            fillCategory(e);
        }
        
        
        
        jtCategoryName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(pname!=jtCategoryName.getText()){
                        pname = jtCategoryName.getText();
                        try{
                            updatable.setCSSSCategoryName(pname);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(pname!=jtCategoryName.getText()){
                        pname = jtCategoryName.getText();
                        try{
                            updatable.setCSSSCategoryName(pname);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(pname!=jtCategoryName.getText()){
                        pname = jtCategoryName.getText();
                        try{
                            updatable.setCSSSCategoryName(pname);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
            }
        });
        
        
        
        
        
        
        
        
        jtaCategoryDetails.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                positionDetails = jtaCategoryDetails.getText();
                        
                        try{
                            updatable.setDetails(positionDetails);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                positionDetails = jtaCategoryDetails.getText();
                        
                        try{
                            updatable.setDetails(positionDetails);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                positionDetails = jtaCategoryDetails.getText();
                        
                        try{
                            updatable.setDetails(positionDetails);
                        }catch(Exception ex){
                            
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
            return "Company Structure";
        }
        
        CategoryTypeEditorTopComponent tc(){
            return CategoryTypeEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==emp){
                
                        String insertSQL = "INSERT INTO [dbo].[CSSSCategories]\n" +
"           ([CSSSCategoryName]\n" +
"           ,[Details]\n" +
"           ,[Deleted])\n"+                                
"     VALUES\n" +
"           (?,?,?)";
            Query query = DataAccess.entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, pname);
            query.setParameter(2, positionDetails);
            
            query.setParameter(3, false);

            DataAccess.entityManager.getTransaction().begin();
            query.executeUpdate();
            DataAccess.entityManager.getTransaction().commit();
            //Load  The setup
            
            }else{
                //Creating a new Employee
                DataAccess.entityManager.getTransaction().begin();
                DataAccess.entityManager.getTransaction().commit();
              
                    
                
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
        
        
    
            if(pname.length()<1){
                StatusDisplayer.getDefault().setStatusText("Proper Category Name is Required");
            }else{
                if(getLookup().lookup(CategorySavable.class)==null){
                            ic.add(new CategorySavable());
                }
            }
        
        
            
        
    }
    
    void fillCategory(CSSSCategories e){
        try{
                setName(e.getCSSSCategoryName());
                
                
                
                pname = e.getCSSSCategoryName();
                jtCategoryName.setText(pname );
                
                
              
                
                
                
                
                
                
                
                positionDetails = e.getDetails();
                jtaCategoryDetails.setText(positionDetails);
                
                
                
                
                
                
                
                
                
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
        jtCategoryName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaCategoryDetails = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CategoryTypeEditorTopComponent.class, "CategoryTypeEditorTopComponent.jLabel1.text")); // NOI18N

        jtCategoryName.setText(org.openide.util.NbBundle.getMessage(CategoryTypeEditorTopComponent.class, "CategoryTypeEditorTopComponent.jtCategoryName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(CategoryTypeEditorTopComponent.class, "CategoryTypeEditorTopComponent.jLabel6.text")); // NOI18N

        jtaCategoryDetails.setColumns(20);
        jtaCategoryDetails.setRows(5);
        jScrollPane1.setViewportView(jtaCategoryDetails);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(CategoryTypeEditorTopComponent.class, "CategoryTypeEditorTopComponent.jLabel4.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jtCategoryName;
    private javax.swing.JTextArea jtaCategoryDetails;
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
