/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

/**
 *
 * @author a024465169t
 */
public class LineaBean {
    private int id;
    private int cantidad;
    private int id_factura;
    private int id_producto;

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
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the idFactura
     */
    public int getId_factura() {
        return id_factura;
    }

    /**
     * @param id_factura the idFactura to set
     */
    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    /**
     * @return the idProducto
     */
    public int getId_producto() {
        return id_producto;
    }

    /**
     * @param id_producto the idProducto to set
     */
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    
}
