package com.expedia.eps.property;

import com.expedia.eps.ExpediaResponse;
import com.expedia.eps.property.model.Property;
import com.expedia.eps.property.model.PropertyStatus;

import java.util.List;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import rx.Observable;

public interface PropertyApi {

    //    @Headers({
//        "Request-ID: {guid}",
//        "Accept: application/json"
//    })
//    @RequestLine("GET /properties/v1/{provider}/{providerPropertyId}")
//    Observable<ExpediaResponse<Property>> getProperty(@Param("guid") String requestId,
//                                                            @Param("provider") String provider,
//                                                            @Param("providerPropertyId") String providerPropertyId);
//
    @Headers({
        "Request-ID: {guid}",
        "Accept: application/json",
        "Content-Type: application/json"
    })
    @RequestLine("POST /properties/v1/{provider}")
    Observable<ExpediaResponse<List<Property>>> createOrUpdateProperties(@Param("guid") String requestId,
                                                                           @Param("provider") String provider,
                                                                           List<Property> properties);
//
//    @Headers({
//        "Request-ID: {guid}",
//        "Accept: application/json",
//        "Content-Type: application/json"
//    })
//    @RequestLine("DELETE /properties/v1/{provider}/{providerPropertyId")
//    Observable<ExpediaResponse> deleteProperty(@Param("guid") String requestId,
//                                               @Param("provider") String provider,
//                                               @Param("providerPropertyId") String providerPropertyId);

    @Headers({
        "Request-ID: {guid}",
        "Accept: application/json"
    })
    @RequestLine("GET /properties/v1/{provider}/{providerPropertyId}/status")
    Observable<ExpediaResponse<PropertyStatus>> getPropertyStatus(@Param("guid") String requestId,
                                                                  @Param("provider") String provider,
                                                                  @Param("providerPropertyId") String providerPropertyId);

}
