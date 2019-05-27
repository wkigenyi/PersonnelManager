/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.swing.AbstractAction;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.ActionID;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.RequestProcessor;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.notifications.NotificationsBirthDayPanel;
import systems.tech247.notifications.NotificationsContractsPanel;
import systems.tech247.notifications.NotificationsNoBirthDayPanel;
import systems.tech247.notifications.NotificationsVisasPanel;
import systems.tech247.util.MessageType;
import systems.tech247.util.NotifyUtil;
import systems.tech247.util.QueryEmployee;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//EmployeeList//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmployeeListTopComponent",
        iconBase = "systems/tech247/util/icons/position.png", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "explorer", openAtStartup = false /*, roles = {"Personnel"}*/)
@ActionID(category = "Window", id = "systems.tech247.pdr.EmployeeListTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_EmployeeListAction",
//        preferredID = "EmployeeListTopComponent"
//)
@Messages({
    "CTL_EmployeeListAction=EmployeeList",
    "CTL_EmployeeListTopComponent=Employee List",
    "HINT_EmployeeListTopComponent=This is a EmployeeList window"
})
public final class EmployeeListTopComponent extends TopComponent implements ExplorerManager.Provider{
    ExplorerManager em = new ExplorerManager();
    
    Boolean disengaged = false;
    String searchString = "";
    QueryEmployee query = new QueryEmployee();
    InstanceContent ic = new InstanceContent();
    Lookup lookup = new AbstractLookup(ic);
    public EmployeeListTopComponent() throws InterruptedException, InvocationTargetException {
        initComponents();
        setName(Bundle.CTL_EmployeeListTopComponent());
        setToolTipText(Bundle.HINT_EmployeeListTopComponent());
        vPanel.setLayout(new BorderLayout());
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_DND_COPY_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_SLIDING_DISABLED, Boolean.TRUE);
        BeanTreeView btv = new BeanTreeView();
        btv.setRootVisible(false);
        vPanel.add(btv);
        ic.add(btv);
        
        
        //make the items available in the lookup
        associateLookup(new ProxyLookup(
                ExplorerUtils.createLookup(em, getActionMap()),
                lookup));
        
        
        
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
        

        
        //RequestProcessor.getDefault().post(run)
        RequestProcessor.getDefault().post(new Runnable() {
            @Override
            public void run() {
                final List blist = DataAccess.searchBabyThisMonth();
                final List noBdayList = DataAccess.searchEmployeesWithoutBDay();
                //Contracts Expiring in a month

                final List contractList = DataAccess.searchExpiringContracts();
        
                //Visas Expiring in A month
                final List visaList = DataAccess.searchExpiringVisas();
                if(blist.size()>0){
                    NotifyUtil.show(blist.size()+" Employees were born this month", "Employees Born This Month", MessageType.INFO, new AbstractAction() {
                        @Override
                            public void actionPerformed(ActionEvent e) {
                            DialogDisplayer.getDefault().notify(new DialogDescriptor(new NotificationsBirthDayPanel(blist), "Birthdays This Month"));
                            }         }, false);
                }else{
                    NotifyUtil.info("No one was born this month", "Sorry we have no Bdays this month", false);
                }
        
                //Those without Birthdays
                if(noBdayList.size()>0){
                    NotifyUtil.show(noBdayList.size()+" Employees have no Birthdays", "View The Employees", MessageType.WARNING, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DialogDisplayer.getDefault().notify(new DialogDescriptor(new NotificationsNoBirthDayPanel(noBdayList), "Employees without Birth days"));
                    }
                    }, false);
                }
                //Contracts
                if(contractList.size()>0){
                    NotifyUtil.show(contractList.size()+" Expired/Expiring Contracts", "View Contracts Expired / Expiring in a month", MessageType.WARNING, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DialogDisplayer.getDefault().notify(new DialogDescriptor(new NotificationsContractsPanel(contractList), "Expired/Expring Contracts"));
                    }
                    }, false);
                }
        //Vizas
                if(visaList.size()>0){
                    NotifyUtil.show(contractList.size()+" Expired/Expiring Visas", "View Visas Expired / Expiring in a month", MessageType.WARNING, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DialogDisplayer.getDefault().notify(new DialogDescriptor(new NotificationsVisasPanel(visaList), "Expired/Expring Visas"));
                    }
                    }, false);
                }
        
        
        
        
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
        vPanel = new javax.swing.JPanel();
        jcbDisengaged = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EmployeeListTopComponent.class, "EmployeeListTopComponent.jLabel1.text")); // NOI18N

        jtEmployeeName.setText(org.openide.util.NbBundle.getMessage(EmployeeListTopComponent.class, "EmployeeListTopComponent.jtEmployeeName.text")); // NOI18N

        vPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout vPanelLayout = new javax.swing.GroupLayout(vPanel);
        vPanel.setLayout(vPanelLayout);
        vPanelLayout.setHorizontalGroup(
            vPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        vPanelLayout.setVerticalGroup(
            vPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(jcbDisengaged, org.openide.util.NbBundle.getMessage(EmployeeListTopComponent.class, "EmployeeListTopComponent.jcbDisengaged.text")); // NOI18N
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
                    .addComponent(vPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbDisengaged)
                        .addGap(0, 2, Short.MAX_VALUE)))
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
                .addComponent(vPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbDisengagedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDisengagedActionPerformed
        disengaged = jcbDisengaged.isSelected();
        searchString = jtEmployeeName.getText();
        String sqlString = "SELECT e FROM Employees e WHERE (e.surName LIKE  '%"+searchString+"%' OR e.otherNames LIKE  '%"+searchString+"%' OR e.empCode LIKE  '%"+searchString+"%') AND e.isDisengaged =  '"+disengaged+"'  " ;
        query.setSqlString(sqlString);
        loadItems(query);        
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbDisengagedActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JCheckBox jcbDisengaged;
    private javax.swing.JTextField jtEmployeeName;
    private javax.swing.JPanel vPanel;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
//        String sqlString = "SELECT e FROM Employees e WHERE e.surName LIKE  'Nothing'" ;
//        query.setSqlString(sqlString);
//        loadItems(query);
    }

    @Override
    public void componentClosed() {
        
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    void getTheStringAndReload(){
        searchString = jtEmployeeName.getText();
        String sqlString = "SELECT e FROM Employees e WHERE (e.surName LIKE  '%"+searchString+"%' OR e.otherNames LIKE  '%"+searchString+"%' OR e.empCode LIKE  '%"+searchString+"%') AND e.isDisengaged =  '"+disengaged+"'  " ;
        
            query.setSqlString(sqlString);
                loadItems(query);
            
        
    }
    void loadItems(QueryEmployee query){
        em.setRootContext(new AbstractNode(Children.create(new FactoryEmployeesList(query), true)));
    }
}
