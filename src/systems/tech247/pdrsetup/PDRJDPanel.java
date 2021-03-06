/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdrsetup;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.OutlineView;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.JobPositions;

import systems.tech247.pdr.UtilityPDR;

/**
 *
 * @author Admin
 */
public class PDRJDPanel extends javax.swing.JPanel implements ExplorerManager.Provider, LookupListener{
    
    ExplorerManager em = UtilityPDR.pdrJD;
    
    
    DataAccess da = new DataAccess();
    
    
    String sqlString;
    String searchString;
    
    EntityManager entityManager = DataAccess.getEntityManager();
    TopComponent positionTc = WindowManager.getDefault().findTopComponent("PositionsTopComponent");
    Lookup.Result<JobPositions> positionRslt = positionTc.getLookup().lookupResult(JobPositions.class);
    
    
    /**
     * Creates new form PersonalInfoPanel
     */
    public PDRJDPanel() {
        initComponents();
        //Start transaction
        OutlineView ov = new OutlineView("Job Descriptions");
        ov.getOutline().setRootVisible(true);
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(ov);
        
        
        positionRslt.addLookupListener(this);
        resultChanged(new LookupEvent(positionRslt));
        
        jtsearch.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(positionTc, "Search Positions",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                            }

            @Override
            public void keyReleased(KeyEvent e) {
                
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

        viewPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtsearch = new javax.swing.JTextField();

        viewPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout viewPanelLayout = new javax.swing.GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );
        viewPanelLayout.setVerticalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PDRJDPanel.class, "PDRJDPanel.jLabel1.text")); // NOI18N

        jtsearch.setBackground(new java.awt.Color(51, 255, 255));
        jtsearch.setText(org.openide.util.NbBundle.getMessage(PDRJDPanel.class, "PDRJDPanel.jtsearch.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtsearch)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jtsearch;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables

      
    

    
    

    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result<JobPositions> result = (Lookup.Result<JobPositions>)ev.getSource();
        for(JobPositions position: result.allInstances()){
            jtsearch.setText(position.getPositionName());
            UtilityPDR.loadJD(position);
        }
    }
    
    
    
    
    
    
    
    
    
        
        
        
        
    

    
}
