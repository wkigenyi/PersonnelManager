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
import org.openide.util.lookup.Lookups;
import systems.tech247.hr.Employees;


/**
 *
 * @author Admin
 */
public class NodeBirthDayEmployee extends AbstractNode{
    
   
    
    public NodeBirthDayEmployee(Employees emp){
        super(Children.LEAF,Lookups.singleton(emp));
        
        setDisplayName(emp.getSurName()+" "+emp.getOtherNames());
        setIconBaseWithExtension("systems/tech247/util/icons/person.png");
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Set set = Sheet.createPropertiesSet();
        final Employees emp  = getLookup().lookup(Employees.class);
        
        Property bday = new PropertySupport("bday", String.class, "Birthday", "Birthday", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                SimpleDateFormat sdf =  new SimpleDateFormat("EEE,dd-MMM");
                return sdf.format(emp.getDateOfBirth());
            }
            
            @Override
            public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(bday);
        Property dept = new PropertySupport("dept", String.class, "Department", "Department", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return emp.getOrganizationUnitID().getOrganizationUnitName();
            }
            
            @Override
            public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(dept);
        
        
        sheet.put(set);
        return sheet;
    }
    
    
    
}
