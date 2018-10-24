package net.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.daw.bean.TipoproductoBean;

public class TipoproductoDao {

	Connection oConnection;
	String ob = null;

	public TipoproductoDao(Connection oConnection, String ob) {
		super();
		this.oConnection = oConnection;
		this.ob = ob;
	}

	public TipoproductoBean get(int id) throws Exception {
		String strSQL = "SELECT * FROM " + ob + " WHERE id=?";
		TipoproductoBean oTipoProductoBean;
		ResultSet oResultSet = null;
		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oPreparedStatement.setInt(1, id);
			oResultSet = oPreparedStatement.executeQuery();
			if (oResultSet.next()) {
				oTipoProductoBean = new TipoproductoBean();
				oTipoProductoBean.setId(oResultSet.getInt("id"));
				oTipoProductoBean.setDesc(oResultSet.getString("desc"));
			} else {
				oTipoProductoBean = null;
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
		return oTipoProductoBean;
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

	public TipoproductoBean create(TipoproductoBean oTipoProductoBean) throws Exception {
		String strSQL = "INSERT INTO " + ob + " (`id`, `desc`) VALUES (NULL, ?); ";
		ResultSet oResultSet = null;
		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oPreparedStatement.setString(1, oTipoProductoBean.getDesc());
			oPreparedStatement.executeUpdate();
			oResultSet = oPreparedStatement.getGeneratedKeys();
			if (oResultSet.next()) {
				oTipoProductoBean.setId(oResultSet.getInt(1));
			} else {
				oTipoProductoBean.setId(0);
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
		return oTipoProductoBean;
	}

	public int update(TipoproductoBean oTipoProductoBean) throws Exception {
		int iResult = 0;
		String strSQL = "UPDATE " + ob + " SET `desc` = ? WHERE `tipoProducto`.`id` = ?;";

		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oPreparedStatement.setString(1, oTipoProductoBean.getDesc());
			oPreparedStatement.setInt(2, oTipoProductoBean.getId());
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

	public ArrayList<TipoproductoBean> getpage(int iRpp, int iPage) throws Exception {
		String strSQL = "SELECT * FROM `tipoProducto` ";
		ArrayList<TipoproductoBean> alTipoProductoBean;
		if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
			strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
			ResultSet oResultSet = null;
			PreparedStatement oPreparedStatement = null;
			try {
				oPreparedStatement = oConnection.prepareStatement(strSQL);
				oResultSet = oPreparedStatement.executeQuery();
				alTipoProductoBean = new ArrayList<TipoproductoBean>();
				while (oResultSet.next()) {
					TipoproductoBean oTipoProductoBean = new TipoproductoBean();
					oTipoProductoBean.setId(oResultSet.getInt("id"));
					oTipoProductoBean.setDesc(oResultSet.getString("desc"));
					alTipoProductoBean.add(oTipoProductoBean);
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
		return alTipoProductoBean;

	}

}
