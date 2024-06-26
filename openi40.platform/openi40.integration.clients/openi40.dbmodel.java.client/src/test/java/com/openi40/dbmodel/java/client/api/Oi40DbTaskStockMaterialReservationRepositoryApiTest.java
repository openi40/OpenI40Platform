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
import com.openi40.dbmodel.java.client.model.OI40DBTaskStockMaterialReservation;
import com.openi40.dbmodel.java.client.model.PageInfo;
import com.openi40.dbmodel.java.client.model.PageOI40DBTaskStockMaterialReservation;
import com.openi40.dbmodel.java.client.model.QbeSupportOI40DBTaskStockMaterialReservation;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for Oi40DbTaskStockMaterialReservationRepositoryApi
 */
@Ignore
public class Oi40DbTaskStockMaterialReservationRepositoryApiTest {

    private final Oi40DbTaskStockMaterialReservationRepositoryApi api = new Oi40DbTaskStockMaterialReservationRepositoryApi();

    
    /**
     * deleteByCode
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteByCodeVoid33Test() {
        String code = null;
        api.deleteByCodeVoid33(code);

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
    public void deleteByCodesVoid33Test() {
        List<String> codes = null;
        api.deleteByCodesVoid33(codes);

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
    public void doAutocompletePageOI40DBTaskStockMaterialReservationTest() {
        AutoCompleteData autoCompleteData = null;
        PageOI40DBTaskStockMaterialReservation response = api.doAutocompletePageOI40DBTaskStockMaterialReservation(autoCompleteData);

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
    public void doLookupPageOI40DBTaskStockMaterialReservationTest() {
        LookupData lookup = null;
        PageOI40DBTaskStockMaterialReservation response = api.doLookupPageOI40DBTaskStockMaterialReservation(lookup);

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
    public void findAllListOI40DBTaskStockMaterialReservationTest() {
        List<OI40DBTaskStockMaterialReservation> response = api.findAllListOI40DBTaskStockMaterialReservation();

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
    public void findAllPageOI40DBTaskStockMaterialReservationTest() {
        PageInfo p = null;
        PageOI40DBTaskStockMaterialReservation response = api.findAllPageOI40DBTaskStockMaterialReservation(p);

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
    public void findByAfterIntegrationTsListOI40DBTaskStockMaterialReservationTest() {
        String ts = null;
        List<OI40DBTaskStockMaterialReservation> response = api.findByAfterIntegrationTsListOI40DBTaskStockMaterialReservation(ts);

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
    public void findByAfterModifiedTimestampListOI40DBTaskStockMaterialReservationTest() {
        String ts = null;
        List<OI40DBTaskStockMaterialReservation> response = api.findByAfterModifiedTimestampListOI40DBTaskStockMaterialReservation(ts);

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
    public void findByCodeOI40DBTaskStockMaterialReservationTest() {
        String code = null;
        OI40DBTaskStockMaterialReservation response = api.findByCodeOI40DBTaskStockMaterialReservation(code);

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
    public void findByCodesListOI40DBTaskStockMaterialReservationTest() {
        List<String> codes = null;
        List<OI40DBTaskStockMaterialReservation> response = api.findByCodesListOI40DBTaskStockMaterialReservation(codes);

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
    public void findByQbeListOI40DBTaskStockMaterialReservationTest() {
        OI40DBTaskStockMaterialReservation qbe = null;
        List<OI40DBTaskStockMaterialReservation> response = api.findByQbeListOI40DBTaskStockMaterialReservation(qbe);

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
    public void findByQbePagedPageOI40DBTaskStockMaterialReservationTest() {
        QbeSupportOI40DBTaskStockMaterialReservation qbe = null;
        PageOI40DBTaskStockMaterialReservation response = api.findByQbePagedPageOI40DBTaskStockMaterialReservation(qbe);

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
    public void updateListOI40DBTaskStockMaterialReservationTest() {
        List<OI40DBTaskStockMaterialReservation> data = null;
        List<OI40DBTaskStockMaterialReservation> response = api.updateListOI40DBTaskStockMaterialReservation(data);

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
    public void updateSingleOI40DBTaskStockMaterialReservationTest() {
        OI40DBTaskStockMaterialReservation data = null;
        OI40DBTaskStockMaterialReservation response = api.updateSingleOI40DBTaskStockMaterialReservation(data);

        // TODO: test validations
    }
    
}
