/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

import java.sql.Date;

/**
 *
 * @author a024465169t
 */
public class FacturaBean {
    private int id;
    private int id_usuario;
    private Date fecha;
    private Float iva;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idUsuario
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the idUsuario to setu
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the iva
     */
    public Float getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(Float iva) {
        this.iva = iva;
    }
}
