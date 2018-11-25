/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.StatusDisplayer;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.OutlineView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//Departments//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "DepartmentsTopComponent",
        iconBase = "systems/tech247/util/icons/settings.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "explorer", openAtStartup = false)
@ActionID(category = "PDR", id = "systems.tech247.pdr.DepartmentsTopComponent")
@ActionReference(path = "Menu/PDR" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_DepartmentsAction",
        preferredID = "DepartmentsTopComponent"
)
@Messages({
    "CTL_DepartmentsAction=Departments",
    "CTL_DepartmentsTopComponent=Departments Window",
    "HINT_DepartmentsTopComponent= "
})
public final class DepartmentsTopComponent extends TopComponent implements ExplorerManager.Provider {
    
    ExplorerManager em = new ExplorerManager();
    
        
    String searchString;
        
    
    
    String sql="SELECT r from OrganizationUnits r";
    public DepartmentsTopComponent() {
        initComponents();
        setName(Bundle.CTL_NationsTopComponent());
        setToolTipText(Bundle.HINT_ReligionsTopComponent());
        OutlineView ov = new OutlineView("Departments");
        ov.getOutline().setRootVisible(false);
        
        
        loadItems();
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(ov);
        associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
        
        jtDepartment.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
               searchString = jtDepartment.getText();
               sql ="SELECT r FROM OrganizationUnits r WHERE r.organizationUnitName LIKE '%"+searchString+"%'";
               
               loadItems();
            }

            @Override
            public void keyPressed(KeyEvent ke) {
               searchString = jtDepartment.getText();
               sql ="SELECT r FROM OrganizationUnits r WHERE r.organizationUnitName LIKE '%"+searchString+"%'";
               
               loadItems();
            }

            @Override
            public void keyReleased(KeyEvent ke) {
               searchString = jtDepartment.getText();
               sql ="SELECT r FROM OrganizationUnits r WHERE r.organizationUnitName LIKE '%"+searchString+"%'";
               
               loadItems(); 
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

        viewPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtDepartment = new javax.swing.JTextField();

        viewPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout viewPanelLayout = new javax.swing.GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );
        viewPanelLayout.setVerticalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DepartmentsTopComponent.class, "DepartmentsTopComponent.jLabel1.text")); // NOI18N

        jtDepartment.setText(org.openide.util.NbBundle.getMessage(DepartmentsTopComponent.class, "DepartmentsTopComponent.jtDepartment.text")); // NOI18N

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
                        .addComponent(jtDepartment)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jtDepartment;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
       
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
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
    public ExplorerManager getExplorerManager() {
        return em;
    }
    
    void loadItems(){
        em.setRootContext(new AbstractNode(Children.create(new FactoryDepartments(sql), true)));
    }
    
    
  }
