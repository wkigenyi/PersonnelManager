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
import systems.tech247.hr.BankBranches;
import systems.tech247.hr.Religions;

/**
 *
 * @author Wilfred
 */
public class NodeReligion extends AbstractNode {
    private final InstanceContent instanceContent;
        
        public NodeReligion(Religions emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeReligion (InstanceContent ic, Religions emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName(emp.getReligion());
        }
        
                @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();
        final Religions religion = getLookup().lookup(Religions.class);
        
        Property number = new PropertySupport("number", String.class, "Employee Number", "Number", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return religion.getEmployeesCollection().size();
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
