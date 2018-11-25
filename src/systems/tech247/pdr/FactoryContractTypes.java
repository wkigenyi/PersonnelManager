/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.List;
import javax.swing.AbstractAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.hr.PdContractTypes;

import systems.tech247.api.ReloadableQueryCapability;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.util.AddTool;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;
import systems.tech247.util.NodeAddTool;

/**
 *
 * @author Wilfred
 */
public class FactoryContractTypes extends ChildFactory<Object> implements LookupListener{

    QueryContractTypes query;
    Boolean edit;
    Lookup.Result<NodeContractTypesRefreshEvent> result;
    
    public FactoryContractTypes(QueryContractTypes query,Boolean edit){
        this.query = query;
        this.edit = edit;
        this.result = UtilityPDR.getInstance().getLookup().lookupResult(NodeContractTypesRefreshEvent.class);
        result.addLookupListener(this);
    }
    
    @Override
    protected boolean createKeys(List<Object> list) {
        //get this ability from the look
        ReloadableQueryCapability r = query.getLookup().lookup(ReloadableQueryCapability.class);
        //Use the ability
        if(r != null){
            try{
                r.reload();
            }catch(Exception ex){
                
            }
        }
        //Edit Mode
        if(edit){
            list.add(new AddTool(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TopComponent tc = new ContractTypeEditorTopComponent();
                    tc.open();
                    tc.requestActive();
                }
            }));
        }
        
        //Populate the list of child entries
        list.addAll(query.getList());
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Object key){
        Node node = null;
        if(key instanceof PdContractTypes){
            node = new CtypeNode((PdContractTypes)key,edit);
        }else if(key instanceof AddTool){
            node = new NodeAddTool((AddTool)key);
        }
       
        
        return node;
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result r = (Lookup.Result) le.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof NodeContractTypesRefreshEvent){
                refresh(true);
            }
            
        }
    }
    
    private class CtypeNode extends AbstractNode implements LookupListener{
        
        private final InstanceContent instanceContent;
        Boolean edit;
        PdContractTypes p;
        Lookup.Result<NodeContractTypesRefreshEvent> result;
        public CtypeNode(PdContractTypes emp, Boolean edit){
            this(new InstanceContent(),emp,edit);
            this.p = emp;
        }
        
        private CtypeNode (InstanceContent ic, PdContractTypes emp,Boolean edit){
            super(Children.LEAF, new AbstractLookup(ic));
            this.edit = edit;
            p = emp;
            instanceContent = ic;
            instanceContent.add(emp);
            instanceContent.add(new CapEditable() {
                @Override
                public void edit() {
                    TopComponent tc = new ContractTypeEditorTopComponent(p);
                    tc.open();
                    tc.requestActive();
                }
            });
            instanceContent.add(new CapDeletable() {
                @Override
                public void delete() {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/capex.png");
            setDisplayName(emp.getDescription());
            this.result = UtilityPDR.getInstance().getLookup().lookupResult(NodeContractTypesRefreshEvent.class);
            result.addLookupListener(this);
        }

        @Override
        public void resultChanged(LookupEvent ev) {
            Lookup.Result r = (Lookup.Result) ev.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof NodeContractTypesRefreshEvent){
                PdContractTypes pdc = DataAccess.entityManager.find(PdContractTypes.class, p.getId());
                setDisplayName(pdc.getDescription());
            }
            
        }
        }
    
}
    
}
