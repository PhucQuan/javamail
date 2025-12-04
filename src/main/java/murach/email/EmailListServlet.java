package murach.email;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import murach.business.User;
import murach.data.UserDB;
import murach.util.MailUtil;

public class EmailListServlet extends HttpServlet {
    
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
        String url = "/index.html";
        
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";  // default action
        }
        
        // perform action and set URL to appropriate page
        if (action.equals("join")) {
            url = "/index.jsp";  // the "join" page
        }
        else if (action.equals("add")) {
            // get parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            
            // store data in User object
            User user = new User(firstName, lastName, email);
            
            // validate the parameters
            String message;
            if (UserDB.emailExists(user.getEmail())) {
                message = "This email address already exists.<br>" +
                         "Please enter another email address.";
                url = "/index.jsp";
            }
            else {
                message = "";
                url = "/thanks.jsp";
                UserDB.insert(user);
                
                // Send confirmation email
                String to = email;
                String from = "quan610ll@gmail.com"; // TODO: Thay bằng email của bạn
                String subject = "Welcome to our email list";
                String body = "Dear " + firstName + ",\n\n" +
                             "Thank you for joining our email list. " +
                             "We'll use this email address to send you announcements " +
                             "about new products and promotions.\n\n" +
                             "Have a great day!\n\n" +
                             "The Email List Team";
                boolean isBodyHTML = false;
                
                try {
                    MailUtil.sendMail(to, from, subject, body, isBodyHTML);
                } catch (Exception e) {
                    System.out.println("Error sending email: " + e.getMessage());
                    message = "Email sent failed. But you have been added to the list.";
                }
            }
            request.setAttribute("user", user);
            request.setAttribute("message", message);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
