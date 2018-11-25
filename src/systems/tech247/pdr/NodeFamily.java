/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;
import systems.tech247.hr.Family;
import systems.tech247.pdreditors.FamilyEditorTopComponent;


/**
 *
 * @author Admin
 */
public class NodeFamily extends AbstractNode{
    
   Family contact;
    
    public NodeFamily(Family contact){
        super(Children.LEAF,Lookups.singleton(contact));
        this.contact  = contact;
        setDisplayName(contact.getSurName()+" "+contact.getOtherNames());
        setIconBaseWithExtension("systems/tech247/util/icons/person.png");
    }

    @Override
    public Action getPreferredAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                TopComponent tc = new FamilyEditorTopComponent(contact, null);
                tc.open();
                tc.requestActive();
            }
        };
        
    }
    
    

    
    
    
    
}
