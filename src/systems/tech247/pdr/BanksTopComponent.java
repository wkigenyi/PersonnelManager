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
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.OutlineView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;
import systems.tech247.pdreditors.BankEditorTopComponent;
import systems.tech247.util.CapCreatable;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//Banks//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "BanksTopComponent",
        iconBase = "systems/tech247/util/icons/bank.png",
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "explorer", openAtStartup = false)
@ActionID(category = "PDR", id = "systems.tech247.pdr.BanksTopComponent")
//@ActionReference(path = "Menu/PDR" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_BanksAction",
//        preferredID = "BanksTopComponent"
//)
@Messages({
    "CTL_BanksAction=Banks",
    "CTL_BanksTopComponent=Banks",
    "HINT_BanksTopComponent= "
})
public final class BanksTopComponent extends TopComponent implements ExplorerManager.Provider {
    
    ExplorerManager em = new ExplorerManager();
    
    String sqlString = "";
    String searchString;
    QueryBanks query = new QueryBanks();
    CapCreatable enableadd;
    InstanceContent content = new InstanceContent();
    Lookup lkp = new AbstractLookup(content);
    public BanksTopComponent() {
        this("");
    }
    
    public BanksTopComponent(String view) {
        initComponents();
        setName(Bundle.CTL_BanksTopComponent());
        setToolTipText(Bundle.CTL_BanksTopComponent());
        OutlineView ov = new OutlineView("Banks");
        ov.getOutline().setRootVisible(false);
        sqlString ="SELECT r from Banks r";
        query.setSqlString(sqlString);
        
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(ov);
        if(!view.equals("")){
            ov.addPropertyColumn("number", "Number Of Employees");
        }
        
        enableadd = new CapCreatable() {
            @Override
            public void create() {
                TopComponent tc = new BankEditorTopComponent();
                tc.open();
                tc.requestActive();
            }
        };
                
        content.add(enableadd);
        
        associateLookup( new ProxyLookup(ExplorerUtils.createLookup(em, getActionMap()),lkp));
        
        jtBankSearch.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
               searchString = jtBankSearch.getText();
               sqlString ="SELECT r FROM Banks r WHERE r.bankName LIKE '%"+searchString+"%'";
               query.setSqlString(sqlString);
               loadItems();
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtBankSearch = new javax.swing.JTextField();

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

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(BanksTopComponent.class, "BanksTopComponent.jLabel1.text")); // NOI18N

        jtBankSearch.setBackground(new java.awt.Color(51, 255, 255));
        jtBankSearch.setText(org.openide.util.NbBundle.getMessage(BanksTopComponent.class, "BanksTopComponent.jtBankSearch.text")); // NOI18N

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
                        .addComponent(jtBankSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtBankSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jtBankSearch;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        sqlString ="SELECT r FROM Banks r ";
               query.setSqlString(sqlString);
               loadItems();
       
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
        
        em.setRootContext(new AbstractNode(Children.create(new FactoryBanks(query,false), true)));
    }
    
    
  }
