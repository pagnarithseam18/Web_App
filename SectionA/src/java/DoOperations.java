/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pagnarith
 */
public class DoOperations extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertRecord</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertRecord at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String option = request.getParameter("option");
        Student student;
        if(option.equals("insert"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            String firstName = request.getParameter("firstName");
            student = new Student();
            student.setFirstName(firstName);
            student.setId(id);
            DatabaseOperations obj = new DatabaseOperations();
            PrintWriter pw=response.getWriter();
            if(obj.insertRecord(student))
            { 
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('SUCCESS!');");
                pw.println("</script>");
            }
            else
            {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('FAILED!');");
                pw.println("</script>");
            }
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
            rd.include(request, response);
        }
        else if(option.equals("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            student = new Student();
            student.setId(id);
            DatabaseOperations obj = new DatabaseOperations();
            PrintWriter pw=response.getWriter();
            if(obj.deleteRecord(student))
            { 
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('SUCCESS!');");
                pw.println("</script>");
            }
            else
            {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('FAILED!');");
                pw.println("</script>");
            }
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
            rd.include(request, response);    
        }
        
        else if (option.equals("update")) 
        {
            int id = Integer.parseInt(request.getParameter("id"));
            String firstName = request.getParameter("firstName");
            student = new Student();
            student.setFirstName(firstName);
            student.setId(id);
            DatabaseOperations obj = new DatabaseOperations();
            PrintWriter pw=response.getWriter();
            if(obj.updateRecord(student))
            { 
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('SUCCESS!');");
                pw.println("</script>");
            }
            else
            {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('FAILED!');");
                pw.println("</script>");
            }
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
            rd.include(request, response);
        }
        else if(option.equals("view"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            DatabaseOperations obj = new DatabaseOperations();
            student = obj.getSingleRecord(id);
            String tableconstruct = "<table><tr><th>sno</th><th>firstName</th></tr><tr><td>" + student.getId() + "</td><td>" + student.getFirstName() + "</td></tr></table>";
            response.getOutputStream().print(tableconstruct);
        }   
        
        else if(option.equals("viewall"))
        {
            DatabaseOperations obj = new DatabaseOperations();
            ArrayList<Student> students = obj.getAllRecords();
            String tableconstruct = "<table><tr><th>sno</th><th>firstName</th></tr>";
            for(Student s: students)
            {
                tableconstruct += "<tr><td>" + s.getId() + "</td><td>" + s.getFirstName() + "</td></tr>";
            }
            tableconstruct += "</table>";
            response.getOutputStream().print(tableconstruct);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
