/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.BorderLayout;
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
import systems.tech247.hr.Employees;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//EmployeeContracts//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmployeeContractsTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdr.EmployeeContractsTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_EmployeeContractsAction",
        preferredID = "EmployeeContractsTopComponent"
)
@Messages({
    "CTL_EmployeeContractsAction=Contracts",
    "CTL_EmployeeContractsTopComponent=Employee Contracts",
    "HINT_EmployeeContractsTopComponent="
})
public final class EmployeeContractsTopComponent extends TopComponent implements ExplorerManager.Provider  {
    ExplorerManager em = new ExplorerManager();
    Employees emp;
    public EmployeeContractsTopComponent(){
        this(null);
    }
    
    public EmployeeContractsTopComponent(Employees emp) {
        initComponents();
        setName(Bundle.CTL_EmployeeContractsTopComponent()+"->"+emp.getSurName()+" "+emp.getOtherNames());
        setToolTipText(Bundle.HINT_EmployeeContractsTopComponent());
        associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
        this.emp = emp;
        OutlineView ov = new OutlineView("Contracts");
        ov.getOutline().setRootVisible(false);
        setLayout(new BorderLayout());
        add(ov);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        em.setRootContext(new AbstractNode(Children.create(new FactoryEmployeeContracts(emp), true)));
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
