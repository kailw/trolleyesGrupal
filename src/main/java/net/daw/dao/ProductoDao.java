package net.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.daw.bean.ProductoBean;

public class ProductoDao {

    Connection oConnection;
    String ob = null;

    public ProductoDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public ProductoBean get(int id) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id=?";
        ProductoBean oProductoBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oProductoBean = new ProductoBean();
                oProductoBean.setId(oResultSet.getInt("id"));
                oProductoBean.setCodigo(oResultSet.getString("codigo"));
                oProductoBean.setDesc(oResultSet.getString("desc"));
                oProductoBean.setExistencias(oResultSet.getInt("existencias"));
                oProductoBean.setPrecio(oResultSet.getFloat("precio"));
                oProductoBean.setFoto(oResultSet.getString("foto"));
                oProductoBean.setIdProducto(oResultSet.getInt("id_tipoProducto"));
            } else {
                oProductoBean = null;
            }
        } catch (SQLException e) {
            throw new Exception("Error en Dao get de " + ob, e);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return oProductoBean;
    }

    public int remove(int id) throws Exception {
        int iRes = 0;
        String strSQL = "DELETE FROM " + ob + " WHERE id=?";
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            iRes = oPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error en Dao remove de " + ob, e);
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iRes;
    }

    public int getcount() throws Exception {
        String strSQL = "SELECT COUNT(id) FROM " + ob;
        int res = 0;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                res = oResultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new Exception("Error en Dao get de " + ob, e);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return res;
    }

    public ProductoBean create(ProductoBean oProductoBean) throws Exception {
        String strSQL = "INSERT INTO " + ob + " (`id`, `codigo`, `desc`, `existencias`, `precio`, `foto`"
                + ", `id_tipoProducto`) VALUES (NULL, ?,?,?,?,?,?); ";
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setString(1, oProductoBean.getCodigo());
            oPreparedStatement.setString(2, oProductoBean.getDesc());
            oPreparedStatement.setInt(3, oProductoBean.getExistencias());
            oPreparedStatement.setFloat(4, oProductoBean.getPrecio());
            oPreparedStatement.setString(5, oProductoBean.getFoto());
            oPreparedStatement.setString(6, oProductoBean.getFoto());
            oPreparedStatement.setInt(6, oProductoBean.getIdProducto());
            oPreparedStatement.executeUpdate();
            oResultSet = oPreparedStatement.getGeneratedKeys();
            if (oResultSet.next()) {
                oProductoBean.setId(oResultSet.getInt(1));
            } else {
                oProductoBean.setId(0);
            }
        } catch (SQLException e) {
            throw new Exception("Error en Dao create de " + ob, e);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return oProductoBean;
    }

    public int update(ProductoBean oProductoBean) throws Exception {
        int iResult = 0;
        String strSQL = "UPDATE " + ob + " SET  `codigo` = ?, `desc` = ?, `existencias` = ?, `precio` = ? "
                + ", `foto` = ?, `id_tipoProducto` = ? WHERE `Producto`.`id` = ?;";

        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setString(1, oProductoBean.getCodigo());
            oPreparedStatement.setString(2, oProductoBean.getDesc());
            oPreparedStatement.setInt(3, oProductoBean.getExistencias());
            oPreparedStatement.setFloat(4, oProductoBean.getPrecio());
            oPreparedStatement.setString(5, oProductoBean.getFoto());
            oPreparedStatement.setString(6, oProductoBean.getFoto());
            oPreparedStatement.setInt(6, oProductoBean.getIdProducto());
            iResult = oPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Error en Dao update de " + ob, e);
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;
    }

    public ArrayList<ProductoBean> getpage(int iRpp, int iPage) throws Exception {
        String strSQL = "SELECT * FROM `Producto` ";
        ArrayList<ProductoBean> alProductoBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oResultSet = oPreparedStatement.executeQuery();
                alProductoBean = new ArrayList<ProductoBean>();
                while (oResultSet.next()) {
                    ProductoBean oProductoBean = new ProductoBean();
                    oProductoBean.setId(oResultSet.getInt("id"));
                    oProductoBean.setCodigo(oResultSet.getString("codigo"));
                    oProductoBean.setDesc(oResultSet.getString("desc"));
                    oProductoBean.setExistencias(oResultSet.getInt("existencias"));
                    oProductoBean.setPrecio(oResultSet.getFloat("precio"));
                    oProductoBean.setFoto(oResultSet.getString("foto"));
                    oProductoBean.setIdProducto(oResultSet.getInt("id_tipoProducto"));
                }
            } catch (SQLException e) {
                throw new Exception("Error en Dao getpage de " + ob, e);
            } finally {
                if (oResultSet != null) {
                    oResultSet.close();
                }
                if (oPreparedStatement != null) {
                    oPreparedStatement.close();
                }
            }
        } else {
            throw new Exception("Error en Dao getpage de " + ob);
        }
        return alProductoBean;

    }

}
