
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.bean.CarRentalBean"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Car Rental List</title>

<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

</head>

<body class="bg-light">

	<%@ include file="Header.jsp"%>

	<%
	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;

	List<CarRentalBean> list = ServletUtility.getList(request);
	Iterator<CarRentalBean> it = list.iterator();

	String suc = ServletUtility.getSuccessMessage(request);
	String err = ServletUtility.getErrorMessage(request);
	%>

	<form action="<%=ORSView.CAR_RENTAL_LIST_CTL%>" method="post">

		<!-- Main Container -->
		<div class="container pt-5" style="padding-bottom: 100px !important;">

			<div class="card shadow-lg border-0">

				<!-- Card Header -->
				<div class="card-header bg-white text-center border-bottom py-4">

					<h2 class="text-primary fw-bold mb-0">
						<i class="bi bi-car-front-fill"></i> Car Rental List
					</h2>

					<small class="text-muted"> Manage Car Rental Details </small>

				</div>

				<!-- Card Body -->
				<div class="card-body p-4">

					<!-- Success Message -->
					<%
					if (suc != null && suc.length() > 0) {
					%>
					<div class="alert alert-success text-center py-2">

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
					<div class="alert alert-danger text-center py-2">

						<i class="bi bi-exclamation-triangle-fill"></i>
						<%=err%>

					</div>
					<%
					}
					%>

					<!-- Hidden Pagination Fields -->
					<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
						type="hidden" name="pageSize" value="<%=pageSize%>">

					<!-- Search Section -->
					<div class="row g-2 mb-4">

						<div class="col-md-10">

							<input type="text" name="carModel" class="form-control"
								placeholder="Search by Car Model">

						</div>

						<div class="col-md-2">

							<input type="submit" name="operation"
								class="btn btn-primary w-100" value="<%=BaseCtl.OP_SEARCH%>">

						</div>

					</div>

					<!-- Car Rental Table -->
					<div class="table-responsive">

						<table class="table table-bordered table-hover align-middle">

							<thead class="table-primary text-center">

								<tr>

									<th><input type="checkbox" id="selectAll"
										onclick="document.querySelectorAll('input[name=ids]').forEach(e => e.checked=this.checked)">
									</th>

									<th>S.No.</th>

									<th>Car Model</th>

									<th>Customer Name</th>

									<th>Rent Per Day</th>

									<th>Days</th>

									<th>Total Rent</th>

									<th>Edit</th>

								</tr>

							</thead>

							<tbody>

								<%
								while (it.hasNext()) {

									CarRentalBean bean = it.next();
								%>

								<tr>

									<!-- Select Checkbox -->
									<td class="text-center"><input type="checkbox"
										class="form-check-input" name="ids" value="<%=bean.getId()%>">

									</td>

									<!-- Serial Number -->
									<td class="text-center"><%=index++%></td>

									<!-- Car Model -->
									<td><%=bean.getCarModel()%></td>

									<!-- Customer Name -->
									<td><%=bean.getCustomerName()%></td>

									<!-- Rent Per Day -->
									<td class="text-end">₹ <%=bean.getRentPerDay()%>
									</td>

									<!-- Days -->
									<td class="text-center"><%=bean.getDays()%></td>



									<!-- Edit -->
									<td class="text-center"><a
										href="<%=ORSView.CAR_RENTAL_CTL%>?id=<%=bean.getId()%>"
										class="btn btn-outline-primary btn-sm"> <i
											class="bi bi-pencil-square"></i> Edit

									</a></td>

								</tr>

								<%
								}
								%>

							</tbody>

						</table>

					</div>

					<!-- Pagination and Delete Buttons -->
					<div class="d-flex justify-content-between align-items-center mt-3">

						<!-- Previous -->
						<div>

							<input type="submit" name="operation" class="btn btn-secondary"
								<%=pageNo == 1 ? "disabled" : ""%>
								value="<%=BaseCtl.OP_PREVIOUS%>">

						</div>

						<!-- Delete -->
						<div>

							<input type="submit" name="operation" class="btn btn-danger"
								value="<%=BaseCtl.OP_DELETE%>">

						</div>

						<!-- Next -->
						<div>

							<input type="submit" name="operation" class="btn btn-secondary"
								<%=list.size() < pageSize ? "disabled" : ""%>
								value="<%=BaseCtl.OP_NEXT%>">

						</div>

					</div>

				</div>
			</div>
		</div>

	</form>

	<%@ include file="Footer.jsp"%>

</body>
</html>
