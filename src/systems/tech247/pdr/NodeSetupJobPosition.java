/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.hr.JobPositions;
import systems.tech247.util.CapEditable;
import systems.tech247.util.CetusUTL;

/**
 *
 * @author Wilfred
 */
public class NodeSetupJobPosition extends AbstractNode{
        
        private final InstanceContent instanceContent;
        JobPositions position;
        
        public NodeSetupJobPosition(JobPositions emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeSetupJobPosition (InstanceContent ic, JobPositions unit){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            position = unit;
            instanceContent.add(unit);
            if(CetusUTL.userRights.contains(233)){
                instanceContent.add(new CapEditable(){
                    @Override
                    public void edit() {
                        TopComponent tc = new JobPositionEditorTopComponent(position);
                        tc.open();
                        tc.requestActive();
                    }
                    
                });
            }
            
            setIconBaseWithExtension("systems/tech247/util/icons/position.png");
            setDisplayName(unit.getPositionName());
        }    
}
