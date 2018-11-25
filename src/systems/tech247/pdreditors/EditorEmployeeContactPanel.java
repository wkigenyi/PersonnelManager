/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Ctypes;
import systems.tech247.hr.Employees;

import systems.tech247.hr.Nationalities;
import systems.tech247.hr.Religions;
import systems.tech247.hr.Tribes;
import systems.tech247.pdr.UtilityPDR;
import systems.tech247.util.Gender;
import systems.tech247.util.Marital;

/**
 *
 * @author Admin
 */
public class EditorEmployeeContactPanel extends javax.swing.JPanel implements LookupListener{

    Employees emp;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    DataAccess da = new DataAccess();
    Boolean edit =false;
    int popup;
    //Updatables
    String fname = null;
    String lname = null;
    Short genderID = null;
    Tribes tribeID = null;
    Short maritalStatusID =null;
    Nationalities nationalityID =null;
    Religions religionID = null;
    String passportNumber = null;
    String nationalID = null;
    String nssfNumber = null;
    Date dob = null;
    
    
    EntityManager entityManager = da.getEntityManager();
    TopComponent empTc = WindowManager.getDefault().findTopComponent("EmployeesTopComponent");
    Lookup.Result<Employees> empRslt = empTc.getLookup().lookupResult(Employees.class);
    TopComponent nationTc = WindowManager.getDefault().findTopComponent("NationsTopComponent");
    Lookup.Result<Nationalities> nationRslt = nationTc.getLookup().lookupResult(Nationalities.class);
    TopComponent tribeTc = WindowManager.getDefault().findTopComponent("TribesTopComponent");
    Lookup.Result<Tribes> tribeRslt = tribeTc.getLookup().lookupResult(Tribes.class);
    TopComponent genderTc = WindowManager.getDefault().findTopComponent("GenderTopComponent");
    Lookup.Result<Gender> genderRslt = genderTc.getLookup().lookupResult(Gender.class);
    TopComponent maritalTc = WindowManager.getDefault().findTopComponent("MaritalTopComponent");
    Lookup.Result<Marital> maritalRslt = maritalTc.getLookup().lookupResult(Marital.class);
    TopComponent contacttypeTc = WindowManager.getDefault().findTopComponent("ContactTypesTopComponent");
    Lookup.Result<Ctypes> contactTypeRslt = contacttypeTc.getLookup().lookupResult(Ctypes.class);
    /**
     * Creates new form PersonalInfoPanel
     */
    public EditorEmployeeContactPanel(int i) {
        initComponents();
        //Start transaction
        popup = i;
        if (i == 0){
            jbSave.setVisible(false);
        }else{
            jbSave.setVisible(true);
            jbSave.setEnabled(false);
        }
        contactTypeRslt.addLookupListener(this);
        resultChanged(new LookupEvent(contactTypeRslt));
        empRslt.addLookupListener(this);
        resultChanged(new LookupEvent(empRslt));
        
        genderRslt.addLookupListener(this);
        resultChanged(new LookupEvent(genderRslt));
        
        maritalRslt.addLookupListener(this);
        resultChanged(new LookupEvent(maritalRslt));
        
        nationRslt.addLookupListener(this);
        resultChanged(new LookupEvent(nationRslt));
        
        tribeRslt.addLookupListener(this);
        resultChanged(new LookupEvent(tribeRslt));
        
        
        
        
        
        
        
        
        
        
        jtContact.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                passportNumber = jtContact.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
        jtContact.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                passportNumber = jtContact.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
        
      
        
        jtContactType.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(contacttypeTc, "Select A Contact Type",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null)); }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jtContact = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtContactType = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jbSave = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtContact.setText(org.openide.util.NbBundle.getMessage(EditorEmployeeContactPanel.class, "EditorEmployeeContactPanel.jtContact.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(EditorEmployeeContactPanel.class, "EditorEmployeeContactPanel.jLabel8.text")); // NOI18N

        jtContactType.setBackground(new java.awt.Color(0, 204, 0));
        jtContactType.setText(org.openide.util.NbBundle.getMessage(EditorEmployeeContactPanel.class, "EditorEmployeeContactPanel.jtContactType.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(EditorEmployeeContactPanel.class, "EditorEmployeeContactPanel.jLabel7.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtContact, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jtContactType))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtContactType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(EditorEmployeeContactPanel.class, "EditorEmployeeContactPanel.jbSave.text")); // NOI18N
        jbSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbSave)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveActionPerformed
        entityManager.getTransaction().begin();    
        
        
            String insertSQL = "INSERT INTO [dbo].[Employees]\n" +
