
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.bean.OnlineShoppingBean"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Shopping</title>

<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

</head>

<body class="bg-light">

	<%@ include file="Header.jsp"%>

	<%
	OnlineShoppingBean bean = (OnlineShoppingBean) request.getAttribute("bean");

	if (bean == null) {
		bean = new OnlineShoppingBean();
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

							<i class="bi bi-cart-check-fill"></i>

							<%=bean != null && bean.getProductId() > 0 ? "Update Product" : "Add Product"%>

						</h3>

						<small class="text-muted"> Manage Online Shopping Products
						</small>

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

						<!-- Product Form -->
						<form action="<%=ORSView.Online_Shopping_CTL%>" method="post">
							<!-- Hidden Product ID -->
							<input type="hidden" name="id"
								value="<%=DataUtility.getStringData(bean.getProductId())%>">

							<!-- Product Name -->
							<div class="mb-3">

								<label class="form-label fw-bold"> <i
									class="bi bi-box-seam text-primary"></i> Product Name <span
									class="text-danger">*</span>
								</label> <input type="text" name="productName" class="form-control"
									value="<%=DataUtility.getStringData(bean.getProductName())%>"
									placeholder="Enter product name"> <small
									class="text-danger"> <%=ServletUtility.getErrorMessage("productName", request)%>
								</small>

							</div>

							<!-- Product Price -->
							<div class="mb-3">

								<label class="form-label fw-bold"> <i
									class="bi bi-currency-rupee text-success"></i> Product Price <span
									class="text-danger">*</span>
								</label> <input type="number" name="productPrice" class="form-control"
									step="0.01" min="0"
									value="<%=DataUtility.getStringData(bean.getProductPrice())%>"
									placeholder="Enter product price"> <small
									class="text-danger"> <%=ServletUtility.getErrorMessage("productPrice", request)%>
								</small>

							</div>

							<!-- Category -->
							<div class="mb-3">

								<label class="form-label fw-bold"> <i
									class="bi bi-tags-fill text-warning"></i> Category <span
									class="text-danger">*</span>
								</label> <input type="text" name="category" class="form-control"
									value="<%=DataUtility.getStringData(bean.getCategory())%>"
									placeholder="Enter product category"> <small
									class="text-danger"> <%=ServletUtility.getErrorMessage("category", request)%>
								</small>

							</div>

							<!-- Stock -->
							<div class="mb-4">

								<label class="form-label fw-bold"> <i
									class="bi bi-boxes text-info"></i> Stock <span
									class="text-danger">*</span>
								</label> <input type="number" name="stock" class="form-control" min="0"
									value="<%=DataUtility.getStringData(bean.getStock())%>"
									placeholder="Enter available stock"> <small
									class="text-danger"> <%=ServletUtility.getErrorMessage("stock", request)%>
								</small>

							</div>

							<!-- Save Button -->
							<div class="text-center">

								<input type="submit" name="operation"
									class="btn btn-primary px-5 py-2"
									value="<%=bean.getProductId() > 0 ? "Update" : BaseCtl.OP_SAVE%>">

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
