/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.nodes.Sheet.Set;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.hr.Employees;
import systems.tech247.hr.Visa;
import systems.tech247.pdreditors.VisaEditorTopComponent;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;


/**
 *
 * @author Admin
 */
public class NodeEmployeeVisa extends AbstractNode{
    InstanceContent content;
    public NodeEmployeeVisa(Visa visa){
        this(visa,new InstanceContent());
    }
    
    private NodeEmployeeVisa(final Visa cont,InstanceContent ic){
        super(Children.LEAF,new AbstractLookup(ic));
        this.content = ic;
        
        Employees e = cont.getEmployeeId();
        setDisplayName(e.getSurName()+" "+e.getOtherNames());
        setIconBaseWithExtension("systems/tech247/util/icons/capex.png");
        content.add(new CapEditable() {
            @Override
            public void edit() {
                TopComponent tc = new VisaEditorTopComponent(cont, null);
                tc.open();
                tc.requestActive();
                        
            }
        });
        content.add(new CapDeletable() {
            @Override
            public void delete() {
                //Implement Delete
            }
        });
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Set set = Sheet.createPropertiesSet();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        final Visa emp  = getLookup().lookup(Visa.class);
        
        Property type = new PropertySupport("rcpt", String.class, "Receipt", "Receipt", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                
                return emp.getRcptNo();
            }
            
            @Override
            public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(type);
        Property start = new PropertySupport("start", String.class, "Start", "Start", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return sdf.format(emp.getCFrom());
            }
            
            @Override
            public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(start);
        
        Property end = new PropertySupport("end", String.class, "End", "End", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return sdf.format(emp.getCTo());
            }
            
            @Override
            public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(end);
        
        
        sheet.put(set);
        return sheet;
    }
    
    
    
}
