package org.jbehave.web.acceptance;

import org.springframework.beans.factory.annotation.Autowired;

public class InsertStockPage {

	private static final String STOCK_FORM_ID = "name";
	private static final String THRESHOLD_FORM_ID = "threshold";
	private static final String TRADEAT_FORM_ID = "tradeAt";
	private static final String SUBMIT_FORM_ID = "submit";
	
	private String stockUrl;
	@Autowired
	private PageUtils pageUtils;
	
	public void open() {
		pageUtils.open(this.stockUrl);
	}
	
	public void fillForm(String stockName, String threshold, String tradeAt) {
		pageUtils.fillFormField(STOCK_FORM_ID, stockName);
		pageUtils.fillFormField(THRESHOLD_FORM_ID, threshold);
		pageUtils.fillFormField(TRADEAT_FORM_ID, tradeAt);
	}
	
	public void submitForm() {
		pageUtils.submitForm(SUBMIT_FORM_ID);		
	}
	
	public void setStockUrl(String url) {
		this.stockUrl = url;
	}
	
}
