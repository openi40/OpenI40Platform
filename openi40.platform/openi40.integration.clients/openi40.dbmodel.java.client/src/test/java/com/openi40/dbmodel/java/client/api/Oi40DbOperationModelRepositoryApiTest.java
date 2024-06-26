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
import com.openi40.dbmodel.java.client.model.OI40DBOperationModel;
import com.openi40.dbmodel.java.client.model.PageInfo;
import com.openi40.dbmodel.java.client.model.PageOI40DBOperationModel;
import com.openi40.dbmodel.java.client.model.QbeSupportOI40DBOperationModel;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for Oi40DbOperationModelRepositoryApi
 */
@Ignore
public class Oi40DbOperationModelRepositoryApiTest {

    private final Oi40DbOperationModelRepositoryApi api = new Oi40DbOperationModelRepositoryApi();

    
    /**
     * deleteByCode
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteByCodeVoid11Test() {
        String code = null;
        api.deleteByCodeVoid11(code);

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
    public void deleteByCodesVoid11Test() {
        List<String> codes = null;
        api.deleteByCodesVoid11(codes);

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
    public void doAutocompletePageOI40DBOperationModelTest() {
        AutoCompleteData autoCompleteData = null;
        PageOI40DBOperationModel response = api.doAutocompletePageOI40DBOperationModel(autoCompleteData);

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
    public void doLookupPageOI40DBOperationModelTest() {
        LookupData lookup = null;
        PageOI40DBOperationModel response = api.doLookupPageOI40DBOperationModel(lookup);

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
    public void findAllListOI40DBOperationModelTest() {
        List<OI40DBOperationModel> response = api.findAllListOI40DBOperationModel();

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
    public void findAllPageOI40DBOperationModelTest() {
        PageInfo p = null;
        PageOI40DBOperationModel response = api.findAllPageOI40DBOperationModel(p);

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
    public void findByAfterIntegrationTsListOI40DBOperationModelTest() {
        String ts = null;
        List<OI40DBOperationModel> response = api.findByAfterIntegrationTsListOI40DBOperationModel(ts);

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
    public void findByAfterModifiedTimestampListOI40DBOperationModelTest() {
        String ts = null;
        List<OI40DBOperationModel> response = api.findByAfterModifiedTimestampListOI40DBOperationModel(ts);

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
    public void findByCodeOI40DBOperationModelTest() {
        String code = null;
        OI40DBOperationModel response = api.findByCodeOI40DBOperationModel(code);

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
    public void findByCodesListOI40DBOperationModelTest() {
        List<String> codes = null;
        List<OI40DBOperationModel> response = api.findByCodesListOI40DBOperationModel(codes);

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
    public void findByQbeListOI40DBOperationModelTest() {
        OI40DBOperationModel qbe = null;
        List<OI40DBOperationModel> response = api.findByQbeListOI40DBOperationModel(qbe);

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
    public void findByQbePagedPageOI40DBOperationModelTest() {
        QbeSupportOI40DBOperationModel qbe = null;
        PageOI40DBOperationModel response = api.findByQbePagedPageOI40DBOperationModel(qbe);

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
    public void updateListOI40DBOperationModelTest() {
        List<OI40DBOperationModel> data = null;
        List<OI40DBOperationModel> response = api.updateListOI40DBOperationModel(data);

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
    public void updateSingleOI40DBOperationModelTest() {
        OI40DBOperationModel data = null;
        OI40DBOperationModel response = api.updateSingleOI40DBOperationModel(data);

        // TODO: test validations
    }
    
}
