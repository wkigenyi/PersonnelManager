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
import systems.tech247.hr.Ref;
import systems.tech247.pdreditors.RefereeEditorTopComponent;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;


/**
 *
 * @author Admin
 */
public class NodeEmployeeReferee extends AbstractNode{
    
    Ref contact;
    InstanceContent content;
    
    public NodeEmployeeReferee(Ref contact){
        this(contact,new InstanceContent());
    }
    
    
    public NodeEmployeeReferee(final Ref contact, InstanceContent ic){
        super(Children.LEAF,new AbstractLookup(ic));
        this.contact  = contact;
        content = ic;
        content.add(new CapEditable() {
            @Override
            public void edit() {
                TopComponent tc = new RefereeEditorTopComponent(contact, null);
                tc.open();
                tc.requestActive();
            }
        });
        content.add(new CapDeletable() {
            @Override
            public void delete() {
                //Implement delete
            }
        });
        content.add(contact);
        setDisplayName(contact.getSurname()+" "+contact.getOtherNames());
        setIconBaseWithExtension("systems/tech247/util/icons/person.png");
    }

    @Override
    public Action getPreferredAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                TopComponent tc = new RefereeEditorTopComponent(contact, null);
                tc.open();
                tc.requestActive();
            }
        };
        
    }

    @Override
    protected Sheet createSheet() {
        Set set = Sheet.createPropertiesSet();
        Sheet sheet = Sheet.createDefault();
        final Ref ref = getLookup().lookup(Ref.class);
        
        Property email;
        email = new PropertySupport("email", String.class, "Email", "Email", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return ref.getEMail();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(email);
        
        Property phone;
        phone = new PropertySupport("phone", String.class, "Phone", "Phone", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return ref.getPhone();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(phone);
        
        Property mobile;
        mobile = new PropertySupport("mobile", String.class, "Mobile", "Mobile", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return ref.getMNo();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(mobile);
        

        
        Property address;
        address = new PropertySupport("address", String.class, "Address", "Address", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return ref.getAddress();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(address);
        
        
        sheet.put(set);
        return sheet;
    }
    
    
    
    

    
    
    
    
}
