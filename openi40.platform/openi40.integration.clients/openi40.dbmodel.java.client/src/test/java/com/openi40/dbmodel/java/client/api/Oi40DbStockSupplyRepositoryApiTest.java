/*
 * Openi40
 * The open source industy 4.0 production scheduler & MES platform
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.openi40.dbmodel.java.client.api;

import com.openi40.dbmodel.java.client.model.AutoCompleteData;
import com.openi40.dbmodel.java.client.model.LookupData;
import com.openi40.dbmodel.java.client.model.OI40DBStockSupply;
import com.openi40.dbmodel.java.client.model.PageInfo;
import com.openi40.dbmodel.java.client.model.PageOI40DBStockSupply;
import com.openi40.dbmodel.java.client.model.QbeSupportOI40DBStockSupply;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for Oi40DbStockSupplyRepositoryApi
 */
@Ignore
public class Oi40DbStockSupplyRepositoryApiTest {

    private final Oi40DbStockSupplyRepositoryApi api = new Oi40DbStockSupplyRepositoryApi();

    
    /**
     * deleteByCode
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteByCodeVoid27Test() {
        String code = null;
        api.deleteByCodeVoid27(code);

        // TODO: test validations
    }
    
    /**
     * deleteByCodes
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteByCodesVoid27Test() {
        List<String> codes = null;
        api.deleteByCodesVoid27(codes);

        // TODO: test validations
    }
    
    /**
     * doAutocomplete
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void doAutocompletePageOI40DBStockSupplyTest() {
        AutoCompleteData autoCompleteData = null;
        PageOI40DBStockSupply response = api.doAutocompletePageOI40DBStockSupply(autoCompleteData);

        // TODO: test validations
    }
    
    /**
     * doLookup
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void doLookupPageOI40DBStockSupplyTest() {
        LookupData lookup = null;
        PageOI40DBStockSupply response = api.doLookupPageOI40DBStockSupply(lookup);

        // TODO: test validations
    }
    
    /**
     * findAll
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findAllListOI40DBStockSupplyTest() {
        List<OI40DBStockSupply> response = api.findAllListOI40DBStockSupply();

        // TODO: test validations
    }
    
    /**
     * findAll
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findAllPageOI40DBStockSupplyTest() {
        PageInfo p = null;
        PageOI40DBStockSupply response = api.findAllPageOI40DBStockSupply(p);

        // TODO: test validations
    }
    
    /**
     * findByAfterIntegrationTs
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findByAfterIntegrationTsListOI40DBStockSupplyTest() {
        String ts = null;
        List<OI40DBStockSupply> response = api.findByAfterIntegrationTsListOI40DBStockSupply(ts);

        // TODO: test validations
    }
    
    /**
     * findByAfterModifiedTimestamp
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findByAfterModifiedTimestampListOI40DBStockSupplyTest() {
        String ts = null;
        List<OI40DBStockSupply> response = api.findByAfterModifiedTimestampListOI40DBStockSupply(ts);

        // TODO: test validations
    }
    
    /**
     * findByCode
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findByCodeOI40DBStockSupplyTest() {
        String code = null;
        OI40DBStockSupply response = api.findByCodeOI40DBStockSupply(code);

        // TODO: test validations
    }
    
    /**
     * findByCodes
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findByCodesListOI40DBStockSupplyTest() {
        List<String> codes = null;
        List<OI40DBStockSupply> response = api.findByCodesListOI40DBStockSupply(codes);

        // TODO: test validations
    }
    
    /**
     * findByQbe
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findByQbeListOI40DBStockSupplyTest() {
        OI40DBStockSupply qbe = null;
        List<OI40DBStockSupply> response = api.findByQbeListOI40DBStockSupply(qbe);

        // TODO: test validations
    }
    
    /**
     * findByQbePaged
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void findByQbePagedPageOI40DBStockSupplyTest() {
        QbeSupportOI40DBStockSupply qbe = null;
        PageOI40DBStockSupply response = api.findByQbePagedPageOI40DBStockSupply(qbe);

        // TODO: test validations
    }
    
    /**
     * update
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateListOI40DBStockSupplyTest() {
        List<OI40DBStockSupply> data = null;
        List<OI40DBStockSupply> response = api.updateListOI40DBStockSupply(data);

        // TODO: test validations
    }
    
    /**
     * updateSingle
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateSingleOI40DBStockSupplyTest() {
        OI40DBStockSupply data = null;
        OI40DBStockSupply response = api.updateSingleOI40DBStockSupply(data);

        // TODO: test validations
    }
    
}
