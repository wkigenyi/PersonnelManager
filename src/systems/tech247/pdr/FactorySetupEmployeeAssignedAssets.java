/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import systems.tech247.hr.Employees;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;

/**
 *
 * @author Admin
 */
public class FactorySetupEmployeeAssignedAssets extends ChildFactory<AddTool> {
    
    Employees emp;
    
    public FactorySetupEmployeeAssignedAssets(Employees emp){
        this.emp = emp;
    }
    
    

    @Override
    protected boolean createKeys(List<AddTool> list) {
        list.add(new AddTool(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open A top component to add a new contact for the employee
            }
        }));
        
        
        return true;
    }
    @Override
    protected Node createNodeForKey(AddTool key) {
        
        Node node =  null;
        try {
            
            node = new NodeAddTool(key);
            
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }
    
}
