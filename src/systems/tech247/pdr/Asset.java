/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import systems.tech247.hr.CompanyAssets;

/**
 *
 * @author Wilfred
 */
 public class Asset {
    
    private Boolean select;
    private CompanyAssets asset;
    public Asset(CompanyAssets asset,Boolean select){
        this.asset = asset;
        this.select = select;
        
    }

    /**
     * @return the select
     */
    public Boolean getSelect() {
        return select;
    }

    /**
     * @param select the select to set
     */
    public void setSelect(Boolean select) {
        this.select = select;
    }

    /**
     * @return the asset
     */
    public CompanyAssets getAsset() {
        return asset;
    }

    /**
     * @param asset the asset to set
     */
    public void setAsset(CompanyAssets asset) {
        this.asset = asset;
    }
    
}
