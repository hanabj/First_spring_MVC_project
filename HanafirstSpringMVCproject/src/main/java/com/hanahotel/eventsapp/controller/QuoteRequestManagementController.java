package com.hanahotel.eventsapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hanahotel.eventsapp.datarepos.QuoteRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hanahotel.eventsapp.domain.QuoteRequest;

@Controller
public class QuoteRequestManagementController {


    @Autowired
    private QuoteRequestRepository quoteRequestRepository;

    @GetMapping(path = "/quoteRequests")
    public String listRequests(Model model) {

        List<QuoteRequest> listRequests = new ArrayList<>();
        quoteRequestRepository.findAll().forEach(listRequests::add);
        model.addAttribute("ListOfRequests", listRequests);
        return "quoteRequestList";
    }

    @GetMapping(path = "/quoteRequests", params="eventType=Wedding")
    public String listWeddingRequests(Model model) {

        List<QuoteRequest> listRequests = new ArrayList<>();
        quoteRequestRepository.findByEventType("Wedding").forEach(listRequests::add);
        model.addAttribute("ListOfRequests", listRequests);
        return "quoteRequestList";
    }

    @GetMapping("/quoteRequest/{id}")
    public ModelAndView viewQuoteRequest(@PathVariable int id) {
        QuoteRequest quoteRequestBean= new QuoteRequest();
        if (quoteRequestRepository.findById(id).isPresent())
            quoteRequestBean= quoteRequestRepository.findById(id).get();

        ModelAndView mav = new ModelAndView();
        mav.addObject("quoteRequestBean", quoteRequestBean);
        mav.setViewName("quoteRequestDetail");

        return mav;
    }

    @GetMapping("/quoteRequest/social/{id}")
    public ModelAndView viewRequestSocial(@PathVariable int id) {
        String returnViewName = "quoteRequestSocialEventDetail";


        if ((quoteRequestRepository.findById(id).isPresent())) {
            QuoteRequest quoteRequestBean= quoteRequestRepository.findById(id).get();
            if (quoteRequestBean.getEventType().equalsIgnoreCase("Wedding"))
            returnViewName = "redirect:/quoteRequest/{id}?eventType";
        }

         return new ModelAndView(returnViewName);
    }

    @GetMapping
    @ResponseBody
    public QuoteRequest viewQuoteRequestApi() {

        // add some implementation here
        QuoteRequest quoteRequest = new QuoteRequest();
        quoteRequest.setBudget(5000);

        return quoteRequest;
    }

    @PostMapping("/quoteDetail")
    public String updateQuoteRequest(@ModelAttribute QuoteRequest formBean) {

        // implement a save of all of the form bean information

        return "quoteRequestDetail";
    }

    @ModelAttribute
    public void addCommonAttributes(@RequestParam String eventType,
                                    Model model) {

        String customMessage = "You are viewing requests for "
                + eventType;
        model.addAttribute("eventTypeMessage", customMessage);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(dateFormat, false));

    }
}
