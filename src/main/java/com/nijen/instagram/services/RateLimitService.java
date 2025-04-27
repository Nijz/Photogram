package com.nijen.instagram.services;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimitService {

    private final int MAX_REQUESTS = 10;  // Max requests per hour
    private final int REFILL_INTERVAL = 1; // Interval to refill in hours
    private final int REFILL_TOKENS = 100; // Tokens to refill

    // This bucket stores rate limiting information (can be stored per user/IP)
    private Bucket createBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.simple(MAX_REQUESTS, Duration.ofHours(REFILL_INTERVAL))
                        .withInitialTokens(REFILL_TOKENS))
                .build();
    }

    public boolean isAllowed(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        Bucket bucket = getBucketForUser(ip);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        return probe.isConsumed();
    }

    // Returns a rate-limiting bucket for a specific user (or IP)
    private Bucket getBucketForUser(String userIdentifier) {
        // Use a map to store user-specific buckets (In a real app, consider using Redis for distributed storage)
        return createBucket();
    }
}
