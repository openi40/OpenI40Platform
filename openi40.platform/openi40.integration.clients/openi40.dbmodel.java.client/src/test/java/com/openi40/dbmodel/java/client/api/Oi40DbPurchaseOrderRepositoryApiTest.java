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
import com.openi40.dbmodel.java.client.model.OI40DBPurchaseOrder;
import com.openi40.dbmodel.java.client.model.PageInfo;
import com.openi40.dbmodel.java.client.model.PageOI40DBPurchaseOrder;
import com.openi40.dbmodel.java.client.model.QbeSupportOI40DBPurchaseOrder;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for Oi40DbPurchaseOrderRepositoryApi
 */
@Ignore
public class Oi40DbPurchaseOrderRepositoryApiTest {

    private final Oi40DbPurchaseOrderRepositoryApi api = new Oi40DbPurchaseOrderRepositoryApi();

    
    /**
     * deleteByCode
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteByCodeVoid20Test() {
        String code = null;
        api.deleteByCodeVoid20(code);

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
    public void deleteByCodesVoid20Test() {
        List<String> codes = null;
        api.deleteByCodesVoid20(codes);

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
    public void doAutocompletePageOI40DBPurchaseOrderTest() {
        AutoCompleteData autoCompleteData = null;
        PageOI40DBPurchaseOrder response = api.doAutocompletePageOI40DBPurchaseOrder(autoCompleteData);

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
    public void doLookupPageOI40DBPurchaseOrderTest() {
        LookupData lookup = null;
        PageOI40DBPurchaseOrder response = api.doLookupPageOI40DBPurchaseOrder(lookup);

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
    public void findAllListOI40DBPurchaseOrderTest() {
        List<OI40DBPurchaseOrder> response = api.findAllListOI40DBPurchaseOrder();

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
    public void findAllPageOI40DBPurchaseOrderTest() {
        PageInfo p = null;
        PageOI40DBPurchaseOrder response = api.findAllPageOI40DBPurchaseOrder(p);

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
    public void findByAfterIntegrationTsListOI40DBPurchaseOrderTest() {
        String ts = null;
        List<OI40DBPurchaseOrder> response = api.findByAfterIntegrationTsListOI40DBPurchaseOrder(ts);

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
    public void findByAfterModifiedTimestampListOI40DBPurchaseOrderTest() {
        String ts = null;
        List<OI40DBPurchaseOrder> response = api.findByAfterModifiedTimestampListOI40DBPurchaseOrder(ts);

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
    public void findByCodeOI40DBPurchaseOrderTest() {
        String code = null;
        OI40DBPurchaseOrder response = api.findByCodeOI40DBPurchaseOrder(code);

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
    public void findByCodesListOI40DBPurchaseOrderTest() {
        List<String> codes = null;
        List<OI40DBPurchaseOrder> response = api.findByCodesListOI40DBPurchaseOrder(codes);

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
    public void findByQbeListOI40DBPurchaseOrderTest() {
        OI40DBPurchaseOrder qbe = null;
        List<OI40DBPurchaseOrder> response = api.findByQbeListOI40DBPurchaseOrder(qbe);

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
    public void findByQbePagedPageOI40DBPurchaseOrderTest() {
        QbeSupportOI40DBPurchaseOrder qbe = null;
        PageOI40DBPurchaseOrder response = api.findByQbePagedPageOI40DBPurchaseOrder(qbe);

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
    public void updateListOI40DBPurchaseOrderTest() {
        List<OI40DBPurchaseOrder> data = null;
        List<OI40DBPurchaseOrder> response = api.updateListOI40DBPurchaseOrder(data);

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
    public void updateSingleOI40DBPurchaseOrderTest() {
        OI40DBPurchaseOrder data = null;
        OI40DBPurchaseOrder response = api.updateSingleOI40DBPurchaseOrder(data);

        // TODO: test validations
    }
    
}
