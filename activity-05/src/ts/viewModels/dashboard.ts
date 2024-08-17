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
import { RESTDataProvider } from 'ojs/ojrestdataprovider';
import "ojs/ojlistitemlayout";

type D = { id: number; name: string; email: string , username:string };
type K = D['id'];

class DashboardViewModel {

  readonly keyAttributes = 'id';
  readonly restDataProvider: RESTDataProvider<K, D>;
  // private readonly deptArray = JSON.parse('[{"DepartmentId" : 12 , "DepartmentName" : "OFSS(FlexCube)"}]');
  //   readonly dataprovider = new ArrayDataProvider(this.deptArray, {
  //     keyAttributes: "DepartmentId",
  //     implicitSort: [{ attribute: "DepartmentId", direction: "ascending" }],
  //   });

    constructor(){
      this.restDataProvider = new RESTDataProvider({
        keyAttributes: this.keyAttributes,
        url: "https://jsonplaceholder.typicode.com/users",
        // fetchByOffset and fetchByKeys delegate to fetchFirst if their capabilities are not defined
        // so at minimum we must specify transforms for fetchFirst
        transforms: {
          fetchFirst: {
            request: async (options) => {
              // Use size and offset to set the expected paging parameters and create a request.
              // In this example, "size" corresponds to the endpoint' "limit"
              // parameter and "offset" corresponds to the endpoint' "offset" parameter for the mock
              // server.
              const url = new URL(options.url);
              return new Request(url.href);
            },
            response: async ({ body }) => {
              // The mock server sends back a response body with shape { hasMore, totalSzie, data } so
              // we need to extract and return them
              // console.log(body)
              // for (let i = 0; i < body.length; i++) {
              //   console.log(body[i])
              // }
              try {
                var res:any = {}
                res['data'] = [{id: 1, name:"Ojas" , email: "ojask2002@gmail.com" , username:"ojask002"}]
                console.log(res)
                return res;
              } catch (error) {
                console.log(error)
                return null;
              }
             
            }
          }
        }
      });
      console.log(this.restDataProvider)
      console.log(this.dataProvider)
    }

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
