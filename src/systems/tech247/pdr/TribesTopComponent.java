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
        dtd = "-//systems.tech247.pdr//Tribes//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "TribesTopComponent",
        iconBase = "systems/tech247/util/icons/tribe.png",
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "explorer", openAtStartup = false)
@ActionID(category = "PDR", id = "systems.tech247.pdr.TribesTopComponent")
//@ActionReference(path = "Menu/PDR" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_TribesAction",
//        preferredID = "TribesTopComponent"
//)
@Messages({
    "CTL_TribesAction=Tribes",
    "CTL_TribesTopComponent=Tribes Window",
    "HINT_TribesTopComponent=This is a Employees window"
})
public final class TribesTopComponent extends TopComponent implements ExplorerManager.Provider {
    
    ExplorerManager em = new ExplorerManager();
    
    String searchString = "";
    QueryTribe query = new QueryTribe();
    
    public TribesTopComponent(){
        this("");
    }    
    
    
    public TribesTopComponent(String view) {
        initComponents();
        setName(Bundle.CTL_TribesTopComponent());
        setToolTipText(Bundle.HINT_TribesTopComponent());
        OutlineView ov = new OutlineView("Tribes");
        ov.getOutline().setRootVisible(false);
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(ov);
        if(!view.equals("")){
            ov.addPropertyColumn("number", "Number Of Employees");
        }
        
        
        
        
        
        
        associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
        
        jtSearchTribe.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                searchString = jtSearchTribe.getText();
                
                String sqlString = "SELECT e FROM Tribes e WHERE e.tribe LIKE  '%"+searchString+"%' " ;
                query.setSqlString(sqlString);
                loadItems(query);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                searchString = jtSearchTribe.getText();
                
                String sqlString = "SELECT e FROM Tribes e WHERE e.tribe LIKE  '%"+searchString+"%' " ;
                query.setSqlString(sqlString);
                loadItems(query);
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                searchString = jtSearchTribe.getText();
                
                String sqlString = "SELECT e FROM Tribes e WHERE e.tribe LIKE  '%"+searchString+"%' " ;
                query.setSqlString(sqlString);
                loadItems(query);
                
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

        jtSearchTribe = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        viewPanel = new javax.swing.JPanel();

        jtSearchTribe.setBackground(new java.awt.Color(51, 255, 255));
        jtSearchTribe.setText(org.openide.util.NbBundle.getMessage(TribesTopComponent.class, "TribesTopComponent.jtSearchTribe.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(TribesTopComponent.class, "TribesTopComponent.jLabel1.text")); // NOI18N

        viewPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout viewPanelLayout = new javax.swing.GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        viewPanelLayout.setVerticalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtSearchTribe, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 165, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtSearchTribe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jtSearchTribe;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        //load
        String sqlString = "SELECT e FROM Tribes e " ;
                query.setSqlString(sqlString);
                loadItems(query);
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
    
    void loadItems(QueryTribe query){
        em.setRootContext(new AbstractNode(Children.create(new FactoryTribes(query), true)));
    }
    
    
  }
