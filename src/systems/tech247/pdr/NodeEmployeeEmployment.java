/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.nodes.Sheet.Set;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Employment;
import systems.tech247.pdreditors.EmploymentEditorTopComponent;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;


/**
 *
 * @author Admin
 */
public class NodeEmployeeEmployment extends AbstractNode implements LookupListener{
    
InstanceContent content;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
EntityManager em = DataAccess.entityManager;
Employment contact;
Lookup.Result<String> rslt;
    
    public NodeEmployeeEmployment(final Employment contact,InstanceContent ic) throws IntrospectionException{
        super(Children.LEAF,new AbstractLookup(ic));
        this.contact = contact;
        rslt = UtilityPDR.getInstance().getLookup().lookupResult(String.class);
        rslt.addLookupListener(this);
        
        //add the capabilities to the instance content
        ic.add(new CapDeletable() {
            @Override
            public void delete() {
                //Delete
                String sql = "DELETE FROM employment WHERE ID="+contact.getId()+"";
                Query query = em.createNativeQuery(sql);
                em.getTransaction().begin();
                query.executeUpdate();
                em.getTransaction().commit();
                //send notification that the view should be freshed ..via lookup
                UtilityPDR.pdrIC.add(new NodeContactRefreshEvent());
                
            }
        });
        
        ic.add(new CapEditable() {
            @Override
            public void edit() {
                TopComponent tc = new EmploymentEditorTopComponent(contact, null);
                tc.open();
                tc.requestActive();
            }
        });
        
        
        setDisplayName(contact.getEmployer());
        setIconBaseWithExtension("systems/tech247/util/icons/company.png");
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result r = (Lookup.Result) ev.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof String){
                Employment co = DataAccess.entityManager.find(Employment.class, contact.getId());
                setDisplayName(co.getEmployer());
            }
            
        }
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Set set = Sheet.createPropertiesSet();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        final Employment emp = getLookup().lookup(Employment.class);
        
        Property from = new PropertySupport("from", String.class, "FROM", "FROM", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return sdf.format(emp.getCFrom());
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Property to = new PropertySupport("to", String.class, "TO", "TO", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return sdf.format(emp.getCTo());
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Property contact = new PropertySupport("contact", String.class, "CONTACT", "CONTACT", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return emp.getPhone();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Property designation = new PropertySupport("designation", String.class, "CONTACT", "CONTACT", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return emp.getDesig();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        
        set.put(to);
        set.put(from);
        set.put(designation);
        set.put(contact);
        sheet.put(set);
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
    
    

    

    
    
    
    
}
