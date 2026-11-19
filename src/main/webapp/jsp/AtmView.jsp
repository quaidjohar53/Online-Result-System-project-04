
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.bean.ATMBean"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ATM</title>

<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

</head>

<body class="bg-light">

	<%@ include file="Header.jsp"%>

	<%
	ATMBean bean = (ATMBean) request.getAttribute("bean");

	if (bean == null) {
		bean = new ATMBean();
		request.setAttribute("bean", bean);
	}

	String suc = ServletUtility.getSuccessMessage(request);
	String err = ServletUtility.getErrorMessage(request);
	%>

	<!-- Main Container -->
	<div class="container pt-5" style="padding-bottom: 100px !important;">

		<div class="row justify-content-center">

			<div class="col-md-10 col-lg-7">

				<!-- Card -->
				<div class="card shadow-lg border-0">

					<!-- Card Header -->
					<div class="card-header bg-white text-center py-3">

						<h3 class="mb-1 text-primary">

							<i class="bi bi-bank"></i>

							<%=bean.getId() > 0 ? "Update ATM" : "Add ATM"%>

						</h3>

						<small class="text-muted"> Manage ATM Details </small>

					</div>

					<!-- Card Body -->
					<div class="card-body p-4">

						<!-- Success Message -->
						<%
						if (suc != null && suc.length() > 0) {
						%>

						<div class="alert alert-success">
							<i class="bi bi-check-circle-fill"></i>
							<%=suc%>
						</div>

						<%
						}
						%>

						<!-- Error Message -->
						<%
						if (err != null && err.length() > 0) {
						%>

						<div class="alert alert-danger">
							<i class="bi bi-exclamation-triangle-fill"></i>
							<%=err%>
						</div>

						<%
						}
						%>

						<!-- ATM Form -->
						<form action="<%=ORSView.ATM_CTL%>" method="post">

							<!-- Hidden ATM ID -->
							<input type="hidden" name="id"
								value="<%=DataUtility.getStringData(bean.getId())%>">

							<!-- Location -->
							<div class="mb-3">

								<label class="form-label fw-bold"> <i
									class="bi bi-geo-alt-fill text-primary"></i> Location <span
									class="text-danger">*</span>
								</label> <input type="text" name="location" class="form-control"
									value="<%=DataUtility.getStringData(bean.getLocation())%>"
									placeholder="Enter ATM location"> <small
									class="text-danger"> <%=ServletUtility.getErrorMessage("location", request)%>
								</small>

							</div>

							<!-- Bank Name -->
							<div class="mb-3">

								<label class="form-label fw-bold"> <i
									class="bi bi-bank text-success"></i> Bank Name <span
									class="text-danger">*</span>
								</label> <input type="text" name="bankName" class="form-control"
									value="<%=DataUtility.getStringData(bean.getBankName())%>"
									placeholder="Enter bank name"> <small
									class="text-danger"> <%=ServletUtility.getErrorMessage("bankName", request)%>
								</small>

							</div>

							<!-- Cash Available -->
							<div class="mb-3">

								<label class="form-label fw-bold"> <i
									class="bi bi-currency-rupee text-warning"></i> Cash Available <span
									class="text-danger">*</span>
								</label> <input type="number" name="cashAvailable" class="form-control"
									min="0" step="0.01"
								value="<%=DataUtility.getStringData(bean.getCashAvailable())%>"
									placeholder="Enter available cash"> <small
									class="text-danger"> <%=ServletUtility.getErrorMessage("cashAvailable", request)%>
								</small>

							</div>

							<!-- Status -->
							<div class="mb-4">

								<label class="form-label fw-bold"> <i
									class="bi bi-toggle-on text-info"></i> Status <span
									class="text-danger">*</span>
								</label> <select name="status" class="form-control">

									<option value="">-- Select Status --</option>

									<option value="Active"
										<%="Active".equals(bean.getStatus()) ? "selected" : ""%>>
										Active</option>

									<option value="Inactive"
										<%="Inactive".equals(bean.getStatus()) ? "selected" : ""%>>
										Inactive</option>

								</select> <small class="text-danger"> <%=ServletUtility.getErrorMessage("status", request)%>
								</small>

							</div>

							<!-- Save Button -->
							<div class="text-center">

								<input type="submit" name="operation"
									class="btn btn-primary px-5 py-2"
									value="<%=bean.getId() > 0 ? "Update" : BaseCtl.OP_SAVE%>">

							</div>

						</form>

					</div>

				</div>

			</div>

		</div>

	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>
