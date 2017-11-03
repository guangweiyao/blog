(function(window, document, $, Granite, undefined) {
    "use strict";
    var registry = $(window).adaptTo("foundation-registry");

    function createGenericIsInvalid(el) {
        return function() {
            return el.attr("aria-invalid") === "true";
        };
    }

    function createGenericSetInvalid(el) {
        return function(value) {
            el.attr("aria-invalid", "" + value).toggleClass("is-invalid", value);
        };
    }

    registry.register("foundation.adapters", {
        type: "foundation-field",
        selector: "[data-validation=icon-picker]",
        adapter: function(el) {
            var field = $(el);
            var button = field.children(".icons-selector");
            var select = field.children("select");

            return {
                isDisabled: function() {
                    return select.prop("disabled");
                },
                setDisabled: function(disabled) {
                    select.prop("disabled", disabled);
                    input.prop("disabled", disabled);
                },
                isInvalid: createGenericIsInvalid(field),
                setInvalid: createGenericSetInvalid(field)
            };
        }
    });

    registry.register("foundation.validation.selector", {
        submittable: "[data-validation=icon-picker]",
        candidate: "[data-validation=icon-picker]:not([disabled]):not([readonly])",
        exclusion: "[data-validation=icon-picker] *"
    });

    registry.register("foundation.validation.validator", {
        selector: "[data-validation=icon-picker]",
        validate: function (element) {
            var field,
                value;
            field = $(element);
            value = $(field).find(".selected-icon>i").attr("class");
            if (value == "fip-icon-block") {
                return "Please select the icon";
            } else {
                return;
            }
        },
        show: function(element, message, ctx) {
            $(element).closest(".icon-picker-base").adaptTo("foundation-field").setInvalid(true);
            ctx.next();
        },
        clear: function(element, ctx) {
            $(element).closest(".icon-picker-base").adaptTo("foundation-field").setInvalid(false);
            ctx.next();
        }
    });

    var validateHandler = function(e) {
        var iconpicker = $(document).find("[data-validation=icon-picker]");
        var api = $(iconpicker).adaptTo("foundation-validation");
        if (api) {
            api.checkValidity();
            api.updateUI();
        }
    };

    $(document).on("dialog-ready", function () {
        var container = $(this).find("div.fip-icons-container");
        if (container.length > 0) {
            $('.fip-icons-container').on('click', function(){
                setTimeout(validateHandler, 200);
            });
        }
    });

})(window, document, Granite.$, Granite);
