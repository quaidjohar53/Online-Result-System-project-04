package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CarRentalBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CarRentalModel extends BaseModel<CarRentalBean> {

	@Override
	public long add(CarRentalBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		long pk = 0;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			pk = nextPK();

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO " + getTable() + " VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCarModel());
			pstmt.setString(3, bean.getCustomerName());
			pstmt.setDouble(4, bean.getRentPerDay());
			pstmt.setInt(5, bean.getDays());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDateTime());
			pstmt.setTimestamp(9, bean.getModifiedDateTime());

			pstmt.executeUpdate();

			conn.commit();

			bean.setRentalId((int) pk);

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	@Override
	public void update(CarRentalBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable()
					+ "set car_model=?, customer_name=?, rent_per_day=?, days=?, modified_by=?, created_datetime=?, modified_datetime=? where rental_id=?");

			pstmt.setString(1, bean.getCarModel());
			pstmt.setString(2, bean.getCustomerName());
			pstmt.setDouble(3, bean.getRentPerDay());
			pstmt.setInt(4, bean.getDays());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setString(6, bean.getModifiedDateTime().toString());
			pstmt.setLong(7, bean.getRentalId());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	@Override
	public String getWhereClause(CarRentalBean bean) {
		StringBuffer sql = new StringBuffer(" where 1=1 ");

		if (bean != null) {
			if (bean.getRentalId() > 0) {
				sql.append(" and rental_id = " + bean.getRentalId());
			}
			if (bean.getCarModel() != null && bean.getCarModel().length() > 0) {
				sql.append(" and car_model like '" + bean.getCarModel() + "%'");
			}
			if (bean.getCustomerName() != null && bean.getCustomerName().length() > 0) {
				sql.append(" and customer_name like '" + bean.getCustomerName() + "%'");
			}
		}

		return sql.toString();
	}

	@Override
	public String getTable() {
		return "car_rental";
	}

	@Override
	public CarRentalBean getBean() {
		return new CarRentalBean();
	}
}
