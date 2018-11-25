/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Employees;
import systems.tech247.hr.Nationalities;
import systems.tech247.hr.Religions;
import systems.tech247.hr.Tribes;
import systems.tech247.util.Gender;
import systems.tech247.util.Marital;
import systems.tech247.util.NotifyUtil;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//EmployeePersonalInfoEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmployeePersonalInfoEditorTopComponent",
        iconBase="systems/tech247/util/icons/person.png", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)



@Messages({
    "CTL_EmployeePersonalInfoEditorAction=EmployeePersonalInfoEditor",
    "CTL_EmployeePersonalInfoEditorTopComponent=New Employee",
    "HINT_EmployeePersonalInfoEditorTopComponent=This is a EmployeePersonalInfoEditor window"
})
public final class EmployeePersonalInfoEditorTopComponent extends TopComponent implements LookupListener{
    
    Employees emp;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    DataAccess da = new DataAccess();
    InstanceContent ic = new InstanceContent();
    
    int popup;
    //Updatables
    String fname = "";
    String lname = "";
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
    int minimumAge = 16;
    
    
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
    Employees updatable;
    
    public EmployeePersonalInfoEditorTopComponent(){
        this(null);
    }

    public EmployeePersonalInfoEditorTopComponent(Employees e) {
        initComponents();
        setName(Bundle.CTL_EmployeePersonalInfoEditorTopComponent());
        setToolTipText(Bundle.HINT_EmployeePersonalInfoEditorTopComponent());
        jlPhoto.setText("");
        emp = e;
        
        if(null!=e){
            updatable = entityManager.find(Employees.class, e.getEmployeeID());
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
        
        //Fill in the employee details
        if(null!=e){
            fillEmployee(e);
        }
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -minimumAge);
        jdcDOB.setMaxSelectableDate(cal.getTime());
        
        jdcDOB.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getSource()==jdcDOB && "date".equals(evt.getPropertyName())){
                    dob = jdcDOB.getDate();
                    try{
                        updatable.setDateOfBirth(dob);
                    }catch(NullPointerException ex){
                        
                    }
                    modify();
                    
                }
               
                
            }
        });
        
        
        
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
        
        jtNationality.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(nationTc, "Search Nations",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
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
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        jtTribe.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(tribeTc, "Select A Tribe",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
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
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        jtOtherNames.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                
                        fname = jtOtherNames.getText();
                        try{
                            updatable.setOtherNames(fname);
                        }catch(Exception ex){
                            
                        }
                        modify();
                    
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fname = jtOtherNames.getText();
                        try{
                            updatable.setOtherNames(fname);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fname = jtOtherNames.getText();
                        try{
                            updatable.setOtherNames(fname);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }
        });
        
        jtSurname.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                lname = jtSurname.getText();
                        try{
                            updatable.setSurName(lname);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                lname = jtSurname.getText();
                        try{
                            updatable.setSurName(lname);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                lname = jtSurname.getText();
                        try{
                            updatable.setSurName(lname);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }
        });
        
        jtPassport.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                passportNumber = jtPassport.getText();
                        
                        try{
                            updatable.setPassportNo(passportNumber);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                passportNumber = jtPassport.getText();
                        
                        try{
                            updatable.setPassportNo(passportNumber);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                passportNumber = jtPassport.getText();
                        
                        try{
                            updatable.setPassportNo(passportNumber);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }
        });
        
        
        
        
        jtNSSF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                nssfNumber = jtNSSF.getText();
                        try{
                        updatable.setNSSFNo(nssfNumber);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nssfNumber = jtNSSF.getText();
                        try{
                        updatable.setNSSFNo(nssfNumber);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                nssfNumber = jtNSSF.getText();
                        try{
                        updatable.setNSSFNo(nssfNumber);
                        }catch(Exception ex){
                            
                        }
                        modify();
            }
        });
        
        jtTINNo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                tin = jtTINNo.getText();
                        try{
                        updatable.setPINNo(tin);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                tin = jtTINNo.getText();
                        try{
                        updatable.setPINNo(tin);
                        }catch(NullPointerException ex){
                            
                        }
                        modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                tin = jtTINNo.getText();
                        try{
                        updatable.setPINNo(tin);
                        }catch(NullPointerException ex){
                            
                        }
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
        jtMaritalStatus.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(maritalTc, "Select Marital Status",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
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
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        
        
        
        
        
        
        

        
        
       
        jtGender.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(genderTc, "Select Gender",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null)); 
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        
        jtGender.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(genderTc, "Select Gender",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
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
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        jtReligion.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(religionTc, "Select Religion",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
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
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jtNationalID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                nationalID = jtNationalID.getText();
                try{
                    updatable.setIDNo(nationalID);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nationalID = jtNationalID.getText();
                try{
                    updatable.setIDNo(nationalID);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                nationalID = jtNationalID.getText();
                try{
                    updatable.setIDNo(nationalID);
                }catch(NullPointerException ex){
                    
                }
                modify();
            }
        });
                
        
        

    }
    
    private class EmployeeSavable extends AbstractSavable{
        
        EmployeeSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            if(null==emp){
                return "Employee";
            }else{
                return emp.getSurName()+" "+emp.getOtherNames();
            }
        }
        
        EmployeePersonalInfoEditorTopComponent tc(){
            return EmployeePersonalInfoEditorTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            //New Employee
            if(null==emp){
                
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
"           ,[IDNo]\n" +
"           ,[NSSFNo]\n" +
"           ,[Photo]\n" +
"           ,[isDeleted]\n" +
"           ,[EmpCode]\n" +                    
"           ,[isDisengaged]"+
"           ,[PINNo]\n"                                + ")\n" +                    
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, lname);
            query.setParameter(2, fname);
            try{
            query.setParameter(3, dob);
            }catch(NullPointerException ex){
                System.out.println("DOB is null");
            }
            try{
            query.setParameter(4, genderID);
            }catch(NullPointerException ex){
                System.out.println("Gender is null");
            }
            try{
            query.setParameter(5, maritalStatusID);
            }catch(NullPointerException ex){
                System.out.println("Marital Status is null");
            
            }
            try{
                query.setParameter(6, nationalityID.getNationalityID());
            }catch(NullPointerException ex){
                System.out.println("Nationality is null");
            }
            
            
            try{
            query.setParameter(7, tribeID.getTribeID());
            }catch(NullPointerException ex){
                System.out.println("Tribe is null");
            }
            try{
            query.setParameter(8, religionID.getReligionID());
            }catch(NullPointerException ex){
                System.out.println("Religion is null");
            }
            query.setParameter(9, passportNumber);
            try{
            query.setParameter(11, nssfNumber);
            }catch(NullPointerException ex){
                
            }
            
            query.setParameter(10, nationalID);
            try{
            query.setParameter(11, nssfNumber);
            }catch(NullPointerException ex){
                
            }
            
            
            try{
            query.setParameter(12, imageAsBytes);
            }catch(NullPointerException ex){
                
            }
            query.setParameter(13, false);
            query.setParameter(14, randomCode(12));
            query.setParameter(15, false);
            
            try{
            query.setParameter(16, tin);
            }catch(NullPointerException ex){
                
            }
            
            
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            }else{
                //Creating a new Employee
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            }
            this.tc().close();
            UtilityPDR.loadPDRSetup();
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof EmployeeSavable){
                EmployeeSavable e = (EmployeeSavable)o;
                return tc() == e.tc();
            }
            return false;        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
    
    public void modify(){
        if(null==updatable){ //New Employee
            if(lname.length()<=1){
                StatusDisplayer.getDefault().setStatusText("Proper Surname is Required");
            }else if(fname.length()<=1){
                    StatusDisplayer.getDefault().setStatusText("Proper First Name is Required");
            }else{
                if(getLookup().lookup(EmployeeSavable.class)==null){
                            ic.add(new EmployeeSavable());
                }
            }
        }else{
            if(updatable.getSurName().length()<=1){
                StatusDisplayer.getDefault().setStatusText("Proper Surname is Required");
            }else if(updatable.getOtherNames().length()<=1){
                    StatusDisplayer.getDefault().setStatusText("Proper First Name is Required");
            }else{
                if(getLookup().lookup(EmployeeSavable.class)==null){
                            ic.add(new EmployeeSavable());
                }
            }
        }
        
    
            
        
        
            
        
    }
    
    void fillEmployee(Employees e){
        try{
                setName(e.getSurName()+" "+e.getOtherNames());
                jtOtherNames.setText(e.getOtherNames());
                
                
                
                jtSurname.setText( e.getSurName());
                
                try{
                    genderID = e.getGender();
                    jtGender.setText((new Gender(genderID)).getDescription());
                }catch(NullPointerException ex){
                    
                }    
                
                try{
                    jtNationality.setText(e.getNationalityID().getNationality());
                }catch(NullPointerException ex){
                    
                }
                
                try{        
                    jdcDOB.setDate(e.getDateOfBirth());
                }catch(NullPointerException ex){
                    
                }
                
                try{
                    maritalStatusID= e.getGender();
                    jtMaritalStatus.setText((new Marital(maritalStatusID)).getDescription());
                }catch(NullPointerException ex){
                    
                }
                try{
                    jtTribe.setText(e.getTribeID().getTribe());
                }catch(NullPointerException ex){
                    
                }
                
                try{
                    jtReligion.setText(e.getReligionID().getReligion());
                }catch(NullPointerException ex){
                    
                }
                try{
                    jtPassport.setText(e.getPassportNo());
                }catch(NullPointerException ex){
                    
                }
                try{
                    jtNationalID.setText(e.getIDNo());
                }catch(NullPointerException ex){
                    
                }
                try{
                    jtNSSF.setText( e.getNSSFNo());
                }catch(NullPointerException ex){
                    
                }
                
                try{
                jtTINNo.setText(e.getPINNo());
                }catch(NullPointerException ex){
                    
                }
                
                //Set the photo   
                try {
                   ImageIcon photo = UtilityPDR.getImage(((Employees) e).getPhoto());
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
            NotifyUtil.error("Error", "Error", ex, false);
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
        jtSurname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtOtherNames = new javax.swing.JTextField();
        jtGender = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtNationality = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtTribe = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtMaritalStatus = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtReligion = new javax.swing.JTextField();
        jtPassport = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtNationalID = new javax.swing.JTextField();
        jtNSSF = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtTINNo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jlPhoto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jdcDOB = new com.toedter.calendar.JDateChooser();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jLabel1.text")); // NOI18N

        jtSurname.setText(org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jtSurname.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jLabel2.text")); // NOI18N

        jtOtherNames.setText(org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jtOtherNames.text")); // NOI18N

        jtGender.setText(org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jtGender.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jLabel3.text")); // NOI18N

        jtNationality.setText(org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jtNationality.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jLabel5.text")); // NOI18N

        jtTribe.setText(org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jtTribe.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jLabel6.text")); // NOI18N

        jtMaritalStatus.setText(org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jtMaritalStatus.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jLabel7.text")); // NOI18N

        jtReligion.setText(org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jtReligion.text")); // NOI18N

        jtPassport.setText(org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jtPassport.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jLabel9.text")); // NOI18N

        jtNationalID.setText(org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jtNationalID.text")); // NOI18N

        jtNSSF.setText(org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jtNSSF.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jLabel10.text")); // NOI18N

        jtTINNo.setText(org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jtTINNo.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jLabel11.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jLabel12.text")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jlPhoto, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jlPhoto.text")); // NOI18N

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

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jdcDOB.setDateFormatString(org.openide.util.NbBundle.getMessage(EmployeePersonalInfoEditorTopComponent.class, "EmployeePersonalInfoEditorTopComponent.jdcDOB.dateFormatString")); // NOI18N

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
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtSurname)
                    .addComponent(jtOtherNames, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtGender, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtNationality, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtTribe, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtMaritalStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtReligion, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtPassport, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtNationalID, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtNSSF, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jtTINNo, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jdcDOB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                            .addComponent(jtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtOtherNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtNationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtTribe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jtMaritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jtReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jtPassport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jtNationalID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jtNSSF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jtTINNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jdcDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(37, Short.MAX_VALUE))
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
                updatable.setPhoto(imageAsBytes);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
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
    private com.toedter.calendar.JDateChooser jdcDOB;
    private javax.swing.JLabel jlPhoto;
    private javax.swing.JTextField jtGender;
    private javax.swing.JTextField jtMaritalStatus;
    private javax.swing.JTextField jtNSSF;
    private javax.swing.JTextField jtNationalID;
    private javax.swing.JTextField jtNationality;
    private javax.swing.JTextField jtOtherNames;
    private javax.swing.JTextField jtPassport;
    private javax.swing.JTextField jtReligion;
    private javax.swing.JTextField jtSurname;
    private javax.swing.JTextField jtTINNo;
    private javax.swing.JTextField jtTribe;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        religionRslt.removeLookupListener(this);
        religionRslt = null;
        
        
        
        genderRslt.removeLookupListener(this);
        genderRslt = null;
        
        maritalRslt.removeLookupListener(this);
        maritalRslt = null;
        
        nationRslt.removeLookupListener(this);
        nationRslt = null;
        
        tribeRslt.removeLookupListener(this);
        tribeRslt = null;
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

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result result = (Lookup.Result)le.getSource();
        for (Object e: result.allInstances()){
        if(e instanceof Tribes){
            
                
                    tribeID = (Tribes)e;
                    jtTribe.setText(((Tribes) e).getTribe());
                    try{
                
                    updatable.setTribeID(tribeID);
                
                
                    }catch(Exception ex){
                        
                    }
                    modify();
                
            }
            
            if(e instanceof Nationalities){
                
                if(!Objects.equals(nationalityID, e)){
                nationalityID = (Nationalities)e;
                jtNationality.setText(((Nationalities) e).getNationality());
                
                try{
                    updatable.setNationalityID(nationalityID);
                }catch(NullPointerException ex){
                    
                }
                modify();
                
                }
                
            }
            
            if(e instanceof Gender){
                if(!Objects.equals(genderID, ((Gender)e).getCode())){
                genderID = ((Gender) e).getCode();
                jtGender.setText(((Gender) e).toString());
                try{
                    updatable.setGender(genderID);
                }catch(Exception ex){
                    
                }
                    modify();
                }
                
                
                
                
                
            }
            
            if(e instanceof Religions){
                if(!Objects.equals(religionID, e)){
                religionID = (Religions) e;
                jtReligion.setText(((Religions) e).getReligion());
                
                try{
                    updatable.setReligionID(religionID);
                }catch(Exception ex){
                    
                }
                modify();
                }
            }
            if(e instanceof Marital){
                if(!Objects.equals(maritalStatusID, ((Marital)e).getCode())){
                    
                    maritalStatusID = ((Marital) e).getCode();
                    jtMaritalStatus.setText(((Marital)e).toString());
                    try{
                        updatable.setMaritalStatus(maritalStatusID);
                    }catch(Exception ex){
                    
                    }
                    modify();
                }
            }
        }
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
