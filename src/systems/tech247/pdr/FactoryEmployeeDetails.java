/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import systems.tech247.util.SetupItem;
import systems.tech247.util.NodeSetupItem;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import org.openide.awt.StatusDisplayer;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import systems.tech247.hr.Employees;
import systems.tech247.pdreditors.DisengagementEditorTopComponent;
import systems.tech247.pdreditors.EmployeeCheckListTopComponent;
import systems.tech247.pdreditors.EmployeeDisabilityTopComponent;
import systems.tech247.pdreditors.EmploymentDetailsTopComponent;
import systems.tech247.pdreditors.ProbationEditorTopComponent;

/**
 *
 * @author WKigenyi
 */
public class FactoryEmployeeDetails extends ChildFactory<SetupItem> {
    
    Employees emp;
    
    public FactoryEmployeeDetails(Employees emp){
        this.emp = emp;
    }
    
    
    @Override
    protected boolean createKeys(List<SetupItem> toPopulate) {
        toPopulate.add(new SetupItem("Personal Information",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeePersonalInfoEditorTopComponent tc = new EmployeePersonalInfoEditorTopComponent(emp);
                tc.open();
                tc.requestActive();
                
                TopComponent tc1 = new EmployeeCheckListTopComponent(emp);
                tc1.open();
                
                TopComponent tc2 = new EmployeeDisabilityTopComponent(emp);
                tc2.open();
            }
        },"systems/tech247/util/icons/document.png"));
        
        toPopulate.add(new  SetupItem("Contact Information",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new EmployeeContactsTopComponent(emp);
                tc.open();
                tc.requestActive();
                        
            }
        },"systems/tech247/util/icons/phonebook.png"));
        toPopulate.add(new SetupItem("Referee Information", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new EmployeeRefereeTopComponent(emp);
                tc.open();
                tc.requestActive();
                        
            }
        },"systems/tech247/util/icons/position.png"));
        toPopulate.add(new SetupItem("Next Of Kin Info", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new EmployeeNOKTopComponent(emp);
                tc.open();
                tc.requestActive();
                        
            }
        },"systems/tech247/util/icons/position.png"));
        toPopulate.add(new SetupItem("Family", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new EmployeeFamilyTopComponent(emp);
                tc.open();
                tc.requestActive();
                        
            }
        },"systems/tech247/util/icons/position.png"));
        
        
        
        toPopulate.add(new SetupItem("Bank Accounts",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new EmployeeBankAccountsTopComponent(emp);
                tc.open();
                tc.requestActive();
                        
            }
        },"systems/tech247/util/icons/bankBranch.png"));
        toPopulate.add(new SetupItem("Employment History", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new EmployeeEmploymentHistoryTopComponent(emp);
                tc.open();
                tc.requestActive();
                        
            }
        },"systems/tech247/util/icons/company.png"));
        toPopulate.add(new SetupItem("Education", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new EmployeeEducationTopComponent(emp);
                tc.open();
                tc.requestActive();
                        
            }
        },"systems/tech247/util/icons/student.png"));
        
        toPopulate.add(new SetupItem("Profesional Qualifications", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new EmployeeProfTopComponent(emp);
                tc.open();
                tc.requestActive();
                        
            }
        },"systems/tech247/util/icons/student.png"));
        
        
        
        
        toPopulate.add(new SetupItem("Employment Details", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent editor = new EmploymentDetailsTopComponent(emp);
                editor.open();
                editor.requestActive();
            }
        },"systems/tech247/util/icons/capex.png"));
        toPopulate.add(new SetupItem("Probation", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new ProbationEditorTopComponent(emp);
                tc.open();
                tc.requestActive();
            }
        },"systems/tech247/util/icons/capex.png"));
        toPopulate.add(new SetupItem("Contracts",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new EmployeeContractsTopComponent(emp);
                tc.open();
                tc.requestActive();
            }
        },"systems/tech247/util/icons/capex.png"));
        
        toPopulate.add(new SetupItem("Assigned Assets",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusDisplayer.getDefault().setStatusText("Asset Register Module NOT ACTIVE");
            }
        }));
        toPopulate.add(new SetupItem("Awards", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new EmployeeAwardsTopComponent(emp);
                tc.open();
                tc.requestActive();
            }
        },"systems/tech247/util/icons/award.png"));
        toPopulate.add(new SetupItem("Viza Information",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new EmployeeVisaTopComponent(emp);
                tc.open();
                tc.requestActive();
            }
        },"systems/tech247/util/icons/capex.png"));
        
        toPopulate.add(new SetupItem("Termination", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new DisengagementEditorTopComponent(emp);
                tc.open();
                tc.requestActive();
            }
        },"systems/tech247/util/icons/capex.png"));
        
        
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
