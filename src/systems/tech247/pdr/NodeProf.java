/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.beans.IntrospectionException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Prof;
import systems.tech247.pdreditors.ProfEditorTopComponent;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;


/**
 *
 * @author Admin
 */
public class NodeProf extends AbstractNode implements LookupListener{
    
InstanceContent content;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
EntityManager em = DataAccess.entityManager;
Prof contact;
Lookup.Result<String> rslt;
    
    public NodeProf(final Prof contact,InstanceContent ic) throws IntrospectionException{
        super(Children.LEAF,new AbstractLookup(ic));
        this.contact = contact;
        rslt = UtilityPDR.getInstance().getLookup().lookupResult(String.class);
        rslt.addLookupListener(this);
        
        //add the capabilities to the instance content
        ic.add(new CapDeletable() {
            @Override
            public void delete() {
                //Delete
                String sql = "DELETE FROM Prof WHERE ID="+contact.getId()+"";
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
                TopComponent tc = new ProfEditorTopComponent(contact, null);
                tc.open();
                tc.requestActive();
            }
        });
        
        
        setDisplayName(contact.getAward());
        setIconBaseWithExtension("systems/tech247/util/icons/student.png");
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result r = (Lookup.Result) ev.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof String){
                Prof co = DataAccess.entityManager.find(Prof.class, contact.getId());
                setDisplayName(co.getAward());
            }
            
        }
    }

    

    
    
    
    
}
