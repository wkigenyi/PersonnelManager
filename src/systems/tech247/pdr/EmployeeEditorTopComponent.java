/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.explorer.ExplorerUtils;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.ProxyLookup;
import org.openide.windows.WindowManager;
import systems.tech247.hr.Employees;
import systems.tech247.pdreditors.EmpAwardsPanel;
import systems.tech247.pdreditors.EmpBankAccountsPanel;
import systems.tech247.pdreditors.EmpCheckListPanel;
import systems.tech247.pdreditors.EmpContactInfoPanel;
import systems.tech247.pdreditors.EmpDisengagementPanel;

import systems.tech247.pdreditors.EmploymentHistoryPanel;
import systems.tech247.pdreditors.EmpEmploymentPanel;
import systems.tech247.pdreditors.EmpHealthInfoPanel;
import systems.tech247.pdreditors.EmpKinInfoPanel;
import systems.tech247.pdreditors.EmpAssetIssuePanel;

import systems.tech247.pdreditors.EmpFamilyPanel;
import systems.tech247.pdreditors.EmpProbationPanel;
import systems.tech247.pdreditors.EmpProfPanel;




/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//EmployeeEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmployeeEditorTopComponent",
        iconBase = "systems/tech247/util/icons/settings.png", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = true, roles={"Personnel"})
//@ActionID(category = "PDR", id = "systems.tech247.pdr.EmployeeEditorTopComponent")
//@ActionReference(path = "Menu/PDR" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_EmployeeEditorAction",
        
        preferredID = "EmployeeEditorTopComponent"
)
@Messages({
    "CTL_EmployeeEditorAction=Employee Editor",
    "CTL_EmployeeEditorTopComponent=Employee Editor",
    "HINT_EmployeeEditorTopComponent= Employee Editor"
})
public final class EmployeeEditorTopComponent extends TopComponent implements TreeSelectionListener, LookupListener {

    
    Lookup lookup;
    TopComponent empTc = WindowManager.getDefault().findTopComponent("EmployeesTopComponent");
    Lookup.Result<Employees> empRslt = empTc.getLookup().lookupResult(Employees.class);
    
