package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.ATMBean;
import in.co.rays.proj4.model.AtmModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/AtmListCtl")
public class AtmListCtl extends BaseListCtl<ATMBean, AtmModel> {

	@Override
	protected ATMBean populateBean(HttpServletRequest request) {
		ATMBean bean = new ATMBean();
		bean.setLocation(DataUtility.getString(request.getParameter("Location")));
		bean.setBankName(DataUtility.getString(request.getParameter("BankName")));
		return bean;
	}

	@Override
	protected String getView() {
		return ORSView.ATM_LIST_VIEW;
	}

	@Override
	protected AtmModel getModel() {
		return new AtmModel();
	}
	
	

}
