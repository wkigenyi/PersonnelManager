/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.hr.OrganizationUnits;
import systems.tech247.util.CapEditable;
import systems.tech247.util.CetusUTL;

/**
 *
 * @author Wilfred
 */
public class NodeSetupDepartment extends AbstractNode{
        
        private final InstanceContent instanceContent;
        OrganizationUnits unit;
        
        public NodeSetupDepartment(OrganizationUnits emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeSetupDepartment (InstanceContent ic, final OrganizationUnits unit){
            super(Children.LEAF, new AbstractLookup(ic));
            this.unit = unit;
            instanceContent = ic;
            instanceContent.add(unit);
            //If the user has editing rights
            if(CetusUTL.userRights.contains(230)){
                instanceContent.add(new CapEditable() {
                    @Override
                    public void edit() {
                        TopComponent editor = new OUEditorTopComponent(unit);
                        editor.open();
                        editor.requestActive();
                    }
                });
            }
            
            setIconBaseWithExtension("systems/tech247/util/icons/department.png");
            setDisplayName(unit.getOrganizationUnitName());
        }
        
        

        
        
        
    
}
