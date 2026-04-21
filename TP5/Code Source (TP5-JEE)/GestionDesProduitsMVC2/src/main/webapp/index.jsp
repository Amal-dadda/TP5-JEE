<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Produit, model.User" %>
<%
    model.User currentUser = (model.User) session.getAttribute("currentUser");
    boolean isAdmin = currentUser.getRole().equals("admin");
    Produit produitEdit = (Produit) request.getAttribute("produitEdit");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des produits</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>



<div class="container col-8">

    <% if (request.getAttribute("erreur") != null) { %>
        <div class="alert alert-danger">
            <%= request.getAttribute("erreur") %>
        </div>
    <% } %>


    <% if (isAdmin) { %>
    <form action="<%= request.getContextPath() %>/app" method="POST">

        <input type="hidden" name="action"
               value="<%= produitEdit != null ? "update" : "add" %>">

        <% if (produitEdit != null) { %>
            <input type="hidden" name="id" value="<%= produitEdit.getId() %>">
        <% } %>

        <legend><%= produitEdit != null ? "Modifier" : "Ajouter" %> un produit</legend>

        <div class="mb-1">
            <label class="form-label">Nom :</label>
            <input type="text" class="form-control" name="nom"
                   value="<%= produitEdit != null ? produitEdit.getNom() : "" %>" required>
        </div>
        <div class="mb-1">
            <label class="form-label">Prix :</label>
            <input type="text" class="form-control" name="prix"
                   value="<%= produitEdit != null ? produitEdit.getPrix() : "" %>" required>
        </div>
        <div class="mb-1">
            <label class="form-label">Description :</label>
            <input type="text" class="form-control" name="description"
                   value="<%= produitEdit != null ? produitEdit.getDescription() : "" %>" required>
        </div>

        <button type="submit" class="btn btn-success">
            <%= produitEdit != null ? "Modifier" : "Ajouter" %>
        </button>
    </form>
    <% } %>

    <hr/>

    <!-- Tableau des produits -->
    <table class="table table-bordered">
        <tr>
            <th>Id</th>
            <th>Nom</th>
            <th>Description</th>
            <th>Prix</th>
            <th>Actions</th>
        </tr>
        <%
            List<Produit> produits = (List<Produit>) request.getAttribute("produits");
            if (produits != null) {
                for (Produit p : produits) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getNom() %></td>
            <td><%= p.getDescription() %></td>
            <td><%= p.getPrix() %> MAD</td>
            <td>
                <% if (isAdmin) { %>
                    <a href="<%= request.getContextPath() %>/app?action=edit&id=<%= p.getId() %>"
                       class="btn btn-warning btn-sm">Modifier</a>
                    <a href="<%= request.getContextPath() %>/app?action=delete&id=<%= p.getId() %>"
                       onclick="return confirm('Supprimer ce produit ?');"
                       class="btn btn-danger btn-sm">Supprimer</a>
                <% } else { %>
                    <span class="text-muted">Lecture seule</span>
                <% } %>
            </td>
        </tr>
        <% } } %>
    </table>
    
     <a href="<%= request.getContextPath() %>/app?action=logout"
           class="btn btn-outline-light btn-sm"><button type="submit" class="btn btn-danger">Déconnexion </button></a>
    

</div>
</body>
</html>