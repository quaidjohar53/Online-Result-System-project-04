package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.CarRentalBean;
import in.co.rays.proj4.model.CarRentalModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/CarRentalCtl")

public class CarRentalCtl extends BaseCtl<CarRentalBean, CarRentalModel> {

	protected boolean validate(HttpServletRequest request) {

		Boolean pass = true;

		if (DataValidator.isNull(request.getParameter("carModel"))) {
			request.setAttribute("carModel", "Car Model is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("customerName"))) {
			request.setAttribute("customerName", "Customer Name is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("rentPerDay"))) {
			request.setAttribute("rentPerDay", "Rent Per Day is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("days"))) {
			request.setAttribute("days", "Days is required");
			pass = false;
		}

		return pass;
	}

	@Override

	public CarRentalBean populateBean(HttpServletRequest request) {

		CarRentalBean bean = new CarRentalBean();

		bean.setRentalId(DataUtility.getInt(request.getParameter("rentalId")));
		bean.setCarModel(DataUtility.getString(request.getParameter("carModel")));
		bean.setCustomerName(DataUtility.getString(request.getParameter("customerName")));
		bean.setRentPerDay(DataUtility.getDouble(request.getParameter("rentPerDay")));
		bean.setDays(DataUtility.getInt(request.getParameter("days")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	public String getView() {
		return ORSView.CAR_RENTAL_VIEW;
	}

	@Override
	protected CarRentalModel getModel() {

		return new CarRentalModel();
	}

}
