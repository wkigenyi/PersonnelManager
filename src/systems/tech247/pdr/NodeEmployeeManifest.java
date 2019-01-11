/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
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
import systems.tech247.hr.Employees;
import systems.tech247.util.CapEditable;

/**
 *
 * @author Admin
 */
public class NodeEmployeeManifest extends AbstractNode{
    InstanceContent content = new InstanceContent();
    
    Employees emp;
    
    public NodeEmployeeManifest(Employees emp){
        this(emp, new InstanceContent());
    }
    
    public NodeEmployeeManifest(final Employees emp, InstanceContent ic){
        super(Children.LEAF,new AbstractLookup(ic));
        ic.add(emp);
        ic.add(new CapEditable() {
            @Override
            public void edit() {
                //Open the details
                TopComponent editor = new EmployeePersonalInfoEditorTopComponent(emp);
                editor.open();
                editor.requestActive();
                //Expand the node
            }
        });
        setIconBaseWithExtension("systems/tech247/util/icons/person.png");
            setDisplayName(emp.getSurName());
    }

    @Override
    public Action getPreferredAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        };
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Set set = Sheet.createPropertiesSet();
        final Employees employee = getLookup().lookup(Employees.class);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Property dob = new PropertySupport("dob", String.class, PROP_DISPLAY_NAME, PROP_SHORT_DESCRIPTION, true, true) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                String dob = "Not Specified";
                try{
                    dob = sdf.format(employee.getDateOfBirth());
                }catch(NullPointerException ex){
                    
                }
                return dob;
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        set.put(dob);
        
        Property gender = new PropertySupport("gender", String.class, "Gender", "Gender", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                
                if(null==employee.getGender()){
                    return "NOT SPECIFIED";
                }else switch (employee.getGender()) {
                    case 1:
                        return "MALE";
                    case 2:
                        return "FEMALE";
                    default:
                        return "NOT SPECIFIED";
                }
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Property code = new PropertySupport("code", String.class, "CODE", "CODE", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return employee.getEmpCode(); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Property category = new PropertySupport("cat", String.class, "Category", "Category", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return employee.getCategoryID().getCategoryName(); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        Property dept = new PropertySupport("dept", String.class, "Dept", "Dept", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return employee.getOrganizationUnitID().getOrganizationUnitName();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Property othername = new PropertySupport("othername", String.class, "Othername", "Othername", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return employee.getOtherNames();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(othername);
        set.put(dept);
        set.put(category);
        set.put(gender);
        set.put(code);
        sheet.put(set);
        
        return sheet;
    }
    
    
    
    
}
