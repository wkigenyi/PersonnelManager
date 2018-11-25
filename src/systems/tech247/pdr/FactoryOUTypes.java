/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.openide.awt.StatusDisplayer;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.BooleanEditor;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.CompanyAssets;
import systems.tech247.hr.Employees;
import systems.tech247.hr.OrganizationUnitTypes;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryOUTypes extends ChildFactory<OrganizationUnitTypes>{

    DataAccess da = new DataAccess();
    EntityManager entityManager =  da.getEntityManager();
    
    QueryOUType query;
    
    public FactoryOUTypes(QueryOUType query){
        this.query = query;
        
    }
    
    @Override
    protected boolean createKeys(List<OrganizationUnitTypes> list) {
        //get this ability from the look
        ReloadableQueryCapability r = query.getLookup().lookup(ReloadableQueryCapability.class);
        //Use the ability
        if(r != null){
            try{
                r.reload();
            }catch(Exception ex){
                
            }
        }
        //Populate the list of child entries
        List<OrganizationUnitTypes> a = query.getList();
        for(OrganizationUnitTypes c : a){
            list.add(c);
        }
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(OrganizationUnitTypes key){
        Node node = new OUTypeNode(key);
       
        
        return node;
    }

    
    
    private class OUTypeNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        
        public OUTypeNode(OrganizationUnitTypes emp){
            this(new InstanceContent(),emp);
        }
        
        private OUTypeNode (InstanceContent ic, OrganizationUnitTypes emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName(emp.getOrganizationUnitTypeName());
            
            
        }
        
        
    
    
    

    
    
    
    
    }
   
    
    
    
}
    

    