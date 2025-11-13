package com.huntercodexs.demo.samples.retry;

import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.NeverRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.Duration;
import java.util.function.Predicate;

public class HttpStatusRetry {

    static final Predicate<Throwable> SERVER_ERROR_PREDICATE = throwable -> {
        if (throwable instanceof HttpStatusCodeException httpStatusException) {
            int statusCode = httpStatusException.getStatusCode().value();
            return statusCode >= 500 && statusCode < 600;
        }
        return false;
    };

    static final Predicate<Throwable> CLIENT_ERROR_PREDICATE = throwable -> {
        if (throwable instanceof HttpStatusCodeException httpStatusException) {
            int statusCode = httpStatusException.getStatusCode().value();
            return statusCode >= 400 && statusCode < 500;
        }
        return false;
    };

    private static RetryTemplate buildRetryTemplate(long maxAttempts,
                                                    long backoffMillis,
                                                    Predicate<Throwable> predicate) {
        RetryTemplate template = new RetryTemplate();

        // Classifier to decide whether to retry based on predicate result
        org.springframework.classify.Classifier<Throwable, RetryPolicy> classifier =
                throwable -> predicate.test(throwable)
                        ? new SimpleRetryPolicy(Math.max(1, (int) maxAttempts))
                        : new NeverRetryPolicy();

        ExceptionClassifierRetryPolicy exceptionPolicy =
                new ExceptionClassifierRetryPolicy();
        exceptionPolicy.setExceptionClassifier(classifier);
        template.setRetryPolicy(exceptionPolicy);

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(Math.max(0L, backoffMillis));
        template.setBackOffPolicy(backOffPolicy);

        return template;
    }

    public static RetryTemplate serverError(long maxAttempts, Duration fixedDelay) {
        return buildRetryTemplate(maxAttempts, fixedDelay.toMillis(), SERVER_ERROR_PREDICATE);
    }

    public static RetryTemplate serverError(long maxAttempts) {
        return buildRetryTemplate(maxAttempts, 0L, SERVER_ERROR_PREDICATE);
    }

    public static RetryTemplate serverError() {
        return serverError(3L);
    }

    public static RetryTemplate clientError(long maxAttempts, Duration fixedDelay) {
        return buildRetryTemplate(maxAttempts, fixedDelay.toMillis(), CLIENT_ERROR_PREDICATE);
    }

    public static RetryTemplate clientError(long maxAttempts) {
        return buildRetryTemplate(maxAttempts, 0L, CLIENT_ERROR_PREDICATE);
    }

    public static RetryTemplate clientError() {
        return clientError(3L);
    }
}
