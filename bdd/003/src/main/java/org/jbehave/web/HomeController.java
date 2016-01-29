package org.jbehave.web;

import org.jbehave.business.Stock;
import org.jbehave.business.TradingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {


	@Autowired
	private TradingService tradingService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showForm(Model model) {
		model.addAttribute("stockInfo", new StockForm());
		return "newstock";
	}
	
	/**
	 * Simply gets a new stock and shows current status.
	 * @return view.
	 */
	@RequestMapping(value="/", method=RequestMethod.POST )
	public ModelAndView submitForm(@ModelAttribute("stockInfo")StockForm stockForm) {

		Stock stock = getStock(stockForm.getName(), stockForm.getThreshold());
		stock.setTradeAt(stockForm.getTradeAt());
		return  new ModelAndView("showstatus", "status", stock.getStatus());
	}
	
	public Stock getStock(String name, double threshold) {
		return this.tradingService.addNewStock(name, threshold);
	}
	
}

