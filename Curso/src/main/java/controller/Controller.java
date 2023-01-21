package controller;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.DAO;
import model.JavaBeans;
/**
 *
 * @author Pedro Henrqiue
 */
public class Controller extends HttpServlet {
    JavaBeans contato = new JavaBeans();
    DAO dao = new DAO();
   
    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();
        switch (action) {
            case "/main":
                listarContatos(request, response);
                break;
            case "/novo":
                adicionarContato(request, response);
                break;
            case "/editar":
                editar(request, response);
                break;
            case "/editado":
                editarContato(request, response);
                break;
            case "/deletar":
                deletar(request,response);
                break;
            case "/report":
                relatorio(request,response);
                break;
            default:
                break;
        }

    }

    /**
     * Método inicial para listar todos os contatos no banco de dados
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void listarContatos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<JavaBeans> l = dao.listar();
        request.setAttribute("contatos", l);
        RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
        rd.forward(request, response);
    }

    /**
     * Método para adicionar contato
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void adicionarContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
        contato.setNome(request.getParameter("nome"));
        contato.setTelefone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));
        dao.add(contato);
        response.sendRedirect("main");
    }

    /**
     * Método para abrir o contato desejado , para ser editado
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        contato.setId(Long.parseLong(request.getParameter("id")));
        dao.procurarContato(contato);
        request.setAttribute("id", contato.getId());
        request.setAttribute("nome", contato.getNome());
        request.setAttribute("telefone", contato.getTelefone());
        request.setAttribute("email", contato.getEmail());
        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
        rd.forward(request, response);

    }

    /**
     * Método para editar contato
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void editarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        contato.setId( Long.parseLong(request.getParameter("id")));
        contato.setNome(request.getParameter("nome"));
        contato.setTelefone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));
        dao.editar(contato);
        response.sendRedirect("main");
    }
    
    /**
     * Método para deletar contato
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void deletar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
         contato.setId(Long.parseLong(request.getParameter("id")));
         dao.deletar(contato);
         response.sendRedirect("main");
    }
     
    /**
     * Método para gerar um relatorio de uma lista de contato
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void relatorio(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Document document = new Document();
        try {
            response.setContentType("Apllication/pdf");
            response.addHeader("Content-Disposition", "inline; filename="+"contatos.pdf");
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new Paragraph("Relatório de contatos:"));
            document.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(4);
            table.addCell( new PdfPCell(new Paragraph("Id")));
            table.addCell(new PdfPCell(new Paragraph("Nome")));
            table.addCell(new PdfPCell(new Paragraph("Telefone")));
            table.addCell(new PdfPCell(new Paragraph("E-mail")));
            ArrayList<JavaBeans> l = dao.listar();
            for(JavaBeans c : l ){
                table.addCell(String.valueOf(c.getId()));
                table.addCell(c.getNome());
                table.addCell(c.getTelefone());
                table.addCell(c.getEmail());
            }
            document.add(table);
            document.close();
        } catch (DocumentException | IOException e) {
            System.out.println("Erro de relatório ==>> " + e.getMessage());
            document.close();
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
        processRequest(request, response);
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

