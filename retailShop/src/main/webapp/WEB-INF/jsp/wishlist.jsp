<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">

<script src="public/script.js"></script>

<title>R-Store</title>
</head>

	<%
	 response.setHeader("cache-control", "no-cache , no-store , must-revalidate");
    response.setHeader("pragma", "no-cache");
    response.setDateHeader("Expires", 0);

        if (session.getAttribute("name") == null || session.getAttribute("token") == null) {
    %>
    <c:redirect url="/error" />
    <%
        }
    %>

<body>
	<!-- nav bar  -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand"> R-Store</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">

				<li class="nav-item "><a class="nav-link" href="/product">Products
						<span class="badge badge-danger">New</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="/cart">Cart</a>
				</li>
				<li class="nav-item active"><a class="nav-link"
					href="/wishlist">Wish List</a></li>
				<li class="nav-item "><a class="nav-link" href="/login">LogOut</a>
				</li>

			</ul>
		</div>
	</nav>

	<!-- products  -->


	<div class="album py-5 bg-light">
		<div class="container">

			<div class="row">
				<c:forEach items="${wishes}" var="wish">
					<div class="col-md-4">
						<div class="card mb-4 shadow-sm">
							<div class="card" style="" 100%" height="300">
								<div class="card-body">
									<h5 class="card-title">${wish.vendorName}
										<i style="color: rgb(92, 89, 87);">  ${wish.rating} </i>
									</h5>
									<p class="card-text">${wish.deliveryCharge}</p>
								</div>
								<ul class="list-group list-group-flush">
									<li class="list-group-item">
										<form action="" method="post">
											<div class="stars">
												<i class="lar la-star" data-value="1"></i><i
													class="lar la-star" data-value="2"></i><i
													class="lar la-star" data-value="3"></i><i
													class="lar la-star" data-value="4"></i><i
													class="lar la-star" data-value="5"></i>
											</div>
										</form>
									</li>
									</li>
									<li class="list-group-item"><a
										href="/add-tocart?id=${wish.vendorId}" class="btn btn-success">Add
											to Cart</a></li>

								</ul>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>
	</div>



	<footer class="container">
		<p class="float-right">
			<a href="#">Back to top</a>
		</p>
		<p>© 2020-2021 R-Store, Inc..</p>
	</footer>








	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
</body>

</html>