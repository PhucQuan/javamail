package murach.admin;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.List;

import murach.business.User;
import murach.data.UserDB;

public class UserServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/index.jsp";
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "display_users";
        }
        
        if (action.equals("display_users")) {
            url = "/users.jsp";
            List<User> users = UserDB.selectUsers();
            request.setAttribute("users", users);
        }
        else if (action.equals("display_user")) {
            url = "/user.jsp";
            String email = request.getParameter("email");
            User user = UserDB.selectUser(email);
            request.setAttribute("user", user);
        }
        else if (action.equals("update_user")) {
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            
            User user = new User(firstName, lastName, email);
            UserDB.update(user);
            
            url = "/users.jsp";
            List<User> users = UserDB.selectUsers();
            request.setAttribute("users", users);
        }
        else if (action.equals("delete_user")) {
            String email = request.getParameter("email");
            User user = UserDB.selectUser(email);
            if (user != null) {
                UserDB.delete(user);
            }
            
            url = "/users.jsp";
            List<User> users = UserDB.selectUsers();
            request.setAttribute("users", users);
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