    JTree tree;
    DefaultMutableTreeNode selectedNode;
    public EmployeeEditorTopComponent() {
        initComponents();
        setName(Bundle.CTL_EmployeeEditorTopComponent());
        setToolTipText(Bundle.HINT_EmployeeEditorTopComponent());
        lookup = new AbstractLookup(UtilityPDR.editorIC);
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Employee Information");
        panelHolder.setLayout(new BorderLayout());
        tree = new JTree(top);
        createNodes(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treeHolder.setLayout(new BorderLayout());
        treeHolder.add(tree);
        expandAllNodes(tree, 0, tree.getRowCount());
        
        tree.addTreeSelectionListener(this);
        
        tree.setSelectionPath(new TreePath(selectedNode.getPath()));
        
        jTEmployeeName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Object[] options = {new JButton("Close")};
                DialogDisplayer.getDefault().notify(new DialogDescriptor(empTc, "Search Employees",true, options,null,DialogDescriptor.DEFAULT_ALIGN,null,null));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                            }
        });
       
        
        empRslt.addLookupListener(this);
        //Associated lookups
        Lookup employeeContacts =  ExplorerUtils.createLookup(UtilityPDR.emEmployeeContacts, getActionMap());
        Lookup employeeContracts =  ExplorerUtils.createLookup(UtilityPDR.emEmployeeContracts, getActionMap());
        Lookup employeeKins =  ExplorerUtils.createLookup(UtilityPDR.emEmployeeKins, getActionMap());
        Lookup employeeFamily =  ExplorerUtils.createLookup(UtilityPDR.emEmployeeFamily, getActionMap());
        Lookup employeeAssets =  ExplorerUtils.createLookup(UtilityPDR.emEmployeeAssetIssue, getActionMap());
        Lookup employeeRefs =  ExplorerUtils.createLookup(UtilityPDR.emEmployeeReferees, getActionMap());
        Lookup employeeHistory =  ExplorerUtils.createLookup(UtilityPDR.emEmployeeEmploymentHistory, getActionMap());
        Lookup employeeAwards =  ExplorerUtils.createLookup(UtilityPDR.emEmployeeAwards, getActionMap());
        Lookup employeeEducation =  ExplorerUtils.createLookup(UtilityPDR.emEmployeeEducation, getActionMap());
        
        Lookup employeeProf =  ExplorerUtils.createLookup(UtilityPDR.emEmployeeProf, getActionMap());
        Lookup employeeBankAccounts =  ExplorerUtils.createLookup(UtilityPDR.emEmployeeBankAccounts, getActionMap());
        ProxyLookup mergedLookup = new ProxyLookup(employeeContacts,employeeKins,employeeAssets,employeeRefs,employeeHistory,employeeAwards,employeeEducation,employeeProf,employeeBankAccounts,employeeFamily,employeeContracts);
        associateLookup(mergedLookup);

    }
    
    private void createNodes(DefaultMutableTreeNode node){
        
        DefaultMutableTreeNode category;
        DefaultMutableTreeNode panel;
        
        
        
        category = new DefaultMutableTreeNode("Personnel Information");
        node.add(category);
//        selectedNode  = new DefaultMutableTreeNode(new PanelInfo("Identity Information", new EditorNewEmployeePanel(0)));
        category.add(selectedNode);
        panel = new DefaultMutableTreeNode(new PanelInfo("Disability Information", new EmpHealthInfoPanel()));
        category.add(panel);
        panel = new DefaultMutableTreeNode(new PanelInfo("Pre-Employment Check List", new EmpCheckListPanel()));
        category.add(panel);
        panel = new DefaultMutableTreeNode(new PanelInfo("Contact Information", new EmpContactInfoPanel()));
        category.add(panel);
        
        
        
        panel = new DefaultMutableTreeNode(new PanelInfo("Next Of Kin Information", new EmpKinInfoPanel()));
        category.add(panel);
        panel = new DefaultMutableTreeNode(new PanelInfo("Family", new EmpFamilyPanel()));
        category.add(panel);
        panel = new DefaultMutableTreeNode(new PanelInfo("Employment History", new EmploymentHistoryPanel()));
        category.add(panel);
        category = new DefaultMutableTreeNode("Education Information");
        node.add(category);
        
        panel = new DefaultMutableTreeNode(new PanelInfo("Proffessional Qualifications", new EmpProfPanel()));
        category.add(panel);
        category = new DefaultMutableTreeNode("Employment Information");
        node.add(category);
        panel = new DefaultMutableTreeNode(new PanelInfo("Employment Details", new EmpEmploymentPanel()));
        category.add(panel);
        
        
        
        panel = new DefaultMutableTreeNode(new PanelInfo("Probation", new EmpProbationPanel()));
        category.add(panel);
        panel = new DefaultMutableTreeNode(new PanelInfo("Assigned Assets", new EmpAssetIssuePanel()));
        category.add(panel);
        panel = new DefaultMutableTreeNode(new PanelInfo("Awards", new EmpAwardsPanel()));
        category.add(panel);
        panel = new DefaultMutableTreeNode(new PanelInfo("Termination", new EmpDisengagementPanel()));
        category.add(panel);
        
        

        panel = new DefaultMutableTreeNode(new PanelInfo("Bank Account", new EmpBankAccountsPanel()));
        category.add(panel);
        
        
        
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result result = (Lookup.Result)le.getSource();
        for (Object e: result.allInstances()){
            if(e instanceof Employees){
                jTEmployeeName.setText(((Employees) e).getSurName()+ " "+ ((Employees) e).getOtherNames());
            }
        }
    }
    
    private class PanelInfo{
        String description;
        JPanel panel;
        
        public PanelInfo(String desc, JPanel panel){
            this.description = desc;
            this.panel = panel;
        }
        
        @Override
        public String toString(){
            return description;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTEmployeeName = new javax.swing.JTextField();
        panelHolder = new javax.swing.JPanel();
        treeHolder = new javax.swing.JPanel();

        jTEmployeeName.setBackground(new java.awt.Color(0, 204, 0));
        jTEmployeeName.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jTEmployeeName.setText(org.openide.util.NbBundle.getMessage(EmployeeEditorTopComponent.class, "EmployeeEditorTopComponent.jTEmployeeName.text")); // NOI18N
        jTEmployeeName.setToolTipText(org.openide.util.NbBundle.getMessage(EmployeeEditorTopComponent.class, "EmployeeEditorTopComponent.jTEmployeeName.toolTipText")); // NOI18N

        panelHolder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelHolderLayout = new javax.swing.GroupLayout(panelHolder);
        panelHolder.setLayout(panelHolderLayout);
        panelHolderLayout.setHorizontalGroup(
            panelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );
        panelHolderLayout.setVerticalGroup(
            panelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );

        treeHolder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout treeHolderLayout = new javax.swing.GroupLayout(treeHolder);
        treeHolder.setLayout(treeHolderLayout);
        treeHolderLayout.setHorizontalGroup(
            treeHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 131, Short.MAX_VALUE)
        );
        treeHolderLayout.setVerticalGroup(
            treeHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(treeHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(treeHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTEmployeeName;
    private javax.swing.JPanel panelHolder;
    private javax.swing.JPanel treeHolder;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        resultChanged(new LookupEvent(empRslt));
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
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        if(node == null){
            //Do nothing 
        }
        
        Object nodeInfo = node.getUserObject();
        if(node.isLeaf()){
            PanelInfo panel = (PanelInfo)nodeInfo;
            panelHolder.removeAll();
            panelHolder.add(panel.panel);
            panelHolder.setPreferredSize(panel.panel.getPreferredSize());
            panelHolder.revalidate();
            
            repaint();
           
        }else{
            panelHolder.removeAll();
            panelHolder.revalidate();
            repaint();
            
        }
  
    }
    private void expandAllNodes(JTree tree, int startingIndex, int rowCount){
    for(int i=startingIndex;i<rowCount;++i){
        tree.expandRow(i);
    }

    if(tree.getRowCount()!=rowCount){
        expandAllNodes(tree, rowCount, tree.getRowCount());
    }
}
}
