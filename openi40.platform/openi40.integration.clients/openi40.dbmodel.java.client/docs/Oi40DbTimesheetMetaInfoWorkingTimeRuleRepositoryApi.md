# Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid36**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#deleteByCodeVoid36) | **GET** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid36**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#deleteByCodesVoid36) | **POST** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBTimesheetMetaInfoWorkingTimeRule**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#doAutocompletePageOI40DBTimesheetMetaInfoWorkingTimeRule) | **POST** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBTimesheetMetaInfoWorkingTimeRule**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#doLookupPageOI40DBTimesheetMetaInfoWorkingTimeRule) | **POST** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule/doLookup | doLookup
[**findAllListOI40DBTimesheetMetaInfoWorkingTimeRule**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#findAllListOI40DBTimesheetMetaInfoWorkingTimeRule) | **GET** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule | findAll
[**findAllPageOI40DBTimesheetMetaInfoWorkingTimeRule**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#findAllPageOI40DBTimesheetMetaInfoWorkingTimeRule) | **POST** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBTimesheetMetaInfoWorkingTimeRule**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#findByAfterIntegrationTsListOI40DBTimesheetMetaInfoWorkingTimeRule) | **GET** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoWorkingTimeRule**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoWorkingTimeRule) | **GET** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBTimesheetMetaInfoWorkingTimeRule**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#findByCodeOI40DBTimesheetMetaInfoWorkingTimeRule) | **GET** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule/byCode/{code} | findByCode
[**findByCodesListOI40DBTimesheetMetaInfoWorkingTimeRule**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#findByCodesListOI40DBTimesheetMetaInfoWorkingTimeRule) | **POST** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule/findByCodes | findByCodes
[**findByQbeListOI40DBTimesheetMetaInfoWorkingTimeRule**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#findByQbeListOI40DBTimesheetMetaInfoWorkingTimeRule) | **POST** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule/findByQbe | findByQbe
[**findByQbePagedPageOI40DBTimesheetMetaInfoWorkingTimeRule**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#findByQbePagedPageOI40DBTimesheetMetaInfoWorkingTimeRule) | **POST** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule/findByQbePaged | findByQbePaged
[**updateListOI40DBTimesheetMetaInfoWorkingTimeRule**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#updateListOI40DBTimesheetMetaInfoWorkingTimeRule) | **POST** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule/update | update
[**updateSingleOI40DBTimesheetMetaInfoWorkingTimeRule**](Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi.md#updateSingleOI40DBTimesheetMetaInfoWorkingTimeRule) | **POST** /integration/OI40DBTimesheetMetaInfoWorkingTimeRule/updateSingle | updateSingle


<a name="deleteByCodeVoid36"></a>
# **deleteByCodeVoid36**
> deleteByCodeVoid36(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid36(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#deleteByCodeVoid36");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="deleteByCodesVoid36"></a>
# **deleteByCodesVoid36**
> deleteByCodesVoid36(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid36(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#deleteByCodesVoid36");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doAutocompletePageOI40DBTimesheetMetaInfoWorkingTimeRule"></a>
# **doAutocompletePageOI40DBTimesheetMetaInfoWorkingTimeRule**
> PageOI40DBTimesheetMetaInfoWorkingTimeRule doAutocompletePageOI40DBTimesheetMetaInfoWorkingTimeRule(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBTimesheetMetaInfoWorkingTimeRule result = apiInstance.doAutocompletePageOI40DBTimesheetMetaInfoWorkingTimeRule(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#doAutocompletePageOI40DBTimesheetMetaInfoWorkingTimeRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBTimesheetMetaInfoWorkingTimeRule**](PageOI40DBTimesheetMetaInfoWorkingTimeRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBTimesheetMetaInfoWorkingTimeRule"></a>
# **doLookupPageOI40DBTimesheetMetaInfoWorkingTimeRule**
> PageOI40DBTimesheetMetaInfoWorkingTimeRule doLookupPageOI40DBTimesheetMetaInfoWorkingTimeRule(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBTimesheetMetaInfoWorkingTimeRule result = apiInstance.doLookupPageOI40DBTimesheetMetaInfoWorkingTimeRule(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#doLookupPageOI40DBTimesheetMetaInfoWorkingTimeRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBTimesheetMetaInfoWorkingTimeRule**](PageOI40DBTimesheetMetaInfoWorkingTimeRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBTimesheetMetaInfoWorkingTimeRule"></a>
# **findAllListOI40DBTimesheetMetaInfoWorkingTimeRule**
> List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt; findAllListOI40DBTimesheetMetaInfoWorkingTimeRule()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
try {
    List<OI40DBTimesheetMetaInfoWorkingTimeRule> result = apiInstance.findAllListOI40DBTimesheetMetaInfoWorkingTimeRule();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#findAllListOI40DBTimesheetMetaInfoWorkingTimeRule");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;**](OI40DBTimesheetMetaInfoWorkingTimeRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBTimesheetMetaInfoWorkingTimeRule"></a>
# **findAllPageOI40DBTimesheetMetaInfoWorkingTimeRule**
> PageOI40DBTimesheetMetaInfoWorkingTimeRule findAllPageOI40DBTimesheetMetaInfoWorkingTimeRule(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBTimesheetMetaInfoWorkingTimeRule result = apiInstance.findAllPageOI40DBTimesheetMetaInfoWorkingTimeRule(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#findAllPageOI40DBTimesheetMetaInfoWorkingTimeRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBTimesheetMetaInfoWorkingTimeRule**](PageOI40DBTimesheetMetaInfoWorkingTimeRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBTimesheetMetaInfoWorkingTimeRule"></a>
# **findByAfterIntegrationTsListOI40DBTimesheetMetaInfoWorkingTimeRule**
> List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt; findByAfterIntegrationTsListOI40DBTimesheetMetaInfoWorkingTimeRule(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTimesheetMetaInfoWorkingTimeRule> result = apiInstance.findByAfterIntegrationTsListOI40DBTimesheetMetaInfoWorkingTimeRule(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#findByAfterIntegrationTsListOI40DBTimesheetMetaInfoWorkingTimeRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;**](OI40DBTimesheetMetaInfoWorkingTimeRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoWorkingTimeRule"></a>
# **findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoWorkingTimeRule**
> List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt; findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoWorkingTimeRule(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTimesheetMetaInfoWorkingTimeRule> result = apiInstance.findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoWorkingTimeRule(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoWorkingTimeRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;**](OI40DBTimesheetMetaInfoWorkingTimeRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBTimesheetMetaInfoWorkingTimeRule"></a>
# **findByCodeOI40DBTimesheetMetaInfoWorkingTimeRule**
> OI40DBTimesheetMetaInfoWorkingTimeRule findByCodeOI40DBTimesheetMetaInfoWorkingTimeRule(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBTimesheetMetaInfoWorkingTimeRule result = apiInstance.findByCodeOI40DBTimesheetMetaInfoWorkingTimeRule(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#findByCodeOI40DBTimesheetMetaInfoWorkingTimeRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBTimesheetMetaInfoWorkingTimeRule**](OI40DBTimesheetMetaInfoWorkingTimeRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBTimesheetMetaInfoWorkingTimeRule"></a>
# **findByCodesListOI40DBTimesheetMetaInfoWorkingTimeRule**
> List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt; findByCodesListOI40DBTimesheetMetaInfoWorkingTimeRule(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBTimesheetMetaInfoWorkingTimeRule> result = apiInstance.findByCodesListOI40DBTimesheetMetaInfoWorkingTimeRule(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#findByCodesListOI40DBTimesheetMetaInfoWorkingTimeRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;**](OI40DBTimesheetMetaInfoWorkingTimeRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBTimesheetMetaInfoWorkingTimeRule"></a>
# **findByQbeListOI40DBTimesheetMetaInfoWorkingTimeRule**
> List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt; findByQbeListOI40DBTimesheetMetaInfoWorkingTimeRule(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
OI40DBTimesheetMetaInfoWorkingTimeRule qbe = new OI40DBTimesheetMetaInfoWorkingTimeRule(); // OI40DBTimesheetMetaInfoWorkingTimeRule | qbe
try {
    List<OI40DBTimesheetMetaInfoWorkingTimeRule> result = apiInstance.findByQbeListOI40DBTimesheetMetaInfoWorkingTimeRule(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#findByQbeListOI40DBTimesheetMetaInfoWorkingTimeRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBTimesheetMetaInfoWorkingTimeRule**](OI40DBTimesheetMetaInfoWorkingTimeRule.md)| qbe |

### Return type

[**List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;**](OI40DBTimesheetMetaInfoWorkingTimeRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBTimesheetMetaInfoWorkingTimeRule"></a>
# **findByQbePagedPageOI40DBTimesheetMetaInfoWorkingTimeRule**
> PageOI40DBTimesheetMetaInfoWorkingTimeRule findByQbePagedPageOI40DBTimesheetMetaInfoWorkingTimeRule(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
QbeSupportOI40DBTimesheetMetaInfoWorkingTimeRule qbe = new QbeSupportOI40DBTimesheetMetaInfoWorkingTimeRule(); // QbeSupportOI40DBTimesheetMetaInfoWorkingTimeRule | qbe
try {
    PageOI40DBTimesheetMetaInfoWorkingTimeRule result = apiInstance.findByQbePagedPageOI40DBTimesheetMetaInfoWorkingTimeRule(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#findByQbePagedPageOI40DBTimesheetMetaInfoWorkingTimeRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBTimesheetMetaInfoWorkingTimeRule**](QbeSupportOI40DBTimesheetMetaInfoWorkingTimeRule.md)| qbe |

### Return type

[**PageOI40DBTimesheetMetaInfoWorkingTimeRule**](PageOI40DBTimesheetMetaInfoWorkingTimeRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBTimesheetMetaInfoWorkingTimeRule"></a>
# **updateListOI40DBTimesheetMetaInfoWorkingTimeRule**
> List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt; updateListOI40DBTimesheetMetaInfoWorkingTimeRule(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
List<OI40DBTimesheetMetaInfoWorkingTimeRule> data = Arrays.asList(new OI40DBTimesheetMetaInfoWorkingTimeRule()); // List<OI40DBTimesheetMetaInfoWorkingTimeRule> | data
try {
    List<OI40DBTimesheetMetaInfoWorkingTimeRule> result = apiInstance.updateListOI40DBTimesheetMetaInfoWorkingTimeRule(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#updateListOI40DBTimesheetMetaInfoWorkingTimeRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;**](OI40DBTimesheetMetaInfoWorkingTimeRule.md)| data |

### Return type

[**List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;**](OI40DBTimesheetMetaInfoWorkingTimeRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBTimesheetMetaInfoWorkingTimeRule"></a>
# **updateSingleOI40DBTimesheetMetaInfoWorkingTimeRule**
> OI40DBTimesheetMetaInfoWorkingTimeRule updateSingleOI40DBTimesheetMetaInfoWorkingTimeRule(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi;


Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi();
OI40DBTimesheetMetaInfoWorkingTimeRule data = new OI40DBTimesheetMetaInfoWorkingTimeRule(); // OI40DBTimesheetMetaInfoWorkingTimeRule | data
try {
    OI40DBTimesheetMetaInfoWorkingTimeRule result = apiInstance.updateSingleOI40DBTimesheetMetaInfoWorkingTimeRule(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi#updateSingleOI40DBTimesheetMetaInfoWorkingTimeRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBTimesheetMetaInfoWorkingTimeRule**](OI40DBTimesheetMetaInfoWorkingTimeRule.md)| data |

### Return type

[**OI40DBTimesheetMetaInfoWorkingTimeRule**](OI40DBTimesheetMetaInfoWorkingTimeRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

