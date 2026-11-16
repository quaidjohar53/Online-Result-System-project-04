package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.OnlineShoppingBean;
import in.co.rays.proj4.model.OnlineShoppingModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/OnlineShoppinglistCtl")

public class OnlineShoppinglistCtl extends BaseListCtl<OnlineShoppingBean, OnlineShoppingModel> {

	@Override

	protected OnlineShoppingBean populateBean(HttpServletRequest request) {
		OnlineShoppingBean bean = new OnlineShoppingBean();
		bean.setProductName(DataUtility.getString(request.getParameter("productName")));
		bean.setCategory(DataUtility.getString(request.getParameter("category")));

		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.ONLINE_SHOPPING_LIST_VIEW;
	}

	@Override
	protected OnlineShoppingModel getModel() {
		return new OnlineShoppingModel();
	}

}
