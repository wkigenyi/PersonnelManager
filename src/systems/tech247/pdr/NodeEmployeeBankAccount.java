/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.beans.IntrospectionException;
import java.util.Collection;
import javax.persistence.EntityManager;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.EmployeeBankAccounts;
import systems.tech247.pdreditors.AccountEditorTopComponent;
import systems.tech247.util.CapEditable;


/**
 *
 * @author Admin
 */
public class NodeEmployeeBankAccount extends AbstractNode implements LookupListener{
    
InstanceContent content;
EntityManager em = DataAccess.entityManager;
EmployeeBankAccounts contact;
Lookup.Result<NodeRefreshBankAccountEvent> rslt;

    public NodeEmployeeBankAccount(EmployeeBankAccounts contact) throws IntrospectionException{
        this(contact, new InstanceContent());
    }
    
    public NodeEmployeeBankAccount(final EmployeeBankAccounts contact,InstanceContent ic) throws IntrospectionException{
        super(Children.LEAF,new AbstractLookup(ic));
        this.contact = contact;
        rslt = UtilityPDR.getInstance().getLookup().lookupResult(NodeRefreshBankAccountEvent.class);
        rslt.addLookupListener(this);
        
        //add the capabilities to the instance content
        
        
        ic.add(new CapEditable() {
            @Override
            public void edit() {
                TopComponent tc = new AccountEditorTopComponent(contact, null);
                tc.open();
                tc.requestActive();
            }
        });
        
        
        setDisplayName(contact.getBankBranchID().getBankID().getBankName()+"-"+this.contact.getAccountNumber());
        setIconBaseWithExtension("systems/tech247/util/icons/capex.png");
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result r = (Lookup.Result) ev.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof NodeRefreshBankAccountEvent){
                EmployeeBankAccounts co = DataAccess.entityManager.find(EmployeeBankAccounts.class, contact.getEmployeeBankAccountID());
                setDisplayName(co.getBankBranchID().getBankID().getBankName()+"-"+co.getAccountNumber());
            }
            
        }
    }

    

    
    
    
    
}
