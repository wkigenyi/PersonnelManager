/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import javax.persistence.EntityManager;
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
import systems.tech247.hr.Currencies;
import systems.tech247.hr.Employees;
import systems.tech247.hr.JobPositions;
import systems.tech247.hr.Locations;

import systems.tech247.hr.OrganizationUnits;
import systems.tech247.pdr.UtilityPDR;

/**
 *
 * @author Admin
 */
public final class EmpEmploymentPanel extends javax.swing.JPanel implements LookupListener{

    Employees emp;
    
    String empCode = null;
    Date employmentDate = null;
    Date emplomentValidDate = null;
    JobPositions jobID = null;
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
    DataAccess da = new DataAccess();
    
    EntityManager entityManager = DataAccess.getEntityManager();
    TopComponent empTc = WindowManager.getDefault().findTopComponent("EmployeesTopComponent");
    Lookup.Result<Employees> empRslt = empTc.getLookup().lookupResult(Employees.class);
    
        
    TopComponent currencyTc = WindowManager.getDefault().findTopComponent("CurrenciesTopComponent");
    Lookup.Result<Currencies> currencyRslt = currencyTc.getLookup().lookupResult(Currencies.class);
    TopComponent positionTc = WindowManager.getDefault().findTopComponent("PositionsTopComponent");
    Lookup.Result<JobPositions> positionRslt = positionTc.getLookup().lookupResult(JobPositions.class);
    TopComponent departmentTc = WindowManager.getDefault().findTopComponent("DepartmentsTopComponent");
    Lookup.Result<OrganizationUnits> departmentRslt = departmentTc.getLookup().lookupResult(OrganizationUnits.class);
    
    TopComponent locationTc = WindowManager.getDefault().findTopComponent("LocationsTopComponent");
    Lookup.Result<Locations> locationRslt = locationTc.getLookup().lookupResult(Locations.class);
    
