/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Currencies;
import systems.tech247.hr.EmployeeCategories;
import systems.tech247.hr.Employees;
import systems.tech247.hr.JobPositions;
import systems.tech247.hr.Locations;
import systems.tech247.hr.OrganizationUnits;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//EmploymentDetails//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmploymentDetailsTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)


@Messages({
    "CTL_EmploymentDetailsAction=EmploymentDetails",
    "CTL_EmploymentDetailsTopComponent=EmploymentDetails Window",
    "HINT_EmploymentDetailsTopComponent=This is a EmploymentDetails window"
})
public final class EmploymentDetailsTopComponent extends TopComponent implements LookupListener{
    
    InstanceContent ic = new InstanceContent();
    Employees emp;
    String empCode = null;
    Date employmentDate = null;
    Date emplomentValidDate = null;
    JobPositions jobID = null;
    EmployeeCategories catID = null;
    EmployeeCategories catid = null;
    OrganizationUnits ouID = null;
    Locations stationID = null;
    Currencies currencyID = null;
    BigDecimal basicPay = null;
    BigDecimal housing = null;
    String housingString = null;
    BigDecimal annualBonus = null;
    BigDecimal bonusPercentage = null;
    Boolean autoFilling = true;
    String basicPayString = null;
    Boolean isExpat  = false;
    Employees updatable;
    DataAccess da = new DataAccess();
    EntityManager entityManager = DataAccess.getEntityManager();
    
    TopComponent currencyTc = WindowManager.getDefault().findTopComponent("CurrenciesTopComponent");
    Lookup.Result<Currencies> currencyRslt = currencyTc.getLookup().lookupResult(Currencies.class);
    TopComponent positionTc = WindowManager.getDefault().findTopComponent("PositionsTopComponent");
    Lookup.Result<JobPositions> positionRslt = positionTc.getLookup().lookupResult(JobPositions.class);
    TopComponent departmentTc = WindowManager.getDefault().findTopComponent("DepartmentsTopComponent");
    Lookup.Result<OrganizationUnits> departmentRslt = departmentTc.getLookup().lookupResult(OrganizationUnits.class);
    TopComponent locationTc = WindowManager.getDefault().findTopComponent("LocationsTopComponent");
    Lookup.Result<Locations> locationRslt = locationTc.getLookup().lookupResult(Locations.class);
    TopComponent categoryTc = WindowManager.getDefault().findTopComponent("CategoriesTopComponent");
    Lookup.Result<EmployeeCategories> categoryRslt = categoryTc.getLookup().lookupResult(EmployeeCategories.class);
    
    
    
    
    public EmploymentDetailsTopComponent(){
        this(null);
    }

    public EmploymentDetailsTopComponent(Employees e) {
        initComponents();
        setName(Bundle.CTL_EmploymentDetailsTopComponent());
        setToolTipText(Bundle.HINT_EmploymentDetailsTopComponent());
        fillEmployee(e);
        
        emp = e;
        
        if(null!=e){
            updatable = entityManager.find(Employees.class, e.getEmployeeID());
        }
        
        
        currencyRslt.addLookupListener(this);
        resultChanged(new LookupEvent(currencyRslt));
        
        locationRslt.addLookupListener(this);
        resultChanged(new LookupEvent(locationRslt));
        
        
        positionRslt.addLookupListener(this);
        resultChanged(new LookupEvent(positionRslt));
        
        departmentRslt.addLookupListener(this);
        resultChanged(new LookupEvent(departmentRslt));
        
        categoryRslt.addLookupListener(this);
        resultChanged(new LookupEvent(categoryRslt));
        
        
        jtPosition.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(positionTc, "Select A Position",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
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
        
        
        
        
        jtCurrency.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(currencyTc, "Select Currency",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
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
        
        jtDepartment.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(departmentTc, "Select A Department",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));

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
        
        jtLocation.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(locationTc, "Search Location",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
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
        
        jtCategory.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(categoryTc, "Select Category",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
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
        
        
        jdcJoiningDate.getJCalendar().getDayChooser().setDayBordersVisible(true);
        
        jdcJoiningDate.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getSource()==jdcJoiningDate && "date".equals(evt.getPropertyName())){
                    employmentDate = jdcJoiningDate.getDate();
                    updatable.setDateOfEmployment(employmentDate);
                    modify();
                }
            }
        });
        
        
        
        
        
        
        jdcEmploymentValidity.getJCalendar().getDayChooser().setDayBordersVisible(true);
        
