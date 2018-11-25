/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.hr.CompanyDetails;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryCompanyDetails extends ChildFactory<CompanyDetails>{

    QueryCompanyDetails query;
    
    
    public FactoryCompanyDetails(QueryCompanyDetails query){
        this.query = query;
    }
    
    @Override
    protected boolean createKeys(List<CompanyDetails> list) {
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
        list.addAll(query.getList());
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(CompanyDetails key){
        Node node = new CompanyNode(key);
       
        
        return node;
    }
    
    private class CompanyNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        CompanyDetails company;
        
        public CompanyNode(CompanyDetails emp){
            this(new InstanceContent(),emp);
        }
        
        private CompanyNode (InstanceContent ic, CompanyDetails emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            this.company = emp;
            setIconBaseWithExtension("systems/tech247/util/icons/company.png");
            setDisplayName(emp.getCompanyName());
        }

        @Override
        public Action getPreferredAction() {
            return new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TopComponent editor = new CompanyEditorTopComponent(company);
                    editor.open();
                    editor.requestActive();
                }
            };
        }
        
        
    
}
    
}
