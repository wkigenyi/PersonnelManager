/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.JobPositionJDs;
import systems.tech247.hr.JobPositions;
import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class QueryJDForJobAndCategory implements Lookup.Provider {
    
    InstanceContent ic;
    Lookup lookup;
    JobPositions position;
    int categoryID;
    private List<JobPositionJDs> list;
    
    public QueryJDForJobAndCategory(final JobPositions position, final int categoryID){
        this.position = position;
        this.categoryID = categoryID;
        list = new ArrayList<>();
        // create an InstanceContent to hold capabilities
        ic = new InstanceContent();
        // create an abstract look to expose the contents of the instance content
        lookup = new AbstractLookup(ic);
        //Add a reloadable capabiliry to the instance content
        ic.add(new ReloadableQueryCapability() {
            @Override
            public void reload() throws Exception {
                
                getList().removeAll(list);
                ProgressHandle ph = ProgressHandleFactory.createHandle("Loading JobDescriptions..");
                ph.start();
                DataAccess da = new DataAccess();
                Collection<JobPositionJDs> list = da.searchJDsForJobAndCategory(position,categoryID);
                for (JobPositionJDs e: list){
                    
                        getList().add(e);
                    
                }
                ph.finish();
            }
        });
    }

    @Override
    public Lookup getLookup() {
        return lookup;
    }

    /**
     * @return the sqlString
     */
    

    /**
     * @param sqlString the sqlString to set
     */
    

    /**
     * @return the list
     */
    public List<JobPositionJDs> getList() {
        return list;
    }
    
}
