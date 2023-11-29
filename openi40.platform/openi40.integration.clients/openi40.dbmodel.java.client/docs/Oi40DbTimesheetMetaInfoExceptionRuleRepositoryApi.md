# Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid34**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#deleteByCodeVoid34) | **GET** /integration/OI40DBTimesheetMetaInfoExceptionRule/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid34**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#deleteByCodesVoid34) | **POST** /integration/OI40DBTimesheetMetaInfoExceptionRule/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBTimesheetMetaInfoExceptionRule**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#doAutocompletePageOI40DBTimesheetMetaInfoExceptionRule) | **POST** /integration/OI40DBTimesheetMetaInfoExceptionRule/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBTimesheetMetaInfoExceptionRule**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#doLookupPageOI40DBTimesheetMetaInfoExceptionRule) | **POST** /integration/OI40DBTimesheetMetaInfoExceptionRule/doLookup | doLookup
[**findAllListOI40DBTimesheetMetaInfoExceptionRule**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#findAllListOI40DBTimesheetMetaInfoExceptionRule) | **GET** /integration/OI40DBTimesheetMetaInfoExceptionRule | findAll
[**findAllPageOI40DBTimesheetMetaInfoExceptionRule**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#findAllPageOI40DBTimesheetMetaInfoExceptionRule) | **POST** /integration/OI40DBTimesheetMetaInfoExceptionRule/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBTimesheetMetaInfoExceptionRule**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#findByAfterIntegrationTsListOI40DBTimesheetMetaInfoExceptionRule) | **GET** /integration/OI40DBTimesheetMetaInfoExceptionRule/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoExceptionRule**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoExceptionRule) | **GET** /integration/OI40DBTimesheetMetaInfoExceptionRule/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBTimesheetMetaInfoExceptionRule**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#findByCodeOI40DBTimesheetMetaInfoExceptionRule) | **GET** /integration/OI40DBTimesheetMetaInfoExceptionRule/byCode/{code} | findByCode
[**findByCodesListOI40DBTimesheetMetaInfoExceptionRule**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#findByCodesListOI40DBTimesheetMetaInfoExceptionRule) | **POST** /integration/OI40DBTimesheetMetaInfoExceptionRule/findByCodes | findByCodes
[**findByQbeListOI40DBTimesheetMetaInfoExceptionRule**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#findByQbeListOI40DBTimesheetMetaInfoExceptionRule) | **POST** /integration/OI40DBTimesheetMetaInfoExceptionRule/findByQbe | findByQbe
[**findByQbePagedPageOI40DBTimesheetMetaInfoExceptionRule**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#findByQbePagedPageOI40DBTimesheetMetaInfoExceptionRule) | **POST** /integration/OI40DBTimesheetMetaInfoExceptionRule/findByQbePaged | findByQbePaged
[**updateListOI40DBTimesheetMetaInfoExceptionRule**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#updateListOI40DBTimesheetMetaInfoExceptionRule) | **POST** /integration/OI40DBTimesheetMetaInfoExceptionRule/update | update
[**updateSingleOI40DBTimesheetMetaInfoExceptionRule**](Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi.md#updateSingleOI40DBTimesheetMetaInfoExceptionRule) | **POST** /integration/OI40DBTimesheetMetaInfoExceptionRule/updateSingle | updateSingle


<a name="deleteByCodeVoid34"></a>
# **deleteByCodeVoid34**
> deleteByCodeVoid34(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid34(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#deleteByCodeVoid34");
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

<a name="deleteByCodesVoid34"></a>
# **deleteByCodesVoid34**
> deleteByCodesVoid34(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid34(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#deleteByCodesVoid34");
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

<a name="doAutocompletePageOI40DBTimesheetMetaInfoExceptionRule"></a>
# **doAutocompletePageOI40DBTimesheetMetaInfoExceptionRule**
> PageOI40DBTimesheetMetaInfoExceptionRule doAutocompletePageOI40DBTimesheetMetaInfoExceptionRule(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBTimesheetMetaInfoExceptionRule result = apiInstance.doAutocompletePageOI40DBTimesheetMetaInfoExceptionRule(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#doAutocompletePageOI40DBTimesheetMetaInfoExceptionRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBTimesheetMetaInfoExceptionRule**](PageOI40DBTimesheetMetaInfoExceptionRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBTimesheetMetaInfoExceptionRule"></a>
# **doLookupPageOI40DBTimesheetMetaInfoExceptionRule**
> PageOI40DBTimesheetMetaInfoExceptionRule doLookupPageOI40DBTimesheetMetaInfoExceptionRule(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBTimesheetMetaInfoExceptionRule result = apiInstance.doLookupPageOI40DBTimesheetMetaInfoExceptionRule(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#doLookupPageOI40DBTimesheetMetaInfoExceptionRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBTimesheetMetaInfoExceptionRule**](PageOI40DBTimesheetMetaInfoExceptionRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBTimesheetMetaInfoExceptionRule"></a>
# **findAllListOI40DBTimesheetMetaInfoExceptionRule**
> List&lt;OI40DBTimesheetMetaInfoExceptionRule&gt; findAllListOI40DBTimesheetMetaInfoExceptionRule()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
try {
    List<OI40DBTimesheetMetaInfoExceptionRule> result = apiInstance.findAllListOI40DBTimesheetMetaInfoExceptionRule();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#findAllListOI40DBTimesheetMetaInfoExceptionRule");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBTimesheetMetaInfoExceptionRule&gt;**](OI40DBTimesheetMetaInfoExceptionRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBTimesheetMetaInfoExceptionRule"></a>
# **findAllPageOI40DBTimesheetMetaInfoExceptionRule**
> PageOI40DBTimesheetMetaInfoExceptionRule findAllPageOI40DBTimesheetMetaInfoExceptionRule(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBTimesheetMetaInfoExceptionRule result = apiInstance.findAllPageOI40DBTimesheetMetaInfoExceptionRule(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#findAllPageOI40DBTimesheetMetaInfoExceptionRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBTimesheetMetaInfoExceptionRule**](PageOI40DBTimesheetMetaInfoExceptionRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBTimesheetMetaInfoExceptionRule"></a>
# **findByAfterIntegrationTsListOI40DBTimesheetMetaInfoExceptionRule**
> List&lt;OI40DBTimesheetMetaInfoExceptionRule&gt; findByAfterIntegrationTsListOI40DBTimesheetMetaInfoExceptionRule(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTimesheetMetaInfoExceptionRule> result = apiInstance.findByAfterIntegrationTsListOI40DBTimesheetMetaInfoExceptionRule(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#findByAfterIntegrationTsListOI40DBTimesheetMetaInfoExceptionRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTimesheetMetaInfoExceptionRule&gt;**](OI40DBTimesheetMetaInfoExceptionRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoExceptionRule"></a>
# **findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoExceptionRule**
> List&lt;OI40DBTimesheetMetaInfoExceptionRule&gt; findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoExceptionRule(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTimesheetMetaInfoExceptionRule> result = apiInstance.findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoExceptionRule(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoExceptionRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTimesheetMetaInfoExceptionRule&gt;**](OI40DBTimesheetMetaInfoExceptionRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBTimesheetMetaInfoExceptionRule"></a>
# **findByCodeOI40DBTimesheetMetaInfoExceptionRule**
> OI40DBTimesheetMetaInfoExceptionRule findByCodeOI40DBTimesheetMetaInfoExceptionRule(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBTimesheetMetaInfoExceptionRule result = apiInstance.findByCodeOI40DBTimesheetMetaInfoExceptionRule(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#findByCodeOI40DBTimesheetMetaInfoExceptionRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBTimesheetMetaInfoExceptionRule**](OI40DBTimesheetMetaInfoExceptionRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBTimesheetMetaInfoExceptionRule"></a>
# **findByCodesListOI40DBTimesheetMetaInfoExceptionRule**
> List&lt;OI40DBTimesheetMetaInfoExceptionRule&gt; findByCodesListOI40DBTimesheetMetaInfoExceptionRule(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBTimesheetMetaInfoExceptionRule> result = apiInstance.findByCodesListOI40DBTimesheetMetaInfoExceptionRule(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#findByCodesListOI40DBTimesheetMetaInfoExceptionRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBTimesheetMetaInfoExceptionRule&gt;**](OI40DBTimesheetMetaInfoExceptionRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBTimesheetMetaInfoExceptionRule"></a>
# **findByQbeListOI40DBTimesheetMetaInfoExceptionRule**
> List&lt;OI40DBTimesheetMetaInfoExceptionRule&gt; findByQbeListOI40DBTimesheetMetaInfoExceptionRule(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
OI40DBTimesheetMetaInfoExceptionRule qbe = new OI40DBTimesheetMetaInfoExceptionRule(); // OI40DBTimesheetMetaInfoExceptionRule | qbe
try {
    List<OI40DBTimesheetMetaInfoExceptionRule> result = apiInstance.findByQbeListOI40DBTimesheetMetaInfoExceptionRule(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#findByQbeListOI40DBTimesheetMetaInfoExceptionRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBTimesheetMetaInfoExceptionRule**](OI40DBTimesheetMetaInfoExceptionRule.md)| qbe |

### Return type

[**List&lt;OI40DBTimesheetMetaInfoExceptionRule&gt;**](OI40DBTimesheetMetaInfoExceptionRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBTimesheetMetaInfoExceptionRule"></a>
# **findByQbePagedPageOI40DBTimesheetMetaInfoExceptionRule**
> PageOI40DBTimesheetMetaInfoExceptionRule findByQbePagedPageOI40DBTimesheetMetaInfoExceptionRule(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
QbeSupportOI40DBTimesheetMetaInfoExceptionRule qbe = new QbeSupportOI40DBTimesheetMetaInfoExceptionRule(); // QbeSupportOI40DBTimesheetMetaInfoExceptionRule | qbe
try {
    PageOI40DBTimesheetMetaInfoExceptionRule result = apiInstance.findByQbePagedPageOI40DBTimesheetMetaInfoExceptionRule(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#findByQbePagedPageOI40DBTimesheetMetaInfoExceptionRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBTimesheetMetaInfoExceptionRule**](QbeSupportOI40DBTimesheetMetaInfoExceptionRule.md)| qbe |

### Return type

[**PageOI40DBTimesheetMetaInfoExceptionRule**](PageOI40DBTimesheetMetaInfoExceptionRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBTimesheetMetaInfoExceptionRule"></a>
# **updateListOI40DBTimesheetMetaInfoExceptionRule**
> List&lt;OI40DBTimesheetMetaInfoExceptionRule&gt; updateListOI40DBTimesheetMetaInfoExceptionRule(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
List<OI40DBTimesheetMetaInfoExceptionRule> data = Arrays.asList(new OI40DBTimesheetMetaInfoExceptionRule()); // List<OI40DBTimesheetMetaInfoExceptionRule> | data
try {
    List<OI40DBTimesheetMetaInfoExceptionRule> result = apiInstance.updateListOI40DBTimesheetMetaInfoExceptionRule(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#updateListOI40DBTimesheetMetaInfoExceptionRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBTimesheetMetaInfoExceptionRule&gt;**](OI40DBTimesheetMetaInfoExceptionRule.md)| data |

### Return type

[**List&lt;OI40DBTimesheetMetaInfoExceptionRule&gt;**](OI40DBTimesheetMetaInfoExceptionRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBTimesheetMetaInfoExceptionRule"></a>
# **updateSingleOI40DBTimesheetMetaInfoExceptionRule**
> OI40DBTimesheetMetaInfoExceptionRule updateSingleOI40DBTimesheetMetaInfoExceptionRule(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi;


Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi();
OI40DBTimesheetMetaInfoExceptionRule data = new OI40DBTimesheetMetaInfoExceptionRule(); // OI40DBTimesheetMetaInfoExceptionRule | data
try {
    OI40DBTimesheetMetaInfoExceptionRule result = apiInstance.updateSingleOI40DBTimesheetMetaInfoExceptionRule(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoExceptionRuleRepositoryApi#updateSingleOI40DBTimesheetMetaInfoExceptionRule");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBTimesheetMetaInfoExceptionRule**](OI40DBTimesheetMetaInfoExceptionRule.md)| data |

### Return type

[**OI40DBTimesheetMetaInfoExceptionRule**](OI40DBTimesheetMetaInfoExceptionRule.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

