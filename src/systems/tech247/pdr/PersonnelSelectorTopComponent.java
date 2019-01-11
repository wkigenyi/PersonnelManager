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
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.EmployeeCategories;
import systems.tech247.hr.OrganizationUnits;
import systems.tech247.util.FactorySelectableEmployee;
import systems.tech247.util.QueryEmployee;



/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//PersonnelSelector//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "PersonnelSelectorTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdr.PersonnelSelectorTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_PersonnelSelectorAction",
//        preferredID = "PersonnelSelectorTopComponent"
//)
@Messages({
    "CTL_PersonnelSelectorAction=PersonnelSelector",
    "CTL_PersonnelSelectorTopComponent=PersonnelSelector Window",
    "HINT_PersonnelSelectorTopComponent=This is a PersonnelSelector window"
})
public final class PersonnelSelectorTopComponent extends TopComponent implements ExplorerManager.Provider,LookupListener{
    
    
    
    Boolean disengaged = false;
    String searchString = "";
    QueryEmployee query = new QueryEmployee();
    
    
    
    
    Lookup.Result<OrganizationUnits> departmentResult;
    Lookup.Result<EmployeeCategories> categoryResult;
    
    ExplorerManager em = new ExplorerManager();
    public PersonnelSelectorTopComponent() {
        initComponents();
        setName(Bundle.CTL_PersonnelSelectorTopComponent());
        setToolTipText(Bundle.HINT_PersonnelSelectorTopComponent());
        viewHolder.setLayout(new BorderLayout());
        OutlineView ov = new OutlineView("Last Name");
        viewHolder.add(ov);
        ov.addPropertyColumn("fname", "Other Names");
        ov.addPropertyColumn("department", "Department");
        ov.addPropertyColumn("category", "Category");
        ov.addPropertyColumn("isSelected", "Select");
        
        ov.getOutline().setRootVisible(false);
        
        
        associateLookup(ExplorerUtils.createLookup(em, getActionMap()));
        
        jtEmployeeName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                searchString = jtEmployeeName.getText();
                
                String sqlString = "SELECT e FROM Employees e WHERE (e.surName LIKE  '%"+searchString+"%' OR e.otherNames LIKE  '%"+searchString+"%' OR e.empCode LIKE  '%"+searchString+"%') AND e.isDisengaged =  '"+disengaged+"'  " ;
                loadEmployees(sqlString,false);
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

        viewHolder = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtEmployeeName = new javax.swing.JTextField();
        jcbSelectAll = new javax.swing.JCheckBox();

        viewHolder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout viewHolderLayout = new javax.swing.GroupLayout(viewHolder);
        viewHolder.setLayout(viewHolderLayout);
        viewHolderLayout.setHorizontalGroup(
            viewHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        viewHolderLayout.setVerticalGroup(
            viewHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PersonnelSelectorTopComponent.class, "PersonnelSelectorTopComponent.jLabel2.text")); // NOI18N

        jtEmployeeName.setBackground(new java.awt.Color(0, 204, 204));
        jtEmployeeName.setText(org.openide.util.NbBundle.getMessage(PersonnelSelectorTopComponent.class, "PersonnelSelectorTopComponent.jtEmployeeName.text")); // NOI18N
        jtEmployeeName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtEmployeeNameKeyReleased(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jcbSelectAll, org.openide.util.NbBundle.getMessage(PersonnelSelectorTopComponent.class, "PersonnelSelectorTopComponent.jcbSelectAll.text")); // NOI18N
        jcbSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSelectAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26)
                        .addComponent(jtEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbSelectAll)
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addComponent(viewHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbSelectAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtEmployeeNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtEmployeeNameKeyReleased
        
    }//GEN-LAST:event_jtEmployeeNameKeyReleased

    private void jcbSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSelectAllActionPerformed
        
        if(jcbSelectAll.isSelected()){
            DataAccess.selectAllEmployees();
            String sql = "SELECT e FROM Employees e WHERE e.isDisengaged = 0";
            
            loadEmployees(sql,true);
        }else{
            DataAccess.resetSelectedEmployees();
            String sql = "SELECT e FROM Employees e WHERE e.isDisengaged = 0";
            loadEmployees(sql, false);
        }
        
    }//GEN-LAST:event_jcbSelectAllActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JCheckBox jcbSelectAll;
    private javax.swing.JTextField jtEmployeeName;
    private javax.swing.JPanel viewHolder;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
        
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

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result result = (Lookup.Result)le.getSource();
        for(Object o : result.allInstances()){
            if(o instanceof OrganizationUnits){
                OrganizationUnits d= (OrganizationUnits)o;
                //String sql = "SELECT e from TblEmployee e WHERE e.departmentCode = "+d.getTblDepartmentPK().getDepartmentCode()+"";
                //UtilPersonnel.loadSelectableEmloyees(sql);
            }
            if(o instanceof EmployeeCategories){
                EmployeeCategories d= (EmployeeCategories)o;
                //String sql = "SELECT e from TblEmployee e WHERE e.categoryCode = "+d.getTblCategoryPK().getCategoryCode()+"";
                //UtilPersonnel.loadSelectableEmloyees(sql);
            }
            
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    void loadEmployees(String sql,Boolean select){
        em.setRootContext(new AbstractNode(Children.create(new FactorySelectableEmployee(sql, select), true)));
    }
    
}
