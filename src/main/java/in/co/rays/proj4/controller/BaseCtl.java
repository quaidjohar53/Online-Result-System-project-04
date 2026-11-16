package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.BaseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.MessageSource;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class BaseCtl<B extends BaseBean, M extends BaseModel> extends HttpServlet {

	public static final String OP_SAVE = "Save";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	public static final String OP_BACK = "Back";
	public static final String OP_LOG_OUT = "Logout";

	public static final String HAS_ERROR = "haserror";
	public static final String MESSAGE = "message";

	public static final String MSG_SUCCESS = "success";

	public static final String MSG_ERROR = "error";

	private static Logger log = Logger.getLogger(BaseCtl.class);

	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	protected void preload(HttpServletRequest request) {
	}

	protected B populateBean(HttpServletRequest request) {
		return null;
	}

	protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {

		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;

		UserBean userbean = (UserBean) request.getSession().getAttribute("user");

		if (userbean == null) {
			// If record is created without login
			createdBy = "root";
			modifiedBy = "root";
		} else {
			modifiedBy = userbean.getLogin();
			// If record is created first time
			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}
		}
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

		if (cdt > 0) {
			dto.setCreatedDateTime(DataUtility.getTimestamp(cdt));
		} else {
			dto.setCreatedDateTime(DataUtility.getCurrentTimestamp());
		}

		dto.setModifiedDateTime(DataUtility.getCurrentTimestamp());

		return dto;
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Load the necessary preloaded data for displaying in an HTML form
		preload(request);
		getMessageSource(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		// Handle cancel operation
		if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(getView(), request, response);
			return;
		}

		BaseBean bean = populateBean(request);

		// Perform validation if form is submitted using POST method
		if ("POST".equals(request.getMethod()) && !validate(request)) {
			ServletUtility.setBean(bean, request);
			ServletUtility.forward(getView(), request, response);
			return;
		}

		try {
			super.service(request, response);
		} catch (DuplicateRecordException e) {
			ServletUtility.setBean(bean, request);
			ServletUtility.setErrorMessage(e.getMessage(), request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			e.printStackTrace();
//			System.out.println(e.getMessage() + " ============== database is down ===================");
			e.printStackTrace();
			ServletUtility.setPageNo(1, request);
			ServletUtility.setPageSize(10, request);
			ServletUtility.setList(new ArrayList<BaseBean>(), request);
			request.setAttribute("nextList", new ArrayList<BaseBean>());
			ServletUtility.setErrorMessage("database service is down", request);
			ServletUtility.forward(getView(), request, response);
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0 || op != null) {
			BaseBean bean = getModel().findByPK(id);
			ServletUtility.setBean(bean, request);
		}

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("CollegeCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));

		B bean = populateBean(request);

		if (id > 0) {
			getModel().update(bean);
			ServletUtility.setSuccessMessage("Data is successfully updated", request);
		} else {
			long pk = getModel().add(bean);
			ServletUtility.setSuccessMessage("Data is successfully saved", request);
			bean.setId(pk);
		}
		ServletUtility.setBean(bean, request);
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	protected abstract String getView();

	protected abstract M getModel();

	public MessageSource getMessageSource(HttpServletRequest request) {

		// MessageSource messagesource = (MessageSource)
		// request.getServletContext().getAttribute("messagesource");
		MessageSource messagesource = MessageSource.getInstance();
		return messagesource;
	}
}