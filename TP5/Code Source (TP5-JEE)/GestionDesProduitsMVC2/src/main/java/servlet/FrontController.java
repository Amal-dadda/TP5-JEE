package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Produit;
import model.User;
import service.ProduitService;
import service.ProduitServiceImp;
import service.UserService;
import service.UserServiceImp;
import java.io.IOException;

@WebServlet("/app")
public class FrontController extends HttpServlet {

    private ProduitService produitService = ProduitServiceImp.getInstance();
    private UserService    userService    = UserServiceImp.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        handle(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        handle(req, res);
    }

    private void handle(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {

            // gestion des produits
            case "list":
                req.setAttribute("produits", produitService.getAll());
                req.getRequestDispatcher("/index.jsp")
                   .forward(req, res);
                break;

            case "add":
                try {
                    String nom  = req.getParameter("nom");
                    String desc = req.getParameter("description");
                    double prix = Double.parseDouble(req.getParameter("prix"));
                    produitService.add(new Produit(nom, desc, prix));
                } catch (NumberFormatException e) {
                    req.setAttribute("erreur", "Prix invalide !");
                    req.setAttribute("produits", produitService.getAll());
                    req.getRequestDispatcher("/index.jsp")
                       .forward(req, res);
                    return;
                }
                res.sendRedirect("app?action=list");
                break;

            case "delete":
                int delId = Integer.parseInt(req.getParameter("id"));
                produitService.delete(delId);
                res.sendRedirect("app?action=list");
                break;

            case "edit":
                int editId = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("produitEdit", produitService.getById(editId));
                req.setAttribute("produits", produitService.getAll());
                req.getRequestDispatcher("/index.jsp")
                   .forward(req, res);
                break;

            case "update":
            	try {
                int updId   = Integer.parseInt(req.getParameter("id"));
                String nom  = req.getParameter("nom");
                String desc = req.getParameter("description");
                double prix = Double.parseDouble(req.getParameter("prix"));
                Produit p   = new Produit(nom, desc, prix);
                p.setId(updId);
                produitService.update(p);
                res.sendRedirect("app?action=list");
            	} catch (NumberFormatException e) {
                    
                    req.setAttribute("erreur", "Prix invalide !");
                    req.setAttribute("produits", produitService.getAll());
                    
                    int updId = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("produitEdit", produitService.getById(updId));
                    req.getRequestDispatcher("/index.jsp").forward(req, res);
                }
                break;

             // Authentification:
            case "login":
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                User user = userService.loginUser(username, password);
                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("currentUser", user);
                    res.sendRedirect("app?action=list");
                } else {
                    req.setAttribute("erreur", "Identifiants incorrects !");
                    req.getRequestDispatcher("login.jsp")
                       .forward(req, res);
                }
                break;

            case "register":
                String uname = req.getParameter("username");
                String pass  = req.getParameter("password");
                String role  = req.getParameter("role");
                
                User newUser = new User(uname, pass, role);
                userService.registerUser(newUser);
                res.sendRedirect("login.jsp");
               
                break;

            case "logout":
                HttpSession s = req.getSession(false);
                if (s != null) s.invalidate();
                res.sendRedirect("login.jsp");
                break;

            default:
                res.sendRedirect("app?action=list");
        }
    }
}