/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.nodes.Sheet.Set;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.hr.TblEmployeeType;

/**
 *
 * @author Wilfred
 */
public class NodeEmployeeType extends AbstractNode {
    private final InstanceContent instanceContent;
        TblEmployeeType position;
        
        public NodeEmployeeType(TblEmployeeType emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeEmployeeType(InstanceContent ic, TblEmployeeType unit){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            position = unit;
            instanceContent.add(unit);
            setIconBaseWithExtension("systems/tech247/util/icons/position.png");
            setDisplayName(unit.getEmpTypeName());
        }

        @Override
        public Action getPreferredAction() {
            return new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TopComponent tc = new EmpTypeEditorTopComponent(position);
                    tc.open();
                    tc.requestActive();
                }
            };
        }
        
        @Override
    protected Sheet createSheet() {
        final TblEmployeeType bean = getLookup().lookup(TblEmployeeType.class);
        
        Sheet sheet = Sheet.createDefault();
        Set set = Sheet.createPropertiesSet();
        
        
        
                
        

        Property number = new PropertySupport("number", String.class, "Number", "Number", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return bean.getEmpTypeCode();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        set.put(number);
        
        
        sheet.put(set);
        
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
}
