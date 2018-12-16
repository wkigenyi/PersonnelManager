/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdrsetup;

import java.awt.event.ActionEvent;
import systems.tech247.util.SetupItem;
import systems.tech247.util.NodeSetupItem;
import java.util.List;
import javax.swing.AbstractAction;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.pdr.BanksTopComponent;
import systems.tech247.pdr.CompanyStructureTopComponent;
import systems.tech247.pdr.ContactTypesTopComponent;
import systems.tech247.pdr.ContractTypesTopComponent;
import systems.tech247.pdr.CountryTopComponent;
import systems.tech247.pdr.CurrenciesTopComponent;
import systems.tech247.pdr.DepartmentsTopComponent;
import systems.tech247.pdr.EmployeeTypeTopComponent;
import systems.tech247.pdr.LocationsTopComponent;
import systems.tech247.pdr.NationsTopComponent;
import systems.tech247.pdr.PositionsTopComponent;
import systems.tech247.pdr.ReligionsTopComponent;
import systems.tech247.pdr.TribesTopComponent;

/**
 *
 * @author WKigenyi
 */
public class FactoryPDRSetup extends ChildFactory<SetupItem> {
    
    @Override
    protected boolean createKeys(List<SetupItem> toPopulate) {
        
        toPopulate.add(new SetupItem("Employee Departments", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new DepartmentsTopComponent("view");
                tc.open();
                tc.requestActive();
            }
        },"systems/tech247/util/icons/department.png"));
        toPopulate.add(new SetupItem("Company Structures", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open Top Component For Company Structure
                TopComponent tc = new CompanyStructureTopComponent("view");
                tc.open();
                tc.requestActive();
            }
            
        },"systems/tech247/util/icons/EmpCategory.png"));
        
        toPopulate.add(new SetupItem("Employee Categories", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open Top Component For Company Structure
                TopComponent tc = WindowManager.getDefault().findTopComponent("CategoriesTopComponent");
                tc.open();
                tc.requestActive();
            }
            
        },"systems/tech247/util/icons/EmpCategory.png"));
        
        
        toPopulate.add(new SetupItem("Job Positions", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new PositionsTopComponent("view");
                tc.open();
                tc.requestActive();
            }
        },"systems/tech247/util/icons/position.png"));
        
        toPopulate.add(new SetupItem("Employee Types", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new EmployeeTypeTopComponent("view");
                tc.open();
                tc.requestActive();
            }
        },"systems/tech247/util/icons/position.png"));
        
        toPopulate.add(new SetupItem("Countries",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new CountryTopComponent("view");
                tc.open();
                tc.requestActive();
            }
        },"systems/tech247/util/icons/earth.png"));
    
        toPopulate.add(new SetupItem("Nationalities",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new NationsTopComponent("view");
                tc.open();
                tc.requestActive();
            }
        },"systems/tech247/util/icons/earth.png"));
        
        toPopulate.add(new SetupItem("Tribes",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new TribesTopComponent("view");
                tc.open();
                tc.requestActive();
            }
        },"systems/tech247/util/icons/tribe.png"));
        
        toPopulate.add(new SetupItem("Religions",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new ReligionsTopComponent("view");
                tc.open();
                tc.requestActive();
            }
        }));
        toPopulate.add(new SetupItem("Banks",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new BanksTopComponent("view");
                tc.open();
                tc.requestActive();
            }
        },"systems/tech247/util/icons/bank.png"));
        toPopulate.add(new SetupItem("Currencies",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new CurrenciesTopComponent("view");
                tc.open();
                tc.requestActive();
            }
        },"systems/tech247/util/icons/Currency.png"));
        toPopulate.add(new SetupItem("Locations",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new LocationsTopComponent("view");
                tc.open();
                tc.requestActive();
            }
        }));
       
        toPopulate.add(new SetupItem("Contact Types",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new ContactTypesTopComponent("view");
                tc.open();
                tc.requestActive();
            }
        }));
        
        
        toPopulate.add(new SetupItem("Contract Types",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new ContractTypesTopComponent("view");
                tc.open();
                tc.requestActive();
            }
        }));


//toPopulate.add(new SetupItem("Match Departments TO SUN"));
        /*toPopulate.add(new SetupItem("Salary Calculator",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent salaryTc = WindowManager.getDefault().findTopComponent("SalaryCalculatorTopComponent");
                salaryTc.open();
            }
        }));*/
        
        
        return true;
    }
    
    @Override
    protected Node createNodeForKey(SetupItem key) {
        
        Node node =  null;
        try {
            
            node = new NodeSetupItem(key);
            
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }
}
