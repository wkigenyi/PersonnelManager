/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import systems.tech247.hr.Visa;
import systems.tech247.pdreditors.VisaEditorTopComponent;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;


/**
 *
 * @author Admin
 */
public class NodeVisa extends AbstractNode implements LookupListener{
    
InstanceContent content;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
     
EntityManager em = DataAccess.entityManager;
Visa contact;
Lookup.Result<String> rslt;
    
    public NodeVisa(final Visa contact,InstanceContent ic) throws IntrospectionException{
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
                String sql = "DELETE FROM Visa WHERE ID="+contact.getId()+"";
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
                TopComponent tc = new VisaEditorTopComponent(contact, null);
                tc.open();
                tc.requestActive();
            }
        });
        
        
        setDisplayName(contact.getPermit());
        setIconBaseWithExtension("systems/tech247/util/icons/capex.png");
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result r = (Lookup.Result) ev.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof String){
                Visa co = DataAccess.entityManager.find(Visa.class, contact.getId());
                setDisplayName(co.getPermit());
            }
            
        }
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Set set =  Sheet.createPropertiesSet();
        final Visa visa = getLookup().lookup(Visa.class);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        final NumberFormat nf = new DecimalFormat("#,###.00");
        Property from = new PropertySupport("from", String.class, "FROM", "FROM", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return sdf.format(visa.getCFrom());
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Property to = new PropertySupport("to", String.class, "TO", "TO", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return sdf.format(visa.getCTo());
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Property receipt = new PropertySupport("receipt", String.class, "TO", "TO", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return visa.getRcptNo();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Property currency = new PropertySupport("currency", String.class, "TO", "TO", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return visa.getCurrencyId().getCurrencyName();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Property amount = new PropertySupport("amount", String.class, "TO", "TO", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return nf.format(visa.getAmount());
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        set.put(to);
        set.put(from);
        set.put(receipt);
        set.put(amount);
        set.put(currency);
        
        
        sheet.put(set);
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
    
    

    

    
    
    
    
}
