/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Religions;
import systems.tech247.pdr.UtilityPDR;


/**
 *
 * @author Admin
 */
public class PDRReligionEditor extends javax.swing.JPanel{

    
    
    DataAccess da = new DataAccess();
    Religions religion;
    
    
    
    
    //Updatables
  
    String religionString = null;
  
    EntityManager entityManager = da.getEntityManager();
    
   
    /**
     * Creates new form PersonalInfoPanel
     */
    public PDRReligionEditor(){
        this(null);
    }
    
    
    
    
 public PDRReligionEditor(Religions religion) {
        initComponents();
        //Start transaction
        this.religion = religion;
        
        if(religion != null){
            jtReligionName.setText(religion.getReligion());
            
            
        }
        
        jtReligionName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                religionString = jtReligionName.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
religionString = jtReligionName.getText();            
            }

            @Override
            public void keyReleased(KeyEvent e) {
religionString = jtReligionName.getText();
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
        jLabel1 = new javax.swing.JLabel();
        jtReligionName = new javax.swing.JTextField();
        jbSave = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PDRReligionEditor.class, "PDRReligionEditor.jLabel1.text")); // NOI18N

        jtReligionName.setText(org.openide.util.NbBundle.getMessage(PDRReligionEditor.class, "PDRReligionEditor.jtReligionName.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtReligionName, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtReligionName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(PDRReligionEditor.class, "PDRReligionEditor.jbSave.text")); // NOI18N
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
        entityManager.getTransaction().begin();    
        
        if(religion == null){ //Add New Account
            String insertSQL = "INSERT INTO [dbo].[Religions]\n" +
"           ([religion])\n" +
"     VALUES\n" +
"           (?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, religionString);
            
            
            
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            StatusDisplayer.getDefault().setStatusText("Religion Added");
            String sqlString ="SELECT r from Religions r";
            UtilityPDR.loadReligions(sqlString);
        }else{
            
            Religions eba = entityManager.find(Religions.class, religion.getReligionID());
            
            if(religionString != null){
                eba.setReligion(religionString);
            }
            
           
            
            
            
            entityManager.getTransaction().commit();
            String sqlString ="SELECT r from Religions r";
            UtilityPDR.loadReligions(sqlString);
            
            StatusDisplayer.getDefault().setStatusText("Religion Saved");
            resetEditor();
        }
        

    }//GEN-LAST:event_jbSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbSave;
    private javax.swing.JTextField jtReligionName;
    // End of variables declaration//GEN-END:variables

    
    
    
    void modify(){
        
                jbSave.setEnabled(true);
            
        
    }

    
    public Lookup getLookup() {
        TopComponent tc = WindowManager.getDefault().findTopComponent("EmployeeEditorTopComponent");
        return tc.getLookup();
    }
    
    
    
    void resetEditor(){
        jtReligionName.setText("");
        religionString = null;
      
        
    }
    
    


}
