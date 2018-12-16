/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.lang.reflect.InvocationTargetException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.hr.Ctypes;

/**
 *
 * @author Wilfred
 */
public class NodeCtype extends AbstractNode {
    private final InstanceContent instanceContent;
        
        public NodeCtype(Ctypes emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeCtype (InstanceContent ic, Ctypes emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName(emp.getDescription());
        }
        @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();
        final Ctypes ctype = getLookup().lookup(Ctypes.class);
        
        Node.Property number = new PropertySupport("desc", String.class, "Employee Number", "Number", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return ctype.getDescription();
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
