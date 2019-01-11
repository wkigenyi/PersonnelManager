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
import systems.tech247.hr.Contracts;

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
import systems.tech247.pdreditors.ContractEditorTopComponent;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;


/**
 *
 * @author Admin
 */
public class NodeContract extends AbstractNode implements LookupListener{
    
InstanceContent content;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
EntityManager em = DataAccess.entityManager;
Contracts contact;
Lookup.Result<String> rslt;
    
    public NodeContract(final Contracts contact,InstanceContent ic) throws IntrospectionException{
        super(Children.LEAF,new AbstractLookup(ic));
        this.contact = contact;
        rslt = UtilityPDR.getInstance().getLookup().lookupResult(String.class);
        rslt.addLookupListener(this);
        ic.add(contact);
                
        //add the capabilities to the instance content
        ic.add(new CapDeletable() {
            @Override
            public void delete() {
                //Delete
                String sql = "DELETE FROM employment WHERE ID="+contact+"";
                Query query = em.createNativeQuery(sql);
                em.getTransaction().begin();
                query.executeUpdate();
                em.getTransaction().commit();
                //send notification that the view should be freshed ..via lookup
                UtilityPDR.pdrIC.add(new NodeContractRefreshEvent());
                
            }
        });
        
        ic.add(new CapEditable() {
            @Override
            public void edit() {
                TopComponent tc = new ContractEditorTopComponent(contact, null);
                tc.open();
                tc.requestActive();
            }
        });
        
        
        setDisplayName(contact.getDescription());
        setIconBaseWithExtension("systems/tech247/util/icons/capex.png");
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result r = (Lookup.Result) ev.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof String){
                Contracts co = DataAccess.entityManager.find(Contracts.class, contact.getId());
                setDisplayName(co.getDescription());
            }
            
        }
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Set set = Sheet.createPropertiesSet();
        final Contracts contract = getLookup().lookup(Contracts.class);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        
        
        Property start = new PropertySupport("start", String.class, "START", "START", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return sdf.format(contract.getCFrom());
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        Property end = new PropertySupport("end", String.class, "End", "End", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return sdf.format(contract.getCTo());
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(end);
        set.put(start);
        
        sheet.put(set);
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
    
    

    

    
    
    
    
}
