package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class ATMBean extends BaseBean {

	private String Location;
	private String BankName;
	private String cashAvailable;
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

	public String getCashAvailable() {
		return cashAvailable;
	}

	public void setCashAvailable(String cashAvailable) {
		this.cashAvailable = cashAvailable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setResultSet(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setId(rs.getLong("ID"));
			this.setLocation(rs.getString("Location"));
			this.setBankName(rs.getString("BankName"));
			this.setCashAvailable(rs.getString("CashAvailable"));
			this.setStatus(rs.getString("Status"));
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
