package com.hbjpro.androidvoiceassistant.Command;

import com.hbjpro.androidvoiceassistant.Tools.Tools;

/**
 * Created by bassa on 26/06/2018.
 */

public class OrderHelper extends CommandBuilder {
    private Tools.LanguageCode _languageCode;

    public OrderHelper(Tools.LanguageCode languageCode){
        super();
        this._languageCode = languageCode;
        super.createMapOrder(languageCode);
    }

    /* --- Public Methods --- */
    public Tools.OrderTy getOrder(String order){
        Tools.OrderTy orderTy = super.findOrder(order);

        return orderTy;
    }

    /* --- Private Methods --- */
}
