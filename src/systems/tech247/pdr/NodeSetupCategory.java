/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.hr.EmployeeCategories;
import systems.tech247.util.CapEditable;
import systems.tech247.util.CetusUTL;

/**
 *
 * @author Wilfred
 */
public class NodeSetupCategory extends AbstractNode{
        
        private final InstanceContent instanceContent;
        EmployeeCategories cat;
        
        public NodeSetupCategory(EmployeeCategories emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeSetupCategory (InstanceContent ic, EmployeeCategories unit){
            super(Children.LEAF, new AbstractLookup(ic));
            this.cat = unit;
            instanceContent = ic;
            instanceContent.add(unit);
            if(CetusUTL.userRights.contains(231)){
                instanceContent.add(new CapEditable(){
                @Override
                public void edit() {
                    TopComponent tc = new CategoryEditorTopComponent(cat);
                    tc.open();
                    tc.requestActive();
                }
                
            });
            
            }
            
            setIconBaseWithExtension("systems/tech247/util/icons/EmpCategory.png");
            setDisplayName(unit.getCategoryName());
        }


        
        
    
}
