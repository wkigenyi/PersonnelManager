/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import systems.tech247.view.FactoryCategories;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.awt.StatusDisplayer;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.hr.CSSSCategories;
import systems.tech247.util.CapCreatable;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;
import systems.tech247.util.CetusUTL;

/**
 *
 * @author Wilfred
 */
public class NodeSetupCompanyStructure extends AbstractNode{
        
        private final InstanceContent instanceContent;
        CSSSCategories emp;
        boolean edit;
        
        public NodeSetupCompanyStructure(CSSSCategories bank){
            this(new InstanceContent(),bank);
        }
        
        private NodeSetupCompanyStructure (InstanceContent ic, final CSSSCategories bank){
            super(Children.create(new FactoryCategories(bank, true), true), new AbstractLookup(ic));
            this.emp = bank;
            ic.add(bank);
            if(CetusUTL.userRights.contains(229)){
                ic.add(new CapEditable() {
                    @Override
                    public void edit() {
                        TopComponent tc = new CategoryTypeEditorTopComponent(emp);
                        tc.open();
                        tc.requestActive();
                    }
                });
                

                

            }
            
            instanceContent = ic;
            
            
            
            setIconBaseWithExtension("systems/tech247/util/icons/EmpCategory.png");
            setDisplayName(bank.getCSSSCategoryName());
        }

        @Override
        public Action getPreferredAction() {
            return new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TopComponent editor = new CategoryTypeEditorTopComponent(emp);
                    editor.open();
                    editor.requestActive();
                }
            };
        }
        
        
    
}
