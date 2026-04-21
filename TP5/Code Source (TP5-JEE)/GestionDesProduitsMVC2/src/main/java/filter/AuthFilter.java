package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import model.User;
import java.io.IOException;

@WebFilter("/app")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest  req = (HttpServletRequest)  request;
        HttpServletResponse res = (HttpServletResponse) response;

        String action = req.getParameter("action");
        if (action == null) action = "list";

        if (action.equals("login") || action.equals("register")) {
            chain.doFilter(request, response);
            return;
        }

        
        HttpSession session = req.getSession(false);
        User user = (session != null)
                  ? (User) session.getAttribute("currentUser")
                  : null;

        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        boolean isAdminAction = action.equals("add")
                             || action.equals("delete")
                             || action.equals("edit")
                             || action.equals("update");

        if (isAdminAction && !user.getRole().equals("admin")) {
            res.sendRedirect(req.getContextPath() + "/app?action=list");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override public void init(FilterConfig fc) {}
    @Override public void destroy() {}
}