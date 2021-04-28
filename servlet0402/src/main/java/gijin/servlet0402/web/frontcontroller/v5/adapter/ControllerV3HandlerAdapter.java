package gijin.servlet0402.web.frontcontroller.v5.adapter;

import gijin.servlet0402.web.frontcontroller.ModelView;
import gijin.servlet0402.web.frontcontroller.v3.ControllerV3;
import gijin.servlet0402.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3)  handler ;
        HashMap<String,String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        return mv;
    }
    private HashMap<String, String> createParamMap(HttpServletRequest request) {
        HashMap <String,String> paramMap = new HashMap<String,String>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName ->paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