"           ([SurName]\n" +
"           ,[OtherNames]\n" +
"           ,[DateOfBirth]\n" +
"           ,[Gender]\n" +
"           ,[MaritalStatus]\n" +
"           ,[NationalityID]\n" +
"           ,[TribeID]\n" +
"           ,[ReligionID]\n" +
"           ,[PassportNo]\n" +
"           ,[NHIFNo]\n" +
"           ,[NSSFNo]\n" +
"           ,[Photo]\n" +
"           ,[isDeleted]\n" +
"           ,[EmpCode]\n" +                    
"           ,[isDisengaged])\n" +                    
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, lname);
            query.setParameter(2, fname);
            query.setParameter(3, dob);
            query.setParameter(4, genderID);
            query.setParameter(5, maritalStatusID);
            query.setParameter(6, nationalityID.getNationalityID());
            query.setParameter(7, tribeID.getTribeID());
            query.setParameter(8, religionID.getReligionID());
            query.setParameter(9, passportNumber);
            query.setParameter(11, nationalID);
            //query.setParameter(12, genderID);
            query.setParameter(13, false);
            query.setParameter(14, randomCode(12));
            query.setParameter(15, false);
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            

    }//GEN-LAST:event_jbSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbSave;
    private javax.swing.JTextField jtContact;
    private javax.swing.JTextField jtContactType;
    // End of variables declaration//GEN-END:variables

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result result = (Lookup.Result)le.getSource();
        for (Object e: result.allInstances()){
            if(e instanceof Employees){
                
                resetEditor();
                edit = true;
                emp = (Employees)e;
                
                try{
                jtContactType.setText(((Employees) e).getReligionID().getReligion());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtContact.setText(((Employees) e).getPassportNo());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                
                
             //Set the photo   
            
                }
                
                
                
                
                
                
            
            
            
            
            if(e instanceof Religions){
                religionID = (Religions) e;
                jtContactType.setText(((Religions) e).getReligion());
                modify();
            }
            
        }
    }
    
    void modify(){
        if(edit){
            if(popup == 0){
                if(getLookup().lookup(MySavable.class)==null){
                    UtilityPDR.editorIC.add(new MySavable());
                }
            }else{
                jbSave.setEnabled(true);
            }
        } else {
            
        }
    }

    
    public Lookup getLookup() {
        TopComponent tc = WindowManager.getDefault().findTopComponent("EmployeeEditorTopComponent");
        return tc.getLookup();
    }
    
    private class MySavable extends AbstractSavable{

        public MySavable(){
            register();
        } 
        
        @Override
        protected String findDisplayName() {
            return "Employee";
        }

        @Override
        protected void handleSave() throws IOException {
            
            //If it is an update
            if(emp != null ){
            UtilityPDR.editorIC.remove(this);
            unregister();
            entityManager.getTransaction().begin();
            Employees e = entityManager.find(Employees.class, emp.getEmployeeID());
            if(null != tribeID){
            e.setTribeID(tribeID);
            }
            if(null != nationalityID)
            {
            e.setNationalityID(nationalityID);
            }
            
            if(fname!=null){
                e.setOtherNames(fname);
            }
            
            if(lname != null){
                e.setSurName(lname);
            }
            
            if(dob != null){
            e.setDateOfBirth(dob);
            }
            if(genderID != null){
            e.setGender(genderID);
            }
            if(maritalStatusID != null){
            e.setMaritalStatus(maritalStatusID);
            }
            
            if(religionID != null){
            e.setReligionID(religionID);
            }
            
            if(passportNumber != null){
            e.setPassportNo(passportNumber);
            }
            
            if(nationalID != null){
            e.setNHIFNo(nationalID);
            }
            
            if(nssfNumber != null){
            e.setNSSFNo(nssfNumber);
            }
            
            
            entityManager.getTransaction().commit();
            resetEditor();
            }else{
                //It is a new entry 
                
                UtilityPDR.editorIC.remove(this);
                unregister();
                entityManager.getTransaction().begin();
            
                
            String insertSQL = "INSERT INTO [dbo].[Employees]\n" +
"           ([SurName]\n" +
"           ,[OtherNames]\n" +
"           ,[DateOfBirth]\n" +
"           ,[Gender]\n" +
"           ,[MaritalStatus]\n" +
"           ,[NationalityID]\n" +
"           ,[TribeID]\n" +
"           ,[ReligionID]\n" +
"           ,[PassportNo]\n" +
"           ,[NHIFNo]\n" +
"           ,[NSSFNo]\n" +
"           ,[Photo]\n" +
"           ,[isDeleted]\n" +
"           ,[EmpCode]\n" +                    
"           ,[isDisengaged])\n" +                    
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, lname);
            query.setParameter(2, fname);
            query.setParameter(3, dob);
            query.setParameter(4, genderID);
            query.setParameter(5, maritalStatusID);
            query.setParameter(6, nationalityID.getNationalityID());
            query.setParameter(7, tribeID.getTribeID());
            query.setParameter(8, religionID.getReligionID());
            query.setParameter(9, passportNumber);
            query.setParameter(11, nationalID);
            //query.setParameter(12, genderID);
            query.setParameter(13, false);
            query.setParameter(14, randomCode(8));
            query.setParameter(15, false);
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            
            
                
            }
            
        }
        
        EditorEmployeeContactPanel pnel(){
            return EditorEmployeeContactPanel.this;
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof MySavable){
                MySavable m = (MySavable)o;
                return pnel() == m.pnel();
            }
            return false;
        }

        @Override
        public int hashCode() {
            return pnel().hashCode();
        }
        
    }
    
    void resetEditor(){
        
        jtContact.setText("");
        jtContactType.setText("");
        
        fname = null;
        lname = null;
        genderID = null;
        tribeID = null;
        maritalStatusID =null;
        nationalityID =null;
        religionID = null;
        passportNumber = null;
        nationalID = null;
        nssfNumber = null;
        dob = null;
    }
    
    public static String randomCode(int count) {

        StringBuilder builder = new StringBuilder();

        while (count-- != 0) {

        int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());

        builder.append(ALPHA_NUMERIC_STRING.charAt(character));

    }

return builder.toString();

}
private ImageIcon getImage(byte[] dbInfo) throws IOException{
    BufferedImage image = ImageIO.read(new ByteArrayInputStream(dbInfo));
    Image resizedImage = image.getScaledInstance(240, 320, WIDTH);
    StatusDisplayer.getDefault().setStatusText("Image Size:" + image.getHeight()+" x "+ image.getWidth());
    ImageIcon icon = new ImageIcon(resizedImage);
    return icon;
}
}
