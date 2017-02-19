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
@Table(name = "INGRESOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingresos.findAll", query = "SELECT i FROM Ingresos i"),
    @NamedQuery(name = "Ingresos.findByIdIngreso", query = "SELECT i FROM Ingresos i WHERE i.idIngreso = :idIngreso"),
    @NamedQuery(name = "Ingresos.findByConcepto", query = "SELECT i FROM Ingresos i WHERE i.concepto = :concepto"),
    @NamedQuery(name = "Ingresos.findByCantidad", query = "SELECT i FROM Ingresos i WHERE i.cantidad = :cantidad"),
    @NamedQuery(name = "Ingresos.findByFecha", query = "SELECT i FROM Ingresos i WHERE i.fecha = :fecha"),
    @NamedQuery(name = "Ingresos.findByIdUsuario", query = "SELECT i FROM Ingresos i WHERE i.idUserIngreso.idUser = :idUserIngreso")})
public class Ingresos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_INGRESO")
    private Long idIngreso;
    @Size(max = 20)
    @Column(name = "CONCEPTO")
    private String concepto;
    @Column(name = "CANTIDAD")
    private Long cantidad;
    @Size(max = 20)
    @Column(name = "FECHA")
    private String fecha;
    @JoinColumn(name = "ID_USER_INGRESO", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private Usuario idUserIngreso;

    public Ingresos() {
    }

    public Ingresos(Long idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Long getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Long idIngreso) {
        this.idIngreso = idIngreso;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getIdUserIngreso() {
        return idUserIngreso;
    }

    public void setIdUserIngreso(Usuario idUserIngreso) {
        this.idUserIngreso = idUserIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIngreso != null ? idIngreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingresos)) {
            return false;
        }
        Ingresos other = (Ingresos) object;
        if ((this.idIngreso == null && other.idIngreso != null) || (this.idIngreso != null && !this.idIngreso.equals(other.idIngreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ingresos[ idIngreso=" + idIngreso + " ]";
    }
    
}
