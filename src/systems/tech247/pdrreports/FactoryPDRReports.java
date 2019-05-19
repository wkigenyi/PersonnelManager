/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdrreports;

import systems.tech247.util.SetupItem;
import systems.tech247.util.NodeSetupItem;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import systems.tech247.pdr.EmployeeBankAccountsTopComponent;

/**
 *
 * @author WKigenyi
 */
public class FactoryPDRReports extends ChildFactory<SetupItem> {
    
    @Override
    protected boolean createKeys(List<SetupItem> toPopulate) {

        toPopulate.add(new SetupItem("Employee HeadCount",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        },"systems/tech247/util/icons/capex.png"));
        toPopulate.add(new SetupItem("Birth Day Report",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        },"systems/tech247/util/icons/capex.png"));
        toPopulate.add(new SetupItem("Retirement Report",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        },"systems/tech247/util/icons/capex.png"));
        toPopulate.add(new SetupItem("Turnover Report",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        },"systems/tech247/util/icons/capex.png"));
        
        toPopulate.add(new SetupItem("All Employee Bank Accounts",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc = new EmployeeBankAccountsTopComponent();
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
