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
import systems.tech247.hr.Nationalities;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryNations extends ChildFactory<Object>{

    QueryNation query;
    boolean add;
    public FactoryNations(QueryNation query){
        this.query = query;
    }
    
    public FactoryNations(String sql,boolean add){
        this.query = new QueryNation();
        query.setSqlString(sql);
        this.add = add;
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
        //Add the add tool
        if(add){
            list.add(new AddTool(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TopComponent tc = new NationalityEditorTopComponent();
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
        if(key instanceof Nationalities){
            return new NodeNation((Nationalities)key);
        }else if(key instanceof AddTool){
            return new NodeAddTool((AddTool)key);
        }
        
       
        
        return node;
    }
    
    
    
}
