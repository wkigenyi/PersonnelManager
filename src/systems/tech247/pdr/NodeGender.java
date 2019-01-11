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
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.util.Gender;

/**
 *
 * @author Wilfred
 */
public class NodeGender extends AbstractNode{
    
    private final InstanceContent instanceContent;
        
        public NodeGender(Gender emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeGender (InstanceContent ic, Gender emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName(emp.toString());
        }

        @Override
        protected Sheet createSheet() {
            Sheet sheet = Sheet.createDefault();
            Sheet.Set set = Sheet.createPropertiesSet();
            final Gender gender = getLookup().lookup(Gender.class);
            
            Node.Property number = new PropertySupport("number", String.class, "Number", "Number", true, false) {
                @Override
                public Object getValue() throws IllegalAccessException, InvocationTargetException {
                    return DataAccess.getGenderCount(gender.getCode());
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
