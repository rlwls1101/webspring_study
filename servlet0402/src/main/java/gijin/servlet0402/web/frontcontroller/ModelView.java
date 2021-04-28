package gijin.servlet0402.web.frontcontroller;

import java.util.HashMap;

public class ModelView {
    private String viewname;
    private HashMap<String, Object> model = new HashMap<String, Object>();

    public ModelView(String viewname) {
        this.viewname = viewname;
    }

    public String getViewname() {
        return viewname;
    }

    public void setViewname(String viewname) {
        this.viewname = viewname;
    }

    public HashMap<String, Object> getModel() {
        return model;
    }

    public void setModel(HashMap<String, Object> model) {
        this.model = model;
    }
}