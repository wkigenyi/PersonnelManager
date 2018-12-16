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
import systems.tech247.hr.Nationalities;
import systems.tech247.hr.Tribes;

/**
 *
 * @author Wilfred
 */
public class NodeTribe extends AbstractNode {
    private final InstanceContent instanceContent;
        
        public NodeTribe(Tribes emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeTribe (InstanceContent ic, Tribes emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            setIconBaseWithExtension("systems/tech247/util/icons/tribe.png");
            setDisplayName(emp.getTribe());
        }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();
        final Tribes tribe = getLookup().lookup(Tribes.class);
        
        Property number = new PropertySupport("number", String.class, "Employee Number", "Number", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return tribe.getEmployeesCollection().size();
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
