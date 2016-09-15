package com.novosibavto.novosibavtotransport;

import java.util.HashMap;

public class CheckMarsh {

    private static HashMap<String, String> checkMarsh= new HashMap<>();

    public void putMarsh(String typeAvto, String numMarsh) {
        checkMarsh.put(typeAvto, numMarsh);
    }

    public String getTypeAvto() {
        return checkMarsh.keySet().toString();
    }

    public String getNumMarsh() {
        return checkMarsh.values().toString();
    }
}
