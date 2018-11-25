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
        
        
        
        
        
        
        
        
        jtEmail.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
               
                
                    
                    
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
              
                        String email = jtEmail.getText();
                        try{
                        updatable.setEMail(email);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
                    
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String email = jtEmail.getText();
                        try{
                        updatable.setEMail(email);
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
                
                
                
                
                
                
                
                
                jtFax.setText(e.getFax());
                
                jtEmail.setText(e.getEMail());
                
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
        jtNSSFNo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtNHIFNo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtPhysicalAddress = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtPostalAddress = new javax.swing.JTextField();
        jtTelephone1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtTelephone2 = new javax.swing.JTextField();
        jtTelephone3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtEmail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jtWeb = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jtFax = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel1.text")); // NOI18N

        jtDepartmentName.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtDepartmentName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel2.text")); // NOI18N

        jtDepartmentCode.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtDepartmentCode.text")); // NOI18N

        jtNSSFNo.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtNSSFNo.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel3.text")); // NOI18N

        jtNHIFNo.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtNHIFNo.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel5.text")); // NOI18N

        jtPhysicalAddress.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtPhysicalAddress.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel7.text")); // NOI18N

        jtPostalAddress.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtPostalAddress.text")); // NOI18N

        jtTelephone1.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtTelephone1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel9.text")); // NOI18N

        jtTelephone2.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtTelephone2.text")); // NOI18N

        jtTelephone3.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtTelephone3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel10.text")); // NOI18N

        jtEmail.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtEmail.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel11.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel13.text")); // NOI18N

        jtWeb.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtWeb.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jLabel14.text")); // NOI18N

        jtFax.setText(org.openide.util.NbBundle.getMessage(OUEditorTopComponent.class, "OUEditorTopComponent.jtFax.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtDepartmentName)
                    .addComponent(jtDepartmentCode, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtNSSFNo, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtNHIFNo, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtPhysicalAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtPostalAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtTelephone1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtTelephone2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtTelephone3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtWeb, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtFax, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
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
                    .addComponent(jtNSSFNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtNHIFNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtPhysicalAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtPostalAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jtTelephone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtTelephone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtTelephone3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jtWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jtDepartmentCode;
    private javax.swing.JTextField jtDepartmentName;
    private javax.swing.JTextField jtEmail;
    private javax.swing.JTextField jtFax;
    private javax.swing.JTextField jtNHIFNo;
    private javax.swing.JTextField jtNSSFNo;
    private javax.swing.JTextField jtPhysicalAddress;
    private javax.swing.JTextField jtPostalAddress;
    private javax.swing.JTextField jtTelephone1;
    private javax.swing.JTextField jtTelephone2;
    private javax.swing.JTextField jtTelephone3;
    private javax.swing.JTextField jtWeb;
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
