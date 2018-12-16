/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import systems.tech247.view.CategoryEditorTopComponent;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.hr.EmployeeCategories;
import systems.tech247.hr.Employees;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;



/**
 *
 * @author Wilfred
 */
public class FactoryBirthdays extends ChildFactory<Object>{

    
    
    List list;
    
    public FactoryBirthdays(List list){
        this.list = list;
    }
    
    
    
    
    
    
    
    @Override
    protected boolean createKeys(List<Object> toPopulate) {

        toPopulate.addAll(list);
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Object key){
        Node node = null;
        if(key instanceof Employees){
            node = new NodeBirthDayEmployee((Employees)key);
        }else if(key instanceof AddTool){
            node = new NodeAddTool((AddTool)key);
        }
        
        return node;
    }
    
    private class CategoryNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        EmployeeCategories cat;
        
        public CategoryNode(EmployeeCategories emp){
            this(new InstanceContent(),emp);
        }
        
        private CategoryNode (InstanceContent ic, EmployeeCategories unit){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(unit);
            this.cat = unit;
            setIconBaseWithExtension("systems/tech247/util/icons/EmpCategory.png");
            setDisplayName(unit.getCategoryName());
        }

        @Override
        public Action getPreferredAction() {
            return new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TopComponent tc = new CategoryEditorTopComponent(cat);
                    tc.open();
                    tc.requestActive();
                }
            };
        }
        
        
    
}
    
}
