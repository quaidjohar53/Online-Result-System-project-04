package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.ATMBean;
import in.co.rays.proj4.model.AtmModel;

public class AtmReportCtl extends BaseReportCtl<ATMBean> {

	@Override
	public String getView() {
		return ORSView.ATM_REPORT_VIEW;
	}

	@Override
	public String getCompiledReportKey() {
		return "ATM_LIST_COMPILED_REPORT";
	}

	@Override
	public java.util.List<ATMBean> getList() {
		AtmModel model = new AtmModel();
		@SuppressWarnings("unchecked")
		java.util.List<ATMBean> atm = model.list();
		return atm;
	}

}
