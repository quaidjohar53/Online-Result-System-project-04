package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.OnlineShoppingBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class OnlineShoppingModel extends BaseModel<OnlineShoppingBean> {

	@Override
	public long add(OnlineShoppingBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getProductName());
			pstmt.setDouble(3, bean.getProductPrice());
			pstmt.setString(4, bean.getCategory());
			pstmt.setInt(5, bean.getStock());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDateTime());
			pstmt.setTimestamp(9, bean.getModifiedDateTime());

			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);

		}

		return bean.getId();

	}

	@Override
	public void update(OnlineShoppingBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ " set product_name = ?, product_price = ?, category = ?, stock = ?, modified_by = ?, modified_datetime = ? where product_id = ?");

			pstmt.setString(1, bean.getProductName());
			pstmt.setDouble(2, bean.getProductPrice());
			pstmt.setString(3, bean.getCategory());
			pstmt.setInt(4, bean.getStock());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getModifiedDateTime());
			pstmt.setInt(7, bean.getProductId());

			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	@Override
	public String getWhereClause(OnlineShoppingBean bean) {

		StringBuffer sql = new StringBuffer("");
		if (bean != null) {

			if (bean.getProductId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getProductName() != null && bean.getProductName().length() > 0) {
				sql.append(" and product_name like '" + bean.getProductName() + "%'");

			}

			if (bean.getProductPrice() > 0) {
				sql.append(" and product_price = " + bean.getProductPrice());
			}

			if (bean.getCategory() != null && bean.getCategory().length() > 0) {
				sql.append(" and Category like '" + bean.getCategory() + "%'");

			}

			if (bean.getStock() > 0) {
				sql.append(" and stock = " + bean.getStock());
			}

		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_online_shopping";
	}

	@Override
	public OnlineShoppingBean getBean() {
		return new OnlineShoppingBean();
	}

}
