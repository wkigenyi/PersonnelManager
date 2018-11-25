/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Employees;
import systems.tech247.hr.Ref;
import systems.tech247.pdreditors.RefereeEditorTopComponent;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;

/**
 *
 * @author Admin
 */
public class FactoryEmployeeReferee extends ChildFactory<Object> implements LookupListener {
    
    Result<NodeRefreshRefereeEvent> result;
    private static final Logger logger = Logger.getLogger(FactoryEmployeeReferee.class.getName());
    Employees emp;
    
    public FactoryEmployeeReferee(Employees emp){
        this.emp = emp;
        this.result = UtilityPDR.getInstance().getLookup().lookupResult(NodeRefreshRefereeEvent.class);
        result.addLookupListener(this);
    }
    
    

    @Override
    protected boolean createKeys(List<Object> list) {
        /*list.add(new AddTool(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent tc  = new RefereeEditorTopComponent(emp);
                tc.open();
                tc.requestActive();
            }
        }));*/
        list.addAll(new DataAccess().searchReferees(emp));
        
        
        return true;
    }
    @Override
    protected Node createNodeForKey(Object key) {
        
        Node node =  null;
        if(key instanceof AddTool){
            node= new NodeAddTool((AddTool)key);
        }else if(key instanceof Ref){
            node= new NodeEmployeeReferee((Ref)key);
        }
        
        return node;
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result r = (Lookup.Result) le.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof NodeRefreshRefereeEvent){
                refresh(true);
            }
            
        }
    }
    
}
