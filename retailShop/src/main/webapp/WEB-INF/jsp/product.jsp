<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!doctype html>
        <html lang="en">

        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
                integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
                crossorigin="anonymous">
            <link rel="stylesheet"
                href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">

            <link rel="stylesheet" href="style/design.css">
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
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand"> R-Store</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">

                        <li class="nav-item active"><a class="nav-link" href="/product">Products
                                <span class="badge badge-danger">New</span>
                            </a></li>
                        <li class="nav-item"><a class="nav-link" href="/cart">Cart</a>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="/wishlist">Wishlist</a>
                        </li>
                        <li class="nav-item "><a class="nav-link" href="/logout">LogOut</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <button type="button" class="btn btn-warning">${Name}</button>
                        </li>
                    </ul>
                    <form class="d-flex" method="post">

                        <input class="form-control me-2" type="search" placeholder="name / id" name="search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </nav>
            <div class="album py-5 bg-light">
                <div class="container">
                    <div class="row">
                        <c:forEach items="${listOfProducts}" var="product">
                            <div class="col-md-4">
                                <div class="card mb-4 shadow-sm" id="cards">
                                    <div class="card" style="" 100%" height="300">
                                        <div class="card-body">
                                            <h5 class="card-title">${product.name}
                                                : ${product.id}
											
                                                <p style="color: red;" class="float-right">

                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                        fill="currentColor" class="bi bi-star" viewBox="0 0 16 16">
                                                        <path
                                                            d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z" />
                                                    </svg> ${product.rating}
                                                </p>

                                            </h5>

                                            <p class="card-text">${product.description}</p>
                                        </div>
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item"><b style="color: Black;">
                                                    Rs. ${product.price} </b></li>

                                           <!--  <li class="list-group-item"> -->

                                                <li class="list-group-item"><a href="/add-towish?id=${product.id}"
                                                    class="btn btn-primary">Add to Wishlist</a></li>
                                                <!-- Modal -->
                                                <!-- <div class="modal fade" id="exampleModal" tabindex="-1"
                                                    aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel"></h5>
                                                                <button type="button" class="btn-close"
                                                                    data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <h5 class="modal-title" id="exampleModalLabel">Added
                                                                    to Wishlist</h5>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary"
                                                                    data-bs-dismiss="modal">Close</button>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div> -->

                                            <!-- </li>
                                            </li> -->
                                            <li class="list-group-item"><a href="/add-tocart?id=${product.id}"
                                                    class="btn btn-success">Add
                                                    to Cart </a> </li>

                                            <li class="list-group-item">
                                                <form name="rating" action="/add-rating?id=${product.id}" class="d-flex" method="post">
                                                    <input class="form-control me-2" type="search" placeholder="Rating"
                                                        name="rating">
                                                    <button class="btn btn-outline-danger" type="submit">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                            fill="currentColor" class="bi bi-star" viewBox="0 0 16 16">
                                                            <path
                                                                d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z" />
                                                        </svg>
                                                    </button>
                                                </form>
                                            </li>

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

            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
                integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
                crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
                crossorigin="anonymous"></script>
        </body>

        </html>