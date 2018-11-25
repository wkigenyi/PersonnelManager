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
import systems.tech247.hr.TblEmployeeType;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//EmpTypeEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmpTypeEditorTopComponent",
        iconBase="systems/tech247/util/icons/position.png", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)



@Messages({
    
    "CTL_EmpTypeEditorTopComponent=New Employee Type",
    "HINT_EmpTypeEditorTopComponent=Employee Type Editor"
})
public final class EmpTypeEditorTopComponent extends TopComponent{
    
    TblEmployeeType emp;
    
    DataAccess da = new DataAccess();
    InstanceContent ic = new InstanceContent();
    
    
    //Updatables
    String code = "";
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
    
    TblEmployeeType updatable;
    
    public EmpTypeEditorTopComponent(){
        this(null);
    }

    public EmpTypeEditorTopComponent(TblEmployeeType e) {
        initComponents();
        setName(Bundle.CTL_EmpTypeEditorTopComponent());
        setToolTipText(Bundle.HINT_EmpTypeEditorTopComponent());
        
        emp = e;
        
        if(null!=e){
            updatable = entityManager.find(TblEmployeeType.class, e.getEmpTypeID());
        }
        
        
        //Fill in the employee details
        if(null!=e){
            fillPosition(e);
        }
        
        
        
        
        
        
        
        
        
        
        jtTypeName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
                if(pname!=jtTypeName.getText()){
                        pname = jtTypeName.getText();
                        try{
                            updatable.setEmpTypeName(pname);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                
                
                   if(pname!=jtTypeName.getText()){
                        pname = jtTypeName.getText();
                        try{
                            updatable.setEmpTypeName(pname);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
                    
                
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
                
                
                    if(pname!=jtTypeName.getText()){
                        pname = jtTypeName.getText();
                        try{
                            updatable.setEmpTypeName(pname);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
                    
               
                    
                
            }
        });
        
        jtCode.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
                if(code!=jtCode.getText()){
                        code = jtCode.getText();
                        try{
                            updatable.setEmpTypeCode(code);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                
                
                   if(code!=jtCode.getText()){
                        code = jtCode.getText();
                        try{
                            updatable.setEmpTypeCode(code);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
                    
                
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
                
                
                    if(code!=jtCode.getText()){
                        code = jtCode.getText();
                        try{
                            updatable.setEmpTypeCode(code);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    }
                    
               
                    
                
            }
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
       
                
        
        
        

    }
    
    private class EmpTypeSavable extends AbstractSavable{
        
        EmpTypeSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            if(null==emp){
                return "Employee Type";
            }else{
                return emp.getEmpTypeName();
            }
        }
        
        EmpTypeEditorTopComponent tc(){
            return EmpTypeEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==emp){
                
                        String insertSQL = "INSERT INTO [dbo].[tblEmployeeType]\n" +
"           ([Company_ID]\n" +
"           ,[EmpType_Code]\n" +
"           ,[EmpType_Name])\n" +
"     VALUES\n" +
"           (?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, 1);
            query.setParameter(2, code);
            
            query.setParameter(3, pname);
            
            
            
            
            
            
            
            
            
            
            
            

            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Creating a new Employee
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            
            UtilityPDR.loadPDRSetup();
            tc().close();
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof EmpTypeSavable){
                EmpTypeSavable e = (EmpTypeSavable)o;
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
                if(getLookup().lookup(EmpTypeSavable.class)==null){
                            ic.add(new EmpTypeSavable());
                }
            }
        
        
            
        
    }
    
    void fillPosition(TblEmployeeType e){
        try{
                setName(e.getEmpTypeName());
                
                code = e.getEmpTypeCode();
                jtCode.setText(e.getEmpTypeCode());
                
                
                pname = e.getEmpTypeName();
                jtTypeName.setText( pname);
                
                
              
                
                
                
                
                
                
                
                
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
        jtTypeName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtCode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EmpTypeEditorTopComponent.class, "EmpTypeEditorTopComponent.jLabel1.text")); // NOI18N

        jtTypeName.setText(org.openide.util.NbBundle.getMessage(EmpTypeEditorTopComponent.class, "EmpTypeEditorTopComponent.jtTypeName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(EmpTypeEditorTopComponent.class, "EmpTypeEditorTopComponent.jLabel2.text")); // NOI18N

        jtCode.setText(org.openide.util.NbBundle.getMessage(EmpTypeEditorTopComponent.class, "EmpTypeEditorTopComponent.jtCode.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(EmpTypeEditorTopComponent.class, "EmpTypeEditorTopComponent.jLabel6.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(103, 103, 103)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtTypeName)
                    .addComponent(jtCode, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jtCode;
    private javax.swing.JTextField jtTypeName;
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
