package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class ATMBean extends BaseBean {

	private String Location;
	private String BankName;
	private double cashAvailable;
	private String status;

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

	public double getCashAvailable() {
		return cashAvailable;
	}

	public void setCashAvailable(double cashAvailable) {
		this.cashAvailable = cashAvailable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);

			this.setId(rs.getLong("id"));
			this.setLocation(rs.getString("location"));
			this.setBankName(rs.getString("bank_name"));
			this.setCashAvailable(rs.getDouble("cash_available"));
			this.setStatus(rs.getString("status"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
