package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.OnlineShoppingBean;
import in.co.rays.proj4.model.OnlineShoppingModel;

public class OnlineShoppingReportCtl extends BaseReportCtl <OnlineShoppingBean> {

	@Override
	public String getView() {
		return ORSView.ONLINE_SHOPPING_REPORT_VIEW;
	}

	@Override
	public String getCompiledReportKey() {
		return "Online_Shopping_LIST_COMPILED_REPORT";
	}

	@Override
	public List <OnlineShoppingBean> getList() {
		OnlineShoppingModel model = new OnlineShoppingModel();
		@SuppressWarnings("unchecked")
		List <OnlineShoppingBean> OnlineShopping = model.list();
		// TODO  Auto-generated method stub
		return OnlineShopping ;
	}

}
