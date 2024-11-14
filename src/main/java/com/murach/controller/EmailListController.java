package com.murach.controller;

import com.murach.dao.EmailListUserDAO;
import com.murach.dto.EmailListUser;
import com.murach.dto.User;
import com.murach.util.EmailUtil;
import com.murach.util.ValidateUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

@WebServlet(urlPatterns = "/email-list")
public class EmailListController extends HttpServlet {
    EmailListUserDAO emailListUserDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        emailListUserDAO = new EmailListUserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/email-list/join.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action == null) {
            action = "join";
        }

        String url = "/views/email-list/join.jsp";
        if(action.equals("join")) {
            url = "/views/email-list/join.jsp";
        } else if (action.equals("add")) {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");



            EmailListUser emailListUser = new EmailListUser(firstName, lastName, email);
            req.setAttribute("user", emailListUser);

            if(!ValidateUtil.validatePropertiesAreNotNullOrEmpty(emailListUser, Arrays.asList("firstName", "lastName", "email"))) {
                String errorMessage =
                        "ERROR: Please, fill all the fields!";
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/views/email-list/join.jsp").forward(req, resp);
            }

            emailListUserDAO.create(emailListUser);


            // send email to user
            String to = email;
            String from = "tctoan1024@gmail.com";
            String subject = "Welcome to our email list";
            String body = "Dear " + firstName + ",\n\n" +
                    "Thanks for joining our email list. We'll make sure to send " +
                    "you announcements about new products and promotions.\n" +
                    "Have a great day and thanks again!\n\n" +
                    "Toan Tran Cong.";
            boolean isBodyHTML = false;

            try
            {
                EmailUtil.sendMail(to, from, subject, body, isBodyHTML);
//                MailUtilGmail.sendMail(to,from,subject,body,isBodyHTML);
                url = "/views/email-list/thank.jsp";
            }
            catch (MessagingException e)
            {
                String errorMessage =
                        "ERROR: Unable to send email. " +
                                "Check Tomcat logs for details.<br>" +
                                "NOTE: You may need to configure your system " +
                                "as described in chapter 14.<br>" +
                                "ERROR MESSAGE: " + e.getMessage();

                req.setAttribute("errorMessage", errorMessage);
            }

        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(req, resp);
    }
}
