package com.hbjpro.androidvoiceassistant.Speech

import com.hbjpro.androidvoiceassistant.Tools.Tools

data class CommandResult(
        var orderTy: Tools.OrderTy = Tools.OrderTy.INVALID,
        var orderArgumen: String = ""
)