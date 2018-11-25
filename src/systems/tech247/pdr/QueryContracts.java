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
import systems.tech247.hr.Contacts;
import systems.tech247.hr.Contracts;
import systems.tech247.hr.Employees;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class QueryContracts implements Lookup.Provider {
    
    InstanceContent ic;
    Lookup lookup;
    private List<Contracts> list;
    Employees emp;
    private String sqlString;
    
    public QueryContracts(final Employees emp){
        
        this.emp = emp;
        
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
                ProgressHandle ph = ProgressHandleFactory.createHandle("Loading Contracts..");
                ph.start();
                DataAccess da = new DataAccess();
                Collection<Contracts> list = da.searchContracts(emp);
                for (Contracts e: list){
                    
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
    public String getSqlString() {
        
        
        return sqlString;
    }
    
    /**
     * @param sqlString the sqlString to set
     */
    public void setSqlString(String sqlString) {
        this.sqlString = sqlString;
    }

    

    /**
     * @return the list
     */
    public List<Contracts> getList() {
        return list;
    }
    
}
