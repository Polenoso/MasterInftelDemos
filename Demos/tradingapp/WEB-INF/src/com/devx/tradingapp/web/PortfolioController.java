package com.devx.tradingapp.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.neurotech.quotes.Quote;
import net.neurotech.quotes.QuoteException;
import net.neurotech.quotes.QuoteFactory;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.devx.tradingapp.business.Portfolio;

public class PortfolioController implements Controller {

    private com.devx.tradingapp.business.Portfolio portfolio;

    public PortfolioController(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) {
        Map model = new HashMap();

        List portfolioItems = getPortfolioItems();

        model.put("cash", portfolio.getCash() + "");
        model.put("portfolioItems", portfolioItems);

        return new ModelAndView("portfolio", "model", model);
    }

    private List getPortfolioItems() {
        List portfolioItems = new ArrayList();

        Iterator symbolIter = portfolio.getSymbolIterator();

        while (symbolIter.hasNext()) {
            String symbol = (String) symbolIter.next();

            int shares = portfolio.getNumberOfShares(symbol);
            QuoteFactory quoteFactory = new QuoteFactory();

            Quote quote = null;

            try {
                quote = quoteFactory.getQuote(symbol);
            } catch (Exception e) {
                quote = new Quote(this.getClass().getName()) {
                };
            }

            PortfolioItemBean portfolioItem = new PortfolioItemBean();
            portfolioItem.setSymbol(symbol);
            portfolioItem.setShares(shares);
            portfolioItem.setQuote(quote);
            portfolioItem.setCurrentValue(shares * quote.getValue());
            portfolioItem.setGainLoss(shares * quote.getPctChange());
            portfolioItems.add(portfolioItem);
        }
        return portfolioItems;
    }

}