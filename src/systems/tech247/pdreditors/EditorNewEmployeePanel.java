/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.Component;
import java.awt.TextComponent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
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
public final class EditorNewEmployeePanel extends javax.swing.JPanel implements LookupListener{

    Employees emp;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    DataAccess da = new DataAccess();
    
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
    byte[] imageAsBytes = null;
    Boolean autoFilling = true;
    String tin = null;
    
    
    EntityManager entityManager = DataAccess.getEntityManager();
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
    TopComponent religionTc = WindowManager.getDefault().findTopComponent("ReligionsTopComponent");
    Lookup.Result<Religions> religionRslt = religionTc.getLookup().lookupResult(Religions.class);
    /**
     * Creates new form PersonalInfoPanel
     */
    public EditorNewEmployeePanel(int i) {
        initComponents();
        
        disableInput();
        
        //Start transaction
        popup = i;
        if (i == 0){
            jbSave.setVisible(false);
        }else{
            jbSave.setVisible(true);
            jbSave.setEnabled(false);
        }
        religionRslt.addLookupListener(this);
        resultChanged(new LookupEvent(religionRslt));
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
        
        jtNationality.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(nationTc, "Search Nations",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
        jtTribe.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(tribeTc, "Select A Tribe",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
        
        jtFirstName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
                fname = jtFirstName.getText();
                
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                fname = jtFirstName.getText();
                
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
                fname = jtFirstName.getText();
                
                modify();
            }
        });
        
