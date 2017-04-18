package com.expedia.eps.propertyapi;

import feign.RetryableException;
import feign.Retryer;
import lombok.Builder;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

@Builder
public class ExpediaRetryer implements Retryer {

    private final int maxAttempts;
    private final long intervalInMillis;
    private int attempts = 1;

    @Override
    public void continueOrPropagate(RetryableException exception) {
        switch (exception.status()) {
            case 400:
            case 500:
                // Non-retryable error - propagate
                throw exception;
            case 503:

                // Retry on temporary server errors, up to 'maxAttempts'
                if (++attempts <= maxAttempts) {
                    try {
                        sleep(intervalInMillis);
                    } catch (InterruptedException ignored) {
                        currentThread().interrupt();
                    }
                    break;
                }

                // We're out of attempts
                throw exception;
        }
    }

    @Override
    public Retryer clone() {
        return ExpediaRetryer.builder()
            .maxAttempts(maxAttempts)
            .intervalInMillis(intervalInMillis)
            .build();
    }
}
