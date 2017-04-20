package com.expedia.eps.sync;

import static com.expedia.eps.product.utils.Defaults.defaultRoomType;
import static com.expedia.eps.property.model.PhoneNumberType.PHONE;
import static com.expedia.eps.property.model.StatusCodes.EXPEDIAIDASSIGNED;
import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

import com.expedia.eps.ExpediaResponse;
import com.expedia.eps.product.ProductApi;
import com.expedia.eps.product.model.RoomType;
import com.expedia.eps.property.PropertyApi;
import com.expedia.eps.property.model.Address;
import com.expedia.eps.property.model.Contact;
import com.expedia.eps.property.model.PhoneNumber;
import com.expedia.eps.property.model.Property;
import com.expedia.eps.property.model.PropertyContacts;
import com.expedia.eps.property.model.PropertyResponse;
import com.expedia.eps.property.model.PropertyStatus;
import com.expedia.eps.property.model.StatusCodes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        final Property property = buildTestProperty(requestId);
        final String providerId = "1000";

        // Create the property On Expedia
        final PropertyResponse propertyResponse = propertyApi
            .createOrUpdateProperties(requestId, providerId, singletonList(property))
            .map(ExpediaResponse::getEntity)
            .toBlocking()
            .single();

        assertThat(propertyResponse).isNotNull();
        assertThat(propertyResponse.getProperties()).isNotEmpty();

        // Obtain the onboarded property from the response
        final Property onboardedProperty = propertyResponse.getProperties()
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No property has been created"));

        // Poll the server until onboarding process is complete
        final Integer expediaId = propertyApi
            .getPropertyStatus(randomUUID().toString(), providerId, onboardedProperty.getProviderPropertyId())
            .map(ExpediaResponse::getEntity)
            .repeatWhen(observable -> observable.delay(5, SECONDS))
            .takeUntil(response -> response.getCode().equals(EXPEDIAIDASSIGNED))
            .map(PropertyStatus::getExpediaId)
            .toBlocking()
            .single();

        assertThat(expediaId).isNotNull();

        // Now that we have an Expedia ID, we can proceed to create room types & rate plans
        final RoomType newRoomType = productApi
            .createRoomType(randomUUID().toString(), expediaId, defaultRoomType("Test Room"))
            .map(ExpediaResponse::getEntity)
            .toBlocking()
            .single();

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

        // Mocked contact
        final Contact contact = Contact.builder()
            .firstName("John")
            .lastName("Smith")
            .emails(singletonList("JohnSmith@nowhere.com"))
            .phoneNumbers(singletonList(PhoneNumber.builder()
                                            .phoneNumberType(PHONE)
                                            .areaCode("1")
                                            .countryAccessCode("1")
                                            .number("1234567")
                                            .build()))
            .build();
        final PropertyContacts contracts = PropertyContacts.builder()
            .property(contact)
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
}
