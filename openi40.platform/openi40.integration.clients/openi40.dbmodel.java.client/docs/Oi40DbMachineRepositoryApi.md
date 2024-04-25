# Oi40DbMachineRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid9**](Oi40DbMachineRepositoryApi.md#deleteByCodeVoid9) | **GET** /integration/OI40DBMachine/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid9**](Oi40DbMachineRepositoryApi.md#deleteByCodesVoid9) | **POST** /integration/OI40DBMachine/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBMachine**](Oi40DbMachineRepositoryApi.md#doAutocompletePageOI40DBMachine) | **POST** /integration/OI40DBMachine/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBMachine**](Oi40DbMachineRepositoryApi.md#doLookupPageOI40DBMachine) | **POST** /integration/OI40DBMachine/doLookup | doLookup
[**findAllListOI40DBMachine**](Oi40DbMachineRepositoryApi.md#findAllListOI40DBMachine) | **GET** /integration/OI40DBMachine | findAll
[**findAllPageOI40DBMachine**](Oi40DbMachineRepositoryApi.md#findAllPageOI40DBMachine) | **POST** /integration/OI40DBMachine/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBMachine**](Oi40DbMachineRepositoryApi.md#findByAfterIntegrationTsListOI40DBMachine) | **GET** /integration/OI40DBMachine/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBMachine**](Oi40DbMachineRepositoryApi.md#findByAfterModifiedTimestampListOI40DBMachine) | **GET** /integration/OI40DBMachine/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBMachine**](Oi40DbMachineRepositoryApi.md#findByCodeOI40DBMachine) | **GET** /integration/OI40DBMachine/byCode/{code} | findByCode
[**findByCodesListOI40DBMachine**](Oi40DbMachineRepositoryApi.md#findByCodesListOI40DBMachine) | **POST** /integration/OI40DBMachine/findByCodes | findByCodes
[**findByQbeListOI40DBMachine**](Oi40DbMachineRepositoryApi.md#findByQbeListOI40DBMachine) | **POST** /integration/OI40DBMachine/findByQbe | findByQbe
[**findByQbePagedPageOI40DBMachine**](Oi40DbMachineRepositoryApi.md#findByQbePagedPageOI40DBMachine) | **POST** /integration/OI40DBMachine/findByQbePaged | findByQbePaged
[**updateListOI40DBMachine**](Oi40DbMachineRepositoryApi.md#updateListOI40DBMachine) | **POST** /integration/OI40DBMachine/update | update
[**updateSingleOI40DBMachine**](Oi40DbMachineRepositoryApi.md#updateSingleOI40DBMachine) | **POST** /integration/OI40DBMachine/updateSingle | updateSingle


<a name="deleteByCodeVoid9"></a>
# **deleteByCodeVoid9**
> deleteByCodeVoid9(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid9(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#deleteByCodeVoid9");
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

<a name="deleteByCodesVoid9"></a>
# **deleteByCodesVoid9**
> deleteByCodesVoid9(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid9(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#deleteByCodesVoid9");
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

<a name="doAutocompletePageOI40DBMachine"></a>
# **doAutocompletePageOI40DBMachine**
> PageOI40DBMachine doAutocompletePageOI40DBMachine(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBMachine result = apiInstance.doAutocompletePageOI40DBMachine(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#doAutocompletePageOI40DBMachine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBMachine**](PageOI40DBMachine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBMachine"></a>
# **doLookupPageOI40DBMachine**
> PageOI40DBMachine doLookupPageOI40DBMachine(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBMachine result = apiInstance.doLookupPageOI40DBMachine(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#doLookupPageOI40DBMachine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBMachine**](PageOI40DBMachine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBMachine"></a>
# **findAllListOI40DBMachine**
> List&lt;OI40DBMachine&gt; findAllListOI40DBMachine()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
try {
    List<OI40DBMachine> result = apiInstance.findAllListOI40DBMachine();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#findAllListOI40DBMachine");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBMachine&gt;**](OI40DBMachine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBMachine"></a>
# **findAllPageOI40DBMachine**
> PageOI40DBMachine findAllPageOI40DBMachine(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBMachine result = apiInstance.findAllPageOI40DBMachine(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#findAllPageOI40DBMachine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBMachine**](PageOI40DBMachine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBMachine"></a>
# **findByAfterIntegrationTsListOI40DBMachine**
> List&lt;OI40DBMachine&gt; findByAfterIntegrationTsListOI40DBMachine(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBMachine> result = apiInstance.findByAfterIntegrationTsListOI40DBMachine(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#findByAfterIntegrationTsListOI40DBMachine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBMachine&gt;**](OI40DBMachine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBMachine"></a>
# **findByAfterModifiedTimestampListOI40DBMachine**
> List&lt;OI40DBMachine&gt; findByAfterModifiedTimestampListOI40DBMachine(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBMachine> result = apiInstance.findByAfterModifiedTimestampListOI40DBMachine(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#findByAfterModifiedTimestampListOI40DBMachine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBMachine&gt;**](OI40DBMachine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBMachine"></a>
# **findByCodeOI40DBMachine**
> OI40DBMachine findByCodeOI40DBMachine(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBMachine result = apiInstance.findByCodeOI40DBMachine(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#findByCodeOI40DBMachine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBMachine**](OI40DBMachine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBMachine"></a>
# **findByCodesListOI40DBMachine**
> List&lt;OI40DBMachine&gt; findByCodesListOI40DBMachine(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBMachine> result = apiInstance.findByCodesListOI40DBMachine(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#findByCodesListOI40DBMachine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBMachine&gt;**](OI40DBMachine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBMachine"></a>
# **findByQbeListOI40DBMachine**
> List&lt;OI40DBMachine&gt; findByQbeListOI40DBMachine(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
OI40DBMachine qbe = new OI40DBMachine(); // OI40DBMachine | qbe
try {
    List<OI40DBMachine> result = apiInstance.findByQbeListOI40DBMachine(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#findByQbeListOI40DBMachine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBMachine**](OI40DBMachine.md)| qbe |

### Return type

[**List&lt;OI40DBMachine&gt;**](OI40DBMachine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBMachine"></a>
# **findByQbePagedPageOI40DBMachine**
> PageOI40DBMachine findByQbePagedPageOI40DBMachine(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
QbeSupportOI40DBMachine qbe = new QbeSupportOI40DBMachine(); // QbeSupportOI40DBMachine | qbe
try {
    PageOI40DBMachine result = apiInstance.findByQbePagedPageOI40DBMachine(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#findByQbePagedPageOI40DBMachine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBMachine**](QbeSupportOI40DBMachine.md)| qbe |

### Return type

[**PageOI40DBMachine**](PageOI40DBMachine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBMachine"></a>
# **updateListOI40DBMachine**
> List&lt;OI40DBMachine&gt; updateListOI40DBMachine(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
List<OI40DBMachine> data = Arrays.asList(new OI40DBMachine()); // List<OI40DBMachine> | data
try {
    List<OI40DBMachine> result = apiInstance.updateListOI40DBMachine(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#updateListOI40DBMachine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBMachine&gt;**](OI40DBMachine.md)| data |

### Return type

[**List&lt;OI40DBMachine&gt;**](OI40DBMachine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBMachine"></a>
# **updateSingleOI40DBMachine**
> OI40DBMachine updateSingleOI40DBMachine(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineRepositoryApi;


Oi40DbMachineRepositoryApi apiInstance = new Oi40DbMachineRepositoryApi();
OI40DBMachine data = new OI40DBMachine(); // OI40DBMachine | data
try {
    OI40DBMachine result = apiInstance.updateSingleOI40DBMachine(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineRepositoryApi#updateSingleOI40DBMachine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBMachine**](OI40DBMachine.md)| data |

### Return type

[**OI40DBMachine**](OI40DBMachine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