    NumberFormat nf =  new DecimalFormat("#,###.00");
    
    
    
    
    /**
     * Creates new form PersonalInfoPanel
     */
    public EmpEmploymentPanel() {
        initComponents();
        //Start transaction
        
        empRslt.addLookupListener(this);
        resultChanged(new LookupEvent(empRslt));
        
        currencyRslt.addLookupListener(this);
        resultChanged(new LookupEvent(currencyRslt));
        
        locationRslt.addLookupListener(this);
        resultChanged(new LookupEvent(locationRslt));
        
        
        positionRslt.addLookupListener(this);
        resultChanged(new LookupEvent(positionRslt));
        
        departmentRslt.addLookupListener(this);
        resultChanged(new LookupEvent(departmentRslt));
        
        
        
        jtPosition.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(positionTc, "Search Positions",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        
        jtCurrency.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(currencyTc, "Select Currency",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jtDepartment.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(departmentTc, "Search Departments",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }
        });
        
        jtStation.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(locationTc, "Search Location",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }
        });
        
        
        jdcEmploymentDate.getJCalendar().getDayChooser().setDayBordersVisible(true);
        
        jdcEmploymentDate.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getSource()==jdcEmploymentDate && "date".equals(evt.getPropertyName()) && !autoFilling){
                    employmentDate = jdcEmploymentDate.getDate();
                    modify();
                }
            }
        });
        
        jdcEmploymentDate.addKeyListener(new KeyListener() {
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
        jdcEmploymentDate.getJCalendar().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("ancestor")){
                    autoFilling = false;
                }
            }
        });
        
        
        
        
        jdcEmploymentValid.getJCalendar().getDayChooser().setDayBordersVisible(true);
        
        jdcEmploymentValid.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getSource()==jdcEmploymentValid && "date".equals(evt.getPropertyName()) && !autoFilling){
                    emplomentValidDate = jdcEmploymentValid.getDate();
                    
                    StatusDisplayer.getDefault().setStatusText("AUTO Filling: "+autoFilling);
                    modify();
                }
            }
        });
        
        jdcEmploymentValid.addKeyListener(new KeyListener() {
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
        
        jdcEmploymentValid.getJCalendar().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("ancestor")){
                    autoFilling = false;
                }
            }
        });
        
        
        
        jtBasic.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                 basicPayString = jtBasic.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                 basicPayString = jtBasic.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                 basicPayString = jtBasic.getText();
                modify();
            }
        });
        
        jtHousing.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                housingString = jtHousing.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                housingString = jtHousing.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                housingString = jtHousing.getText();
            }
        });
        
        jtEmployeeID.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                empCode = jtEmployeeID.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                empCode = jtEmployeeID.getText();
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                empCode = jtEmployeeID.getText();
                modify();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        //jtBasic.addPropertyChangeListener("value",this);
        
        
        disableInput();
        
        
    }
    
    void disableInput(){
        boolean shl = null != emp;
        Component[] comps = getComponents();
        for(Component c: comps){
           
                c.setEnabled(shl);
                if( c instanceof Container){
                    Component[] innerComponents = ((Container) c).getComponents();
                    for (Component e : innerComponents ){
                        e.setEnabled(shl);
                    }
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

        jLabel1 = new javax.swing.JLabel();
        jtEmployeeID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtPosition = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtStation = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jdcEmploymentDate = new com.toedter.calendar.JDateChooser();
        jdcEmploymentValid = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        JDPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtDepartment = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jtCurrency = new javax.swing.JTextField();
        jtBasic = new javax.swing.JFormattedTextField();
        jtHousing = new javax.swing.JFormattedTextField();
        jtBonus = new javax.swing.JFormattedTextField();
        jtBonusPercentage = new javax.swing.JFormattedTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jLabel1.text")); // NOI18N

        jtEmployeeID.setText(org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jtEmployeeID.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jLabel3.text")); // NOI18N

        jtPosition.setBackground(new java.awt.Color(0, 204, 0));
        jtPosition.setText(org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jtPosition.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jLabel7.text")); // NOI18N

        jtStation.setBackground(new java.awt.Color(0, 204, 0));
        jtStation.setText(org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jtStation.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jLabel9.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jLabel10.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jLabel12.text")); // NOI18N

        JDPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout JDPanelLayout = new javax.swing.GroupLayout(JDPanel);
        JDPanel.setLayout(JDPanelLayout);
        JDPanelLayout.setHorizontalGroup(
            JDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JDPanelLayout.setVerticalGroup(
            JDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jLabel11.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jLabel6.text")); // NOI18N

        jtDepartment.setBackground(new java.awt.Color(0, 204, 0));
        jtDepartment.setText(org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jtDepartment.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jLabel13.text")); // NOI18N

        jtCurrency.setBackground(new java.awt.Color(0, 204, 0));
        jtCurrency.setText(org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jtCurrency.text")); // NOI18N

        jtBasic.setColumns(10);
        jtBasic.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jtBasic.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtBasic.setText(org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jtBasic.text")); // NOI18N

        jtHousing.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jtHousing.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtHousing.setText(org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jtHousing.text")); // NOI18N

        jtBonus.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jtBonus.setText(org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jtBonus.text")); // NOI18N

        jtBonusPercentage.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtBonusPercentage.setText(org.openide.util.NbBundle.getMessage(EmpEmploymentPanel.class, "EmpEmploymentPanel.jtBonusPercentage.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtStation)
                    .addComponent(jtPosition)
                    .addComponent(jtEmployeeID)
                    .addComponent(jdcEmploymentDate, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jdcEmploymentValid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtDepartment)
                    .addComponent(jtCurrency)
                    .addComponent(jtBasic)
                    .addComponent(jtHousing)
                    .addComponent(jtBonus)
                    .addComponent(jtBonusPercentage))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JDPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 254, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel1)
                            .addComponent(jtEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(jdcEmploymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3)
                            .addComponent(jdcEmploymentValid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(jtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel6)
                            .addComponent(jtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel7)
                            .addComponent(jtStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel13)
                            .addComponent(jtCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jtBasic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jtHousing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jtBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jtBonusPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 143, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JDPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JDPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JDateChooser jdcEmploymentDate;
    private com.toedter.calendar.JDateChooser jdcEmploymentValid;
    private javax.swing.JFormattedTextField jtBasic;
    private javax.swing.JFormattedTextField jtBonus;
    private javax.swing.JFormattedTextField jtBonusPercentage;
    private javax.swing.JTextField jtCurrency;
    private javax.swing.JTextField jtDepartment;
    private javax.swing.JTextField jtEmployeeID;
    private javax.swing.JFormattedTextField jtHousing;
    private javax.swing.JTextField jtPosition;
    private javax.swing.JTextField jtStation;
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
                jtEmployeeID.setText( ((Employees) e).getEmpCode());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                   
                jdcEmploymentDate.setDate(((Employees) e).getDateOfEmployment());
                
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                              
                jdcEmploymentValid.setDate(((Employees) e).getEmploymentValidThro());
                
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtPosition.setText(((Employees) e).getPositionID().getPositionName());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                
                try{
                jtStation.setText(((Employees) e).getLocationID().getLocationName());
                }catch(NullPointerException ex){
                    jtStation.setText("");
                }
                
                
                try{
                jtDepartment.setText(((Employees) e).getOrganizationUnitID().getOrganizationUnitName());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                jtStation.setText(((Employees) e).getLocationID().getLocationName());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                try{
                
                String basic = nf.format(((Employees) e).getBasicPay());
                jtBasic.setText(basic);
                
                
                }catch(NullPointerException | IllegalArgumentException ex){
                    // When some info is missing
                }
                try{
                String housing = nf.format(((Employees) e).getHouseAllowance());
                jtHousing.setText(housing);
                
                }catch(NullPointerException | IllegalArgumentException ex){
                    // When some info is missing
                }
                try{
                String bonus = nf.format(((Employees) e).getAnnualBonus()+"");
                jtBonus.setText(bonus);
                }catch(NullPointerException | IllegalArgumentException ex){
                    // When some info is missing
                }
                //jtBonus.setText(((Employees) e).getAnnualBonus()+"");
                
                try{
                
                
                }catch(NullPointerException | IllegalArgumentException ex){
                    // When some info is missing
                }
                
                try{
                jtCurrency.setText(((Employees) e).getCurrencyID().getCurrencyName());
                }catch(NullPointerException ex){
                    // When some info is missing
                }
                
            }
            
            if(e instanceof OrganizationUnits){
                
                jtDepartment.setText(((OrganizationUnits) e).getOrganizationUnitName());
                ouID = (OrganizationUnits)e;
                modify();
            }
            
            if(e instanceof JobPositions){
                
                jtPosition.setText(((JobPositions) e).getPositionName());
                jobID = (JobPositions)e;
                modify();
            }
            
            if(e instanceof Currencies){
                
                jtCurrency.setText(((Currencies) e).getCurrencyName());
                currencyID = (Currencies)e;
                modify();
            }
            
            if(e instanceof Locations){
                
                stationID = (Locations)e;
                jtStation.setText(stationID.getLocationName());
                modify();
            }
            
            
            
            
            
            
        }
    }
    
    void modify(){
        
        if(getLookup().lookup(EmpInfoSavable.class)==null){
            UtilityPDR.editorIC.add(new EmpInfoSavable());
        }
    }

    
    public Lookup getLookup() {
        TopComponent tc = WindowManager.getDefault().findTopComponent("EmployeeEditorTopComponent");
        return tc.getLookup();
    }


    
    private class EmpInfoSavable extends AbstractSavable{

        public EmpInfoSavable(){
            register();
        } 
        
        @Override
        protected String findDisplayName() {
            return "Employment Info";
        }

        @Override
        protected void handleSave() throws IOException {
           
            UtilityPDR.editorIC.remove(this);
            entityManager.getTransaction().begin();
            Employees e = entityManager.find(Employees.class, emp.getEmployeeID());
            if(null != ouID){
            e.setOrganizationUnitID(ouID);
            }
            
            if(null != jobID){
            e.setPositionID(jobID);
            }
            
            if(null != currencyID){
            e.setCurrencyID(currencyID);
            }
            
            if(null != emplomentValidDate){
            e.setEmploymentValidThro(emplomentValidDate);
            }
            
            if(null != emplomentValidDate){
            e.setEmploymentValidThro(emplomentValidDate);
            }
            
            if(null != employmentDate){
            e.setDateOfEmployment(employmentDate);
            }
            
            if(null != basicPayString){
                basicPay = new BigDecimal(basicPayString, MathContext.DECIMAL32);
                e.setBasicPay(basicPay);
                
            }
            
            if(null != housingString){
                housing = new BigDecimal(housingString, MathContext.DECIMAL32);
                e.setHouseAllowance(housing);
                
            }
            
            if(null != stationID){
                e.setLocationID(stationID);
                
            }
            
            if(null != empCode){
                e.setEmpCode(empCode);
                
            }
            
            entityManager.getTransaction().commit();
            unregister();
            
            
            
        }
        
        EmpEmploymentPanel pnel(){
            return EmpEmploymentPanel.this;
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof EmpInfoSavable){
                EmpInfoSavable m = (EmpInfoSavable)o;
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
        jtEmployeeID.setText("");
   
        jtDepartment.setText("");
        jtCurrency.setText("");
        jtBonus.setText("");
        jtHousing.setText("");
     
        jtBasic.setText("");
        jtStation.setText("");
        jtPosition.setText("");
        jdcEmploymentDate.setDate(null);
        jdcEmploymentValid.setDate(null);
        
       
        empCode = null;
        employmentDate = null;
        emplomentValidDate = null;
        jobID = null;
        ouID = null;
        stationID = null;
        currencyID = null;
        basicPay = null;
        housing = null;
        annualBonus = null;
        bonusPercentage = null;
        emp = null;
        autoFilling = true;
    }
}
