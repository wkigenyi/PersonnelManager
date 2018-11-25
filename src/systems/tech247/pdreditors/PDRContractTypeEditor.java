/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.PdContractTypes;
import systems.tech247.pdr.UtilityPDR;


/**
 *
 * @author Admin
 */
public class PDRContractTypeEditor extends javax.swing.JPanel{

    
    
    DataAccess da = new DataAccess();
    PdContractTypes ctype;
    boolean days = false;
    boolean weeks = false;
    boolean months = false;
    boolean years = true;
    
    boolean monday = false;
    boolean tuesday = false;
    boolean wednesday = false;
    boolean thursday = false;
    boolean friday = false;
    boolean saturday = false;
    boolean sunday = false;
    boolean chargeOvertime = false;
    
    String rate = null;
    BigDecimal overtime = new BigDecimal("0.00");
    String length = null;
    Long correLong = null;
    
    
    
    
    //Updatables
  
    String ctypeString = null;
  
    EntityManager entityManager = DataAccess.getEntityManager();
    
   
    /**
     * Creates new form PersonalInfoPanel
     */
    public PDRContractTypeEditor(){
        this(null);
    }
    
    
    
    
 public PDRContractTypeEditor(PdContractTypes ctype) {
        initComponents();
        //Start transaction
        this.ctype = ctype;
        
        if(ctype != null){
            jtContactType.setText(ctype.getDescription());
            jtLength.setText(ctype.getCorrespondingValue()+"");
            jtOvertime.setText(ctype.getOverTimeRate()+"");
            jcbChargeOvertime.setSelected(ctype.getChargeToOverTime());
            jrbDays.setSelected(ctype.getInDays());
            days = ctype.getInDays();
            jrbWeeks.setSelected(ctype.getInWeeks());
            weeks = ctype.getInWeeks();
            jrbMonths.setSelected(ctype.getInMonths());
            months = ctype.getInMonths();
            jrbYears.setSelected(ctype.getInYears());
            years = ctype.getInYears();
            
            jcbFriday.setSelected(ctype.getContractWorkingDays().contains("Fri"));
            friday = ctype.getContractWorkingDays().contains("Fri");
            jcbMonday.setSelected(ctype.getContractWorkingDays().contains("Mon"));
            monday = ctype.getContractWorkingDays().contains("Mon");
            jcbTuesday.setSelected(ctype.getContractWorkingDays().contains("Tue"));
            tuesday = ctype.getContractWorkingDays().contains("Tue");
            jcbSaturday.setSelected(ctype.getContractWorkingDays().contains("Sat"));
            saturday = ctype.getContractWorkingDays().contains("Sat");
            jcbSunday.setSelected(ctype.getContractWorkingDays().contains("Sun"));
            sunday = ctype.getContractWorkingDays().contains("Sun");
            jcbThurday.setSelected(ctype.getContractWorkingDays().contains("Thu"));
            thursday = ctype.getContractWorkingDays().contains("Thu");
            jcbWednesday.setSelected(ctype.getContractWorkingDays().contains("Wed"));
            wednesday = ctype.getContractWorkingDays().contains("Wed");
            chargeOvertime = ctype.getChargeToOverTime();
            
            
        }
        
        jtContactType.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ctypeString = jtContactType.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                ctypeString = jtContactType.getText();            
            }

