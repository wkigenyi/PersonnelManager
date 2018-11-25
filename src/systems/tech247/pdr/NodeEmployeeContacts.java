/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
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
import systems.tech247.hr.Contacts;
import systems.tech247.pdreditors.ContactEditorTopComponent;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;


/**
 *
 * @author Admin
 */
public class NodeEmployeeContacts extends AbstractNode implements LookupListener{
    
InstanceContent content;
EntityManager em = DataAccess.entityManager;
Contacts contact;
Lookup.Result<NodeContactRefreshEvent> rslt;
    
    public NodeEmployeeContacts(final Contacts contact,InstanceContent ic) throws IntrospectionException{
        super(Children.LEAF,new AbstractLookup(ic));
        this.contact = contact;
        rslt = UtilityPDR.getInstance().getLookup().lookupResult(NodeContactRefreshEvent.class);
        rslt.addLookupListener(this);
        
        //add the capabilities to the instance content
        ic.add(new CapDeletable() {
            @Override
            public void delete() {
                //Delete
                String sql = "DELETE FROM Contacts WHERE ContactID="+contact.getContactID()+"";
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
                TopComponent tc = new ContactEditorTopComponent(contact, null);
                tc.open();
                tc.requestActive();
            }
        });
        
        ic.add(contact);
        
        
        setDisplayName(contact.getContact());
        setIconBaseWithExtension("systems/tech247/util/icons/phonebook.png");
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result r = (Lookup.Result) ev.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof String){
                Contacts co = DataAccess.entityManager.find(Contacts.class, contact.getContactID());
                setDisplayName(co.getContact());
            }
            
        }
    }

    @Override
    protected Sheet createSheet() {
        Set set = Sheet.createPropertiesSet();
        Sheet s = Sheet.createDefault();
        final Contacts contact = getLookup().lookup(Contacts.class);
        
        Property comment;
        
            comment = new PropertySupport("comment", String.class, "Comment", "Comment", true, false) {
                @Override
                public Object getValue() throws IllegalAccessException, InvocationTargetException {
                    return contact.getComments();
                }
                
                @Override
                public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
            
        Property contactType;
            contactType = new PropertySupport("type", String.class, "Contact Type", "Contact Type", true, false) {
                @Override
                public Object getValue() throws IllegalAccessException, InvocationTargetException {
                    return contact.getCtypeID().getDescription();
                }
                
                @Override
                public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
            set.put(contactType);
            set.put(comment);
            s.put(set);
        
        
        
        
        
        return s;
    }

    

    
    
    
    
}
