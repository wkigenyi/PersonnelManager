/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import systems.tech247.hr.Awards;
import systems.tech247.hr.HrsGroups;
import systems.tech247.hr.HrsUsers;
import systems.tech247.pdreditors.AwardEditorTopComponent;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;
import systems.tech247.util.CetusUTL;


/**
 *
 * @author Admin
 */
public class NodeEmployeeAward extends AbstractNode implements LookupListener{
    
InstanceContent content;
EntityManager em = DataAccess.entityManager;
Awards contact;
Lookup.Result<String> rslt;
Lookup.Result<HrsUsers> userRslt  = CetusUTL.getInstance().getLookup().lookupResult(HrsUsers.class);
List<Integer> rights = new ArrayList();
    
    public NodeEmployeeAward(final Awards contact,InstanceContent ic) throws IntrospectionException{
        super(Children.LEAF,new AbstractLookup(ic));
        this.contact = contact;
        rslt = UtilityPDR.getInstance().getLookup().lookupResult(String.class);
        rslt.addLookupListener(this);
        
        //add the capabilities to the instance content
        ic.add(new CapDeletable() {
            @Override
            public void delete() {
                //Delete
                String sql = "DELETE FROM Awards WHERE ID="+contact.getId()+"";
                Query query = em.createNativeQuery(sql);
                em.getTransaction().begin();
                query.executeUpdate();
                em.getTransaction().commit();
                //send notification that the view should be freshed ..via lookup
                UtilityPDR.pdrIC.add(new NodeRefreshAwardEvent());
                
            }
        });
        
        ic.add(new CapEditable() {
            @Override
            public void edit() {
                TopComponent tc = new AwardEditorTopComponent(contact, null);
                tc.open();
                tc.requestActive();
            }
        });
        
        
        setDisplayName(contact.getAward());
        setIconBaseWithExtension("systems/tech247/util/icons/award.png");
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result r = (Lookup.Result) ev.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof String){
                Awards co = DataAccess.entityManager.find(Awards.class, contact.getId());
                setDisplayName(co.getAward());
            }else if(object instanceof HrsUsers){
                HrsGroups group = ((HrsUsers) object).getGroupID();
                
            }
            
        }
    }

    

    
    
    
    
}
