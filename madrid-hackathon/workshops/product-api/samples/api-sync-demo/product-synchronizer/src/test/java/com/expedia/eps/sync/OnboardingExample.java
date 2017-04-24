package com.expedia.eps.sync;

import static com.expedia.eps.product.utils.Defaults.defaultRoomType;
import static com.expedia.eps.property.model.PhoneNumberType.PHONE;
import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static java.util.Objects.nonNull;
import static java.util.UUID.randomUUID;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

import com.expedia.eps.ExpediaResponse;
import com.expedia.eps.product.ProductApi;
import com.expedia.eps.product.model.RoomType;
import com.expedia.eps.property.PropertyApi;
import com.expedia.eps.property.model.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class OnboardingExample {

    @Autowired
    private PropertyApi propertyApi;

    @Autowired
    private ProductApi productApi;

    @Test
    public void onboardProperty() {

        final String requestId = randomUUID().toString();
        final String correlationId = randomUUID().toString();
        final Property property = buildTestProperty(requestId);
        final String providerId = "1000";

        // Create the property On Expedia
        final List<Property> properties = propertyApi
            .createOrUpdateProperties(requestId, providerId, singletonList(property))
            .map(ExpediaResponse::getEntity)
            .toBlocking()
            .first();

        // Obtain the onboarded property from the response
        final Property onboardedProperty = properties
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No property has been created"));

        // Poll the server until onboarding process is complete
        final Integer expediaId = propertyApi
            .getPropertyStatus(correlationId, providerId, onboardedProperty.getProviderPropertyId())
            .map(ExpediaResponse::getEntity)
            .repeatWhen(observable -> observable.delay(5, SECONDS))
            .first(response -> response.getCode().equals(StatusCodes.ONBOARDINGSUCCEEDED))
            .map(PropertyStatus::getExpediaId)
            .toBlocking()
            .single();

        assertThat(expediaId).isNotNull();

        // Now that we have an Expedia ID, we can proceed to create room types & rate plans
        final RoomType newRoomType = productApi
            .createRoomType(randomUUID().toString(), expediaId, defaultRoomType("Test Room"))
            .map(ExpediaResponse::getEntity)
            .toBlocking()
            .first();

        assertThat(newRoomType.getResourceId()).isNotNull();
    }

    private Property buildTestProperty(String requestId) {

        // Mocked address
        final List<Address> addressList = singletonList(Address.builder()
                                                            .line1("Calle Ruiz de Alarcon 23")
                                                            .line2("")
                                                            .city("Madrid")
                                                            .state("")
                                                            .postalCode("28014")
                                                            .countryCode("Spain")
                                                            .build());

        // Mocked contacts
        final PropertyContacts contracts = PropertyContacts.builder()
            .property(mockedContact("Jimi", "Hendrix"))
            .generalManager(mockedContact("Janes", "Joplin"))
            .reservationManager(mockedContact("Jimmy", "Page"))
            .alternateReservationManager(mockedContact("Muddy", "Waters"))
            .build();

        // Mocked property
        return Property.builder()
            .providerPropertyId(requestId)
            .name("Prado National Museum")
            .addresses(addressList)
            .latitude("40.413722")
            .longitude("3.692412")
            .currencyCode("EUR")
            .contacts(contracts)
            .build();
    }

    private Contact mockedContact(String firstName, String lastName) {
        return Contact.builder()
            .firstName(firstName)
            .lastName(lastName)
            .emails(singletonList(format("%s%s@nowhere.com", firstName, lastName)))
            .phoneNumbers(singletonList(PhoneNumber.builder()
                                            .phoneNumberType(PHONE)
                                            .areaCode("1")
                                            .countryAccessCode("1")
                                            .number(randomPhone())
                                            .build()))
            .build();
    }

    private String randomPhone() {
        return String.valueOf(new Random().nextInt((9999999 - 9000000) + 1) + 90000000);
    }
}
