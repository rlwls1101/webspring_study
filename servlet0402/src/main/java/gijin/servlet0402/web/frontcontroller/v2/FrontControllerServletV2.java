package gijin.servlet0402.web.frontcontroller.v2;

import gijin.servlet0402.web.frontcontroller.MyView;
import gijin.servlet0402.web.frontcontroller.v2.controller.MemberFormControllerV2;
import gijin.servlet0402.web.frontcontroller.v2.controller.MemberListControllerV2;
import gijin.servlet0402.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name ="frontControllerServletV2", urlPatterns = "/front-controller/v2/*") //하위에 들어오는 녀석은 다 가져온다.
public class FrontControllerServletV2 extends HttpServlet {
    private HashMap<String, ControllerV2> controllerV1Map = new HashMap<>();

    public FrontControllerServletV2() {
        controllerV1Map.put("/front-controller/v2/members/new-form",new MemberFormControllerV2());
        controllerV1Map.put("/front-controller/v2/members/save",new MemberSaveControllerV2());
        controllerV1Map.put("/front-controller/v2/members",new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");
        String requestURI = request.getRequestURI();
        ControllerV2 controller = controllerV1Map.get(requestURI);
        if(controller ==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controller.process(request, response);
        view.render(request,response);
    }
}