        jtLastName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
                lname = jtLastName.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                lname = jtLastName.getText();
                modify();
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
                lname = jtLastName.getText();
                modify();
            }
        });
        
        jtPassportNumber.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                passportNumber = jtPassportNumber.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                passportNumber = jtPassportNumber.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                passportNumber = jtPassportNumber.getText();
                modify();
            }
        });
        
        
        
        
        jtNSSFNo.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                nssfNumber = jtNSSFNo.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                nssfNumber = jtNSSFNo.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                nssfNumber = jtNSSFNo.getText();
                modify();
            }
        });
        
        jtTIN.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
               tin = jtTIN.getText();
               modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                tin = jtTIN.getText();
               modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                tin = jtTIN.getText();
               modify();
            }
        });
        
        
        
        jtMaritalStatus.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(maritalTc, "Select Marital Status",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null)); }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
        jdcDOB.setMaxSelectableDate(new Date());
        
        
        
        jdcDOB.getJCalendar().getDayChooser().setDayBordersVisible(true);
        
        jdcDOB.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getSource()==jdcDOB && "date".equals(evt.getPropertyName()) && !autoFilling){
                    dob = jdcDOB.getDate();
                    modify();
                }
            }
        });
        
        jdcDOB.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                autoFilling = false;
            }

            @Override
            public void keyPressed(KeyEvent e) {
                autoFilling = false;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                autoFilling = false;
            }
        });
        
        jdcDOB.getJCalendar().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("ancestor")){
                    autoFilling = false;
                }
            }
        });
       
        jtGender.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(genderTc, "Select Gender",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null)); }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
        jtReligion.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(religionTc, "Select Religion",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null)); }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
        jtNationalID.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                nationalID = jtNationalID.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                nationalID = jtNationalID.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                nationalID = jtNationalID.getText();
                modify();
            }
        });
        
        
        
    }
    
    void disableInput(){
        boolean shl = null != emp;
        Component[] comps = getComponents();
        for(Component c: comps){
            if(c instanceof TextComponent){
                c.setEnabled(shl);
            }
        }
        
        
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
        jLabel3 = new javax.swing.JLabel();
        jtNationality = new javax.swing.JTextField();
        jtPassportNumber = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jtTribe = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtReligion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtLastName = new javax.swing.JTextField();
        jtNationalID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jdcDOB = new com.toedter.calendar.JDateChooser();
        jtGender = new javax.swing.JTextField();
        jtMaritalStatus = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jbPhotoUpload = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jpPhoto = new javax.swing.JLabel();
        jtNSSFNo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtFirstName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtTIN = new javax.swing.JTextField();
        jbSave = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jLabel3.text")); // NOI18N

        jtNationality.setBackground(new java.awt.Color(0, 204, 0));
        jtNationality.setText(org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jtNationality.text")); // NOI18N

        jtPassportNumber.setText(org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jtPassportNumber.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jLabel11.text")); // NOI18N

        jtTribe.setBackground(new java.awt.Color(0, 204, 0));
        jtTribe.setText(org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jtTribe.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jLabel1.text")); // NOI18N

        jtReligion.setBackground(new java.awt.Color(0, 204, 0));
        jtReligion.setText(org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jtReligion.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jLabel4.text")); // NOI18N

        jtLastName.setText(org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jtLastName.text")); // NOI18N

        jtNationalID.setText(org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jtNationalID.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jLabel6.text")); // NOI18N

        jtGender.setBackground(new java.awt.Color(0, 204, 0));
        jtGender.setText(org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jtGender.text")); // NOI18N

        jtMaritalStatus.setBackground(new java.awt.Color(0, 204, 0));
        jtMaritalStatus.setText(org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jtMaritalStatus.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jLabel7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jbPhotoUpload, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jbPhotoUpload.text")); // NOI18N
        jbPhotoUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPhotoUploadActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jpPhoto, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jpPhoto.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtNSSFNo.setText(org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jtNSSFNo.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jLabel9.text")); // NOI18N

        jtFirstName.setText(org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jtFirstName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jLabel10.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jLabel12.text")); // NOI18N

        jtTIN.setText(org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jtTIN.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtPassportNumber)
                    .addComponent(jtReligion)
                    .addComponent(jtMaritalStatus)
                    .addComponent(jtTribe)
                    .addComponent(jtNationality)
                    .addComponent(jtGender)
                    .addComponent(jtLastName)
                    .addComponent(jtFirstName)
                    .addComponent(jtNationalID)
                    .addComponent(jtNSSFNo)
                    .addComponent(jdcDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtTIN))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbPhotoUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtTribe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jtMaritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jtReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jtPassportNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jtNationalID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jtNSSFNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jtTIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jdcDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbPhotoUpload)
                .addContainerGap())
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(EditorNewEmployeePanel.class, "EditorNewEmployeePanel.jbSave.text")); // NOI18N
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
                    .addComponent(jbSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(jbSave)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveActionPerformed
        
            prepareAndSave();

    }//GEN-LAST:event_jbSaveActionPerformed

    private void jbPhotoUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPhotoUploadActionPerformed
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
                boas.close();
                modify();
                
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }else{
            StatusDisplayer.getDefault().setStatusText("File Upload Aborted");
        }
    }//GEN-LAST:event_jbPhotoUploadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbPhotoUpload;
    private javax.swing.JButton jbSave;
    private com.toedter.calendar.JDateChooser jdcDOB;
    private javax.swing.JLabel jpPhoto;
    private javax.swing.JTextField jtFirstName;
    private javax.swing.JTextField jtGender;
    private javax.swing.JTextField jtLastName;
    private javax.swing.JTextField jtMaritalStatus;
    private javax.swing.JTextField jtNSSFNo;
    private javax.swing.JTextField jtNationalID;
    private javax.swing.JTextField jtNationality;
    private javax.swing.JTextField jtPassportNumber;
    private javax.swing.JTextField jtReligion;
    private javax.swing.JTextField jtTIN;
    private javax.swing.JTextField jtTribe;
    // End of variables declaration//GEN-END:variables

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result result = (Lookup.Result)le.getSource();
        for (Object e: result.allInstances()){
            if(e instanceof Employees){
                
                
                
                autoFilling = true;
                emp = (Employees)e;
                
                disableInput();
                
                try{
                jtFirstName.setText( ((Employees) e).getOtherNames());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtLastName.setText(((Employees) e).getSurName());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                Gender g = new Gender(((Employees) e).getGender());
                
                jtGender.setText(g.toString());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtNationality.setText(((Employees) e).getNationalityID().getNationality());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                        
                    jdcDOB.setDate(((Employees) e).getDateOfBirth());
                    
                
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                Marital m = new Marital(((Employees) e).getMaritalStatus());
                
                jtMaritalStatus.setText(m.toString());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtTribe.setText(((Employees) e).getTribeID().getTribe());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtReligion.setText(((Employees) e).getReligionID().getReligion());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtPassportNumber.setText(((Employees) e).getPassportNo());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtNationalID.setText(((Employees) e).getNHIFNo());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtNSSFNo.setText(((Employees) e).getNSSFNo());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                
                try{
                jtTIN.setText(((Employees) e).getPINNo());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                
             //Set the photo   
             try {
                   ImageIcon photo = UtilityPDR.getImage(((Employees) e).getPhoto());
                   //byte[] imageInfo = DataAccess.getPhotoBytes(e.getEmployeeID());
                   //BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageInfo));
                   //jlEmployeePhoto.setText(" Photo is too Large");
                   jpPhoto.setText("");
                   //StatusDisplayer.getDefault().setStatusText("Photo is too large");
                   
                   jpPhoto.setIcon(photo);
               } catch (IOException ex) {
                   Exceptions.printStackTrace(ex);
               } catch (NullPointerException ex){
                   jpPhoto.setIcon(null);
                   jpPhoto.setText("No Photo");
               }
                }
                
                
                
                
                
                
            
            
            if(e instanceof Tribes){
                tribeID = (Tribes)e;
                jtTribe.setText(((Tribes) e).getTribe());
                modify();
            }
            
            if(e instanceof Nationalities){
                nationalityID = (Nationalities)e;
                jtNationality.setText(((Nationalities) e).getNationality());
                modify();
            }
            
            if(e instanceof Gender){
                genderID = ((Gender) e).getCode();
                jtGender.setText(((Gender) e).toString());
                modify();
            }
            
            if(e instanceof Religions){
                religionID = (Religions) e;
                jtReligion.setText(((Religions) e).getReligion());
                modify();
            }
            if(e instanceof Marital){
                maritalStatusID = ((Marital) e).getCode();
                jtMaritalStatus.setText(((Marital)e).toString());
                modify();
            }
        }
    }
    
    void modify(){
        
            if(popup == 0){
                if(getLookup().lookup(MySavable.class)==null){
                    UtilityPDR.editorIC.add(new MySavable());
                }
            }else{
                jbSave.setEnabled(true);
            }
        
    }
    
    void prepareAndSave(){
        
        
            
        
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
            query.setParameter(11, nssfNumber);
            query.setParameter(12, imageAsBytes);
            query.setParameter(13, false);
            query.setParameter(14, randomCode(12));
            query.setParameter(15, false);
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            
            
            
            
            
            
            
            
            
            
            
            
            
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
            
            
            UtilityPDR.editorIC.remove(this);
            unregister();
            //If it is an update
            if(emp != null ){
            
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
            
            if(tin != null){
            e.setPINNo(tin);
            }
            
            if(imageAsBytes != null){
                e.setPhoto(imageAsBytes);
            }
            
            
            entityManager.getTransaction().commit();
            
            
            }else{
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
            query.setParameter(11, nssfNumber);
            query.setParameter(12, imageAsBytes);
            query.setParameter(13, false);
            query.setParameter(14, randomCode(12));
            query.setParameter(15, false);
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            
        
            }
            
        }
        
        EditorNewEmployeePanel pnel(){
            return EditorNewEmployeePanel.this;
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
        jtFirstName.setText("");
        jtGender.setText("");
        jtLastName.setText("");
        jtMaritalStatus.setText("");
        jtNSSFNo.setText("");
        jtNationalID.setText("");
        jtNationality.setText("");
        jtPassportNumber.setText("");
        jtReligion.setText("");
        jtTribe.setText("");
        jdcDOB.setDate(null);
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

}
