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
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.OrganizationUnits;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;



/**
 *
 * @author Wilfred
 */
public class FactoryDepartments extends ChildFactory<Object>{

    
    
    String sql;
    boolean add;
    public FactoryDepartments(String sql){
        this(sql, false);
    }
    
    public FactoryDepartments(String sql, boolean add){
        this.add = add;
        this.sql = sql;
    }
    
    @Override
    protected boolean createKeys(List<Object> list) {
        if(add){
            list.add(new AddTool(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TopComponent editor = new OUEditorTopComponent();
                    editor.open();
                    editor.requestActive();
                }
            }));
            
        }
        //Populate the list of child entries
        list.addAll(DataAccess.searchDepartments(sql));
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Object key){
        Node node = null;
        if(key instanceof OrganizationUnits){
            node = new NodeSetupDepartment((OrganizationUnits)key);
        }else if(key instanceof AddTool){
            node = new NodeAddTool((AddTool)key);
        }
        
        return node;
    }
    
    
    
}
