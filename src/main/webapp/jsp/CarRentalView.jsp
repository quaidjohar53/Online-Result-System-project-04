
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.bean.CarRentalBean"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Car Rental</title>

<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>

<body class="bg-light">

	<%@ include file="Header.jsp"%>

	<%
	CarRentalBean bean = (CarRentalBean) request.getAttribute("bean");

	if (bean == null) {
		bean = new CarRentalBean();
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
							<i class="bi bi-car-front-fill"></i>
							<%=bean.getId() > 0 ? "Update Car Rental" : "Add Car Rental"%>
						</h3>

						<small class="text-muted"> Manage Car Rental Details </small>

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

						<!-- Car Rental Form -->
						<form action="<%=ORSView.CAR_RENTAL_CTL%>" method="post">

							<!-- Hidden Rental ID -->
							<input type="hidden" name="id"
								value="<%=DataUtility.getStringData(bean.getId())%>">

							<!-- Car Model -->
							<div class="mb-3">

								<label class="form-label fw-bold"> <i
									class="bi bi-car-front text-primary"></i> Car Model <span
									class="text-danger">*</span>
								</label> <input type="text" name="carModel" class="form-control"
									value="<%=DataUtility.getStringData(bean.getCarModel())%>"
									placeholder="Enter car model"> <small
									class="text-danger"> <%=ServletUtility.getErrorMessage("carModel", request)%>
								</small>

							</div>

							<!-- Customer Name -->
							<div class="mb-3">

								<label class="form-label fw-bold"> <i
									class="bi bi-person-fill text-success"></i> Customer Name <span
									class="text-danger">*</span>
								</label> <input type="text" name="customerName" class="form-control"
									value="<%=DataUtility.getStringData(bean.getCustomerName())%>"
									placeholder="Enter customer name"> <small
									class="text-danger"> <%=ServletUtility.getErrorMessage("customerName", request)%>
								</small>

							</div>

							<!-- Rent Per Day -->
							<div class="mb-3">

								<label class="form-label fw-bold"> <i
									class="bi bi-currency-rupee text-warning"></i> Rent Per Day <span
									class="text-danger">*</span>
								</label> <input type="number" name="rentPerDay" class="form-control"
									min="0" step="0.01" value="<%=bean.getRentPerDay()%>"
									placeholder="Enter rent per day"> <small
									class="text-danger"> <%=ServletUtility.getErrorMessage("rentPerDay", request)%>
								</small>

							</div>

							<!-- Number of Days -->
							<div class="mb-4">

								<label class="form-label fw-bold"> <i
									class="bi bi-calendar-check text-info"></i> Number of Days <span
									class="text-danger">*</span>
								</label> <input type="number" name="days" class="form-control" min="1"
									step="1" value="<%=bean.getDays()%>"
									placeholder="Enter number of days"> <small
									class="text-danger"> <%=ServletUtility.getErrorMessage("days", request)%>
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
