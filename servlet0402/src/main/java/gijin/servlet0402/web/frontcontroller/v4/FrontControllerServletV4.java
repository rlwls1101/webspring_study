package gijin.servlet0402.web.frontcontroller.v4;

import gijin.servlet0402.web.frontcontroller.MyView;
import gijin.servlet0402.web.frontcontroller.v4.controller.MemberFormControllerV4;
import gijin.servlet0402.web.frontcontroller.v4.controller.MemberListControllerV4;
import gijin.servlet0402.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name ="frontControllerServletV4", urlPatterns = "/front-controller/v4/*") //하위에 들어오는 녀석은 다 가져온다.
public class FrontControllerServletV4 extends HttpServlet {
    private HashMap<String, ControllerV4> controllerV1Map = new HashMap<>();

    public FrontControllerServletV4() {
        controllerV1Map.put("/front-controller/v4/members/new-form",new MemberFormControllerV4());
        controllerV1Map.put("/front-controller/v4/members/save",new MemberSaveControllerV4());
        controllerV1Map.put("/front-controller/v4/members",new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");
        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerV1Map.get(requestURI);
        if(controller ==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //paramMap
        HashMap<String, String> paramMap = createParamMap(request);
        HashMap<String,Object> model =new HashMap<>();

        String viewName = controller.process(paramMap, model);
        //논리이름만 알수있다.
        MyView view = viewResolver(viewName);

        view.render(model,request,response);
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
