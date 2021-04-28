package gijin.servlet0402.web.frontcontroller.v5.adapter;

import gijin.servlet0402.web.frontcontroller.ModelView;
import gijin.servlet0402.web.frontcontroller.v4.ControllerV4;
import gijin.servlet0402.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4)handler;
        HashMap <String ,String> paramMap= createParamMap(request);
        HashMap <String ,Object> model =new HashMap<>();

        String viewName =controller.process(paramMap,model);
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);
        return mv;
    }
    private HashMap<String, String> createParamMap(HttpServletRequest request) {
        HashMap <String,String> paramMap = new HashMap<String,String>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName ->paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