            @Override
            public void keyReleased(KeyEvent e) {
                ctypeString = jtContactType.getText();
            }
        });
        
        jtOvertime.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                rate = jtOvertime.getText();
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                 rate = jtOvertime.getText();
                 
            }

            @Override
            public void keyReleased(KeyEvent e) {
                  rate = jtOvertime.getText();
                  
            }
        });
        
        jtLength.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                length = jtLength.getText();
                try{
                correLong = Long.parseLong(length);
                }catch(NumberFormatException ex){
                    
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                length = jtLength.getText();
                try{
                correLong = Long.parseLong(length);
                }catch(NumberFormatException ex){
                    
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                length = jtLength.getText();
                try{
                correLong = Long.parseLong(length);
                }catch(NumberFormatException ex){
                    
                }
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

        bgPeriodUnits = new javax.swing.ButtonGroup();
        jTextField2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtContactType = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jrbDays = new javax.swing.JRadioButton();
        jrbWeeks = new javax.swing.JRadioButton();
        jrbMonths = new javax.swing.JRadioButton();
        jrbYears = new javax.swing.JRadioButton();
        jtLength = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jcbMonday = new javax.swing.JCheckBox();
        jcbFriday = new javax.swing.JCheckBox();
        jcbSaturday = new javax.swing.JCheckBox();
        jcbTuesday = new javax.swing.JCheckBox();
        jcbSunday = new javax.swing.JCheckBox();
        jcbWednesday = new javax.swing.JCheckBox();
        jcbThurday = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jcbChargeOvertime = new javax.swing.JCheckBox();
        jtOvertime = new javax.swing.JFormattedTextField();
        jbSave = new javax.swing.JButton();

        jTextField2.setText(org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jTextField2.text")); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jLabel1.text")); // NOI18N

        jtContactType.setText(org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jtContactType.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jLabel2.text")); // NOI18N

        bgPeriodUnits.add(jrbDays);
        org.openide.awt.Mnemonics.setLocalizedText(jrbDays, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jrbDays.text")); // NOI18N
        jrbDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbDaysActionPerformed(evt);
            }
        });

        bgPeriodUnits.add(jrbWeeks);
        org.openide.awt.Mnemonics.setLocalizedText(jrbWeeks, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jrbWeeks.text")); // NOI18N
        jrbWeeks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbWeeksActionPerformed(evt);
            }
        });

        bgPeriodUnits.add(jrbMonths);
        org.openide.awt.Mnemonics.setLocalizedText(jrbMonths, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jrbMonths.text")); // NOI18N
        jrbMonths.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMonthsActionPerformed(evt);
            }
        });

        bgPeriodUnits.add(jrbYears);
        jrbYears.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jrbYears, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jrbYears.text")); // NOI18N
        jrbYears.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbYearsActionPerformed(evt);
            }
        });

        jtLength.setText(org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jtLength.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jLabel4.text")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        org.openide.awt.Mnemonics.setLocalizedText(jcbMonday, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jcbMonday.text")); // NOI18N
        jcbMonday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMondayActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbFriday, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jcbFriday.text")); // NOI18N
        jcbFriday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFridayActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbSaturday, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jcbSaturday.text")); // NOI18N
        jcbSaturday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSaturdayActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbTuesday, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jcbTuesday.text")); // NOI18N
        jcbTuesday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTuesdayActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbSunday, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jcbSunday.text")); // NOI18N
        jcbSunday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSundayActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbWednesday, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jcbWednesday.text")); // NOI18N
        jcbWednesday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbWednesdayActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbThurday, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jcbThurday.text")); // NOI18N
        jcbThurday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbThurdayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jcbMonday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbTuesday))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jcbFriday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbSaturday)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jcbSunday)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jcbWednesday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbThurday))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbMonday)
                    .addComponent(jcbTuesday)
                    .addComponent(jcbWednesday)
                    .addComponent(jcbThurday))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbFriday)
                    .addComponent(jcbSaturday)
                    .addComponent(jcbSunday)))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jcbChargeOvertime, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jcbChargeOvertime.text")); // NOI18N
        jcbChargeOvertime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbChargeOvertimeActionPerformed(evt);
            }
        });

        jtOvertime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jtOvertime.setText(org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jtOvertime.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtContactType)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jrbDays)
                        .addGap(27, 27, 27)
                        .addComponent(jrbWeeks)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jrbMonths)
                        .addGap(18, 18, 18)
                        .addComponent(jrbYears))
                    .addComponent(jtLength)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtOvertime))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcbChargeOvertime)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtContactType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbDays)
                    .addComponent(jrbWeeks)
                    .addComponent(jrbMonths)
                    .addComponent(jrbYears)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtOvertime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbChargeOvertime)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(PDRContractTypeEditor.class, "PDRContractTypeEditor.jbSave.text")); // NOI18N
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbSave, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbSave)
                .addGap(8, 8, 8))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveActionPerformed
       StringBuilder sb = new StringBuilder();
       if(monday){
           sb.append("Mon,");
       }
       if(tuesday){
           sb.append("Tue,");
       }
       if(wednesday){
           sb.append("Wed,");
       }
       if(thursday){
           sb.append("Thur,");
       }
       if(friday){
           sb.append("Fri,");
       }
        
       if(saturday){
           sb.append("Sat,");
       }
       if(sunday){
           sb.append("Sun,");
       }
       if(null != rate){
          overtime = new BigDecimal(rate);
       }
        
       
       entityManager.getTransaction().begin();    
        
        if(ctype == null){ //Add New Contract Type
            String insertSQL = "INSERT INTO [dbo].[pdContractTypes]\n" +
"           ([Description]\n" +
"           ,[InDays]\n" +
"           ,[InWeeks]\n" +
"           ,[InMonths]\n" +
"           ,[InYears]\n" +
"           ,[CorrespondingValue]\n" +
"           ,[ContractWorkingDays]\n" +
"           ,[ChargeToOverTime]\n" +
"           ,[OverTimeRate])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, ctypeString);
            query.setParameter(2, days);
            query.setParameter(3, weeks);
            query.setParameter(4, months);
            query.setParameter(5, years);
            query.setParameter(6, correLong);
            query.setParameter(7, sb.toString());
            query.setParameter(8, chargeOvertime);
            query.setParameter(9, overtime);
            
            
            
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            
            
            resetEditor();
            StatusDisplayer.getDefault().setStatusText("Contract Type Added");
            
            String sqlString ="SELECT r from PdContractTypes r";
            UtilityPDR.loadContractTypes(sqlString);
        }else{
            
            PdContractTypes eba = entityManager.find(PdContractTypes.class, ctype.getId());
            
            if(ctypeString != null){
                eba.setDescription(ctypeString);
            }
            if(correLong != null){
                eba.setCorrespondingValue(correLong);
            }
            if(overtime != null){
                eba.setOverTimeRate(overtime);
            }
            
            eba.setContractWorkingDays(sb.toString());
            eba.setChargeToOverTime(chargeOvertime);
            eba.setInDays(days);
            eba.setInWeeks(weeks);
            eba.setInMonths(months);
            eba.setInYears(years);
            
            
           
            
            
            
            entityManager.getTransaction().commit();
            StatusDisplayer.getDefault().setStatusText("Contract Type Saved");
            String sqlString ="SELECT r from PdContractTypes r";
            UtilityPDR.loadContractTypes(sqlString);
            resetEditor();
        }
        

    }//GEN-LAST:event_jbSaveActionPerformed

    private void jrbWeeksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbWeeksActionPerformed
        years = false;
        weeks = jrbWeeks.isSelected();
    }//GEN-LAST:event_jrbWeeksActionPerformed

    private void jrbMonthsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbMonthsActionPerformed
        years = false;
        months = jrbMonths.isSelected();
    }//GEN-LAST:event_jrbMonthsActionPerformed

    private void jrbYearsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbYearsActionPerformed
        years = jrbYears.isSelected();
    }//GEN-LAST:event_jrbYearsActionPerformed

    private void jrbDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbDaysActionPerformed
        years = false;
        days = jrbDays.isSelected();
    }//GEN-LAST:event_jrbDaysActionPerformed

    private void jcbMondayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMondayActionPerformed
        monday = jcbMonday.isSelected();
    }//GEN-LAST:event_jcbMondayActionPerformed

    private void jcbTuesdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTuesdayActionPerformed
        tuesday = jcbTuesday.isSelected();
    }//GEN-LAST:event_jcbTuesdayActionPerformed

    private void jcbWednesdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbWednesdayActionPerformed
        wednesday = jcbWednesday.isSelected();
    }//GEN-LAST:event_jcbWednesdayActionPerformed

    private void jcbThurdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbThurdayActionPerformed
        thursday = jcbThurday.isSelected();
    }//GEN-LAST:event_jcbThurdayActionPerformed

    private void jcbFridayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbFridayActionPerformed
        friday = jcbFriday.isSelected();
    }//GEN-LAST:event_jcbFridayActionPerformed

    private void jcbSaturdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSaturdayActionPerformed
        saturday = jcbSaturday.isSelected();
    }//GEN-LAST:event_jcbSaturdayActionPerformed

    private void jcbSundayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSundayActionPerformed
        sunday = jcbSunday.isSelected();
    }//GEN-LAST:event_jcbSundayActionPerformed

    private void jcbChargeOvertimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbChargeOvertimeActionPerformed
        chargeOvertime = jcbChargeOvertime.isSelected();
    }//GEN-LAST:event_jcbChargeOvertimeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgPeriodUnits;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton jbSave;
    private javax.swing.JCheckBox jcbChargeOvertime;
    private javax.swing.JCheckBox jcbFriday;
    private javax.swing.JCheckBox jcbMonday;
    private javax.swing.JCheckBox jcbSaturday;
    private javax.swing.JCheckBox jcbSunday;
    private javax.swing.JCheckBox jcbThurday;
    private javax.swing.JCheckBox jcbTuesday;
    private javax.swing.JCheckBox jcbWednesday;
    private javax.swing.JRadioButton jrbDays;
    private javax.swing.JRadioButton jrbMonths;
    private javax.swing.JRadioButton jrbWeeks;
    private javax.swing.JRadioButton jrbYears;
    private javax.swing.JTextField jtContactType;
    private javax.swing.JTextField jtLength;
    private javax.swing.JFormattedTextField jtOvertime;
    // End of variables declaration//GEN-END:variables

    
    
    
    void modify(){
        
                jbSave.setEnabled(true);
            
        
    }

    
    public Lookup getLookup() {
        TopComponent tc = WindowManager.getDefault().findTopComponent("EmployeeEditorTopComponent");
        return tc.getLookup();
    }
    
    
    
    void resetEditor(){
        jtContactType.setText("");
        ctypeString = null;
      
        
    }
    
    


}
