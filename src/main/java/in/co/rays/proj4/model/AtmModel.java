package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.rays.proj4.bean.ATMBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class AtmModel extends BaseModel<ATMBean> {

	@Override
	public long add(ATMBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, nextPK());
			pstmt.setString(2, bean.getLocation());
			pstmt.setString(3, bean.getBankName());
			pstmt.setString(4, bean.getCashAvailable());
			pstmt.setString(5, bean.getStatus());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDateTime());
			pstmt.setTimestamp(9, bean.getModifiedDateTime());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean.getId();
	}

	@Override
	public void update(ATMBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("Update " + getTable()
					+ " set Location = ?, BankName = ?, CashAvailable = ?, Status = ?, ModifiedBy = ?, ModifiedDateTime = ? where ID=?");

			pstmt.setString(1, bean.getLocation());
			pstmt.setString(2, bean.getBankName());
			pstmt.setString(3, bean.getCashAvailable());
			pstmt.setString(4, bean.getStatus());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getModifiedDateTime());
			pstmt.setLong(7, bean.getId());

			pstmt.executeUpdate();
			conn.commit();

		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	@Override
	public String getWhereClause(ATMBean bean) {

		StringBuffer sql = new StringBuffer(" where 1=1 ");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());

				if (bean.getLocation() != null && bean.getLocation().length() > 0) {
					sql.append(" and Location like '" + bean.getLocation() + "%'");

					if (bean.getBankName() != null && bean.getBankName().length() > 0) {
						sql.append(" and BankName like '" + bean.getBankName() + "%'");
					}
					if (bean.getCashAvailable() != null && bean.getCashAvailable().length() > 0) {
						sql.append(" and CashAvailable like '" + bean.getCashAvailable() + "%'");
					}

					if (bean.getStatus() != null && bean.getStatus().length() > 0) {
						sql.append(" and Status like '" + bean.getStatus() + "%'");
					}

				}
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "st_atm";
	}

	@Override
	public ATMBean getBean() {
		return new ATMBean();
	}

}