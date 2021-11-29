package com.areamode.shtrident.api.v1;

import com.areamode.shtrident.domain.model.Subscription;
import com.areamode.shtrident.service.FeedSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/subscription")
@RequiredArgsConstructor
@CrossOrigin("${cors.allowed-origins}")
public class SubscriptionController {

    private final FeedSubscriptionService feedSubscriptionService;

    @GetMapping({"", "/"})
    public List<Subscription> getAll() {
        return feedSubscriptionService.getSubscriptions();
    }

    @PostMapping({"", "/"})
    public Subscription save(@RequestBody Subscription subscription) {
        return feedSubscriptionService.saveSubscription(subscription);
    }

    @GetMapping("{id}")
    public Subscription getSubscription(@PathVariable Long id) {
        Optional<Subscription> subscriptionById = feedSubscriptionService.findSubscriptionById(id);
        return subscriptionById.orElse(null);
    }

}