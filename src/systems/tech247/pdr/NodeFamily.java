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
import systems.tech247.hr.Family;
import systems.tech247.pdreditors.FamilyEditorTopComponent;


/**
 *
 * @author Admin
 */
public class NodeFamily extends AbstractNode{
    
   Family contact;
   
   public NodeFamily(Family contact){
       this(contact,new InstanceContent());
   }
    
    public NodeFamily(Family contact, InstanceContent ic){
        super(Children.LEAF,new AbstractLookup(ic));
        this.contact  = contact;
        ic.add(contact);
        setDisplayName(contact.getSurName());
        setIconBaseWithExtension("systems/tech247/util/icons/person.png");
    }

    @Override
    public Action getPreferredAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                TopComponent tc = new FamilyEditorTopComponent(contact, null);
                tc.open();
                tc.requestActive();
            }
        };
        
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Set set = Sheet.createPropertiesSet();
        final Family family = getLookup().lookup(Family.class);
        
        Property othername = new PropertySupport("othername", String.class, "Surname", "Surname", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return family.getOtherNames();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(othername);
        
        Property relation = new PropertySupport("relation", String.class, "Surname", "Surname", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return family.getRelation();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(relation);
        
        Property mobile = new PropertySupport("mobile", String.class, "Surname", "Surname", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return family.getMNo();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(mobile);
        
        Property tel = new PropertySupport("tel", String.class, "Surname", "Surname", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return family.getHTelNo();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(tel);
        
        Property email = new PropertySupport("email", String.class, "Surname", "Surname", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return family.getEMail();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(email);
        
        
        
        
        
        sheet.put(set);
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    

    
    
    
    
}
