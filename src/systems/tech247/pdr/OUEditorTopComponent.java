/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Date;
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
import systems.tech247.hr.Nationalities;
import systems.tech247.hr.OrganizationUnits;
import systems.tech247.hr.Religions;
import systems.tech247.hr.Tribes;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//EmployeePersonalInfoEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmployeePersonalInfoEditorTopComponent",
        iconBase="systems/tech247/util/icons/department.png", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)



@Messages({
    "CTL_OUEditorAction=Department Editor",
    "CTL_OUEditorTopComponent=Department Editor",
    "HINT_OUEditorTopComponent= Department Editor"
})
public final class OUEditorTopComponent extends TopComponent{
    
    OrganizationUnits emp;
    
    //DataAccess da = new DataAccess();
    InstanceContent ic = new InstanceContent();
    
    int popup;
    //Updatables
    String departmentName = "";
    String departmentCode = "";
    Short genderID = null;
    Tribes tribeID = null;
    Short maritalStatusID =null;
    Nationalities nationalityID =null;
    Religions religionID = null;
    String passportNumber = null;
    String nationalID = null;
    String nssfNumber = null;
    Date dob = null;
    byte[] imageAsBytes = null;
    Boolean autoFilling = true;
    String tin = null;
    String fcode = "";
    
    
    EntityManager entityManager = DataAccess.getEntityManager();
    
    OrganizationUnits updatable;
    
    public OUEditorTopComponent(){
        this(null);
    }

    public OUEditorTopComponent(OrganizationUnits e) {
        initComponents();
        setName(Bundle.CTL_OUEditorTopComponent());
        setToolTipText(Bundle.HINT_OUEditorTopComponent());
       
        emp = e;
        
        if(null!=e){
            updatable = entityManager.find(OrganizationUnits.class, e.getOrganizationUnitID());
        }
        
        
        
        //Fill in the employee details
        if(null!=e){
            fillEmployee(e);
        }
        
        
        
        
        
        
        jtDepartmentCode.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
                
                
                    
                        departmentCode = jtDepartmentCode.getText();
                        try{
                            updatable.setOrganizationUnitCode(departmentCode);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    
                    
                
                    
                
                
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                departmentCode = jtDepartmentCode.getText();
                        try{
                            updatable.setOrganizationUnitCode(departmentCode);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
                departmentCode = jtDepartmentCode.getText();
                        try{
                            updatable.setOrganizationUnitCode(departmentCode);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }
        });
        
        jtDepartmentName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
                
                        departmentName = jtDepartmentName.getText();
                        try{
                            updatable.setOrganizationUnitName(departmentName);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                
                
                   departmentName = jtDepartmentName.getText();
                        try{
                            updatable.setOrganizationUnitName(departmentName);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    
                
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
                
                
                    departmentName = jtDepartmentName.getText();
                        try{
                            updatable.setOrganizationUnitName(departmentName);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    
               
                    
                
            }
        });
        
        jtFinancial.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fcode = jtFinancial.getText();
                try{
                    updatable.setLASCNo(fcode);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fcode = jtFinancial.getText();
                try{
                    updatable.setLASCNo(fcode);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fcode = jtFinancial.getText();
                try{
                    updatable.setLASCNo(fcode);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

    }
    
    private class CompanySavable extends AbstractSavable{
        
        CompanySavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            if(null==emp){
                return "New Department";
            }else{
                return emp.getOrganizationUnitName();
            }
        }
        
        OUEditorTopComponent tc(){
            return OUEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
                //When it is new
            if(emp == null){
                
                String sql ="INSERT INTO [dbo].[OrganizationUnits]\n" +
"           ([OrganizationUnitCode]\n" +
"           ,[OrganizationUnitName]\n" +
"           ,[Address]\n" +
"           ,[Telephone]\n" +
"           ,[EMail]\n" +
"           ,[Fax]\n" +
"           ,[PINNo]\n" +
"           ,[NHIFNo]\n" +
"           ,[NSSFNo]\n" +
"           ,[LASCNo]\n" +

"           ,[Comments]\n" +
"           ,[OrganizationUnitTypeID]\n" +                        
"           ,[OrganizationUnitFunction])\n" +
"     VALUES\n" +
"           ('"+departmentCode+"'" +
"           ,'"+departmentName+"'" +
"           ,''" +
"           ,''" +
"           ,''" +
"           ,''" +
"           ,''" +
"           ,''" +
"           ,''" +
"           ,''" +

"           ,''" +
"           ,13" +                        
"           ,'')";
                Query q = entityManager.createNativeQuery(sql);
                entityManager.getTransaction().begin();
                q.executeUpdate();
                entityManager.getTransaction().commit();
                
                resetFields();
                
            }else{
                //When Updating
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
            }    
                
            
            
            
                
                
                UtilityPDR.loadPDRSetup();
                    
                
            
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof CompanySavable){
                CompanySavable e = (CompanySavable)o;
                return tc() == e.tc();
            }
            return false;        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
    
    void resetFields(){
        jtDepartmentCode.setText("");
        departmentName = "";
        jtDepartmentName.setText("");
        departmentCode = "";
        jtDepartmentName.requestFocus();
        
    }
    
    public void modify(){
        
        
    
            if(departmentCode.length()<=1){
                StatusDisplayer.getDefault().setStatusText("Proper Department Name is Required");
            }else if(departmentName.length()<=1){
                    StatusDisplayer.getDefault().setStatusText("Proper Department Code is Required");
            }else{
                if(getLookup().lookup(CompanySavable.class)==null){
                            ic.add(new CompanySavable());
                }
            }
        
        
            
        
    }
    
    void fillEmployee(OrganizationUnits e){
        try{
                setName(e.getOrganizationUnitName());
                
                departmentName = e.getOrganizationUnitName();
                
                
                jtDepartmentName.setText(departmentName);
                departmentCode = e.getOrganizationUnitCode();
                jtDepartmentCode.setText(departmentCode);
                fcode = e.getLASCNo();
                jtFinancial.setText(fcode);
                
                
                
                
                
                
                
                
                
                
                //Set the photo   
             
                
                
                
                
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
        jtDepartmentName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtDepartmentCode = new javax.swing.JTextField();
        jtFinancial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel1.text")); // NOI18N

        jtDepartmentName.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtDepartmentName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel2.text")); // NOI18N

        jtDepartmentCode.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtDepartmentCode.text")); // NOI18N

        jtFinancial.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtFinancial.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel3.text")); // NOI18N

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtDepartmentName)
                    .addComponent(jtDepartmentCode, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtFinancial, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtDepartmentCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtFinancial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(247, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jtDepartmentCode;
    private javax.swing.JTextField jtDepartmentName;
    private javax.swing.JTextField jtFinancial;
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
