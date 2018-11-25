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
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import systems.tech247.hr.Employees;
import systems.tech247.pdreditors.EmployeeCheckListTopComponent;
import systems.tech247.pdreditors.EmployeeDisabilityTopComponent;

/**
 *
 * @author Admin
 */
public class FactoryPersonalInfo extends ChildFactory<SetupItem> {
    
    Employees emp;
    
    public FactoryPersonalInfo(Employees emp){
        this.emp = emp;
    }
    
    

    @Override
    protected boolean createKeys(List<SetupItem> list) {
        
        

      
        
        
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
