package com.expedia.eps.sync;

import static java.util.Collections.singletonList;
import static java.util.UUID.randomUUID;

import com.expedia.eps.ExpediaResponse;
import com.expedia.eps.product.ProductApi;
import com.expedia.eps.property.PropertyApi;
import com.expedia.eps.property.model.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class OnboardingExample {

    @Autowired
    private PropertyApi propertyApi;

    @Autowired
    private ProductApi productApi;

    @Test
    public void onboardProperty() throws Exception
    {

        final String correlationId = randomUUID().toString();
        final String providerPropertyId = randomUUID().toString();
        final String providerId = "1000";

        List<Address> addressList = new ArrayList<>();
        addressList.add(new Address("Calle Ruiz de Alarcon 23", "", "Madrid", "", "28014", "Spain"));

        Contact propertyContact = new Contact("John", "Smith",
                new ArrayList<>(Collections.singletonList("JohnSmith@nowhere.com")),
                new ArrayList<PhoneNumber>(Collections.singletonList(new PhoneNumber(PhoneNumberType.PHONE, "1", "1", "1234567"))));
        Contact generalManager = new Contact("David", "Barter",
                new ArrayList<>(Collections.singletonList("DavidBarter@nowhere.com")),
                new ArrayList<PhoneNumber>(Collections.singletonList(new PhoneNumber(PhoneNumberType.PHONE, "1", "1", "1234567"))));
        Contact altManager = new Contact("Adam", "Pool",
                new ArrayList<>(Collections.singletonList("AdamPool@nowhere.com")),
                new ArrayList<PhoneNumber>(Collections.singletonList(new PhoneNumber(PhoneNumberType.PHONE, "1", "1", "1234567"))));
        Contact reservation = new Contact("Kyle", "Lion",
                new ArrayList<>(Collections.singletonList("KyleLion@nowhere.com")),
                new ArrayList<PhoneNumber>(Collections.singletonList(new PhoneNumber(PhoneNumberType.PHONE, "1", "1", "1234567"))));

        PropertyContacts buildContacts = PropertyContacts.builder()
                .property(propertyContact)
                .generalManager(generalManager)
                .alternateReservationManager(altManager)
                .reservationManager(reservation)
                .build();

        Property property = Property.builder()
                .providerPropertyId(providerPropertyId)
                .name("Prado National Museum")
                .addresses(addressList)
                .latitude("40.413722")
                .longitude("3.692412")
                .currencyCode("EUR")
                .contacts(buildContacts)
                .build();

        // Set up the property in Xpresso
        List<Property> properties = propertyApi.createOrUpdateProperties(correlationId, providerId, singletonList(property))
                .map(ExpediaResponse::getEntity)
                .toBlocking().single();


        // Now create rooms.. rates..
    }
}
