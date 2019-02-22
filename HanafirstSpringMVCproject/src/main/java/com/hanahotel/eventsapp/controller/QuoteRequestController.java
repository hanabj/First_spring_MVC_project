package com.hanahotel.eventsapp.controller;

import com.hanahotel.eventsapp.datarepos.QuoteRequestRepository;
import com.hanahotel.eventsapp.domain.QuoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuoteRequestController {


    @Autowired
    private QuoteRequestRepository quoteRequestRepository;

    @GetMapping("/newquote")
    public String beginQuoteRequest(Model model) {
        // add implementation later

        model.addAttribute("quoteRequestForm", new QuoteRequest());

        return "newQuote";
    }

    @PostMapping
    public String submitQuoteRequest(@ModelAttribute QuoteRequest formBean) {


        quoteRequestRepository.save(formBean);

        return "newQuoteConfirmation";
    }

}
