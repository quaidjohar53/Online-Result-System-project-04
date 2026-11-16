package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class OnlineShoppingBean extends BaseBean {

	private int productId;
	private String productName;
	private double productPrice;
	private String category;
	private int stock;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String getValue() {
		return null;

	}

	@Override

	public void setResultset(ResultSet rs) {
		try {
			super.setResultset(rs);
			this.productId = rs.getInt("productId");
			this.productName = rs.getString("productName");
			this.productPrice = rs.getDouble("productPrice");
			this.category = rs.getString("category");
			this.stock = rs.getInt("stock");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
