/*
 * ADOBE CONFIDENTIAL
 *
 * Copyright 2014 Adobe Systems Incorporated
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 */

"use strict";

/**
 * Text foundation component JS backing script
 */
use(["/libs/wcm/foundation/components/utils/AuthoringUtils.js"], function (AuthoringUtils) {

    var CONST = {
        PROP_TEXT: "text",
        PROP_RICH_FORMAT: "textIsRich",
        CONTEXT_TEXT: "text",
        CONTEXT_HTML: "html"
    };
    
    var text = {};
    
    // The actual text content
    text.text = granite.resource.properties[CONST.PROP_TEXT]
            || "";
    
    // Wether the text contains HTML or not
    text.context = granite.resource.properties[CONST.PROP_RICH_FORMAT]
            ? CONST.CONTEXT_HTML : CONST.CONTEXT_TEXT

    // Set up placeholder if empty
    /*
    if (!text.text) {
        text.cssClass = AuthoringUtils.isTouch
                ? "cq-placeholder"
                : "cq-text-placeholder-ipe";
        text.context = CONST.CONTEXT_TEXT;
        
        // only dysplay placeholder in edit mode
        if (typeof wcmmode != "undefined" && wcmmode.isEdit()) {
            text.text = AuthoringUtils.isTouch
            ? ""
            : "Edit text";
        } else {
            text.text = "";
        }
    }*/
                    
    // Adding the constants to the exposed API
    text.CONST = CONST;
    
    return text;
    
});
