package com.expedia.eps.sync;

import static com.expedia.eps.property.model.StatusCodes.ONBOARDINGSUCCEEDED;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

import com.expedia.eps.ExpediaResponse;
import com.expedia.eps.property.PropertyApi;
import com.expedia.eps.property.model.Property;
import com.expedia.eps.property.model.PropertyStatus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class GetProperty {

    @Autowired
    private PropertyApi propertyApi;

    @Test
    public void getProperty() {

        final String correlationId = randomUUID().toString();
        final String propertyId = "11112";

        final PropertyStatus status = propertyApi.getPropertyStatus(correlationId, "1000", propertyId)
            .map(ExpediaResponse::getEntity)
            .toBlocking()
            .single();

        assertThat(status.getCode()).isEqualTo(ONBOARDINGSUCCEEDED);

        final Property property = propertyApi.getProperty(correlationId, "1000", propertyId)
                .map(ExpediaResponse::getEntity)
                .toBlocking()
                .single();

        assertThat(property.getExpediaId()).isNotNull();
        assertThat(property.getName()).isNotNull();
        assertThat(property.getAddresses()).isNotNull();
        assertThat(property.getProvider().equals("1000"));
        assertThat(property.getProviderPropertyId().equals(propertyId));
    }
}
