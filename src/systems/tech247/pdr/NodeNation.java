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
import systems.tech247.hr.Nationalities;

/**
 *
 * @author Wilfred
 */
public class NodeNation extends AbstractNode{
    private final InstanceContent instanceContent;
        Nationalities emp;
        
        public NodeNation(Nationalities emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeNation (InstanceContent ic, Nationalities emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            this.emp = emp;
            setIconBaseWithExtension("systems/tech247/util/icons/nation.png");
            setDisplayName(emp.getNationality());
        }

        @Override
        public Action getPreferredAction() {
            return new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TopComponent tc = new NationalityEditorTopComponent(emp);
                    tc.open();
                    tc.requestActive();
                }
            };
        }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Set set = Sheet.createPropertiesSet();
        final Nationalities nation = getLookup().lookup(Nationalities.class);
        
        Property number = new PropertySupport("number", String.class, "Employee Number", "Number", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return nation.getEmployeesCollection().size();
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