        jdcEmploymentValidity.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getSource()==jdcEmploymentValidity && "date".equals(evt.getPropertyName())){
                    emplomentValidDate = jdcEmploymentValidity.getDate();
                    
                    updatable.setEmploymentValidThro(emplomentValidDate);
                    modify();
                }
            }
        });

        
        
        
        jftBasic.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                basicPayString = jftBasic.getText();
                 try{
                     basicPay = new BigDecimal(basicPayString);
                     updatable.setBasicPay(basicPay);
                    modify();
                 }catch(Exception ex){
                     
                 }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                basicPayString = jftBasic.getText();
                 try{
                     basicPay = new BigDecimal(basicPayString);
                     updatable.setBasicPay(basicPay);
                    modify();
                 }catch(Exception ex){
                     
                 }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                basicPayString = jftBasic.getText();
                 try{
                     basicPay = new BigDecimal(basicPayString);
                     updatable.setBasicPay(basicPay);
                    modify();
                 }catch(Exception ex){
                     
                 }
            }
        });
        
        jftHousing.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                housingString = jftHousing.getText();
                 try{
                     housing = new BigDecimal(housingString);
                     updatable.setHouseAllowance(housing);
                    modify();
                 }catch(Exception ex){
                     
                 }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                housingString = jftHousing.getText();
                 try{
                     housing = new BigDecimal(housingString);
                     updatable.setHouseAllowance(housing);
                    modify();
                 }catch(Exception ex){
                     
                 }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                housingString = jftHousing.getText();
                 try{
                     housing = new BigDecimal(housingString);
                     updatable.setHouseAllowance(housing);
                    modify();
                 }catch(Exception ex){
                     
                 }
            }
        });
        
        jtEmployeeID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                empCode = jtEmployeeID.getText();
                updatable.setEmpCode(empCode);
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                empCode = jtEmployeeID.getText();
                updatable.setEmpCode(empCode);
                modify();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                empCode = jtEmployeeID.getText();
                updatable.setEmpCode(empCode);
                modify();
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtEmployeeID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtPosition = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtDepartment = new javax.swing.JTextField();
        jtLocation = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtCurrency = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jdcJoiningDate = new com.toedter.calendar.JDateChooser();
        jdcEmploymentValidity = new com.toedter.calendar.JDateChooser();
        jftBasic = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jtCategory = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jftHousing = new javax.swing.JFormattedTextField();
        jcbExpertriate = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jLabel1.text")); // NOI18N

        jtEmployeeID.setText(org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jtEmployeeID.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jLabel4.text")); // NOI18N

        jtPosition.setText(org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jtPosition.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jLabel5.text")); // NOI18N

        jtDepartment.setText(org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jtDepartment.text")); // NOI18N

        jtLocation.setText(org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jtLocation.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jLabel6.text")); // NOI18N

        jtCurrency.setText(org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jtCurrency.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jLabel7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jLabel8.text")); // NOI18N

        jftBasic.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jftBasic.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jftBasic.setText(org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jftBasic.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jLabel10.text")); // NOI18N

        jtCategory.setText(org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jtCategory.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jLabel11.text")); // NOI18N

        jftHousing.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jftHousing.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jftHousing.setText(org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jftHousing.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbExpertriate, org.openide.util.NbBundle.getMessage(EmploymentDetailsTopComponent.class, "EmploymentDetailsTopComponent.jcbExpertriate.text")); // NOI18N
        jcbExpertriate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbExpertriateActionPerformed(evt);
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
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbExpertriate)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jtEmployeeID, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addComponent(jdcJoiningDate, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                        .addComponent(jdcEmploymentValidity, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                        .addComponent(jtPosition, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addComponent(jtDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addComponent(jtLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addComponent(jtCurrency, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addComponent(jftBasic, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                        .addComponent(jtCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addComponent(jftHousing)))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jdcEmploymentValidity, jdcJoiningDate, jftBasic, jtCategory, jtCurrency, jtDepartment, jtEmployeeID, jtLocation, jtPosition});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jdcJoiningDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jdcEmploymentValidity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jftBasic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jftHousing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbExpertriate)
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbExpertriateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbExpertriateActionPerformed
        isExpat = jcbExpertriate.isSelected();
        updatable.setExpatriate(isExpat);
        modify();
    }//GEN-LAST:event_jcbExpertriateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JCheckBox jcbExpertriate;
    private com.toedter.calendar.JDateChooser jdcEmploymentValidity;
    private com.toedter.calendar.JDateChooser jdcJoiningDate;
    private javax.swing.JFormattedTextField jftBasic;
    private javax.swing.JFormattedTextField jftHousing;
    private javax.swing.JTextField jtCategory;
    private javax.swing.JTextField jtCurrency;
    private javax.swing.JTextField jtDepartment;
    private javax.swing.JTextField jtEmployeeID;
    private javax.swing.JTextField jtLocation;
    private javax.swing.JTextField jtPosition;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        currencyRslt.removeLookupListener(this);
        currencyRslt=null;
        
        locationRslt.removeLookupListener(this);
        locationRslt = null;
        
        
        positionRslt.removeLookupListener(this);
        positionRslt=null;
        
        departmentRslt.removeLookupListener(this);
        departmentRslt = null;
        
        categoryRslt.addLookupListener(this);
        categoryRslt = null;
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
            
            
            if(e instanceof OrganizationUnits){
                
                jtDepartment.setText(((OrganizationUnits) e).getOrganizationUnitName());
                ouID = (OrganizationUnits)e;
                updatable.setOrganizationUnitID(ouID);
                modify();
            }
            
            if(e instanceof JobPositions){
                
                jtPosition.setText(((JobPositions) e).getPositionName());
                jobID = (JobPositions)e;
                updatable.setPositionID(jobID);
                modify();
            }
            
            if(e instanceof Currencies){
                
                jtCurrency.setText(((Currencies) e).getCurrencyName());
                currencyID = (Currencies)e;
                updatable.setCurrencyID(currencyID);
                modify();
            }
            
            if(e instanceof Locations){
                
                stationID = (Locations)e;
                jtLocation.setText(stationID.getLocationName());
                updatable.setLocationID(stationID);
                modify();
            }
            
            if(e instanceof EmployeeCategories){
                
                catID = (EmployeeCategories)e;
                jtCategory.setText(catID.getCategoryName());
                updatable.setCategoryID(catID);
                modify();
            }
        }
    }
    
    void fillEmployee(Employees e){
        
                setName(e.getSurName()+" "+e.getOtherNames()+ " > Employment Details");
                jtEmployeeID.setText(e.getEmpCode());
                
                
                try{
                jtCategory.setText( e.getCategoryID().getCategoryName());
                }catch(Exception ex){
                    
                }
                try{
                jtCurrency.setText(e.getCurrencyID().getCurrencyName());
                }catch(Exception ex){
                    
                }
                
                try{
                jtDepartment.setText(e.getOrganizationUnitID().getOrganizationUnitName());
                }catch(Exception ex){
                    
                }
                try{
                    jtPosition.setText(e.getPositionID().getPositionName());
                }catch(Exception ex){
                    
                }
                
                jcbExpertriate.setSelected(e.getExpatriate());
                
                
                
                try{        
                jdcJoiningDate.setDate(e.getDateOfEmployment());
                }catch(Exception ex){
                    
                }
                try{
                jtLocation.setText(e.getLocationID().getLocationName());
                }catch(NullPointerException ex){
                    
                }
                
                
                try{
                jdcEmploymentValidity.setDate(e.getEmploymentValidThro());
                }catch(Exception ex){
                    
                }
                
                try{
                jftBasic.setValue(e.getBasicPay());
                }catch(Exception ex){
                    
                }
                try{
                    jftHousing.setValue(e.getHouseAllowance());
                }catch(Exception ex){
                    
                }
                
                
                
                //Set the photo   
             
                
                
                
                
        
                
    }
    
    void modify(){
        
        
                if(getLookup().lookup(EmpSavable.class)==null){
                            ic.add(new EmpSavable());
                }
            
    }
    
    private class EmpSavable extends AbstractSavable{
        
        EmpSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            
                return emp.getSurName()+" "+emp.getOtherNames();
            
        }
        
        EmploymentDetailsTopComponent tc(){
            return EmploymentDetailsTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            
                //Creating a new Employee
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                
                    
                
            
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof EmpSavable){
                EmpSavable e = (EmpSavable)o;
                return tc() == e.tc();
            }
            return false;        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
}
