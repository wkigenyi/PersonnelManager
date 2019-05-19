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
import systems.tech247.hr.BankBranches;
import systems.tech247.hr.Banks;
import systems.tech247.api.ReloadableQueryCapability;
import systems.tech247.dbaccess.DataAccess;

/**
 *
 * @author Wilfred
 */
public class QueryBankBranches implements Lookup.Provider {
    
    InstanceContent ic;
    Lookup lookup;
    Banks bank;
    private List<BankBranches> list;
    
    public QueryBankBranches(final Banks bank){
        this.bank = bank;
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
                ProgressHandle ph = ProgressHandleFactory.createHandle("Loading..");
                ph.start();
                //DataAccess da = new DataAccess();
                //Banks b = da.getEntityManager().find(Banks.class, bank.getBankID());
                
                if(bank == null){
                    List<BankBranches> list = DataAccess.getBankBranches();
                    for(BankBranches b: list){
                        getList().add(b);
                    }
                }else{
                    //Branches For A Specific Bank
                    Collection<BankBranches> list = bank.getBankBranchesCollection();
                    for (BankBranches e: list){
                    
                        getList().add(e);
                    
                }
                    
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
    public List<BankBranches> getList() {
        return list;
    }
    
}
