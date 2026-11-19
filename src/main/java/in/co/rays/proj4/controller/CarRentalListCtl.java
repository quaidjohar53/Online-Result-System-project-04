package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CarRentalBean;
import in.co.rays.proj4.model.CarRentalModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/CarRentalListCtl")

public class CarRentalListCtl extends BaseListCtl<CarRentalBean, CarRentalModel> {

	protected CarRentalBean populateBean(HttpServletRequest request) {
		CarRentalBean bean = new CarRentalBean();
		bean.setCarModel(DataUtility.getString(request.getParameter("carModel")));
		bean.setCustomerName(DataUtility.getString(request.getParameter("customerName")));
		return bean;
	}

	@Override
	public String getView() {
		return ORSView.CAR_RENTAL_LIST_VIEW;
	}

	@Override
	protected CarRentalModel getModel() {
		return new CarRentalModel();
	}

}
