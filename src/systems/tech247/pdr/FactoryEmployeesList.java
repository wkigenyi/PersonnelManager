/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import systems.tech247.util.QueryEmployee;
import java.util.List;
import javax.swing.AbstractAction;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import systems.tech247.hr.Employees;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryEmployeesList extends ChildFactory<Object>{

    QueryEmployee query;
    
    public FactoryEmployeesList(QueryEmployee query){
        this.query = query;
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
        //Populate the list of child entries
        /*list.add(new AddTool(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeePersonalInfoEditorTopComponent newEditor = new EmployeePersonalInfoEditorTopComponent();
                newEditor.open();
                newEditor.requestActive();
            }
        }));*/
        list.addAll(query.getList());
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Object key){
        Node node = null;
        if(key instanceof Employees){
            node = new NodeEmployeeDetails((Employees)key);
        }else{
            node = new NodeAddTool((AddTool)(key));
        }
        
        return node;
    }
    
    
    
}
