package com.hbjpro.androidvoiceassistant.Speech

import com.hbjpro.androidvoiceassistant.Tools.Tools

/**
 * Created by bassamhbj on 15/07/2018.
 */
data class CommandResult(
        var orderTy: Tools.OrderTy = Tools.OrderTy.INVALID,
        var orderArgumen: String = ""
)