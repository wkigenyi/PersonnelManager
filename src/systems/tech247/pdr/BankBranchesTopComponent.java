/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.StatusDisplayer;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.OutlineView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;
import org.openide.windows.WindowManager;
import systems.tech247.hr.Banks;
import systems.tech247.pdreditors.BankBranchEditorTopComponent;
import systems.tech247.util.CapCreatable;


/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//BankBranches//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "BankBranchesTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdr.BankBranchesTopComponent")

@Messages({
    "CTL_BankBranchesAction=Bank Branches",
    "CTL_BankBranchesTopComponent=Bank Branches",
    "HINT_BankBranchesTopComponent="
})
public final class BankBranchesTopComponent extends TopComponent implements ExplorerManager.Provider, LookupListener {
    ExplorerManager em = new ExplorerManager();
    TopComponent tc = WindowManager.getDefault().findTopComponent("BanksTopComponent");
    Lookup.Result<Banks> bankRslt = tc.getLookup().lookupResult(Banks.class);
    InstanceContent content = new InstanceContent();
    Banks bank;
    AbstractLookup lkp = new AbstractLookup(content); 
    CapCreatable enableAddition;
    public BankBranchesTopComponent() {
        initComponents();
        setName(Bundle.CTL_BankBranchesTopComponent());
        setToolTipText(Bundle.HINT_BankBranchesTopComponent());
        OutlineView ov = new OutlineView("Bank Branch");
        ov.getOutline().setRootVisible(false);
        ov.addPropertyColumn("bank", "Bank");
        ov.addPropertyColumn("number", "Number");
        jpView.setLayout(new BorderLayout());
        jpView.add(ov);
        associateLookup(new ProxyLookup(ExplorerUtils.createLookup(em, getActionMap()),lkp));
        enableAddition = this::add;
        content.add(enableAddition);
        
        jtProduct.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectProduct();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jtProduct.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                selectProduct();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        load();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtProduct = new javax.swing.JTextField();
        jpView = new javax.swing.JPanel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(BankBranchesTopComponent.class, "BankBranchesTopComponent.jLabel1.text")); // NOI18N

        jtProduct.setText(org.openide.util.NbBundle.getMessage(BankBranchesTopComponent.class, "BankBranchesTopComponent.jtProduct.text")); // NOI18N

        jpView.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jpViewLayout = new javax.swing.GroupLayout(jpView);
        jpView.setLayout(jpViewLayout);
        jpViewLayout.setHorizontalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpViewLayout.setVerticalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 275, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jpView;
    private javax.swing.JTextField jtProduct;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        bankRslt.addLookupListener(this);
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
    
    void load(){
        if(bank != null){
            em.setRootContext(new AbstractNode(Children.create(new FactoryBankBranches(bank,false), true)));
        }else{
            em.setRootContext(new AbstractNode(Children.create(new FactoryBankBranches(), true)));
        }
    }
    void add(){
        
        if(bank == null){
            StatusDisplayer.getDefault().setStatusText("Select A Bank");
        }else{
        
        TopComponent tc = new BankBranchEditorTopComponent(bank);
        tc.open();
        tc.requestActive();
        }
        
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result<Banks> r = (Lookup.Result<Banks>)ev.getSource();
        for(Banks p : r.allInstances()){
            bank = p;
            jtProduct.setText(bank.getBankName());
            load();
        }
    }
    
    void selectProduct(){
        DialogDisplayer.getDefault().notify(new DialogDescriptor(tc, "Select A Bank"));
    }
    
    
}
