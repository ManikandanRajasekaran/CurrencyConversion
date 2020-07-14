package com.crossPay.currencyConversion.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crossPay.currencyConversion.dto.CurrencyConversionDto;
import com.crossPay.currencyConversion.entity.CurrencyConversionEntity;
import com.crossPay.currencyConversion.repository.CurrencyConversionRepository;

@Service
public class CurrencyConversionService{

	@Autowired
	private CurrencyConversionRepository CurrencyConversionRepository;
	public CurrencyConversionDto getLatestExchangeAmount(CurrencyConversionDto req) {
		
		/*
		 * From Repository connecting to DB and fetching the row available based on From and To Currency.
		 */
		CurrencyConversionEntity cur_obj = CurrencyConversionRepository.findByCurrency(req.getFromCurrency(), req.getToCurrency());
		
		if(cur_obj == null)
			return null;
		/*
		 * Along with input parameter need to display final To Amount in response.
		 * We are multiplying Exchange rate with From Amount and pushing the same in response.
		 */
		req.setToAmount(cur_obj.getRate() * req.getFromAmount());
		return req;
		
	}

}
