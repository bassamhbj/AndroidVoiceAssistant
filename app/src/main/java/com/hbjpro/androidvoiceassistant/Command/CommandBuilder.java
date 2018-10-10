package com.hbjpro.androidvoiceassistant.Command;

import com.hbjpro.androidvoiceassistant.Tools.Tools;

import java.util.HashMap;

// More than one word for one order, next version

public class CommandBuilder {
    protected HashMap<String, Tools.OrderTy> _mapOrder;

    public CommandBuilder(){
        this._mapOrder = new HashMap<>();
    }

    /* --- Public Methods --- */
    public void createMapOrder(Tools.LanguageCode languageCode){
        switch (languageCode){
            case ENGLISH_AMERICA: _mapOrder = createMapEnglish(); break;
            case SPANISH: _mapOrder = createMapSpanish(); break;
            case JAPANESE: _mapOrder = createMapJapanese(); break;
            default: break;
        }
    }

    /* --- Protected Methods --- */
    protected Tools.OrderTy findOrder(String order){
        return _mapOrder.containsKey(order) ? _mapOrder.get(order) : Tools.OrderTy.INVALID;
    }

    /* --- Private Methods --- */
    private HashMap<String, Tools.OrderTy> createMapEnglish(){
        HashMap<String, Tools.OrderTy> map = new HashMap<>();
        map.put("open", Tools.OrderTy.OPEN_APP);
        map.put("search", Tools.OrderTy.SEARCH_INTERNET);

        return map;
    }
    private HashMap<String, Tools.OrderTy> createMapSpanish(){
        HashMap<String, Tools.OrderTy> map = new HashMap<>();
        map.put("abrir", Tools.OrderTy.OPEN_APP);
        map.put("busca", Tools.OrderTy.SEARCH_INTERNET);

        return map;
    }
    private HashMap<String, Tools.OrderTy> createMapJapanese(){
        HashMap<String, Tools.OrderTy> map = new HashMap<>();

        return map;
    }
}
