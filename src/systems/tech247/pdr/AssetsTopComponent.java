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
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//Assets//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "AssetsTopComponent",
        iconBase = "systems/tech247/util/icons/settings.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "explorer", openAtStartup = false)
@ActionID(category = "PDR", id = "systems.tech247.pdr.AssetsTopComponent")
@ActionReference(path = "Menu/PDR" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_AssetsAction",
        preferredID = "AssetsTopComponent"
)
@Messages({
    "CTL_AssetsAction=Company Assets",
    "CTL_AssetsTopComponent=Assets Window",
    "HINT_AssetsTopComponent= "
})
public final class AssetsTopComponent extends TopComponent implements ExplorerManager.Provider {
    
    ExplorerManager em = UtilityPDR.emAssignableAsset;
    
    String sqlString = "";
    String searchString;
    QueryCompanyAssets query = new QueryCompanyAssets();
    public AssetsTopComponent() {
        initComponents();
        setName(Bundle.CTL_AssetsTopComponent());
        setToolTipText(Bundle.HINT_AssetsTopComponent());
        OutlineView ov = new OutlineView("Company Assets");
        ov.getOutline().setRootVisible(false);
        
        ov.addPropertyColumn("select", "Select");
        sqlString ="SELECT * FROM CompanyAssets WHERE AssetCode NOT IN (SELECT DISTINCT AssetCode FROM CompanyAssetIssue )";
        UtilityPDR.loadAssignableAssets(sqlString);
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(ov);
        associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
        
        jAssettSearch.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
               
            }

            @Override
            public void keyPressed(KeyEvent ke) {
//               searchString = jAssettSearch.getText();
//               sqlString ="SELECT r FROM CompanyAssets r WHERE r.assetName LIKE '%"+searchString+"%'";
//               query.setSqlString(sqlString);
//               loadItems();
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                searchString = jAssettSearch.getText();
               sqlString ="SELECT * FROM CompanyAssets r WHERE r.assetName LIKE '%"+searchString+"%' AND r.AssetCode NOT IN (SELECT DISTINCT p.AssetCode FROM CompanyAssetIssue p)";
               UtilityPDR.loadAssignableAssets(sqlString);
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
        jAssettSearch = new javax.swing.JTextField();

        viewPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout viewPanelLayout = new javax.swing.GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );
        viewPanelLayout.setVerticalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(AssetsTopComponent.class, "AssetsTopComponent.jLabel1.text")); // NOI18N

        jAssettSearch.setBackground(new java.awt.Color(51, 255, 255));
        jAssettSearch.setText(org.openide.util.NbBundle.getMessage(AssetsTopComponent.class, "AssetsTopComponent.jAssettSearch.text")); // NOI18N

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
                        .addComponent(jAssettSearch)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jAssettSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jAssettSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
       sqlString ="SELECT * FROM CompanyAssets WHERE AssetCode NOT IN (SELECT DISTINCT AssetCode FROM CompanyAssetIssue )";
        query.setSqlString(sqlString);
        UtilityPDR.loadAssignableAssets(sqlString);
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
    
    
    
    
  }
