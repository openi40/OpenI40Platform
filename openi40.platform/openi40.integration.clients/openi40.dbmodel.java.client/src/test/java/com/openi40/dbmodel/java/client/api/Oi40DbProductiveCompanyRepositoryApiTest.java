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
import com.openi40.dbmodel.java.client.model.OI40DBProductiveCompany;
import com.openi40.dbmodel.java.client.model.PageInfo;
import com.openi40.dbmodel.java.client.model.PageOI40DBProductiveCompany;
import com.openi40.dbmodel.java.client.model.QbeSupportOI40DBProductiveCompany;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for Oi40DbProductiveCompanyRepositoryApi
 */
@Ignore
public class Oi40DbProductiveCompanyRepositoryApiTest {

    private final Oi40DbProductiveCompanyRepositoryApi api = new Oi40DbProductiveCompanyRepositoryApi();

    
    /**
     * deleteByCode
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteByCodeVoid18Test() {
        String code = null;
        api.deleteByCodeVoid18(code);

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
    public void deleteByCodesVoid18Test() {
        List<String> codes = null;
        api.deleteByCodesVoid18(codes);

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
    public void doAutocompletePageOI40DBProductiveCompanyTest() {
        AutoCompleteData autoCompleteData = null;
        PageOI40DBProductiveCompany response = api.doAutocompletePageOI40DBProductiveCompany(autoCompleteData);

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
    public void doLookupPageOI40DBProductiveCompanyTest() {
        LookupData lookup = null;
        PageOI40DBProductiveCompany response = api.doLookupPageOI40DBProductiveCompany(lookup);

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
    public void findAllListOI40DBProductiveCompanyTest() {
        List<OI40DBProductiveCompany> response = api.findAllListOI40DBProductiveCompany();

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
    public void findAllPageOI40DBProductiveCompanyTest() {
        PageInfo p = null;
        PageOI40DBProductiveCompany response = api.findAllPageOI40DBProductiveCompany(p);

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
    public void findByAfterIntegrationTsListOI40DBProductiveCompanyTest() {
        String ts = null;
        List<OI40DBProductiveCompany> response = api.findByAfterIntegrationTsListOI40DBProductiveCompany(ts);

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
    public void findByAfterModifiedTimestampListOI40DBProductiveCompanyTest() {
        String ts = null;
        List<OI40DBProductiveCompany> response = api.findByAfterModifiedTimestampListOI40DBProductiveCompany(ts);

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
    public void findByCodeOI40DBProductiveCompanyTest() {
        String code = null;
        OI40DBProductiveCompany response = api.findByCodeOI40DBProductiveCompany(code);

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
    public void findByCodesListOI40DBProductiveCompanyTest() {
        List<String> codes = null;
        List<OI40DBProductiveCompany> response = api.findByCodesListOI40DBProductiveCompany(codes);

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
    public void findByQbeListOI40DBProductiveCompanyTest() {
        OI40DBProductiveCompany qbe = null;
        List<OI40DBProductiveCompany> response = api.findByQbeListOI40DBProductiveCompany(qbe);

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
    public void findByQbePagedPageOI40DBProductiveCompanyTest() {
        QbeSupportOI40DBProductiveCompany qbe = null;
        PageOI40DBProductiveCompany response = api.findByQbePagedPageOI40DBProductiveCompany(qbe);

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
    public void updateListOI40DBProductiveCompanyTest() {
        List<OI40DBProductiveCompany> data = null;
        List<OI40DBProductiveCompany> response = api.updateListOI40DBProductiveCompany(data);

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
    public void updateSingleOI40DBProductiveCompanyTest() {
        OI40DBProductiveCompany data = null;
        OI40DBProductiveCompany response = api.updateSingleOI40DBProductiveCompany(data);

        // TODO: test validations
    }
    
}
