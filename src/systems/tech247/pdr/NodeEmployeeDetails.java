/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.hr.Employees;

/**
 *
 * @author Admin
 */
public class NodeEmployeeDetails extends AbstractNode{
    
    Employees emp;
    public NodeEmployeeDetails(Employees emp){
        super(Children.create(new FactoryEmployeeDetails(emp), true));
        this.emp = emp;
        setIconBaseWithExtension("systems/tech247/util/icons/person.png");
            setDisplayName(emp.getSurName()+" "+emp.getOtherNames());
    }

    @Override
    public Action getPreferredAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open the details
                TopComponent editor = new EmployeePersonalInfoEditorTopComponent(emp);
                editor.open();
                editor.requestActive();
                //Expand the node
                BeanTreeView view = WindowManager.getDefault().findTopComponent("EmployeeListTopComponent").getLookup().lookup(BeanTreeView.class);
                view.expandNode(NodeEmployeeDetails.this);
                
                
            }
        };
    }
    
    
    
}
