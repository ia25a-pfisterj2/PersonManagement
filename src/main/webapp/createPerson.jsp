<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Person erstellen</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link href="style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<h1>Person erstellen</h1>
				</div>
			</div>
			<br>
			<form action="persons" method="post">
				<div class="row">
					<div class="col-12">
						<label for="vorname">Vorname:</label><br>
						<input type="text" name="vorname" id="vorname" required>
					</div>
				</div><br>
				
				<div class="row">
					<div class="col-12">
						<label for="nachname">Nachname:</label><br>
						<input type="text" name="nachname" id="nachname" required>
					</div>
				</div><br>
				
				<div class="row">
					<div class="col-12">
						<label for="geburtsdatum">Geburtsdatum:</label><br>
						<input type="date" min="0001-01-01" max="9999-12-31" name="geburtsdatum" id="geburtsdatum" required>
					</div>
				</div><br>
				
				<input type="hidden" name="delete" id="delete" value="insert">
				
				<div class="row">
					<div class="col-3 center btn">
						<input type="submit" value="Erstellen" class="marg">
					</div>
					<div class="col-3 center btn">
						<input type="reset" value="Löschen" class="marg">
					</div>
				</div>
			</form>
			<br><br>
			<div class="row">
				<div class="col-12">
					<a href="persons">
				    	<button>Personen Auflisten</button>
					</a>
				</div>
			</div>
		</div>
	</body>
</html>