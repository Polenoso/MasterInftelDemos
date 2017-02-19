/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleJSF.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sampleJSF.ejb.CustomerFacade;
import sampleJSF.ejb.DiscountCodeFacade;
import sampleJSF.ejb.MicroMarketFacade;
import sampleJSF.entity.Customer;
import sampleJSF.entity.DiscountCode;
import sampleJSF.entity.MicroMarket;

/**
 *
 * @author guzman
 */
@ManagedBean
@SessionScoped
public class CustomerBean {
    @EJB
    private DiscountCodeFacade discountCodeFacade;
    @EJB
    private MicroMarketFacade microMarketFacade;
    @EJB
    private CustomerFacade customerFacade;
    
    
    
    
    protected List<Customer> listaClientes;
    protected Customer clienteSeleccionado;
    protected List<String> listaZipCodes;
    protected String zipCodeSeleccionado;
    protected List<String> listaDiscountcodes;
    protected String discountcodeSeleccionado;
    
    
    

    /**
     * Creates a new instance of CustomerBean
     */
    public CustomerBean() {
    }
    
    @PostConstruct
    public void init () {
        listaClientes = this.customerFacade.findAll();
        listaZipCodes = this.microMarketFacade.findAllZipCode();
        listaDiscountcodes = this.discountCodeFacade.findAllDiscountcode();
    }

    public List<Customer> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Customer> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Customer getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Customer clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public List<String> getListaZipCodes() {
        return listaZipCodes;
    }

    public void setListaZipCodes(List<String> listaZipCodes) {
        this.listaZipCodes = listaZipCodes;
    }

    public String getZipCodeSeleccionado() {
        return zipCodeSeleccionado;
    }

    public void setZipCodeSeleccionado(String zipCodeSeleccionado) {
        this.zipCodeSeleccionado = zipCodeSeleccionado;
    }

    public List<String> getListaDiscountcodes() {
        return listaDiscountcodes;
    }

    public void setListaDiscountcodes(List<String> listaDiscountcodes) {
        this.listaDiscountcodes = listaDiscountcodes;
    }

    public String getDiscountcodeSeleccionado() {
        return discountcodeSeleccionado;
    }

    public void setDiscountcodeSeleccionado(String discountcodeSeleccionado) {
        this.discountcodeSeleccionado = discountcodeSeleccionado;
    }
                
    public String doEdit (Customer cliente) {
        this.clienteSeleccionado = cliente;
        this.zipCodeSeleccionado = cliente.getZip().getZipCode();
        this.discountcodeSeleccionado = cliente.getDiscountCode().getDiscountCode();
        return "customer";
    }
    
    public String doDelete (Customer cliente) {
        this.customerFacade.remove(cliente);
        this.listaClientes.remove(cliente);
        return "listadoCustomer";
    }    
    
    public String doGuardar() {
        MicroMarket mm = this.microMarketFacade.find(this.zipCodeSeleccionado);
        clienteSeleccionado.setZip(mm);
        DiscountCode dc = this.discountCodeFacade.find(this.discountcodeSeleccionado);
        this.clienteSeleccionado.setDiscountCode(dc);
        this.customerFacade.edit(clienteSeleccionado);       
        clienteSeleccionado = new Customer();
        this.zipCodeSeleccionado = "";
        this.discountcodeSeleccionado = "";
      return "listadoCustomer";   
    }            

    
}
