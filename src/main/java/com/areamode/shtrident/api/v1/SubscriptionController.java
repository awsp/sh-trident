package com.areamode.shtrident.api.v1;

import com.areamode.shtrident.data.model.Subscription;
import com.areamode.shtrident.service.FeedSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
