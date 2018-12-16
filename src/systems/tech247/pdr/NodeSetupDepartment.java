/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.lang.reflect.InvocationTargetException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
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

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();
        final OrganizationUnits dept = getLookup().lookup(OrganizationUnits.class);
        
        Property number = new PropertySupport("number", String.class, "Employee Number", "Number", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return dept.getEmployeesCollection().size();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(number);
        sheet.put(set);
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
        
        
        
        

        
        
        
    
}
