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
import systems.tech247.hr.JobPositions;
import systems.tech247.util.CapEditable;
import systems.tech247.util.CetusUTL;

/**
 *
 * @author Wilfred
 */
public class NodeSetupJobPosition extends AbstractNode{
        
        private final InstanceContent instanceContent;
        JobPositions position;
        
        public NodeSetupJobPosition(JobPositions emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeSetupJobPosition (InstanceContent ic, JobPositions unit){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            position = unit;
            instanceContent.add(unit);
            if(CetusUTL.userRights.contains(233)){
                instanceContent.add(new CapEditable(){
                    @Override
                    public void edit() {
                        TopComponent tc = new JobPositionEditorTopComponent(position);
                        tc.open();
                        tc.requestActive();
                    }
                    
                });
            }
            
            setIconBaseWithExtension("systems/tech247/util/icons/position.png");
            setDisplayName(unit.getPositionName());
        }
        
            @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();
        final JobPositions position = getLookup().lookup(JobPositions.class);
        
        Property number = new PropertySupport("number", String.class, "Number Of Employees", "Number Of Employees", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return position.getEmployeesCollection().size();
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
