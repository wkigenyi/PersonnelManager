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
import systems.tech247.hr.Nationalities;
import systems.tech247.pdr.UtilityPDR;


/**
 *
 * @author Admin
 */
public class PDRNationalityEditor extends javax.swing.JPanel{

    
    
    DataAccess da = new DataAccess();
    Nationalities nation;
    
    
    
    
    //Updatables
  
    String nationString = null;
  
    EntityManager entityManager = da.getEntityManager();
    
   
    /**
     * Creates new form PersonalInfoPanel
     */
    public PDRNationalityEditor(){
        this(null);
    }
    
    
    
    
 public PDRNationalityEditor(Nationalities nation) {
        initComponents();
        //Start transaction
        this.nation = nation;
        
        if(nation != null){
            jtTribeName.setText(nation.getNationality());
            
            
        }
        
        jtTribeName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                nationString = jtTribeName.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
nationString = jtTribeName.getText();            
            }

            @Override
            public void keyReleased(KeyEvent e) {
nationString = jtTribeName.getText();
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
        jtTribeName = new javax.swing.JTextField();
        jbSave = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PDRNationalityEditor.class, "PDRNationalityEditor.jLabel1.text")); // NOI18N

        jtTribeName.setText(org.openide.util.NbBundle.getMessage(PDRNationalityEditor.class, "PDRNationalityEditor.jtTribeName.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtTribeName, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtTribeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(PDRNationalityEditor.class, "PDRNationalityEditor.jbSave.text")); // NOI18N
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
        
        if(nation == null){ //Add New Account
            String insertSQL = "INSERT INTO [dbo].[Nationalities]\n" +
"           ([nationality])\n" +
"     VALUES\n" +
"           (?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, nationString);
            
            
            
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            StatusDisplayer.getDefault().setStatusText("Nationality Saved");
            String sqlString ="SELECT r from Nationalities r";
             UtilityPDR.loadNationalities(sqlString);
        }else{
            
            Nationalities eba = entityManager.find(Nationalities.class, nation.getNationalityID());
            
            if(nationString != null){
                eba.setNationality(nationString);
            }
            
           
            
            
            
            entityManager.getTransaction().commit();
            String sqlString ="SELECT r from Nationalities r";
             UtilityPDR.loadNationalities(sqlString);
            StatusDisplayer.getDefault().setStatusText("Nationality Saved");
            resetEditor();
        }
        

    }//GEN-LAST:event_jbSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbSave;
    private javax.swing.JTextField jtTribeName;
    // End of variables declaration//GEN-END:variables

    
    
    
    void modify(){
        
                jbSave.setEnabled(true);
            
        
    }

    
    public Lookup getLookup() {
        TopComponent tc = WindowManager.getDefault().findTopComponent("EmployeeEditorTopComponent");
        return tc.getLookup();
    }
    
    
    
    void resetEditor(){
        jtTribeName.setText("");
        nationString = null;
      
        
    }
    
    


}