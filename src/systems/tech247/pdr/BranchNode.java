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
import systems.tech247.hr.BankBranches;
import systems.tech247.pdreditors.PDRBankBranchEditor;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;

/**
 *
 * @author Wilfred
 */
public class BranchNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        Boolean edit;
        public BranchNode(BankBranches emp,Boolean edit){
            this(new InstanceContent(),emp,edit);
        }
        
        private BranchNode (InstanceContent ic, final BankBranches emp, boolean edit){
            super(Children.LEAF, new AbstractLookup(ic));
            this.edit= edit;
            instanceContent = ic;
            instanceContent.add(emp);
            
            if(edit){
            
            instanceContent.add(new CapEditable() {
                @Override
                public void edit() {
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRBankBranchEditor(emp), "Edit Branch", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
        //nd.setNoDefaultClose(true);  
        DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            
            instanceContent.add(new CapDeletable() {
                @Override
                public void delete() {
                    StatusDisplayer.getDefault().setStatusText("Deleting Branch"); //To change body of generated methods, choose Tools | Templates.
                }
            });
            }
            setIconBaseWithExtension("systems/tech247/util/icons/bankBranch.png");
            setDisplayName(emp.getBranchName());
        }
    
}