# AdApi

All URIs are relative to *http://localhost:8080/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**adCreate**](AdApi.md#adCreate) | **POST** /ad/create | Create ad
[**adDelete**](AdApi.md#adDelete) | **POST** /ad/delete | Delete ad
[**adOffers**](AdApi.md#adOffers) | **POST** /ad/offers | Search offers
[**adRead**](AdApi.md#adRead) | **POST** /ad/read | Read ad
[**adSearch**](AdApi.md#adSearch) | **POST** /ad/search | Search ad
[**adUpdate**](AdApi.md#adUpdate) | **POST** /ad/update | Update ad


<a name="adCreate"></a>
# **adCreate**
> AdCreateResponse adCreate(adCreateRequest)

Create ad

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/v1");

    AdApi apiInstance = new AdApi(defaultClient);
    AdCreateRequest adCreateRequest = new AdCreateRequest(); // AdCreateRequest | Request body
    try {
      AdCreateResponse result = apiInstance.adCreate(adCreateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdApi#adCreate");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **adCreateRequest** | [**AdCreateRequest**](AdCreateRequest.md)| Request body |

### Return type

[**AdCreateResponse**](AdCreateResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  -  |

<a name="adDelete"></a>
# **adDelete**
> AdDeleteResponse adDelete(adDeleteRequest)

Delete ad

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/v1");

    AdApi apiInstance = new AdApi(defaultClient);
    AdDeleteRequest adDeleteRequest = new AdDeleteRequest(); // AdDeleteRequest | Request body
    try {
      AdDeleteResponse result = apiInstance.adDelete(adDeleteRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdApi#adDelete");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **adDeleteRequest** | [**AdDeleteRequest**](AdDeleteRequest.md)| Request body |

### Return type

[**AdDeleteResponse**](AdDeleteResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  -  |

<a name="adOffers"></a>
# **adOffers**
> AdOffersResponse adOffers(adOffersRequest)

Search offers

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/v1");

    AdApi apiInstance = new AdApi(defaultClient);
    AdOffersRequest adOffersRequest = new AdOffersRequest(); // AdOffersRequest | Request body
    try {
      AdOffersResponse result = apiInstance.adOffers(adOffersRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdApi#adOffers");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **adOffersRequest** | [**AdOffersRequest**](AdOffersRequest.md)| Request body |

### Return type

[**AdOffersResponse**](AdOffersResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  -  |

<a name="adRead"></a>
# **adRead**
> AdReadResponse adRead(adReadRequest)

Read ad

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/v1");

    AdApi apiInstance = new AdApi(defaultClient);
    AdReadRequest adReadRequest = new AdReadRequest(); // AdReadRequest | Request body
    try {
      AdReadResponse result = apiInstance.adRead(adReadRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdApi#adRead");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **adReadRequest** | [**AdReadRequest**](AdReadRequest.md)| Request body |

### Return type

[**AdReadResponse**](AdReadResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  -  |

<a name="adSearch"></a>
# **adSearch**
> AdSearchResponse adSearch(adSearchRequest)

Search ad

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/v1");

    AdApi apiInstance = new AdApi(defaultClient);
    AdSearchRequest adSearchRequest = new AdSearchRequest(); // AdSearchRequest | Request body
    try {
      AdSearchResponse result = apiInstance.adSearch(adSearchRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdApi#adSearch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **adSearchRequest** | [**AdSearchRequest**](AdSearchRequest.md)| Request body |

### Return type

[**AdSearchResponse**](AdSearchResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  -  |

<a name="adUpdate"></a>
# **adUpdate**
> AdUpdateResponse adUpdate(adUpdateRequest)

Update ad

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/v1");

    AdApi apiInstance = new AdApi(defaultClient);
    AdUpdateRequest adUpdateRequest = new AdUpdateRequest(); // AdUpdateRequest | Request body
    try {
      AdUpdateResponse result = apiInstance.adUpdate(adUpdateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdApi#adUpdate");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **adUpdateRequest** | [**AdUpdateRequest**](AdUpdateRequest.md)| Request body |

### Return type

[**AdUpdateResponse**](AdUpdateResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  -  |

