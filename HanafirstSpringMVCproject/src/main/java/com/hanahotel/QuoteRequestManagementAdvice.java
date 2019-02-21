package com.hanahotel;

import com.hanahotel.eventsapp.controller.QuoteRequestController;
import com.hanahotel.eventsapp.controller.QuoteRequestManagementController;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice(assignableTypes = {
		QuoteRequestController.class,
		QuoteRequestManagementController.class
})
public class QuoteRequestManagementAdvice {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		
		binder.registerCustomEditor(Date.class, 
				new CustomDateEditor(dateFormat, false));
		
	}
}
