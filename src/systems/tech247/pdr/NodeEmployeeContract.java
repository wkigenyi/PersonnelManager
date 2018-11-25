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
import systems.tech247.hr.Contracts;
import systems.tech247.hr.Employees;
import systems.tech247.pdreditors.ContractEditorTopComponent;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;


/**
 *
 * @author Admin
 */
public class NodeEmployeeContract extends AbstractNode{
    InstanceContent content = new InstanceContent();
    public NodeEmployeeContract(Contracts cont){
        this(cont,new InstanceContent());
    }
    
    private NodeEmployeeContract(final Contracts cont,InstanceContent ic){
        super(Children.LEAF,new AbstractLookup(ic));
        Employees e = cont.getEmployeeId();
        this.content = ic;
        content.add(new CapEditable() {
            @Override
            public void edit() {
                TopComponent tc = new ContractEditorTopComponent(cont, null);
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
        setDisplayName(e.getSurName()+" "+e.getOtherNames());
        setIconBaseWithExtension("systems/tech247/util/icons/capex.png");
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Set set = Sheet.createPropertiesSet();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        final Contracts emp  = getLookup().lookup(Contracts.class);
        
        Property type = new PropertySupport("type", String.class, "Type", "Type", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                
                return emp.getContractTypeID().getDescription();
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
