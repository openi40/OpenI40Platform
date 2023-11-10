# Oi40DbResourceGroupRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid21**](Oi40DbResourceGroupRepositoryApi.md#deleteByCodeVoid21) | **GET** /integration/OI40DBResourceGroup/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid21**](Oi40DbResourceGroupRepositoryApi.md#deleteByCodesVoid21) | **POST** /integration/OI40DBResourceGroup/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBResourceGroup**](Oi40DbResourceGroupRepositoryApi.md#doAutocompletePageOI40DBResourceGroup) | **POST** /integration/OI40DBResourceGroup/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBResourceGroup**](Oi40DbResourceGroupRepositoryApi.md#doLookupPageOI40DBResourceGroup) | **POST** /integration/OI40DBResourceGroup/doLookup | doLookup
[**findAllListOI40DBResourceGroup**](Oi40DbResourceGroupRepositoryApi.md#findAllListOI40DBResourceGroup) | **GET** /integration/OI40DBResourceGroup | findAll
[**findAllPageOI40DBResourceGroup**](Oi40DbResourceGroupRepositoryApi.md#findAllPageOI40DBResourceGroup) | **POST** /integration/OI40DBResourceGroup/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBResourceGroup**](Oi40DbResourceGroupRepositoryApi.md#findByAfterIntegrationTsListOI40DBResourceGroup) | **GET** /integration/OI40DBResourceGroup/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBResourceGroup**](Oi40DbResourceGroupRepositoryApi.md#findByAfterModifiedTimestampListOI40DBResourceGroup) | **GET** /integration/OI40DBResourceGroup/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBResourceGroup**](Oi40DbResourceGroupRepositoryApi.md#findByCodeOI40DBResourceGroup) | **GET** /integration/OI40DBResourceGroup/byCode/{code} | findByCode
[**findByCodesListOI40DBResourceGroup**](Oi40DbResourceGroupRepositoryApi.md#findByCodesListOI40DBResourceGroup) | **POST** /integration/OI40DBResourceGroup/findByCodes | findByCodes
[**findByQbeListOI40DBResourceGroup**](Oi40DbResourceGroupRepositoryApi.md#findByQbeListOI40DBResourceGroup) | **POST** /integration/OI40DBResourceGroup/findByQbe | findByQbe
[**findByQbePagedPageOI40DBResourceGroup**](Oi40DbResourceGroupRepositoryApi.md#findByQbePagedPageOI40DBResourceGroup) | **POST** /integration/OI40DBResourceGroup/findByQbePaged | findByQbePaged
[**updateListOI40DBResourceGroup**](Oi40DbResourceGroupRepositoryApi.md#updateListOI40DBResourceGroup) | **POST** /integration/OI40DBResourceGroup/update | update
[**updateSingleOI40DBResourceGroup**](Oi40DbResourceGroupRepositoryApi.md#updateSingleOI40DBResourceGroup) | **POST** /integration/OI40DBResourceGroup/updateSingle | updateSingle


<a name="deleteByCodeVoid21"></a>
# **deleteByCodeVoid21**
> deleteByCodeVoid21(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid21(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#deleteByCodeVoid21");
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

<a name="deleteByCodesVoid21"></a>
# **deleteByCodesVoid21**
> deleteByCodesVoid21(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid21(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#deleteByCodesVoid21");
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

<a name="doAutocompletePageOI40DBResourceGroup"></a>
# **doAutocompletePageOI40DBResourceGroup**
> PageOI40DBResourceGroup doAutocompletePageOI40DBResourceGroup(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBResourceGroup result = apiInstance.doAutocompletePageOI40DBResourceGroup(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#doAutocompletePageOI40DBResourceGroup");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBResourceGroup**](PageOI40DBResourceGroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBResourceGroup"></a>
# **doLookupPageOI40DBResourceGroup**
> PageOI40DBResourceGroup doLookupPageOI40DBResourceGroup(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBResourceGroup result = apiInstance.doLookupPageOI40DBResourceGroup(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#doLookupPageOI40DBResourceGroup");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBResourceGroup**](PageOI40DBResourceGroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBResourceGroup"></a>
# **findAllListOI40DBResourceGroup**
> List&lt;OI40DBResourceGroup&gt; findAllListOI40DBResourceGroup()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
try {
    List<OI40DBResourceGroup> result = apiInstance.findAllListOI40DBResourceGroup();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#findAllListOI40DBResourceGroup");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBResourceGroup&gt;**](OI40DBResourceGroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBResourceGroup"></a>
# **findAllPageOI40DBResourceGroup**
> PageOI40DBResourceGroup findAllPageOI40DBResourceGroup(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBResourceGroup result = apiInstance.findAllPageOI40DBResourceGroup(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#findAllPageOI40DBResourceGroup");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBResourceGroup**](PageOI40DBResourceGroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBResourceGroup"></a>
# **findByAfterIntegrationTsListOI40DBResourceGroup**
> List&lt;OI40DBResourceGroup&gt; findByAfterIntegrationTsListOI40DBResourceGroup(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBResourceGroup> result = apiInstance.findByAfterIntegrationTsListOI40DBResourceGroup(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#findByAfterIntegrationTsListOI40DBResourceGroup");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBResourceGroup&gt;**](OI40DBResourceGroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBResourceGroup"></a>
# **findByAfterModifiedTimestampListOI40DBResourceGroup**
> List&lt;OI40DBResourceGroup&gt; findByAfterModifiedTimestampListOI40DBResourceGroup(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBResourceGroup> result = apiInstance.findByAfterModifiedTimestampListOI40DBResourceGroup(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#findByAfterModifiedTimestampListOI40DBResourceGroup");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBResourceGroup&gt;**](OI40DBResourceGroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBResourceGroup"></a>
# **findByCodeOI40DBResourceGroup**
> OI40DBResourceGroup findByCodeOI40DBResourceGroup(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBResourceGroup result = apiInstance.findByCodeOI40DBResourceGroup(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#findByCodeOI40DBResourceGroup");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBResourceGroup**](OI40DBResourceGroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBResourceGroup"></a>
# **findByCodesListOI40DBResourceGroup**
> List&lt;OI40DBResourceGroup&gt; findByCodesListOI40DBResourceGroup(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBResourceGroup> result = apiInstance.findByCodesListOI40DBResourceGroup(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#findByCodesListOI40DBResourceGroup");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBResourceGroup&gt;**](OI40DBResourceGroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBResourceGroup"></a>
# **findByQbeListOI40DBResourceGroup**
> List&lt;OI40DBResourceGroup&gt; findByQbeListOI40DBResourceGroup(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
OI40DBResourceGroup qbe = new OI40DBResourceGroup(); // OI40DBResourceGroup | qbe
try {
    List<OI40DBResourceGroup> result = apiInstance.findByQbeListOI40DBResourceGroup(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#findByQbeListOI40DBResourceGroup");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBResourceGroup**](OI40DBResourceGroup.md)| qbe |

### Return type

[**List&lt;OI40DBResourceGroup&gt;**](OI40DBResourceGroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBResourceGroup"></a>
# **findByQbePagedPageOI40DBResourceGroup**
> PageOI40DBResourceGroup findByQbePagedPageOI40DBResourceGroup(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
QbeSupportOI40DBResourceGroup qbe = new QbeSupportOI40DBResourceGroup(); // QbeSupportOI40DBResourceGroup | qbe
try {
    PageOI40DBResourceGroup result = apiInstance.findByQbePagedPageOI40DBResourceGroup(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#findByQbePagedPageOI40DBResourceGroup");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBResourceGroup**](QbeSupportOI40DBResourceGroup.md)| qbe |

### Return type

[**PageOI40DBResourceGroup**](PageOI40DBResourceGroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBResourceGroup"></a>
# **updateListOI40DBResourceGroup**
> List&lt;OI40DBResourceGroup&gt; updateListOI40DBResourceGroup(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
List<OI40DBResourceGroup> data = Arrays.asList(new OI40DBResourceGroup()); // List<OI40DBResourceGroup> | data
try {
    List<OI40DBResourceGroup> result = apiInstance.updateListOI40DBResourceGroup(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#updateListOI40DBResourceGroup");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBResourceGroup&gt;**](OI40DBResourceGroup.md)| data |

### Return type

[**List&lt;OI40DBResourceGroup&gt;**](OI40DBResourceGroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBResourceGroup"></a>
# **updateSingleOI40DBResourceGroup**
> OI40DBResourceGroup updateSingleOI40DBResourceGroup(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi;


Oi40DbResourceGroupRepositoryApi apiInstance = new Oi40DbResourceGroupRepositoryApi();
OI40DBResourceGroup data = new OI40DBResourceGroup(); // OI40DBResourceGroup | data
try {
    OI40DBResourceGroup result = apiInstance.updateSingleOI40DBResourceGroup(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbResourceGroupRepositoryApi#updateSingleOI40DBResourceGroup");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBResourceGroup**](OI40DBResourceGroup.md)| data |

### Return type

[**OI40DBResourceGroup**](OI40DBResourceGroup.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

