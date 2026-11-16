package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.OnlineShoppingBean;
import in.co.rays.proj4.model.OnlineShoppingModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/OnlineShoppingCtl")

public class OnlineShoppingCtl extends BaseCtl<OnlineShoppingBean, OnlineShoppingModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("productName"))) {
			request.setAttribute("productName", "Product Name is require");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("productPrice"))) {
			request.setAttribute("productPrice", "Product Price is require");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("category"))) {
			request.setAttribute("category", "Category is require");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("stock"))) {
			request.setAttribute("stock", "Stock is require");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("productId"))) {
			request.setAttribute("productId", "Product Id is require");
			pass = false;
		}

		return pass;
	}

	@Override

	protected OnlineShoppingBean populateBean(HttpServletRequest request) {

		OnlineShoppingBean bean = new OnlineShoppingBean();

		bean.setProductId(DataUtility.getInt(request.getParameter("productId")));
		bean.setProductName(DataUtility.getString(request.getParameter("productName")));
		bean.setProductPrice(DataUtility.getDouble(request.getParameter("productPrice")));
		bean.setCategory(DataUtility.getString(request.getParameter("category")));
		bean.setStock(DataUtility.getInt(request.getParameter("stock")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.ONLINE_SHOPPING_VIEW;

	}

	@Override
	protected OnlineShoppingModel getModel() {
		return new OnlineShoppingModel();
	}

}
