/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.BorderLayout;
import java.util.List;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import org.netbeans.api.progress.ProgressHandle;
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
import org.openide.util.RequestProcessor;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.EmployeeBankAccounts;
import systems.tech247.hr.Employees;
import systems.tech247.pdreditors.AccountEditorTopComponent;
import systems.tech247.pdrreports.ReportEmployeeAccounts;
import systems.tech247.util.CapCreatable;
import systems.tech247.util.CapPreview;
import systems.tech247.util.NotifyUtil;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdr//EmployeeBankAccounts//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmployeeBankAccountsTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.pdr.EmployeeBankAccountsTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_EmployeeBankAccountsAction",
//        preferredID = "EmployeeBankAccountsTopComponent"
//)
@Messages({
    "CTL_EmployeeBankAccountsAction= Bank Accounts",
    "CTL_EmployeeBankAccountsTopComponent= Bank Accounts",
    "HINT_EmployeeBankAccountsTopComponent=This is a EmployeeContacts window"
})
public final class EmployeeBankAccountsTopComponent extends TopComponent implements ExplorerManager.Provider  {
    ExplorerManager em = new ExplorerManager();
    Employees emp;
    InstanceContent content = new InstanceContent();
    Lookup lookup = new AbstractLookup(content);
    public EmployeeBankAccountsTopComponent(){
        this(null);
    }
    
    public EmployeeBankAccountsTopComponent(final Employees emp) {
        initComponents();
        if(emp!=null){
            setName(Bundle.CTL_EmployeeBankAccountsTopComponent()+" -> "+emp.getSurName()+" "+emp.getOtherNames());
            content.add((CapCreatable) () -> {
                TopComponent tc = new AccountEditorTopComponent(emp);
                tc.open();
                tc.requestActive();
            });
        }else{
            setName("All Bank Accounts");
            content.add((CapPreview) () -> {
                
                ProgressHandle ph = ProgressHandle.createHandle("Collecting Employee Accounts");
                ph.start();
                TextColumnBuilder[] columns = new TextColumnBuilder[9];
                String[] columnsNames = new String[9];
                
                TextColumnBuilder<Integer> sno = DynamicReports.col.column("S/No","sno",Integer.class).setWidth(30);
                
                columns[0] = sno;
                columnsNames[0] = sno.getName();
                TextColumnBuilder<String> empCode = DynamicReports.col.column("P/No", "empcode", String.class).setWidth(30);
                columns[1] = empCode;
                columnsNames[1] = empCode.getName();
                TextColumnBuilder<String> surname = DynamicReports.col.column("Surname", "surname", String.class).setWidth(75);
                columns[2] = surname;
                columnsNames[2] = surname.getName();
                TextColumnBuilder<String> othername = DynamicReports.col.column("Other Names", "othername", String.class).setWidth(75);
                columns[3] = othername;
                columnsNames[3] = othername.getName();
                TextColumnBuilder<String> dept = DynamicReports.col.column("DEPARTMENT", "dept", String.class);
                columns[4] = dept;
                columnsNames[4] = dept.getName();
//                TextColumnBuilder<String> position = DynamicReports.col.column("POSITION", "position", String.class);
//                columns[5] = position;
//                columnsNames[5] = position.getName();
                TextColumnBuilder<String> bank = DynamicReports.col.column("BANK", "bank", String.class);
                columns[5] = bank;
                columnsNames[5] = bank.getName();
                TextColumnBuilder<String> branch = DynamicReports.col.column("BRANCH", "branch", String.class);
                columns[6] = branch;
                columnsNames[6] = branch.getName();
                TextColumnBuilder<String> account = DynamicReports.col.column("ACCOUNT", "account", String.class);
                columns[7] = account;
                columnsNames[7] = account.getName();
                TextColumnBuilder<String> signature = DynamicReports.col.column("Signature", "signature", String.class);
                columns[8] = signature;
                columnsNames[8] = signature.getName();
                DRDataSource dataSource = new DRDataSource(columnsNames);
                
                RequestProcessor.getDefault().post(new Runnable() {
                    @Override
                    public void run() {
                        
                        List<Object> l = DataAccess.entityManager.createNativeQuery("SELECT e.EmpCode,e.SurName,e.OtherNames,ou.OrganizationUnitName,b.BankName,bb.BranchName,eba.AccountNumber,'*' AS Signature FROM EmployeeBankAccounts eba INNER JOIN BankBranches bb ON eba.BankBranchID = bb.BankBranchID INNER JOIN Employees e ON eba.EmployeeID = e.EmployeeID INNER JOIN OrganizationUnits ou ON ou.OrganizationUnitID= e.OrganizationUnitID INNER JOIN Banks b ON b.BankID = bb.BankID WHERE e.IsDisengaged = 0").getResultList();
                        for(int i=0;i<l.size();i++){
                            Object[] data = (Object[])l.get(i);
                            int counter = i+1;
                            dataSource.add(counter,data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7]);
                            //NotifyUtil.info("Array Size", data[0].toString()+" Emp Code ", false);
                        }
                        
                        ReportEmployeeAccounts design = new ReportEmployeeAccounts(columns, dataSource);
                        try{
                            JasperReportBuilder report = design.getReport();
                            report.show(false);
                        }catch(Exception ex){
                            
                        }
                        ph.finish();
                        
                    }
                });
            });
        }    
        setToolTipText(Bundle.HINT_EmployeeBankAccountsTopComponent());
        
        
        
        associateLookup(new ProxyLookup(ExplorerUtils.createLookup(em, getActionMap()),lookup));
        this.emp = emp;
        OutlineView ov = new OutlineView("Account");
        ov.addPropertyColumn("number", "A/C Number");
        ov.addPropertyColumn("bank", "Bank");
        ov.addPropertyColumn("branch", "Branch");
        ov.addPropertyColumn("main", "Main Account");
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
        if(emp==null){
            em.setRootContext(new AbstractNode(Children.create(new FactoryEmployeeBankAccounts(), true)));
            //NotifyUtil.info("We reached Here", "Here", false);
        }else{
            em.setRootContext(new AbstractNode(Children.create(new FactoryEmployeeBankAccounts(emp), true)));
        }
        
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
