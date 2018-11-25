/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.io.IOException;
import javax.persistence.EntityManager;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Employees;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//EmployeeCheckList//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmployeeCheckListTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)


@Messages({
    "CTL_EmployeeCheckListAction=Check List",
    "CTL_EmployeeCheckListTopComponent=Check List",
    "HINT_EmployeeCheckListTopComponent=Check List"
})
public final class EmployeeCheckListTopComponent extends TopComponent{
    
    InstanceContent ic = new InstanceContent();

    Employees updatable;
    DataAccess da = new DataAccess();
    EntityManager entityManager = DataAccess.getEntityManager();
    Employees emp;
    
    
    
    
    
    public EmployeeCheckListTopComponent(){
        this(null);
    }

    public EmployeeCheckListTopComponent(Employees e) {
        initComponents();
        setName(Bundle.CTL_EmployeeCheckListTopComponent());
        setToolTipText(Bundle.HINT_EmployeeCheckListTopComponent());
        fillEmployee(e);
        
        emp = e;
        
        if(null!=e){
            updatable = entityManager.find(Employees.class, e.getEmployeeID());
        }
        
        

        

        
        
        

        

        

        

        
        

        
        
        
        
        
        


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbCvCopy = new javax.swing.JCheckBox();
        jcbApplicationForm = new javax.swing.JCheckBox();
        jcbTestimonials = new javax.swing.JCheckBox();
        jcbMedicalReport = new javax.swing.JCheckBox();
        jcbSignedTA = new javax.swing.JCheckBox();
        jcbBankDetails = new javax.swing.JCheckBox();
        jcbemployeeHandBook = new javax.swing.JCheckBox();
        jcbAppointmentLetter = new javax.swing.JCheckBox();
        jcbIDCopy = new javax.swing.JCheckBox();
        jcbNHIF = new javax.swing.JCheckBox();
        jcbNSSFCopy = new javax.swing.JCheckBox();
        jcbTIN = new javax.swing.JCheckBox();
        jcbJobDescription = new javax.swing.JCheckBox();
        jcbJobTraining = new javax.swing.JCheckBox();
        jcbOrientation = new javax.swing.JCheckBox();
        jcbRefereeCheck = new javax.swing.JCheckBox();
        jcbTaskBreakdown = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(jcbCvCopy, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbCvCopy.text")); // NOI18N
        jcbCvCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbCvCopyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbApplicationForm, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbApplicationForm.text")); // NOI18N
        jcbApplicationForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbApplicationFormActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbTestimonials, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbTestimonials.text")); // NOI18N
        jcbTestimonials.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTestimonialsActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbMedicalReport, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbMedicalReport.text")); // NOI18N
        jcbMedicalReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMedicalReportActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbSignedTA, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbSignedTA.text")); // NOI18N
        jcbSignedTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSignedTAActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbBankDetails, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbBankDetails.text")); // NOI18N
        jcbBankDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbBankDetailsActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbemployeeHandBook, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbemployeeHandBook.text")); // NOI18N
        jcbemployeeHandBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbemployeeHandBookActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbAppointmentLetter, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbAppointmentLetter.text")); // NOI18N
        jcbAppointmentLetter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAppointmentLetterActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbIDCopy, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbIDCopy.text")); // NOI18N
        jcbIDCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbIDCopyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbNHIF, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbNHIF.text")); // NOI18N
        jcbNHIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbNHIFActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbNSSFCopy, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbNSSFCopy.text")); // NOI18N
        jcbNSSFCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbNSSFCopyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbTIN, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbTIN.text")); // NOI18N
        jcbTIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTINActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbJobDescription, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbJobDescription.text")); // NOI18N
        jcbJobDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbJobDescriptionActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbJobTraining, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbJobTraining.text")); // NOI18N
        jcbJobTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbJobTrainingActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbOrientation, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbOrientation.text")); // NOI18N
        jcbOrientation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbOrientationActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbRefereeCheck, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbRefereeCheck.text")); // NOI18N
        jcbRefereeCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRefereeCheckActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbTaskBreakdown, org.openide.util.NbBundle.getMessage(EmployeeCheckListTopComponent.class, "EmployeeCheckListTopComponent.jcbTaskBreakdown.text")); // NOI18N
        jcbTaskBreakdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTaskBreakdownActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbCvCopy)
                    .addComponent(jcbApplicationForm)
                    .addComponent(jcbTestimonials)
                    .addComponent(jcbMedicalReport)
                    .addComponent(jcbSignedTA)
                    .addComponent(jcbBankDetails)
                    .addComponent(jcbemployeeHandBook)
                    .addComponent(jcbAppointmentLetter)
                    .addComponent(jcbTaskBreakdown))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbIDCopy)
                    .addComponent(jcbNHIF)
                    .addComponent(jcbNSSFCopy)
                    .addComponent(jcbTIN)
                    .addComponent(jcbJobDescription)
                    .addComponent(jcbJobTraining)
                    .addComponent(jcbOrientation)
                    .addComponent(jcbRefereeCheck))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcbIDCopy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbNHIF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbNSSFCopy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbTIN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbJobDescription)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbJobTraining)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbOrientation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbRefereeCheck))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcbCvCopy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbApplicationForm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbTestimonials)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbMedicalReport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbSignedTA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbBankDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbemployeeHandBook)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbAppointmentLetter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbTaskBreakdown)))
                .addContainerGap(168, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbCvCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbCvCopyActionPerformed
        updatable.setCVCopy(jcbCvCopy.isSelected());
        modify();
    }//GEN-LAST:event_jcbCvCopyActionPerformed

    private void jcbApplicationFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbApplicationFormActionPerformed
        updatable.setApplicationForm(jcbApplicationForm.isSelected());
        modify();
    }//GEN-LAST:event_jcbApplicationFormActionPerformed

    private void jcbTestimonialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTestimonialsActionPerformed
        updatable.setTestimonials(jcbTestimonials.isSelected());
        modify();
    }//GEN-LAST:event_jcbTestimonialsActionPerformed

    private void jcbMedicalReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMedicalReportActionPerformed
        updatable.setMedicalReport(jcbMedicalReport.isSelected());
        modify();
    }//GEN-LAST:event_jcbMedicalReportActionPerformed

    private void jcbSignedTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSignedTAActionPerformed
        updatable.setSignedTermsAndConditions(jcbSignedTA.isSelected());
        modify();
    }//GEN-LAST:event_jcbSignedTAActionPerformed

    private void jcbBankDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbBankDetailsActionPerformed
        updatable.setBankDetailsForm(jcbBankDetails.isSelected());
        modify();
    }//GEN-LAST:event_jcbBankDetailsActionPerformed

    private void jcbemployeeHandBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbemployeeHandBookActionPerformed
        updatable.setEmployeeHandbook(jcbemployeeHandBook.isSelected());
        modify();
    }//GEN-LAST:event_jcbemployeeHandBookActionPerformed

    private void jcbAppointmentLetterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAppointmentLetterActionPerformed
        updatable.setSignedAppointmentLetter(jcbAppointmentLetter.isSelected());
        modify();
    }//GEN-LAST:event_jcbAppointmentLetterActionPerformed

    private void jcbTaskBreakdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTaskBreakdownActionPerformed
        updatable.setTaskBreakdown(jcbTaskBreakdown.isSelected());
        modify();
    }//GEN-LAST:event_jcbTaskBreakdownActionPerformed

    private void jcbIDCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbIDCopyActionPerformed
        updatable.setIDCardCopy(jcbIDCopy.isSelected());
        modify();
    }//GEN-LAST:event_jcbIDCopyActionPerformed

    private void jcbNHIFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbNHIFActionPerformed
        updatable.setNHIFCopy(jcbNHIF.isSelected());
        modify();
    }//GEN-LAST:event_jcbNHIFActionPerformed

    private void jcbNSSFCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbNSSFCopyActionPerformed
        updatable.setNSSFCopy(jcbNSSFCopy.isSelected());
        modify();
    }//GEN-LAST:event_jcbNSSFCopyActionPerformed

    private void jcbTINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTINActionPerformed
        updatable.setPINCopy(jcbTIN.isSelected());
        modify();
    }//GEN-LAST:event_jcbTINActionPerformed

    private void jcbJobDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbJobDescriptionActionPerformed
        updatable.setJobDescription(jcbJobDescription.isSelected());
        modify();
    }//GEN-LAST:event_jcbJobDescriptionActionPerformed

    private void jcbJobTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbJobTrainingActionPerformed
        updatable.setJobTraining(jcbJobTraining.isSelected());
        modify();
    }//GEN-LAST:event_jcbJobTrainingActionPerformed

    private void jcbOrientationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbOrientationActionPerformed
        updatable.setOrientation(jcbOrientation.isSelected());
        modify();
    }//GEN-LAST:event_jcbOrientationActionPerformed

    private void jcbRefereeCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRefereeCheckActionPerformed
        updatable.setRefereesCheck(jcbRefereeCheck.isSelected());
        modify();
    }//GEN-LAST:event_jcbRefereeCheckActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jcbApplicationForm;
    private javax.swing.JCheckBox jcbAppointmentLetter;
    private javax.swing.JCheckBox jcbBankDetails;
    private javax.swing.JCheckBox jcbCvCopy;
    private javax.swing.JCheckBox jcbIDCopy;
    private javax.swing.JCheckBox jcbJobDescription;
    private javax.swing.JCheckBox jcbJobTraining;
    private javax.swing.JCheckBox jcbMedicalReport;
    private javax.swing.JCheckBox jcbNHIF;
    private javax.swing.JCheckBox jcbNSSFCopy;
    private javax.swing.JCheckBox jcbOrientation;
    private javax.swing.JCheckBox jcbRefereeCheck;
    private javax.swing.JCheckBox jcbSignedTA;
    private javax.swing.JCheckBox jcbTIN;
    private javax.swing.JCheckBox jcbTaskBreakdown;
    private javax.swing.JCheckBox jcbTestimonials;
    private javax.swing.JCheckBox jcbemployeeHandBook;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {

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

    
    
    void fillEmployee(Employees emp){
                Employees e = DataAccess.entityManager.find(Employees.class, emp.getEmployeeID());
                setName(e.getSurName()+" "+e.getOtherNames()+ " > Check List");
                
                
                
                try{
                jcbCvCopy.setSelected(e.getCVCopy());
                }catch(Exception ex){
                    
                }
                try{
                jcbApplicationForm.setSelected(e.getApplicationForm());
                }catch(Exception ex){
                    
                }
                
                try{
                jcbTestimonials.setSelected(e.getTestimonials());
                }catch(Exception ex){
                    
                }
                try{
                jcbMedicalReport.setSelected(e.getMedicalReport());
                }catch(Exception ex){
                    
                }
                
               
                
                
                
                try{
                jcbSignedTA.setSelected(e.getSignedTermsAndConditions());
                }catch(Exception ex){
                    
                }
                try{
                jcbBankDetails.setSelected(e.getBankDetailsForm());
                }catch(Exception ex){
                    
                }
                
                
                try{
                jcbemployeeHandBook.setSelected(e.getEmployeeHandbook());
                }catch(Exception ex){
                    
                }
                
                try{
                jcbAppointmentLetter.setSelected(e.getSignedAppointmentLetter());
                }catch(Exception ex){
                    
                }
                try{
                jcbIDCopy.setSelected(e.getIDCardCopy());
                }catch(Exception ex){
                    
                }
                
                try{
                jcbNHIF.setSelected(e.getNHIFCopy());
                }catch(Exception ex){
                    
                }
                
                try{
                jcbNSSFCopy.setSelected(e.getNSSFCopy());
                }catch(Exception ex){
                    
                }
                
                try{
                jcbTIN.setSelected(e.getPINCopy());
                }catch(Exception ex){
                    
                }
                
                try{
                jcbJobDescription.setSelected(e.getJobDescription());
                }catch(Exception ex){
                    
                }
                
                try{
                jcbJobTraining.setSelected(e.getJobTraining());
                }catch(Exception ex){
                    
                }
                
                try{
                jcbOrientation.setSelected(e.getOrientation());
                }catch(Exception ex){
                    
                }
                
                try{
                jcbRefereeCheck.setSelected(e.getRefereesCheck());
                }catch(Exception ex){
                    
                }
                try{
                jcbTaskBreakdown.setSelected(e.getTaskBreakdown());
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
        
        EmployeeCheckListTopComponent tc(){
            return EmployeeCheckListTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            
                //Creating a new Employee
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                tc().close();
                    
                
            
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