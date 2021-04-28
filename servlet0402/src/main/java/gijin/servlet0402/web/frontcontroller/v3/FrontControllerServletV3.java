package gijin.servlet0402.web.frontcontroller.v3;

import gijin.servlet0402.web.frontcontroller.ModelView;
import gijin.servlet0402.web.frontcontroller.MyView;
import gijin.servlet0402.web.frontcontroller.v3.controller.MemberFormControllerV3;
import gijin.servlet0402.web.frontcontroller.v3.controller.MemberListControllerV3;
import gijin.servlet0402.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name ="frontControllerServletV3", urlPatterns = "/front-controller/v3/*") //하위에 들어오는 녀석은 다 가져온다.
public class FrontControllerServletV3 extends HttpServlet {
    private HashMap<String, ControllerV3> controllerV1Map = new HashMap<>();

    public FrontControllerServletV3() {
        controllerV1Map.put("/front-controller/v3/members/new-form",new MemberFormControllerV3());
        controllerV1Map.put("/front-controller/v3/members/save",new MemberSaveControllerV3());
        controllerV1Map.put("/front-controller/v3/members",new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerV1Map.get(requestURI);
        if(controller ==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //paramMap
        HashMap<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        //논리이름만 알수있다.
        String viewName = mv.getViewname();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(),request,response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private HashMap<String, String> createParamMap(HttpServletRequest request) {
        HashMap <String,String> paramMap = new HashMap<String,String>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName ->paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
