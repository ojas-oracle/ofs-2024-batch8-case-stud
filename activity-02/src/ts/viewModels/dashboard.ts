/**
 * @license
 * Copyright (c) 2014, 2024, Oracle and/or its affiliates.
 * Licensed under The Universal Permissive License (UPL), Version 1.0
 * as shown at https://oss.oracle.com/licenses/upl/
 * @ignore
 */
import * as ko from "knockout";
import * as Bootstrap from "ojs/ojbootstrap";
import Message = require("ojs/ojmessaging");
import "oj-c/input-text";
import "ojs/ojknockout";
import "ojs/ojlabel";
import "ojs/ojbutton";
import "ojs/ojformlayout";
import "oj-c/text-area";
import "oj-c/input-password";
import 'ojs/ojdatetimepicker';
import { ojDatePicker } from 'ojs/ojdatetimepicker';
import { IntlConverterUtils } from "@oracle/oraclejet/ojconverterutils-i18n";

class DashboardViewModel {
  error: Message[];
    warning: Message[];
    info: Message[];
    confirmation: Message[];
    value: ko.Observable<string>;
    rawValue: ko.Observable<string>;
  
    constructor() {
      this.error = [{ summary: "summary", detail: "detail", severity: "error" }];
      this.warning = [
        { summary: "summary", detail: "detail", severity: "warning" },
      ];
      this.info = [{ summary: "summary", detail: "detail", severity: "info" }];
      this.confirmation = [
        { summary: "summary", detail: "detail", severity: "confirmation" },
      ];
  
      this.value = ko.observable("");
      this.rawValue = ko.observable("");
    }
}

Bootstrap.whenDocumentReady().then(() => {
  ko.applyBindings(new DashboardViewModel(), document.getElementById("div1"));
});

export = DashboardViewModel;
