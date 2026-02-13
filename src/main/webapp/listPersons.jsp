<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Personen auflisten</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link href="style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<h1>Personen auflisten</h1>
				</div>
			</div>
			<br>
			<c:choose>
			    <c:when test="${empty personen}">
			        <div class="row">
						<div class="col-12">
							<p>Keine Personen vorhanden</p>
						</div>
					</div>
			    </c:when>
			    <c:otherwise>
			        <div class="row">
						<div class="col-2 align">
							<h2>Vorname</h2>
						</div>
						<div class="col-2 align pad">
							<h2>Nachname</h2>
						</div>
						<div class="col-4 align">
							<h2>Geburtsdatum</h2>
						</div>
						<div class="col-2 align">
							<h2>Löschen</h2>
						</div>
						<div class="col-2 align">
							<h2>Update</h2>
						</div>
					</div>
					<c:set value="${0}" var="i"/>
					<c:forEach var = "item" items="${personen}">
						<div class="row">
							<div class="col-2 align break">
								<p><c:out value = "${item.vorname}"/></p>
							</div>
							<div class="col-2 align break">
								<p><c:out value = "${item.nachname}"/></p>
							</div>
							<div class="col-4 align break">
								<p><c:out value = "${item.geburtsdatum}"/></p>
							</div>
							<div class="col-2 align">
							<!--
								<form action="persons" method="post">
									<input type="hidden" name="delete" id="delete" value="delete">
									<input type="hidden" name="id" id="id" value="${i}">
									<input type="submit" value="Löschen">
								</form>
							-->
								<button class="delete-button" data-id="${item.getUuid()}">Löschen</button>
							</div>
							<div class="col-2 align">
								<form action="persons" method="post">
									<input type="hidden" name="delete" id="delete" value="transfer">
									<input type="hidden" name="vorname" id="vorname" value="${item.vorname}">
									<input type="hidden" name="nachname" id="nachname" value="${item.nachname}">
									<input type="hidden" name="geburtsdatum" id="geburtsdatum" value="${item.geburtsdatum}">
									<input type="hidden" name="id" id="id" value="${item.getUuid()}">
									<input type="submit" value="Updaten" class="update-button">
								</form>
							</div>
						</div>
						<c:set value="${i+1}" var="i"/>
					</c:forEach>
			    </c:otherwise>
			</c:choose>
				
			<br>
			<div class="row">
				<div class="col-12">
					<a href="persons">
						<button>Liste neu Laden</button>
					</a>
				</div>
			</div>
			<br><br>
			<div class="row">
				<div class="col-12">
					<a href="createPerson.jsp">
						<button>Person erstellen</button>
					</a>
				</div>
			</div>	
		</div>
		<script>
			const buttons = document.querySelectorAll(".delete-button");
			
			buttons.forEach(button => {
				button.addEventListener("click", function() {
					const index = button.dataset.id;
					
					fetch("persons?index=" + index, {
						method: "DELETE"
					})
					.then(response => {
						if(response.ok){
							location.reload();
						}
					})
					.catch(error => console.error("Fehler: ", error));
				});
			});
		</script>
	</body>
</html>