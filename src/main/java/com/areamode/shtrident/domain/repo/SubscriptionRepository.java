package com.areamode.shtrident.domain.repo;

import com.areamode.shtrident.domain.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}