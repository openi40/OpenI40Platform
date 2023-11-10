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
import com.openi40.dbmodel.java.client.model.OI40DBCycleModel;
import com.openi40.dbmodel.java.client.model.PageInfo;
import com.openi40.dbmodel.java.client.model.PageOI40DBCycleModel;
import com.openi40.dbmodel.java.client.model.QbeSupportOI40DBCycleModel;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for Oi40DbCycleModelRepositoryApi
 */
@Ignore
public class Oi40DbCycleModelRepositoryApiTest {

    private final Oi40DbCycleModelRepositoryApi api = new Oi40DbCycleModelRepositoryApi();

    
    /**
     * deleteByCode
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteByCodeVoid5Test() {
        String code = null;
        api.deleteByCodeVoid5(code);

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
    public void deleteByCodesVoid5Test() {
        List<String> codes = null;
        api.deleteByCodesVoid5(codes);

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
    public void doAutocompletePageOI40DBCycleModelTest() {
        AutoCompleteData autoCompleteData = null;
        PageOI40DBCycleModel response = api.doAutocompletePageOI40DBCycleModel(autoCompleteData);

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
    public void doLookupPageOI40DBCycleModelTest() {
        LookupData lookup = null;
        PageOI40DBCycleModel response = api.doLookupPageOI40DBCycleModel(lookup);

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
    public void findAllListOI40DBCycleModelTest() {
        List<OI40DBCycleModel> response = api.findAllListOI40DBCycleModel();

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
    public void findAllPageOI40DBCycleModelTest() {
        PageInfo p = null;
        PageOI40DBCycleModel response = api.findAllPageOI40DBCycleModel(p);

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
    public void findByAfterIntegrationTsListOI40DBCycleModelTest() {
        String ts = null;
        List<OI40DBCycleModel> response = api.findByAfterIntegrationTsListOI40DBCycleModel(ts);

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
    public void findByAfterModifiedTimestampListOI40DBCycleModelTest() {
        String ts = null;
        List<OI40DBCycleModel> response = api.findByAfterModifiedTimestampListOI40DBCycleModel(ts);

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
    public void findByCodeOI40DBCycleModelTest() {
        String code = null;
        OI40DBCycleModel response = api.findByCodeOI40DBCycleModel(code);

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
    public void findByCodesListOI40DBCycleModelTest() {
        List<String> codes = null;
        List<OI40DBCycleModel> response = api.findByCodesListOI40DBCycleModel(codes);

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
    public void findByQbeListOI40DBCycleModelTest() {
        OI40DBCycleModel qbe = null;
        List<OI40DBCycleModel> response = api.findByQbeListOI40DBCycleModel(qbe);

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
    public void findByQbePagedPageOI40DBCycleModelTest() {
        QbeSupportOI40DBCycleModel qbe = null;
        PageOI40DBCycleModel response = api.findByQbePagedPageOI40DBCycleModel(qbe);

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
    public void updateListOI40DBCycleModelTest() {
        List<OI40DBCycleModel> data = null;
        List<OI40DBCycleModel> response = api.updateListOI40DBCycleModel(data);

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
    public void updateSingleOI40DBCycleModelTest() {
        OI40DBCycleModel data = null;
        OI40DBCycleModel response = api.updateSingleOI40DBCycleModel(data);

        // TODO: test validations
    }
    
}
