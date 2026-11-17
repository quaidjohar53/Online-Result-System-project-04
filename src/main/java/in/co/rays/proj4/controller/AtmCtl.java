package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.ATMBean;
import in.co.rays.proj4.model.AtmModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/AtmCtl")
public class AtmCtl extends BaseCtl<ATMBean, AtmModel> {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("Location"))) {
			request.setAttribute("Location", "Location is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("BankName"))) {
			request.setAttribute("BankName", "Bank Name is required");
			pass = false;

		}

		if (DataValidator.isNull(request.getParameter("CashAvailable"))) {
			request.setAttribute("CashAvailable", "Cash Available is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Status"))) {
			request.setAttribute("Status", "Status is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("ID"))) {
			request.setAttribute("ID", "ID is required");
			pass = false;
		}

		return pass;

	}

	@Override
	protected ATMBean populateBean(HttpServletRequest request) {
		ATMBean bean = new ATMBean();

		bean.setId(DataUtility.getLong(request.getParameter("ID")));
		bean.setLocation(DataUtility.getString(request.getParameter("Location")));
		bean.setBankName(DataUtility.getString(request.getParameter("BankName")));
		bean.setCashAvailable(DataUtility.getString(request.getParameter("CashAvailable")));
		bean.setStatus(DataUtility.getString(request.getParameter("Status")));

		populateDTO(bean, request);

		return bean;
	}

	@Override
	public String getView() {
		return ORSView.ATM_VIEW;
	}

	@Override
	protected AtmModel getModel() {
		return new AtmModel();
	}

}
