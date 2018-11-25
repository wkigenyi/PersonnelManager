/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdrsetup;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.windows.TopComponent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.pdr.CompanyEditorTopComponent;

/**
 *
 * @author Admin
 */
public class NodePDRSetup extends  AbstractNode{

    public NodePDRSetup() {
        super(Children.create(new FactoryPDRSetup(), true));
        setDisplayName(DataAccess.getDefaultCompany().getCompanyName());
        setIconBaseWithExtension("systems/tech247/util/icons/company.png");
    }

    @Override
    public Action getPreferredAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent editor = new CompanyEditorTopComponent(DataAccess.getDefaultCompany());
                editor.open();
                editor.requestActive();
            }
        };
    }
    
    
    
}
