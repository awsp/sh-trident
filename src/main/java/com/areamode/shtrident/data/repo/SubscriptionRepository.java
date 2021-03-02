package com.areamode.shtrident.data.repo;

import com.areamode.shtrident.data.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
