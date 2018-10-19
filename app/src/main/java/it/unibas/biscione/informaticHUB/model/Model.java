package it.unibas.biscione.informaticHUB.model;

import java.util.HashMap;
import java.util.Map;

public class Model {

    private final Map<String, Object> mapBean = new HashMap<String, Object>();

    public void putBean(String name, Object bean) {
        this.mapBean.put(name, bean);
    }

    public Object getBean(String name) {
        return this.mapBean.get(name);
    }

}
