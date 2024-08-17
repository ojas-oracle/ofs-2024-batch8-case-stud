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
import ArrayListDataProvider = require("ojs/ojarraydataprovider");
import "ojs/ojlistview";
import "ojs/ojavatar";
import "ojs/ojlistitemlayout";

class DashboardViewModel {
  private readonly deptArray = JSON.parse('[{"DepartmentId" : 12 , "DepartmentName" : "OFSS(FlexCube)"}]');
    readonly dataprovider = new ArrayDataProvider(this.deptArray, {
      keyAttributes: "DepartmentId",
      implicitSort: [{ attribute: "DepartmentId", direction: "ascending" }],
    });

    private readonly data = [
      {
        id: 1,
        name: "John Doe",
        title: "Oracle Cloud Infrastructure GTM Channel Director EMEA",
        image : "https://thispersondoesnotexist.com/"
      },
      {
        id: 2,
        name: "Jane Doe",
        title: "Global Consulting Head",
        image: "https://thispersondoesnotexist.com/",
      },
    ];

    readonly dataProvider = new ArrayListDataProvider(this.data, {
      keyAttributes: "id",
    });
}

whenDocumentReady().then(function () {
  ko.applyBindings(new DashboardViewModel(), document.getElementById("table"));
});

export = DashboardViewModel;
