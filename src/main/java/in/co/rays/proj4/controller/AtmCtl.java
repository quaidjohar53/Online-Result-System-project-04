
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

		if (DataValidator.isNull(request.getParameter("location"))) {
			request.setAttribute("location", "Location is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("bankName"))) {
			request.setAttribute("bankName", "Bank Name is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("cashAvailable"))) {
			request.setAttribute("cashAvailable", "Cash Available is required");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", "Status is required");
			pass = false;
		}

		return pass;
	}

	@Override
	protected ATMBean populateBean(HttpServletRequest request) {

		ATMBean bean = new ATMBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setLocation(DataUtility.getString(request.getParameter("location")));
		bean.setBankName(DataUtility.getString(request.getParameter("bankName")));
		bean.setCashAvailable(DataUtility.getDouble(request.getParameter("cashAvailable")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));

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
