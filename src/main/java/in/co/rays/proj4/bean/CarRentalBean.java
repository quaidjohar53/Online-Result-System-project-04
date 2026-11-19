package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class CarRentalBean extends BaseBean {

	private int rentalId;
	private String carModel;
	private String cutomerName;
	private double rentPerDay;
	private int days;

	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCustomerName() {
		return cutomerName;
	}

	public void setCustomerName(String cutomerName) {
		this.cutomerName = cutomerName;
	}

	public double getRentPerDay() {
		return rentPerDay;
	}

	public void setRentPerDay(double rentPerDay) {
		this.rentPerDay = rentPerDay;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	
	@Override
	public void setResultset (ResultSet rs) {
		try {
			super.setResultset(rs);
			this.setRentalId(rs.getInt("rental_id"));
			this.setCarModel(rs.getString("car_model"));
			this.setCustomerName(rs.getString("customer_name"));
			this.setRentPerDay(rs.getDouble("rent_per_day"));
			this.setDays(rs.getInt("days"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
