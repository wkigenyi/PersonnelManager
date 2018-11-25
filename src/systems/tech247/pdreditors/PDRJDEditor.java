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
import javax.swing.JButton;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.StatusDisplayer;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.JDCategories;
import systems.tech247.hr.JobPositionJDs;
import systems.tech247.hr.JobPositions;
import systems.tech247.pdr.UtilityPDR;


/**
 *
 * @author Admin
 */
public class PDRJDEditor extends javax.swing.JPanel implements LookupListener{

    
    
    DataAccess da = new DataAccess();
    JDCategories jdc;
    JobPositionJDs jd;
    JobPositions position;
    
    
    
    //Updatables
    String jdValue = null;
    JDCategories category;
    
    
    EntityManager entityManager = DataAccess.getEntityManager();
    
    TopComponent categoryTc = WindowManager.getDefault().findTopComponent("JDCategoryTopComponent");
    Lookup.Result<JDCategories> categoryRslt = categoryTc.getLookup().lookupResult(JDCategories.class);
   
    /**
     * Creates new form PersonalInfoPanel
     */
    public PDRJDEditor(JobPositions position){
        this(position, null, null);
    }
    
    public PDRJDEditor(JobPositions position, JDCategories category){
        this(position,category,null);
    }
    
    
 public PDRJDEditor(JobPositions position, JDCategories category, JobPositionJDs jdid) {
        initComponents();
        //Start transaction
        this.jdc = category;
        this.position = position;
        this.jd = jdid;
        
        if(jdc != null){
            jtCategoryName.setText(jdc.getCategoryName());
        }
        
        if(jd != null){
            jtValue.setText(jd.getFieldValue());
        }
        categoryRslt.addLookupListener(this);
        resultChanged(new LookupEvent(categoryRslt));
        
        
       jtValue.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                jdValue = jtValue.getText();
                modify();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                jdValue = jtValue.getText();
                modify();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                jdValue = jtValue.getText();
                modify();
            }
        });
        

        
        jtCategoryName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(categoryTc, "Select Category",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null)); 
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
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
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jtCategoryName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtValue = new javax.swing.JTextArea();
        jbSave = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PDRJDEditor.class, "PDRJDEditor.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PDRJDEditor.class, "PDRJDEditor.jLabel1.text")); // NOI18N

        jtCategoryName.setBackground(new java.awt.Color(0, 204, 0));
        jtCategoryName.setText(org.openide.util.NbBundle.getMessage(PDRJDEditor.class, "PDRJDEditor.jtCategoryName.text")); // NOI18N

        jtValue.setColumns(20);
        jtValue.setRows(5);
        jScrollPane1.setViewportView(jtValue);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSave, org.openide.util.NbBundle.getMessage(PDRJDEditor.class, "PDRJDEditor.jbSave.text")); // NOI18N
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbSave, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbSave)
                .addGap(8, 8, 8))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveActionPerformed
        entityManager.getTransaction().begin();    
        
        if(jd == null){ //Add New Account
            String insertSQL = "INSERT INTO [dbo].[JobPositionJDs]\n" +
"           ([PositionID]\n" +
"           ,[JDCategoryID]\n" +
"           ,[FieldValue]\n" +
"           ,[Deleted])\n" +
"     VALUES\n" +
"           (?,?,?,?)";
            Query query = entityManager.createNativeQuery(insertSQL);
            query.setParameter(1, position.getPositionID());
            if(null != jdc){
                query.setParameter(2, jdc.getJDCategoryID());
            }else if(null != category ){
                query.setParameter(2, category.getJDCategoryID());
            }
            if(jdValue != null){
                query.setParameter(3, jdValue);
            }
            query.setParameter(4, false);
            
            
            query.executeUpdate();
            entityManager.getTransaction().commit(); 
            
            resetEditor();
            //String sqlString ="SELECT r from OrganizationUnitTypes r";
            UtilityPDR.loadJD(position);
            StatusDisplayer.getDefault().setStatusText("JD Added");
        }else{
            
            JobPositionJDs eba = entityManager.find(JobPositionJDs.class, jd.getJobPositionJDID());
            
            
            
            if(null != category){
                eba.setJDCategoryID(category);
            }
            
            if(null != jdValue){
                eba.setFieldValue(jdValue);
            }
            
            
            
            
            
            
            
            entityManager.getTransaction().commit();
            StatusDisplayer.getDefault().setStatusText("JD Saved");
            
            //String sqlString ="SELECT r from JDCategories r";
            UtilityPDR.loadJD(position);
            
           
        }
        

    }//GEN-LAST:event_jbSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbSave;
    private javax.swing.JTextField jtCategoryName;
    private javax.swing.JTextArea jtValue;
    // End of variables declaration//GEN-END:variables

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result<JDCategories> result = (Lookup.Result<JDCategories>)le.getSource();
        for (JDCategories e: result.allInstances()){
            
                
                
               
                category = ((JDCategories) e);
                jtCategoryName.setText(category.getCategoryName());
                modify();
            
                
                
                
                
                
                
            }
            
            
            
            
            
            
            
            
            
        }
    
    
    void modify(){
        
                jbSave.setEnabled(true);
            
        
    }

    
    
    
    
    
    void resetEditor(){
        jtCategoryName.setText("");
        jtValue.setText("");
        
        
        
        
                
    }
    
    


}
