package com.firatulgay;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by FiratUlgay on 12.12.2019.
 */
public class FindAllPersonServlet implements Servlet {

    ArrayList<Person> persons = new ArrayList<>();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Init metodu çalıştı");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("ServletConfig metodu çalıştı");
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        String tip = servletRequest.getParameter("tip");

        if ( tip.equals("kaydet")){
        kisiEkle(servletRequest);
        }else if (tip.equals("listele")) {
            listele(servletResponse);
        }

    }

    private void kisiEkle(ServletRequest servletRequest) {

        String isim = servletRequest.getParameter("isim");
        String soyisim = servletRequest.getParameter("soyisim");
        Person person = new Person();
        person.setAdi(isim);
        person.setSoyadi(soyisim);
        persons.add(person);
        System.out.println(person.getAdi() + " eklendi");
    }

    private void listele(ServletResponse servletResponse) throws IOException {
        for (int i = 0; i < persons.size(); i++) {
            String personAdiSoyadi = persons.get(i).getAdi() + " " + persons.get(i).getSoyadi();
            String html =
                    "<html> \n" +
                            "\t<head> \n" +
                            "\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                            "\t\t<title>Person List</title>\n" +
                            "\t</head>\n" +
                            "\t<body>\n" +
                            personAdiSoyadi +
                            "\t</body>\n" +
                            "</html>\n";
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.getWriter().write(html);
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}