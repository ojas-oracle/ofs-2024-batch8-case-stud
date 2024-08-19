import * as ko from "knockout";
import { whenDocumentReady } from "ojs/ojbootstrap";
import "ojs/ojtable";
import "ojs/ojknockout";
import { RESTDataProvider } from 'ojs/ojrestdataprovider';

type Transaction = { id: number; from : number; to: number , type : string , amount : string , status : string };
type K = Transaction['id'];

class DashboardViewModel {

  readonly keyAttributes = 'id';
  readonly restDataProvider: RESTDataProvider<K, Transaction>;

  constructor(){
  this.restDataProvider = new RESTDataProvider({
    keyAttributes: this.keyAttributes,
    url: "http://localhost:8080/banking/dashboard/22",
    transforms: {
      fetchFirst: {
        request: async (options) => {
          const url = new URL(options.url);
          return new Request(url.href);
        },
        response: async ({ body }) => {
          try {
            var res:any = {}
            let data = [];
            for(let i=0; i<body['result'].length;i++){
              data.push({
                "id" : body['result'][i].id,
                "from" : body['result'][i].from.accountNumber,
                "to" : body['result'][i].to.accountNumber,
                "type" : "",
                "amount" : "₹ "  + body['result'][i].amount,
                "status" : body['result'][i].status
                })
            }
            res['data'] = data
            return res;
          } catch (error) {
            console.log(error)
            return null;
          }
        }
      }}})
    }
}

whenDocumentReady().then(function () {
  ko.applyBindings(new DashboardViewModel(), document.getElementById("table"));
});

export = DashboardViewModel;
