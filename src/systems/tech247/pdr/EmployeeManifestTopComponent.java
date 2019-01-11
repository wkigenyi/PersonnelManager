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
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;
import systems.tech247.util.CapCreatable;
import systems.tech247.util.QueryEmployee;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//EmlployeeManafest//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmlployeeManafestTopComponent",
        iconBase = "systems/tech247/util/icons/position.png", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdr.EmlployeeManafestTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_EmployeeManifestAction",
//        preferredID = "EmployeeManifestTopComponent"
//)
@Messages({
    "CTL_EmployeeManifestAction=EmployeeManifest",
    "CTL_EmployeeManifestTopComponent=Employee Manifest",
    "HINT_EmployeeManifestTopComponent=This is a EmployeeManifest"
})
public final class EmployeeManifestTopComponent extends TopComponent implements ExplorerManager.Provider {
    
    ExplorerManager em = new ExplorerManager();
    
    Boolean disengaged = false;
    String searchString = "";
    QueryEmployee query = new QueryEmployee();
    InstanceContent ic = new InstanceContent();
    Lookup lookup = new AbstractLookup(ic);

    public EmployeeManifestTopComponent() {
        initComponents();
        setName(Bundle.CTL_EmployeeManifestTopComponent());
        setToolTipText(Bundle.HINT_EmployeeManifestTopComponent());
        associateLookup(new ProxyLookup(lookup,ExplorerUtils.createLookup(em, getActionMap())));
        OutlineView ov = new OutlineView("Employee Manifest");
        jpView.setLayout(new BorderLayout());
        jpView.add(ov);
        ov.addPropertyColumn("othername", "Other Name");
        ov.addPropertyColumn("code", "P/R Code");
        ov.addPropertyColumn("dob", "Date Of Birth");
        ov.addPropertyColumn("gender", "Gender");
        ov.addPropertyColumn("dept", "Department");
        ov.addPropertyColumn("cat", "Category");
        
        ov.getOutline().setRootVisible(false);
        
        
        

        ic.add(new CapCreatable() {
            @Override
            public void create() {
                EmployeePersonalInfoEditorTopComponent newEditor = new EmployeePersonalInfoEditorTopComponent();
                newEditor.open();
                newEditor.requestActive();
            }
        });
            jtEmployeeName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //getTheStringAndReload();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //getTheStringAndReload();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                getTheStringAndReload();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

        jLabel1 = new javax.swing.JLabel();
        jtEmployeeName = new javax.swing.JTextField();
        jpView = new javax.swing.JPanel();
        jcbDisengaged = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EmployeeManifestTopComponent.class, "EmlployeeManafestTopComponent.jLabel1.text")); // NOI18N

        jtEmployeeName.setText(org.openide.util.NbBundle.getMessage(EmployeeManifestTopComponent.class, "EmlployeeManafestTopComponent.jtEmployeeName.text")); // NOI18N

        jpView.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jpViewLayout = new javax.swing.GroupLayout(jpView);
        jpView.setLayout(jpViewLayout);
        jpViewLayout.setHorizontalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpViewLayout.setVerticalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 189, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(jcbDisengaged, org.openide.util.NbBundle.getMessage(EmployeeManifestTopComponent.class, "EmlployeeManafestTopComponent.jcbDisengaged.text")); // NOI18N
        jcbDisengaged.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDisengagedActionPerformed(evt);
            }
        });

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
                        .addComponent(jtEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jcbDisengaged, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbDisengaged))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbDisengagedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDisengagedActionPerformed
        disengaged = jcbDisengaged.isSelected();
        searchString = jtEmployeeName.getText();
        String sqlString = "SELECT e FROM Employees e WHERE (e.surName LIKE  '%"+searchString+"%' OR e.otherNames LIKE  '%"+searchString+"%' OR e.empCode LIKE  '%"+searchString+"%') AND e.isDisengaged =  '"+disengaged+"'  " ;
        query.setSqlString(sqlString);
        loadItems(query);
    }//GEN-LAST:event_jcbDisengagedActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JCheckBox jcbDisengaged;
    private javax.swing.JPanel jpView;
    private javax.swing.JTextField jtEmployeeName;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        getTheStringAndReload();
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
    
    void getTheStringAndReload(){
        searchString = jtEmployeeName.getText();
        String sqlString = "SELECT e FROM Employees e WHERE (e.surName LIKE  '%"+searchString+"%' OR e.otherNames LIKE  '%"+searchString+"%' OR e.empCode LIKE  '%"+searchString+"%') AND e.isDisengaged =  '"+disengaged+"'  " ;
        
            query.setSqlString(sqlString);
                loadItems(query);
            
        
    }
    void loadItems(QueryEmployee query){
        em.setRootContext(new AbstractNode(Children.create(new FactoryEmployeesManifest(query), true)));
    }
    
    
}