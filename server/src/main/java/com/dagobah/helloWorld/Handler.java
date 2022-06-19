package com.dagobah.helloWorld;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Handler extends HttpServlet {
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        Greeter g = new Greeter();
        try {
            PrintWriter out = response.getWriter();
            out.println(g.getText());
            out.flush();
            out.close();
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}