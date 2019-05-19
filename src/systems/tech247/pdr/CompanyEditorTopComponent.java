/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.CompanyDetails;
import systems.tech247.hr.Nationalities;
import systems.tech247.hr.Religions;
import systems.tech247.hr.Tribes;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//CompanyEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "CompanyEditorTopComponent",
        iconBase="systems/tech247/util/icons/company.png", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)



@Messages({
    "CTL_CompanyEditorAction=Company Editor",
    "CTL_CompanyEditorTopComponent= Company Editor",
    "HINT_CompanyEditorTopComponent= Company Editor"
})
public final class CompanyEditorTopComponent extends TopComponent{
    
    CompanyDetails emp;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    DataAccess da = new DataAccess();
    InstanceContent ic = new InstanceContent();
    
    int popup;
    //Updatables
    String companyName = "";
    String companyPin = "";
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
    String propertyCode = null;
    
    
    EntityManager entityManager = DataAccess.getEntityManager();
    
    CompanyDetails updatable;
    
    public CompanyEditorTopComponent(){
        this(null);
    }

    public CompanyEditorTopComponent(CompanyDetails e) {
        initComponents();
        setName(Bundle.CTL_CompanyEditorTopComponent());
        setToolTipText(Bundle.HINT_CompanyEditorTopComponent());
        jlPhoto.setText("");
        emp = e;
        
        if(null!=e){
            updatable = entityManager.find(CompanyDetails.class, e.getCompanyID());
        }
        
        
        
        //Fill in the employee details
        if(null!=e){
            fillEmployee(e);
        }
        
        jtNHIFNo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String NHIF = jtNHIFNo.getText();
                        try{
                            updatable.setNHIFNumber(NHIF);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String NHIF = jtNHIFNo.getText();
                        try{
                            updatable.setNHIFNumber(NHIF);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String NHIF = jtNHIFNo.getText();
                        try{
                            updatable.setNHIFNumber(NHIF);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }
        });
        
        
        
        
        jtPropertyCode.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                propertyCode = jtPropertyCode.getText();
                        try{
                            updatable.setPropertyCode(propertyCode);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                propertyCode = jtPropertyCode.getText();
                        try{
                            updatable.setPropertyCode(propertyCode);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                propertyCode = jtPropertyCode.getText();
                        try{
                            updatable.setPropertyCode(propertyCode);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }
        });
        
        jtCompanyName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                companyName = jtCompanyName.getText();
                        try{
                            updatable.setCompanyName(companyName);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                companyName = jtCompanyName.getText();
                        try{
                            updatable.setCompanyName(companyName);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                companyName = jtCompanyName.getText();
                        try{
                            updatable.setCompanyName(companyName);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }
        });
        
        jtTelephone1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String telephone1 = jtTelephone1.getText();
                        
                        try{
                            updatable.setTelephone1(telephone1);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String telephone1 = jtTelephone1.getText();
                        
                        try{
                            updatable.setTelephone1(telephone1);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String telephone1 = jtTelephone1.getText();
                        
                        try{
                            updatable.setTelephone1(telephone1);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }
        });
        
        
        
        
        jtTelephone3.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String telephone3 = jtTelephone3.getText();
                        try{
                        updatable.setTelephone3(telephone3);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String telephone3 = jtTelephone3.getText();
                try{
                    updatable.setTelephone3(telephone3);
                }catch(Exception ex){
                            
                }
                modify();            
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String telephone3 = jtTelephone3.getText();
                try{
                    updatable.setTelephone3(telephone3);
                }catch(Exception ex){
                            
                }
                modify();
            }
        });
        
        jtEmail.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String email = jtEmail.getText();
                        try{
                        updatable.setEMail(email);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String email = jtEmail.getText();
                        try{
                        updatable.setEMail(email);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String email = jtEmail.getText();
                        try{
                        updatable.setEMail(email);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }
        });
        
        
        
        jtPhysicalAddress.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String physicalAddress = jtPhysicalAddress.getText();
                        try{
                        updatable.setPhysicalAddress(physicalAddress);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String physicalAddress = jtPhysicalAddress.getText();
                        try{
                        updatable.setPhysicalAddress(physicalAddress);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String physicalAddress = jtPhysicalAddress.getText();
                        try{
                        updatable.setPhysicalAddress(physicalAddress);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }
        });
        
       
       
        jtNSSFNo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                nssfNumber = jtNSSFNo.getText();
                        try{
                        updatable.setNSSFNumber(nssfNumber);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nssfNumber = jtNSSFNo.getText();
                        try{
                        updatable.setNSSFNumber(nssfNumber);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                nssfNumber = jtNSSFNo.getText();
                        try{
                        updatable.setNSSFNumber(nssfNumber);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }
        });
        
        jtPostalAddress.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String postalAddress = jtNSSFNo.getText();
                        try{
                        updatable.setPostalAddress(postalAddress);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String postalAddress = jtNSSFNo.getText();
                        try{
                        updatable.setPostalAddress(postalAddress);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String postalAddress = jtNSSFNo.getText();
                        try{
                        updatable.setPostalAddress(postalAddress);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }
        });
        
        jtWeb.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String web = jtWeb.getText();
                        try{
                        updatable.setWebURL(web);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String web = jtWeb.getText();
                        try{
                        updatable.setWebURL(web);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String web = jtWeb.getText();
                        try{
                        updatable.setWebURL(web);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }
        });
        
        jtTelephone2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String tele2 = jtTelephone2.getText();
                        try{
                        updatable.setTelephone2(tele2);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String tele2 = jtTelephone2.getText();
                        try{
                        updatable.setTelephone2(tele2);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String tele2 = jtTelephone2.getText();
                        try{
                        updatable.setTelephone2(tele2);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }
        });
        
        jtFax.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String fax = jtFax.getText();
                        try{
                        updatable.setFax(fax);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String fax = jtFax.getText();
                        try{
                        updatable.setFax(fax);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String fax = jtFax.getText();
                        try{
                        updatable.setFax(fax);
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
                return "New Company";
            }else{
                return emp.getCompanyName();
            }
        }
        
        CompanyEditorTopComponent tc(){
            return CompanyEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            
            
                //We shall always be updating
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
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
    
    public void modify(){
        
        
    
            if(companyPin.length()<=1){
                StatusDisplayer.getDefault().setStatusText("Proper Company Name is Required");
            }else if(companyName.length()<=1){
                    StatusDisplayer.getDefault().setStatusText("Proper First Name is Required");
            }else{
                if(getLookup().lookup(CompanySavable.class)==null){
                            ic.add(new CompanySavable());
                }
            }
        
        
            
        
    }
    
    void fillEmployee(CompanyDetails e){
        try{
                setName(e.getCompanyName());
                try{
                jtPropertyCode.setText(e.getPropertyCode());
                }catch(Exception ex){
                    
                }
                companyName = e.getCompanyName();
                
                
                jtCompanyName.setText( e.getCompanyName());
                companyPin = e.getPINNumber();
                
                
                
                jtNSSFNo.setText(e.getNSSFNumber());
                
                
                jtNHIFNo.setText(e.getNHIFNumber());
                
                
                        
                
                
                jcbIsNGO.setSelected(e.getIsNGO());
                
                
                
                jtPhysicalAddress.setText(e.getPhysicalAddress());
                
                jtWeb.setText(e.getWebURL());
                
                
                
                jtPostalAddress.setText(e.getPostalAddress());
                
                
                jtTelephone1.setText(e.getTelephone1());
                
                
                jtTelephone2.setText(e.getTelephone2());
                
                
                jtTelephone3.setText( e.getTelephone3());
                
                jtFax.setText(e.getFax());
                
                jtEmail.setText(e.getEMail());
                
                //Set the photo   
             try {
                   ImageIcon photo = UtilityPDR.getImage(e.getLogo());
                   //byte[] imageInfo = DataAccess.getPhotoBytes(e.getEmployeeID());
                   //BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageInfo));
                   //jlEmployeePhoto.setText(" Photo is too Large");
                   
                   //StatusDisplayer.getDefault().setStatusText("Photo is too large");
                   
                   jlPhoto.setIcon(photo);
               } catch (IOException ex) {
                   Exceptions.printStackTrace(ex);
               } catch (NullPointerException ex){
                   jlPhoto.setIcon(null);
                   jlPhoto.setText("No Photo");
               }
                
                
                
                
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
        jtCompanyName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtPropertyCode = new javax.swing.JTextField();
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
        jPanel1 = new javax.swing.JPanel();
        jlPhoto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jtWeb = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jtFax = new javax.swing.JTextField();
        jcbIsNGO = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jLabel1.text")); // NOI18N

        jtCompanyName.setText(org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jtCompanyName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jLabel2.text")); // NOI18N

        jtPropertyCode.setText(org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jtPropertyCode.text")); // NOI18N

        jtNSSFNo.setText(org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jtNSSFNo.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jLabel3.text")); // NOI18N

        jtNHIFNo.setText(org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jtNHIFNo.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jLabel5.text")); // NOI18N

        jtPhysicalAddress.setText(org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jtPhysicalAddress.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jLabel7.text")); // NOI18N

        jtPostalAddress.setText(org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jtPostalAddress.text")); // NOI18N

        jtTelephone1.setText(org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jtTelephone1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jLabel9.text")); // NOI18N

        jtTelephone2.setText(org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jtTelephone2.text")); // NOI18N

        jtTelephone3.setText(org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jtTelephone3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jLabel10.text")); // NOI18N

        jtEmail.setText(org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jtEmail.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jLabel11.text")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jlPhoto, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jlPhoto.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jlPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jLabel13.text")); // NOI18N

        jtWeb.setText(org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jtWeb.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jLabel14.text")); // NOI18N

        jtFax.setText(org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jtFax.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbIsNGO, org.openide.util.NbBundle.getMessage(CompanyEditorTopComponent.class, "CompanyEditorTopComponent.jcbIsNGO.text")); // NOI18N
        jcbIsNGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbIsNGOActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jtCompanyName)
                        .addComponent(jtPropertyCode, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
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
                    .addComponent(jcbIsNGO))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(84, 84, 84))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtPropertyCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(jtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jcbIsNGO))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            // TODO add your handling code here:
            JFileChooser jfc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Photos", "jpg","png","JPEG");
        jfc.setFileFilter(filter);
        int returnVal = jfc.showOpenDialog(this);
        
        String extension = "";
        if(returnVal==JFileChooser.APPROVE_OPTION){
            File file = jfc.getSelectedFile();
            extension = file.getName().replaceAll("^.*\\.([^.]+)$", "$1");
            //StatusDisplayer.getDefault().setStatusText("Extension: "+ extension);
           
            try {
                BufferedImage imm = ImageIO.read(file);
                ByteArrayOutputStream boas = new ByteArrayOutputStream();
                //Add the Encoding
                ImageIO.write(imm, extension, boas);
                boas.flush();
                imageAsBytes = boas.toByteArray();
                try{
                updatable.setLogo(imageAsBytes);
                }catch(NullPointerException ex){
                    
                }
                boas.close();
                modify();
                
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }else{
            StatusDisplayer.getDefault().setStatusText("File Upload Aborted");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jcbIsNGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbIsNGOActionPerformed
        updatable.setIsNGO(jcbIsNGO.isSelected());
    }//GEN-LAST:event_jcbIsNGOActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox jcbIsNGO;
    private javax.swing.JLabel jlPhoto;
    private javax.swing.JTextField jtCompanyName;
    private javax.swing.JTextField jtEmail;
    private javax.swing.JTextField jtFax;
    private javax.swing.JTextField jtNHIFNo;
    private javax.swing.JTextField jtNSSFNo;
    private javax.swing.JTextField jtPhysicalAddress;
    private javax.swing.JTextField jtPostalAddress;
    private javax.swing.JTextField jtPropertyCode;
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

    
    
     public static String randomCode(int count) {

        StringBuilder builder = new StringBuilder();

        while (count-- != 0) {

        int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());

        builder.append(ALPHA_NUMERIC_STRING.charAt(character));

    }

return builder.toString();

}
}
