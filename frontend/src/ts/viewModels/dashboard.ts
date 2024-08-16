/**
 * @license
 * Copyright (c) 2014, 2024, Oracle and/or its affiliates.
 * Licensed under The Universal Permissive License (UPL), Version 1.0
 * as shown at https://oss.oracle.com/licenses/upl/
 * @ignore
 */
import * as ko from "knockout";
import { whenDocumentReady } from "ojs/ojbootstrap";
import ArrayDataProvider = require("ojs/ojarraydataprovider");
import "ojs/ojtable";
import "ojs/ojknockout";
class DashboardViewModel {
  private readonly deptArray = JSON.parse('[{"DepartmentId" : 12 , "DepartmentName" : "OFSS"}]');
    readonly dataprovider = new ArrayDataProvider(this.deptArray, {
      keyAttributes: "DepartmentId",
      implicitSort: [{ attribute: "DepartmentId", direction: "ascending" }],
    });
}

whenDocumentReady().then(function () {
  ko.applyBindings(new DashboardViewModel(), document.getElementById("table"));
});

export = DashboardViewModel;
