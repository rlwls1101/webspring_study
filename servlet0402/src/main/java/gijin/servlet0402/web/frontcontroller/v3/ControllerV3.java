package gijin.servlet0402.web.frontcontroller.v3;

import gijin.servlet0402.web.frontcontroller.ModelView;

import java.util.Map;
public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}