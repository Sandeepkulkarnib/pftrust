package com.coreintegra.pftrust.util;

public class SettlementEmailTemplate {

    public static String TEMPLATE = "<!doctype html>\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "\n" +
            "<head>\n" +
            "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
            "    <!-- Turn off iOS phone number autodetect -->\n" +
            "    <meta name=\"format-detection\" content=\"telephone=no\">\n" +
            "    <style>\n" +
            "        body, p {\n" +
            "            font-family: 'Helvetica Neue', Helvetica,Arial, sans-serif;\n" +
            "            -webkit-font-smoothing: antialiased;\n" +
            "            -webkit-text-size-adjust: none;\n" +
            "        }\n" +
            "        table {\n" +
            "            border-collapse: collapse;\n" +
            "            border-spacing: 0;\n" +
            "            border: 0;\n" +
            "            padding: 0;\n" +
            "        }\n" +
            "        img {\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "        }\n" +
            "\n" +
            "        .content {\n" +
            "            width: 600px;\n" +
            "        }\n" +
            "\n" +
            "        .no_text_resize {\n" +
            "            -moz-text-size-adjust: none;\n" +
            "            -webkit-text-size-adjust: none;\n" +
            "            -ms-text-size-adjust: none;\n" +
            "            text-size-adjust: none;\n" +
            "        }\n" +
            "\n" +
            "        /* Media Queries */\n" +
            "        @media all and (max-width: 600px) {\n" +
            "\n" +
            "            table[class=\"content\"] {\n" +
            "                width: 100% !important;\n" +
            "            }\n" +
            "\n" +
            "            tr[class=\"grid-no-gutter\"] td[class=\"grid__col\"] {\n" +
            "                padding-left: 0 !important;\n" +
            "                padding-right: 0 !important;\n" +
            "            }\n" +
            "\n" +
            "            td[class=\"grid__col\"] {\n" +
            "                padding-left: 18px !important;\n" +
            "                padding-right: 18px !important;\n" +
            "            }\n" +
            "\n" +
            "            table[class=\"small_full_width\"] {\n" +
            "                width: 100% !important;\n" +
            "                padding-bottom: 10px;\n" +
            "            }\n" +
            "\n" +
            "            a[class=\"header-link\"] {\n" +
            "                margin-right: 0 !important;\n" +
            "                margin-left: 10px !important;\n" +
            "            }\n" +
            "\n" +
            "            a[class=\"btn\"] {\n" +
            "                width: 100%;\n" +
            "                border-left-width: 0px !important;\n" +
            "                border-right-width: 0px !important;\n" +
            "            }\n" +
            "\n" +
            "            table[class=\"col-layout\"] {\n" +
            "                width: 100% !important;\n" +
            "            }\n" +
            "\n" +
            "            td[class=\"col-container\"] {\n" +
            "                display: block !important;\n" +
            "                width: 100% !important;\n" +
            "                padding-left: 0 !important;\n" +
            "                padding-right: 0 !important;\n" +
            "            }\n" +
            "\n" +
            "            td[class=\"col-nav-items\"] {\n" +
            "                display: inline-block !important;\n" +
            "                padding-left: 0 !important;\n" +
            "                padding-right: 10px !important;\n" +
            "                background: none !important;\n" +
            "            }\n" +
            "\n" +
            "            img[class=\"col-img\"] {\n" +
            "                height: auto !important;\n" +
            "                max-width: 520px !important;\n" +
            "                width: 100% !important;\n" +
            "            }\n" +
            "\n" +
            "            td[class=\"col-center-sm\"] {\n" +
            "                text-align: center;\n" +
            "            }\n" +
            "\n" +
            "            tr[class=\"footer-attendee-cta\"] > td[class=\"grid__col\"] {\n" +
            "                padding: 24px 0 0 !important;\n" +
            "            }\n" +
            "\n" +
            "            td[class=\"col-footer-cta\"] {\n" +
            "                padding-left: 0 !important;\n" +
            "                padding-right: 0 !important;\n" +
            "            }\n" +
            "\n" +
            "            td[class=\"footer-links\"] {\n" +
            "                text-align: left !important;\n" +
            "            }\n" +
            "\n" +
            "            .hide-for-small {\n" +
            "                display: none !important;\n" +
            "            }\n" +
            "\n" +
            "            .ribbon-mobile {\n" +
            "                line-height: 1.3 !important;\n" +
            "            }\n" +
            "\n" +
            "            .small_full_width {\n" +
            "                width: 100% !important;\n" +
            "                padding-bottom: 10px;\n" +
            "            }\n" +
            "\n" +
            "            .table__ridge {\n" +
            "                height: 7px !important;\n" +
            "            }\n" +
            "\n" +
            "            .table__ridge img {\n" +
            "                display: none !important;\n" +
            "            }\n" +
            "\n" +
            "            .table__ridge--top {\n" +
            "                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_top_fullx2.jpg) !important;\n" +
            "                background-size: 170% 7px;\n" +
            "            }\n" +
            "\n" +
            "            .table__ridge--bottom {\n" +
            "                background-image: url(https://cdn.evbstatic.com/s3-s3/marketing/emails/modules/ridges_bottom_fullx2.jpg) !important;\n" +
            "                background-size: 170% 7px;\n" +
            "            }\n" +
            "\n" +
            "            .summary-table__total {\n" +
            "                padding-right: 10px !important;\n" +
            "            }\n" +
            "\n" +
            "            .app-cta {\n" +
            "                display: none !important;\n" +
            "            }\n" +
            "\n" +
            "            .app-cta__mobile {\n" +
            "                width: 100% !important;\n" +
            "                height: auto !important;\n" +
            "                max-height: none !important;\n" +
            "                overflow: visible !important;\n" +
            "                float: none !important;\n" +
            "                display: block !important;\n" +
            "                margin-top: 12px !important;\n" +
            "                visibility: visible;\n" +
            "                font-size: inherit !important;\n" +
            "            }\n" +
            "\n" +
            "            /* List Event Cards */\n" +
            "            .list-card__header {\n" +
            "                width: 130px !important;\n" +
            "            }\n" +
            "\n" +
            "            .list-card__label {\n" +
            "                width: 130px !important;\n" +
            "            }\n" +
            "\n" +
            "            .list-card__image-wrapper {\n" +
            "                width: 130px !important;\n" +
            "                height: 65px !important;\n" +
            "            }\n" +
            "\n" +
            "            .list-card__image {\n" +
            "                max-width: 130px !important;\n" +
            "                max-height: 65px !important;\n" +
            "            }\n" +
            "\n" +
            "            .list-card__body {\n" +
            "                padding-left: 10px !important;\n" +
            "            }\n" +
            "\n" +
            "            .list-card__title {\n" +
            "                margin-bottom: 10px !important;\n" +
            "            }\n" +
            "\n" +
            "            .list-card__date {\n" +
            "                padding-top: 0 !important;\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:landscape) {\n" +
            "            .ribbon-mobile {\n" +
            "                line-height: 1.3 !important;\n" +
            "            }\n" +
            "\n" +
            "            .ribbon-mobile__text {\n" +
            "                padding: 0 !important;\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        @media all and (device-width: 768px) and (device-height: 1024px) and (orientation:portrait) {\n" +
            "            .ribbon-mobile {\n" +
            "                line-height: 1.3 !important;\n" +
            "            }\n" +
            "\n" +
            "            .ribbon-mobile__text {\n" +
            "                padding: 0 !important;\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        @media screen and (min-device-height:480px) and (max-device-height:568px), (min-device-width : 375px) and (max-device-width : 667px) and (-webkit-min-device-pixel-ratio : 2), (min-device-width : 414px) and (max-device-width : 736px) and (-webkit-min-device-pixel-ratio : 3) {\n" +
            "\n" +
            "            .hide_for_iphone {\n" +
            "                display: none !important;\n" +
            "            }\n" +
            "\n" +
            "            .passbook {\n" +
            "                width: auto !important;\n" +
            "                height: auto !important;\n" +
            "                line-height: auto !important;\n" +
            "                visibility: visible !important;\n" +
            "                display: block !important;\n" +
            "                max-height: none !important;\n" +
            "                overflow: visible !important;\n" +
            "                float: none !important;\n" +
            "                text-indent: 0 !important;\n" +
            "                font-size: inherit !important;\n" +
            "            }\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<!-- Global container with background styles. Gmail converts BODY to DIV so we\n" +
            "  lose properties like BGCOLOR. -->\n" +
            "\n" +
            "<body border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" bgcolor=\"#F7F7F7\" style=\"margin: 0;\">\n" +
            "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" bgcolor=\"#F7F7F7\">\n" +
            "    <tr>\n" +
            "        <td>\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            <table width=\"600\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" bgcolor=\"#FFFFFF\">\n" +
            "                <tr>\n" +
            "                    <td>\n" +
            "            <![endif]-->\n" +
            "            <table class=\"content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" bgcolor=\"#F7F7F7\" style=\"width: 600px; max-width: 600px;\">\n" +
            "                <tr>\n" +
            "                    <td colspan=\"2\" style=\"background: #fff; border-radius: 8px;\">\n" +
            "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "                            <tr>\n" +
            "                                <td style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;\">\n" +
            "                            <tr class=\"\">\n" +
            "                                <td class=\"grid__col\" style=\"font-family: 'Helvetica neue', Helvetica, arial, sans-serif; padding: 32px 40px; \">\n" +
            "\n" +
            "                                    <h2 style=\"color: #404040; font-weight: 300; margin: 0 0 12px 0; font-size: 24px; line-height: 30px; font-family: 'Helvetica neue', Helvetica, arial, sans-serif; \">\n" +
            "\n" +
            "                                        Dear {{name}},\n" +
            "\n" +
            "                                    </h2>\n" +
            "\n" +
            "                                    <p style=\"color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: 'Helvetica neue', Helvetica, arial, sans-serif; \" class=\"\">We have credited to your account number : {{accountNumber}} with {{bankName}}. on {{paymentDate}} for amount of {{amount}}\n" +
            "                                        on behalf of Mahindra & Mahindra Ltd. Staff Provident Fund towards full & Final Settlement of your Provident Fund A/c as per statement in the attachment.</p>\n" +
            "                                    <br>\n" +
            "                                    <br>\n" +
            "                                    <br>\n" +
            "                                    <br>\n" +
            "                                    <p style=\"color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: 'Helvetica neue', Helvetica, arial, sans-serif; \" class=\"\">This is system generated mail. Do not reply.</p>\n" +
            "                                    <br>\n" +
            "                                    <br>\n" +
            "                                    <br>\n" +
            "                                    <p style=\"color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: 'Helvetica neue', Helvetica, arial, sans-serif; \" class=\"\">Thanks and Regards</p>\n" +
            "                                    <br>\n" +
            "                                    <p style=\"color: #282828; font-weight: 400; font-size: 15px; line-height: 21px; font-family: 'Helvetica neue', Helvetica, arial, sans-serif; \" class=\"\">M&M PF Trust</p>\n" +
            "                                </td>\n" +
            "                            </tr>\n" +
            "                            </td>\n" +
            "                            </tr>\n" +
            "                        </table>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </table>\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            </td>\n" +
            "            </tr>\n" +
            "            </table>\n" +
            "            <![endif]-->\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            <table width=\"600\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                <tr>\n" +
            "                    <td>\n" +
            "            <![endif]-->\n" +
            "            <table class=\"content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 600px; max-width: 600px; font-family: Helvetica, Arial, sans-serif;\">\n" +
            "                <tr>\n" +
            "                    <td style=\"padding-top: 24px;\">\n" +
            "                        <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
            "                            <tr>\n" +
            "                                <td style=\"background-color: #dedede;  width: 100%; font-size: 1px; height: 1px; line-height: 1px;\">&nbsp;</td>\n" +
            "                            </tr>\n" +
            "                        </table>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </table>\n" +
            "            <!--[if (gte mso 9)|(IE)]>\n" +
            "            </td>\n" +
            "            </tr>\n" +
            "            </table>\n" +
            "            <![endif]-->\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "</table>\n" +
            "</body>\n" +
            "\n" +
            "</html>";

}
