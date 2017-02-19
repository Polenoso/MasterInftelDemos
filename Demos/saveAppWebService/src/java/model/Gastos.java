/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aitorpagan
 */
@Entity
@Table(name = "GASTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gastos.findAll", query = "SELECT g FROM Gastos g"),
    @NamedQuery(name = "Gastos.findByIdGasto", query = "SELECT g FROM Gastos g WHERE g.idGasto = :idGasto"),
    @NamedQuery(name = "Gastos.findByConcepto", query = "SELECT g FROM Gastos g WHERE g.concepto = :concepto"),
    @NamedQuery(name = "Gastos.findByCantidad", query = "SELECT g FROM Gastos g WHERE g.cantidad = :cantidad"),
    @NamedQuery(name = "Gastos.findByTipo", query = "SELECT g FROM Gastos g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "Gastos.findByFecha", query = "SELECT g FROM Gastos g WHERE g.fecha = :fecha"),
    @NamedQuery(name = "Gastos.findByIdUsuario", query = "SELECT i FROM Gastos i WHERE i.idUserGasto.idUser = :idUserGasto")})
public class Gastos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_GASTO")
    private Long idGasto;
    @Size(max = 20)
    @Column(name = "CONCEPTO")
    private String concepto;
    @Column(name = "CANTIDAD")
    private Long cantidad;
    @Size(max = 20)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 20)
    @Column(name = "FECHA")
    private String fecha;
    @JoinColumn(name = "ID_USER_GASTO", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Usuario idUserGasto;

    public Gastos() {
    }

    public Gastos(Long idGasto) {
        this.idGasto = idGasto;
    }

    public Long getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(Long idGasto) {
        this.idGasto = idGasto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getIdUserGasto() {
        return idUserGasto;
    }

    public void setIdUserGasto(Usuario idUserGasto) {
        this.idUserGasto = idUserGasto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGasto != null ? idGasto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gastos)) {
            return false;
        }
        Gastos other = (Gastos) object;
        if ((this.idGasto == null && other.idGasto != null) || (this.idGasto != null && !this.idGasto.equals(other.idGasto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Gastos[ idGasto=" + idGasto + " ]";
    }
    
}
