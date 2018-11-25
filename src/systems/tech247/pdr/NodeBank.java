/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.StatusDisplayer;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.hr.Banks;
import systems.tech247.pdreditors.PDRBankBranchEditor;
import systems.tech247.pdreditors.PDRBankEditor;
import systems.tech247.util.CapCreatable;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;

/**
 *
 * @author Wilfred
 */
public class NodeBank extends AbstractNode{
        
        private final InstanceContent instanceContent;
        
        public NodeBank(Banks bank,boolean edit){
            this(new InstanceContent(),bank,edit);
        }
        
        private NodeBank (InstanceContent ic, final Banks bank,boolean edit){
            super(Children.create(new FactoryBankBranches(bank,edit), true), new AbstractLookup(ic));
            ic.add(bank);
            if(edit){
                ic.add(new CapEditable() {
                    @Override
                    public void edit() {
                        NotifyDescriptor nd = new NotifyDescriptor(new PDRBankEditor(bank), "Edit Bank", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
        //nd.setNoDefaultClose(true);  
        DialogDisplayer.getDefault().notifyLater(nd);
                    }
                });
                
                ic.add(new CapCreatable() {
                    @Override
                    public void create() {
                        NotifyDescriptor nd = new NotifyDescriptor(new PDRBankBranchEditor(bank), "Add New Branch", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
        //nd.setNoDefaultClose(true);  
        DialogDisplayer.getDefault().notifyLater(nd);
                    }
                });
                
                ic.add(new CapDeletable() {
                    @Override
                    public void delete() {
                        StatusDisplayer.getDefault().setStatusText("Delete the Bank");
                    }
                });
            }
            
            instanceContent = ic;
            
            
            
            setIconBaseWithExtension("systems/tech247/util/icons/bank.png");
            setDisplayName(bank.getBankName());
        }
    
}
