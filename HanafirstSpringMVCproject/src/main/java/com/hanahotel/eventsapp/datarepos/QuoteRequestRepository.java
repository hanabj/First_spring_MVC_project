package com.hanahotel.eventsapp.datarepos;

import com.hanahotel.eventsapp.domain.QuoteRequest;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRequestRepository extends CrudRepository<QuoteRequest, Integer> {

     Iterable<QuoteRequest> findByEventType(String eventType);
}
